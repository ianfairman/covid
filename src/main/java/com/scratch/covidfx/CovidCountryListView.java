package com.scratch.covidfx;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

// TODO: make more dynamic

public class CovidCountryListView extends ListView<String> {

    public CovidCountryListView(ObservableList<CovidRecord> records) {
        ObservableList<String> countries = FXCollections.observableArrayList();
        records.stream().map(r -> r.getCountry())
                  .distinct().forEach(c -> countries.add(c));
        setItems(countries);
        getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, 
                    String oldValue, String newValue) {
                  setSelectedCountry(newValue);
            }
        });
    }
    
    private final ReadOnlyStringWrapper selectedCountryProperty = new ReadOnlyStringWrapper(this, "selectedCountry", null);
    
    public String getSelectedCountry() {
        return selectedCountryProperty.get();
    }
    
    private void setSelectedCountry(String selectedCountry) {
        selectedCountryProperty.set(selectedCountry);
    }
    
    public ReadOnlyStringProperty selectedCountryProperty() {
        return selectedCountryProperty.getReadOnlyProperty();
    }
}
