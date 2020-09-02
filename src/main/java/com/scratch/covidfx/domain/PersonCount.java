package com.scratch.covidfx.domain;

public class PersonCount {
  
  public static PersonCount createPersonCount(String stringValue) {
    return new PersonCount(Integer.parseInt(stringValue));
  }
  
  private final int value;

  public PersonCount(int value) {
    this.value = value;
    if (value < 0) {
      throw new IllegalArgumentException("value must be zero or positive, was " + value);
    }
  }

  public int getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + this.value;
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
    final PersonCount other = (PersonCount) obj;
    return this.value == other.value;
  }

  @Override
  public String toString() {
    return "PersonCount{" + "value=" + value + '}';
  }
}
