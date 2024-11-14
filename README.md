基于SpringBoot + Vue3 + TypeScript + Vite的个人博客，MySQL数据库，Redis缓存，ElasticSearch全文搜索，支持QQ、Gitee、Github第三方登录，留言、友链、评论、说说、相册等功能。
已经实现后端


项目特点
前台界面参考 Hexo 的 Shoka 和 Butterfly 设计，页面美观，响应式布局
后台管理基于若依二次开发，含有侧边栏，历史标签，面包屑等
前后端分离，Docker Compose 一键部署
采用 RABC 权限模型，使用 Sa-Token 进行权限管理
支持动态权限修改、动态菜单和路由
说说、友链、相册、留言弹幕墙、音乐播放器、聊天室
支持代码高亮、图片预览、黑夜模式、点赞、取消点赞等功能
发布评论、回复评论、表情包
发送 HTML 邮件评论回复提醒，内容详细
接入第三方登录，减少注册成本
文章搜索支持关键字高亮分词
文章编辑使用 Markdown 编辑器
含有最新评论、文章目录、文章推荐和文章置顶功能
实现日志管理、定时任务管理、在线用户和下线用户
代码支持多种搜索模式（Elasticsearch 或 MYSQL），支持多种文件上传模式（OSS、COS、本地）
采用 Restful 风格的 API，注释完善，代码遵循阿里巴巴开发规范，有利于开发者学习
技术介绍
前端： Vue3 + Pinia + Vue Router + TypeScript + Axios + Element Plus + Naive UI + Echarts + Swiper

后端： SpringBoot + Mysql + Redis + Quartz + Thymeleaf + Nginx + Docker + Sa-Token + Swagger2 + MyBatisPlus + ElasticSearch + RabbitMQ + Canal

其他： 接入 QQ、Gitee、Github 第三方登录

运行环境
服务器： 腾讯云 2 核 4G CentOS7.6

对象存储： 阿里云 OSS、腾讯云 COS

最低配置： 2 核 2G 服务器（关闭 ElasticSearch）
