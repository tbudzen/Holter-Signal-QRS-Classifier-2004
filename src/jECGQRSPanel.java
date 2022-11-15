/*
 * Created on 2006-04-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * Created on 2006-02-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

	public class jECGQRSPanel extends JPanel
	{
		jECG ecg;		
		BufferedImage off;
		QRSInfoNode startingNode;
		
		public jECGQRSPanel(jECG e)
		{
			ecg = e;
		}
		
		public void paintComponent(Graphics g)
		{
			int i, w, h, hHalf, cOffset, y0, y1;
			byte [] d;
			Graphics offG;
			QRSInfoNode currentNode;
			int qx_0, qx_R, qy_0, qW, qH, w1, w2;
			String s1, s2;
		
			super.paintComponent(g);

			FontMetrics fm = g.getFontMetrics();
			   
			Color whiteColor 	= new Color((float) 1.00, (float) 1.00, (float) 1.00);
			Color qrsColor  	= new Color((float) 0.95, (float) 0.90, (float) 0.80);
			Color qrsRColor 	= new Color((float) 0.30, (float) 0.30, (float) 0.30);
			Color dataColor	 	= new Color((float) 0.30, (float) 0.50, (float) 0.70);
			Color signalColor	= new Color((float) 0.70, (float) 0.50, (float) 0.30);
		    Color axisColor 	= new Color((float) 0.30, (float) 0.70, (float) 0.40);
			
		    w = getWidth();
		    h = getHeight();
			hHalf = h / 2;
			
			// cOffset validation

			if(ecg.currentOffset > 0 && ecg.currentOffset > ecg.mainChannel[0].size - ecg.pSignal.getWidth()) 
			{
				ecg.currentOffset = ((int) ((int) ecg.mainChannel[0].size - ecg.pSignal.getWidth()) / 100) * 100;
			}
			
		    cOffset = ecg.currentOffset;
		    
			off = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			offG = off.getGraphics();
			offG.setColor(Color.WHITE);
			offG.fillRect(0, 0, w, h);
		    
		    // Graphics2D g2 = (Graphics2D) offG;		    
		    // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// First inRange node
			
			if(ecg.qrsList != null)
			{
				if(ecg.qrsList.size > 0)
				{
					startingNode = ecg.qrsList.first;
					while(startingNode.info.timeStart < cOffset)
					{
						startingNode = startingNode.next;
					}
				}
				
		    	y0 = h / 2;
				
				// QRS
				
				if(startingNode != null)
				{
					currentNode = startingNode;
					while(currentNode != null && currentNode.info.timeStart < cOffset + w)
					{
						qx_0 = (int) (Math.max(currentNode.info.timeStart, cOffset) - cOffset);
						qy_0 = 0;
						
						qW = (int) (Math.min(currentNode.info.timeEnd, cOffset + w) - cOffset) - qx_0;
						qH = h;
						
						offG.setColor(qrsColor);
						offG.fillRect(qx_0, qy_0, qW, qH);
						
						qx_R = (int) (Math.max(currentNode.info.timeR, cOffset) - cOffset);
						
						offG.setColor(qrsRColor);
						offG.drawLine(qx_R, 0, qx_R, qH);
						offG.drawString(Integer.toString(currentNode.info.clusterNr), qx_0 + 2, h - 2);
						
						s1 = Double.toString(currentNode.info.area);
						s2 = Double.toString(currentNode.info.width());
						
						w1 = fm.stringWidth(s1);
						w2 = fm.stringWidth(s2);
						
						offG.setColor(whiteColor);
						offG.fillRect(
							qx_R + 2, 						 qH / 2 - 12,
							qx_R + 2 + 4 + Math.max(w1, w2), qH / 2 + 12
						);
						
						offG.setColor(qrsRColor);
						
						offG.drawString(s1, qx_R + 2, qH / 2 -  2);
						offG.drawString(s2, qx_R + 2, qH / 2 + 12);
						
						currentNode = currentNode.next;
					}
				}
				
		    	g.drawImage(off, 0, 0, Color.WHITE, null);
		    }
			
		    off = null;
		}
	}
