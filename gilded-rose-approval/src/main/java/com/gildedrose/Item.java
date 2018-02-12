package com.gildedrose;

import static com.gildedrose.ItemNames.*;

public class Item {

  public String name;

  public int sellIn;

  public int quality;

  public Item(String name, int sellIn, int quality) {
    this.name = name;
    this.sellIn = sellIn;
    this.quality = quality;
  }

  @Override
  public String toString() {
    return this.name + ", " + this.sellIn + ", " + this.quality;
  }

  boolean isSulfuras() {
    return name.equals(SULFURAS);
  }

  boolean isConjured() {
    return name.startsWith(CONJURED);
  }

  boolean isBrie() {
    return name.equals(BRIE);
  }

  boolean isPasses() {
    return name.equals(PASS);
  }

}
