package ru.gb.mvc.database;

public class FindInDatabaseException extends RuntimeException{

    public FindInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
