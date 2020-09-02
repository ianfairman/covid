package com.scratch.covidfx.domain;

import static com.scratch.covidfx.util.Strings.requireTrimmedNonBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ContinentName {
 
  private static final Map<String, ContinentName> LOOKUP = new HashMap<>();
  
  public static ContinentName lookupContinentName(String value) {
    return LOOKUP.computeIfAbsent(value, k -> new ContinentName(k));
  }
  private final String value;

  private ContinentName(String value) {
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
    final ContinentName other = (ContinentName) obj;
    return Objects.equals(this.value, other.value);
  }

  @Override
  public String toString() {
    return "ContinentName{" + "value=" + value + '}';
  }
}
