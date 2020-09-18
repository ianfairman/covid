package com.scratch.covidfx;

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

// https://www.ecdc.europa.eu/en/publications-data/download-todays-data-geographic-distribution-covid-19-cases-worldwide

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Covid-19 Stats");

        final LocalCovidRecordFactory recordFactory =
                new LocalCovidRecordFactory("/covid-19-20200918.csv");
        final CovidTableView tableView = new CovidTableView(recordFactory.getData());
        final CovidLineChart lineChart = new CovidLineChart(recordFactory.getData());
                
        ObservableList<String> countries = FXCollections.observableArrayList();
        recordFactory.getData().stream().map(r -> r.getCountry())
                  .distinct().forEach(c -> countries.add(c));

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
}