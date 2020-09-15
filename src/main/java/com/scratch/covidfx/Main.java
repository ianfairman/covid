package com.scratch.covidfx;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
        final CovidTableView tableView = new CovidTableView(items);
        final CovidLineChart lineChart = new CovidLineChart(items);
                
        ObservableList<String> countries = FXCollections.observableArrayList();
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
                    String oldValue, String newValue) {
                  tableView.setFilterCountry(newValue);
                  lineChart.setFilterCountry(newValue);
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
}