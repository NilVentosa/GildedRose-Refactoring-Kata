package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.ItemWrapper;

public class ConjuredManaCake extends ItemWrapper {

    public ConjuredManaCake(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        decreaseQualityBy(2);
        decreaseSellIn();

        if (item.sellIn < 0) {
            decreaseQualityBy(2);
        }
    }
}
