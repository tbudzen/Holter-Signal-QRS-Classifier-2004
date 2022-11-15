import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * Created on 2006-02-27
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
	
	public class jECGOpenDialog 
	{

		private static JDialog dialog;
    	private static jECG client; // Wywolujacy dialog
    
    public jECGOpenDialog(jECG j)
    {
    	client = j;
    }
	
    public void Open()
    {
    	int i;
    	
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        
//        JLabel lChannel = new JLabel("Channel:");
//      lChannel.setBorder(emptyBorder);
        
        JLabel lSize = new JLabel("Fragment:");
        	lSize.setBorder(emptyBorder);

        JPanel l = new JPanel(new GridLayout(0, 1));
        	l.setBorder(emptyBorder);
//        	l.add(lChannel);
        	l.add(lSize);
/*	
        final JComboBox cbChannel = new JComboBox();
        	cbChannel.addItem("1");
        	cbChannel.addItem("2");
        	cbChannel.addItem("3");

        	cbChannel.setBorder(emptyBorder);
*/

        final JComboBox cbFragment = new JComboBox();
        	for(i = 0; i < client.oMaxFragments; i++)
        	{        		
        		cbFragment.addItem(Integer.toString(i + 1));
        	}
	       	cbFragment.setBorder(emptyBorder);		        
                	
	    JPanel cb = new JPanel(new GridLayout(0, 1));
	       	cb.setBorder(emptyBorder);		        
//	       	cb.add(cbChannel);	        
	       	cb.add(cbFragment);
		   
	    JPanel d = new JPanel(new GridLayout(0, 2));			       
	    	d.add(l);
	    	d.add(cb);

	    JButton bOK = new JButton("OK");
	        bOK.addActionListener(new ActionListener() 
	        { 
	    		public void actionPerformed(ActionEvent e) 
	    		{
//	    			client.oChannel = 1 + cbChannel.getSelectedIndex();
	    			client.oFragmentNr = cbFragment.getSelectedIndex(); // 0 ... N - 1
	       			client.oResult = true;
	    			
	    			dialog.dispose();
	    		}
	        });
	    			
	    JButton bCancel = new JButton("Cancel");
	        bCancel.addActionListener(new ActionListener() 
	        { 
	       		public void actionPerformed(ActionEvent e) 
	       		{ 
	       			client.oResult = false;
	       			
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
	        
	    dialog = new JDialog(client.frame, "Open options", true);
	    dialog.setResizable(false); // Sta³e wymiary
	    dialog.getContentPane().add(m);
	    dialog.getContentPane().setLayout(new GridLayout(1, 2)); // 2 kolumny
	    dialog.setSize(250, 120);
	    dialog.setLocationRelativeTo(null); 
	    dialog.setVisible(true);
    }
	}
