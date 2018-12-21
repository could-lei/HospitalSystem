package com.example.demo.error;

/**
 * Created by next on 2018/12/9.
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
