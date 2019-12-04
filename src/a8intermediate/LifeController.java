package a8intermediate;

public class LifeController implements LifeObserver, LifeViewListener {
	LifeView view;
	LifeModel model;
	
	public LifeController(LifeModel _model, LifeView _view) {
		this.view = _view;
		this.model = _model;
		view.addLifeViewListener(this);
		model.addObserver(this);
	}
	public void handleLifeViewEvent(LifeViewEvent e) {
		if (e.isAdvanceEvent()) {
			model.setInputBoard(view.getBoard(), view.getTorus());
			model.evaluateCells(view.getLBThreshold(), view.getHBThreshold(), view.getLSThreshold(), view.getHSThreshold());
		} else if(e.isClearFieldEvent()) {
			model.resetModelArrays();
			view.clearDisplay();
		} else if(e.isDimensionEvent()) {
			DimensionEvent dimension = (DimensionEvent) e;
			view.resizeBoard(dimension.getDimension());
			model.setInputBoard(view.getBoard(), view.getTorus());
		} else if(e.isRandomFillEvent()) {
			view.randomFill();
			model.setInputBoard(view.getBoard(), view.getTorus());
		} else if(e.isToggleTorusEvent()) {
			view.toggleTorus();
			model.setInputBoard(view.getBoard(), view.getTorus());
		} else if(e.isLBThresholdEvent()) {
			LBThresholdEvent lb_event = (LBThresholdEvent) e;
			view.setLBThreshold(lb_event.getLBThreshold());
		} else if(e.isHBThresholdEvent()) {
			HBThresholdEvent hb_event = (HBThresholdEvent) e;
			view.setHBThreshold(hb_event.getHBThreshold());
		} else if(e.isLSThresholdEvent()) {
			LSThresholdEvent ls_event = (LSThresholdEvent) e;
			view.setLSThreshold(ls_event.getLSThreshold());
		} else if(e.isHSThresholdEvent()) {
			HSThresholdEvent hs_event = (HSThresholdEvent) e;
			view.setHSThreshold(hs_event.getHSThreshold());
		} 
	}
	public void update(LifeModel model) {
		view.setDisplay(model.getOutputBoard());
	}
	
}
