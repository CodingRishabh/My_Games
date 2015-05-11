import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ButtonListnener implements ActionListener 
    {
        Move m;
        int SIZE=8;
        public void actionPerformed (ActionEvent e)
        {
          
           JButton button = (JButton) e.getSource();
           int x=((JButton) e.getSource()).getX();
           int y=((JButton) e.getSource()).getY();
           int [][]list= { {1,1},
                           {100,97},
                           {199,193},
                           {298,289},
                           {397,385},
                           {496,481},
                           {595,577},
                           {694,673},
                        };
           for(int i=0; i<SIZE;i++)
           {
               for(int j=0; j<2;j++)
               {
                   if (x==list[i][j])
                   {
                       x=i;
                    }
                    if (y==list[i][j])
                   {
                       y=i;
                    }
                }
           }
           System.out.println(""+x+" "+y+" "+button.getLocation());
           m = new Move(y,x);
        }
        
        public Move getMove(){return m;} 
    }