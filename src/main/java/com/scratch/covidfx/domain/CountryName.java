package com.scratch.covidfx.domain;

import static com.scratch.covidfx.util.Strings.requireTrimmedNonBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CountryName {
 
  private static final Map<String, CountryName> LOOKUP = new HashMap<>();
  
  public static CountryName lookupCountryName(String value) {
    return LOOKUP.computeIfAbsent(value, k -> new CountryName(k));
  }
  private final String value;

  private CountryName(String value) {
    this.value = requireTrimmedNonBlank(value);
  }

  public String getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + Objects.hashCode(this.value);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CountryName other = (CountryName) obj;
    return Objects.equals(this.value, other.value);
  }

  @Override
  public String toString() {
    return "CountryName{" + "value=" + value + '}';
  }
}
