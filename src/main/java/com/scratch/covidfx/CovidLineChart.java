package com.scratch.covidfx;

import static com.scratch.covidfx.CovidSeriesType.NEW_CASES;
import java.util.Comparator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    
    public final String getFilterCountry() {
        return filterCountryProperty.get();
    }
    
    public void setFilterCountry(String filterCountry) {
        filterCountryProperty.set(filterCountry);
    }
    
    public StringProperty filterCountryProperty() {
        return filterCountryProperty;
    }
    
    private final ObjectProperty<CovidSeriesType> seriesTypeProperty = new SimpleObjectProperty<>(this, "seriesType", NEW_CASES);
    
    public final CovidSeriesType getSeriesType() {
        return seriesTypeProperty.get();
    }
    
    public void setSeriesType(CovidSeriesType seriesType) {
        seriesTypeProperty.set(seriesType);
    }
    
    public ObjectProperty<CovidSeriesType> seriesTypeProperty() {
        return seriesTypeProperty;
    }
    
    private final FilteredList<CovidRecord> filteredItems;
    private final SortedList<CovidRecord> sortedItems;
    private final XYChart.Series series;
    
    public CovidLineChart(ObservableList<CovidRecord> originalItems) {
        super(new CategoryAxis(), new NumberAxis());
        this.filteredItems = new FilteredList<>(originalItems, s -> true);
        this.sortedItems = new SortedList<>(filteredItems, Comparator.reverseOrder());
        getXAxis().setLabel("Date");
        getYAxis().setLabel("People");
        setTitle(getSeriesType().getLabelText());
        series = new XYChart.Series();
        series.setName(getFilterCountry() == null ? "" : getFilterCountry());
        filterCountryProperty.addListener((property, oldValue, newValue) -> {
            getData().clear();
            if (getFilterCountry() == null) {
              filteredItems.setPredicate(s -> true);
              setTitle(getSeriesType().getLabelText());
            } else {
              series.setName(getFilterCountry());
              filteredItems.setPredicate(r -> r.isCountry(getFilterCountry()) && r.isRecent());
              refreshGraph();
            }
        });
        seriesTypeProperty.addListener((property, oldValue, newValue) -> {
            series.setName(getFilterCountry());
            refreshGraph();
        });
    }

    private void refreshGraph() {
        getData().clear();
        if (getFilterCountry() == null) {
            return;
        }
        setTitle(getSeriesType().getLabelText());
        setAnimated(true);
        series.getData().clear();
        sortedItems.forEach(x -> {
            XYChart.Data datum = new XYChart.Data(x.getDate().toString(), getSeriesType().extractSeriesData(x));
            series.getData().add(datum);
        });
        getData().add(series);
        setAnimated(false);
    }
}
