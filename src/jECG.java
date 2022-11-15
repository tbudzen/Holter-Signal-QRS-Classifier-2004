import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

/*
 * Created on 2006-02-03
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

	public class jECG extends JPanel
	{
		static jECG ecg;

		static jECGMainMenu menu;

		static jECGOpenDialog 			 oDlg;
		static jECGWaveletOptionsDialog  wtOptDlg;
		static jECGTrainingOptionsDialog trOptDlg;
		static jECGQRSOptionsDialog 	 qrsOptDlg;
		
		static JFrame frame;
		
		public static JPanel // 'public' for jECGNavPanel -> width
				pSignal, 
				pSubBands, 
				pQRS,
				pNavigation;
						
		static Channel [] mainChannel; // Chosen to analysis
		static Channel stimChannel; // Stimulator channel
		static Channel [] subBands;
		
		static JPanel [] panels;
		
		public QRSInfoList 
				qrsList, // Detected
				annList; // Annotations (from file, if any available)
		
		// From "Wavelet options"
		
		WaveletFactory 
				wFactory;
		int 	wType; 
		int		wChannel;
		int		wDegree;
		int		wLevel;
		private WaveletFilter 
				wFilter;
		
		// From "QRS Detection" options:
		
		int		qDetectionSubBand;
		int 	qWindowSize;
		int 	qTolerance;
		double 	qThreshold;
		double 	qOverlap;
		int		qDurSubBand;
		double  qDurThreshold;
		int		qDurNegThreshold;
		int		qDurPosThreshold;
		int		qAreaSubBand;
		int		qAreaNegThreshold;
		int		qAreaPosThreshold;
		
		// From "Open" options:
		
		int		oMaxFragments;
		boolean oResult; 	// Was finally opened ?
		int		oFragmentNr;
		int 	oChannel; 	// Channel # to open
		int 	oSize; 	  	// Size of fragment
		
		// From "Training options":
		
		int 	sb1;
		int		sb2; 	
		
		int 	currentOffset;
		int 	currentMoveStep;
		
		RBFNeuralNet net;
				
		public jECG()
		{
			int i;
			
			wFactory = new WaveletFactory();
			
			mainChannel = new Channel [3];
			for(i = 0; i < 3; i++)
			{
				mainChannel[i] = new Channel();
			}
			
			stimChannel = new Channel();
			
			wChannel			= 0; // 1
			wType   			= 1; // Daubechies
			wDegree 			= 4; // 6
			wLevel  			= 2;
						
			oMaxFragments 		= 0;						
			oFragmentNr 		= 0;
			oChannel 			= 1;
			oSize 				= 0;
			
			qDetectionSubBand 	= 5;
			qWindowSize 		= 200;
			qTolerance  		= 25; // 0.20 s
			qThreshold  		= 0.60;
			qOverlap    		= 0.70;

			qDurSubBand 		= 4;
			qDurThreshold 		= 0.2;
			qDurNegThreshold 	= 13; // 0.10 s
			qDurPosThreshold 	= 19; // 0.15 s
			
			qAreaSubBand 		= 3;
			qAreaNegThreshold 	= 25; // 0.20 s
			qAreaPosThreshold 	= 25; // 0.20 s
						
			net = new RBFNeuralNet(2, 2, 1);
		}	    

		public String getFileName(String title)
		{
			String result;
			int retVal;
			JFileChooser fc;

    		result = null;
		    fc = new JFileChooser();
		    fc.setDialogTitle(title);
		    retVal = fc.showOpenDialog(ecg);
		    if(retVal == JFileChooser.APPROVE_OPTION) 
		    {
		    	result = fc.getSelectedFile().getAbsolutePath();
		    }		    
		    return result;
		}		
		
		public void Repaint()
		{			
			frame.repaint();
		}
		
/*--------------------------------------------------------------------------------
 * 
 * 		File IO
 * 
 *-------------------------------------------------------------------------------- 
 */
		
		public void OpenChannel()
		{
			int i;
			String fName;
			File f;

			qrsList = null;	
			
			pSubBands.removeAll();
			
			Repaint();		
			
			System.gc();
			
			fName = getFileName("Open Holter ECG channel ...");
			if(fName != null)
			{				
				// Determination of number of fragments
				
				f = new File(fName);
				oMaxFragments = (int) (f.length() / 3 / (128 * 60 * 30)); // Integer
				f = null;
				
				oDlg = new jECGOpenDialog(ecg);
				oDlg.Open();
				oDlg = null;
				
				if(oResult)
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

					// mainChannel.load(fName, 3, oChannel, oFragmentNr);
					
					for(i = 0; i < 3; i++)
					{
						mainChannel[i].load(fName, 3, i, oFragmentNr);
						mainChannel[i].treatAsECG();
						
					}

					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

					//QRSChannel = null;
					//QRSChannel = new Channel(mainChannel.size);
				}
			}		
			Repaint();
		}
		
		public void OpenQRSAnnotations
		{
		
		}
		
		public void InsertTestWave()
		{
			mainChannel[0].sineWave(5000, 100.0, 20.0);
			Repaint();
		}

		public void SaveSubBands()
		{
			int i, K;
			
			K = wLevel + 3;
			for(i = 1; i < K; i++)
			{				
				subBands[i].save(mainChannel[0].name + ".subBand." + i + ".raw");
			}
		}

