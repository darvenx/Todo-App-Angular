import { Component, computed, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Todo, TodoFilter } from './Models/todo.model';

@Component({
  selector: 'app-todo',
  imports: [FormsModule],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css'
})
export class TodoComponent {

  todos = signal<Todo[]>([
    { id: 1, title: 'Learn Angular', status: false },
    { id: 2, title: 'Build Todo App', status: true }
  ]);

  newTodoTitle    = signal('');
  searchText      = signal('');
  filterSelection = signal<TodoFilter>('all');

  totalCount      = computed(() => this.todos().length);
  completedCount  = computed(() => this.todos().filter(t => t.status).length);
  incompleteCount = computed(() => this.totalCount() - this.completedCount());

  filteredTodos = computed(() => {
    const search   = this.searchText().toLowerCase().trim();
    const selected = this.filterSelection();

    return this.todos().filter(todo => {
      const matchesSearch = todo.title.toLowerCase().includes(search);
      const matchesFilter =
        selected === 'all'       ? true :
        selected === 'completed' ? todo.status :
                                   !todo.status;
      return matchesSearch && matchesFilter;
    });
  });

  addTodo() {
    const title = this.newTodoTitle().trim();
    if (title === '') {
      return;
    }

    const newTodo: Todo = { id: Date.now(), title: title, status: false };
    this.todos.update(list => [...list, newTodo]);
    this.newTodoTitle.set('');
  }

  toggleTodo(id: number) {
    this.todos.update(list =>
      list.map(todo => todo.id === id ? { ...todo, status: !todo.status } : todo)
    );
  }

  deleteTodo(id: number) {
    this.todos.update(list => list.filter(todo => todo.id !== id));
  }
}