package dcc.gahag.tictactoe.game;


/**
 * Immutable bidimensional boundaries.
 */
public class Bounds {
  public final Coord lower, upper;
  

  /**
   * Construct a Bounds object from the given lower and upper coordinates.
   * Lower's coordinates must be equal or lower than upper's, forming a rectangle.
   * @param lower the lower coordinate, mustn't be null
   * @param upper the upper coordinate, mustn't be null
   */
  public Bounds(Coord lower, Coord upper) {
    if (lower == null)
      throw new IllegalArgumentException("lower mustn't be null");

    if (upper == null)
      throw new IllegalArgumentException("upper mustn't be null");
    
    if (upper.x < lower.x || upper.y < lower.y)
      throw new IllegalArgumentException(
        "lower's coordinates must be equal or lower than upper's"
      );

    this.lower = lower;
    this.upper = upper;
  }


  /**
   * Checks wether the bounds contains the given coordinate.
   * @param c the coordinate to check, possibly null
   */
  public boolean contains(Coord c) {
    if (c == null)
      return false;
    
    return this.lower.x <= c.x && c.x <= this.upper.x
        && this.lower.y <= c.y && c.y <= this.upper.y;
  }
}
