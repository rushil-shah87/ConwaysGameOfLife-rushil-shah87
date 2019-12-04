package a8intermediate;

import a8intermediate.Spot;

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
