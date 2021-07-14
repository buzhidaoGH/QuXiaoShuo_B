## 趣小说后台
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
