import java.util.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class GraphicalOthello extends Othello implements ActionListener {
    private JFrame gameFrame;
    private JButton[][] buttons;
    private JPanel gridPanel;
    Move move;
    private Othello othello;
    private boolean empty;
    private int row,col;
    private static int CAN_GO = 9; 
    private static int CANT_GO = 10; 
    /**
     * Constructor for objects of class GraphicalOthello
     */
    public GraphicalOthello()
    {
        othello = new Othello();
        gameFrame = new JFrame("Othello");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1000, 1000);
        gameFrame.setResizable(false);
        gridPanel = new JPanel();
        buttons = new JButton[SIZE][SIZE];
        
        
        
        gridPanel.setLayout(new GridLayout(othello.SIZE,othello.SIZE));
        for(int i=0; i<othello.SIZE;i++)
        {
            for(int j=0; j<othello.SIZE;j++)
            {
                buttons[i][j]= new JButton(""+othello.grid[i][j]);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setActionCommand("" + i + j);
                gridPanel.add(buttons[i][j]);
            }
            
        }
        
        JPanel gridPanel1 = new JPanel();
        gridPanel1.setLayout(new GridLayout());
        
        JButton rButton = new JButton("Random");
        rButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "Computer will play random move"); 
                RandomMove rm = new RandomMove();
                rm.selectMove(othello);
            }
        });
        JButton fButton = new JButton("First Available");
        fButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "Computer will play random move") ;
                FirstAvailableMove  fm = new FirstAvailableMove();
                fm.selectMove(othello);
            }
        });
        JButton gButton = new JButton("Greedy");
        gButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "Computer will play greedy move"); 
                GreedyMove gm = new GreedyMove();
                gm.selectMove(othello);
            }
        });
        
        
        gridPanel1.add(rButton);
        gridPanel1.add(fButton);
        gridPanel1.add(gButton);
        
        
        
        gameFrame.getContentPane().setLayout(new BorderLayout());
        gameFrame.getContentPane().add (gridPanel,BorderLayout.NORTH);
        gameFrame.getContentPane().add (gridPanel1,BorderLayout.SOUTH);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
    
    public void print()
    {
        othello.print();
        for(int i=0; i<othello.SIZE;i++)
        {
            for(int j=0; j<othello.SIZE;j++)
            {
                buttons[i][j].setText(""+othello.grid[i][j]);
            }
        }
    }
    
    public static void main (String [] args)
    {
        new GraphicalOthello();
    }
    
    public void actionPerformed (ActionEvent e)
    {
       
        String command = e.getActionCommand();
        row = Integer.parseInt(command.substring(0, 1));
        col = Integer.parseInt(command.substring(1, 2));  
        move=new Move(row, col);
        //Human Plays
        System.out.println("Your move: <row, col>"); 
         
        if (othello.canPlay(move))
        {
            
            System.out.println(row+","+col);   
            othello.status = othello.play(move);
            empty = false;
            print();
            //Can Machine Play?
            othello.toggleTurn();
            
            if (!(othello.generateMoves().isEmpty())) {
                //Machine Plays
                do{
                    print();
                    System.out.println("Computer's turn: ");
                    
                    othello.status = othello.machinePlay();
                    empty = false;
                    print();
                    //Can Human play?
                    othello.toggleTurn();
                    empty = (othello.generateMoves().isEmpty());
                    System.out.println(empty);
                    if (!empty){
                        othello.status=CAN_GO;
                    }
                    else{
                        //Can Machine Play?
                        othello.toggleTurn();
                        empty = (othello.generateMoves().isEmpty());
                        if (empty){
                            othello.status=othello.GAME_OVER;
                        }
                        else{
                            othello.status =othello.ONGOING;
                        }
                    }
                }while(othello.status==othello.ONGOING); 
            }
            else{
                //Can Human Play?
                othello.toggleTurn();
                empty = (othello.generateMoves().isEmpty());
                if (empty){
                    othello.status = othello.GAME_OVER;
                }
            }
            
            if (othello.status == othello.GAME_OVER){
                determineWinner();
                JOptionPane.showMessageDialog(null, "CONGRATULATIONS, YOU ARE  A WINNER");
                //JFrame f = new JFrame();
                //JLabel l= new JLabel("Congratulations you won");
                //f.add(l);
                //f.setVisible(true);
            }
        }
        else
        {
            System.out.println("Invalid Move");
        }
    }
}


