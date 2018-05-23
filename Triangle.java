package paintproject;

import java.awt.Color;
import java.awt.Graphics;


public class Triangle extends Shape {

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		int x1=getPosition().x;
		int y1=getPosition().y;
		int x2=getWidth();
		int y2=getHeight();
		Color c=getColor();
		int[] Xcoord = { x1, x1 + (x2), x1 -( x2 )};

        int[] Ycoord = { y1, y1+y2,y1+y2};
        g.setColor(c);

         g.fillPolygon(Xcoord, Ycoord,3);
         g.setColor(Color.black);
         g.drawPolygon(Xcoord,Ycoord,3);

		
		
	}
	
}
