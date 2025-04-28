
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tic_Tac_Toe {
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
                        if(tile.getText()==""){
                            tile.setText(currentPlayer);
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
    }

    void checkWinner(){
       for(int i=0;i<3;i++){
           if(board[i][0].getText() == "") continue;
           if(board[i][0].getText() == board[i][1].getText() && board[i][1].getText() == board[i][2].getText()){
               gameOver = true;
               return;
           }
       }
    }
}
