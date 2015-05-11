import java.util.*;

public class DiceGame
{
   static Scanner keyz = new Scanner (System.in);
   public static void main(String[] args)
   {
        System.out.println("DO YOU WANT TO PLAY A DICE GAME? Type 'yes' to continue");
        String line = keyz.nextLine();
        
        if(line.equals("yes"))
        {
            System.out.println("Lets play the game.");
        }
        else
        {
           System.out.println("EXITING THE GAME");
           System.exit(0);
        }
        int player = 50;
        System.out.println("Your initial score is 50");
        System.out.println("To quit during the game, type in a negative number");
        
     for(int i = 1; i <= 999; i++)
     {
        System.out.println("Enter Biding amount in dolars to roll the dice and bid money");
        
        int bid = keyz.nextInt();
        
        if(bid <= 0)
        {
            System.out.println("Exiting the game........");
            System.exit(0);
        }
        
        System.out.println("Type 'roll' to roll the dice");
        String a = keyz.nextLine();
        int roll1 = Die.roll();
        int roll2 = Die2.roll2();

        System.out.println("YOU ROLLED: " + roll1);
        System.out.println("COMPUTER ROLLED: " + roll2);

        if(roll1 < roll2)
        {
         player = player - bid  ; 
        }
        else if(roll1 == roll2)
        {
            player = player;
          
        }
        else
        {
         player = player + bid;
        }
        
        System.out.println("YOUR SCORE: " + player);
        
        
        if(player <= 0)
        {
            System.out.println("You lost");
            System.exit(0);
        } 
    }
   }
   
}

