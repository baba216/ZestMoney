package com.shubham.zest_money.exception;

public class IllegalGameException extends RuntimeException {

  private static final long serialVersionUID = 5948680486436694863L;

  public IllegalGameException(String message) {
    super(message);
  }
}
