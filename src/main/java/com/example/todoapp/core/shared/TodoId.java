package com.example.todoapp.core.shared;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TodoId implements Serializable {

    private String value;

    //기본 생성자
    protected TodoId(){
    }

    private TodoId(String value) {
       if(value == null || value.isBlank()){
           throw new IllegalArgumentException("TODO-ID값은 null이거나 빈값일 수 없습니다.");
       }
       this.value = value;
    }

    public static TodoId of(String value){
        return new TodoId(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        TodoId todoId = (TodoId) obj;
        return Objects.equals(value, todoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    @Override
    public String toString() {
        return value;
    }
}
