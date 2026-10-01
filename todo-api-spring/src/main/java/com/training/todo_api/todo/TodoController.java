package com.training.todo_api.todo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.MediaType;

@Tag(name = "todos", description = "Todo CRUD operations")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all todos", operationId = "getAllTodos")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create todo", operationId = "createTodo")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@Valid @RequestBody Todo todo) {
        todo.setStatus(false);
        return repository.save(todo);
    }

    @Operation(summary = "Update todo by id", operationId = "updateTodo")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo changes) {
        return repository.findById(id)
                .map(existing -> {
                    if (changes.getTitle() != null) {
                        existing.setTitle(changes.getTitle());
                    }
                    existing.setStatus(changes.isStatus());
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete todo by id", operationId = "deleteTodo")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}