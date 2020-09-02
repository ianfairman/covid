package com.scratch.covidfx.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

public class CountryCode {

  private final static Map<CountryCode3, CountryCode> LOOKUP = new HashMap<>();
  
  public static CountryCode lookupCountryCode(CountryCode2 code2, CountryCode3 code3) {
    return LOOKUP.computeIfAbsent(code3, k -> new CountryCode(code2, code3));
  }
  
  private final CountryCode2 code2;
  private final CountryCode3 code3;

  private CountryCode(CountryCode2 code2, CountryCode3 code3) {
    this.code2 = requireNonNull(code2);
    this.code3 = requireNonNull(code3);
  }

  public CountryCode2 getCode2() {
    return code2;
  }

  public CountryCode3 getCode3() {
    return code3;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.code3);
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
    final CountryCode other = (CountryCode) obj;
    return Objects.equals(this.code3, other.code3);
  }

  @Override
  public String toString() {
    return "CountryCode{" + "code2=" + code2 + ", code3=" + code3 + '}';
  }
}
