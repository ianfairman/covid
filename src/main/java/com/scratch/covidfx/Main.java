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
        primaryStage.setTitle("Covid-19 App");

        ObservableList<CovidRecord> items = FXCollections.observableArrayList();
        final FilteredList<CovidRecord> filteredItems = new FilteredList<>(items, s -> true);
        final SortedList<CovidRecord> sortedItems = new SortedList<>(filteredItems, Comparator.reverseOrder());
        TableView<CovidRecord> tableView = new TableView<>(sortedItems);

        TableColumn<CovidRecord, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<CovidRecord, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        TableColumn<CovidRecord, String> continentColumn = new TableColumn<>("Continent");
        continentColumn.setCellValueFactory(new PropertyValueFactory<>("continent"));
        TableColumn<CovidRecord, Integer> casesColumn = new TableColumn<>("New Cases");
        casesColumn.setCellValueFactory(new PropertyValueFactory<>("cases"));
        TableColumn<CovidRecord, Integer> deathsColumn = new TableColumn<>("Deaths");
        deathsColumn.setCellValueFactory(new PropertyValueFactory<>("deaths"));
        TableColumn<CovidRecord, Integer> populationColumn = new TableColumn<>("Population");
        populationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));

        tableView.getColumns().add(dateColumn);
        tableView.getColumns().add(countryColumn);
        tableView.getColumns().add(continentColumn);
        tableView.getColumns().add(casesColumn);
        tableView.getColumns().add(deathsColumn);
        tableView.getColumns().add(populationColumn);

        ObservableList<String> countries = FXCollections.observableArrayList();
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("People");
        //creating the chart
        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
                
        lineChart.setTitle("New Cases by Date");
        //defining a series
        final XYChart.Series newCasesSeries = new XYChart.Series();
        newCasesSeries.setName("New Cases");
        final XYChart.Series deathsSeries = new XYChart.Series();
        deathsSeries.setName("Deaths");
        
        lineChart.getData().add(newCasesSeries);        
        lineChart.getData().add(deathsSeries);        
        try (InputStream resourceStream = Main.class.getResourceAsStream("/covid-19-20200902.csv");
                Reader resourceReader = new InputStreamReader(resourceStream);
                BufferedReader reader = new BufferedReader(resourceReader)) {
          CSVReader csvReader = new CSVReader(reader);
          csvReader.skip(1);
          List<String[]> records = csvReader.readAll();
          records.stream().map(CovidRecord::new).forEach(r -> items.add(r));
          records.stream().map(r -> r[6]).map(r -> r.replace('_', ' ')).distinct().forEach(c -> countries.add(c));
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
                    deathsSeries.getData().clear();
                  } else {
                    filteredItems.setPredicate(r -> r.getCountry().equals(newVal));
                    lineChart.setTitle(newVal);
                    newCasesSeries.getData().clear();
                    deathsSeries.getData().clear();
                    sortedItems.forEach(x -> {
                      newCasesSeries.getData().add(new XYChart.Data(x.getDate().toString(), x.getCases()));
                      deathsSeries.getData().add(new XYChart.Data(x.getDate().toString(), x.getDeaths()));
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
}