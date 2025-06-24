import React from 'react';
import './Pagination.css';

interface PaginationProps {
  currentPage: number;
  totalPages: number;
  totalPosts: number;
  onNextPage: () => void;
  onPrevPage: () => void;
  onGoToPage: (page: number) => void;
}

const Pagination: React.FC<PaginationProps> = ({
  currentPage,
  totalPages,
  totalPosts,
  onNextPage,
  onPrevPage,
  onGoToPage
}) => {
  // Generar números de página para mostrar
  const getPageNumbers = () => {
    const delta = 2; // Cuántas páginas mostrar a cada lado de la página actual
    const range = [];
    const rangeWithDots = [];

    // Calcular el rango de páginas a mostrar
    for (
      let i = Math.max(2, currentPage - delta);
      i <= Math.min(totalPages - 1, currentPage + delta);
      i++
    ) {
      range.push(i);
    }

    // Agregar la primera página
    if (currentPage - delta > 2) {
      rangeWithDots.push(1, '...');
    } else {
      rangeWithDots.push(1);
    }

    // Agregar el rango central
    rangeWithDots.push(...range);

    // Agregar la última página
    if (currentPage + delta < totalPages - 1) {
      rangeWithDots.push('...', totalPages);
    } else if (totalPages > 1) {
      rangeWithDots.push(totalPages);
    }

    return rangeWithDots;
  };

  const startPost = (currentPage - 1) * 10 + 1;
  const endPost = Math.min(currentPage * 10, totalPosts);

  return (
    <div className="pagination">
      <div className="pagination-info">
        Mostrando {startPost}-{endPost} de {totalPosts} publicaciones
      </div>
      
      <div className="pagination-controls">
        <button
          className="pagination-btn"
          onClick={onPrevPage}
          disabled={currentPage === 1}
        >
          ← Anterior
        </button>

        <div className="pagination-numbers">
          {getPageNumbers().map((page, index) => (
            <span key={index}>
              {page === '...' ? (
                <span className="pagination-dots">...</span>
              ) : (
                <button
                  className={`pagination-number ${
                    page === currentPage ? 'active' : ''
                  }`}
                  onClick={() => onGoToPage(page as number)}
                >
                  {page}
                </button>
              )}
            </span>
          ))}
        </div>

        <button
          className="pagination-btn"
          onClick={onNextPage}
          disabled={currentPage === totalPages}
        >
          Siguiente →
        </button>
      </div>
    </div>
  );
};

export default Pagination;
