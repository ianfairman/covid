package com.scratch.covidfx.util;

import static java.util.Objects.requireNonNull;

public class Strings {

  public static String requireTrimmedNonBlank(String value) {
    String normalizedValue = requireNonNull(value).trim();
    if (normalizedValue.isEmpty()) {
      throw new IllegalArgumentException("Value cannot be blank");
    }
    return normalizedValue;
  }
  
  public static String requireLength(String value, int length) {
    if (value.length() != length) {
      throw new IllegalArgumentException("Value " + value +  " was not of length " + length);
    }
    return value;
  }
}
