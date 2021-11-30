package hzt.aoc.day06.model

class Group(private val answers: List<Char>, private val personsInGroupAnswers: List<List<Char>>) {
    fun amountAnyoneAnsweredYes(): Int {
        val answeredYes: Set<Char> = HashSet(answers)
        return answeredYes.size
    }

    val givenAnswerSet: Set<Char>
        get() = HashSet(answers)

    fun amountEveryoneAnsweredYes(): Int {
        var amountAnswersEveryoneAnsweredYes = 0
        for (answer in givenAnswerSet) {
            var everyOneAnsweredYes = true
            for (personAnswers in personsInGroupAnswers) {
                if (!personAnswers.contains(answer)) {
                    everyOneAnsweredYes = false
                    break
                }
            }
            if (everyOneAnsweredYes) amountAnswersEveryoneAnsweredYes++
        }
        return amountAnswersEveryoneAnsweredYes
    }

    override fun toString(): String {
        return "Group{" +
                "answers=" + answers +
                ", personsInGroupAnswers=" + personsInGroupAnswers +
                '}'
    }
}
