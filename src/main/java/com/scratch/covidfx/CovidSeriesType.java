package com.scratch.covidfx;

import java.util.function.Function;

public enum CovidSeriesType {
    NEW_CASES("New Cases", r -> r.getCases()),
    NEW_DEATHS("New Deaths", r -> r.getDeaths()),
    AVERAGE_CASES("7 Day Avg. Cases", r -> r.getSevenDayAverageCases()),
    AVERAGE_DEATHS("7 Day Avg. Deaths", r -> r.getSevenDayAverageDeaths()),
    CUMULATIVE_CASES("Cumulative Cases", r -> r.getCumulativeCases()),
    CUMULATIVE_DEATHS("Cumulative Deaths", r -> r.getCumulativeDeaths());
    
    
    private final String labelText;
    private final Function<CumulativeCovidRecord, Integer> extractorFunction;

    private CovidSeriesType(String labelText, Function<CumulativeCovidRecord, Integer> extractorFunction) {
        this.labelText = labelText;
        this.extractorFunction = extractorFunction;
    }

    public String getLabelText() {
        return labelText;
    }
    
    public Integer extractSeriesData(CumulativeCovidRecord record) {
        return extractorFunction.apply(record);
    }
}
