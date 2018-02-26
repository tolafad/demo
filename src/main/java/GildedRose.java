import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    private static final int MAX_QUALITY = 50;
    static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    static final String ITEM_5_DEXTERITY_VEST = "+5 Dexterity Vest";
    static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    static final String AGED_BRIE = "Aged Brie";
    static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Item> items = null;

        System.out.println("OMGHAI!");

        items = new ArrayList<Item>();
        items.add(new Item(ITEM_5_DEXTERITY_VEST, 10, 20));
        items.add(new Item(AGED_BRIE, 2, 0));
        items.add(new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7));
        items.add(new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80));
        items.add(new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
        items.add(new Item(CONJURED_MANA_CAKE, 3, 6));

        updateQuality(items);
    }

    public static void updateQuality(List<Item> items) {
        for (Item item : items) {
            updateAnItem(item);
        }
    }

    public static void updateAnItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        updateQualityWhenSellInHasEnded(item);
    }

    private static void updateQuality(Item item) {
        if (isAgedBrie(item)) {
            incrementQuality(item);            
        }
        else if (isBackStageToConcert(item)) {
            incrementQuality(item);

            if (item.getSellIn() < 11) {
                incrementQuality(item);
            }

            if (item.getSellIn() < 6) {
                incrementQuality(item);
            }

        } else {
            decrementQuality(item);
        }
    }

    private static boolean isBackStageToConcert(Item item) {
        return BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(item.getName());
    }

    private static boolean isAgedBrie(Item item) {
        return AGED_BRIE.equals(item.getName());
    }

    private static void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            item.setSellIn(item.getSellIn() - 1);
        }
    }

    private static boolean isSulfuras(Item item) {
        return SULFURAS_HAND_OF_RAGNAROS.equals(item.getName());
    }

    private static void updateQualityWhenSellInHasEnded(Item item) {
        if (hasSellInEnded(item)) {
            if (isAgedBrie(item))
                incrementQuality(item);
            else if (isBackStageToConcert(item)) {
                item.setQuality(0);
            } else {
                decrementQuality(item);
            }
        }
    }

    private static void incrementQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
        else
            item.setQuality(MAX_QUALITY);
    }

    private static void decrementQuality(Item item) {
        if (item.getQuality() > 0) {
            if (!isSulfuras(item)) {
                item.setQuality(item.getQuality() - 1);
            }
        }
    }

    private static boolean hasSellInEnded(Item item) {
        return item.getSellIn() < 0;
    }

}