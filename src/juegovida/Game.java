package juegovida;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener, MouseMotionListener {
	
	private static final int CELL_SIZE = 15;
	private static final int GRID_SIZE = 30;
	
	private boolean[][] matriz;
	
	private int width = GRID_SIZE;
	private int height = GRID_SIZE;
	
	private int speed = 1;
	
	public Game() {
		initMouseListeners();
		initMatriz();
	}
	
	public void setCellAlive(int row, int col, boolean alive) {
		matriz[row][col] = alive;
	}
	
	public void nextIteration() {
		for (int s = 0; s < speed; s ++)
		for (int i=1; i<width-1; i++)
			for (int j=1; j<height-1; j++) {
				// Count the number of live neighbours.
				int count = 0;
				if (matriz[i-1][j-1]) count++;
				if (matriz[i  ][j-1]) count++;
				if (matriz[i+1][j-1]) count++;
				if (matriz[i-1][j  ]) count++;
				if (matriz[i+1][j  ]) count++;
				if (matriz[i-1][j+1]) count++;
				if (matriz[i  ][j+1]) count++;
				if (matriz[i+1][j+1]) count++;

				if (matriz[i][j])
					// A living cell will stays alive only if it
					// has exactly 2 or 3 living neighbours.
					matriz[i][j] = count==2 || count==3;
				else
					// A dead cell will only come back to life
					// if it has exactly 3 living neighbours.
					matriz[i][j] = count==3;
			}
		repaint();
	}
	
	@Override
	 public void mouseClicked(MouseEvent e) {  
	    }  
	
	 

	@Override
	public void mousePressed(MouseEvent e) {
		handleMouseEvent(e);
	}

	private void handleMouseEvent(MouseEvent e) {
		int cellX = e.getX()/CELL_SIZE;
		int cellY = e.getY()/CELL_SIZE;
		if (cellX >= 0 && cellX <width && cellY >=0 && cellY <height) {
			setCellAlive(cellX, cellY, true);
			Game.this.repaint();
		}
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int x = 0; x<width ; x++)
		for(int y = 0; y<height ; y++)
		if (matriz[x][y]) {
			g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
		} else {
			g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		handleMouseEvent(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void setWidthHeight(int w, int h) {
		this.width = w;
		this.height = h;
		initMatriz();
		repaint();
	}
	
	public void clear() {
		initMatriz();
		repaint();
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public boolean[][] getMatriz() {
		return this.matriz;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void initMouseListeners() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void initMatriz() {
		matriz = new boolean[width][height];
	}
}
