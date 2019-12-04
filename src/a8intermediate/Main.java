package a8intermediate;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
public static void main(String[] args) {
		
		LifeView view = new LifeView();
		LifeModel model = new LifeModel();
		LifeController controller = new LifeController(model, view);
		
		/* Create top level window. */
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conways Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create panel for content. Uses BorderLayout. */
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);

		top_panel.add(view, BorderLayout.CENTER);

		/* Pack main frame and make visible. */
		main_frame.pack();
		main_frame.setVisible(true);		
	}
}
