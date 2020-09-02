package com.scratch.covidfx;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.util.Objects.requireNonNull;

public class CovidRecord implements Comparable<CovidRecord> {

  private final String[] fields;
  
  public CovidRecord(String... fields) {
    this.fields = requireNonNull(fields);
  }

  private int getDay() {
    return Integer.parseInt(fields[1]);
  }
  
  private int getMonth() {
    return Integer.parseInt(fields[2]);
  }
  
  private int getYear() {
    return Integer.parseInt(fields[3]);
  }
  
  public LocalDate getDate() {
    return LocalDate.of(getYear(), getMonth(), getDay());
  }
  
  public int getCases() {
    return Integer.parseInt(fields[4]);
  }
  
  public int getDeaths() {
    return Integer.parseInt(fields[5]);
  }
  
  public String getCountry() {
    return fields[6].replace('_', ' ');
  }
  
  public String getCountryCode2() {
    return fields[7];
  }
  
  public String getCountryCode3() {
    return fields[8];
  }
  
  public String getPopulation() {
    return fields[9];
  }
  
  public String getContinent() {
    return fields[10];
  }
  
  public float getCumulativeCases() {
    return Float.parseFloat(fields[11]);
  }

  @Override
  public int compareTo(CovidRecord o) {
    if (getCountry().compareTo(o.getCountry()) == 0) {
      return getDate().compareTo(o.getDate());
    }
    return getCountry().compareTo(o.getCountry());
  }
  
  public long calculateAgeInDays() {
      return ChronoUnit.DAYS.between(getDate(), LocalDate.now());
  }
}
