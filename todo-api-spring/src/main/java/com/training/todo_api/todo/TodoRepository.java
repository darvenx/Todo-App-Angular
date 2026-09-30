package com.training.todo_api.todo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TodoRepository {

    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public TodoRepository() {
        save(new Todo(null, "Learn Angular", false));
        save(new Todo(null, "Build Todo App", true));
    }

    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    public Optional<Todo> findById(Long id) {
        return todos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Todo save(Todo todo) {
        todo.setId(nextId.getAndIncrement());
        todos.add(todo);
        return todo;
    }

    public boolean deleteById(Long id) {
        return todos.removeIf(t -> t.getId().equals(id));
    }
}