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
package com.scratch.covidfx.convert;

import com.scratch.covidfx.domain.Country;
import com.scratch.covidfx.domain.CovidRecord;
import com.scratch.covidfx.domain.Stats;
import java.time.LocalDate;
import java.util.function.Function;

/**
 *
 * @author Ian Fairman <ian.fairman@gmail.com>
 */
public class CovidCsvToRecordConverter implements Function<String[], CovidRecord> {

    @Override
    public CovidRecord apply(String[] t) {
        
        LocalDate date = LocalDate.of(
                Integer.parseInt(t[3]),
                Integer.parseInt(t[2]), 
                Integer.parseInt(t[1]));
        
        Country country = new Country(t[6].replace('_', ' '));
        
        int cases = Integer.parseInt(t[4]);
        int deaths = Integer.parseInt(t[5]);
        Stats stats = new Stats(cases, deaths);
        
        return new CovidRecord(country, date, stats);
    }
}