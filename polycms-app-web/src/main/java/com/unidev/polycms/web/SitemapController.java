package com.unidev.polycms.web;

import com.unidev.platform.j2ee.common.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class SitemapController {

    private static final Long ITEM_PER_PAGE = 10000L;

    @Autowired
    private WebPolyCore webPolyCore;

    @Autowired
    private WebUtils webUtils;

    @Autowired
    private HttpServletRequest context;

    @RequestMapping(value = "/sitemap.xml",method = RequestMethod.GET)
    public String sitemapIndexFiles(Model model, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        String domain = webUtils.removeWWW(webUtils.getDomain(context));
        model.addAttribute("schema", context.getScheme());
        model.addAttribute("domain", domain);

        WebPolyQuery polyQuery = WebPolyQuery.polyRequest()
                .model(model)
                .request(context);

        Long polyCount = webPolyCore.fetchPolyCount(polyQuery);

        if (polyCount<ITEM_PER_PAGE) {
            WebPolyQuery firstPageQuery = WebPolyQuery.polyRequest()
                    .model(model)
                    .request(context)
                    .page(0L).itemPerPage(ITEM_PER_PAGE);

            webPolyCore.addNew(firstPageQuery, "");
            return "sitemap.xml";
        }

        long pages = (polyCount / ITEM_PER_PAGE) + 1;
        model.addAttribute("pages", pages);
        model.addAttribute("date", new Date());

        return "sitemapindex.xml";
    }

    @RequestMapping("/sitemap/{page}.xml")
    public String sitemapFile(@PathVariable("page") Long page, Model model, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        if (page <= 0) {
            page = 1L;
        }
        String domain = webUtils.removeWWW(webUtils.getDomain(context));
        model.addAttribute("schema", context.getScheme());
        model.addAttribute("domain", domain);

        WebPolyQuery firstPageQuery = WebPolyQuery.polyRequest()
                .model(model)
                .request(context)
                .page(page + 1).itemPerPage(ITEM_PER_PAGE);

        webPolyCore.addNew(firstPageQuery, "");
        return "sitemap.xml";
    }

    @RequestMapping(value = "/sitemapindex.xsl",method = RequestMethod.GET)
    public String sitemapIndex(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        return "sitemapindex.xsl";
    }

    @RequestMapping(value = "/sitemap.xsl",method = RequestMethod.GET)
    public String sitemap(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        return "sitemap.xsl";
    }

}
