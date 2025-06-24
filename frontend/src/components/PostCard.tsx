import React from 'react';
import { Post } from '../types/Post';

interface PostCardProps {
  post: Post;
  onClick: (post: Post) => void;
}

const PostCard: React.FC<PostCardProps> = ({ post, onClick }) => {
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
    <div className="post-card" onClick={() => onClick(post)}>
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
};

export default PostCard;
