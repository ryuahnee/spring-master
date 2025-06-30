package com.example.todoapp.core.todo.domain.support;

import com.example.todoapp.core.shared.identifier.TodoId;
import com.example.todoapp.core.todo.domain.TodoIdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDBasedTodoIdGenerator implements TodoIdGenerator {

    @Override
    public TodoId generateId() {
        return TodoId.of(UUID.randomUUID().toString());
    }
}