/*--------------------------------------------------------------------------------
 * 
 * 		Options
 * 
 *-------------------------------------------------------------------------------- 
 */
		
		public void WaveletOptions()
		{
			wtOptDlg = new jECGWaveletOptionsDialog(ecg);
			wtOptDlg.Open();
			wtOptDlg = null;
		}

		public void QRSOptions()
		{
			qrsOptDlg = new jECGQRSOptionsDialog(ecg);
			qrsOptDlg.Open();
			qrsOptDlg = null;
		}
		
		public void TrainingOptions()
		{
			//trOptDlg = new jECGTrainingOptionsDialog(ecg);
			//trOptDlg.Open();
			//trOptDlg = null;
		}

/*--------------------------------------------------------------------------------
 * 
 * 		Decomposition
 * 
 *-------------------------------------------------------------------------------- 
 */		
		public void Decompose()
		{
			WaveletFilter wF;
			jECGChannelPanel p;
			int i, K, N, M;
			
			qrsList = null;	
			
			Repaint();			
			
			System.gc();
			
			switch(wType + 1)
			{
				case 1: wF = wFactory.Haar(); break;
				case 2: wF = wFactory.Daubechies(wDegree + 2); break;
				case 3:	wF = wFactory.Coiflet(wDegree + 1); break;
				case 4: wF = wFactory.Symmlet(wDegree + 2); break; // 2, 3, 4, 5, 6, 7, 8
				case 5: switch(wDegree + 1)
						{
							case 1: N = 1; M = 3; break;
							case 2: N = 1; M = 5; break;
							case 3: N = 2; M = 2; break;
							case 4: N = 2; M = 4; break;
							case 5: N = 2; M = 6; break;
							case 6: N = 3; M = 3; break;
							// case 7: N = 4; M = 4; break;		
							default: N = 1; M = 3; break;
						}				
						wF = wFactory.BiOrthogonalSpline(N, M); break; 
				case 6: wF = wFactory.MexicanHat(); break; 
				
				default: wF = wFactory.Daubechies(6); // Daubechies_6 by default !
			}			
			
			K = wLevel + 3;		

			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			subBands = wF.decompose(mainChannel[wChannel], K);

			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
			for(i = 0; i < K; i++)
			{		
				subBands[i].name = mainChannel[0].name;
				subBands[i].treatAsECGSubBand();
			}			
			
			pSubBands.removeAll();
			for(i = 0; i < K; i++)
			{
				p = new jECGChannelPanel(subBands[i], ecg);
					p.setBackground(Color.white);
					p.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
					p.setPreferredSize(new Dimension(0, 100));
				pSubBands.add(p);
			}			
			pSubBands.doLayout();
			
			wF = null;
			
			Repaint();
		}

