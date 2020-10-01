package com.scratch.covidfx;

import static com.scratch.covidfx.CovidSeriesType.NEW_CASES;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class CovidSeriesToggleBox extends VBox {
    private final ToggleGroup seriesToggleGroup;
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
        boolean firstRun = true;
         
        Label titleLabel = new Label("Data type:");
        getChildren().add(titleLabel);

        for (CovidSeriesType type: CovidSeriesType.values()) {
            RadioButton button = new RadioButton(type.getLabelText());
            button.setToggleGroup(seriesToggleGroup);
            button.setSelected(firstRun);
            firstRun = false;
            button.setUserData(type);
            getChildren().add(button);
        }
        
        seriesToggleGroup.selectedToggleProperty()
                .addListener(x -> setSelectedSeries((CovidSeriesType) seriesToggleGroup.getSelectedToggle().getUserData()));
    }
}
