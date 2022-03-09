package fr.lernejo.guessgame;

import java.security.SecureRandom;

public class ComputerPlayer implements Player{
    private int lastGuess = -1;
    private int max;
    private int low;
    private boolean response;

    @Override
    public long askNextGuess() {
        if (lastGuess == -1) {
            max = 100;
            low = 0;
            lastGuess = (int) new SecureRandom().nextLong(low, max);
        }
        if (response){
            low = lastGuess;
            lastGuess = (int) new SecureRandom().nextLong(low, max);
        }
        else
        {
            max = lastGuess;
            lastGuess = (int) new SecureRandom().nextLong(low, max);
        }
        //lastGuess = response ? lastGuess * 2 : lastGuess / 2;
        return lastGuess;
    }

    /**
     * Called by {@link Simulation} to inform that the previous guess was lower or greater that the number to find.
     */
    @Override
    public void respond(boolean lowerOrGreater) {
        response = lowerOrGreater;
    }
}
