import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

	public class jECGNavPanel extends JPanel implements ActionListener
	{
		static jECG ecg;
		static JButton moveLeft, moveRight, moveToStart;
		static JLabel info;
		static JComboBox mStep;
		static JPanel nav;
		
		public jECGNavPanel(jECG e)
		{
			ecg = e;
				ecg.currentOffset = 0;
				ecg.currentMoveStep = 100;
			
			moveToStart = new JButton("|<");
			moveLeft 	= new JButton("<<");
			moveRight 	= new JButton(">>");	
			
			mStep = new JComboBox();
				mStep.addItem("100");
				mStep.addItem("1000");
				mStep.addItem("10000");
				mStep.addItem("100000");
				mStep.addItem("1000000");
			
			moveToStart.addActionListener(this);
			moveLeft.addActionListener(this);
			moveRight.addActionListener(this);
			mStep.addActionListener(this);

			nav = new JPanel();
				nav.add(moveToStart);
				nav.add(moveLeft);
				nav.add(moveRight);
				nav.add(mStep);			
			
			add(nav);
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			Object e;
			int k;
			
			e = arg0.getSource();
			
			if(e == moveToStart)
			{
				ecg.currentOffset = 0;
				System.out.println(ecg.currentOffset);		
			}
			else if(e == moveLeft)
			{
				ecg.currentOffset -= ecg.currentMoveStep;
				if(ecg.currentOffset < 0) 
				{
					ecg.currentOffset = 0;
				}
				System.out.println(ecg.currentOffset);		
			}
			else if(e == moveRight)
			{
				ecg.currentOffset += ecg.currentMoveStep;	
				if(ecg.currentOffset > ecg.mainChannel[0].size - ecg.pSignal.getWidth()) 
				{
					ecg.currentOffset = ((int) ((int) ecg.mainChannel[0].size - ecg.pSignal.getWidth()) / 100) * 100;
				}
				System.out.println(ecg.currentOffset);			
			}
			else if(e == mStep)
			{
				switch(mStep.getSelectedIndex())
				{
					case 0: k = 100; break;
					case 1: k = 1000; break;
					case 2: k = 10000; break;
					case 3: k = 100000; break;
					case 4: k = 1000000; break;
					default: 
							k = 0;
				}
				System.out.println(k);
				ecg.currentMoveStep = k;
			}

			ecg.Repaint();
		}		
	}
