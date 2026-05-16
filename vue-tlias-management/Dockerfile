FROM node:20-alpine AS build
WORKDIR /app

COPY package*.json ./
COPY pnpm-lock.yaml* ./

RUN if [ -f pnpm-lock.yaml ]; then npm i -g pnpm && pnpm install --frozen-lockfile; else npm install; fi

COPY . .
RUN if [ -f pnpm-lock.yaml ]; then pnpm build; else npm run build; fi

FROM nginx:1.27-alpine
COPY nginx/default.conf.template /etc/nginx/templates/default.conf.template
COPY --from=build /app/dist /usr/share/nginx/html

ENV BACKEND_URL=http://host.docker.internal:8080
EXPOSE 80
