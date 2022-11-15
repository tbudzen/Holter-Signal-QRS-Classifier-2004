import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*
 * Created on 2006-02-26
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

	public class jECGQRSOptionsDialog 
	{
		private static JDialog dialog;
    	private static jECG client; // Wywolujacy dialog
    
    public jECGQRSOptionsDialog(jECG j)
    {
    	client = j;
    }
	
    public void Open()
    {
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border bBorder = BorderFactory.createLoweredBevelBorder();
                
        JLabel lDetectionSubBand = new JLabel("Detection subband:");
        JLabel lWindowSize = new JLabel("Window size (# of samples):"); 
    	JLabel lOverlap    = new JLabel("Overlap degree:"); 
        JLabel lTolerance  = new JLabel("Tolerance (# of samples):"); 
    	JLabel lThreshold  = new JLabel("Threshold:"); 
    	JLabel lDurSubBand = new JLabel("Duration subband #:");
    	JLabel lDurThreshold = new JLabel("Duration threshold:");
    	JLabel lDurNegThreshold = new JLabel("Duration negative threshold (# of samples):");
    	JLabel lDurPosThreshold = new JLabel("Duration positive threshold (# of samples):");    	
    	JLabel lAreaSubBand = new JLabel("Area subband #:");
    	JLabel lAreaNegThreshold = new JLabel("Area negative threshold (# of samples):");
    	JLabel lAreaPosThreshold = new JLabel("Area positive threshold (# of samples):");
        
    	lDetectionSubBand.setBorder(emptyBorder);
    	lWindowSize.setBorder(emptyBorder);
    	lOverlap.setBorder(emptyBorder);
    	lTolerance.setBorder(emptyBorder);
    	lThreshold.setBorder(emptyBorder); 
    	lDurSubBand.setBorder(emptyBorder); 
    	lDurThreshold.setBorder(emptyBorder);   	
    	lDurNegThreshold.setBorder(emptyBorder);
    	lDurPosThreshold.setBorder(emptyBorder);
    	lAreaSubBand.setBorder(emptyBorder); 
    	lAreaNegThreshold.setBorder(emptyBorder);
    	lAreaPosThreshold.setBorder(emptyBorder);		

        JPanel l = new JPanel(new GridLayout(0, 1));
        	l.setBorder(emptyBorder);
        	l.add(lDetectionSubBand);
        	l.add(lWindowSize);
        	l.add(lOverlap);
        	l.add(lTolerance);
        	l.add(lThreshold); 
        	l.add(lDurSubBand);  
        	l.add(lDurThreshold);      	
        	l.add(lDurNegThreshold);
        	l.add(lDurPosThreshold);
        	l.add(lAreaSubBand);  
        	l.add(lAreaNegThreshold);
        	l.add(lAreaPosThreshold);
        	
        final JTextField tbDetectionSubBand = new JTextField();
        final JTextField tbWindowSize = new JTextField();
        final JTextField tbOverlap    = new JTextField();
        final JTextField tbTolerance  = new JTextField();
        final JTextField tbThreshold  = new JTextField();     
        final JTextField tbDurSubBand  = new JTextField();     
        final JTextField tbDurThreshold  = new JTextField();           
        final JTextField tbDurNegThreshold  = new JTextField();    
        final JTextField tbDurPosThreshold  = new JTextField(); 
        final JTextField tbAreaSubBand  = new JTextField();           
        final JTextField tbAreaNegThreshold  = new JTextField();    
        final JTextField tbAreaPosThreshold  = new JTextField();              
        
        	tbDetectionSubBand.setBorder(bBorder);
        	tbWindowSize.setBorder(bBorder);
        	tbOverlap.setBorder(bBorder);
        	tbTolerance.setBorder(bBorder);
        	tbThreshold.setBorder(bBorder);
        	tbDurSubBand.setBorder(bBorder);
        	tbDurThreshold.setBorder(bBorder);
        	tbDurNegThreshold.setBorder(bBorder);
        	tbDurPosThreshold.setBorder(bBorder);
        	tbAreaSubBand.setBorder(bBorder);
        	tbAreaNegThreshold.setBorder(bBorder);
        	tbAreaPosThreshold.setBorder(bBorder);

	    JPanel cb = new JPanel(new GridLayout(0, 1));
	       	cb.setBorder(emptyBorder);	
	       	cb.add(tbDetectionSubBand);
	       	cb.add(tbWindowSize);	        
	       	cb.add(tbOverlap);
	       	cb.add(tbTolerance);
	       	cb.add(tbThreshold);
	       	cb.add(tbDurSubBand);
	       	cb.add(tbDurThreshold);
	       	cb.add(tbDurNegThreshold);
	       	cb.add(tbDurPosThreshold);
	       	cb.add(tbAreaSubBand);
	       	cb.add(tbAreaNegThreshold);
	       	cb.add(tbAreaPosThreshold);
		
	    l.setPreferredSize(new Dimension(300, -1));
	       	
	    JPanel d = new JPanel();
	    d.setLayout(new BoxLayout(d, 0));			       
	    	d.add(l);
	    	d.add(cb);

	    JButton bOK = new JButton("OK");
	        bOK.addActionListener(new ActionListener() 
	        { 
	    		public void actionPerformed(ActionEvent e) 
	    		{
	    			client.qDetectionSubBand = Integer.parseInt(tbDetectionSubBand.getText());
	    			client.qWindowSize = Integer.parseInt(tbWindowSize.getText());
	    			client.qOverlap    = Double.parseDouble(tbOverlap.getText());
	    			client.qTolerance  = Integer.parseInt(tbTolerance.getText());
	    			client.qThreshold  = Double.parseDouble(tbThreshold.getText());
	    			client.qDurSubBand = Integer.parseInt(tbDurSubBand.getText());
	    			client.qDurThreshold = Double.parseDouble(tbDurThreshold.getText());
	    			client.qDurNegThreshold = Integer.parseInt(tbDurNegThreshold.getText());
	    			client.qDurPosThreshold = Integer.parseInt(tbDurPosThreshold.getText());
	    			client.qAreaSubBand = Integer.parseInt(tbAreaSubBand.getText());
	    			client.qAreaNegThreshold = Integer.parseInt(tbAreaNegThreshold.getText());
	    			client.qAreaPosThreshold = Integer.parseInt(tbAreaPosThreshold.getText());
	    			
	    			dialog.dispose();
	    		}
	        });
	    			
	    JButton bCancel = new JButton("Cancel");
	        bCancel.addActionListener(new ActionListener() 
	        { 
	       		public void actionPerformed(ActionEvent e) { dialog.dispose(); }
	        });
	        
	    JPanel b = new JPanel();
	    	b.add(bCancel);
	    	b.add(bOK);

	    JPanel m = new JPanel();
	        m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));
	        m.add(d);
	        m.add(b);
	        
	    tbDetectionSubBand.setText(Integer.toString(client.qDetectionSubBand));
	    tbWindowSize.setText(Integer.toString(client.qWindowSize));
	    tbOverlap.setText(Double.toString(client.qOverlap));
	    tbTolerance.setText(Integer.toString(client.qTolerance));
	    tbThreshold.setText(Double.toString(client.qThreshold));
	    tbDurSubBand.setText(Integer.toString(client.qDurSubBand));
	    tbDurThreshold.setText(Double.toString(client.qDurThreshold));
	    tbDurNegThreshold.setText(Integer.toString(client.qDurNegThreshold));
	    tbDurPosThreshold.setText(Integer.toString(client.qDurPosThreshold));
	    tbAreaSubBand.setText(Integer.toString(client.qAreaSubBand));
	    tbAreaNegThreshold.setText(Integer.toString(client.qAreaNegThreshold));
	    tbAreaPosThreshold.setText(Integer.toString(client.qAreaPosThreshold));
	    
	    dialog = new JDialog(client.frame, "QRS detection options", true);
	    dialog.setResizable(false); // Sta³e wymiary
	    dialog.getContentPane().add(m);
	    dialog.getContentPane().setLayout(new GridLayout(1, 2)); // 2 kolumny
	    dialog.setSize(450, 350);
	    dialog.setLocationRelativeTo(null); 
	    dialog.setVisible(true);
    }

	}
