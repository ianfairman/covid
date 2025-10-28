/*
 * Copyright 2025 Ian Fairman <ian.fairman@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scratch.covidfx.port.impl;

import com.scratch.covidfx.domain.Country;
import com.scratch.covidfx.domain.CovidRecord;
import com.scratch.covidfx.domain.Stats;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ian Fairman <ian.fairman@gmail.com>
 */
public class PortImplTest {
        
    @Test
    void shouldReturnNoCountriesIfNoneHaveBeenLoaded() {
        // Given
        var port = new PortImpl();
        
        // When
        var countries = port.getCountries();
        
        // Then
        assertEquals(0, countries.size());
    }
    
    @Test
    void shouldReturnOneCountryIfOneRecordIsLoaded() {
        // Given
        var port = new PortImpl();
        port.load(new CovidRecord(new Country("Vanatu"), LocalDate.now(), new Stats(0, 0)));
        
        // When
        var countries = port.getCountries();
        
        // Then
        assertEquals(1, countries.size());
    }
    
    @Test
    void shouldReturnCountryIfOneRecordIsLoaded() {
        // Given
        var port = new PortImpl();
        port.load(new CovidRecord(new Country("Fiji"), LocalDate.now(), new Stats(0, 0)));
        
        // When
        var countries = port.getCountries();
        
        // Then
        assertEquals(new Country("Fiji"), countries.getFirst());
    }
    
    @Test
    void shouldReturnTwoCountriesForTwoRecordsWithDifferentCountries() {
                // Given
        var port = new PortImpl();
        port.load(new CovidRecord(new Country("Fiji"), LocalDate.now(), new Stats(0, 0)));
        port.load(new CovidRecord(new Country("France"), LocalDate.now(), new Stats(0,0)));
        
        // When
        var countries = port.getCountries();
        
        // Then
        assertEquals(2, countries.size());
    }
}
