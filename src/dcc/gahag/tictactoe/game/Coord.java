package dcc.gahag.tictactoe.game;


import java.util.Random;
import java.util.Objects;


/**
 * A bidimensional immutable coordinate.
 */
public class Coord {
  public final int x, y;


  /**
   * The origin coordinate, i.e. (0, 0).
   */
  public static final Coord origin = new Coord(0, 0);


  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }


  /**
   * Indicates whether some other object is a Coord with the same values for x and y.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Coord))
      return false;
    
    Coord c = (Coord) obj;

    return c.x == this.x && c.y == this.y;
  }

  /**
   * Returns a hash code value for the Coord.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  
  /**
   * Construct a random Coord, within the given bounds, if any.
   * @param b the bounds within the Coord will be randomized, possibly null
   */
  public static Coord random(Bounds b) {
    if (b == null) {
      Random r = new Random();
      
      return new Coord(r.nextInt(), r.nextInt());
    }
    
    return new Coord(
      b.lower.x + (int) (Math.random() * (b.upper.x - b.lower.x + 1)),
      b.lower.y + (int) (Math.random() * (b.upper.y - b.lower.y + 1))
    );
  }
}