/*--------------------------------------------------------------------------------
 * 
 * 		QRS Detection
 * 
 *-------------------------------------------------------------------------------- 
 */
		
		public void FindQRSComplexes()
		{
			Channel R_SUBBAND, A_SUBBAND, D_SUBBAND;
			int x, vMax, vMaxT, bQRSStart;
			long SIZE, q_0, q_1, j, k, k_1, k_2, w_0, w_1, A, D;
			boolean bQRSFound, bPrecedingQRS;
				
			SIZE = mainChannel[0].size;
			
			R_SUBBAND = subBands[qDurSubBand];  // QRS detection
			D_SUBBAND = subBands[qDurSubBand];  // Computation of duration
			A_SUBBAND = subBands[qAreaSubBand]; // Computation of area
			
			qrsList = null;
			qrsList = new QRSInfoList();

			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			w_0 = 0;
			w_1 = qWindowSize;
			while(w_1 < SIZE)
			{					
				// Detection of max abs value 
				vMax = 0;
				for(j = w_0; j < w_1; j++)
				{	
					x = R_SUBBAND.getData((int) j);
					if(x > vMax)
					{
						vMax = x;
					}
				}
				vMaxT = (int) (vMax * qThreshold);
				// Detection of QRS within window
				bQRSFound = false;
				bQRSStart = 0;
				for(j = w_0; j < w_1; j++)
				{					
					x = R_SUBBAND.getAbsData((int) j);
					if(bQRSFound) // After we found QRS we must wait qTolerance samples
					{						
						bQRSStart --;
						if(bQRSStart == 0)
						{
							bQRSFound = false;
						}						
					}
					else if(x > vMaxT) // We have candidate for QRS
					{	
						// We try to find preceding QRS, if any exists:
						
						bPrecedingQRS = false;
						k_1 = j - qTolerance; if(k_1 < 0) k_1 = 0;
						k_2 = j - 1;
						for(k = k_1; k < k_2; k++) 
						{							
							if(R_SUBBAND.getData((int) k) > vMaxT)
							{
								bPrecedingQRS = true;
							}
						}
						
						// If there's no preceding QRS, we add current one:
						
						if( ! bPrecedingQRS) 
						{
							// Determination of range and size of QRS complex (without morphology)
						
							D = 0; // Duration							
							q_0 = j - qDurNegThreshold; if(q_0 < 0) q_0 = 0;
							q_1 = j + qDurPosThreshold; if(q_1 > SIZE) q_1 = SIZE;
							for(k = q_0; k < q_1; k++)
							{								
								if(D_SUBBAND.getAbsData((int) k) > (int) (vMax * qDurThreshold))
								{
									D ++;
								}
							}
						
							/*
							A = 0; // Area
							q_0 = j - qAreaNegThreshold; if(q_0 < 0) q_0 = 0;
							q_1 = j + qAreaPosThreshold; if(q_1 > SIZE) q_1 = SIZE;
							for(k = q_0; k < q_1; k++)
							{								
								A += A_SUBBAND.getAbsData((int) k);
							}
							*/
												
							qrsList.add(new QRSInfo(j - D / 2, j, j + D / 2, 0)); // A)); // Rough
							bQRSFound = true;
							bQRSStart = qTolerance;
						}
					}
					// else nothing (j++)
				}
				// Move window ...
				w_0 += qWindowSize * qOverlap; 
				w_1 = w_0 + qWindowSize;
			}
			
			// Removal of too short QRS-es (possible stimulator)
			
			//jECGChannelPanel p = new jECGChannelPanel(QRSChannel, ecg);
			//	p.setBackground(Color.white);
			//	p.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			//	p.setPreferredSize(new Dimension(0, 100));
			
			//pQRS.removeAll();
			//pQRS.add(p);
			//pQRS.doLayout();	

			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
			Repaint();
		}

