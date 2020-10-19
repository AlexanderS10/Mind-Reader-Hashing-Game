public class Hash
{
    //User guess breakdown array
    userGuessBreakdown []  userGuesses ;

    public Hash()
    {
        //intialize userGuessBreakdown based on the array size for the game
        userGuesses= new userGuessBreakdown[16];
    }

    //Tells you what to guess next if key exists!
    public int searchKey(String key)
    {
        if(key.length()<4)// if the key size is less than 4 then -1 is returned which means a random
        {               //value is chosen
            return -1;
        }
        //Convert Key to index by using the hashing function below
        int index=hashingFunction(key);//it uses the key and turns it to a index
        //Return 0 or 1 if the key has a value associated with it (0 for guess heads, 1 for guess tails)
        if(userGuesses[index]!=null) {//if an object exists then it will search the value
            int guess = userGuesses[index].predictedUserGuess();//0 for head 1 for tails and -1 if they
            //are equal meaning no preference showed

 /*           if (guess != 1 || guess != 0) {
                guess = -1;
            }*/
            return guess;
        }
        else{//if no object exits then it return -1
            return -1;
        }
    }

    //puts a key like "1010" to a value like "1" (means thth hashes to t)
    public void insert(int key, int value)
    {
        if (key==-1)//depends of the hashing function meaning nothing will execute if
        {           //string key size is less than 4
            return;
        }
        //Use hashingFunction to determine the index
        //int index=hashingFunction(key);
        //If a userGuessBreakdown object exists-- update the value numHeads or numTails
        if (userGuesses[key]!=null)//if there is an object existing then it only updates it
        {
            if (value==1)//updates 1 for tails and 0 for heads
            {
                userGuesses[key].incrementTails();
            }
            else
            {
                userGuesses[key].incrementHeads();
            }
        }
        // //If a userGuessBreakdown object does not exist at this location-- create a userGuessBreakdown object,
        else//if there is no object then it creates one and updates the value
        {
            userGuesses[key]=new userGuessBreakdown();
            if (value==1)
            {
                userGuesses[key].incrementTails();
            }
            else
            {
                userGuesses[key].incrementHeads();
            }
        }
        //update the numHeads and numTails in this object based on value, and insert it into the array.
    }

    public int hashingFunction(String key)
    {
        if (key.length()<4)//code will not execute before string has a size 4
        {
            return -1;//once reached size four it will be changed to obtain an index
        }
        //converts the string htht to a binary value
        int [] binary=new int[4];//forms an array of length 4 which would be the one used to form the binary
        int j=0;                //to later be converted to a decimal
        for (int i=key.length()-4 ; i<key.length() ;i++)//takes the last 4 characters in the string
        {
            char ch=key.charAt(i);//changes then to 0 or 1 and stores it in the array of ints
            if (ch=='h')
            {
                binary[j]=0;
                j++;
            }
            else
            {
                binary[j]=1;
                j++;
            }
        }
        //Returns the index from the key by converting it to decimal (really converting the 4 digit binary number to decimal)
        int index=0;

        //It turns the four ints in the array to a decimal
        index=(int)((binary[0]*Math.pow(2,3))+(binary[1]*Math.pow(2,2))+(binary[2]*Math.pow(2,1))+(binary[3]*Math.pow(2,0)));
        return index;
    }
}