package com.scratch.covidfx;

import java.util.Comparator;
import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class CovidLineChart extends LineChart<String,Number>{

    private final StringProperty filterCountryProperty = new SimpleStringProperty(this, "filterCountry", null);
    
    public String getFilterCountry() {
        return filterCountryProperty.get();
    }
    
    public void setFilterCountry(String filterCountry) {
        filterCountryProperty.set(filterCountry);
    }
    
    public StringProperty filterCountryProperty() {
        return filterCountryProperty;
    }
    
    private final StringProperty seriesIdProperty = new SimpleStringProperty(this, "seriesId", "newCases");
    
    public String getSeriesId() {
        return seriesIdProperty.get();
    }
    
    public void setSeriesId(String seriesId) {
        seriesIdProperty.set(seriesId);
    }
    
    public StringProperty seriesIdProperty() {
        return seriesIdProperty;
    }
    
    private final FilteredList<CovidRecord> filteredItems;
    private final SortedList<CovidRecord> sortedItems;
    private final XYChart.Series series;
    
    private static final Function<CovidRecord, Integer> NEW_CASES_FN = r -> r.getCases();
    private static final Function<CovidRecord, Integer> DEATHS_FN = r -> r.getDeaths();
    
    private Function<CovidRecord, Integer> extractorFn = NEW_CASES_FN;
    
    public CovidLineChart(ObservableList<CovidRecord> originalItems) {
        super(new CategoryAxis(), new NumberAxis());
        this.filteredItems = new FilteredList<>(originalItems, s -> true);
        this.sortedItems = new SortedList<>(filteredItems, Comparator.reverseOrder());
        getXAxis().setLabel("Date");
        getYAxis().setLabel("People");
        setTitle("Covid-19");
        series = new XYChart.Series();
        series.setName("New Cases");
        getData().add(series);
        filterCountryProperty.addListener((property, oldValue, newValue) -> {
            if (getFilterCountry() == null) {
              filteredItems.setPredicate(s -> true);
              setTitle("");
              series.getData().clear();
            } else {
              filteredItems.setPredicate(r -> r.isCountry(getFilterCountry()) && r.isRecent());
              refreshGraph();
            }
        });
        seriesIdProperty.addListener((property, oldValue, newValue) -> {
            if (getSeriesId() == null || getSeriesId().equals("newCases")) {
                extractorFn = NEW_CASES_FN;
                series.setName("New Cases");
            } else {
                extractorFn = DEATHS_FN;
                series.setName("Deaths");
            }
            refreshGraph();
        });
    }

    private void refreshGraph() {
        if (getFilterCountry() == null) {
            return;
        }
        setTitle(getFilterCountry());
        series.getData().clear();
        setAnimated(true);
        sortedItems.forEach(x -> {
            series.getData().add(new XYChart.Data(x.getDate().toString(), extractorFn.apply(x)));
        });
        setAnimated(false);
    }
}
