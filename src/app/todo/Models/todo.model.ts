export interface Todo {
  id: number;
  title: string;
  status: boolean;

}

export type TodoFilter = 'all' | 'completed' | 'incomplete';