package com.gildedrose;

class GildedRose {

    ItemWrapper[] items;

    public GildedRose(Item[] items) {
        this.items = new ItemWrapper[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = ItemWrapper.generate(items[i]);
        }
    }

    public void updateQuality() {
        for (ItemWrapper item: items) {
            item.update();
        }
    }
}
