package hzt

import hzt.aoc.Challenge
import hzt.aoc.ChallengeDay
import hzt.aoc.day01.Part1ReportRepair
import hzt.aoc.day01.Part2ReportRepair
import hzt.aoc.day02.Part1PasswordPhilosophy
import hzt.aoc.day02.Part2PasswordPhilosophy
import hzt.aoc.day03.TreesEncounteredPart1
import hzt.aoc.day03.TreesEncounteredPart2
import hzt.aoc.day04.PassportProcessingPart1
import hzt.aoc.day04.PassportProcessingPart2
import hzt.aoc.day05.Part1BinaryBoarding
import hzt.aoc.day05.Part2BinaryBoarding
import hzt.aoc.day06.Part1CustomCustoms
import hzt.aoc.day06.Part2CustomCustoms
import hzt.aoc.day07.Part1HandyHaversacks
import hzt.aoc.day07.Part2HandyHaversacks
import hzt.aoc.day08.Part1HandheldHalting
import hzt.aoc.day08.Part2HandheldHalting
import hzt.aoc.day09.Part1EncodingError
import hzt.aoc.day09.Part2EncodingError
import hzt.aoc.day10.Part1AdaptorArray
import hzt.aoc.day10.Part2AdaptorArrayWithCaching
import hzt.aoc.day10.Part2AdaptorArrayWithCachingLongs
import hzt.aoc.day10.Part2AdaptorArrayWithoutCaching
import hzt.aoc.day11.Part1SeatingSystem
import hzt.aoc.day11.Part2SeatingSystem
import hzt.aoc.day12.Part1RainRisk
import hzt.aoc.day12.Part2RainRisk
import hzt.aoc.day13.Part1ShuttleSearch
import hzt.aoc.day13.Part2ShuttleSearch
import hzt.aoc.day13.Part2ShuttleSearchOwnImpl
import hzt.aoc.day14.Part1DockingData
import hzt.aoc.day14.Part2DockingData
import hzt.aoc.day15.Part1RambunctiousRecitation
import hzt.aoc.day15.Part2RambunctiousRecitation
import hzt.aoc.day16.Part1TicketTranslation
import hzt.aoc.day16.Part2TicketTranslation
import hzt.aoc.day17.Part1ConwayCubes
import hzt.aoc.day17.Part2ConwayCubes
import hzt.aoc.day18.Part1OperationOrder
import hzt.aoc.day18.Part2OperationOrder
import hzt.aoc.day19.Part1MonsterMessages
import hzt.aoc.day19.Part2MonsterMessages
import hzt.aoc.day20.Part1JurassicJigsaw
import hzt.aoc.day20.Part2JurassicJigsaw
import hzt.aoc.day21.Part1AllergenAssessment
import hzt.aoc.day21.Part2AllergenAssessment
import hzt.aoc.day22.Part1CrabCombat
import hzt.aoc.day22.Part2CrabCombat
import hzt.aoc.day23.Part1CrabCups
import hzt.aoc.day23.Part2CrabCups
import hzt.aoc.day24.Part1LobbyLayout
import hzt.aoc.day24.Part2LobbyLayout
import hzt.aoc.day25.Part1ComboBreaker
import org.apache.log4j.Level
import org.apache.log4j.LogManager
import java.time.LocalDate
import java.util.*
import kotlin.system.exitProcess

/**
 * This is a project to participate in the Advent of code 2020.
 * This event was held between 01-12-2020 and 25-12-2020
 *
 * @author Hans Zuidervaart,
 */
class Launcher : Runnable {
    private val challengeDays: MutableMap<Int, ChallengeDay> = HashMap()

    private fun dateOfDay(day: Int): LocalDate = LocalDate.of(2020, 12, day)