/*--------------------------------------------------------------------------------
 * 
 * 		Training
 * 
 *-------------------------------------------------------------------------------- 
 */		
		
		private double D(double x_0, double y_0, double x_1, double y_1)
		{
			double d_0, d_1, r;
			
			d_0 = x_0 - y_0;
			d_1 = x_1 - y_1;
			
			r = Math.sqrt((d_0 * d_0) + (d_1 * d_1));
			
			return r;			
		}
		
		public void Train()
		{
			int i, K, iterCount, maxIter, dMin, cNr, cIdx;
			double [] clustersX; // Width
			double [] clustersY; // Size
			double [] r;	
			double [] cSumX;
			double [] cSumY;
			double r0;
			int [] cCount;
			double w, wMin, wMax;
			double d, dMinValue;
			boolean bReallocation;
			QRSInfoNode n;

			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			// Initialization
			
			maxIter = 25;	
			
			K = 2;
			
			clustersX = new double [K];
			clustersY = new double [K];
			r = new double [K];
			cSumX = new double [K];
			cSumY = new double [K];
			cCount = new int [K];
			for(i = 0; i < K; i++)
			{
				clustersX[i] = 0.0;
				clustersY[i] = 0.0;
				r[i] = 0.0;
			}			

			// Initial random clusters (1 / K of maximum range of WIDTH)

			wMin = 10000.0;
			wMax = 0.0;
			n = qrsList.first;
			while(n != null)
			{			
				w = n.info.width();
				if(w > wMax) wMax = w;
				if(w < wMin) wMin = w;
				
				n = n.next;
			}		
			wMax = wMax * 1.01; // Hack
			
			n = qrsList.first;
			while(n != null)
			{
				w = n.info.width();
				n.info.clusterNr = (int) ((w - wMin) * K / (wMax - wMin)) + 1;		
				System.out.print(n.info.clusterNr);
				n = n.next;
			}		
			
			// Clustering ...
			
			iterCount = 0;
			bReallocation = true;
			while(bReallocation && (iterCount < maxIter))
			{				
				iterCount ++;
				
				System.out.println("Clustering iteration #" + iterCount);
				
				// Calculation of centers
				
				for(i = 0; i < K; i++)
				{
					cSumX[i] = 0.0;
					cSumY[i] = 0.0;
					
					cCount[i] = 0;
				}
				
				n = qrsList.first;
				while(n != null)
				{
					cNr = n.info.clusterNr - 1;
					
					cSumX[cNr] += n.info.width();
					cSumY[cNr] += n.info.area;
					
					cCount[cNr] ++;
					
					n = n.next;
				}				
				
				for(i = 0; i < K; i++)
				{
					clustersX[i] = cSumX[i] / cCount[i];
					clustersY[i] = cSumY[i] / cCount[i];
					
					System.out.println("    New center #" + (i + 1) + ": " + clustersX[i] + ", " + clustersY[i] + "(" + cCount[i] + ")");
				}
				
				// Assigning samples	
				
				bReallocation = false;
				n = qrsList.first;
				while(n != null)
				{
					dMin = 0;
					dMinValue = D(clustersX[0], n.info.width(), clustersY[0], n.info.area);
					for(i = 1; i < K; i++) // 1 !
					{
							d =	D(clustersX[i], n.info.width(), clustersY[i], n.info.area);
							if(d < dMinValue)
							{
								dMin = i;
								dMinValue = d;
							}
					}
					
					if(n.info.clusterNr != (dMin + 1))
					{
						bReallocation = true;
					}					
					n.info.clusterNr = (dMin + 1);
					
					n = n.next;
				}	
			}
			
			// Finding max radius for each cluster

			n = qrsList.first;
			while(n != null)
			{
				cIdx = n.info.clusterNr - 1;
				r0 = D(clustersX[cIdx], n.info.width(), clustersY[cIdx], n.info.area);
				if(r0 > r[cIdx])
				{
					r[cIdx] = r0;
				}
				
				n = n.next;
			}
			
			// Setting RBF-neurons' centers and radii
			
			for(i = 0; i < K; i++)
			{				
				System.out.println("RBF neuron #" + (i + 1) + ": " + clustersX[i] + ", " + clustersY[i] + "/" + r[i]);
				
				net.hiddenLayer.neurons[i].rCenter = 0.0; //clusters[i];
				net.hiddenLayer.neurons[i].rRadius = r[i];
			}
			
			// Training
			
			n = qrsList.first;
			while(n != null)
			{
					// net.classify(n.info);
					n = n.next;
			}	

			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
			Repaint();
		}

		public void SaveNet()
		{
			String fName;
				
			fName = getFileName("Save neural net as ...");
			if(fName != null)
			{
				net.save(fName);
			}
		}
		
		public void LoadNet()
		{
			String fName;
				
			fName = getFileName("Load neural net ...");
			if(fName != null)
			{
				net.load(fName);
			}
		}
		
