<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="${schema}://${domain}/sitemapindex.xsl"?>
<#setting date_format="YYYY-MM-dd">
<sitemapindex xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/siteindex.xsd"
              xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
    <#list 1..pages as page>
        <sitemap>
            <loc>${schema}://${domain}/sitemap/${page}.xml</loc>
            <lastmod>${date?date}</lastmod>
        </sitemap>
    </#list>

</sitemapindex>
