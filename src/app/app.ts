import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodoComponent } from './todo/todo.component';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TodoComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
