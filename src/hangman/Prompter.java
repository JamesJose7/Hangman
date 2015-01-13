package hangman;

import java.io.Console;
import java.util.Scanner;

public class Prompter {
	Scanner scanner = new Scanner(System.in);
	
	private Game mGame;
	  
	  public Prompter(Game game) {
	    mGame = game;
	  }
	  
	  public void play() {
	    while (mGame.getRemainingTries() > 0 && !mGame.isSolved()) {
	      displayProgress();
	      promptForGuess();
	    }
	    if (mGame.isSolved()) {
	      System.out.printf("Congratulations you won with %d tries remaining.\n",
	                        mGame.getRemainingTries());
	    } else {
	      System.out.printf("Bummer the word was %s. :(\n",
	                        mGame.getAnswer());
	    }
	  }
	  
	  public boolean promptForGuess() {
	    Console console = System.console();
	    boolean isHit = false;
	    boolean isValidGuess = false;
	    while (! isValidGuess) {
	    	System.out.print("Enter a letter: ");
    	  String guessAsString =  scanner.next();
    			  //console.readLine("Enter a letter: ");
	     
	      try {
	        
	        isHit = mGame.applyGuess(guessAsString);
	        isValidGuess = true;
	      } catch(IllegalArgumentException iae) {
	        console.printf("%s. Please try again.\n", iae.getMessage());
	      }
	    }
	    return isHit;
	  }
	  
	  public void displayProgress() {
	    System.out.printf("You have %d tries left to to solve: %s\n",
	                      mGame.getRemainingTries(),
	                      mGame.getCurrentProgress());
	  }
}
