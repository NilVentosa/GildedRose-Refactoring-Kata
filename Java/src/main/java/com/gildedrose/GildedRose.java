package com.gildedrose;

import java.util.Arrays;

class GildedRose {
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
        if (item.quality > 0) {
            item.quality-=2;
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality-=2;
            }
        }
    }

    private void updateBrie(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality++;
            }
        }
    }

    private void updateConcertTicket(Item item) {
        if (item.quality < 50) {
            item.quality++;

            if (item.sellIn < 11) {
                item.quality++;
            }

            if (item.sellIn < 6) {
                item.quality++;
            }
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void updateDefault(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality--;
            }
        }
    }
}
