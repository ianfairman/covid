package com.scratch.covidfx;

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
        ObservableList<CumulativeCovidRecord> cumulativeCovidRecords = FXCollections.observableArrayList();
        for (CovidRecord record : sortedRecords) {
            if (record.isCountry(currentCountry)) {
                cumulativeDeaths += record.getDeaths();
                cumulativeCases += record.getCases();
            } else {
                cumulativeDeaths = record.getDeaths();
                cumulativeCases = record.getCases();
                currentCountry = record.getCountry();
            }
            cumulativeCovidRecords.add(new CumulativeCovidRecord(record, cumulativeCases, cumulativeDeaths));
        }
        data = new SortedList<>(cumulativeCovidRecords, Comparator.naturalOrder());
    }

    public SortedList<CumulativeCovidRecord> getData() {
        return data;
    }
}
