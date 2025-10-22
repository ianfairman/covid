package com.scratch.covidfx.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.function.Supplier;

/**
 *
 * @author Ian Fairman <ian.fairman@gmail.com>
 */
public class UrlInputStreamSupplier implements Supplier<InputStream> {

    private final URL url;
    
    public UrlInputStreamSupplier(URL url) {
        this.url = url;
    }

    @Override
    public InputStream get() {
        try {
            return url.openStream();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
