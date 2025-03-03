package com.gildedrose;

import static com.gildedrose.Const.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            handleItem(item);
        }
    }

    private void handleBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    private void handleSulfuras() {
        // Vide :(
    }

    private void handleBackstage(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn < 11) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void handleNormal(Item item) {
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality > 0) {
                if (!item.name.equals(SULFURAS)) {
                    item.quality = item.quality - 1;
                }
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(SULFURAS)) {
                            item.quality = item.quality - 1;
                        }
                    }
                }
            }
        }
    }

    private void handleItem(Item item) {
        switch (item.name) {
            case AGED_BRIE -> handleBrie(item);
            case SULFURAS -> handleSulfuras();
            case BACKSTAGE_PASSES -> handleBackstage(item);
            default -> handleNormal(item);
        }
    }
}
