package com.scratch.covidfx;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ian Fairman <ian.fairman@gmail.com>
 */
public abstract class CovidRecordFactory {
    
    protected final ReadOnlyBooleanWrapper validProperty = new ReadOnlyBooleanWrapper(this, "valid", true);
    protected final ObservableList<CovidRecord> items = FXCollections.observableArrayList();

    public CovidRecordFactory() {
    }

    public void refresh() {
         items.clear();
       try (InputStream resourceStream = openStream();
                Reader resourceReader = new InputStreamReader(resourceStream);
                BufferedReader reader = new BufferedReader(resourceReader)) {
          CSVReader csvReader = new CSVReader(reader);
          csvReader.skip(1);
          List<String[]> records = csvReader.readAll();
          records.stream().map(CovidRecord::new).forEach(r -> items.add(r));
          setValid(true);
        } catch (IOException | CsvException _) {
          items.clear();
          setValid(false);
        }
    }

    protected abstract InputStream openStream() throws IOException;
    
    public boolean isValid() {
        return validProperty.get();
    }

    protected void setValid(boolean value) {
        validProperty.set(value);
    }

    public ReadOnlyBooleanProperty validProperty() {
        return validProperty.getReadOnlyProperty();
    }

    public ObservableList<CovidRecord> getData() {
        return FXCollections.unmodifiableObservableList(items);
    }
}
