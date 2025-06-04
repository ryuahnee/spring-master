package com.example.todoapp.core.todo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoStateTest {

    @Test
    @DisplayName("literal 값으로 TodoState를 찾는다")
    void getTodoStateValue() {
        String value = "active";
        String value2 = "completed";
        TodoState todoState = TodoState.fromLiteral(value);
        TodoState todoState2 = TodoState.fromLiteral(value2);

        System.out.println("todoState: " + todoState);
        System.out.println("todoState2: " + todoState2);

        assertEquals(TodoState.ACTIVE,todoState);
        assertEquals(TodoState.COMPLETED,todoState2);
    }

    @Test
    @DisplayName("literal에 해당하는 상태가 없는 경우-IllegalArgumentException")
    void getTodoStateValueWithNullValue(){
        String value = "test";
        try{
            TodoState todoState = TodoState.fromLiteral(value);
            System.out.println("todoState: " + todoState);
        }catch (IllegalArgumentException e){
            assertEquals("해당하는 상태가 없습니다.",e.getMessage());
        }
    }



}