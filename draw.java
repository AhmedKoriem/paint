package paintproject;

import com.thoughtworks.xstream.XStream;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.BLUE;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.*;
/**
 *
 * @author Amir
 */
public class draw  extends JFrame implements ActionListener, MouseListener,MouseMotionListener {
                Stack<List<Shape>> undostack = new Stack<List<Shape>>();
		Stack<List<Shape>> redostack = new Stack<List<Shape>>();
                XStream xstream = new XStream();

    /**
     *
     * @return
     */
    public static String fileChooser()
		{
		    JFileChooser fc= new JFileChooser();
		    int ret = fc.showOpenDialog(null);

		               if (ret== JFileChooser.APPROVE_OPTION) 
		               {
		             File file = fc.getSelectedFile();
		             String filename= file.getAbsolutePath();
		             return filename;
		            }

		           else
		             return null;
		 }
             
   int xvalue;
  int yvalue;
    int x1;
    int y1;
    int x2;
    int y2;

    /**
     *
     */
    public Color colfg=Color.WHITE;

    /**
     *
     */
    public Color fill=Color.WHITE;
    public Color colbg;
    Shape moving;
    Shape resize;
    boolean resizeLeft ;
    boolean resizeRight;
    boolean resizeTop ;
    boolean resizeBottom;
    Shape selected;
    
     private List<Shape> shapes = new ArrayList<Shape>();
     
     Point drawStart=new Point(), drawEnd=new Point();

	    public static JLabel xlab = new JLabel("0");
	    public static JLabel ylab = new JLabel("0");
	     static String shapeType = new String();
	     String strshape = null;
                        
            static JFrame frame1 = new JFrame();            
	    static JButton quitbutton = new JButton("Quit");
	    static JButton infobutton = new JButton("Info");
	    static JButton clearbutton = new JButton("Clear");
	    static JButton savebutton = new JButton("Save");
	    static JButton loadbutton = new JButton("load");
	    static JButton redo = new JButton("Redo");
	    static JButton undo = new JButton("Undo");
	    static JButton delete = new JButton("Delete");
            
             static Icon Triangle = new ImageIcon("triangle.jpg");
	    static final JButton triangle = new JButton(Triangle);
            
	    static Icon Circle = new ImageIcon("circle.jpg");
	    static JButton circle = new JButton(Circle);
            
	    static Icon Square = new ImageIcon("square.jpg");
	    static JButton square = new JButton(Square);
	    
	    static Icon Rectangle= new ImageIcon("rectangle.jpg");
	    static JButton rectangle = new JButton(Rectangle);
	    
	    static Icon Ellipse = new ImageIcon("oval.jpg");
	    static JButton ellipse = new JButton(Ellipse);
            
	    static Icon Line = new ImageIcon("line.jpg");

	    static JButton line = new JButton(Line);

	    static JButton selectcolor = new JButton("Select Color");
	    static JButton selectbg = new JButton("Select Background");
	    public static Color col1 = Color.WHITE;
	    private static Color col2 = Color.WHITE;
	    static JPanel panel2 = new JPanel();
	    static JPanel panel1 = new JPanel();
            
             void addPanel1(){

		       panel1.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
	         panel1.setBackground(Color.WHITE);
	     }
              public  void addPanel2(){
		
		  panel2.setBackground(Color.WHITE.getHSBColor((float) 0.5,(float) 0.05,1));
		  panel2.add(new JLabel("Select Shape  "));
                  
	          panel2.add(line);
                 panel2.add(rectangle);
	    	 panel2.add(square);
	    	 panel2.add(ellipse);
	    	 panel2.add(circle);
                 panel2.add(triangle);
                 
	    
	    	 
	    	 
	         panel2.add(selectbg);
	         panel2.add(selectcolor);
	    

	    	 
	    	    triangle.setActionCommand("Triangle");
	    	    ellipse.setActionCommand("Ellipse");
	    	   circle.setActionCommand("Circle");
	    	    line.setActionCommand("Line");
	    	   square.setActionCommand("Square");
	    	    rectangle.setActionCommand("Rectangle");
	            circle.setBackground(Color.WHITE);
	            triangle.setBackground(Color.WHITE);
	            rectangle.setBackground(Color.WHITE);
	            square.setBackground(Color.WHITE);
	            ellipse.setBackground(Color.WHITE);
	            line.setBackground(Color.WHITE);


	    	   
		         panel2.add(delete);

	            panel2.add(undo);
	            panel2.add(redo);
		 

		    	 
	    	 panel2.add(savebutton);
	    	 panel2.add(loadbutton);
	         panel2.add(clearbutton);
	         panel2.add(quitbutton);
	  
	       
	        
	        
	         quitbutton.addActionListener(this);
	         infobutton.addActionListener(this);
	         clearbutton.addActionListener(this);
	         savebutton.addActionListener(this);
	         line.addActionListener(this);
	         rectangle.addActionListener(this);
	         triangle.addActionListener(this);
	         square.addActionListener(this);
	         ellipse.addActionListener(this);
	         circle.addActionListener(this);
	  
	         selectcolor.addActionListener(this);
	         selectbg.addActionListener(this);
	         delete.addActionListener(this);
                 undo.addActionListener(this);
	         redo.addActionListener(this);
                 loadbutton.addActionListener(this);
	    	 
	    }
              
              
              