/*--------------------------------------------------------------------------------
 * 
 * 		Classification
 * 
 *-------------------------------------------------------------------------------- 
 */		
		public void Classify()
		{
			QRSInfoNode n;
			
			n = qrsList.first;
			while(n != null)
			{
				// net.classify(n.info);
				
				n = n.next;
			}
		}
		
		public void Results()
		{
		}

		public void About() 
		{}

/*--------------------------------------------------------------------------------
 * 
 * 		main()
 * 
 *-------------------------------------------------------------------------------- 
 */		
		public static void main(String [] args) 
		{
			int i;
			Container cPane;
	        JPanel jp;
			
			ecg = new jECG();
			
			menu = new jECGMainMenu(ecg);	
			
			pSignal = new JPanel(new GridLayout(0, 1));
			for(i = 0; i < 3; i++)
			{
				jp = new jECGChannelPanel(mainChannel[i], ecg);
				jp.setBackground(Color.white);
				jp.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
				jp.setPreferredSize(new Dimension(0, 80));
			
				pSignal.add(jp);
			}
				
			pSubBands = new JPanel(new GridLayout(0, 1));			
			
			pQRS      = new JPanel(new GridLayout(0, 1));	
				pQRS.add(new jECGQRSPanel(ecg));
				pQRS.setPreferredSize(new Dimension(0, 80));
			
			pNavigation = new jECGNavPanel(ecg);
				pNavigation.setPreferredSize(new Dimension(0, 50));
	        
			frame = new JFrame("Holter ECG wavelet analyser");
	        frame.setJMenuBar(menu);
	        frame.addWindowListener
			(
	        	new WindowAdapter() 
				{
	        		public void windowClosing(WindowEvent e) 
	        		{
	        			System.exit(0);
	                }
	            }
	        );	        
	        
	        Toolkit.getDefaultToolkit().setDynamicLayout(false); // Po co to wstawilem ???

		    JPanel pECG = new JPanel(new BorderLayout());
		    	pECG.add(pSubBands, BorderLayout.CENTER);
		    	pECG.add(pQRS, 		BorderLayout.SOUTH);
		    	
		    cPane = frame.getContentPane();	        	        
		    	cPane.add(pSignal, 		BorderLayout.NORTH);
	    		cPane.add(pECG, 		BorderLayout.CENTER);
		        cPane.add(pNavigation, 	BorderLayout.SOUTH);
		        	    	
		    frame.setSize(800, 500);
	        frame.setLocationRelativeTo(null); // Centrowanie
	        frame.setVisible(true); // Wyswietlenie
		}
	}
