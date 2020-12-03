package com.sogeti.codingchallenge;

import com.sogeti.codingchallenge.day1.ThreeDigitsSumTo2020;
import com.sogeti.codingchallenge.day1.TwoDigitsSumTo2020;
import com.sogeti.codingchallenge.day2.PasswordValidChallenge1;
import com.sogeti.codingchallenge.day2.PasswordValidChallenge2;
import com.sogeti.codingchallenge.day3.TreesEncounteredPart1;
import com.sogeti.codingchallenge.day3.TreesEncounteredPart2;
import com.sogeti.codingchallenge.view.MainPanelLauncher;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;

public class Launcher {

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    private static final String TITTLE = " _______  ______            _______  _       _________   _______  _______    _______  _______  ______   _______    _______  _______  _______  _______  _ \n" +
            "(  ___  )(  __  \\ |\\     /|(  ____ \\( (    /|\\__   __/  (  ___  )(  ____ \\  (  ____ \\(  ___  )(  __  \\ (  ____ \\  / ___   )(  __   )/ ___   )(  __   )( )\n" +
            "| (   ) || (  \\  )| )   ( || (    \\/|  \\  ( |   ) (     | (   ) || (    \\/  | (    \\/| (   ) || (  \\  )| (    \\/  \\/   )  || (  )  |\\/   )  || (  )  || |\n" +
            "| (___) || |   ) || |   | || (__    |   \\ | |   | |     | |   | || (__      | |      | |   | || |   ) || (__          /   )| | /   |    /   )| | /   || |\n" +
            "|  ___  || |   | |( (   ) )|  __)   | (\\ \\) |   | |     | |   | ||  __)     | |      | |   | || |   | ||  __)       _/   / | (/ /) |  _/   / | (/ /) || |\n" +
            "| (   ) || |   ) | \\ \\_/ / | (      | | \\   |   | |     | |   | || (        | |      | |   | || |   ) || (         /   _/  |   / | | /   _/  |   / | |(_)\n" +
            "| )   ( || (__/  )  \\   /  | (____/\\| )  \\  |   | |     | (___) || )        | (____/\\| (___) || (__/  )| (____/\\  (   (__/\\|  (__) |(   (__/\\|  (__) | _ \n" +
            "|/     \\|(______/    \\_/   (_______/|/    )_)   )_(     (_______)|/         (_______/(_______)(______/ (_______/  \\_______/(_______)\\_______/(_______)(_)\n" +
            "                                                                                                                                                         ";

    enum Challenge {

        SUM_TO_2020(new ChallengeDay("Sum to 2020", LocalDate.of(2020, 12, 1),
                new TwoDigitsSumTo2020(),
                new ThreeDigitsSumTo2020())),

        ARE_PASSWORDS_VALID(new ChallengeDay("Are the passwords valid?", LocalDate.of(2020, 12, 2),
                new PasswordValidChallenge1(),
                new PasswordValidChallenge2())),

        DAY_3(new ChallengeDay("Toboggan Trajectory", LocalDate.of(2020, 12, 3),
                new TreesEncounteredPart1(),
                new TreesEncounteredPart2()));

        private final ChallengeDay challengeDay;

        Challenge(ChallengeDay challengeDay) {
            this.challengeDay = challengeDay;
        }
    }

    public static void main(String[] args) {
        LOGGER.info(TITTLE);
        Arrays.stream(Challenge.values()).forEach(item -> item.challengeDay.solveChallenges());
        new MainPanelLauncher().demo();
    }


}
