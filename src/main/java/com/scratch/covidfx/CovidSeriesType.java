package com.scratch.covidfx;

import java.util.function.Function;

public enum CovidSeriesType {
    NEW_CASES("New Cases", r -> r.getCases()),
    DEATHS("Deaths", r -> r.getDeaths());
    
    private final String labelText;
    private final Function<CovidRecord, Integer> extractorFunction;

    private CovidSeriesType(String labelText, Function<CovidRecord, Integer> extractorFunction) {
        this.labelText = labelText;
        this.extractorFunction = extractorFunction;
    }

    public String getLabelText() {
        return labelText;
    }
    
    public Integer extractSeriesData(CovidRecord record) {
        return extractorFunction.apply(record);
    }
}
