package com.example.todoapp.core.todo.domain;

import com.example.todoapp.core.shared.identifier.TodoId;
import com.example.todoapp.core.shared.identifier.UserId;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="todos")
public class Todo implements Serializable{
    /*
    사용자는 할일 목록을 조회할 수 있습니다
    사용자는 새로운 할일을 등록할 수 있습니다
    사용자는 등록된 할일을 변경할 수 있습니다
    사용자는 등록된 할일을 완료할 수 있습니다
    사용자는 완료된 할일을 정리할 수 있습니다
     */
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name="id"))
    private TodoId id;
    private String text;   //내용
    @Embedded
    @AttributeOverride(name = "id",column = @Column(name="owner_id"))
    private UserId owner;   //할일 소유자
    private TodoState state;
    private LocalDateTime createdDate;  // 생성일
    private LocalDateTime lastModifiedDate; //마지막 수정일시

    //JPA용 기본 생성자 (private으로 선언)
    private Todo(){}

    //매개변수가 있는 생성자 (id, text, createdDate)
    public Todo(TodoId id, String text , LocalDateTime createdDate) {
        this.id = id;
        this.text = text;
        this.createdDate = createdDate;
        this.state = TodoState.ACTIVE;  // ← 추가 필요
        this.lastModifiedDate = createdDate;  // ← 추가 필요
    }

    //매개변수가 있는 생성자 (id, text, owner, createdDate)
    public Todo(TodoId id, String text, UserId owner, LocalDateTime createdDate) {
       this(id,text,createdDate);
       this.owner = owner;
    }

    //4단계 : 정적 팩토리 메서드 작성
    public static Todo create(String text, TodoIdGenerator idGenerator) {
        TodoId id = idGenerator.generateId();
        LocalDateTime now = LocalDateTime.now();
        Todo todo = new Todo(id,text,now);
        todo.state = TodoState.ACTIVE;
        todo.lastModifiedDate = now;
        return todo;
    }

    public static Todo create(String text, UserId owner, TodoIdGenerator idGenerator) {
        Todo todo = create(text, idGenerator);
        todo.owner = owner;
        return todo;
    }

    //5단계: Getter 메서드들 작성
    public TodoId getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public UserId getOwner() {
        return owner;
    }

    public TodoState getState() {
        return state;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    //6단계: 비즈니스 메서드 작성
    public boolean isCompleted() {
        // TodoState가 COMPLETED인지 확인하는 로직
       return state != null && getState().isCompleted();
    }

    public Todo edit(String text, boolean completed) {
        // 할일을 수정하는 로직
        // state를 completed 값에 따라 설정
        // lastModifiedDate를 현재 시간으로 업데이트
            this.text = text;
            this.state = completed ? TodoState.COMPLETED : TodoState.ACTIVE;
            this.lastModifiedDate = LocalDateTime.now();
            return this;

    }

    public Todo edit(String text, boolean completed, UserId owner) {
        // 소유자 검증 후 edit 호출
        // 소유자가 다르면 TodoOwnerMismatchException 발생
        if(!Objects.equals(this.owner, owner)){
            throw new TodoOwnerMismatchException("소유자가 다릅니다.");
        }
        return edit(text,completed);
    }


  // 7. equals, hashCode, toString 구현 (7단계)
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Todo todo = (Todo)obj;
        return Objects.equals(id,todo.id);
    }

    @Override
    public int hashCode() {
       return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", owner=" + owner +
                ", state=" + state +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
