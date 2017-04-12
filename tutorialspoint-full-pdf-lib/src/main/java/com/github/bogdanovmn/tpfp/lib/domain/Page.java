package com.github.bogdanovmn.tpfp.lib.domain;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
public class Page {
    private final String title;
    private final String url;

    public Page(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public File toPdf()
        throws IOException
    {
        File tmpFile = File.createTempFile("tpfp.", ".tmp.pdf");
        FileUtils.copyURLToFile(this.getPdfUrl(), tmpFile);

System.out.println(this.url);
System.out.println(tmpFile.getAbsolutePath());
System.out.println(this.getPdfUrl());

        tmpFile.deleteOnExit();

        return tmpFile;
    }

    private URL getPdfUrl() throws MalformedURLException {
        return new URL(
            this.url.replaceFirst("(.*)/([^/]+)\\.htm$", "$1/pdf/$2.pdf")
        );
    }
}
