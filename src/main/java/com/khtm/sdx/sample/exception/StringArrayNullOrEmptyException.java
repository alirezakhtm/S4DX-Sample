package com.khtm.sdx.sample.exception;

/**
 * This exception occurred when String array in constructor of Finder class is empty or null
 *
 * @author Alireza Khatami Doost [alireza.khtm@gmail.com]
 * */
public class StringArrayNullOrEmptyException extends RuntimeException{
    public StringArrayNullOrEmptyException() {
        super("String array is null or empty.");
    }
}
