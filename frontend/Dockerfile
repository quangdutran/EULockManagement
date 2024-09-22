FROM node:15-alpine

WORKDIR /app

# Copy the package.json and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the React app's source code
COPY . .

# Build the React app
RUN npm run build

# Serve the app using serve
RUN npm install -g serve
CMD ["serve", "-s", "build"]

EXPOSE 3000
