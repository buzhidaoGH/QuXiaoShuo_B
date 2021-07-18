## 趣小说
+ **技术:SpringBoot + SpringMVC + Spring + Mybatis + Redis + Webmagic + Tomcat + Nginx(前端) + Vue(前端)**
+ 主要问题:小说数据过于庞大,一次性无法爬取,爬取速度太快有被限制访问的风险
    + 先通过Webmagic爬取所有小说基本信息(不包括章节信息和章节内容,标记为未存在)
        + https://github.com/buzhidaoGH/quxiaoshuo-task
    + 再通过网络访问,访问的数据再去二次网络爬取(更改标记为存在,当标记存在时直接访问数据库)

## 趣小说数据来源
+ 本小说后台,爬取分析的网站为"http://www.biquge.tv/"
    + 网站的爬取规则为:
        ```
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
    + 个人获取资源的网页为:
        ```
        http://www.biquge.tv/index
        http://www.biquge.tv/书名代号/
        http://www.biquge.tv/书名代号/章节代号.html
        ```
+ 趣小说网址于2021/7/18更改为https://www.qbiqu.com/
## 趣小说后台功能
+ 小说信息后台接口包括:
    1. "/index":主要用于展示基本的推荐信息
    2. "/randomnovel":按照日期随机推荐小说
    3. "/xiaoshuocategory":展示小说的分类本数
    4. "/xiaoshuo/{category}/{page}":
        + category:小说的分类类型
        + page:页码
    5. "/searchTips":模糊搜索小说名(redis缓存)
        + title:搜索串
    6. "/search":模糊查询
        + title:搜索串
+ 章节信息后台接口:
    1. "/book/{novelkey}":搜索小说章节数
        + novelkey:小说id
    2. "/book/{novelkey}/{chapterweight}":章节详情的完善和返回(包括章节内容)
        + novelkey:小说id
        + chapterweight:小说章节id