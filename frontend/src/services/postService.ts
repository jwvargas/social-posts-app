import { Post } from '../types/Post';

const API_BASE_URL = 'http://localhost:8080/api';

export const postService = {
  // Obtener todas las publicaciones
  getAllPosts: async (): Promise<Post[]> => {
    const response = await fetch(`${API_BASE_URL}/posts`);
    if (!response.ok) throw new Error('Error al obtener publicaciones');
    return response.json();
  },

  // Obtener una publicación por ID
  getPostById: async (id: string): Promise<Post> => {
    const response = await fetch(`${API_BASE_URL}/posts/${id}`);
    if (!response.ok) throw new Error('Error al obtener publicación');
    return response.json();
  },

  // Buscar publicaciones
  searchPosts: async (query: string): Promise<Post[]> => {
    const response = await fetch(`${API_BASE_URL}/posts/search?q=${encodeURIComponent(query)}`);
    if (!response.ok) throw new Error('Error en búsqueda');
    return response.json();
  },

  // Crear una nueva publicación
  createPost: async (post: Omit<Post, 'id'>): Promise<Post> => {
    const response = await fetch(`${API_BASE_URL}/posts`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(post),
    });
    if (!response.ok) throw new Error('Error al crear publicación');
    return response.json();
  },

  // Actualizar una publicación
  updatePost: async (id: string, post: Omit<Post, 'id'>): Promise<Post> => {
    const response = await fetch(`${API_BASE_URL}/posts/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(post),
    });
    if (!response.ok) throw new Error('Error al actualizar publicación');
    return response.json();
  },

  // Eliminar una publicación
  deletePost: async (id: string): Promise<void> => {
    const response = await fetch(`${API_BASE_URL}/posts/${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) throw new Error('Error al eliminar publicación');
  },
};
