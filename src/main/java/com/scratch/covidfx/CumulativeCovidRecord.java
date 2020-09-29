package com.scratch.covidfx;

import java.time.LocalDate;

public class CumulativeCovidRecord implements Comparable<CumulativeCovidRecord> {

    private final int cumulativeDeaths;
    private final int cumulativeCases;
    
    private final CovidRecord record;

    public CumulativeCovidRecord(CovidRecord record, int cumulativeCases, int cumulativeDeaths) {
        this.record = record;
        this.cumulativeCases = cumulativeCases;
        this.cumulativeDeaths = cumulativeDeaths;
    }

    public int getCumulativeDeaths() {
        return cumulativeDeaths;
    }

    public int getCumulativeCases() {
        return cumulativeCases;
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
