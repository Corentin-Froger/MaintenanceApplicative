package com.gildedrose.items;

import static com.gildedrose.Const.SULFURAS;

public class Sulfuras extends Item {
    public Sulfuras(int sellIn, int quality) {
        super(SULFURAS, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        // Rien à faire
    }
}
