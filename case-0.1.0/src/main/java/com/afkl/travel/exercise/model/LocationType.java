package com.afkl.travel.exercise.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets LocationType
 */
public enum LocationType {
  
  COUNTRY("country"),
  
  CITY("city"),
  
  AIRPORT("airport");

  private String value;

  LocationType(String value) {
    this.value = value;
  }
  
  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

 
  @JsonValue
  public static LocationType fromValue(String value) {
    for (LocationType b : LocationType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