    private fun populateChallengeDaysMap(challengeDays: MutableMap<Int, ChallengeDay>) {
        var day = 0
        challengeDays[++day] = ChallengeDay(
            "Report Repair", BRIGHT_BLUE, dateOfDay(day),
            Part1ReportRepair(), Part2ReportRepair()
        )
        challengeDays[++day] = ChallengeDay(
            "Password Philosophy", GREEN, dateOfDay(day),
            Part1PasswordPhilosophy(), Part2PasswordPhilosophy()
        )
        challengeDays[++day] = ChallengeDay(
            "Toboggan Trajectory", CYAN, dateOfDay(day),
            TreesEncounteredPart1(), TreesEncounteredPart2()
        )
        challengeDays[++day] = ChallengeDay(
            "Passport Processing", YELLOW, dateOfDay(day),
            PassportProcessingPart1(), PassportProcessingPart2()
        )
        challengeDays[++day] = ChallengeDay(
            "Binary Boarding", RED, dateOfDay(day),
            Part1BinaryBoarding(), Part2BinaryBoarding()
        )
        challengeDays[++day] = ChallengeDay(
            "Custom Customs", BRIGHT_BLUE, dateOfDay(day),
            Part1CustomCustoms(), Part2CustomCustoms()
        )
        challengeDays[++day] = ChallengeDay(
            "Handy Haversacks", GREEN, dateOfDay(day),
            Part1HandyHaversacks(), Part2HandyHaversacks()
        )
        challengeDays[++day] = ChallengeDay(
            "Handheld Halting", CYAN, dateOfDay(day),
            Part1HandheldHalting(), Part2HandheldHalting()
        )
        challengeDays[++day] = ChallengeDay(
            "Encoding Error", YELLOW, dateOfDay(day),
            Part1EncodingError(), Part2EncodingError()
        )
        challengeDays[++day] = ChallengeDay(
            "Adaptor Array", RED, dateOfDay(day),
            Part1AdaptorArray(), Part2AdaptorArrayWithCaching(),
            Part2AdaptorArrayWithCachingLongs(), Part2AdaptorArrayWithoutCaching()
        )
        challengeDays[++day] = ChallengeDay(
            "Seating System", BRIGHT_BLUE, dateOfDay(day),
            Part1SeatingSystem(), Part2SeatingSystem()
        )
        challengeDays[++day] = ChallengeDay(
            "Rain Risk", GREEN, dateOfDay(day),
            Part1RainRisk(), Part2RainRisk()
        )
        challengeDays[++day] = ChallengeDay(
            "Shuttle Search", CYAN, dateOfDay(day),
            Part1ShuttleSearch(), Part2ShuttleSearchOwnImpl(), Part2ShuttleSearch()
        )
        challengeDays[++day] = ChallengeDay(
            "Docking Data", YELLOW, dateOfDay(day),
            Part1DockingData(), Part2DockingData()
        )
        challengeDays[++day] = ChallengeDay(
            "Rambunctious Recitation", RED, dateOfDay(day),
            Part1RambunctiousRecitation(), Part2RambunctiousRecitation()
        )
        challengeDays[++day] = ChallengeDay(
            "Ticket Translation", BRIGHT_BLUE, dateOfDay(day),
            Part1TicketTranslation(), Part2TicketTranslation()
        )
        challengeDays[++day] = ChallengeDay(
            "Conway Cubes", GREEN, dateOfDay(day),
            Part1ConwayCubes(), Part2ConwayCubes()
        )
        challengeDays[++day] = ChallengeDay(
            "Operation Order", CYAN, dateOfDay(day),
            Part1OperationOrder(), Part2OperationOrder()
        )
        challengeDays[++day] = ChallengeDay(
            "Monster Messages", YELLOW, dateOfDay(day),
            Part1MonsterMessages(), Part2MonsterMessages()
        )
        challengeDays[++day] = ChallengeDay(
            "Jurassic Jigsaw", RED, dateOfDay(day),
            Part1JurassicJigsaw(), Part2JurassicJigsaw()
        )
        challengeDays[++day] = ChallengeDay(
            "Allergen Assessment", BRIGHT_BLUE, dateOfDay(day),
            Part1AllergenAssessment(), Part2AllergenAssessment()
        )
        challengeDays[++day] = ChallengeDay(
            "Crab Combat", GREEN, dateOfDay(day),
            Part1CrabCombat(), Part2CrabCombat()
        )
        challengeDays[++day] = ChallengeDay(
            "Crab Cups", CYAN, dateOfDay(day),
            Part1CrabCups(), Part2CrabCups()
        )
        challengeDays[++day] = ChallengeDay(
            "Lobby Layout", YELLOW, dateOfDay(day),
            Part1LobbyLayout(), Part2LobbyLayout()
        )
        challengeDays[++day] = ChallengeDay(
            "Combo Breaker", RED, dateOfDay(day),
            Part1ComboBreaker()
        )
    }

    private fun start() = run()

    override fun run() {
        var userInput = ALL
        val startTime = System.nanoTime()
        LOGGER.info(String.format("%n%s", TITTLE))
        while (userInput != EXIT) {
            pressEnterToContinue()
            print(menuAsString())
            userInput = execute(Scanner(System.`in`).next())
        }
        val runtime = System.nanoTime() - startTime
        System.out.printf("%s%nRuntime: %2.3f seconds%n%s%n", GREEN, runtime / 1e9, DOTTED_LINE)
        exitProcess(0)
    }

    private fun executeAllAndPrintSummary() {
        challengeDays.values.forEach(ChallengeDay::solveChallenges)
        val totalSolveTime = challengeDays.values.asSequence()
            .map(ChallengeDay::solveTime)
            .sum()
        LOGGER.info(sortedSolveTimesAsString(ArrayList(challengeDays.values)))
        LOGGER.info(
            String.format(
                "%s%nTotal solve time: %.2f seconds%n%s%n",
                DOTTED_LINE, totalSolveTime / 1e9, DOTTED_LINE
            )
        )
    }

