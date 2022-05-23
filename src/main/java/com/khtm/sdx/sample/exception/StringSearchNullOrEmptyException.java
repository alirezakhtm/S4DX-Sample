package com.khtm.sdx.sample.exception;

/**
 * This exception occurred when input search is empty or null
 *
 * @author Alireza Khatami Doost [alireza.khtm@gmail.com]
 * */
public class StringSearchNullOrEmptyException extends RuntimeException{
    public StringSearchNullOrEmptyException() {
        super("String search is null or empty.");
    }
}
