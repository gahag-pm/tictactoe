package dcc.gahag.tictactoe;

import javax.swing.UIManager;

import dcc.gahag.tictactoe.game.Coord;
import dcc.gahag.tictactoe.game.Game;
import dcc.gahag.tictactoe.game.InteractivePlayer;
import dcc.gahag.tictactoe.game.PCPlayer;
import dcc.gahag.tictactoe.game.Player;
import dcc.gahag.tictactoe.game.TicTacToe;
import dcc.gahag.tictactoe.gui.GameWindow;
import dcc.gahag.tictactoe.gui.MainWindow;


/**
 * The main class for the tic tac toe game.
 */
public class Main {
  public static void main(String args[]) {
    // Set the look & feel to gtk if possible:
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
    }
    catch (Exception e) { }

    MainWindow window = new MainWindow(
      () -> { // Factory for a new game.
        TicTacToe game = new Game();
        GameWindow gameWindow = new GameWindow(game);
        Player p1 = new PCPlayer('X', game);
        Player p2 = new InteractivePlayer('O', game, gameWindow::setTickHandler);
        
        gameWindow.setVisible(true);

        // The InteractivePlayer starts playing:
        p2.play();
      }
    );

    window.setVisible(true);
  }
}