    private fun execute(input: String): String {
        if (input == EXIT) return EXIT else if (input == ALL) executeAllAndPrintSummary() else if (input == CLEAR) clearAnswers() else if (input == INFO) setChallengeLoggerToLevel(
            Level.INFO
        ) else if (input == TRACE) setChallengeLoggerToLevel(Level.TRACE) else if (input.matches(
                NUMBER_LENGTH_ONE_OR_MORE
            )
        ) executeByChallengeNumber(input) else println("You didn't enter a valid option...")
        return input
    }

    private fun clearAnswers() {
        challengeDays.values.forEach { day -> day.challengesAsSequence().forEach { it.clearAnswer() } }
        println("Answers cleared")
    }

    private fun setChallengeLoggerToLevel(level: Level) {
        Challenge.LOGGER.level = level
        clearAnswers()
        println("Challenge Logger level set to $level")
    }

    private fun executeByChallengeNumber(input: String) {
        val dayNr = input.toInt()
        if (challengeDays.containsKey(dayNr)) {
            challengeDays[dayNr]?.solveChallenges()
        } else println("The selected number is not in the challenge list...")
    }

    private fun menuAsString(): String {
        val sb = StringBuilder()
        sb.append(String.format("%n"))
        challengeDays.forEach { (dayNr, day) -> sb.append(menuOption(dayNr, day.title)) }
        sb.append(String.format("Enter '%s'  and press 'Enter' to execute all challenges at once.%n", ALL))
        sb.append(String.format("Enter '%s'  and press 'Enter' to clear answers.%n", CLEAR))
        sb.append(String.format("Enter '%s'  and press 'Enter' to set the logging level to 'INFO'.%n", INFO))
        sb.append(String.format("Enter '%s'  and press 'Enter' to set the logging level to 'TRACE'.%n", TRACE))
        sb.append(String.format("Enter '%s'  and press 'Enter' to exit the program.%n", EXIT))
        sb.append("Your input: ")
        return sb.toString()
    }

    private fun menuOption(dayNr: Int, title: String): String =
        String.format("Enter '%2d' and press 'Enter' to execute day %2d %s.%n", dayNr, dayNr, title)

    private fun sortedSolveTimesAsString(challengeDays: List<ChallengeDay>): String {
        val challenges = challengeDays.asSequence()
            .map { it.challengesAsSequence().map { c -> Pair(c, it) } }
            .flatMap { it.distinct() }
            .sortedBy { it.first.solveTime }
            .toList()
        val sb = StringBuilder()
        sb.append(RESET)
        sb.append(String.format("%nChallenges sorted by solve time:%n"))
        for (p in challenges) {
            sb.append(
                String.format(
                    "Day %2d Challenge: %-50s, answer: %-50s, solve time: %8.3f milliseconds%n",
                    p.second.dayOfMonth,
                    p.second.title + " " + p.first.part,
                    p.first.answer,
                    p.first.solveTime / 1e6
                )
            )
        }
        return sb.toString()
    }

    companion object {
        const val DOTTED_LINE = "___________________________________________________________________"
        private val LOGGER = LogManager.getLogger(Launcher::class.java)
        private const val RESET = "\u001B[0m"
        private const val RED = "\u001B[31m"
        private const val GREEN = "\u001B[32m"
        private const val YELLOW = "\u001B[33m"
        private const val CYAN = "\u001B[36m"
        private const val BRIGHT_BLUE = "\u001B[94m"
        private const val TITTLE = RED +
                "   __    ____  _  _  ____  _  _  ____    _____  ____     ___  _____  ____  ____    ___   ___  ___   ___  /\\        \n" +
                "  /__\\  (  _ \\( \\/ )( ___)( \\( )(_  _)  (  _  )( ___)   / __)(  _  )(  _ \\( ___)  (__ \\ / _ \\(__ \\ / _ \\ )(\n" +
                " /(__)\\  )(_) )\\  /  )__)  )  (   )(     )(_)(  )__)   ( (__  )(_)(  )(_) ))__)    / _/( (_) )/ _/( (_) )\\/      \n" +
                "(__)(__)(____/  \\/  (____)(_)\\_) (__)   (_____)(__)     \\___)(_____)(____/(____)  (____)\\___/(____)\\___/ ()    \n" +
                "__________________________________________________________________________________________________________          \n" +
                RESET

        @JvmStatic
        fun main(args: Array<String>) = Launcher().start()

        private val NUMBER_LENGTH_ONE_OR_MORE: Regex = Regex("\\d+")
        private const val EXIT = "e"
        private const val ALL = "a"
        private const val CLEAR = "c"
        private const val TRACE = "t"
        private const val INFO = "i"

        private fun pressEnterToContinue() {
            System.out.printf("%n%sPress Enter key to continue...%s", GREEN, RESET)
            Scanner(System.`in`).nextLine()
        }
    }

    init {
        populateChallengeDaysMap(challengeDays)
    }
}
