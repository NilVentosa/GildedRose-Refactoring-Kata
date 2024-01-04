package com.gildedrose.items;

import com.gildedrose.ItemWrapper;
import com.gildedrose.Item;

public class BackstagePasses extends ItemWrapper {

    public BackstagePasses(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        if (item.sellIn < 6) {
            increaseQualityBy(3);
        } else if (item.sellIn < 11) {
            increaseQualityBy(2);
        } else {
            increaseQuality();
        }

        decreaseSellIn();

        if (item.sellIn < 0) {
            item.quality = MIN_QUALITY;
        }
    }
}
