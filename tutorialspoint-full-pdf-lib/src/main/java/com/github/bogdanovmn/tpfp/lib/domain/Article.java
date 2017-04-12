package com.github.bogdanovmn.tpfp.lib.domain;

import com.github.bogdanovmn.tpfp.lib.common.LinkedHashMapArrayList;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class Article {
    private final LinkedHashMapArrayList<String, Page> pages;

    public Article(LinkedHashMapArrayList<String, Page> pages) {
        this.pages = pages;
    }

    public void toPdf(String output)
        throws IOException
    {
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        for (String sectionTitle : this.pages.keys()) {
            List<Page> pages = this.pages.get(sectionTitle);
            for (Page page : pages) {
                File pdfFile = page.toPdf();
                pdfMergerUtility.addSource(pdfFile);
            }
        }
        pdfMergerUtility.setDestinationFileName(output);
        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
    }
}
