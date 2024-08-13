import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3, 5, 5));
        getContentPane().setBackground(new Color(50, 50, 50));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Roboto", Font.BOLD, 60)); // Modern font
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(new Color(240, 240, 240)); // Light grey button background
            buttons[i].setForeground(new Color(0, 0, 0)); // Black text
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 5)); // Border around buttons
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (!buttonClicked.getText().equals("")) {
            return; // Ignore click if button is already marked
        }

        if (isXTurn) {
            buttonClicked.setText("X");
            buttonClicked.setForeground(new Color(255, 87, 34)); // Orange color for X
        } else {
            buttonClicked.setText("O");
            buttonClicked.setForeground(new Color(33, 150, 243)); // Blue color for O
        }

        isXTurn = !isXTurn; // Switch turns

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this,
                    "Player " + (isXTurn ? "O" : "X") + " wins!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this,
                    "The game is a draw!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
        }
    }

    private boolean checkForWin() {
        // Winning combinations (rows, columns, diagonals)
        int[][] winningPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] positions : winningPositions) {
            if (buttons[positions[0]].getText().equals(buttons[positions[1]].getText()) &&
                    buttons[positions[1]].getText().equals(buttons[positions[2]].getText()) &&
                    !buttons[positions[0]].getText().equals("")) {
                return true;
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
            button.setBackground(new Color(240, 240, 240)); // Reset button color
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
