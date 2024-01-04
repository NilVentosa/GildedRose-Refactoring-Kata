package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.ItemWrapper;

public class AgedBrie extends ItemWrapper {

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        increaseQuality();
        decreaseSellIn();

        if (item.sellIn < 0) {
            increaseQuality();
        }
    }
}
