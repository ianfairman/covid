package com.scratch.covidfx.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

public class Country {
  
  private static final Map<CountryName, Country> LOOKUP = new HashMap<>();
  
  public static Country lookupCountry(CountryName name, Continent continent, CountryCode code) {
    return LOOKUP.computeIfAbsent(name, k -> new Country(k, continent, code));
  }
  
  private final CountryName name;
  private final Continent continent;
  private final CountryCode code;
  
  private Country(CountryName name, Continent continent, CountryCode code) {
    this.name = requireNonNull(name);
    this.continent = requireNonNull(continent);
    this.code = requireNonNull(code);
  }

  public CountryName getName() {
    return name;
  }

  public Continent getContinent() {
    return continent;
  }

  public CountryCode getCode() {
    return code;
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
    final Country other = (Country) obj;
    return Objects.equals(this.name, other.name);
  }

  @Override
  public String toString() {
    return "Country{" + "name=" + name + ", continent=" + continent + ", code=" + code + '}';
  }
}

