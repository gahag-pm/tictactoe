package dcc.gahag.tictactoe.game;

import java.util.ArrayList;
import java.util.List;


/**
 * The virtual computer player.
 * This player makes random movements.
 */
public class PCPlayer implements Player {
  /**
   * The player's tick on the board. Might be 'X', 'O', or anything else really.
   */
  protected final char tick;
  /**
   * The game being played.
   */
  protected final TicTacToe game;
  /**
   * Flag to control if the player is in it's turn.
   */
  protected boolean playing;
  

  /**
   * Construct a player with the given tick to play the given game.
   * This will add a observer to the game for the alternating playing.
   * @param game the game to be played, mustn't be null
   */
  public PCPlayer(char tick, TicTacToe game) {
    if (game == null)
      throw new IllegalArgumentException("game mustn't be null");

    this.tick = tick;
    this.game = game;

    game.addObserver(
      (Coord c) -> {
        this.playing = !this.playing;
        
        if (this.playing && !this.game.isFinished())
          this.play();
      }
    );
  }


  /**
   * Method for the PCPlayer to make it's move.
   * The PCPlayer makes random moves.
   */
  public void play() {
    this.playing = true;

    List<Coord> empty = new ArrayList<>();
    this.game.unsetTiles().iterator().forEachRemaining(empty::add);

    Coord c = empty.get((int) (Math.random() * empty.size()));

    game.setTile(c, this.tick);
  }
}
