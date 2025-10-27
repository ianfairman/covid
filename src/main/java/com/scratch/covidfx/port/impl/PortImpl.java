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
import com.scratch.covidfx.port.LoadPort;
import com.scratch.covidfx.port.QueryPort;
import java.time.LocalDate;
import static java.util.Collections.emptyList;
import java.util.List;

/**
 *
 * @author Ian Fairman <ian.fairman@gmail.com>
 */
public class PortImpl implements LoadPort, QueryPort{

    private List<CovidRecord> records = emptyList();
    
    @Override
    public void load(CovidRecord record) {
        records = List.of(record);
    }

    @Override
    public List<Country> getCountries() {
        return records.stream().map(CovidRecord::country).toList();
    }

    @Override
    public List<Record> getRecords() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Record> getRecords(Country country) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
