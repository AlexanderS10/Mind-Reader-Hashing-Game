import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userScore=0;
        int compScore=0;
        String compGuess="t";
        String userCurrentGuess;
        String pastFourUserGuesses="";
        int roundNumber=0;
        System.out.println("** User Score 0, Computer Score 0 **");
        Hash myHash= new Hash();
        while(userScore!=20 && compScore!=20)
        {
            do
            {
                System.out.println("Type in your choice, h or t followed by enter");
                userCurrentGuess=sc.next();
            }while(!(userCurrentGuess.equals("t") || userCurrentGuess.equals("h")));

            if(userCurrentGuess.equals(compGuess))
            {
                compScore++;
                //Updated the hash based on the user Response
                if(pastFourUserGuesses.length()>3) {
                    if (compGuess.equals("t")) {
                        myHash.insert(myHash.hashingFunction(pastFourUserGuesses), 1);
                    } else {
                        myHash.insert(myHash.hashingFunction(pastFourUserGuesses), 0);
                    }
                }
                System.out.println("Comp guessed correct!");
            }
            else
            {
                userScore++;
                // Updated the Hash based on the user Response
                if(pastFourUserGuesses.length()>3) {
                    if (compGuess.equals("t")) {
                        myHash.insert(myHash.hashingFunction(pastFourUserGuesses), 1);
                    } else {
                        myHash.insert(myHash.hashingFunction(pastFourUserGuesses), 0);
                    }
                }
                System.out.println("Comp guessed wrong!");
            }

            System.out.println("** User Score:"+userScore+" Computer Score:"+compScore+ "**");


            //Updated past4UserGuesses
            pastFourUserGuesses+=userCurrentGuess;
            //TODO use as key to the Hash

            //If key hashes to value, that is your next guess (invoke searchKey)
            //if key does not hash to value Make a random guess between heads and tails

            int guess=myHash.searchKey(pastFourUserGuesses);
            if (guess==1)
            {
                compGuess="t";
            }
            else if (guess==0)
            {
                compGuess="h";
            }
            else
            {
                if(Math.random()>0.5)
                {
                    compGuess="h";
                }
                else
                {
                    compGuess="t";
                }
            }

        }
        //Adds a message outlining who won and lost
        System.out.println();
        if (compScore>userScore)
        {
            System.out.println("THE WINNER IS THE COMPUTER!!!");
            System.out.println("AS THE USER YOU GOT ["+userScore+"] POINTS WHILE THE COMPUTER GOT ["+compScore+"] POINTS");
        }
        else
        {
            System.out.println("YOU WIN!!!");
            System.out.println("YOOUR SCORE IS ["+userScore+"] WHILE THE COMPUTER'S SCORE IS ["+compScore+"]");
        }
    }
}