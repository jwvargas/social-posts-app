#!/bin/bash

# Script para probar los endpoints de la API Social Posts

echo "=== Probando API Social Posts ==="
echo ""

BASE_URL="http://localhost:8080/api"

echo "1. Probando GET /api/posts"
curl -s -o /dev/null -w "Status: %{http_code}\n" "$BASE_URL/posts"
echo ""

echo "2. Probando búsqueda GET /api/posts/search?q=React"
curl -s -o /dev/null -w "Status: %{http_code}\n" "$BASE_URL/posts/search?q=React"
echo ""

echo "3. Probando GET de todas las publicaciones (con contenido)"
curl -s "$BASE_URL/posts" | head -c 200
echo ""
echo "..."
echo ""

echo "4. Probando búsqueda con término específico"
curl -s "$BASE_URL/posts/search?q=React" | head -c 200
echo ""
echo "..."
echo ""

echo "=== Pruebas completadas ==="
