package com.example.todoapp.core.shared;

import com.example.todoapp.core.shared.identifier.TodoId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
// given
// when
// then
class TodoIdTest {
    @Test
    @DisplayName("유효한 값으로 TodoId를 생성할 수 있다")
    void createTodoIdWithValidValue() {

        // given
        String newValue = UUID.randomUUID().toString();
        // when
        TodoId todoId = TodoId.of(newValue);
        // then

        assertNotNull(todoId);
        assertEquals(newValue,todoId.toString());
    }


    @Test
    @DisplayName("null 값으로 TodoId 생성시 예외가 발생한다")
    void createTodoIdWithNullValue() {
        try{
            // given
            String newValue = null;
            // when
            TodoId todoId = TodoId.of(newValue);
            // then
        }catch(IllegalArgumentException e){
            assertEquals("TODO-ID값은 null이거나 빈값일 수 없습니다.",e.getMessage());
        }
    }
    @Test
    @DisplayName("빈 문자열로 TodoId 생성시 예외가 발생한다")
    void createTodoIdWithEmptyValue() {
        try{
            String newValue = "";
            TodoId todoId = TodoId.of(newValue);
        }catch(IllegalArgumentException e){
            assertEquals("TODO-ID값은 null이거나 빈값일 수 없습니다.",e.getMessage());
        }
    }

    @Test
    @DisplayName("같은 값을 가진 TodoId는 동등하다")
    void todoIdsWithSameValueAreEqual() {
        // given
        String newValue = "test-value";
        // when
        TodoId todoId1 = TodoId.of(newValue);
        TodoId todoId2 = TodoId.of(newValue);
        assertEquals(todoId1,todoId2);
        System.out.println("todoId1.hashCode():" + todoId1.hashCode());
        System.out.println("todoId2.hashCode():" + todoId2.hashCode());
        assertEquals(todoId1.hashCode(),todoId2.hashCode());
    }

    @Test
    @DisplayName("다른 값을 가진 TodoId는 동등하지 않다")
    void todoIdsWithDifferentValueAreNotEqual() {
        // given
        String newValue = "test-value";
        String newValue2 = "test-value2";
        // when
        TodoId todoId1 = TodoId.of(newValue);
        TodoId todoId2 = TodoId.of(newValue2);
        assertNotEquals(todoId1,todoId2);
    }

}