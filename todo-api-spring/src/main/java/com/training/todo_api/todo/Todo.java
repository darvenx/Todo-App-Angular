package com.training.todo_api.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Todo {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, message = "Title needs at least 3 characters")
    private String title;

    

    private boolean status;

    public Todo() {
    }

    public Todo(Long id, String title, boolean status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}