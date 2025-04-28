
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tic_Tac_Toe implements KeyListener {
    int boardWidth = 600;
    int boardHeight= 650;

    JFrame frame = new JFrame("TIC TAC TOE");
    JLabel textLable = new JLabel();
    JPanel textPanel = new JPanel();

    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";

    String currentPlayer = playerX;

    boolean  gameOver = false;
    int turns = 0;
    Tic_Tac_Toe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLable.setBackground(Color.darkGray);
        textLable.setForeground(Color.white);
        textLable.setFont(new Font("Arial",Font.BOLD,50));
        textLable.setHorizontalAlignment(JLabel.CENTER);
        textLable.setText("TIC-TAC-TOE");
        textLable.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLable);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText().isEmpty()){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLable.setText(currentPlayer+"'s turn");
                            }
                        }

                    }
                });
            }
        }

        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    void checkWinner(){
        //horizontal
       for(int i=0;i<3;i++){
           if(board[i][0].getText().isEmpty()) continue;
           if(board[i][0].getText().equals(board[i][1].getText()) &&
               board[i][1].getText().equals(board[i][2].getText())){
               for(int j=0;j<3;j++){
                   setWinner(board[i][j]);
               }
               gameOver = true;
               return;
           }
       }
       //vertical
       for(int i=0;i<3;i++){
           if(board[0][i].getText().isEmpty()) continue;
           if(board[0][i].getText().equals(board[1][i].getText()) &&
           board[1][i].getText().equals(board[2][i].getText())){
               for(int j=0;j<3;j++){
                   setWinner(board[j][i]);
               }
               gameOver = true;
               return;
           }
       }

       //diagonal
        if(board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) &&
            !board[0][0].getText().isEmpty()){
                for(int i=0;i<3;i++){
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
        }

        //anti-diagonal
        if(board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) &&
            !board[0][2].getText().isEmpty()){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        //draw logic
        if(turns == 9){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    setTile(board[i][j]);
                }
            }
            gameOver = true;
            return;
        }
    }


    //winner logic
    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLable.setText(currentPlayer+ " is the winner");
    }

    void setTile(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLable.setText("Its a draw");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(gameOver){
               resetGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    void resetGame(){
        currentPlayer = playerX;
        textLable.setText("TIC-TAC-TOE");
        gameOver=false;
        turns = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setBackground(Color.darkGray);
                board[i][j].setForeground(Color.white);
            }
        }
    }
}
