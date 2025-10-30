package com.scratch.covidfx.convert;

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

import com.scratch.covidfx.domain.Country;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ian Fairman <ian.fairman@gmail.com>
 */
public class CovidCsvToRecordConverterTest {
    
    @Test
    void shouldConvertArrayDateToObject() {
        // Given
        var converter = new CovidCsvToRecordConverter();
        var fields = new String[] {"blah", "25", "12", "2025", "24", "23", "Iran"};
        
        // When
        var record = converter.apply(fields);
        
        // Then
        assertEquals(new Country("Iran"), record.country());
   }
}
