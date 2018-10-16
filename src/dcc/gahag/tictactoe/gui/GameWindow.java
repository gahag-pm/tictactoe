package dcc.gahag.tictactoe.gui;

import java.util.function.Consumer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dcc.gahag.tictactoe.game.Coord;
import dcc.gahag.tictactoe.game.TicTacToe;


/**
 * The game window.
 * It contains the tiles for the tic tac toe game.
 */
public class GameWindow extends JFrame {
  /**
   * The handler to process the event of click a tile.
   */
  protected Consumer<Coord> tickHandler;
  

  /**
   * Construct a GameWindow for the given game.
   * The window has a fixed size, and disposes on close.
   * This will install observers in the game to update the buttons' text and inform the
   * winner or draw.
   */
  public GameWindow(final TicTacToe game) {
    super("Tic Tac Toe");

    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    this.setSize(new Dimension(170, 80));
    this.setResizable(false);
    
    this.setLayout(new GridLayout(3, 3, 5 , 5));

    for (int x = TicTacToe.bounds.lower.x; x <= TicTacToe.bounds.upper.x; x++)
      for (int y = TicTacToe.bounds.lower.y; y <= TicTacToe.bounds.upper.y; y++) {
        final Coord c = new Coord(x, y);
        
        final JButton button = new JButton();
        button.setPreferredSize(new Dimension(60, 60));
        button.addActionListener((ActionEvent e) -> this.onTick(c));
        this.add(button);
        
        game.addObserver(
          (Coord pos) -> {
            if (pos.equals(c)) // The tile corresponding to the button changed:
              button.setText(Character.toString(game.getTile(pos)));
          }
        );
      }

    game.addObserver( // Check if there is a winner after every move:
      (Coord c) -> {
        Character winner = game.getWinner();
            
        if (winner != null || game.isTied()) {
          if (winner != null)
            this.showWinner(winner);
          else
            this.showDraw();

          this.setVisible(false);
          this.dispose();
        }
      }
    );

    this.pack();
    this.setLocationRelativeTo(null);
  }


  /**
   * Show a message indicating the winner of the game.
   */
  protected void showWinner(char winner) {
    JOptionPane.showMessageDialog(
      this,
      "Winner: " + winner,
      "Game result",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  /**
   * Show a message indicating that the game is tied.
   */
  protected void showDraw() {
    JOptionPane.showMessageDialog(
      this,
      "Draw!",
      "Game result",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  /**
   * The method to handle the click event of the tile buttons.
   * Invokes the tickHandler, unless it is none.
   */
  protected void onTick(Coord c) {
    if (this.tickHandler != null)
      this.tickHandler.accept(c);
  }

  /**
   * Sets the tick handler.
   * The tickHandler is the function to handle the user input for a given tile.
   */
  public void setTickHandler(Consumer<Coord> handler) {
    this.tickHandler = handler;
  }
}
