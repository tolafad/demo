import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GildedRoseTest {

    
    @Test
    public void testTheTruth() {
        assertTrue(true);
    }

    @Test
    public void testQualityDegradesByOneBeforeSellIn() {
        Item item = new Item(GildedRose.ITEM_5_DEXTERITY_VEST, 10, 20);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.ITEM_5_DEXTERITY_VEST, 9, 19, items.get(0));
    }

    @Test
    public void testQualityDegradesTwiceAsMuchAfterSellIn() {
        Item item = new Item(GildedRose.ITEM_5_DEXTERITY_VEST, 0, 20);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.ITEM_5_DEXTERITY_VEST, -1, 18, items.get(0));

    }

    @Test
    public void testQualityIsNeverNegative() {
        Item item = new Item(GildedRose.ITEM_5_DEXTERITY_VEST, 10, 0);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.ITEM_5_DEXTERITY_VEST, 9, 0, items.get(0));
    }

    @Test
    public void testAgedBrieIncreaseInQuality() {
        Item item = new Item(GildedRose.AGED_BRIE, 2, 0);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.AGED_BRIE, 1, 1, items.get(0));

    }

    @Test
    public void testQualityIsNeverMoreThan50() {
        Item item = new Item(GildedRose.AGED_BRIE, 5, 50);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.AGED_BRIE, 4, 50, items.get(0));

    }

    @Test
    public void testQualityIsNeverMoreThan50_WhenQualityIsMoreThan50() {
        Item item = new Item(GildedRose.AGED_BRIE, 5, 51);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.AGED_BRIE, 4, 50, items.get(0));

    }
    @Test
    public void testSulfurasNeverDecreasesInQuality() {
        Item item = new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 0, 80);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 0, 80, items.get(0));
    }

    @Test
    public void testBackstageIncreaseQualityBy2_When_10daysOrLess() {
        Item item = new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 10, 9);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 9, 11, items.get(0));
    }

    @Test
    public void testBackstageIncreaseQualityBy3_When_5daysOrLess() {
        Item item = new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 4, 5);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 3, 8, items.get(0));
    }
    
    @Test
    public void testQualityIs0_When_concertHasEnded() {
        Item item = new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 0, 5);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        GildedRose.updateQuality(items);
        assertItem(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, -1, 0, items.get(0));
    }
    
    @Test
    public void testConjuredItemsDecreaseInQualityTwiceAsFast() {
        fail();
    }
    
    private void assertItem(String name, int sellIn, int quality,Item item) {
        assertEquals(name, item.name);
        assertEquals("sellIn", sellIn, item.sellIn);
        assertEquals("quality", quality,item.quality);
    }
}
