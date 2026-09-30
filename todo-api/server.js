const express = require('express');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

let todos = [
  { id: 1, title: 'Learn Angular', status: false },
  { id: 2, title: 'Build Todo App', status: true }
];
let nextId = 3;

// deliberately slow, so the loading state is visible
app.get('/api/todos', (req, res) => {
  setTimeout(() => res.json(todos), 800);
});

app.get('/api/todos/:id', (req, res) => {
  const todo = todos.find(t => t.id === Number(req.params.id));
  if (!todo) return res.status(404).json({ message: 'Todo not found' });
  res.json(todo);
});

app.post('/api/todos', (req, res) => {
  const todo = { id: nextId++, title: req.body.title, status: false };
  todos.push(todo);
  res.status(201).json(todo);
});

app.put('/api/todos/:id', (req, res) => {
  const todo = todos.find(t => t.id === Number(req.params.id));
  if (!todo) return res.status(404).json({ message: 'Todo not found' });

  todo.title  = req.body.title  ?? todo.title;
  todo.status = req.body.status ?? todo.status;
  res.json(todo);
});

app.delete('/api/todos/:id', (req, res) => {
  todos = todos.filter(t => t.id !== Number(req.params.id));
  res.status(204).send();
});

app.listen(3000, () => console.log('Todo API on http://localhost:3000'));