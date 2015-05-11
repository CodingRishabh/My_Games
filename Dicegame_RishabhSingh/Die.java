import java.util.Random;
public class Die
{
    public static int roll()
    {                  
        int a = (int)(6*Math.random() +1 );
        return a;      
    }

    public static void main(String[] args)
    {
        for(int i = 1; i < 6; i++)

       {
           roll();           
           System.out.println(roll());
       }
    }

}
