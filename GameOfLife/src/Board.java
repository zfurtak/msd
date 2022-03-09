import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

/**
 * Board with Points that may be expanded (with automatic change of cell
 * number) with mouse event listener
 */

public class Board extends JComponent implements MouseInputListener, ComponentListener {
	private static final long serialVersionUID = 1L;
	private Point[][] points;
	private int size = 14;
	private int mode = 0;

	public Board(int length, int height) {
		addMouseListener(this);
		addComponentListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		setOpaque(true);
		initialize(length, height);
	}

	// single iteration
	public void iteration() {
		if(this.mode == 1){
			for(int i = 0; i < points.length; i++){
				points[i][0].drop();
			}
		}
		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y)
				points[x][y].calculateNewState();

		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y)
				points[x][y].changeState();
		this.repaint();
	}

	public void changeMode(int i){
		if(this.mode != i) {
			this.mode = i;
			clear();
			initialize(points.length, points[0].length);
		}
	}

	// clearing board
	public void clear() {
		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y) {
				points[x][y].setState(0);
			}
		this.repaint();
	}

	private void initialize(int length, int height) {
		points = new Point[length][height];

		for (int x = 0; x < points.length; ++x)
			for (int y = 0; y < points[x].length; ++y)
				points[x][y] = new Point(this.mode);

		for (int x = 0; x < points.length; ++x) {
			for (int y = 0; y < points[x].length; ++y) {
				if(this.mode == 0) {
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							if (x + i < points.length && y + j < points[0].length && x + i >= 0 && y + j >= 0) {
								if (i == 0 && j == 0) continue;
								points[x][y].addNeighbor(points[x + i][y + j]);
							}
						}
					}
				}

				if(this.mode == 1 &&  y - 1 >= 0){
					points[x][y].addNeighbor(points[x][y-1]);
				}
			}
		}
	}

	//paint background and separators between cells
	protected void paintComponent(Graphics g) {
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		g.setColor(Color.GRAY);
		drawNetting(g, size);
	}

	// draws the background netting
	private void drawNetting(Graphics g, int gridSpace) {
		Insets insets = getInsets();
		int firstX = insets.left;
		int firstY = insets.top;
		int lastX = this.getWidth() - insets.right;
		int lastY = this.getHeight() - insets.bottom;

		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}

		for (x = 0; x < points.length; ++x) {
			for (y = 0; y < points[x].length; ++y) {
				if (points[x][y].getState() != 0 && this.mode == 1) {
					switch (points[x][y].getState()) {
					case 1:
						g.setColor(new Color(0xB8CAE0));
						break;
					case 2:
						g.setColor(new Color(0x95ACC7));
						break;
					case 3:
						g.setColor(new Color(0x76A0CE));
						break;						
					case 4:
						g.setColor(new Color(0x5396E3));
						break;						
					case 5:
						g.setColor(new Color(0x3277CE));
						break;						
					case 6:
						g.setColor(new Color(0x0762CE));
						break;						
					}
					g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
				}else if (points[x][y].getState() != 0 && this.mode == 0) {
					switch (points[x][y].getState()) {
						case 1:
							g.setColor(new Color(0xD391B7));
							break;
						case 2:
							g.setColor(new Color(0xA95686));
							break;
						case 3:
							g.setColor(new Color(0xE02994));
							break;
						case 4:
							g.setColor(new Color(0x6E3556));
							break;
						case 5:
							g.setColor(new Color(0xF146AB));
							break;
						case 6:
							g.setColor(new Color(0xC70073));
							break;
					}
					g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
				}
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
			points[x][y].clicked();
			this.repaint();
		}
	}

	public void componentResized(ComponentEvent e) {
		int dlugosc = (this.getWidth() / size) + 1;
		int wysokosc = (this.getHeight() / size) + 1;
		initialize(dlugosc, wysokosc);
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
			points[x][y].setState(1);
			this.repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

}
