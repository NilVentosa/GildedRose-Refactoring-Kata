package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(this::updateItem);
    }

    public void updateItem(Item item) {
        switch (item.name) {
            case "Aged Brie":
                updateBrie(item);
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                updateConcertTicket(item);
                break;
            case "Sulfuras, Hand of Ragnaros":
                break;
            case "Conjured Mana Cake":
                updateConjured(item);
                break;
            default:
                updateDefault(item);
        }
    }

    private void updateConjured(Item item) {
        decreaseQualityBy(item, 2);
        decreaseSellIn(item);

        if (item.sellIn < 0) {
            decreaseQualityBy(item, 2);
        }
    }

    private void updateBrie(Item item) {
        increaseQuality(item);
        decreaseSellIn(item);

        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private void updateConcertTicket(Item item) {
        if (item.sellIn < 6) {
            increaseQualityBy(item, 3);
        } else if (item.sellIn < 11) {
            increaseQualityBy(item, 2);
        } else {
            increaseQuality(item);
        }

        decreaseSellIn(item);

        if (item.sellIn < 0) {
            item.quality = MIN_QUALITY;
        }
    }

    private void updateDefault(Item item) {
        if (item.quality > 0) {
            decreaseQualityBy(item, 1);
        }

        decreaseSellIn(item);

        if (item.sellIn < 0 && item.quality > MIN_QUALITY) {
            decreaseQualityBy(item, 1);
        }
    }

    private void decreaseQualityBy(Item item, int amount) {
        item.quality -= amount;
        if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        }
    }

    private void increaseQuality(Item item) {
        increaseQualityBy(item, 1);
    }

    private void increaseQualityBy(Item item, int amount) {
        item.quality += amount;
        if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
