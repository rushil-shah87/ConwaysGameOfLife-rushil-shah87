package a8intermediate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LifeView extends JPanel implements SpotListener, ActionListener {
	
	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JPanel utility_panel;
	private JPanel side_panel;
	private List<LifeViewListener> listeners;
	private int _dimension;
	private JTextField dimension_field;
	private boolean _torus;
	private JCheckBox torus_field;
	private int _low_birth;
	private JTextField low_birth_field;
	private int _high_birth;
	private JTextField high_birth_field;
	private int _low_survive;
	private JTextField low_survive_field;
	private int _high_survive;
	private JTextField high_survive_field;
	
	public LifeView() {
		this._dimension = 30;
		this._torus = false;
		_low_birth = 3;
		_high_birth = 3;
		_low_survive = 2;
		_high_survive = 3;

		/* Create SpotBoard and message label. */
		_board = new JSpotBoard(_dimension, _dimension);
		
		/* Set layout and place SpotBoard at center. */
		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);
		
		/* Create subpanel for message area and reset button. */
		utility_panel = new JPanel();
		utility_panel.setLayout(new GridLayout(2,3));

		/* Reset button. Add ourselves as the action listener. */
		JButton clear = new JButton("clear field");
		clear.addActionListener(this);
		utility_panel.add(clear);
				    
	    JButton random = new JButton("random fill");
	    random.addActionListener(this);
	    utility_panel.add(random);
		    
		JButton advance = new JButton("advance");
	    advance.addActionListener(this);
	    utility_panel.add(advance);
	    
	    JCheckBox torus = new JCheckBox("wrapping", false);
	    torus.addActionListener(this);
	    utility_panel.add(torus);
	    torus_field = torus;
	    
	    utility_panel.add( new JLabel("set dimensions between 10 and 500:") );
	    
	    JTextField dimensions = new JTextField("");
	    dimensions.addActionListener(this);
	    utility_panel.add(dimensions);
	    dimension_field = dimensions;
	    
	    JPanel side_panel = new JPanel();
	    side_panel.setLayout( new GridLayout(9,1) );
	    
	    side_panel.add( new JLabel("set birth/survival thresholds") );
	    
	    side_panel.add( new JLabel("low birth") );
	    JTextField low_birth = new JTextField("");
	    low_birth.addActionListener(this);
	    side_panel.add(low_birth);
	    low_birth_field = low_birth;
	    
	    side_panel.add( new JLabel("high birth") );
	    JTextField high_birth = new JTextField("");
	    high_birth.addActionListener(this);
	    side_panel.add(high_birth);
	    high_birth_field = high_birth;
	    
	    side_panel.add( new JLabel("low survive") );
	    JTextField low_survive = new JTextField("");
	    low_survive.addActionListener(this);
	    side_panel.add(low_survive);
	    low_survive_field = low_survive;
	    
	    side_panel.add( new JLabel("high survive") );
	    JTextField high_survive = new JTextField("");
	    high_survive.addActionListener(this);
	    side_panel.add(high_survive);
	    high_survive_field = high_survive;

		/* Add subpanel in south area of layout. */
		add(utility_panel, BorderLayout.SOUTH);
		add(side_panel, BorderLayout.EAST);

		/* Add ourselves as a spot listener for all of the spot board. */
		_board.addSpotListener(this);
		
		listeners = new ArrayList<LifeViewListener>();
		
		this.setFocusable(true);
		this.grabFocus();
	}
	public void resizeBoard(int dimension) {
		_dimension = dimension;
		remove(_board);
		_board = new JSpotBoard(_dimension, _dimension);
		add(_board, BorderLayout.CENTER);
		validate();
		repaint();
		_board.addSpotListener(this);
	}
	public void randomFill() {
		clearDisplay();
		for(Spot spot : _board) {
			double random = (Math.random());
			if(random > 0.8) {
				spot.setBackground(Color.BLACK);
			}
		}
	}
	public void toggleTorus() {
		_torus = !_torus;
	}
	public boolean getTorus() {
		return _torus;
	}
	public int getLBThreshold() {
		return _low_birth;
	} 
	public int getHBThreshold() {
		return _high_birth;
	} 
	public int getLSThreshold() {
		return _low_survive;
	} 
	public int getHSThreshold() {
		return _high_survive;
	} 
	public void setLBThreshold(int new_val) {
		_low_birth = new_val;
	} 
	public void setHBThreshold(int new_val) {
		_high_birth = new_val;
	} 
	public void setLSThreshold(int new_val) {
		_low_survive = new_val;
	} 
	public void setHSThreshold(int new_val) {
		_high_survive = new_val;
	} 
	
	public boolean[][] getBoard() {
		int width = _board.getSpotWidth();
		int height = _board.getSpotHeight();
		boolean[][] board_array = new boolean[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(_board.getSpotAt(i, j).getBackground()==Color.BLACK) {
					board_array[i][j] = true;
				} else {
					board_array[i][j] = false;
				}
			}
		}
		return board_array;
	}
	
	public void setDisplay(boolean[][] input) {
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				_board.getSpotAt(i, j).setBackground(Color.WHITE);
				if(input[i][j]) {
					_board.getSpotAt(i, j).setBackground(Color.BLACK);
				}
			}
		}
	}
	
	public void clearDisplay() {
		for(Spot s : _board) {
			s.setBackground(Color.WHITE);
		}
	}
	
	public void spotClicked(Spot spot) {
		if (spot.getBackground()==Color.BLACK) spot.setBackground(Color.WHITE);
		else spot.setBackground(Color.BLACK);
	}
	
	public void spotEntered(Spot spot) {}
	
	public void spotExited(Spot spot) {}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dimension_field) {
			JTextField field = (JTextField) e.getSource();
			String value = field.getText();
			fireEvent(new DimensionEvent(value));
			return;
		}
		if(e.getSource() == torus_field) {
			JCheckBox box = (JCheckBox) e.getSource();
			boolean checked = box.isSelected();
			fireEvent(new ToggleTorusEvent());
			return;
		} 
		if(e.getSource() == low_birth_field) {
			JTextField field = (JTextField) e.getSource();
			String value = field.getText();
			fireEvent(new LBThresholdEvent(value));
			return;
		} 
		if(e.getSource() == high_birth_field) {
			JTextField field = (JTextField) e.getSource();
			String value = field.getText();
			fireEvent(new HBThresholdEvent(value));
			return;
		} 
		if(e.getSource() == low_survive_field) {
			JTextField field = (JTextField) e.getSource();
			String value = field.getText();
			fireEvent(new LSThresholdEvent(value));
			return;
		} 
		if(e.getSource() == high_survive_field) {
			JTextField field = (JTextField) e.getSource();
			String value = field.getText();
			fireEvent(new HSThresholdEvent(value));
			return;
		} 
		
		JButton button = (JButton) e.getSource();
		char first_char = button.getText().charAt(0);
		dispatchEventByChar(first_char);
	}
	private void dispatchEventByChar(char c) {
		switch(c) {
		case 'a':
			fireEvent(new AdvanceEvent());
			break;
		case 'c':
			fireEvent(new ClearFieldEvent());
			break;
		case 'r':
			fireEvent(new RandomFillEvent());
			break;
		}
	}
	public void fireEvent(LifeViewEvent e) {
		for (LifeViewListener l : listeners) {
			l.handleLifeViewEvent(e);
		}
	}
	public void addLifeViewListener(LifeViewListener listen) {
		listeners.add(listen);
	}
	public void removeCalculatorViewListener(LifeViewListener listen) {
		listeners.remove(listen);
	}
}
