package com.scratch.covidfx;

import java.io.IOException;
import java.io.InputStream;
import static java.util.Objects.requireNonNull;

public final class LocalCovidRecordFactory extends CovidRecordFactory {

    private final String resourcePath;
    
    public LocalCovidRecordFactory(String resourcePath) {
        this.resourcePath = requireNonNull(resourcePath, "resourcePath");
        refresh();
    }
    
    @Override
    protected InputStream openStream() throws IOException {
        return LocalCovidRecordFactory.class.getResourceAsStream(resourcePath);
    }
}
