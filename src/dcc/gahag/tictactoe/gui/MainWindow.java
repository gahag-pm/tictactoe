package dcc.gahag.tictactoe.gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * The main window.
 * The window has a single button to start a tic tac toe game.
 */
public class MainWindow extends JFrame {
  /**
   * Construct a new MainWindow.
   * The window has a fixed size, and exits on close.
   * @param gameFactory the function to create a new game, called when the new game button
   *  is clicked
   */
  public MainWindow(Runnable gameFactory) {
    super("Tic Tac Toe");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setSize(new Dimension(170, 80));
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    
    this.setLayout(new GridBagLayout());

    JButton newGamebutton = new JButton("New game");
    newGamebutton.setMaximumSize(new Dimension(130, 30));
    newGamebutton.addActionListener((ActionEvent e) -> gameFactory.run());
    this.add(newGamebutton);
  }
}
