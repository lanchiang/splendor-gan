package com.github.lanchiang.elements;

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

//abstract public class Gemstone {
//
//    public static Gemstone createGemstoneInstance(String name) {
//        switch (name) {
//            case "emerald":
//                return new Emerald();
//            case "diamond":
//                return new Diamond();
//            case "sapphire":
//                return new Sapphire();
//            case "onyx":
//                return new Onyx();
//            case "ruby":
//                return new Ruby();
//            default:
//                throw new IllegalArgumentException("Gemstone name is not correct in the resource file");
//        }
//    }
//}
//
//class Emerald extends Gemstone {}
//class Diamond extends Gemstone {}
//class Sapphire extends Gemstone {}
//class Onyx extends Gemstone {}
//class Ruby extends Gemstone {}
//class GoldJoker extends Gemstone {}
//
