package hzt.aoc.day25;

public class Part1ComboBreaker extends Day25Challenge {

    private static final long STARTING_VALUE = 1;

    public Part1ComboBreaker() {
        super("part 1",
                "What encryption key is the handshake trying to establish?");
    }

    @Override
    protected long solveByInput(final long cardPublicKey, final long doorPublicKey) {
        final long loopSizeCard = findLoopSize(cardPublicKey);
        final long loopSizeDoor = findLoopSize(doorPublicKey);
        final long encryptionKeyDoor = calculateEncryptionKey(doorPublicKey, loopSizeCard);
        final long encryptionKeyCard = calculateEncryptionKey(cardPublicKey, loopSizeDoor);
        LOGGER.trace("Card loop size: " + loopSizeCard);
        LOGGER.trace("Door loop size: " + loopSizeDoor);
        LOGGER.trace("Card encryption key: " + encryptionKeyCard);
        LOGGER.trace("Door encryption key: " + encryptionKeyDoor);
        return encryptionKeyCard == encryptionKeyDoor ? encryptionKeyCard : 0;
    }

    private long calculateEncryptionKey(final long publicKey, final long loopSizeOther) {
        long value = STARTING_VALUE;
        for (int i = 0; i < loopSizeOther; i++) {
            value = performStep(value, publicKey);
        }
        return value;
    }

    private long performStep(long value, final long subjectNumber) {
        value = value * subjectNumber;
        value = value % NUMBER_TO_DIVIDE_BY;
        return value;
    }

    private long findLoopSize(final long publicKey) {
        long newVal = STARTING_VALUE;
        int counter = 0;
        do {
            newVal = performStep(newVal, INIT_SUBJECT_NUMBER);
            counter++;
        } while (newVal != publicKey);
        return counter;
    }

    @Override
    String getMessage(final long global) {
        return String.format("%d", global);
    }

}
