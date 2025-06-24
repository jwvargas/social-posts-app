import React from 'react';
import { Post } from '../types/Post';

interface PostDetailProps {
  post: Post;
  onBack: () => void;
}

const PostDetail: React.FC<PostDetailProps> = ({ post, onBack }) => {
  const formatDate = (dateString: string): string => {
    const date = new Date(dateString);
    return date.toLocaleDateString('es-ES', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  const getAuthorInitials = (name: string): string => {
    return name
      .split(' ')
      .map(word => word.charAt(0))
      .join('')
      .toUpperCase()
      .slice(0, 2);
  };

  const getTotalInteractions = (): number => {
    return post.interactions.likes + post.interactions.comments + post.interactions.shares;
  };

  return (
    <div className="post-detail">
      <button className="back-button" onClick={onBack}>
        <span>←</span>
        Volver
      </button>
      
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
          <div className="interaction-count">{post.interactions.likes}</div>
          <div className="interaction-label">Me gusta</div>
        </div>
        <div className="interaction-item comments">
          <span className="interaction-icon">💬</span>
          <div className="interaction-count">{post.interactions.comments}</div>
          <div className="interaction-label">Comentarios</div>
        </div>
        <div className="interaction-item shares">
          <span className="interaction-icon">🔄</span>
          <div className="interaction-count">{post.interactions.shares}</div>
          <div className="interaction-label">Compartidos</div>
        </div>
        <div className="interaction-item">
          <span className="interaction-icon">📊</span>
          <div className="interaction-count">{getTotalInteractions()}</div>
          <div className="interaction-label">Total</div>
        </div>
      </div>
    </div>
  );
};

export default PostDetail;
