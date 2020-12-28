package hzt.aoc.day25;

public class Part1ComboBreaker extends Day25Challenge {

    public Part1ComboBreaker() {
        super("part 1",
                "What encryption key is the handshake trying to establish?");
    }

    private static final long STARTING_VALUE = 1;

    @Override
    protected long solveByInput(long cardPublicKey, long doorPublicKey) {
        long loopSizeCard = findLoopSize(cardPublicKey);
        long loopSizeDoor = findLoopSize(doorPublicKey);
        long encryptionKeyDoor = calculateEncryptionKey(doorPublicKey, loopSizeCard);
        long encryptionKeyCard = calculateEncryptionKey(cardPublicKey, loopSizeDoor);
        LOGGER.trace("Card loop size: " + loopSizeCard);
        LOGGER.trace("Door loop size: " + loopSizeDoor);
        LOGGER.trace("Card encryption key: " + encryptionKeyCard);
        LOGGER.trace("Door encryption key: " + encryptionKeyDoor);
        return encryptionKeyCard == encryptionKeyDoor ? encryptionKeyCard : 0;
    }

    private long calculateEncryptionKey(long publicKey, long loopSizeOther) {
        long value = STARTING_VALUE;
        for (int i = 0; i < loopSizeOther; i++) {
            value = performStep(value, publicKey);
        }
        return value;
    }

    private long performStep(long value, long subjectNumber) {
        value = value * subjectNumber;
        value = value % NUMBER_TO_DIVIDE_BY;
        return value;
    }

    private long findLoopSize(long publicKey) {
        long newVal = STARTING_VALUE;
        int counter = 0;
        do {
            newVal = performStep(newVal, INIT_SUBJECT_NUMBER);
            counter++;
        } while (newVal != publicKey);
        return counter;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}
