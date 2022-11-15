import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/*
 * Created on 2006-02-06
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
	
	public class jECGMainMenu extends JMenuBar implements ActionListener
	{
		private static jECG j;
		
		private static JMenu 
			mF, mD, mQ, mT, mC, mH;
		
		private static JMenuItem
			miFOpenHolterChannel,
			miFOpenHolterQRSAnnotations,
			miFInsertTestWave,
			miFSaveSubBands,
			miFExit,
			miDWaveletOptions,
			miDDecompose,
			miQFindQRSOptions,
			miQFindQRS,
			miTTrainingOptions,
			miTTrain,
			miTSaveNet,
			miCOpenNet,
			miCClassify,
			miCResults,
			miHAbout;
					
		public jECGMainMenu(jECG main)
		{			
			j = main;
			
			mF = new JMenu("File");
				miFOpenHolterChannel 		= new JMenuItem("Open Holter ECG channel ...");
				miFOpenHolterQRSAnnotations = new JMenuItem("Open Holter QRS annotations ...");
				miFInsertTestWave 			= new JMenuItem("Insert test wave");
				miFSaveSubBands				= new JMenuItem("Save subbands");
				miFExit 					= new JMenuItem("Exit");

				miFOpenHolterChannel.addActionListener(this);
				miFOpenHolterQRSAnnotations.addActionListener(this);
				miFInsertTestWave.addActionListener(this);
				miFSaveSubBands.addActionListener(this);
				miFExit.addActionListener(this);
				
				mF.add(miFOpenHolterChannel);   
				mF.add(miFOpenHolterQRSAnnotations);  
				mF.add(new JSeparator()); 
				mF.add(miFInsertTestWave);  
				mF.add(new JSeparator());
				mF.add(miFSaveSubBands);    
				mF.add(new JSeparator());
				mF.add(miFExit);
				
			mD = new JMenu("Decomposition");
				miDWaveletOptions 	= new JMenuItem("Wavelet & sub-bands options ...");
			    miDDecompose 		= new JMenuItem("Decompose");
			    
			    miDWaveletOptions.addActionListener(this);
			    miDDecompose.addActionListener(this);	   		    
				
				mD.add(miDWaveletOptions);
				mD.add(miDDecompose);
				
			mQ = new JMenu("QRS detection");

		    	miQFindQRSOptions 	= new JMenuItem("QRS options ...");
		    	miQFindQRS 			= new JMenuItem("Find QRS complexes");
		    
		    	miQFindQRSOptions.addActionListener(this);	 
		    	miQFindQRS.addActionListener(this);	
				
		    	mQ.add(miQFindQRSOptions);
				mQ.add(miQFindQRS);

			mT = new JMenu("Training");
				miTTrainingOptions 	= new JMenuItem("Training options ...");
				miTTrain 			= new JMenuItem("Train");
				miTSaveNet 			= new JMenuItem("Save neural net ...");
				
				miTTrainingOptions.addActionListener(this);		
				miTTrain.addActionListener(this);		
				miTSaveNet.addActionListener(this);			

				mT.add(miTTrainingOptions);
				mT.add(miTTrain);
				mT.add(new JSeparator());
				mT.add(miTSaveNet);
			
			mC = new JMenu("Classification");
				miCOpenNet = new JMenuItem("Load neural net ...");
				miCClassify = new JMenuItem("Classify");
				miCResults = new JMenuItem("Results");
				
				miCOpenNet.addActionListener(this);	
				miCClassify.addActionListener(this);		
				miCResults.addActionListener(this);		
			
				mC.add(miCOpenNet);
				mC.add(new JSeparator());
				mC.add(miCClassify);
				mC.add(miCResults);
				
			mH = new JMenu("Help");
				miHAbout = new JMenuItem("About ...");

				miHAbout.addActionListener(this);		
				
				mH.add(miHAbout);
				
			this.add(mF);
			this.add(mD);
			this.add(mQ);
			this.add(mT);
			this.add(mC);
			this.add(mH);
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) 
		{
			Object eSrc;
			
			eSrc = e.getSource();			
			if(eSrc == miFExit)
			{				
				System.exit(0);
			}
			else if(eSrc == miFOpenHolterChannel) 	{ j.OpenChannel(); }
			else if(eSrc == miFOpenHolterQRSAnnotations) 	{ j.OpenQRSAnnotations(); }
			else if(eSrc == miFInsertTestWave)		{ j.InsertTestWave(); }
			else if(eSrc == miFSaveSubBands)		{ j.SaveSubBands(); }
			else if(eSrc == miDWaveletOptions)		{ j.WaveletOptions(); }
			else if(eSrc == miDDecompose)			{ j.Decompose(); }
			else if(eSrc == miQFindQRSOptions)		{ j.QRSOptions(); }
			else if(eSrc == miQFindQRS)			{ j.FindQRSComplexes(); }
			else if(eSrc == miTTrainingOptions)		{ j.TrainingOptions(); }
			else if(eSrc == miTTrain)			{ j.Train(); }
			else if(eSrc == miTSaveNet)			{ j.SaveNet(); }
			else if(eSrc == miCOpenNet)			{ j.LoadNet(); }
			else if(eSrc == miCClassify)			{ j.Classify(); }
			else if(eSrc == miCResults)			{ j.Results(); }
			else if(eSrc == miHAbout) 			{ j.About(); }
		}
	}
