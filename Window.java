package pathfinding2;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame{
	private final int WIDTH = 802, HEIGHT= 802;
	private final String TITLE= "Pathfinding";
	
	public Window() {
		add(Game.getInstance());
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setResizable(false);
		setVisible(true);
		pack();
		pack();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Window();
			}
		});
		printInstructions();
	}
	
	private static void printInstructions() {
		System.out.println("WHITE Node: Start/End");
		System.out.println("BLUE Node: Checked Node");
		System.out.println("GREEN Node: Optimized Path");
		System.out.println("KEY A: Use A* Algorithm");
		System.out.println("KEY U: Use Uniform Cost Search")
		System.out.println("KEY G: Use Greedy Search");
		System.out.println("KEY C");
	}
}
