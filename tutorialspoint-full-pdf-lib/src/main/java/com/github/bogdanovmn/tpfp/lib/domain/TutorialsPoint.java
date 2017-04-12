package com.github.bogdanovmn.tpfp.lib.domain;

import com.github.bogdanovmn.tpfp.lib.common.LinkedHashMapArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *
 */
public class TutorialsPoint {
    private final static String PREFIX = "https://www.tutorialspoint.com";

    public Article getArticle(String articlePathName)
        throws IOException
    {
        LinkedHashMapArrayList<String, Page> pages = new LinkedHashMapArrayList<>();

        Document document = Jsoup.connect(
            String.format("%s/%s/", PREFIX, articlePathName)
        ).get();

        Elements sections = document.select("ul[class^=nav nav-list primary left-menu]");
        for (Element section : sections) {
            Element sectionHeader = section.select("li[class=heading]").first();
            for (Element link : section.select("a[href]")) {
                pages.put(
                    sectionHeader.text(),
                    new Page(link.text(), PREFIX + link.attr("href"))
                );
            }
        }

        return new Article(pages);
    }
}
