package com.example.todoapp.core.todo.domain;

/**
 * 할일 소유자가 일치하지 않을 때 발생하는 예외
 */
public class TodoOwnerMismatchException extends RuntimeException {

    // 1. 메시지만 받는 생성자
    public TodoOwnerMismatchException(String message) {
        super(message);
    }
    // 2. 메시지와 원인을 받는 생성자
    public TodoOwnerMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
