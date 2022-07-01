package hzt.aoc.day23;

import hzt.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day23Challenge extends Challenge {
    private static final int CUPS_PICKED_UP = 3;

    Day23Challenge(final String challengeTitle, final String description) {
        super(challengeTitle, description, "20201223-input-day23.txt");
    }

    @Override
    protected String solve(final List<String> inputList) {
        final List<Integer> integers = inputList.get(0).chars()
                .mapToObj(Character::getNumericValue)
                .collect(Collectors.toList());

        return getMessage(calculateAnswer(integers));
    }

    protected abstract long calculateAnswer(List<Integer> integers);

    int makeMove(final List<Integer> cups, int indexCurrent, final int lowestCupLabel, final int highestCupLabel) {
        final int currentCupLabel = cups.get(indexCurrent);
        final List<Integer> threePickedUpCups = listPickedUpCups(indexCurrent, cups);
        cups.removeAll(threePickedUpCups);
        final int targetCupLabel = determineTargetCupLabel(currentCupLabel, lowestCupLabel, highestCupLabel, cups);
        final int targetIndex = getIndexByLabel(targetCupLabel, cups);
        cups.addAll(targetIndex + 1, threePickedUpCups);
        indexCurrent = getIndexByLabel(currentCupLabel, cups);
        return ((indexCurrent + 1) < cups.size()) ? (indexCurrent + 1) : 0;
    }

    int determineTargetCupLabel(final int currentCupLabel,
                                final int lowestCupLabel,
                                final int highestCupLabel,
                                final List<Integer> cups) {
        int targetCupLabel = currentCupLabel - 1;
        while (!cups.contains(targetCupLabel)) {
            targetCupLabel--;
            if (targetCupLabel < lowestCupLabel) {
                targetCupLabel = highestCupLabel;
            }
        }
        return targetCupLabel;
    }

    List<Integer> listPickedUpCups(final int indexCurrent,
                                   final List<Integer> cups) {
        final List<Integer> pickedUpCups = new ArrayList<>();
        int indexNext = indexCurrent + 1;
        while (indexNext <= indexCurrent + CUPS_PICKED_UP) {
            pickedUpCups.add(cups.get(indexNext % cups.size()));
            indexNext++;
        }
        return pickedUpCups;
    }

    int getIndexByLabel(final int label, final List<Integer> cups) {
        int index = 0;
        for (int i = 0; i < cups.size(); i++) {
            if (cups.get(i) == label) {
                index = i;
                break;
            }
        }
        return index;
    }

    abstract String getMessage(long value);
}
