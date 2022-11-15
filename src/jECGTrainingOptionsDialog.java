	
	import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	import javax.swing.*;
import javax.swing.border.Border;

/*
 * Created on 2006-02-08
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

	public class jECGTrainingOptionsDialog 
	{
		private static JDialog dialog;
	    	private static jECG client; // Wywolujacy dialog
	    
	    public jECGTrainingOptionsDialog(jECG j)
	    {
	    	client = j;
	    }
		
	    public void Open()
	    {
	        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        
	        JLabel lType = new JLabel("Wavelet type:"); 
	        JLabel lDegree = new JLabel("Wavelet degree:"); 
	        JLabel lLevel = new JLabel("Decomposition level:"); 
        	
	        lType.setBorder(emptyBorder);
        	lDegree.setBorder(emptyBorder);
	        lLevel.setBorder(emptyBorder);

	        JPanel l = new JPanel(new GridLayout(0, 1));
	        	l.setBorder(emptyBorder);
	        	l.add(lType);
	        	l.add(lDegree);
	        	l.add(lLevel);
	        	
	        final JComboBox cbType = new JComboBox();
        		cbType.addItem("Haar");
	        	cbType.addItem("Daubechies");
	        	cbType.addItem("Symmlet");
	        	cbType.addItem("Coiflet");

		    final JComboBox cbDegree = new JComboBox();
		    	cbDegree.addItem("1");
		        cbDegree.addItem("2");
		        cbDegree.addItem("3");

			final JComboBox cbLevel = new JComboBox();
		    	cbLevel.addItem("3");
			    cbLevel.addItem("4");
			    cbLevel.addItem("5");
			    cbLevel.addItem("6");
			    cbLevel.addItem("7");
			    cbLevel.addItem("8");

			    cbType.setBorder(emptyBorder);
	        	cbDegree.setBorder(emptyBorder);
		        cbLevel.setBorder(emptyBorder);

		    JPanel cb = new JPanel(new GridLayout(0, 1));
		       	cb.setBorder(emptyBorder);		        
		       	cb.add(cbType);
		       	cb.add(cbDegree);
		       	cb.add(cbLevel);
			   
		    JPanel d = new JPanel(new GridLayout(0, 2));			       
		    	d.add(l);
		    	d.add(cb);

		    JButton bOK = new JButton("OK");
		        bOK.addActionListener(new ActionListener() 
		        { 
		    		public void actionPerformed(ActionEvent e) 
		    		{		    			
		    			dialog.dispose();
		    		}
		        });
		    			
		    JButton bCancel = new JButton("Cancel");
		        bCancel.addActionListener(new ActionListener() 
		        { 
		       		public void actionPerformed(ActionEvent e) 
		       		{ 
		       			dialog.dispose(); 
		       		}
		        });
		        
		    JPanel b = new JPanel();
		    	b.add(bCancel);
		    	b.add(bOK);

		    JPanel m = new JPanel();
		        m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));
		        m.add(d);
		        m.add(b);

		    //cbType.setSelectedIndex(client.wType);
		        
		    dialog = new JDialog(client.frame, "Training options", true);
		    dialog.setResizable(false); // Sta³e wymiary
		    dialog.getContentPane().add(m);
		    dialog.getContentPane().setLayout(new GridLayout(1, 2)); // 2 kolumny
		    dialog.setSize(380, 190);
		    dialog.setLocationRelativeTo(null); 
		    dialog.setVisible(true);
	    }
	}
