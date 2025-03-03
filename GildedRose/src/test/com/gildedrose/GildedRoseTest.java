package com.gildedrose;

import com.gildedrose.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new NormalItem("foo", 0, 0)};
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
        Item[] items = new Item[]{new NormalItem("foo", 1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality);
        app.updateQuality();
        assertEquals(17, app.items[0].quality);
    }

    @Test
    void qualityPasNegative() {
        Item[] items = new Item[]{new NormalItem("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityJamaisSuperieureA50() {
        Item[] items = new Item[]{new NormalItem("foo", 0, 50)};
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
        Item[] items = new Item[]{new AgedBrie(0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void qualityAgedBrie() {
        Item[] items = new Item[]{new AgedBrie(0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    /**
     * "Sulfuras", étant un objet légendaire, n'a pas de date de péremption et ne perd jamais en qualité (quality)
     */
    @Test
    void qualitySulfuras() {
        Item[] items = new Item[]{new Sulfuras(0, 80)};
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
        Item[] items = new Item[]{new BackstagePasses(10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses6Jours() {
        Item[] items = new Item[]{new BackstagePasses(6, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses5Jours() {
        Item[] items = new Item[]{new BackstagePasses(5, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses1Jours() {
        Item[] items = new Item[]{new BackstagePasses(1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void qualityBackstagePasses0Jours() {
        Item[] items = new Item[]{new BackstagePasses(0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    /**
     * Les éléments "Conjured" voient leur qualité se dégrader deux fois plus vite que les objets normaux.
     */
    @Test
    void sellInAvantApresConjured() {
        Item[] items = new Item[]{new ConjuredItem("foo", 1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
        app.updateQuality();
        assertEquals(14, app.items[0].quality);
    }

    @Test
    void qualityPasNegativeConjured() {
        Item[] items = new Item[]{new ConjuredItem("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityJamaisSuperieureA50Conjured() {
        Item[] items = new Item[]{new ConjuredItem("foo", 0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(46, app.items[0].quality);
    }
}
