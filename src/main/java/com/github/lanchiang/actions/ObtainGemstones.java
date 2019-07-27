package com.github.lanchiang.actions;

import com.github.lanchiang.components.Gemstone;
import com.github.lanchiang.components.GemstonePool;
import com.github.lanchiang.exceptions.ActionNotExecutableException;
import com.github.lanchiang.game.Game;
import com.github.lanchiang.game.Player;
import com.github.lanchiang.message.GemstoneCostMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

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

        List<GemstoneCostMessage> fetchCandidates = new LinkedList<>(fetchCandidates(gemstonePool, 10 - occupiedGemstoneNumber));

        // Todo: select and take a random fetch.
        Collections.shuffle(fetchCandidates);
        GemstoneCostMessage fetch = fetchCandidates.get(0);

        // add the gemstone chips to the player's hand
        player.obtainGemstones(fetch);
    }

    /**
     * @param gemstonePool
     * @param limit        the number of gemstones this player can still fetch.
     * @return
     */
    private Set<GemstoneCostMessage> fetchCandidates(GemstonePool gemstonePool, int limit) {
        Set<GemstoneCostMessage> fetchCandidates = fetchTwoSameColorCandidates(gemstonePool, limit);
        fetchCandidates.addAll(fetchThreeDifferentColorCandidates(gemstonePool, limit));
        return fetchCandidates;
    }

    private Set<GemstoneCostMessage> fetchTwoSameColorCandidates(GemstonePool gemstonePool, int limit) {
        Set<GemstoneCostMessage> fetchCandidates = new HashSet<>();
        limit = (limit > 2) ? 2 : limit;
        switch (limit) {
            case 0:
                break;
            case 1: {
                if (gemstonePool.getEmeralds() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(1, 0, 0, 0, 0));
                }
                if (gemstonePool.getDiamonds() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 1, 0, 0, 0));
                }
                if (gemstonePool.getSapphires() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 0, 1, 0, 0));
                }
                if (gemstonePool.getOnyxes() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 0, 0, 1, 0));
                }
                if (gemstonePool.getRubies() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 0, 0, 0, 1));
                }
                break;
            }
            case 2: {
                if (gemstonePool.getEmeralds() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(2, 0, 0, 0, 0));
                }
                if (gemstonePool.getDiamonds() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 2, 0, 0, 0));
                }
                if (gemstonePool.getSapphires() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 0, 2, 0, 0));
                }
                if (gemstonePool.getOnyxes() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 0, 0, 2, 0));
                }
                if (gemstonePool.getRubies() >= 4) {
                    fetchCandidates.add(new GemstoneCostMessage(0, 0, 0, 0, 2));
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Limit is not possible.");
        }
        return fetchCandidates;
    }

    private Set<GemstoneCostMessage> fetchThreeDifferentColorCandidates(GemstonePool gemstonePool, int limit) {
        Set<GemstoneCostMessage> fetchCandidates = new HashSet<>();
        limit = (limit > 3) ? 3 : limit;

        List<Gemstone> available = gemstonePool.getAvailableGemstones();
        Set<List<Gemstone>> imputation = imputation(available, 3);
        switch (limit) {
            case 0:
                break;
            case 1: {
                fetchCandidates.addAll(
                        imputation.stream().flatMap(gemstones -> imputation(gemstones, 1).stream()).map(GemstoneCostMessage::new).collect(Collectors.toSet())
                );
                break;
            }
            case 2: {
                fetchCandidates.addAll(
                        imputation.stream().flatMap(gemstones -> imputation(gemstones, 2).stream()).map(GemstoneCostMessage::new).collect(Collectors.toSet())
                );
                break;
            }
            case 3: {
                fetchCandidates.addAll(imputation.stream().map(GemstoneCostMessage::new).collect(Collectors.toSet()));
                break;
            }
            default:
                throw new IllegalArgumentException("Limit is not possible.");
        }
        return fetchCandidates;
    }

    private Set<List<Gemstone>> imputation(List<Gemstone> available, int limit) {
        return combination(available, limit);
    }

    private static Set<List<Gemstone>> combination(List<Gemstone> available, int size) {
        Set<List<Gemstone>> result = new HashSet<>();

        for (int i = 1; i < Math.pow(2, available.size()) - 1; i++) {
            List<Gemstone> eligibleCollections = new LinkedList<>();
            // 依次将数字 i 与 2^n 按位与，判断第 n 位是否为 1
            for (int j = 0; j < available.size(); j++) {
                if ((i & (int) Math.pow(2, j)) == Math.pow(2, j)) {
                    eligibleCollections.add(available.get(j));
                }
            }
            if (eligibleCollections.size() == size) {
                result.add(eligibleCollections);
            }
        }
        return result;
    }
}
