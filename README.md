## 趣小说


- **技术:SpringBoot + SpringMVC + Spring + Mybatis + Redis + Webmagic + Tomcat + Nginx(前端) + Vue(前端)**
- 主要问题:小说数据过于庞大,一次性无法爬取,爬取速度太快有被限制访问的风险
   - 先通过Webmagic爬取所有小说基本信息(不包括章节信息和章节内容,标记为未存在)
      - [https://github.com/buzhidaoGH/quxiaoshuo-task](https://github.com/buzhidaoGH/quxiaoshuo-task)
   - 再通过网络访问,访问的数据再去二次网络爬取(更改标记为存在,当标记存在时直接访问数据库)
- Redis可以支持非启动状态依然可以运行



## 趣小说数据来源


- 本小说后台,爬取分析的网站为"[http://www.biquge.tv/](http://www.biquge.tv/)"
   - 网站的爬取规则为:
```bash
User-agent: *
Disallow: /admin/
Disallow: /blocks/
Disallow: /blockcache/
Disallow: /cache/
Disallow: /compiled/
Disallow: /configs/
Disallow: /scripts/
Disallow: /*php*
Disallow: /modules/
Disallow: /modules/article/
```

   - 个人获取资源的网页为:
```
http://www.biquge.tv/index
http://www.biquge.tv/书名代号/
http://www.biquge.tv/书名代号/章节代号.html
```

- 趣小说网址于2021/7/18更改为[https://www.qbiqu.com/](https://www.qbiqu.com/)



## 趣小说后台功能


- 小说信息后台接口包括:
   1. "/index":主要用于展示基本的推荐信息
      - ![image.png](https://cdn.nlark.com/yuque/0/2021/png/21696663/1626698077220-b27f39c3-2253-4384-bd03-300bb7c2645e.png#align=left&display=inline&height=257&margin=%5Bobject%20Object%5D&name=image.png&originHeight=513&originWidth=626&size=29752&status=done&style=none&width=313)
   2. "/randomnovel":按照日期随机推荐小说
      - ![image.png](https://cdn.nlark.com/yuque/0/2021/png/21696663/1626698162035-fc12cc50-20ad-4203-9fe6-b42d1ba0666d.png#align=left&display=inline&height=205&margin=%5Bobject%20Object%5D&name=image.png&originHeight=409&originWidth=643&size=26857&status=done&style=none&width=322)
   3. "/xiaoshuocategory":展示小说的分类本数
   3. "/xiaoshuo/{category}/{page}":
      - category:小说的分类类型
      - page:页码
      - ![image.png](https://cdn.nlark.com/yuque/0/2021/png/21696663/1626698226314-aab2f757-fcec-46f3-a7f4-689ab3419131.png#align=left&display=inline&height=184&margin=%5Bobject%20Object%5D&name=image.png&originHeight=367&originWidth=614&size=26098&status=done&style=none&width=307)
   5. "/searchTips":模糊搜索小说名(redis缓存)
      - title:搜索串
      - ![image.png](https://cdn.nlark.com/yuque/0/2021/png/21696663/1626698312451-72a4210b-fa89-4a8c-b171-feaf96ffd690.png#align=left&display=inline&height=187&margin=%5Bobject%20Object%5D&name=image.png&originHeight=374&originWidth=446&size=20152&status=done&style=none&width=223)
   6. "/search":模糊查询
      - title:搜索串
      - ![image.png](https://cdn.nlark.com/yuque/0/2021/png/21696663/1626698359641-c43a1c3c-1060-49a9-8d17-56659b96066c.png#align=left&display=inline&height=183&margin=%5Bobject%20Object%5D&name=image.png&originHeight=365&originWidth=630&size=27132&status=done&style=none&width=315)
- 章节信息后台接口:
   7. "/book/{novelkey}":搜索小说章节信息和小说信息
      - novelkey:小说id
      - ![image.png](https://cdn.nlark.com/yuque/0/2021/png/21696663/1626698420674-df93fd59-cf02-417f-b3c3-4f9b40288ab1.png#align=left&display=inline&height=218&margin=%5Bobject%20Object%5D&name=image.png&originHeight=435&originWidth=445&size=26395&status=done&style=none&width=223)
   8. "/book/{novelkey}/{chapterweight}":章节详情的完善和返回(包括章节内容)
      - novelkey:小说id
      - chapterweight:小说章节id
      - ![image.png](https://cdn.nlark.com/yuque/0/2021/png/21696663/1626698466773-9daf07ba-ad41-4294-8f25-984017b8393b.png#align=left&display=inline&height=319&margin=%5Bobject%20Object%5D&name=image.png&originHeight=637&originWidth=428&size=55502&status=done&style=none&width=214)



## 使用教程


1. **项目导入**
1. **通过maven来导入依赖**
   - `ps:由于SpringBoot2.5版本问题,无法使用maven打包项目` 
3. **执行QuxiaoshuoApplication.java**
