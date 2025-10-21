package com.scratch.covidfx;

import java.io.IOException;
import java.io.InputStream;
import static java.util.Objects.requireNonNull;

public final class ResourceCovidRecordFactory extends CovidRecordFactory {

    private final String resourcePath;
    
    public ResourceCovidRecordFactory(String resourcePath) {
        this.resourcePath = requireNonNull(resourcePath, "resourcePath");
        refresh();
    }
    
    @Override
    protected InputStream openStream() throws IOException {
        return ResourceCovidRecordFactory.class.getResourceAsStream(resourcePath);
    }
}
