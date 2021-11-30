package hzt.aoc.day23;

import hzt.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day23Challenge extends Challenge {

    Day23Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201223-input-day23.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<Integer> integers = inputList.get(0).chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());

        return getMessage(calculateAnswer(integers));
    }

    protected abstract long calculateAnswer(List<Integer> integers);

    int makeMove(List<Integer> cups, int indexCurrent, int lowestCupLabel, int highestCupLabel) {
        int currentCupLabel = cups.get(indexCurrent);
        List<Integer> threePickedUpCups = listPickedUpCups(indexCurrent, cups);
        cups.removeAll(threePickedUpCups);
        int targetCupLabel = determineTargetCupLabel(currentCupLabel, lowestCupLabel, highestCupLabel, cups);
        int targetIndex = getIndexByLabel(targetCupLabel, cups);
        cups.addAll(targetIndex + 1, threePickedUpCups);
        indexCurrent = getIndexByLabel(currentCupLabel, cups);
        return indexCurrent + 1 < cups.size() ? indexCurrent + 1 : 0;
    }

    int determineTargetCupLabel(int currentCupLabel, int lowestCupLabel, int highestCupLabel, List<Integer> cups) {
        int targetCupLabel = currentCupLabel - 1;
        while (!cups.contains(targetCupLabel)) {
            targetCupLabel--;
            if (targetCupLabel < lowestCupLabel) {
                targetCupLabel = highestCupLabel;
            }
        }
        return targetCupLabel;
    }

    private static final int CUPS_PICKED_UP = 3;

    List<Integer> listPickedUpCups(int indexCurrent, List<Integer> cups) {
        List<Integer> pickedUpCups = new ArrayList<>();
        int indexNext = indexCurrent + 1;
        while (indexNext <= indexCurrent + CUPS_PICKED_UP) {
            pickedUpCups.add(cups.get(indexNext % cups.size()));
            indexNext++;
        }
        return pickedUpCups;
    }

    int getIndexByLabel(int label, List<Integer> cups) {
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
