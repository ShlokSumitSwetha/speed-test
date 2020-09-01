
package com.internet.speed.test.exception;

/**
 * Custom exception for Internet Speed
 * @author Sumith Ksheerasagar
 */
public class InternetSpeedTestException extends RuntimeException {

  public InternetSpeedTestException() {
    super();
  }

  public InternetSpeedTestException(String message) {
    super(message);
  }

  public InternetSpeedTestException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
