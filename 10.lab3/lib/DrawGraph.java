import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* A class for drawing a graph.
* ( it's really only drawing text and lines and have no idea what it is drawing)
* @author Original map drawing is thanks to Tommi Kerola and Marcus Johansson IT students 2010
* @author changes by Erland Holmström 2010
* @author rewritten 2011 by Jesper Lloyd
* @TODO måste kunna rita riktade grafer
* "version 2012-02
*/
public class DrawGraph extends JFrame {
	
	private GraphLayer base;
	private GraphLayer overlay;
	
	public enum Layer{BASE, OVERLAY};
	
	public DrawGraph() {
		this(1300, 750); // passar bra för vårt linjenät
	}
	
	public DrawGraph(int width, int height) {
		Dimension windSize = new Dimension(width, height);
		setPreferredSize(windSize);
		base = new GraphLayer();
		base.setSize(windSize);
		base.setLayout(null);
		overlay = new GraphLayer();
		overlay.setOpaque(false);
		overlay.setSize(windSize);
		overlay.setLayout(null);
		getContentPane().add(base);
		base.add(overlay);
		pack();
		setVisible(true);
	}
	
	public void drawString(String text, int x, int y, Layer l) {
		x = scaleX(x)+10;
		y = scaleY(y)-10;
		JLabel newLabel = new JLabel(text);
		newLabel.setSize(150, 18);
		newLabel.setLocation(x, y);
		newLabel.setFont(newLabel.getFont().deriveFont(8));//.deriveFont(Font.BOLD));
		base.add(newLabel);
		base.shapes.add(new CWEllipse2D(x - 11, y + 8, 3, 3, 2, Color.BLACK));
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, Color color, double width, Layer l) {
		x1 = scaleX(x1);
		y1 = scaleY(y1);
		x2 = scaleX(x2);
		y2 = scaleY(y2);
		CWLine2D line = new CWLine2D(x1, y1, x2, y2, width, color);
		
		if(l == Layer.BASE)
			base.shapes.add(0,line); //Make sure lines are not drawn over stops
		else{
			overlay.shapes.add(line);
			overlay.shapes.add(new CWEllipse2D(x1-1, y1-2, 3, 3, width, color));
		}	
	}
	
	public void clearLayer(Layer l){
		if(l == Layer.BASE)
			base.shapes.clear(); //Never used in program
		else
			overlay.shapes.clear();
	}
	
	private int scaleX(int x) {
		return (int)(x*4)+10;
	}
	
	private int scaleY(int y) {
		return (int)(y*1.5) + 11;
	}
	
	//Fixing repainting of paths and nodes.
	private class GraphLayer extends JPanel{

		private List<SpecShape> shapes =  new LinkedList<SpecShape>();
		
		@Override
		protected void paintComponent(Graphics g){
			for(SpecShape s: this.shapes){
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(s.getColor());
				g2.setStroke(new BasicStroke((float)s.getWidth()));
				g2.draw(s);
			}
		}
	}
	
	//Line shape knowing color and width (stupid solution)
	private class CWLine2D extends Line2D.Float implements SpecShape{
		
		private Color color;
		private double lwidth;
		
		CWLine2D(float x1, float y1, float x2, float y2, double width, Color c){
			super(x1, y1, x2, y2);
			color = c;
			this.lwidth = width;
		}

		@Override
		public Color getColor() {
			return color;
		}

		@Override
		public double getWidth() {
			return lwidth;
		}
		
	}

	//Ellipse knowing color and width (stupid solution)
	private class CWEllipse2D extends Ellipse2D.Float implements SpecShape{
		
		private Color color;
		private double lwidth;
		
		CWEllipse2D(float x, float y, float width, float height, double lwidth, Color c){
			super(x, y, width, height);
			color = c;
			this.lwidth = lwidth;
		}

		@Override
		public Color getColor() {
			return color;
		}

		@Override
		public double getWidth() {
			return lwidth;
		}
		
	}
	
	//Shape width color and line width;
	private interface SpecShape extends Shape{
		
		public Color getColor();
		public double getWidth();
	}
	
}
