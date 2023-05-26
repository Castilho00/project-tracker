package com.eu.castilho.project.tracker.services.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException (String msg){
        super(msg);
    }
}
