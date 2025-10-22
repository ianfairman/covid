package com.scratch.covidfx;

import java.time.LocalDate;
import java.util.Comparator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CovidTableView extends TableView<CumulativeCovidRecord> {
  
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
    
    private final FilteredList<CumulativeCovidRecord> filteredTableItems;
    private final SortedList<CumulativeCovidRecord> sortedTableItems;
    
    public CovidTableView(ObservableList<CumulativeCovidRecord> originalItems) {
        super();
        this.filteredTableItems = new FilteredList<>(originalItems, s -> true);
        this.sortedTableItems = new SortedList<>(filteredTableItems, Comparator.naturalOrder());
        setItems(sortedTableItems);
        
        getColumns().add(createLocalDateColumn("Date", "date"));
        getColumns().add(createStringColumn("Country", "country"));
        getColumns().add(createStringColumn("Continent", "continent"));
        TableColumn<CumulativeCovidRecord, String> casesColumn = new TableColumn<>("Cases");
        casesColumn.getColumns().add(createIntegerColumn("New", "cases"));
        casesColumn.getColumns().add(createIntegerColumn("7-Day Avg.", "sevenDayAverageCases"));
        casesColumn.getColumns().add(createIntegerColumn("Total", "cumulativeCases"));
        getColumns().add(casesColumn);
        TableColumn<CumulativeCovidRecord, String> deathsColumn = new TableColumn<>("Deaths");
        deathsColumn.getColumns().add(createIntegerColumn("New", "deaths"));
        deathsColumn.getColumns().add(createIntegerColumn("7-Day Avg.", "sevenDayAverageDeaths"));
        deathsColumn.getColumns().add(createIntegerColumn("Total", "cumulativeDeaths"));
        getColumns().add(deathsColumn);
//        getColumns().add(createIntegerColumn("Population", "population"));
        
        filterCountryProperty.addListener((property, oldValue, newValue) -> {
            if (newValue == null) {
                filteredTableItems.setPredicate(s -> true);
            } else {
                filteredTableItems.setPredicate(r -> r.isCountry(newValue));
            }
        });
    }

    private TableColumn<CumulativeCovidRecord, String> createStringColumn(String tableHeading, String propertyName) {
        TableColumn<CumulativeCovidRecord, String> column = new TableColumn<>(tableHeading);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    private TableColumn<CumulativeCovidRecord, LocalDate> createLocalDateColumn(String tableHeading, String propertyName) {
        TableColumn<CumulativeCovidRecord, LocalDate> column = new TableColumn<>(tableHeading);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    private TableColumn<CumulativeCovidRecord, Integer> createIntegerColumn(String tableHeading, String propertyName) {
        TableColumn<CumulativeCovidRecord, Integer> column = new TableColumn<>(tableHeading);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }
}
