# 项目简介
&nbsp;&nbsp;本项目基于SpringBoot搭建后台，使用Vue搭建前端服务。完成了Vue2安装并集成ElementUI、Vue2前端主体框架搭建、SpringBoot后端初始框架搭建、SpringBoot集成Mybatis、SpringBoot实现增删改查、SpringBoot实现分页查询、SpringBoot实现集成Mybatis-Plus和SwaggerUI、Vue实现增删改查、SpringBoot实现代码生成器、Vue使用路由、SpringBoot和Vue实现导入导出、SpringBoot和Vue实现用户登录注册与异常处理、SpringBoot和Vue实现用户个人信息展示与保存与集成JWT、SpringBoot和Vue实现文件上传与下载、SpringBoot和Vue整合ECharts、权限菜单讲解、SpringBoot实现1对1、1对多、多对多关联查询、用户前台页面设计与实现、SpringBoot集成Redis实现缓存、高德地图集成演示、Vue集成视频播放组件、Vue集成Markdown和多级评论等功能。从功能的实现难度以及可应用性出发，此项目完全可以作为本科毕业设计以及简历项目使用。
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# 项目开发软件环境
- Windows 11
- Idea 2019.3
- Maven 3.3.9
- Postman
- jdk 1.8
- springboot 2.7.1
- mybatis-plus 3.5.1
- mybatis 2.2.2
- mysql 5.7
- element-ui 2.15.9
- vue 2.6.14
- vue-router 3.5.1
- vuex 3.6.2
- echarts 5.3.3
- axios 0.27.2
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# 项目开发硬件环境
- CPU：Intel® Core™ i7-8750H CPU @ 2.20GHz 2.20 GHz
- RAM：24GB
- GPU：NVIDIA GeForce GTX 1060
<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# 一、功能图片演示
## 1.1 基本功能
### 1.1.1 用户登录
![请添加图片描述](https://img-blog.csdnimg.cn/37ef90abc9ef4ba38a8bef8497fce8a8.png#pic_center)
### 1.1.2 用户注册
![请添加图片描述](https://img-blog.csdnimg.cn/62233b3f809542d2a9f8377ca158e25b.png#pic_center)
## 1.2 系统后台功能
### 1.2.1 系统后台主页
![请添加图片描述](https://img-blog.csdnimg.cn/47dedd87cf3c44fa816f8347ee67f838.png#pic_center)
### 1.2.2 用户管理
![请添加图片描述](https://img-blog.csdnimg.cn/4109dfa1961e41a796a959e126a59cf6.png#pic_center)
### 1.2.3 选课信息
![请添加图片描述](https://img-blog.csdnimg.cn/73da1230a5db4c1a85a86cc338f022d7.png#pic_center)
### 1.2.4 修改密码
![请添加图片描述](https://img-blog.csdnimg.cn/ab64a70ce4c04d0b87b99876dea03b7b.png#pic_center)
### 1.2.5 文章管理
![请添加图片描述](https://img-blog.csdnimg.cn/a380d3ecc7674e3c8aac8bc4f17426f1.png#pic_center)
### 1.2.6 查看文章内容
![请添加图片描述](https://img-blog.csdnimg.cn/830dd7eb91a64abf9b0eb436d193447f.png#pic_center)

### 1.2.7 编写MarkDown文章
![请添加图片描述](https://img-blog.csdnimg.cn/f6043208d4fa424996cec8b74f05ff9b.png#pic_center)
### 1.2.8 文件管理
![请添加图片描述](https://img-blog.csdnimg.cn/c4610021914c42f3b0f3f7fc8014adad.png)
### 1.2.9 授课信息
![请添加图片描述](https://img-blog.csdnimg.cn/7d4fc0206df2483fb53e56ff3aa02b96.png)
### 1.2.10 课程管理
![请添加图片描述](https://img-blog.csdnimg.cn/d18eec63507d48c3884874802d56d7e3.png)
### 1.2.11 角色管理
![请添加图片描述](https://img-blog.csdnimg.cn/01bfc5914223468eb8e27c6b4c034512.png)
### 2.12 个人信息
![请添加图片描述](https://img-blog.csdnimg.cn/6125838d83d6468c9a609d485224d872.png)
### 1.2.13 高德地图
![请添加图片描述](https://img-blog.csdnimg.cn/984f429551f9417a9a343ddeeb864ea1.png)
### 1.2.14 菜单管理
![请添加图片描述](https://img-blog.csdnimg.cn/e0a7a3353edb439385b6a9018a62bacc.png)
### 1.2.15 菜单分配
![请添加图片描述](https://img-blog.csdnimg.cn/0fbc32c16ca3424a9d96c63e088a7d09.png)
## 1.3 系统前台功能
### 1.3.1 系统前台主页
![请添加图片描述](https://img-blog.csdnimg.cn/0b44c856e5bb49049997852cf6fb4ef7.png)
### 1.3.2 视频播放列表
![请添加图片描述](https://img-blog.csdnimg.cn/c7e8268bf87c4e759e0d67b778e13d35.png)
### 1.3.3 视频播放内容展示
![请添加图片描述](https://img-blog.csdnimg.cn/9ed11c9dcdfc4a6f83a09598e6972402.png)
### 1.3.4 文章列表
![请添加图片描述](https://img-blog.csdnimg.cn/93374e5e9a08488280e1d76fb9d9a6ba.png)
### 1.3.5 文章内容展示
![请添加图片描述](https://img-blog.csdnimg.cn/35064232ad194dda8962fe4ab8914192.png)
### 1.3.6 用户多级评论
![请添加图片描述](https://img-blog.csdnimg.cn/6efd630eb2394b8bbe5bef7648635469.png)
# 二、功能视频演示

功能视频演示见如下链接：[基于SpringBoot和Vue的后台管理系统项目视频演示](https://live.csdn.net/v/247792)

# 三、项目启动
## 3.1 SpringBoot项目启动配置
![请添加图片描述](https://img-blog.csdnimg.cn/f6c0eeef11ea409b8d12aea7e542d4c1.png)

## 3.2 进入Vue项目目录
![请添加图片描述](https://img-blog.csdnimg.cn/1cc2d8b3cdae49daadfc8c9243896132.png)

## 3.3 启动SpringBoot
![请添加图片描述](https://img-blog.csdnimg.cn/6ab63242d8f548a38bb6907908c672d6.png)

## 3.4 启动Vue项目
![请添加图片描述](https://img-blog.csdnimg.cn/30dc0f881eb34681965a079ca0bd4616.png)

## 3.5 进入系统
![请添加图片描述](https://img-blog.csdnimg.cn/bd3e7d139d2b487b8ef26311ac5b74f3.png)

<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">
