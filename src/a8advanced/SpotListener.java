package a8advanced;
import a8advanced.Spot;

/*
 * SpotListener
 * 
 * Listener interface supported by Spot to report click, enter, and exit events.
 * 
 */

public interface SpotListener {

	void spotClicked(Spot spot);
	void spotEntered(Spot spot);
	void spotExited(Spot spot);
}
