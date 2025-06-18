package com.example.todoapp.core.todo.domain;

import com.example.todoapp.core.shared.identifier.TodoId;

public interface TodoIdGenerator {
    TodoId generateId();
}
