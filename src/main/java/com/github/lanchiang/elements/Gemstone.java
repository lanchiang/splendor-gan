package com.github.lanchiang.elements;

/**
 * A resource is a gemstone that is fetched by a player and used as the currency to by the cards.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
abstract public class Gemstone {

    public static Gemstone createGemstoneInstance(String name) {
        if (name.equals("emerald")) {
            return new Emerald();
        } else if (name.equals("diamond")) {
            return new Diamond();
        } else if (name.equals("sapphire")) {
            return new Sapphire();
        } else if (name.equals("onyx")) {
            return new Onyx();
        } else if (name.equals("ruby")) {
            return new Ruby();
        } else {
            throw new IllegalArgumentException("Gemstone name is not correct in the resource file");
        }
    }
}

class Emerald extends Gemstone {}
class Diamond extends Gemstone {}
class Sapphire extends Gemstone {}
class Onyx extends Gemstone {}
class Ruby extends Gemstone {}
class GoldJoker extends Gemstone {}