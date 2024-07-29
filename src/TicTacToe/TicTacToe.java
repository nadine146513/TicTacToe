package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
	//----------------------------------------------------------------
	//Initialize the Swing components
    JFrame frame = new JFrame("Tic-Tac-Toe");//initialize the main window that will hold everything
    JLabel textLabel = new JLabel();//initialize the text label that will hold everything
    JPanel textPanel = new JPanel();//initialize a container that can hold components
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];//initialize a 2D array that represent the TicTacToe grid
    
    //---------------------------------------------------------------------------------
    //Player Variables
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;//to keep track of the current player
    //---------------------------------------------------------------------------------------------
    //State Variables
    boolean gameOver = false;
    int turns = 0;
    //--------------------------------------------------------------------------------------------
    //Constructor
   public TicTacToe() {
	   //Set the properties of the JFrame
        frame.setVisible(true);//to show the window one the constructor is called
        frame.setSize(600,650);//set the board width to 600 and the boardHeight to 650 because the additional 50px re for the text panel on the top
        frame.setLocationRelativeTo(null);//set the location of the window in the center
        frame.setResizable(false);//inhibit the user from resizing the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//to close the window once the user click the x button
        frame.setLayout(new BorderLayout());//to divide the frame into North, south, east,west,center

        //set the properties of the textLabel
        textLabel.setBackground(Color.darkGray);//set the background of the text box to black
        textLabel.setForeground(Color.white);//set the color of the letters of the text to white
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));//specify the style, bold,size of the letters
        textLabel.setHorizontalAlignment(JLabel.CENTER);//set its location to the center
        textLabel.setText("Tic-Tac-Toe");//specify what is the text
        textLabel.setOpaque(true);//set the background of the text box to black
        
        //set the properties of the textPanel which is the container of the textLabel
        textPanel.setLayout(new BorderLayout());//to divide the frame into North, south, east,west,center
        textPanel.add(textLabel);//add the textLabel to the textPanel
        frame.add(textPanel, BorderLayout.NORTH);//add all the textPanel which include the textLabel into the frame in the North 

        //set the properties of the boardPanel 
        boardPanel.setLayout(new GridLayout(3, 3));//divide the panel into grid 3 by 3
        boardPanel.setBackground(Color.darkGray);//set the background color to black
        frame.add(boardPanel);//add the boarderPanel to the frame and it will occupy the remaining place

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();//initialize a button for each cell in the grid
                board[r][c] = tile;// add this button title to the corresponding cell
                boardPanel.add(tile);//add the title to the board panel

                tile.setBackground(Color.darkGray);//set the color of the text box in the button
                tile.setForeground(Color.white);//set the colors of the letters
                tile.setFont(new Font("Arial", Font.BOLD, 120));//the font of the text in the button 
                tile.setFocusable(false);//when clicking the button do not focus on it 
                // tile.setText(currentPlayer);

                //add action listener to the button tile
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }

                    }
                });
            }
        }
    }
   //---------------------------------------------------------------------------------------------------
    
    public void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;
            
            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    public void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }
//---------------------------------------------------------------------------------------------------
    public void setTie(JButton tile) {
        tile.setForeground(Color.pink);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie.");
    }
}