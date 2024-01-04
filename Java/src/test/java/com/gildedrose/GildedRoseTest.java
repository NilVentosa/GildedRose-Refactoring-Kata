package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * * Una vez que ha pasado la fecha recomendada de venta, la `calidad` se degrada al doble de velocidad
 * * La `calidad` de un artículo nunca es negativa
 * * El "Queso Brie envejecido" (`Aged brie`) incrementa su `calidad` a medida que se pone viejo
 *   * Su `calidad` aumenta en `1` unidad cada día
 *   * luego de la `fecha de venta` su `calidad` aumenta `2` unidades por día
 * * La `calidad` de un artículo nunca es mayor a `50`
 * * El artículo "Sulfuras" (`Sulfuras`), siendo un artículo legendario, no modifica su `fecha de venta` ni se degrada en `calidad`
 * * Una "Entrada al Backstage", como el queso brie, incrementa su `calidad` a medida que la `fecha de venta` se aproxima
 *   * si faltan 10 días o menos para el concierto, la `calidad` se incrementa en `2` unidades
 *   * si faltan 5 días o menos, la `calidad` se incrementa en `3` unidades
 *   * luego de la `fecha de venta` la `calidad` cae a `0`
 *
 *   "common item", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"
 */

class GildedRoseTest {

    /**
     * common item
     */
    @Test
    void givenCommonItem_whenSellInIsPositive_thenUpdateReducesQualityByOne() {
        Item[] items = new Item[] { new Item("common item", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].item.quality);
        assertEquals(1, app.items[0].item.sellIn);
    }

    @Test
    void givenCommonItem_whenSellInIsZero_thenUpdateReducesQualityByTwo() {
        Item[] items = new Item[] { new Item("common item", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].item.quality);
        assertEquals(-1, app.items[0].item.sellIn);
    }

    @Test
    void givenCommonItem_whenQualityIsZero_thenUpdateDoesNotReduceQuality() {
        Item[] items = new Item[] { new Item("common item", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].item.quality);
        assertEquals(-1, app.items[0].item.sellIn);
    }

    @Test
    void givenCommonItem_whenSellInIsNegative_thenQualityReducesByTwo() {
        Item[] items = new Item[] { new Item("common item", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].item.quality);
        assertEquals(-2, app.items[0].item.sellIn);
    }

    /**
     * Aged Brie
     */
    @Test
    void givenAgedBrie_whenSellInIsPositive_thenUpdateIncreasesQualityByOne() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].item.quality);
        assertEquals(1, app.items[0].item.sellIn);
    }

    @Test
    void givenAgedBrie_whenSellInIsZero_thenUpdateIncreasesQualityByTwo() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].item.quality);
        assertEquals(-1, app.items[0].item.sellIn);
    }

    @Test
    void givenAgedBrie_whenQualityIsZero_thenUpdateIncreasesQualityByTwo() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].item.quality);
        assertEquals(-1, app.items[0].item.sellIn);
    }

    @Test
    void givenAgedBrie_whenQualityIsFifty_thenUpdateQualityDoesNotIncrease() {
        Item[] items = new Item[] { new Item("Aged Brie", 3, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].item.quality);
        assertEquals(2, app.items[0].item.sellIn);
    }

    /**
     * Backstage passes to a TAFKAL80ETC concert
     */
    @Test
    void givenBackstageTicket_whenSellInIsBiggerThanTen_thenUpdateIncreasesQualityByOne() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].item.quality);
        assertEquals(10, app.items[0].item.sellIn);
    }

    @Test
    void givenBackstageTicket_whenSellInIsTen_thenUpdateIncreasesQualityByTwo() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].item.quality);
        assertEquals(9, app.items[0].item.sellIn);
    }

    @Test
    void givenBackstageTicket_whenSellInIsFive_thenUpdateIncreasesQualityByThree() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].item.quality);
        assertEquals(4, app.items[0].item.sellIn);
    }

    @Test
    void givenBackstageTicket_whenSellInIsZero_thenQualityIsZero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].item.quality);
        assertEquals(-1, app.items[0].item.sellIn);
    }

    @Test
    void givenBackstageTicket_whenQualityIsFifty_thenQualityDoesNotIncrease() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].item.quality);
        assertEquals(3, app.items[0].item.sellIn);
    }

    /**
     * Sulfuras, Hand of Ragnaros
     */
    @Test
    void givenSulfuras_whenUpdate_nothingChanges() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 11, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].item.quality);
        assertEquals(11, app.items[0].item.sellIn);
    }



    /**
     * Conjured Mana Cake
     */
    @Test
    void givenConjured_whenSellInIsPositive_thenUpdateReducesQualityByTwo() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].item.quality);
        assertEquals(1, app.items[0].item.sellIn);
    }

    @Test
    void givenConjured_whenSellInIsZero_thenUpdateReducesQualityByFour() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].item.quality);
        assertEquals(-1, app.items[0].item.sellIn);
    }

    @Test
    void givenConjured_whenQualityIsZero_thenUpdateDoesNotReduceQuality() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].item.quality);
        assertEquals(-1, app.items[0].item.sellIn);
    }

    @Test
    void givenConjured_whenSellInIsNegative_thenQualityReducesByFour() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].item.quality);
        assertEquals(-2, app.items[0].item.sellIn);
    }

}
