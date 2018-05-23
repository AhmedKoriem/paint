package paintproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
 
public abstract class Shape {
	 Point position = new Point(0, 0);
	  private Color color;
	   
	     public Color getColor()
	     {
	         return color;
	     }
	     

	     public void setColor(Color value)
	     {
	        color = value;
	     }
	     
	     
	
	     public Point getPosition()
	     {
	         return position;
	     }
	     
	  
	     public void setPosition(Point value)
	     {
	        // if(value == null) throw new IllegalArgumentException("Cannot set position to a null point.");
	         position = value;
	     }
	     
	     private int width;
	 
	 
	     public int getWidth()
	     {
	         return width;
	     }
	     public void moveby(int dx,int dy)
	     {
	    	 position.x+=dx;
	    	 position.y+=dy;
	     }
	     
	     
	     public void setWidth(int value)
	     {
	        
	         width = (value);
	     }
	     
	     private int height;
	 
	   
	     public int getHeight()
	     {
	         return height;
	     }
	     
	   
	     public void setHeight(int value)
	     {
	         //if(value < 1) throw new IllegalArgumentException("Cannot set height to 0 or a negative value.");
	         height = value;
	     }
	 
	 
	     public boolean isAt(Point point)
	     {
	         return point.getX() >= getPosition().getX() && 
	             point.getX() < getPosition().getX() + getWidth() && 
	             point.getY() >= getPosition().getY() && 
	             point.getY() < getPosition().getY() + getHeight();
	     }
	 
	    
	     public abstract void draw(Graphics g);
 }

