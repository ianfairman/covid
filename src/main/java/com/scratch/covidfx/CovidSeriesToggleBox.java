package com.scratch.covidfx;

import static com.scratch.covidfx.CovidSeriesType.NEW_CASES;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import static com.scratch.covidfx.CovidSeriesType.NEW_DEATHS;

public class CovidSeriesToggleBox extends VBox {
    private final ToggleGroup seriesToggleGroup;
    private final RadioButton newCasesButton;
    private final RadioButton deathsButton;
    private final ReadOnlyObjectWrapper<CovidSeriesType> selectedSeriesProperty = new ReadOnlyObjectWrapper<>(this, "seriesType", NEW_CASES);
    
    public CovidSeriesType getSelectedSeries() {
        return selectedSeriesProperty.get();
    }
    
    private void setSelectedSeries(CovidSeriesType series) {
        selectedSeriesProperty.set(series);
    }
    
    public ReadOnlyObjectProperty selectedSeriesProperty() {
        return selectedSeriesProperty.getReadOnlyProperty();
    }
        
    public CovidSeriesToggleBox() {
        super(8);
        seriesToggleGroup = new ToggleGroup();
        
        newCasesButton = new RadioButton("New Cases");
        newCasesButton.setToggleGroup(seriesToggleGroup);
        newCasesButton.setSelected(true);
        newCasesButton.setUserData(NEW_CASES);
        
        deathsButton = new RadioButton("New Deaths");
        deathsButton.setToggleGroup(seriesToggleGroup);
        deathsButton.setUserData(NEW_DEATHS);
        
        Label titleLabel = new Label("Data type:");
        getChildren().addAll(titleLabel, newCasesButton, deathsButton);

        seriesToggleGroup.selectedToggleProperty()
                .addListener(x -> setSelectedSeries((CovidSeriesType) seriesToggleGroup.getSelectedToggle().getUserData()));
    }
}
