# EduAdmin 智慧教务管理平台

EduAdmin 是一个基于前后端分离架构开发的智慧教务管理平台，面向学校、培训机构及教学管理场景，提供人员管理、班级管理、学员管理、数据统计与操作日志追踪等功能。

系统采用 Vue 3 + Spring Boot 技术栈构建，前端负责页面展示与交互，后端负责业务处理、数据持久化与接口服务。整体结构清晰，便于维护、扩展与部署。

## 一、项目简介

本项目围绕教务管理中的常见业务场景进行设计与实现，主要包括部门管理、员工管理、班级管理、学员管理、数据报表和系统日志等模块。

通过该系统，管理员可以完成基础教务信息维护、学员与班级数据管理，以及关键业务数据统计分析，从而提升教务管理的信息化、规范化和可视化水平。

## 二、项目结构

```text
.
├── vue-tlias-management/          # 前端项目，基于 Vue 3 + Vite
├── web-project/
│   └── tlias-web-management/      # 后端项目，基于 Spring Boot
├── docker-compose.yml             # Docker 容器编排配置
└── logs/                          # 系统运行日志
```

## 三、技术栈

### 3.1 前端技术

- Vue 3
- Vite
- Pinia
- Vue Router
- Axios
- Element Plus
- Nginx

### 3.2 后端技术

- Spring Boot
- MyBatis
- MySQL
- Maven
- RESTful API

### 3.3 部署与运维

- Docker
- Docker Compose
- Nginx
- 日志管理

## 四、核心功能

### 4.1 登录认证

- 用户登录
- Token 身份校验
- 登录状态维护
- 请求权限拦截

### 4.2 部门管理

- 部门信息查询
- 新增部门
- 修改部门信息
- 删除部门

### 4.3 员工管理

- 员工列表查询
- 员工信息新增
- 员工信息编辑
- 员工删除
- 条件分页查询

### 4.4 班级管理

- 班级信息维护
- 班级列表查询
- 班级新增、修改与删除
- 班级状态管理

### 4.5 学员管理

- 学员信息录入
- 学员列表分页查询
- 学员信息修改
- 学员删除
- 学员所属班级管理

### 4.6 数据报表

- 员工数据统计
- 学员数据统计
- 班级数据分析
- 可视化数据展示

### 4.7 操作日志

- 用户操作记录
- 日志查询
- 系统行为追踪
- 便于问题排查与审计

## 五、快速启动

### 5.1 启动后端服务

进入后端项目目录：

```bash
cd web-project/tlias-web-management
```

启动 Spring Boot 项目：

```bash
mvn spring-boot:run
```

后端默认运行地址：

```text
http://localhost:8080
```

### 5.2 启动前端开发环境

进入前端项目目录：

```bash
cd vue-tlias-management
```

安装依赖：

```bash
pnpm install
```

启动开发服务：

```bash
pnpm dev
```

前端开发环境启动后，可根据终端提示访问对应地址。

### 5.3 使用 Docker 启动前端服务

在项目根目录执行：

```bash
docker compose up -d --build
```

前端默认访问地址：

```text
http://localhost
```

Docker 部署时，前端容器会通过 `BACKEND_URL` 将接口请求转发到本地后端服务：

```text
http://host.docker.internal:8080
```

## 六、配置说明

前端 Nginx 配置文件位于：

```text
vue-tlias-management/nginx/default.conf.template
```

如果后端服务端口不是 `8080`，需要同步修改 `docker-compose.yml` 中的 `BACKEND_URL` 配置。

示例：

```yaml
BACKEND_URL: http://host.docker.internal:8080
```

## 七、项目特点

- 采用前后端分离架构，职责清晰，便于独立开发与部署
- 使用 RESTful API 进行前后端数据交互
- 支持 Token 鉴权，提高系统访问安全性
- 前端基于 Vue 3 构建，页面组件化程度较高
- 后端基于 Spring Boot 开发，结构清晰，便于扩展
- 使用 MyBatis 完成数据访问层开发，便于 SQL 管理与维护
- 支持 Docker 部署，降低环境配置成本
- 提供操作日志记录，便于系统维护、问题追踪和行为审计

## 八、适用场景

本项目适用于以下场景：

- 高校或培训机构的教务管理系统
- Java Web 课程设计
- 前后端分离项目实践
- Spring Boot + Vue 全栈项目练习
- 后台管理系统开发模板参考

## 九、后续优化方向

- 引入更细粒度的角色权限控制
- 增加菜单权限与按钮权限管理
- 优化数据报表的可视化展示
- 增加导入、导出功能
- 增加系统异常统一处理
- 完善接口文档与单元测试
- 支持多环境配置与自动化部署

## 十、项目说明

本项目主要用于智慧教务管理业务场景的开发实践，涵盖前端页面开发、后端接口设计、数据库操作、权限校验、日志记录和 Docker 部署等内容，适合作为 Java 后端开发、前后端分离开发以及后台管理系统开发的综合实践项目。
