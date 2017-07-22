package integral;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	public static final double Increment = 1E-4;
	static boolean drawConnectingLine = true;
	public static void main(String[] args) {
		System.out.println(integral(5,2, x-> {
		 return Math.sin(90);
		}));
		 SwingUtilities.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                JFrame frame = new JFrame("Test");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	                Sinusoid s = new Sinusoid();
	                s.setPreferredSize(new Dimension(600, 600));
	                frame.getContentPane().add(s);
	  
	                frame.pack();
	                frame.setVisible(true);
	            }
	        });
	}
	
	public static double integral(double a, double b, Function function){
		double area = 0;
		double modifier = 1;
		if(a > b){
			double tempA = a;
			a = b;
			b = tempA;
			modifier = -1;
		}
		for(double i = a + Increment ; i <= b; i+=Increment){
			double dFromA = i - a;
			area += (Increment / 2) * (function.f(a + dFromA) + function.f(a + dFromA - Increment));
			
		}
		return((Math.round(area * 1000.0)) / 1000.0) * modifier;
	}
	
	 static class Sinusoid extends JPanel
	    {
	        int previousY = 0;
	 
	        double degToRad (int deg)
	        {
	            return ((2*Math.PI)/360.0) * deg;
	        }
	 
	        int scale (int i, int width)
	        {
	            return (int) ((i/(double)width)*720);
	        }
	 
	        public void paintComponent (Graphics g)
	        {
	            super.paintComponent(g);
	            int width = getWidth();
	            int height = getHeight();
	        	g.drawLine(0,height/2,width,height/2); // x-axis
	            g.drawLine(width/2,0,width/2,height); // y-axis
	      
	            g.setColor(Color.red);
	            for (int i=0; i<width; i++)
	            {
	                int y = (int) Math.round((-Math.sin(degToRad(scale(i,width)))+1)*height/2.0);
	                if (drawConnectingLine && i>0)
	                {
	                    g.drawLine(i-1, previousY, i, y);
	                } else {
	                    g.drawLine(i, y, i, y);
	                }
	                previousY = y;
	            }
	        }
	    }

}