                public draw() {
	        this.setTitle("el program el fashee5");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        // add check box group
	        addPanel2();
	        addPanel1();

	        panel2.setPreferredSize(new Dimension(1340,70));
	        panel1.setPreferredSize(new Dimension(1340,650));
	        


	        this.addMouseListener(this);
	        this.setLayout(new BorderLayout());
	        this.add(panel2, BorderLayout.NORTH);
	       
	        this.add(panel1,BorderLayout.CENTER);
	        this.addMouseMotionListener(this);
	    }
                
                @Override
		public void mouseDragged(MouseEvent me) {
			// TODO Auto-generated method stub
			drawEnd = new Point(me.getX(),me.getY());
		
	 		 int dx=drawEnd.x-drawStart.x;
	 		 int dy=drawEnd.y-drawStart.y;
	 		if(moving!=null)
	 		 {
	 			 selected.moveby(dx,dy);
	 			 drawStart=me.getPoint();
                                 repaint();
	 		 }
	 		 if (resizeLeft) {
	 		      selected.position.x+=dx;
	 		      selected.setWidth(selected.getWidth()-dx);
	 		     drawStart=me.getPoint();
	 		      }
	 		 if (resizeRight) {
	 			selected.setWidth(selected.getWidth()+dx);
	 		    drawStart=me.getPoint();
	 		      }
	 		 if (resizeTop) {
	 		      selected.position.y+=dy;
	 		      selected.setHeight(selected.getHeight()-dy);
	 		    drawStart=me.getPoint();
	 		      }
	 		 if (resizeBottom) { 
	 			selected.setHeight(selected.getHeight()+dy) ;
	 		     drawStart=me.getPoint();
	 		      }
	         repaint();
 
              
}
                 public void changeColor(){
		    	//see if selected change color attribute
		    	//change current color
		    	
		        fill = JColorChooser.showDialog(this, "Select color", fill);
		        if(selected!=null && drawStart==null && drawEnd==null)
		        {
		        	selected.setColor(fill);
		        }
		        repaint();
		    }
		    public void changeBg(){
		        colbg=JColorChooser.showDialog(this, "Select color", colbg);
		        panel1.setBackground(colbg);
		        repaint();
}
                    public void remove(){
		    	shapes.remove(selected);
		    	repaint();
		    }
           
		 public void mouseMoved(MouseEvent z) {
	        xvalue = z.getX();
	        yvalue = z.getY();
	        String xvalueret = Integer.toString(xvalue);
	        String yvalueret = Integer.toString(yvalue);
	        draw.xlab.setText(" " + xvalueret + " ");
	        draw.ylab.setText(" " + yvalueret + " ");
	
	    }
            
		public void mouseClicked(MouseEvent z) {
			int flag=1;
	 		selected=null;
	 		strshape=null;
         for (Shape p : shapes) {
	    		 
                 if (p.isAt(z.getPoint()) && selected==null) {
                	 selected=p;
                	 strshape=p.toString();
                	 flag=0;
                         
                 }
                   }
         if(flag==1)
        	 selected=null;
         strshape=null;
         
         repaint();
			// TODO Auto-generated method stub
			
		}

	
		public void mouseEntered(MouseEvent z) {
			// TODO Auto-generated method stub
			
		}
         
