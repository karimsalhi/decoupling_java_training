package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Simulation {

    private final Logger logger = LoggerFactory.getLogger("Simulation");
    private final Player player;
    private long numberToGuess;

    public Simulation(Player player) {
        this.player=player;
    }

    public void initialize(long numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    /**
     * @return true if the player have guessed the right number
     */
    private boolean nextRound() {
        //System.out.println("Devinez le nombre ( done or not ): ");
        long guess = player.askNextGuess();
        if (guess == numberToGuess) {
            //System.out.println("won \n");
            //logger.log("won");
            return true;
        }
        if (guess > numberToGuess) {
            logger.log("Le nombre est plus petit\n");
            player.respond(false);
        }else{
            logger.log("Le nombre est plus grand \n");
            player.respond(true);
        }

        return false;
    }

    public void loopUntilPlayerSucceed(long maximumLoops) {
        boolean won = false;
        long start = System.currentTimeMillis();
        for (int i = 0; i < maximumLoops; i++) {
            if (nextRound()) {
                won = true;
                break;
            }
        }
        long eLapsedTime = System.currentTimeMillis() - start;
        if(won)
        {
            logger.log("You have won\n");
        }else{
            logger.log("Vous avez perdu\n");
        }
        //logger.log(won ? "Bravo, vous avez done !" : "C'est la fin, vous avez perdu");
        logger.log("Temps total : "  + new SimpleDateFormat("mm:ss:SSS").format(new Date(eLapsedTime)));
    }
}
