package com.internet.speed.test.service;

import com.fasterxml.uuid.Generators;
import com.internet.speed.test.domain.InternetSpeedDomain;
import com.internet.speed.test.dao.InternetSpeedDao;
import com.internet.speed.test.exception.InternetSpeedTestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Implementation for InternetSpeedService
 */
@Slf4j
@Service
public class InternetSpeedServiceImpl  implements InternetSpeedService{

  @Autowired
  InternetSpeedDao internetSpeedDao;

  @Override
  public List<InternetSpeedDomain> getInternetSpeeds() {
    return internetSpeedDao.findAll();
  }

  @Override
  public InternetSpeedDomain getInternetSpeed() {
    return  evaluateInternetSpeed();
  }

  public InternetSpeedDomain evaluateInternetSpeed(){
    ProcessBuilder processBuilder = new ProcessBuilder("fast-speedtest", "YXNkZmFzZGxmbnNkYWZoYXNkZmhrYWxm");
    processBuilder.redirectErrorStream(true);
    Process process;
    List<String> result;
    try {
      process = processBuilder.start();
      ProcessReadTask task = new ProcessReadTask(process.getInputStream());
      ExecutorService pool = Executors.newSingleThreadExecutor();
      Future<List<String>> future = pool.submit(task);
      result = future.get();
    } catch (IOException | InterruptedException| ExecutionException e) {
      log.error("Error calling Fast for evaluating internet speed {}", e.getMessage());
      throw new InternetSpeedTestException("Error calling Fast for evaluating internet speed");
    }
    String internetSpeedResponse = result
        .stream()
        .findFirst()
        .orElseThrow(() -> new InternetSpeedTestException("Error: Result not found"));

    //UNKNOWN: Unknown error
    if(internetSpeedResponse.contains("UNKNOWN")|| internetSpeedResponse.contains("error")){
      log.error("Error response from Fast for evaluating internet speed {}", internetSpeedResponse);
      throw new InternetSpeedTestException("Error response from Fast for evaluating internet speed:"+internetSpeedResponse);
    }
    String[] splitInternetSpeed = internetSpeedResponse.split("\\s");
    if(!ObjectUtils.isEmpty(splitInternetSpeed)&& splitInternetSpeed.length>=2){
      String unit = splitInternetSpeed[2];
      double speedDouble;
      try {
        speedDouble = Double.parseDouble(splitInternetSpeed[1]);
      } catch (NumberFormatException e) {
       log.error("Unable to convert speed to double {}", e.getMessage());
        throw new InternetSpeedTestException("Unable to convert Internet speed to double");
      }
      String id = Generators.timeBasedGenerator().generate().toString();
      return InternetSpeedDomain.builder().id(id).unit(unit).speed(speedDouble).timestamp(new Date().toString()).build();
    }
   return null;
  }

  private static class ProcessReadTask implements Callable<List<String>> {

    private final InputStream inputStream;

    public ProcessReadTask(InputStream inputStream) {
      this.inputStream = inputStream;
    }

    @Override
    public List<String> call() {
      return new BufferedReader(new InputStreamReader(inputStream))
          .lines()
          .collect(Collectors.toList());
    }
  }

}
