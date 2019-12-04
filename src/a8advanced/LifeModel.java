package a8advanced;

import java.util.ArrayList;
import java.util.List;

public class LifeModel {
	private boolean[][] _input_board;
	private boolean[][] _output_board;
	private boolean[][] _point_test;
	private List<LifeObserver> observers;
	
	public LifeModel() {
		observers = new ArrayList<LifeObserver>();
	}
	public void addObserver(LifeObserver observer) {
		observers.add(observer);
	}
	public void removeObserver(LifeObserver observer) {
		observers.remove(observer);
	}
	private void notifyObservers() {
		for (LifeObserver observer : observers) {
			observer.update(this);
		}
	}
	public void evaluateCells(int lb, int hb, int ls, int hs) {
		_output_board = new boolean[_input_board.length-2][_input_board.length-2];
		for(int i = 1; i < _input_board.length - 1; i++) {
			for(int j = 1; j < _input_board.length - 1; j++) {
				int surrounding = numSurrounding(i, j);
				boolean alive = _input_board[i][j];
				_point_test = null;
				if(alive && (surrounding<ls || surrounding>hs)) {
					_output_board[i-1][j-1] = false;
				} else if (alive) {
					_output_board[i-1][j-1] = true;
				} else if (!alive && (surrounding>=lb && surrounding<=hb)) {
					_output_board[i-1][j-1] = true;
				} else {
					_output_board[i-1][j-1] = false;
				}
			}
		}
		notifyObservers();
	}
	public int numSurrounding(int i, int j) {
		_point_test = new boolean[3][3];
		_point_test[0][0] = _input_board[i-1][j-1];
		_point_test[0][1] = _input_board[i-1][j];
		_point_test[0][2] = _input_board[i-1][j+1];
		_point_test[1][0] = _input_board[i][j-1];
		//_point_test[1][1] = current spot
		_point_test[1][2] = _input_board[i][j+1];
		_point_test[2][0] = _input_board[i+1][j-1];
		_point_test[2][1] = _input_board[i+1][j];
		_point_test[2][2] = _input_board[i+1][j+1];
		int surrounding = 0;
		for(boolean row[] : _point_test) {
			for(boolean point : row) {
				if(point) {
					surrounding++;
				}
			}
		}
		return surrounding;
	}
	public void setInputBoard(boolean[][] board, boolean torus) {
		_input_board = new boolean[board.length+2][board.length+2];
		for(int i = 1; i<_input_board.length-1; i++) {
			for(int j = 1; j<_input_board.length-1; j++) {
				_input_board[i][j]=board[i-1][j-1];
			}
		}
		if(!torus) return;
		_input_board[0][0]=board[board.length-1][board.length-1];
		_input_board[0][board.length+1]=board[board.length-1][0];
		_input_board[board.length+1][0]=board[0][board.length-1];
		_input_board[board.length+1][board.length+1]=board[0][0];
		for(int i=1; i<board.length; i++) {
			_input_board[i][0] = board[i-1][board.length-1];
			_input_board[i][board.length+1] = board[i-1][0];
			_input_board[0][i] = board[board.length-1][i-1];
			_input_board[board.length+1][i] = board[0][i-1];
		}
	}
	public boolean[][] getOutputBoard() {
		return _output_board;
	}
	public void resetModelArrays() {
		_input_board = null;
		_output_board = null;
	}
}
