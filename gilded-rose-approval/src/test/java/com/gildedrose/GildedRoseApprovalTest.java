package com.gildedrose;


import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemNames.BRIE;
import static com.gildedrose.ItemNames.SULFURAS;
import static org.approvaltests.combinations.CombinationApprovals.verifyAllCombinations;
import static org.junit.jupiter.api.Assertions.assertEquals;


class GildedRoseApprovalTest {

    @Test
    void updateQuality_normal_shouldDecrease() throws Exception {
        Item sword = new Item("basic sword", 10, 8);
        Item swordNextDay = GildedRose.nextDay(sword);
        assertEquals(9, swordNextDay.sellIn);
        assertEquals(7, swordNextDay.quality);
    }

    @Test
    void approvalSwordShouldDeteriorate() throws Exception {
        Item sword = new Item("basic sword", 10, 8);
        Approvals.verify(GildedRose.nextDay(sword));
    }

    @Test
    void approvalSulfurasShouldNeverChange() throws Exception {
        Integer[] sellInDays = {-5, 0, 20};
        Integer[] qualities = {-1, 0, 10, 50};

        verifyAllCombinations((sellIn, quality) ->
                GildedRose.nextDay(new Item(SULFURAS, sellIn, quality)), sellInDays, qualities);
    }

    @Test
    void approvalBrieShouldImprove() throws Exception {
        Integer[] sellInDays = {-5, 0, 1, 20};
        Integer[] qualities = {0, 10};

        verifyAllCombinations((sellIn, quality) ->
                GildedRose.nextDay(new Item(BRIE, sellIn, quality)), sellInDays, qualities);
    }
}
