package com.example.todoapp.core.todo.domain;
/**
 * 할일 상태 값
 **/
public enum TodoState {
    ACTIVE("active","처리할 할일"),
    COMPLETED("completed","완료된 할일");

    private final String literal;
    private final String description;

    TodoState(String literal, String description) {
        this.literal = literal;
        this.description = description;
    }

    public String getLiteral() {
        return literal;
    }

    public String getDescription() {
        return description;
    }

    /**
     * literal 값으로 TodoState를 찾는다
     *
     * @param literal 찾을 literal 값
     * @return 해당하는 TodoState
     * @throws IllegalArgumentException literal에 해당하는 상태가 없는 경우
     */
    public static TodoState fromLiteral(String literal){
        for(TodoState state : values()){
           if(state.literal.equals(literal)){
               return state;
           }
        }
        throw new IllegalArgumentException("해당하는 상태가 없습니다.");
    }

    /**
     * 할일이 완료 상태인지 확인한다
     *
     * @return 완료 상태이면 true, 아니면 false
     */
    public boolean isCompleted() {
        return this == COMPLETED;
    }

    /**
     * 할일이 활성 상태인지 확인한다
     *
     * @return 활성 상태이면 true, 아니면 false
     */
    public boolean isActive() {
        return this == ACTIVE;
    }
}
