import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';

test('renders social posts header', () => {
  render(<App />);
  const headerElement = screen.getByText(/Social Posts/i);
  expect(headerElement).toBeInTheDocument();
});

test('renders search input', () => {
  render(<App />);
  const searchInput = screen.getByPlaceholderText(/Buscar publicaciones/i);
  expect(searchInput).toBeInTheDocument();
});
