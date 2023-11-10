FROM node:16-alpine AS builder

# Set the working directory
WORKDIR /app

# Copy package*.json for npm install
COPY package*.json ./

# Run npm install
RUN npm install

# Copy all files
COPY . .

EXPOSE 4200

CMD ["npm", "start"]
