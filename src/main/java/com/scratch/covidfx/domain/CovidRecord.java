package com.scratch.covidfx.domain;

import com.scratch.covidfx.domain.Continent;
import com.scratch.covidfx.domain.ContinentName;
import static com.scratch.covidfx.domain.CountryName.lookupCountryName;
import com.scratch.covidfx.domain.CountryName;
import static java.util.Objects.requireNonNull;
import static com.scratch.covidfx.domain.Continent.lookupContinent;
import static com.scratch.covidfx.domain.ContinentName.lookupContinentName;
import com.scratch.covidfx.domain.Country;
import static com.scratch.covidfx.domain.Country.lookupCountry;
import com.scratch.covidfx.domain.CountryCode;
import static com.scratch.covidfx.domain.CountryCode.lookupCountryCode;
import com.scratch.covidfx.domain.CountryCode2;
import static com.scratch.covidfx.domain.CountryCode2.lookupCountryCode2;
import com.scratch.covidfx.domain.CountryCode3;
import static com.scratch.covidfx.domain.CountryCode3.lookupCountryCode3;
import com.scratch.covidfx.domain.PersonCount;
import static com.scratch.covidfx.domain.PersonCount.createPersonCount;

public class CovidRecord {

  private final String[] fields;
  
  public CovidRecord(String[] fields) {
    this.fields = requireNonNull(fields);
  }

  private String getDateField() {
    return fields[0];
  }
  
  private String getDayField() {
    return fields[1];
  }
  
  private String getMonthField() {
    return fields[2];
  }
  
  private String getYearField() {
    return fields[3];
  }
  
  private String getCasesField() {
    return fields[4];
  }
  
  private PersonCount getCases() {
    return createPersonCount(getCasesField());
  }
  
  private String getDeathsField() {
    return fields[5];
  }
  
  private PersonCount getDeaths() {
    return createPersonCount(getDeathsField());
  }
  
  private String getCountryNameField() {
    return fields[6];
  }
  
  private CountryName getCountryName() {
    return lookupCountryName(getCountryNameField());
  }
  
  private Country getCountry() {
    return lookupCountry(getCountryName(), getContinent(), getCountryCode());
  }
  
  private String getCountryCode2Field() {
    return fields[7];
  }
  
  private CountryCode2 getCountryCode2() {
    return lookupCountryCode2(getCountryCode2Field());
  }
  
  private String getCountryCode3Field() {
    return fields[8];
  }
  
  private CountryCode3 getCountryCode3() {
    return lookupCountryCode3(getCountryCode3Field());
  }
  
  private CountryCode getCountryCode() {
    return lookupCountryCode(getCountryCode2(), getCountryCode3());
  }
  
  private String getPopulationField() {
    return fields[9];
  }
  
  private PersonCount getPopulation() {
    return createPersonCount(getPopulationField());
  }
  
  private String getContinentNameField() {
    return fields[10];
  }
  
  private ContinentName getContinentName() {
    return lookupContinentName(getContinentNameField());
  }
  
  private Continent getContinent() {
    return lookupContinent(getContinentName());
  }
  
  private String getCumulativeCasesField() {
    return fields[11];
  }
}
