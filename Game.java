package pathfinding2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	static Game instance;
	int height = 20, width = 20;
	Node[][] nodes = new Node[50][50];
	ArrayList<Node> path = new ArrayList<Node>();
	boolean isChosenS, isChosenE;
	Node start, end;
	Domain s = new Domain();

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	private Game() {
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocusInWindow();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		for (int r = 0; r < nodes.length; r++) {
			for (int c = 0; c < nodes[r].length; c++) {
				nodes[r][c] = new Node(r, c);
			}
		}
		// getEdges
		for (int r = 0; r < nodes.length; r++) {
			for (int c = 0; c < nodes[r].length; c++) {
				nodes[r][c].getEdges(nodes);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int r = 0; r < nodes.length; r++) {
			for (int c = 0; c < nodes[r].length; c++) {
				if (nodes[r][c].visited) {
					g2d.setColor(new Color(0,128,255));
					g2d.fillRect(c * width, r * height, width, height);
				}
				if (nodes[r][c].openList && !nodes[r][c].visited) {
					g2d.setColor(new Color(102,178,255));
					g2d.fillRect(c * width, r * height, width, height);
				}
				if (nodes[r][c].isStart) {
					g2d.setColor(Color.white);
					g2d.fillRect(c * width, r * height, width, height);
				}
				if (nodes[r][c].isEnd) {
					g2d.setColor(Color.white);
					g2d.fillRect(c * width, r * height, width, height);
				}
				if (nodes[r][c].isObstacle) {
					g2d.setColor(new Color(255,0,127));
					g2d.fillRect(c * width, r * height, width, height);
				}
				if (nodes[r][c].inPath) {
					g2d.setColor(new Color(128,255,0));
					g2d.fillRect(c * width, r * height, width, height);
				}
			}
		}
		// grid
		g2d.setColor(new Color(110,110,110));
		for (int r = 0; r < nodes.length; r++)
			g2d.drawLine(0, r * height, 800, r * height);
		for (int c = 0; c < nodes[0].length; c++)
			g2d.drawLine(c * width, 0, c * width, 800);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int r = e.getY() / height;
		int c = e.getX() / width;
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (r >= 0 && r < nodes.length && c >= 0 && c < nodes[0].length) {
				if (!isChosenS) {
					start = nodes[r][c];
					start.isStart = true;
					isChosenS = true;
				} else if (isChosenS && !isChosenE && !nodes[r][c].isStart) {
					end = nodes[r][c];
					end.isEnd = true;
					isChosenE = true;
				}
			}
		}
		repaint();
	}

	public void reset() {
		for (int r = 0; r < nodes.length; r++) {
			for (int c = 0; c < nodes[r].length; c++) {
				nodes[r][c] = new Node(r, c);
			}
		}
		// getEdges
		for (int r = 0; r < nodes.length; r++) {
			for (int c = 0; c < nodes[r].length; c++) {
				nodes[r][c].getEdges(nodes);
			}
		}
		start = null;
		end = null;
		isChosenS = false;
		isChosenE = false;
		s = new Domain();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int r = e.getY() / height;
		int c = e.getX() / width;
		if (isChosenS && isChosenE && !nodes[r][c].isStart && !nodes[r][c].isEnd && !nodes[r][c].isObstacle)
			nodes[r][c].isObstacle = true;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_U)
			s.UCS(start, end);
		if (e.getKeyCode() == KeyEvent.VK_G)
			s.Greedy(start, end);
		if (e.getKeyCode() == KeyEvent.VK_A)
			s.AStar(start, end);
		if (e.getKeyCode() == KeyEvent.VK_R)
			reset();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

