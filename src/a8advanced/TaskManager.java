package a8advanced;

public class TaskManager extends Thread {
	private boolean done;
	private LifeController _ctrl;
	private long _mil;
	
	public TaskManager(LifeController ctrl, long mil) {
		_ctrl = ctrl;
		done = false;
		_mil = mil;
	}

	public void halt() {
		done = true;
	}
	
	public void run() {		
		while (!done) {
			try {
				Thread.sleep(_mil);
			} catch (InterruptedException e) {}
			_ctrl.handleLifeViewEvent( new AdvanceEvent() );
		}
	}
}
