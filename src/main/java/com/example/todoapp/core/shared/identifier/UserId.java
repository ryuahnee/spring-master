package com.example.todoapp.core.shared.identifier;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserId implements Serializable {

    private String id;

    public UserId() {
    }

    public UserId(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("user_id는 null이거나 빈값일 수 없습니다.");
        }
        this.id = id;
    }

    private static UserId of(String id) {
       return new UserId(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
       UserId userId = (UserId) obj;
        return Objects.equals(id, userId.id);
    }

    @Override
    public String toString() {
        return this.toString();
    }
}
