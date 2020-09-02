package com.scratch.covidfx.domain;

import com.scratch.covidfx.util.Strings;
import static com.scratch.covidfx.util.Strings.requireTrimmedNonBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CountryCode2 {
 
  private static final Map<String, CountryCode2> LOOKUP = new HashMap<>();
  
  public static CountryCode2 lookupCountryCode2(String value) {
    return LOOKUP.computeIfAbsent(value, k -> new CountryCode2(k));
  }
  private final String value;

  private CountryCode2(String value) {
    this.value = Strings.requireLength(requireTrimmedNonBlank(value), 2);
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
    final CountryCode2 other = (CountryCode2) obj;
    return Objects.equals(this.value, other.value);
  }

  @Override
  public String toString() {
    return "CountryCode2{" + "value=" + value + '}';
  }
}
