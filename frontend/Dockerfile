FROM node:16-alpine AS builder

# Set the working directory
WORKDIR /app

# Copy package*.json for npm install
COPY package*.json ./

# Run npm install
RUN npm install

# Copy all files
COPY . .

RUN npm run build

# Serve Application using Nginx Server
FROM nginx:alpine

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/help-now-app-front /usr/share/nginx/html

EXPOSE 80