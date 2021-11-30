package hzt.aoc.day25

class Part1ComboBreaker : Day25Challenge(
    "part 1",
    "What encryption key is the handshake trying to establish"
) {
    override fun solveByInput(cardPublicKey: Long, doorPublicKey: Long): Long {
        val loopSizeCard = findLoopSize(cardPublicKey)
        val loopSizeDoor = findLoopSize(doorPublicKey)
        val encryptionKeyDoor = calculateEncryptionKey(doorPublicKey, loopSizeCard)
        val encryptionKeyCard = calculateEncryptionKey(cardPublicKey, loopSizeDoor)
        LOGGER.trace("Card loop size: $loopSizeCard")
        LOGGER.trace("Door loop size: $loopSizeDoor")
        LOGGER.trace("Card encryption key: $encryptionKeyCard")
        LOGGER.trace("Door encryption key: $encryptionKeyDoor")
        return if (encryptionKeyCard == encryptionKeyDoor) encryptionKeyCard else 0
    }

    private fun calculateEncryptionKey(publicKey: Long, loopSizeOther: Long): Long {
        var value = STARTING_VALUE
        for (i in 0 until loopSizeOther) {
            value = performStep(value, publicKey)
        }
        return value
    }

    private fun performStep(value: Long, subjectNumber: Long): Long = (value * subjectNumber) % NUMBER_TO_DIVIDE_BY

    private fun findLoopSize(publicKey: Long): Long {
        var newVal = STARTING_VALUE
        var counter = 0
        do {
            newVal = performStep(newVal, INIT_SUBJECT_NUMBER.toLong())
            counter++
        } while (newVal != publicKey)
        return counter.toLong()
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }

    companion object {
        private const val STARTING_VALUE: Long = 1
    }
}
