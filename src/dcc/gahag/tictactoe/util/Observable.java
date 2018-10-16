package dcc.gahag.tictactoe.util;


/**
 * The interface for the observable object in the observer design pattern.
 */
public interface Observable<T> {
  /**
   * Add a observer to the object.
   */
  public void addObserver(Observer<T> observer);
}
