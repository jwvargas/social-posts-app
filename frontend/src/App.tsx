import React, { useState } from 'react';
import { Post } from './types/Post';
import SearchBar from './components/SearchBar';
import PostList from './components/PostList';
import PostDetail from './components/PostDetail';
import Pagination from './components/Pagination';
import { usePosts } from './hooks/usePosts';
import './index.css';

const App: React.FC = () => {
  const {
    posts,
    loading,
    error,
    searchPosts,
    currentPage,
    totalPages,
    totalPosts,
    nextPage,
    prevPage,
    goToPage  } = usePosts();
  
  const [selectedPost, setSelectedPost] = useState<Post | null>(null);
  const [searchTerm, setSearchTerm] = useState<string>('');

  // Función para manejar la búsqueda
  const handleSearch = async (query: string) => {
    setSearchTerm(query);
    await searchPosts(query);
  };
  const handlePostClick = (post: Post) => {
    setSelectedPost(post);
  };

  const handleBackClick = () => {
    setSelectedPost(null);
  };
  const handleSearchChange = (newSearchTerm: string) => {
    setSearchTerm(newSearchTerm);
    handleSearch(newSearchTerm);
  };

  return (
    <div className="app">
      <div className="container">
        {!selectedPost ? (
          <>
            <header className="header">
              <h1>Social Posts</h1>
              <p>Descubre y explora publicaciones de redes sociales</p>
            </header>            
            <SearchBar
              searchTerm={searchTerm}
              onSearchChange={handleSearch}
            />
            
            <PostList
              posts={posts}
              onPostClick={handlePostClick}
              loading={loading}
              error={error}
            />
            
            {!loading && !error && posts.length > 0 && (
              <Pagination
                currentPage={currentPage}
                totalPages={totalPages}
                totalPosts={totalPosts}
                onNextPage={nextPage}
                onPrevPage={prevPage}
                onGoToPage={goToPage}
              />
            )}
          </>
        ) : (
          <PostDetail
            post={selectedPost}
            onBack={handleBackClick}
          />
        )}
      </div>
    </div>
  );
};

export default App;
