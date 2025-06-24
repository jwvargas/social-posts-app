import { useState, useEffect } from 'react';
import { Post } from '../types/Post';

interface UsePostsResult {
  posts: Post[];
  loading: boolean;
  error: string | null;
  searchPosts: (query: string) => Promise<void>;
  refreshPosts: () => Promise<void>;
  currentPage: number;
  totalPages: number;
  totalPosts: number;
  nextPage: () => void;
  prevPage: () => void;
  goToPage: (page: number) => void;
}

const mockPosts: Post[] = [
  {
    id: '1',
    content: '¡Qué hermoso día para explorar nuevas tecnologías! Acabo de terminar un proyecto increíble con React y Spring Boot. 🚀',
    publishDate: new Date(Date.now() - 2 * 60 * 60 * 1000).toISOString(),
    author: 'María González',
    interactions: { likes: 45, comments: 12, shares: 8 }
  },
  {
    id: '2',
    content: 'Reflexionando sobre la importancia de escribir código limpio y mantenible. Cada línea cuenta para el futuro del proyecto.',
    publishDate: new Date(Date.now() - 5 * 60 * 60 * 1000).toISOString(),
    author: 'Carlos Rodríguez',
    interactions: { likes: 32, comments: 18, shares: 5 }
  },
  {
    id: '3',
    content: 'Compartiendo mi experiencia con MongoDB: la flexibilidad de las bases de datos NoSQL nunca deja de sorprenderme 📊',
    publishDate: new Date(Date.now() - 8 * 60 * 60 * 1000).toISOString(),
    author: 'Ana Silva',
    interactions: { likes: 67, comments: 25, shares: 15 }
  },
  {
    id: '4',
    content: '¿Alguien más está emocionado por las nuevas características de Java 17? Las mejoras en performance son impresionantes ☕',
    publishDate: new Date(Date.now() - 12 * 60 * 60 * 1000).toISOString(),
    author: 'Luis Martinez',
    interactions: { likes: 89, comments: 34, shares: 22 }
  },
  {
    id: '5',
    content: 'Acabo de desplegar mi primera aplicación en la nube. El sentimiento de logro es indescriptible. ¡Nunca pares de aprender! 🌟',
    publishDate: new Date(Date.now() - 18 * 60 * 60 * 1000).toISOString(),
    author: 'Carmen López',
    interactions: { likes: 156, comments: 42, shares: 38 }
  }
];

const POSTS_PER_PAGE = 10;

export const usePosts = (): UsePostsResult => {
  const [allPosts, setAllPosts] = useState<Post[]>([]);
  const [filteredPosts, setFilteredPosts] = useState<Post[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  // Calcular publicaciones para la página actual
  const totalPosts = filteredPosts.length;
  const totalPages = Math.ceil(totalPosts / POSTS_PER_PAGE);
  const startIndex = (currentPage - 1) * POSTS_PER_PAGE;
  const posts = filteredPosts.slice(startIndex, startIndex + POSTS_PER_PAGE);

  const fetchPosts = async (): Promise<Post[]> => {
    try {
      const response = await fetch('http://localhost:8080/api/posts');
      
      if (!response.ok) {
        throw new Error('Error al obtener las publicaciones');
      }
      
      const data = await response.json();
      return data;
    } catch (err) {
      console.warn('Error conectando con el backend, usando datos de ejemplo:', err);
      return mockPosts;
    }
  };
  const refreshPosts = async (): Promise<void> => {
    setLoading(true);
    setError(null);
    
    try {
      const data = await fetchPosts();
      setAllPosts(data);
      setFilteredPosts(data);
      setCurrentPage(1); // Resetear a la primera página
    } catch (err) {
      setError('Error al cargar las publicaciones');
    } finally {
      setLoading(false);
    }
  };

  const searchPosts = async (query: string): Promise<void> => {
    if (!query.trim()) {
      setFilteredPosts(allPosts);
      setCurrentPage(1);
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/posts/search?q=${encodeURIComponent(query)}`);
      
      if (!response.ok) {
        throw new Error('Error en la búsqueda');
      }
      
      const data = await response.json();
      setFilteredPosts(data);
      setCurrentPage(1); // Resetear a la primera página
    } catch (err) {
      console.warn('Error en búsqueda del backend, usando filtro local:', err);
      const filtered = allPosts.filter(post =>
        post.content.toLowerCase().includes(query.toLowerCase()) ||
        post.author.toLowerCase().includes(query.toLowerCase())
      );
      setFilteredPosts(filtered);
      setCurrentPage(1); // Resetear a la primera página
    }
  };

  // Funciones de paginación
  const nextPage = (): void => {
    if (currentPage < totalPages) {
      setCurrentPage(currentPage + 1);
    }
  };

  const prevPage = (): void => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  const goToPage = (page: number): void => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
    }
  };

  useEffect(() => {
    refreshPosts();
  }, []);
  return {
    posts,
    loading,
    error,
    searchPosts,
    refreshPosts,
    currentPage,
    totalPages,
    totalPosts,
    nextPage,
    prevPage,
    goToPage
  };
};
