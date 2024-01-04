package com.gildedrose;

import com.gildedrose.items.AgedBrie;
import com.gildedrose.items.BackstagePasses;
import com.gildedrose.items.ConjuredManaCake;
import com.gildedrose.items.DefaultItem;
import com.gildedrose.items.SulfurasHand;

public abstract class ItemWrapper {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;

    protected Item item;
    public abstract void update();

    public static ItemWrapper generate(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return new AgedBrie(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePasses(item);
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurasHand(item);
            case "Conjured Mana Cake":
                return new ConjuredManaCake(item);
            default:
                return new DefaultItem(item);
        }
    }

    protected void decreaseQualityBy(int amount) {
        item.quality -= amount;
        if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        }
    }

    protected void increaseQuality() {
        increaseQualityBy(1);
    }

    protected void increaseQualityBy(int amount) {
        item.quality += amount;
        if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

    protected void decreaseSellIn() {
        item.sellIn--;
    }
}
