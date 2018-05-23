package paintproject;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape{
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		int x1=getPosition().x;
		int y1=getPosition().y;
		int x2=getWidth();
		int y2=getHeight();
		Color c=getColor();
		g.setColor(c);
        g.fillRect(x1,y1,x2,x2);
        g.setColor(Color.black);
		g.drawRect(x1,y1,x2,x2);
	
	
	}


}