package com.github.jasongoetz.asanajama.exception;

public class GatewayException extends Throwable {

    public GatewayException(String message, Exception exc) {
        super(exc);
    }

    public GatewayException(String message) {
    }

}
