package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class GildedRoseGoldenMasterTest {

    @Test
    public void
    gildedRoseApprovalTest() {
        CombinationApprovals.verifyAllCombinations(
            this::updateItem,
            itemTypes(),
            generateQuality(),
            generateSellIn()
        );
    }

    private String[] itemTypes() {
        return new String[] {"common item", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"};
    }

    private Integer[] generateSellIn() {
        return IntStream.range(-1, 12).boxed().toArray(Integer[]::new);
    }

    private Integer[] generateQuality() {
        return IntStream.range(0, 51).boxed().toArray(Integer[]::new);
    }

    private Item updateItem(String name, Integer sellIn, Integer quality) {
        Item item = new Item(name, sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[] {item});
        gildedRose.updateQuality();
        return item;
    }
}
