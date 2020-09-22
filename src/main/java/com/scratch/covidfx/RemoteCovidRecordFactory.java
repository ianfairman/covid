package com.scratch.covidfx;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import static java.util.Objects.requireNonNull;

public class RemoteCovidRecordFactory extends CovidRecordFactory {

    private final URL url;
    
    public RemoteCovidRecordFactory(String urlString) {
        
        try {
            this.url = new URL(requireNonNull(urlString));
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
        refresh();
    }
    
    @Override
    protected InputStream openStream() throws IOException {
        return url.openStream();
    }
}
