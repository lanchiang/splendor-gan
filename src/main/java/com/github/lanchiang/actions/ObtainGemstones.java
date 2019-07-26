package com.github.lanchiang.actions;

import com.github.lanchiang.components.Gemstone;
import com.github.lanchiang.components.GemstonePool;
import com.github.lanchiang.exceptions.ActionNotExecutableException;
import com.github.lanchiang.game.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class ObtainGemstones extends PlayerAction {

    public ObtainGemstones(Player player) {
        super(player);
    }

    @Override
    public void execute() throws ActionNotExecutableException {
        GemstonePool gemstonePool = player.getGame().getGemstonePool();

        int occupiedGemstoneNumber = player.getOccupiedGemstones().values().stream().mapToInt(value -> value).sum();

        List<Fetch> fetchCandidates = fetchCandidates(gemstonePool, 10 - occupiedGemstoneNumber);

        // Todo: select and take a random fetch.
        Collections.shuffle(fetchCandidates);
        Fetch fetch = fetchCandidates.get(0);

        // add the gemstone chips to the player's hand
        player.obtainGemstones(fetch);
    }

    /**
     *
     * @param gemstonePool
     * @param limit the number of gemstones this player can still fetch.
     * @return
     */
    private Set<Fetch> fetchCandidates(GemstonePool gemstonePool, int limit) {
        Set<Fetch> fetchCandidates = new HashSet<>(fetchTwoSameColorCandidates(gemstonePool, limit));
        fetchCandidates.addAll(fetchThreeDifferentColorCandidates(gemstonePool, limit));
        return fetchCandidates;
    }

    private List<Fetch> fetchTwoSameColorCandidates(GemstonePool gemstonePool, int limit) {
        List<Fetch> fetchCandidates = new LinkedList<>();
        switch (limit) {
            case 0: break;
            case 1: {
                if (gemstonePool.getEmeralds() >= 4) {
                    fetchCandidates.add(new Fetch(1, 0, 0, 0, 0));
                }
                if (gemstonePool.getDiamonds() >= 4) {
                    fetchCandidates.add(new Fetch(0, 1, 0, 0, 0));
                }
                if (gemstonePool.getSapphires() >= 4) {
                    fetchCandidates.add(new Fetch(0, 0, 1, 0, 0));
                }
                if (gemstonePool.getOnyxes() >= 4) {
                    fetchCandidates.add(new Fetch(0, 0, 0, 1, 0));
                }
                if (gemstonePool.getRubies() >= 4) {
                    fetchCandidates.add(new Fetch(0, 0, 0, 0, 1));
                }
                break;
            }
            case 2: {
                if (gemstonePool.getEmeralds() >= 4) {
                    fetchCandidates.add(new Fetch(2, 0, 0, 0, 0));
                }
                if (gemstonePool.getDiamonds() >= 4) {
                    fetchCandidates.add(new Fetch(0, 2, 0, 0, 0));
                }
                if (gemstonePool.getSapphires() >= 4) {
                    fetchCandidates.add(new Fetch(0, 0, 2, 0, 0));
                }
                if (gemstonePool.getOnyxes() >= 4) {
                    fetchCandidates.add(new Fetch(0, 0, 0, 2, 0));
                }
                if (gemstonePool.getRubies() >= 4) {
                    fetchCandidates.add(new Fetch(0, 0, 0, 0, 2));
                }
                break;
            }
            default: throw new IllegalArgumentException("Limit is not possible.");
        }
        return fetchCandidates;
    }

    private List<Fetch> fetchThreeDifferentColorCandidates(GemstonePool gemstonePool, int limit) {

    }

    public class Fetch {

        private final int emeralds;

        private final int diamonds;

        private final int sapphires;

        private final int onyxes;

        private final int rubies;

        public Fetch(int emeralds, int diamonds, int sapphires, int onyxes, int rubies) {
            this.emeralds = emeralds;
            this.diamonds = diamonds;
            this.sapphires = sapphires;
            this.onyxes = onyxes;
            this.rubies = rubies;
        }

        public int getCost(Gemstone gemstone) {
            switch (gemstone) {
                case Onyx: return onyxes;
                case Ruby: return rubies;
                case Diamond: return diamonds;
                case Emerald: return emeralds;
                case Sapphire: return sapphires;
                default: throw new IllegalArgumentException();
            }
        }
    }
}
