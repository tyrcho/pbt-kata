package com.gildedrose;

class GildedRose {
    Item[] items;

    static Item nextDay(Item item) {
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app.items[0];
    }

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQuality(item);
            updateDate(item);
        }
    }

    private void updateQuality(Item item) {
        if (item.isBrie()) {
            improve(item, item.sellIn <= 0 ? 2 : 1);
        } else if (item.isPasses()) {
            improvePass(item);
        } else if (item.isConjured()) {
            deteriorate(item);
            deteriorate(item);
        } else if (!item.isSulfuras()) {
            deteriorate(item);
        }
    }

    private void updateDate(Item item) {
        if (!item.isSulfuras()) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void improvePass(Item item) {
        boolean mid = item.sellIn < 11;
        boolean late = item.sellIn < 6;
        boolean elapsed = item.sellIn <= 0;
        int count = elapsed ? -item.quality : late ? 3 : mid ? 2 : 1;
        improve(item, count);
    }

    private void deteriorate(Item item) {
        if (item.quality > 0) {
            improve(item, item.sellIn <= 0 ? -2 : -1);
        }
    }

    private void improve(Item item, int count) {
        item.quality = Math.max(0, Math.min(Math.max(50, item.quality), item.quality + count));
    }
}
