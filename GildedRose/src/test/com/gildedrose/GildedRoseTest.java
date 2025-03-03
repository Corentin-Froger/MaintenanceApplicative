package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String AGED_BRIE = "Aged Brie";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo, -1, 0", app.items[0].toString());
    }

    /**
     * Tous les éléments ont une valeur sellIn qui désigne le nombre de jours restant pour vendre l'article.
     * Tous les articles ont une valeur quality qui dénote combien l'article est précieux.
     * <p>
     * À la fin de chaque journée, notre système diminue ces deux valeurs pour chaque produit.
     * <p>
     * Une fois que la date de péremption est passée, la qualité se dégrade deux fois plus rapidement.
     * La qualité d'un produit n'est jamais de plus de 50.
     * La qualité (quality) d'un produit ne peut jamais être négative.
     */
    @Test
    void sellInAvantApres() {
        Item[] items = new Item[]{new Item("foo", 1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality);
        app.updateQuality();
        assertEquals(17, app.items[0].quality);
    }

    @Test
    void qualityPasNegative() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityJamaisSuperieureA50() {
        Item[] items = new Item[]{new Item("foo", 0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(48, app.items[0].quality);
    }

    /**
     * "Aged Brie" augmente sa qualité (quality) plus le temps passe.
     * La qualité d'un produit n'est jamais de plus de 50.
     */
    @Test
    void qualityJamaisSuperieureA50AgedBrie() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void qualityAgedBrie() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    /**
     * "Sulfuras", étant un objet légendaire, n'a pas de date de péremption et ne perd jamais en qualité (quality)
     */
    @Test
    void qualitySulfuras() {
        Item[] items = new Item[]{new Item(SULFURAS, 0, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    /**
     * "Backstage passes", comme le "Aged Brie", augmente sa qualité (quality) plus le temps passe (sellIn);
     * La qualité augmente de 2 quand il reste 10 jours ou moins et de 3 quand il reste 5 jours ou moins,
     * mais la qualité tombe à 0 après le concert.
     */
    @Test
    void qualityBackstagePasses10Jours() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses6Jours() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 6, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses5Jours() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 5, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses1Jours() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses0Jours() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    /**
     * Les éléments "Conjured" voient leur qualité se dégrader de deux fois plus vite que les objets normaux.
     * Vous pouvez faire les changements que vous voulez à la méthode updateQuality et ajouter autant de code que
     * vous voulez, tant que tout fonctionne correctement. Cependant, nous devons vous prévenir, vous ne devez en aucun
     * cas modifier la classe Item ou ses propriétés, car cette classe appartient au gobelin à l'étage qui entrerait
     * dans une rage instantanée et vous tuerait sans délai : il ne croit pas au partage du code.
     * (Vous pouvez rendre la méthode updateQuality statique,
     * ainsi que des propriétés dans la classe Item si vous voulez, nous vous couvrirons)
     *
     * Un produit ne peut jamais voir sa qualité augmenter au-dessus de 50, cependant "Sulfuras" est un objet légendaire
     * et comme tel sa qualité est de 80 et elle ne change jamais.
     */
}
