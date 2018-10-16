package dcc.gahag.tictactoe.game;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dcc.gahag.tictactoe.util.Observer;


/**
 * The data model for the classic tic tac toe game.
 */
public class Game implements TicTacToe {
  /**
   * The board data.
   */
  protected char[][] tiles;

  /**
   * The set indicating the empty tiles of the board.
   */
  protected Set<Coord> empty = new HashSet<Coord>(Arrays.asList(
    new Coord(0, 0),
    new Coord(0, 1),
    new Coord(0, 2),
    new Coord(1, 0),
    new Coord(1, 1),
    new Coord(1, 2),
    new Coord(2, 0),
    new Coord(2, 1),
    new Coord(2, 2)
  ));

  /**
   * List of the added observers.
   */
  protected List<Observer<Coord>> observers = new ArrayList<Observer<Coord>>();
  

  public Game() {
    this.tiles = new char[TicTacToe.bounds.upper.x + 1][TicTacToe.bounds.upper.y + 1];
  }


  /**
   * {@inheritDoc}
   * @param c the coordinate of the tile, mustn't be null
   */
  public boolean isTileSet(Coord c) {
    if (c == null)
      throw new IllegalArgumentException("Coord mustn't be null");

    return this.getTile(c) != '\0';
  }
  /**
   * {@inheritDoc}
   * @param c the coordinate of the tile, mustn't be null
   */
  public char getTile(Coord c) {
    if (c == null)
      throw new IllegalArgumentException("Coord mustn't be null");

    return this.tiles[c.x][c.y];
  }
  /**
   * {@inheritDoc}
   * @param c the coordinate of the tile, mustn't be null
   */
  public void setTile(Coord c, char tick) {
    if (c == null)
      throw new IllegalArgumentException("Coord mustn't be null");
    
    if (tick == '\0')
      this.empty.add(c);
    else
      this.empty.remove(c);

    this.tiles[c.x][c.y] = tick;
    
    for (Observer<Coord> observer : this.observers)
      observer.update(c);
  }

  /**
   * {@inheritDoc}
   */
  public Iterable<Coord> unsetTiles() {
    return this.empty;
  }


  /**
   * {@inheritDoc}
   */
  public void addObserver(Observer<Coord> observer) {
    if (observer != null)
      this.observers.add(observer);
  }
}
