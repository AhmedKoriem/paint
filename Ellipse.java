package paintproject;


import java.awt.Color;
import java.awt.Graphics;


public class Ellipse extends Shape {

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		int x1=getPosition().x;
		int y1=getPosition().y;
		int x2=getWidth();
		int y2=getHeight();
		
		Color c=getColor();
		g.setColor(c);
        g.fillOval(x1,y1,x2,y2);
        g.setColor(Color.BLACK);
		g.drawOval(x1,y1,x2,y2);
		
	}
	
}
