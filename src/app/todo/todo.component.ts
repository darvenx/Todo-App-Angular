import { Component, signal } from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-todo',
  imports: [FormsModule],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css'
})
export class TodoComponent {

  heading = signal('My Todos');
    todos = [
    { id: 1, title: 'Learn Angular', status: false, priority: 'high' },
    { id: 2, title: 'Build Todo App', status: true,  priority: 'low' }
  ];
  
  renameHeading() {
    this.heading.set('Today\'s work');
  }
}
