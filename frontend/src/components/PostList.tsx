import React from 'react';
import { Post } from '../types/Post';

interface PostListProps {
  posts: Post[];
  onPostClick: (post: Post) => void;
  loading: boolean;
  error: string | null;
}

const PostList: React.FC<PostListProps> = ({ posts, onPostClick, loading, error }) => {
  if (loading) {
    return (
      <div className="loading">
        <p>Cargando publicaciones...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="error">
        <p>Error al cargar las publicaciones: {error}</p>
      </div>
    );
  }

  if (posts.length === 0) {
    return (
      <div className="no-posts">
        <p>No se encontraron publicaciones.</p>
      </div>
    );
  }

  return (
    <div className="posts-grid">
      {posts.map((post) => {
        const formatDate = (dateString: string): string => {
          const date = new Date(dateString);
          const now = new Date();
          const diffMs = now.getTime() - date.getTime();
          const diffHours = Math.floor(diffMs / (1000 * 60 * 60));
          const diffDays = Math.floor(diffHours / 24);
          
          if (diffHours < 1) {
            return 'Hace menos de una hora';
          } else if (diffHours < 24) {
            return `Hace ${diffHours} hora${diffHours > 1 ? 's' : ''}`;
          } else if (diffDays < 7) {
            return `Hace ${diffDays} día${diffDays > 1 ? 's' : ''}`;
          } else {
            return date.toLocaleDateString('es-ES', {
              year: 'numeric',
              month: 'long',
              day: 'numeric'
            });
          }
        };

        const getAuthorInitials = (name: string): string => {
          return name
            .split(' ')
            .map(word => word.charAt(0))
            .join('')
            .toUpperCase()
            .slice(0, 2);
        };

        return (
          <div key={post.id} className="post-card" onClick={() => onPostClick(post)}>
            <div className="post-header">
              <div className="author-avatar">
                {getAuthorInitials(post.author)}
              </div>
              <div className="post-author-info">
                <h3>{post.author}</h3>
                <div className="post-date">{formatDate(post.publishDate)}</div>
              </div>
            </div>
            
            <div className="post-text">
              {post.content}
            </div>
            
            <div className="post-interactions">
              <div className="interaction-item likes">
                <span className="interaction-icon">❤️</span>
                <span>{post.interactions.likes}</span>
              </div>
              <div className="interaction-item comments">
                <span className="interaction-icon">💬</span>
                <span>{post.interactions.comments}</span>
              </div>
              <div className="interaction-item shares">
                <span className="interaction-icon">🔄</span>
                <span>{post.interactions.shares}</span>
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default PostList;
