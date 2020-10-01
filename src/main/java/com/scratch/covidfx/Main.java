package com.scratch.covidfx;

import javafx.application.Application;
import javafx.scene.Scene;
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

        final CovidRecordFactory recordFactory =
                new RemoteCovidRecordFactory("https://opendata.ecdc.europa.eu/covid19/casedistribution/csv");
//                new LocalCovidRecordFactory("/covid-19-20200918.csv");
        
        final CumulativeCovidRecordFactory cumulativeRecordFactory = new CumulativeCovidRecordFactory(recordFactory.getData());
        final CovidCountryListView countryList = new CovidCountryListView(recordFactory.getData());
        
        final CovidTableView tableView = new CovidTableView(cumulativeRecordFactory.getData());
        tableView.filterCountryProperty().bind(countryList.selectedCountryProperty());
        
        final CovidLineChart lineChart = new CovidLineChart(cumulativeRecordFactory.getData());
        lineChart.filterCountryProperty().bind(countryList.selectedCountryProperty());
        
        final CovidSeriesToggleBox seriesToggleBox = new CovidSeriesToggleBox();
        lineChart.seriesTypeProperty().bind(seriesToggleBox.selectedSeriesProperty());
        
        HBox hBox = new HBox(8);
        hBox.getChildren().addAll(tableView, countryList, seriesToggleBox);
        VBox vBox = new VBox(8);
        vBox.getChildren().addAll(new CovidMenuBar(), hBox, lineChart);

        Scene scene = new Scene(vBox, 1040, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}