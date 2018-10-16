package dcc.gahag.tictactoe.game;

import dcc.gahag.tictactoe.util.Observable;
import dcc.gahag.tictactoe.util.Observer;


/**
 * The model for the classic tic tac toe game.
 */
public interface TicTacToe extends Observable<Coord> {
  /**
   * The bounds of the board.
   */
  public static final Bounds bounds = new Bounds(new Coord(0, 0), new Coord(2, 2));
  

  /**
   * Check whether the given tile is not empty.
   */
  public boolean isTileSet(Coord c);
  /**
   * Get the given tile's value.
   * The value for a empty tile is unspecified.
   */
  public char getTile(Coord c);
  /**
   * Set the given tile to the given value.
   */
  public void setTile(Coord c, char tick);

  /**
   * An iterable to the empty tiles of the board.
   */
  public Iterable<Coord> unsetTiles();


  /**
   * Add a observer for the board.
   * The observer is notified whenever any tile is changed.
   */
  public void addObserver(Observer<Coord> observer);


  /**
   * Check whether the game is finished.
   * A game is finished when there is a draw (i.e.: all the tiles are set) or a winner.
   */
  public default boolean isFinished() {
    return this.isTied() || this.getWinner() != null;
  }

  /**
   * Check whether the game is tied.
   */
  public default boolean isTied() {
    return !this.unsetTiles().iterator().hasNext();
  }

  /**
   * Returns the winner, or null if there is none.
   */
  public default Character getWinner() {
    char _00 = this.getTile(new Coord(0, 0))
       , _01 = this.getTile(new Coord(0, 1))
       , _02 = this.getTile(new Coord(0, 2))
       , _10 = this.getTile(new Coord(1, 0))
       , _11 = this.getTile(new Coord(1, 1))
       , _12 = this.getTile(new Coord(1, 2))
       , _20 = this.getTile(new Coord(2, 0))
       , _21 = this.getTile(new Coord(2, 1))
       , _22 = this.getTile(new Coord(2, 2));
    
    // Columns:
    if (this.isTileSet(new Coord(0, 0)) && _00 == _01 && _00 == _02)
      return _00;

    if (this.isTileSet(new Coord(1, 0)) && _10 == _11 && _10 == _12)
      return _10;

    if (this.isTileSet(new Coord(2, 0)) && _20 == _21 && _20 == _22)
      return _20;

    // Rows:
    if (this.isTileSet(new Coord(0, 0)) && _00 == _10 && _00 == _20)
      return _00;

    if (this.isTileSet(new Coord(0, 1)) && _01 == _11 && _01 == _21)
      return _01;

    if (this.isTileSet(new Coord(0, 2)) && _02 == _12 && _02 == _22)
      return _02;
    
    // Diagonals:
    if (this.isTileSet(new Coord(0, 0)) && _00 == _11 && _00 == _22)
      return _00;

    if (this.isTileSet(new Coord(2, 0)) && _20 == _11 && _20 == _02)
      return _20;

    return null;
  }
}