		public void mouseExited(MouseEvent z) {
			// TODO Auto-generated method stub
			
		}

	
		public void mousePressed(MouseEvent me) {
			// TODO Auto-generated method stub
			List<Shape> newList = new ArrayList<Shape>();
			newList.addAll(shapes);
 	        undostack.push(newList);
			drawStart = new Point(me.getX(),me.getY());
	         drawEnd = drawStart;
	    	 int flag=0;
	    	 for (Shape p : shapes) {
	    		 
                 if (p.isAt(me.getPoint())) {
                	 
                	 if (me.getX()>= p.getPosition().x-5 && me.getX()<=p.getPosition().x+p.getWidth()+5 &&
                		        me.getY()>=p.getPosition().y-5 && me.getY()<=p.getPosition().y+p.getHeight()+5) {
                		 
                		 if (me.getX()<=p.getPosition().x+10){ resizeLeft = true;resize=p;}
                	      else if (me.getX()>=p.getPosition().x+p.getWidth()-10) {resizeRight = true;resize=p;}
                	      else if (me.getY()<=p.getPosition().y+10){ resizeTop = true;resize=p;}
                	      else if (me.getY()>=p.getPosition().y+p.getHeight()-10){ resizeBottom = true;resize=p;}
                	      else moving=p;
                 
                	 }
                     flag=1;
                    drawStart = me.getPoint();
                     break;
                 }
                 
             }
	    	 if(flag==0)
		 		drawStart = new Point(me.getX(),me.getY());
		         drawEnd = drawStart;
		        repaint();
		      
		}
                
	
		public void mouseReleased(MouseEvent me) {
	 		// TODO Auto-generated method stub

	 		Shape s=null;
	 	        if (shapeType.equals("Rectangle")) {
	 	        
	 	        	x1=drawStart.x;y1=drawStart.y;
	 	    	    x2=me.getX();y2=me.getY();
	 	    	    Point xx=new Point();
	 	    	    xx.x=Math.min(x1,x2);
	 	    	    xx.y=Math.min(y1,y2);
	 	    	   s = new Rectangle();
	 	    	    s.setPosition(xx);
	 	    	    s.setWidth(Math.abs(x1-x2));
	 	    	    s.setHeight(Math.abs(y1-y2));
                            s.setColor(colfg);
                            
	 	    	   
	 	         
	 	        } 
	 	        else if(shapeType.equals("Ellipse"))
	 	        {
	 	        
	 	        	x1=drawStart.x;
	 	        	y1=drawStart.y;
	 	    	    x2=me.getX();y2=me.getY();
	 	    	   Point xx=new Point();
	 	    	    xx.x=Math.min(x1,x2);
	 	    	    xx.y=Math.min(y1,y2);
	 	    	   s = new Ellipse();
	 	    	    s.setPosition(xx);
                               s.setColor(colfg);

	 	    	    s.setWidth(Math.abs(x1-x2));
	 	    	    s.setHeight(Math.abs(y1-y2));
	 	           
	 	          
	 	        }
	 	       else if(shapeType.equals("Triangle"))
	 	        {
	 	        
	 	        	x1=drawStart.x;
	 	        	y1=drawStart.y;
	 	    	    x2=me.getX();y2=me.getY();
	 	    	   Point xx=new Point();
	 	    	    xx.x=Math.min(x1,x2);
	 	    	    xx.y=Math.min(y1,y2);
	 	    	   s = new Triangle();
	 	    	    s.setPosition(xx);
                               s.setColor(colfg);

	 	    	    s.setWidth(Math.abs(x1-x2));
	 	    	    s.setHeight(Math.abs(y1-y2));
	 	           
	 	          
	 	        }
	 	        else if(shapeType.equals("Line"))
	 	        {
	 	    	    s = new Line();

	 	    	    s.setPosition(drawStart);
	 	    	    s.setWidth(drawEnd.x);
	 	    	    s.setHeight(drawEnd.y);
                               s.setColor(colfg);
	 	         
	 	        }
	 	        else if (shapeType.equals("Square")) {
		 	        
	 	        	x1=drawStart.x;y1=drawStart.y;
	 	    	    x2=me.getX();y2=me.getY();
		 	    	  

	 	    	    Point xx=new Point();
	 	    	    xx.x=Math.min(x1,x2);
	 	    	    xx.y=Math.min(y1,y2);
	 	    	   s = new Square(); 
	 	    	    s.setPosition(xx);
	 	    	    s.setWidth(Math.abs(x1-x2));
	 	    	    s.setHeight(Math.abs(x1-x2));
	 	    	      s.setColor(colfg);
	 	        } 

	 	       else if(shapeType.equals("Circle"))
	 	        {
	 	        
	 	        	x1=drawStart.x;y1=drawStart.y;
	 	    	    x2=me.getX();y2=me.getY();
	 	    	   Point xx=new Point();
	 	    	    xx.x=Math.min(x1,x2);
	 	    	    xx.y=Math.min(y1,y2);
	 	    	   s = new Circle();
	 	    	    s.setPosition(xx);
	 	    	    s.setWidth(Math.abs(x1-x2));
	 	    	    s.setHeight(Math.abs(x1-x2));  
	 	             s.setColor(colfg);
	 	        }
                         if (s != null) {
	 	            shapes.add(s);
	 	            drawStart = null;
	 	           drawEnd = null;
	 	         
                           shapes.add(s);
	 	           drawStart = null;
	 	           drawEnd = null;
	 	            moving=null;
	 	            resize=null;
	 	            resizeLeft = false;
	 	            resizeRight = false;
	 	            resizeTop = false;
	 	            resizeBottom = false;
                            
	 	           repaint();
	 	        }
	 	repaint();	
	 	}
         
