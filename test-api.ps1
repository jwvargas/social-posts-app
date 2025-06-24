# Script para probar los endpoints de la API Social Posts en PowerShell

Write-Host "=== Probando API Social Posts ===" -ForegroundColor Green
Write-Host ""

$BaseUrl = "http://localhost:8080/api"

Write-Host "1. Probando GET /api/posts" -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "$BaseUrl/posts" -UseBasicParsing
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "2. Probando búsqueda GET /api/posts/search?q=React" -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "$BaseUrl/posts/search?q=React" -UseBasicParsing
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "3. Probando GET de todas las publicaciones (con contenido)" -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "$BaseUrl/posts" -UseBasicParsing
    $content = $response.Content
    if ($content.Length -gt 200) {
        Write-Host $content.Substring(0, 200) -ForegroundColor Cyan
        Write-Host "..." -ForegroundColor Gray
    } else {
        Write-Host $content -ForegroundColor Cyan
    }
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "4. Probando búsqueda con término específico" -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "$BaseUrl/posts/search?q=React" -UseBasicParsing
    $content = $response.Content
    if ($content.Length -gt 200) {
        Write-Host $content.Substring(0, 200) -ForegroundColor Cyan
        Write-Host "..." -ForegroundColor Gray
    } else {
        Write-Host $content -ForegroundColor Cyan
    }
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "=== Pruebas completadas ===" -ForegroundColor Green
