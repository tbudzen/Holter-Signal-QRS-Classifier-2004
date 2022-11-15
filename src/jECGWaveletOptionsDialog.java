import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

	public class jECGWaveletOptionsDialog implements ItemListener
	{
		private static JDialog dialog;
	    private static jECG client; // Wywolujacy dialog
	    	    
	    static JComboBox cbChannel;
	    static JComboBox cbType;
	    static JComboBox cbDegree;
	    
	    public jECGWaveletOptionsDialog(jECG j)
	    {
	    	client = j;
	    }
		
	    public void Open()
	    {
	        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        
	        JLabel lChannel= new JLabel("Holter channel #:");
	        JLabel lType   = new JLabel("Wavelet type:"); 
	        JLabel lDegree = new JLabel("Wavelet degree:"); 
	        JLabel lLevel  = new JLabel("Decomposition level:"); 
        	
	        lChannel.setBorder(emptyBorder);
        	lType.setBorder(emptyBorder);
        	lDegree.setBorder(emptyBorder);
	        lLevel.setBorder(emptyBorder);

	        JPanel l = new JPanel(new GridLayout(0, 1));
	        	l.setBorder(emptyBorder);
	        	l.add(lChannel);
	        	l.add(lType);
	        	l.add(lDegree);
	        	l.add(lLevel);
	        	
	        cbChannel = new JComboBox();
	        	cbChannel.addItem("1");
	        	cbChannel.addItem("2");
	        	cbChannel.addItem("3");
	        	
	        cbType = new JComboBox();
        		cbType.addItem("Haar");
	        	cbType.addItem("Daubechies");
	        	cbType.addItem("Coiflet");
	        	cbType.addItem("Symmlet");
	        	cbType.addItem("Biorthogonal spline");
	        	cbType.addItem("'Mexican hat'");
				
	        	cbType.addItemListener(this);
	        	
		    cbDegree = new JComboBox();
    			cbDegree.addItem("-");

			final JComboBox cbLevel = new JComboBox();
	    		cbLevel.addItem("3");
	    		cbLevel.addItem("4");
	    		cbLevel.addItem("5");
	    		cbLevel.addItem("6");
	    		cbLevel.addItem("7");
		    	cbLevel.addItem("8");

		    	cbChannel.setBorder(emptyBorder);
			    cbType.setBorder(emptyBorder);
	        	cbDegree.setBorder(emptyBorder);
		        cbLevel.setBorder(emptyBorder);

		    JPanel cb = new JPanel(new GridLayout(0, 1));
		       	cb.setBorder(emptyBorder);		        
		       	cb.add(cbChannel);
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
		    			client.wChannel = cbChannel.getSelectedIndex();
		    			client.wType 	= cbType.getSelectedIndex();
		    			client.wDegree 	= cbDegree.getSelectedIndex();
		    			client.wLevel 	= cbLevel.getSelectedIndex();
		    			
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

		    cbChannel.setSelectedIndex(client.wChannel);
		    cbType.setSelectedIndex(client.wType);
		    cbDegree.setSelectedIndex(client.wDegree);
		    cbLevel.setSelectedIndex(client.wLevel);
		        
		    dialog = new JDialog(client.frame, "Decomposition options", true);
		    dialog.setResizable(false); // Sta³e wymiary
		    dialog.getContentPane().add(m);
		    dialog.getContentPane().setLayout(new GridLayout(1, 2)); // 2 kolumny
		    dialog.setSize(450, 230);
		    dialog.setLocationRelativeTo(null); 
		    dialog.setVisible(true);
	    }
	    
		public void itemStateChanged(ItemEvent e) 
		{
			int idx;
			
			idx = cbType.getSelectedIndex();
			// System.out.println("Item #" + idx + "selected.");
							
			cbDegree.removeAllItems();
							
			switch(idx)
			{
				case 0: 
					cbDegree.addItem("-");
					break;
									
				case 1: 
					cbDegree.addItem("2");
					cbDegree.addItem("3");
					cbDegree.addItem("4");
					cbDegree.addItem("5");
					cbDegree.addItem("6");
					break;
									
				case 2: 
					cbDegree.addItem("1");
					cbDegree.addItem("2");
					cbDegree.addItem("3");
					break;
								
				case 3: 
					cbDegree.addItem("2");
					cbDegree.addItem("3");
					cbDegree.addItem("4");
					cbDegree.addItem("5");
					cbDegree.addItem("6");
					cbDegree.addItem("7");
					cbDegree.addItem("8");
					break;
								
				case 4: 
					cbDegree.addItem("1.3");
					cbDegree.addItem("1.5");
					cbDegree.addItem("2.2 (Cohen-Daubechies-Fauraue 5/3)");
					cbDegree.addItem("2.4");
					cbDegree.addItem("2.6");
					cbDegree.addItem("3.3");
					// cbDegree.addItem("4.4 (Cohen-Daubechies-Fauraue 9/7)");
					break;
								
				case 5: 
					cbDegree.addItem("-");
					break;					
								
				default: 
					break;
			}
			cbDegree.doLayout();
			cbDegree.setSelectedIndex(0);
		}
	}
	
