package com.scratch.covidfx;

import java.time.LocalDate;
import java.util.Comparator;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CovidTableView extends TableView<CovidRecord> {
  
    private final FilteredList<CovidRecord> filteredTableItems;
    private final SortedList<CovidRecord> sortedTableItems;
    
    public CovidTableView(ObservableList<CovidRecord> originalItems) {
        super();
        this.filteredTableItems = new FilteredList<>(originalItems, s -> true);
        this.sortedTableItems = new SortedList<>(filteredTableItems, Comparator.reverseOrder());
        setItems(sortedTableItems);
        getColumns().add(createLocalDateColumn("Date", "date"));
        getColumns().add(createStringColumn("Country", "country"));
        getColumns().add(createStringColumn("Continent", "continent"));
        getColumns().add(createIntegerColumn("New Cases", "cases"));
        getColumns().add(createIntegerColumn("Deaths", "deaths"));
        getColumns().add(createIntegerColumn("Population", "population"));
    }

    public void filterOnCountry(String countryFilter) {
        if (countryFilter == null) {
            filteredTableItems.setPredicate(s -> true);
        } else {
            filteredTableItems.setPredicate(r -> r.isCountry(countryFilter));
        }
    }

    private TableColumn<CovidRecord, String> createStringColumn(String tableHeading, String propertyName) {
        TableColumn<CovidRecord, String> countryColumn = new TableColumn<>(tableHeading);
        countryColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return countryColumn;
    }

    private TableColumn<CovidRecord, LocalDate> createLocalDateColumn(String tableHeading, String propertyName) {
        TableColumn<CovidRecord, LocalDate> column = new TableColumn<>(tableHeading);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    private TableColumn<CovidRecord, Integer> createIntegerColumn(String tableHeading, String propertyName) {
        TableColumn<CovidRecord, Integer> column = new TableColumn<>(tableHeading);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }
}
