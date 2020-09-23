package com.scratch.covidfx;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class CovidSeriesToggleBox extends VBox {
    private final ToggleGroup seriesToggleGroup;
    private final RadioButton newCasesButton;
    private final RadioButton deathsButton;
    private final ReadOnlyStringWrapper selectedSeriesProperty = new ReadOnlyStringWrapper();
    
    public String getSelectedSeries() {
        return selectedSeriesProperty.get();
    }
    
    private void setSelectedSeries(String series) {
        selectedSeriesProperty.set(series);
    }
    
    public ReadOnlyStringProperty selectedSeriesProperty() {
        return selectedSeriesProperty.getReadOnlyProperty();
    }
        
    public CovidSeriesToggleBox() {
        seriesToggleGroup = new ToggleGroup();
        
        newCasesButton = new RadioButton("New Cases");
        newCasesButton.setToggleGroup(seriesToggleGroup);
        newCasesButton.setSelected(true);
        newCasesButton.setUserData("newCases");
        
        deathsButton = new RadioButton("Deaths");
        deathsButton.setToggleGroup(seriesToggleGroup);
        deathsButton.setUserData("deaths");
        
        getChildren().addAll(newCasesButton, deathsButton);

        seriesToggleGroup.selectedToggleProperty()
                .addListener(x -> setSelectedSeries(seriesToggleGroup.getSelectedToggle().getUserData().toString()));
    }
}
