package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.ItemWrapper;

public class DefaultItem extends ItemWrapper {

    public DefaultItem(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        if (item.quality > 0) {
            decreaseQualityBy(1);
        }

        decreaseSellIn();

        if (item.sellIn < 0 && item.quality > MIN_QUALITY) {
            decreaseQualityBy(1);
        }
    }
}
