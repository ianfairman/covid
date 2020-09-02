package com.scratch.covidfx.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

public class Continent {
  
  private static final Map<ContinentName, Continent> LOOKUP = new HashMap<>();
  
  public static Continent lookupContinent(ContinentName name) {
    return LOOKUP.computeIfAbsent(name, k -> new Continent(k));
  }
  
  private final ContinentName name;

  private Continent(ContinentName name) {
    this.name = requireNonNull(name);
  }

  public ContinentName getName() {
    return name;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + Objects.hashCode(this.name);
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
    final Continent other = (Continent) obj;
    return Objects.equals(this.name, other.name);
  }

  @Override
  public String toString() {
    return "Continent{" + "name=" + name + '}';
  }
}
