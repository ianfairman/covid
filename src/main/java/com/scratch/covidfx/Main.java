package com.scratch.covidfx;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Covid-19 Stats");

        ObservableList<CovidRecord> items = FXCollections.observableArrayList();
        final FilteredList<CovidRecord> filteredItems = new FilteredList<>(items, s -> true);
        final SortedList<CovidRecord> sortedItems = new SortedList<>(filteredItems, Comparator.naturalOrder());
        TableView<CovidRecord> tableView = new TableView<>(sortedItems);

        tableView.getColumns().add(createLocalDateColumn("Date", "date"));
        tableView.getColumns().add(createStringColumn("Country", "country"));
        tableView.getColumns().add(createStringColumn("Continent", "continent"));
        tableView.getColumns().add(createIntegerColumn("New Cases", "cases"));
        tableView.getColumns().add(createIntegerColumn("Deaths", "deaths"));
        tableView.getColumns().add(createIntegerColumn("Population", "population"));

        ObservableList<String> countries = FXCollections.observableArrayList();
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("People");
        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
                
        lineChart.setTitle("New Cases by Date");
        final XYChart.Series newCasesSeries = new XYChart.Series();
        newCasesSeries.setName("New Cases");
        
        lineChart.getData().add(newCasesSeries);        
        try (InputStream resourceStream = Main.class.getResourceAsStream("/covid-19-20200902.csv");
                Reader resourceReader = new InputStreamReader(resourceStream);
                BufferedReader reader = new BufferedReader(resourceReader)) {
          CSVReader csvReader = new CSVReader(reader);
          csvReader.skip(1);
          List<String[]> records = csvReader.readAll();
          records.stream().map(CovidRecord::new).forEach(r -> items.add(r));
          records.stream().map(r -> r[COUNTRY_COLUMN_INDEX])
                  .map(r -> r.replace('_', ' ')).distinct().forEach(c -> countries.add(c));
        } catch (IOException | CsvException ex) {
          throw new RuntimeException(ex);
        }

        ListView<String> countryList = new ListView<>();
        countryList.setItems(countries);
        countryList.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, 
                    String oldValue, String newVal) {
                  if (newVal == null) {
                    filteredItems.setPredicate(s -> true);
                    lineChart.setTitle("");
                    newCasesSeries.getData().clear();
                  } else {
                    filteredItems.setPredicate(r -> r.getCountry().equals(newVal) && r.calculateAgeInDays() <= 31);
                    lineChart.setTitle(newVal);
                    newCasesSeries.getData().clear();
                    sortedItems.forEach(x -> {
                      newCasesSeries.getData().add(new XYChart.Data(x.getDate().toString(), x.getCases()));
                            });
                  }
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(tableView, countryList);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(new CovidMenuBar(), hBox, lineChart);

        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static final int COUNTRY_COLUMN_INDEX = 6;

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