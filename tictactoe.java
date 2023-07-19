import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tictactoe {
    int boardwidth=500;
    int boardheight =550;
        //game frame
    JFrame frame= new JFrame("Tic Tac Toe");
    //text in game
    JLabel textlabel= new JLabel();
    //text panel in game
    JPanel textpanel= new JPanel();
    //board panel to play the game
    JPanel boardpanel= new JPanel();
    //button on board

    JButton[][] board= new JButton[3][3];
    //there will be two player which is X and O
    String player1= "X";
    String player2= "O";
    //to track who is playing currently
    String current= player1;

    //game over prediction
    Boolean gameover=false;

    //corner case if its tie
    int count=0;

    //constructor
    tictactoe(){
        //intialisizing frame
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //initialising text label
        textlabel.setBackground(Color.black);
        textlabel.setForeground(Color.red);
        textlabel.setFont(new Font("Arial", Font.BOLD,25));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("TIC-TAC-TOE");
        textlabel.setOpaque(true);

        //intialising text panel
        textpanel.setLayout(new BorderLayout());
        textpanel.add(textlabel);
        frame.add(textpanel, BorderLayout.NORTH);
        
        //board panel for game in 3x3 grid
        boardpanel.setLayout(new GridLayout(3,3));
        boardpanel.setBackground(Color.GRAY);
        frame.add(boardpanel);

        //game play
        for(int r=0; r<3; r++)
        {
            for(int c=0; c<3; c++)
            {
                JButton tile= new JButton();
                board[r][c]=tile;
                boardpanel.add(tile);
                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,25));
                tile.setFocusable(false);
                //tile.setText(current);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        //if the game is over we have to return so this will be our base case
                        if(gameover)
                        {
                            return;
                        }
                        JButton tile= (JButton) e.getSource();
                        if(tile.getText()=="")
                        {
                            tile.setText(current);
                            count++;
                            //we are calling the fuction to check the winner
                            checkwinner();

                            if(!gameover)
                            {
                                current= current==player1?player2:player1;
                                textlabel.setText(current+"'s Turn");  
                            }
                        }
                        
                    }
                });

            }
        }
    }
    void checkwinner(){
        //horzontal winner
        for(int r=0 ;r<3; r++)
        {
            if(board[r][0].getText()=="")
            continue;

            if(board[r][0].getText()==board[r][1].getText() && board[r][1].getText()== board[r][2].getText()){
                for(int i=0; i<3; i++)
                {
                    //we are calling the funtion set winner to make it obvious for the winner identification
                    setwinner(board[r][i]);
                }
                gameover=true;
                return;
            }
            

        }
        //vertical
        for(int c=0; c<3; c++)
        {
            if(board[0][c].getText()=="")
            continue;

            if(board[0][c].getText()==board[1][c].getText() && board[1][c].getText()==board[2][c].getText())
            {
                for(int i=0; i<3; i++)
                {
                    setwinner(board[i][c]);
                }
                gameover=true;
                return;
            }
        }
        //diagonal
        if(board[0][0].getText()==board[1][1].getText() && board[1][1].getText()==board[2][2].getText()&&
        board[0][0].getText()!="")
        {
            for(int i=0; i<3; i++)
            {
                setwinner(board[i][i]);
            }
            gameover=true;
            return;
        }

        //anti diagonal
        if(board[0][2].getText()==board[1][1].getText() && board[1][1].getText()==board[2][0].getText()&&
        board[0][2].getText()!="")
        {
            setwinner(board[0][2]);
            setwinner(board[1][1]);
            setwinner(board[2][0]);
            
            gameover=true;
            return;
        }
        
        //if it tie
        if(count==9)
        {
            for(int r=0; r<3; r++)
            {
                for(int c=0; c<3; c++)
                {
                    settie(board[r][c]);
                }
            }
            gameover=true;
        }

    }
    void setwinner(JButton tile){
        tile.setBackground(Color.DARK_GRAY);
        tile.setForeground(Color.green);
        textlabel.setText(current+ " is the winner");
    }
    void settie(JButton tile){
        tile.setBackground(Color.DARK_GRAY);
        tile.setForeground(Color.orange);
        textlabel.setText("Its a TIE !!!");
    }

}
