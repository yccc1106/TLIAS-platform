# TLIAS 智慧教务管理平台

TLIAS 是一个前后端分离的教务管理系统，提供部门、员工、班级、学员、报表与操作日志等管理能力。

## 项目名称

**TLIAS 智慧教务管理平台**

## 项目结构

```text
.
├── vue-tlias-management/          # 前端（Vue 3 + Vite）
├── web-project/
│   └── tlias-web-management/      # 后端（Spring Boot）
├── docker-compose.yml             # 前端容器编排
└── logs/                          # 运行日志
```

## 技术栈

- 前端：Vue 3、Vite、Pinia、Vue Router
- 后端：Spring Boot、MyBatis
- 部署：Docker、Nginx

## 快速启动

### 1) 启动后端（本地）

```bash
cd web-project/tlias-web-management
mvn spring-boot:run
```

默认后端地址：`http://localhost:8080`

### 2) 启动前端（本地开发）

```bash
cd vue-tlias-management
pnpm install
pnpm dev
```

### 3) Docker 方式启动前端

在项目根目录执行：

```bash
docker compose up -d --build
```

默认将前端发布到：`http://localhost`，并通过 `BACKEND_URL` 转发到本地后端 `http://host.docker.internal:8080`。

## 主要功能

- 登录认证与 Token 校验
- 部门管理
- 员工管理
- 班级管理
- 学员管理
- 报表统计
- 操作日志查询

## 说明

- 前端 Docker 配置位于：`vue-tlias-management/nginx/default.conf.template`
- 若后端端口不是 `8080`，请同步调整 `docker-compose.yml` 中的 `BACKEND_URL`。
