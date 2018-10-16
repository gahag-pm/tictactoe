package dcc.gahag.tictactoe.util;


/**
 * The interface for the observer in the observer design pattern.
 */
public interface Observer<T> {
  /**
   * The method to be called when the observable changes.
   */
  public void update(T value);
}
