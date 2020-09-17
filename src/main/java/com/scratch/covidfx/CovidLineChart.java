package com.scratch.covidfx;

import java.util.Comparator;
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
    
    private final FilteredList<CovidRecord> filteredItems;
    private final SortedList<CovidRecord> sortedItems;
    private final XYChart.Series newCasesSeries;
    
    public CovidLineChart(ObservableList<CovidRecord> originalItems) {
        super(new CategoryAxis(), new NumberAxis());
        this.filteredItems = new FilteredList<>(originalItems, s -> true);
        this.sortedItems = new SortedList<>(filteredItems, Comparator.reverseOrder());
        getXAxis().setLabel("Date");
        getYAxis().setLabel("People");
        setTitle("Covid-19");
        newCasesSeries = new XYChart.Series();
        newCasesSeries.setName("New Cases");
        getData().add(newCasesSeries);
        filterCountryProperty.addListener((property, oldValue, newValue) -> {
            if (newValue == null) {
              filteredItems.setPredicate(s -> true);
              setTitle("");
              newCasesSeries.getData().clear();
            } else {
              filteredItems.setPredicate(r -> r.isCountry(newValue) && r.isRecent());
              setTitle(newValue);
              newCasesSeries.getData().clear();
              setAnimated(true);
              sortedItems.forEach(x -> {
                newCasesSeries.getData().add(new XYChart.Data(x.getDate().toString(), x.getCases()));
                      });
              setAnimated(false);
            }
        });
    }
}
