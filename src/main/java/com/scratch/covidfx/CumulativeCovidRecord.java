package com.scratch.covidfx;

import java.time.LocalDate;

public class CumulativeCovidRecord implements Comparable<CumulativeCovidRecord> {

    private final int cumulativeDeaths;
    private final int cumulativeCases;
    private final int sevenAverageDeaths;
    private final int sevenDayAverageCases;
    
    private final CovidRecord record;

    public CumulativeCovidRecord(CovidRecord record, int cumulativeCases, int cumulativeDeaths,
                                    int sevenDayAverageCases, int sevenDayAverageDeaths) {
        this.record = record;
        this.cumulativeCases = cumulativeCases;
        this.cumulativeDeaths = cumulativeDeaths;
        this.sevenDayAverageCases = sevenDayAverageCases;
        this.sevenAverageDeaths = sevenDayAverageDeaths;
    }

    public int getCumulativeDeaths() {
        return cumulativeDeaths;
    }

    public int getCumulativeCases() {
        return cumulativeCases;
    }

    public int getSevenDayAverageDeaths() {
        return sevenAverageDeaths;
    }

    public int getSevenDayAverageCases() {
        return sevenDayAverageCases;
    }

    public LocalDate getDate() {
        return record.getDate();
    }

    public int getCases() {
        return record.getCases();
    }

    public int getDeaths() {
        return record.getDeaths();
    }

    public String getCountry() {
        return record.getCountry();
    }

    public String getPopulation() {
        return record.getPopulation();
    }

    public String getContinent() {
        return record.getContinent();
    }

    @Override
    public int compareTo(CumulativeCovidRecord o) {
        return record.compareTo(o.record);
    }

    public boolean isCountry(String country) {
        return record.isCountry(country);
    }
}
