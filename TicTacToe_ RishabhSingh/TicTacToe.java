import java.util.Scanner;
import java.util.*;

/**
 * TicTacToe Game
 * 
 * by (Rishabh Singh) 
 */
public class TicTacToe
{
    static Scanner input = new Scanner(System.in);
    public static int SIZE =3;
    public static final int ILLEGAL =0;
    public static final int ONGOING =1;
    public static final int TIE =2;
    public static final int X_WON =3;
    public static final int O_WON =4;
    private static char[][] grid;
    private char turn;
    
     
    
    /**
     * initialize the grid to empty spaces; initial turn is 'x'
     */
    public TicTacToe()
    
    {   
        turn = 'X';
        grid = new char[SIZE][SIZE];
        
        for (int row=0; row<SIZE; row++)
        {
            for (int col=0; col<SIZE; col++)
            {
                grid[row][col] = '_';
            }
        }
        
    }
    
    /**
     * return the current turn
     */
    public char getTurn()
    {
        return turn;
    }
    
    /**
     * change 'x' to 'o' or vice-versa
     */
    public void ToggleTurn(){
        if (turn == 'X'){
            turn = 'O';
        }
        else {
            turn = 'X';
        }
    }
    
    /**
     * check if the move is leagal or not
     */
    private boolean canPlay(Move m){
        
        if ((m.getRow() >= 0) && (m.getRow() < SIZE)) 
        {
            if ((m.getCol() >= 0) && (m.getCol() < SIZE))
            {
                if (grid[m.getRow()][m.getCol()] == '_')
                {
                    return true;
                }
            }
        }
        return false;
    }
    
   /**
    * update the grid with the supplied move, if it is legal.
    */
    public int play(Move m){

          if (canPlay(m))
          {
                   grid[m.getRow()][m.getCol()] = turn;
                   return ONGOING;
          }

        return ILLEGAL;
    }
    
    /**
     * Toggle between random moves and ordered moves.
     */
    public int machinePlay()
    {
    Scanner input = new Scanner(System.in);
    System.out.print("Type 0 for Random Mode \n Type 1 for Ordered mode: ");
    int mode = input.nextInt();
    
    if (mode == 0)
    {
     while (play(randomMove())== ILLEGAL )
     {
         play(randomMove());
     }
    }
     
    else if (mode == 1)
    {
       play(firstAvailableMove());
    }
    else 
    {
        System.out.println("ERROR: ONLY 0 AND 1 ARE VALID OPTIONS");
            return ILLEGAL;
    }
    
    return ONGOING;
    }
    
    /**
     * generate random moves 
     */
    private Move randomMove()
    {
        Random move= new Random();
        int first =move.nextInt(SIZE);
        int second =move.nextInt(SIZE);
        Move rand = new Move(first,second);
        return rand;
    }
    
    /**
     * this will generate first available spot
     */
      private Move firstAvailableMove()
      {
      Move k = new Move();
      for (int i =0; i<SIZE;i++)
      {
            for (int j =0;j<SIZE;j++)
            {
                k = new Move(i,j);
                if(canPlay(k))
                {
                 return k;
                }
            }

        }
        return k;
    }
    
   /**
    *  print the string representation of the game to the console
    */
    public void print(){
                System.out.print(toString());
            }   
       
   
  
    /**
     *  return a String represenation of the game (as per the example in the assignment)
     */
    public String toString()
    {
        String outline = "";
        outline += "************\n";
        for (int i =0; i<SIZE;i++)
        {
            outline += "| ";
            for (int j =0;j<SIZE;j++)
            {
                outline += grid[i][j]+" | ";
            }   
            outline += "\n";
        }
        outline += "************\n";
        return outline;
    }
    
    
    public boolean winOrLose()
    {
        for (int box = 0; box < SIZE; box++ )
        {
                
                if (check(grid[box][0],grid[box][1],grid[box][2]))
                {
                    System.out.println("WINNER");
                    return true;
                    
                }
                if (check(grid[0][box],grid[1][box],grid[2][box]))
                {
                    System.out.println("WINNER");
                    return true;
                }
                if (check(grid[0][0],grid[1][1],grid[2][2]))
                {
                    System.out.println("WINNER");
                    return true;
                }
                if (check(grid[0][2],grid[1][1],grid[2][0]))
                {
                    System.out.println("WINNER");
                    return true;
                }
    }
    System.out.println("Game IN PROGRESS");
    return false;
    }
    
    private boolean check(char box1, char box2, char box3)
    {
        return ((box1!='_')&&(box1==box2)&&(box2==box3));
    }
    
}
