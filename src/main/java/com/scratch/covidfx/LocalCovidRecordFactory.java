package com.scratch.covidfx;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import static java.util.Objects.requireNonNull;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class LocalCovidRecordFactory {

    private final String resourcePath;
    
    public LocalCovidRecordFactory(String resourcePath) {
        this.resourcePath = requireNonNull(resourcePath, "resourcePath");
        refresh();
    }

    public void refresh() {
        items.clear();
        try (InputStream resourceStream = LocalCovidRecordFactory.class.getResourceAsStream(resourcePath);
                Reader resourceReader = new InputStreamReader(resourceStream);
                BufferedReader reader = new BufferedReader(resourceReader)) {
          CSVReader csvReader = new CSVReader(reader);
          csvReader.skip(1);
          List<String[]> records = csvReader.readAll();
          records.stream().map(CovidRecord::new).forEach(r -> items.add(r));
          setValid(true);
        } catch (IOException | CsvException ex) {
          items.clear();
          setValid(false);
        }

    }
    
    private final ReadOnlyBooleanWrapper validProperty =  new ReadOnlyBooleanWrapper(this, "valid", true);
    
    public boolean isValid() {
        return validProperty.get();
    }
    
    private void setValid(boolean value) {
        validProperty.set(value);
    }
    
    public ReadOnlyBooleanProperty validProperty() {
        return validProperty.getReadOnlyProperty();
    }
    
    private final ObservableList<CovidRecord> items = FXCollections.observableArrayList();
    
    public ObservableList<CovidRecord> getData() {
        return FXCollections.unmodifiableObservableList(items);
    }
}
