FROM node:16.14.2
WORKDIR .
COPY package*.json ./
RUN yarn
COPY . .
EXPOSE 8081
CMD ["node", "app.js"]