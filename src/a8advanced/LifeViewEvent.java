package a8advanced;

abstract public class LifeViewEvent {
	public boolean isAdvanceEvent() { return false; }
	public boolean isDimensionEvent() { return false; }
	public boolean isRandomFillEvent() { return false; }
	public boolean isClearFieldEvent() { return false; }
	public boolean isToggleTorusEvent() { return false; }
	public boolean isLBThresholdEvent() { return false; }
	public boolean isHBThresholdEvent() { return false; }
	public boolean isLSThresholdEvent() { return false; }
	public boolean isHSThresholdEvent() { return false; }
	public boolean isStartStopEvent() { return false; }
	public boolean isStartEvent() { return false; }
	public boolean isStopEvent() { return false; }

}

class AdvanceEvent extends LifeViewEvent {
	public boolean isAdvanceEvent() { return true; }
}

class DimensionEvent extends LifeViewEvent {
	private int _dimension;
	
	public DimensionEvent(String dim){
		_dimension = Integer.parseInt(dim);
		if(_dimension < 10) {
			_dimension = 10;
		}
		if(_dimension > 500) {
			_dimension = 500;
		}
	}
	
	public int getDimension() {
		return _dimension;
	}
	
	public boolean isDimensionEvent() { return true; }
}

class RandomFillEvent extends LifeViewEvent {
	
	public boolean isRandomFillEvent() { return true; }
}

class ClearFieldEvent extends LifeViewEvent {
	
	public boolean isClearFieldEvent() { return true; }
}

class ToggleTorusEvent extends LifeViewEvent {
	
	public boolean isToggleTorusEvent() { return true; }
}

class LBThresholdEvent extends LifeViewEvent {
	private int _low_birth;
	
	public LBThresholdEvent (String val) {
		if (Integer.parseInt(val)>-1) _low_birth = Integer.parseInt(val);
	}
	public int getLBThreshold() {
		return _low_birth;
	}
	public boolean isLBThresholdEvent() { return true; }
}

class HBThresholdEvent extends LifeViewEvent {
	private int _high_birth;
	
	public HBThresholdEvent (String val) {
		if (Integer.parseInt(val)>-1) _high_birth = Integer.parseInt(val);
	}
	public int getHBThreshold() {
		return _high_birth;
	}
	public boolean isHBThresholdEvent() { return true; }
}

class LSThresholdEvent extends LifeViewEvent {
	private int _low_survive;
	
	public LSThresholdEvent (String val) {
		if (Integer.parseInt(val)>-1) _low_survive = Integer.parseInt(val);
	}
	public int getLSThreshold() {
		return _low_survive;
	}
	public boolean isLSThresholdEvent() { return true; }
}

class HSThresholdEvent extends LifeViewEvent {
	private int _high_survive;
	
	public HSThresholdEvent (String val) {
		if (Integer.parseInt(val)>-1) _high_survive = Integer.parseInt(val);
	}
	public int getHSThreshold() {
		return _high_survive;
	}
	public boolean isHSThresholdEvent() { return true; }
}

class StartStopEvent extends LifeViewEvent {
	private boolean _paused;
	
	public StartStopEvent(boolean paused) {
		_paused = paused;
	}
	
	public boolean isStartEvent() { 
		if (!_paused) return true;
		else return false;
	}
	public boolean isStopEvent() { 
		if (_paused) return true;
		else return false;	}
	
	public boolean isStartStopEvent() { return true; }
}