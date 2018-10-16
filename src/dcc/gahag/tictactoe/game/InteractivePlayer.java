package dcc.gahag.tictactoe.game;

import java.util.function.Consumer;


/**
 * The interactive player.
 * This player represents a person playing the game, by some form of interaction.
 */
public class InteractivePlayer implements Player {
  /**
   * The player's tick on the board. Might be 'X', 'O', or anything else really.
   */
  protected final char tick;
  /**
   * The game being played.
   */
  protected final TicTacToe game;
  /**
   * The function to set the tickHandler.
   * The tickHandler is the function to handle the user input for a given tile.
   */
  protected final Consumer<Consumer<Coord>> setTickHandler;
  /**
   * Flag to control if the player is in it's turn.
   */
  protected boolean playing;
  

  /**
   * Construct a player with the given tick to play the given game.
   * This will add a observer to the game for the alternating playing.
   * @param setTickHandler The function to set the tickHandler.
   *  The tickHandler is the function to handle the user input for a given tile.
   */
  public InteractivePlayer(
    char tick,
    TicTacToe game,
    Consumer<Consumer<Coord>> setTickHandler
  ) {
    this.tick = tick;
    this.game = game;
    this.setTickHandler = setTickHandler;

    this.game.addObserver(
      (Coord c) -> {
        this.playing = !this.playing;

        if (this.playing && !this.game.isFinished())
          this.play();
      }
    );
  }


  /**
   * Method for the InteractivePlayer to make it's move.
   * This will install a tickHandler to receive the user's input, set the tile in the
   * game, and uninstall the tickHandler right after the move.
   */
  public void play() {
    this.playing = true;

    this.setTickHandler.accept(
      (Coord c) -> {
        if (!this.game.isTileSet(c)) {
          this.setTickHandler.accept(null);
          this.game.setTile(c, this.tick);
        }
      }
    );
  }
}
