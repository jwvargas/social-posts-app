# Usar imagen base de Node.js 18
FROM node:18-alpine

# Establecer directorio de trabajo
WORKDIR /app

# Copiar package.json y package-lock.json
COPY package*.json ./

# Instalar dependencias
RUN npm ci --only=production

# Copiar código fuente
COPY . .

# Compilar aplicación
RUN npm run build

# Instalar servidor estático para servir la aplicación
RUN npm install -g serve

# Exponer puerto
EXPOSE 3000

# Comando para servir la aplicación
CMD ["serve", "-s", "build", "-l", "3000"]