                @Override
		public void actionPerformed(ActionEvent x) {
			// TODO Auto-generated method stub
            if (x.getSource()==quitbutton){
                System.exit(0);
            }
            
            else if(x.getSource()==selectcolor){
 
                changeColor();
 
            }
            else if(x.getSource()==selectbg){
            	changeBg();
            	 
            }
            else if(x.getSource()==delete){
            	remove();
            	remove();
           
                
            	}
             else if(x.getSource()==savebutton){
            	String xml = xstream.toXML(shapes);
            	
            	JFrame parentFrame = new JFrame();
            	 
            	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setDialogTitle("Specify a file to save");   
            	 
            	int userSelection = fileChooser.showSaveDialog(parentFrame);
            	 
            	if (userSelection == JFileChooser.APPROVE_OPTION) {
            	    File fileToSave = fileChooser.getSelectedFile();
            	    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            	    String path = fileToSave.getAbsolutePath();
        	    	path = path.replaceAll("\\\\", "\\\\\\\\");
        	    	//System.out.println(path);
            	    try{
            	    	
            	        Writer output = null;
            	        File file = new File(path);
            	        output = new BufferedWriter(new FileWriter(file));

            	        output.write(xml);

            	        output.close();
            	        System.out.println("File has been written");

            	    }catch(Exception e){
            	        System.out.println("Could not create file");
            	    }
                    repaint();
            	}
            	
            	
            }
            else if(x.getSource()==loadbutton){

            	String path = fileChooser();
    	    	path = path.replaceAll("\\\\", "\\\\\\\\");
                

            	try {
					BufferedReader in =new BufferedReader(new FileReader(path));
					StringBuilder out = new StringBuilder();
			        String line;
			        while ((line = in.readLine()) != null) {
			            out.append(line);
			        }
			       String xml = out.toString();
					shapes = (List<Shape>) xstream.fromXML(xml);
					repaint();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
            	
                                }
                repaint();
            }
        
               else if(x.getSource()==undo){
                   
         
            	if(!undostack.isEmpty()){
            		List<Shape> ff = new ArrayList<Shape>();
            		ff.addAll(shapes);
         	        redostack.push(ff);
            		//shapes = undostack.peek();
            		List<Shape> nn = new ArrayList<Shape>();
        			nn.addAll(undostack.pop());
        			shapes.clear();
        			shapes.addAll(nn);

            		repaint();
                        
            	}
            	 
            }
            else if(x.getSource()==redo){
 

            	if(!redostack.isEmpty()){ 
            		List<Shape> cc = new ArrayList<Shape>();
            		cc.addAll(shapes);
         	        undostack.push(cc);
            		List<Shape> ff = new ArrayList<Shape>();
            		ff.addAll(redostack.pop());
            		shapes.clear();
            		shapes.addAll(ff);
            		repaint();

            	}
            }
            else if(x.getSource()==clearbutton){

                panel1.setForeground(null);
                shapes.clear();
 
            }
	 		shapeType = x.getActionCommand();
                }
                
          
                public void paint(Graphics g) {
	        super.paintComponents(g);
	        Graphics2D g2 = (Graphics2D)g; 
	       // g.setColor(colfg);
	       // Triangle t = new Triangle();

	        for (Shape shape : shapes) { 
	          shape.draw(g);
                  
	        }
	        if(selected!=null && drawStart==null && drawStart==null)
	        {
	        	float dash1[] = {8.0f};
	            BasicStroke dashed =new BasicStroke(3.0f,
	                                BasicStroke.CAP_BUTT,
	                                BasicStroke.JOIN_MITER,
	                                10.0f, dash1, 0.0f);
	        g2.setStroke(dashed);

	        g2.draw(new RoundRectangle2D.Double(selected.position.x-5,selected.position.y-5,
	                                           selected.getWidth()+10,
	                                           selected.getHeight()+10,
	                                    10, 10));
                 if(selected.toString().contains("Triangle")){
	        	
	        	 g2.draw(new RoundRectangle2D.Double(selected.position.x-(selected.getWidth())-5,selected.position.y-5,
                         2*selected.getWidth()+10,
                         selected.getHeight()+10,
                  10, 10));
	        }
	        else{
	        
	        g2.draw(new RoundRectangle2D.Double(selected.position.x-5,selected.position.y-5,
	                                           selected.getWidth()+10,
	                                           selected.getHeight()+10,
	                                    10, 10));
	        }

	        }

	        if (drawStart != null && drawStart != null) {
	        	 x1=drawStart.x;y1=drawStart.y;
	      	    x2=drawEnd.x;y2=drawEnd.y;
	      	  Point xx=new Point();
 	    	    xx.x=Math.min(x1,x2);
 	    	    xx.y=Math.min(y1,y2);
 	    	    Shape r=null;
	   
	 	        if (shapeType.equals("Rectangle")) {
	 	        	
	 	        	 r=new Rectangle();
	 	        	 r.setPosition(xx);
		 	    	 r.setWidth(Math.abs(x1-x2));
		 	    	 r.setHeight(Math.abs(y1-y2));
		 	    	// r.setColor(colfg);
	 	             r.draw(g);
	 	        
	 	        } 
	 	        else if(shapeType.equals("Ellipse"))
	 	        {
	 	      
	 	        	  r=new Ellipse();
	 	        	  r.setPosition(xx);
			 	      r.setWidth(Math.abs(x1-x2));
			 	      r.setHeight(Math.abs(y1-y2));
			 	    //	 r.setColor(colfg);

	 	              r.draw(g);;
	 	    
	 	        }
	 	        
	 	        else if(shapeType.equals("Line"))
	 	        {
	 	        	    r=new Line();
	 	        	    r.setPosition(drawStart);
			 	    	r.setWidth(x2);
			 	    	r.setHeight(y2);
			 	   //  r.setColor(colfg);

	 	                r.draw(g);;
	 	     
	 	        }
	  	       if (shapeType.equals("Square")) {
	 	        	
	 	        	 r=new Square();
	 	        	 r.setPosition(xx);
		 	    	 r.setWidth(Math.abs(x1-x2));
		 	    	 r.setHeight(Math.abs(x1-x2));
                         //  r.setColor(colfg);
	 	             r.draw(g);
	 	        
	 	        } 

	 	       else if(shapeType.equals("Circle"))
	 	        {
	 	      
	 	        	  r=new Circle();
	 	        	  r.setPosition(xx);
			 	      r.setWidth(Math.abs(x1-x2));
			 	      r.setHeight(Math.abs(x1-x2));
                               //  r.setColor(colfg);
	 	              r.draw(g);
	 	    
	 	        }
	 	      else if(shapeType.equals("Triangle"))
	 	        {
	 	      
	 	        	  r=new Triangle();

	 	        	  r.setPosition(xx);
			 	      r.setWidth(Math.abs(x1-x2));
			 	      r.setHeight(Math.abs(x1-x2));
                         //  r.setColor(colfg);
	 	              r.draw(g);
	 	    
	 	        }
	          }
		}
}

            	

                