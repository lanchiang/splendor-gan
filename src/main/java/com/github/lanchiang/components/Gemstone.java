package com.github.lanchiang.components;

/**
 * A resource is a gemstone that is fetched by a player and used as the currency to by the cards.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
public enum Gemstone {
    Emerald,
    Diamond,
    Sapphire,
    Onyx,
    Ruby,
    GoldJoker;

    public static Gemstone getGemstone(String name) {
        switch (name) {
            case "emerald":
                return Emerald;
            case "diamond":
                return Diamond;
            case "sapphire":
                return Sapphire;
            case "onyx":
                return Onyx;
            case "ruby":
                return Ruby;
            default:
                throw new IllegalArgumentException("Gemstone name is not correct in the resource file");
        }
    }
}
