package com.scratch.covidfx;

import com.scratch.covidfx.util.RollingAverage;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class CumulativeCovidRecordFactory {
    
    private final SortedList<CumulativeCovidRecord> data;

    public CumulativeCovidRecordFactory(ObservableList<CovidRecord> records) {
        SortedList<CovidRecord> sortedRecords = new SortedList<>(records, Comparator.reverseOrder());
        String currentCountry = "";
        int cumulativeDeaths = 0;
        int cumulativeCases = 0;
        RollingAverage deathsAverage = new RollingAverage(WINDOW_SIZE);
        RollingAverage casesAverage = new RollingAverage(WINDOW_SIZE);
        ObservableList<CumulativeCovidRecord> cumulativeCovidRecords = FXCollections.observableArrayList();
        for (CovidRecord record : sortedRecords) {
            if (record.isCountry(currentCountry)) {
                cumulativeDeaths += record.getDeaths();
                cumulativeCases += record.getCases();
                deathsAverage.register(record.getDeaths());
                casesAverage.register(record.getCases());
            } else {
                cumulativeDeaths = record.getDeaths();
                cumulativeCases = record.getCases();
                currentCountry = record.getCountry();
                deathsAverage.reset(record.getDeaths());
                casesAverage.reset(record.getCases());
            }
            cumulativeCovidRecords.add(new CumulativeCovidRecord(record, cumulativeCases, cumulativeDeaths,
                    casesAverage.average(), deathsAverage.average()));
        }
        data = new SortedList<>(cumulativeCovidRecords, Comparator.naturalOrder());
    }

    private static final int WINDOW_SIZE = 7;

    public SortedList<CumulativeCovidRecord> getData() {
        return data;
    }
}
