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
 
	public class WaveletFactory 
	{		
		WaveletFilter Haar()
		{
			DiscreteWaveletFilter n_w;
			
			n_w = new DiscreteWaveletFilter(2);
			
			n_w.setCoeffs(0, 1.000000);
			n_w.setCoeffs(1, 1.000000); // ERROR: Was 2.000000
			
			n_w.setScales(0.5, 1.0);
			
			return n_w;
		}
	
		WaveletFilter Daubechies(int N)
		{
			DiscreteWaveletFilter n_w;
			
			n_w = new DiscreteWaveletFilter(2 * N);
			
			switch(N)
			{
				case 2: 
					n_w.setCoeffs(0,  0.6830127);
					n_w.setCoeffs(1,  1.1830127);
					n_w.setCoeffs(2,  0.3169873);
					n_w.setCoeffs(3, -0.1830127);
					break;
					
				case 3: 
					n_w.setCoeffs(0,  0.47046721);
					n_w.setCoeffs(1,  1.14111692);
					n_w.setCoeffs(2,  0.650365);
					n_w.setCoeffs(3, -0.19093442);
					n_w.setCoeffs(4, -0.12083221);
					n_w.setCoeffs(5,  0.0498175);
					break;
				
				case 4: 
					n_w.setCoeffs(0,  0.32580343);
					n_w.setCoeffs(1,  1.01094572);
					n_w.setCoeffs(2,  0.8922014);
					n_w.setCoeffs(3, -0.03967503);
					n_w.setCoeffs(4, -0.26450717);
					n_w.setCoeffs(5,  0.0436163);
					n_w.setCoeffs(6,  0.0465036);
					n_w.setCoeffs(7,  0.01498699);
					break;
				
				case 5: 
					n_w.setCoeffs(0,  0.22641898);
					n_w.setCoeffs(1,  0.85394354);
					n_w.setCoeffs(2,  1.02432694);
					n_w.setCoeffs(3,  0.19576696);
					n_w.setCoeffs(4, -0.34265671);
					n_w.setCoeffs(5, -0.04560113);
					n_w.setCoeffs(6,  0.10970265);
					n_w.setCoeffs(7, -0.00882680);
					n_w.setCoeffs(8, -0.01779187);
					n_w.setCoeffs(9,  4.71742793e-3);
					break;
				
				case 6: 
					n_w.setCoeffs( 0,  0.15774243);
					n_w.setCoeffs( 1,  0.69950381);
					n_w.setCoeffs( 2,  1.06226376);
					n_w.setCoeffs( 3,  0.44583132);
					n_w.setCoeffs( 4, -0.31998660);
					n_w.setCoeffs( 5, -0.18351806);
					n_w.setCoeffs( 6,  0.13788809);
					n_w.setCoeffs( 7,  0.03892321);
					n_w.setCoeffs( 8, -0.04466375);
					n_w.setCoeffs( 9,  7.83251152e-4);
					n_w.setCoeffs(10,  6.75606236e-3);
					n_w.setCoeffs(11, -1.52353381e-3);
					break;		
			}

			n_w.setScales(0.5, 1.0);
			
			return n_w;
		}

		WaveletFilter Symmlet(int N)
		{
			DiscreteWaveletFilter n_w;
			
			n_w = new DiscreteWaveletFilter(2 * N);
			
			switch(N)
			{
				case 2: 
					n_w.setCoeffs( 0,  0.482962913145);
					n_w.setCoeffs( 1,  0.836516303737);
					n_w.setCoeffs( 2,  0.224143868042);
					n_w.setCoeffs( 3, -0.129409522551);
					break;

				case 3: 
					n_w.setCoeffs( 0,  0.332670552951);
					n_w.setCoeffs( 1,  0.806891509313);
					n_w.setCoeffs( 2,  0.459877502119);
					n_w.setCoeffs( 3, -0.135011020010);
					n_w.setCoeffs( 4, -0.085441273882);
					n_w.setCoeffs( 5,  0.035226291882);
					break;

				case 4: 
					n_w.setCoeffs( 0,  0.032223100604);
					n_w.setCoeffs( 1, -0.012603967262);
					n_w.setCoeffs( 2, -0.099219543577);
					n_w.setCoeffs( 3,  0.297857795606);
					n_w.setCoeffs( 4,  0.803738751807);
					n_w.setCoeffs( 5,  0.497618667633);
					n_w.setCoeffs( 6, -0.029635527646);
					n_w.setCoeffs( 7, -0.075765714789);
					break;

				case 5: 
					n_w.setCoeffs( 0,  0.019538882735);
					n_w.setCoeffs( 1, -0.021101834025);
					n_w.setCoeffs( 2, -0.175328089908);
					n_w.setCoeffs( 3,  0.016602105765);
					n_w.setCoeffs( 4,  0.633978963458);
					n_w.setCoeffs( 5,  0.723407690402);
					n_w.setCoeffs( 6,  0.199397533977);
					n_w.setCoeffs( 7, -0.039134249302);
					n_w.setCoeffs( 8,  0.029519490926);
					n_w.setCoeffs( 9,  0.027333068345);
					break;

				case 6: 
					n_w.setCoeffs( 0, -0.007800708325);
					n_w.setCoeffs( 1,  0.001767711864);
					n_w.setCoeffs( 2,  0.044724901771);
					n_w.setCoeffs( 3, -0.021060292512);
					n_w.setCoeffs( 4, -0.072637522786);
					n_w.setCoeffs( 5,  0.337929421728);
					n_w.setCoeffs( 6,  0.787641141030);
					n_w.setCoeffs( 7,  0.491055941927);
					n_w.setCoeffs( 8, -0.048311742586);
					n_w.setCoeffs( 9, -0.117990111148);
					n_w.setCoeffs(10,  0.003490712084);
					n_w.setCoeffs(11,  0.015404109327);
					break;

				case 7: 
					n_w.setCoeffs( 0,  0.010268176709);
					n_w.setCoeffs( 1,  0.004010244872);
					n_w.setCoeffs( 2, -0.107808237704);
					n_w.setCoeffs( 3, -0.140047240443);
					n_w.setCoeffs( 4,  0.288629631752);
					n_w.setCoeffs( 5,  0.767764317003);
					n_w.setCoeffs( 6,  0.536101917092);
					n_w.setCoeffs( 7,  0.017441255087);
					n_w.setCoeffs( 8, -0.049552834937);
					n_w.setCoeffs( 9,  0.067892693501);
					n_w.setCoeffs(10,  0.030515513166);
					n_w.setCoeffs(11, -0.012636303403);
					n_w.setCoeffs(12, -0.001047384889);
					n_w.setCoeffs(13,  0.002681814568);
					break;

				case 8: 
					n_w.setCoeffs( 0,  0.001889950333);
					n_w.setCoeffs( 1, -0.000302920515);
					n_w.setCoeffs( 2, -0.014952258337);
					n_w.setCoeffs( 3,  0.003808752014);
					n_w.setCoeffs( 4,  0.049137179674);
					n_w.setCoeffs( 5, -0.027219029917);
					n_w.setCoeffs( 6, -0.051945838108);
					n_w.setCoeffs( 7,  0.364441894835);
					n_w.setCoeffs( 8,  0.777185751701);
					n_w.setCoeffs( 9,  0.481359651258);
					n_w.setCoeffs(10, -0.061273359068);
					n_w.setCoeffs(11, -0.143294238351);
					n_w.setCoeffs(12,  0.007607487325);
					n_w.setCoeffs(13,  0.031695087811);
					n_w.setCoeffs(14, -0.000542132332);
					n_w.setCoeffs(15, -0.003382415951);
					break;
			}
			
			n_w.setScales(Math.sqrt(2.0) / 2.0, 1.0);

			return n_w;
		}

		WaveletFilter Coiflet(int N)
		{
			DiscreteWaveletFilter n_w;
			
			n_w = new DiscreteWaveletFilter(6 * N);
			
			switch(N)
			{
				case 1: 
					n_w.setCoeffs(0, -0.102859456942);
					n_w.setCoeffs(1,  0.477859456942);
					n_w.setCoeffs(2,  1.205718913884);
					n_w.setCoeffs(3,  0.544281086116);
					n_w.setCoeffs(4, -0.102859456942);
					n_w.setCoeffs(5, -0.022140543057);
					break;

				case 2: 
					n_w.setCoeffs( 0,  0.023175193479);
					n_w.setCoeffs( 1, -0.058640275960);
					n_w.setCoeffs( 2, -0.095279180620);
					n_w.setCoeffs( 3,  0.546042093070);
					n_w.setCoeffs( 4,  1.149364787715);
					n_w.setCoeffs( 5,  0.589734387392);
					n_w.setCoeffs( 6, -0.108171214184);
					n_w.setCoeffs( 7, -0.084052960922);
					n_w.setCoeffs( 8,  0.033488820325);
					n_w.setCoeffs( 9,  0.007935767225);
					n_w.setCoeffs(10, -0.002578406712);
					n_w.setCoeffs(11, -0.001019010797);
					break;

				case 3: 
					n_w.setCoeffs( 0, -0.005364837341);
					n_w.setCoeffs( 1,  0.011006253418);
					n_w.setCoeffs( 2,  0.033167120958);
					n_w.setCoeffs( 3, -0.093015528958);
					n_w.setCoeffs( 4, -0.086441527120);
					n_w.setCoeffs( 5,  0.573006670549);
					n_w.setCoeffs( 6,  1.122570513741);
					n_w.setCoeffs( 7,  0.605967143547);
					n_w.setCoeffs( 8, -0.101540281510);
					n_w.setCoeffs( 9, -0.116392501524);
					n_w.setCoeffs(10,  0.048868188642);
					n_w.setCoeffs(11,  0.022458481925);
					n_w.setCoeffs(12, -0.012739202022);
					n_w.setCoeffs(13, -0.003640917832);
					n_w.setCoeffs(14,  0.001580410202);
					n_w.setCoeffs(15,  0.000659330348);
					n_w.setCoeffs(16, -0.000100385550);
					n_w.setCoeffs(17, -0.000048931468);
					break;
			}

			n_w.setScales(0.5, 1.0);
			
			return n_w;
		}

		WaveletFilter BiOrthogonalSpline(int N, int M) 
		{
			DiscreteWaveletFilter n_w;
			
			n_w = null;
			
			if(N == 1)
			{
				n_w = new DiscreteWaveletFilter(2, 2 * M);
			
				n_w.setHCoeff( 0, 0.707106781);
				n_w.setHCoeff( 1, 0.707106781);
				
				if(M == 3)
				{
					n_w.setGCoeff( 0, -0.088388348);
					n_w.setGCoeff( 1, -0.088388348);
					n_w.setGCoeff( 2,  0.707106781);
					n_w.setGCoeff( 3, -0.707106781);
					n_w.setGCoeff( 4,  0.088388348);
					n_w.setGCoeff( 5,  0.088388348);					
				}
				else
				if(M == 5)
				{
					n_w.setGCoeff( 0,  0.016572815);
					n_w.setGCoeff( 1,  0.016572815);
					n_w.setGCoeff( 2, -0.121533978);
					n_w.setGCoeff( 3, -0.121533978);
					n_w.setGCoeff( 4,  0.707106781);
					n_w.setGCoeff( 5, -0.707106781);					
					n_w.setGCoeff( 6,  0.121533978);
					n_w.setGCoeff( 7,  0.121533978);
					n_w.setGCoeff( 8, -0.016572815);
					n_w.setGCoeff( 9, -0.016572815);					
				}

				n_w.setScales(Math.sqrt(2.0) / 2, 1.0);
			}
			else
			if(N == 2)
			{
				n_w = new DiscreteWaveletFilter(3, 2 * M + 1);
				
				n_w.setHCoeff( 0, 0.353553391);
				n_w.setHCoeff( 1, 0.707106781);
				n_w.setHCoeff( 2, 0.353553391);
				
				if(M == 2)
				{
					n_w.setGCoeff( 0, -0.176776695);	
					n_w.setGCoeff( 1, -0.353553391);	
					n_w.setGCoeff( 2,  1.060660172);	
					n_w.setGCoeff( 3,  0.353553391);	
					n_w.setGCoeff( 4,  0.176776695);		
				}
				else
				if(M == 4)
				{
					n_w.setGCoeff( 0,  0.03314563);	
					n_w.setGCoeff( 1,  0.066291261);	
					n_w.setGCoeff( 2, -0.176776695);	
					n_w.setGCoeff( 3, -0.419844651);	
					n_w.setGCoeff( 4,  0.994368911);		
					n_w.setGCoeff( 5, -0.419844651);	
					n_w.setGCoeff( 6, -0.176776695);	
					n_w.setGCoeff( 7,  0.066291261);	
					n_w.setGCoeff( 8,  0.03314563);	
				}
				else
				if(M == 6)
				{
					n_w.setGCoeff( 0, -0.00690534);	
					n_w.setGCoeff( 1, -0.013810679);	
					n_w.setGCoeff( 2,  0.04695631);	
					n_w.setGCoeff( 3,  0.107723299);	
					n_w.setGCoeff( 4, -0.169871356);		
					n_w.setGCoeff( 5, -0.44746601);	
					n_w.setGCoeff( 6,  0.966747552);	
					n_w.setGCoeff( 7, -0.44746601);	
					n_w.setGCoeff( 8, -0.169871356);	
					n_w.setGCoeff( 9,  0.107723299);	
					n_w.setGCoeff(10,  0.04695631);	
					n_w.setGCoeff(11, -0.013810679);	
					n_w.setGCoeff(12, -0.00690534);	
				}

				n_w.setScales(Math.sqrt(2.0) / 2, 1.0);
			}
			
			if(N == 3 && M == 3)
			{
				n_w = new DiscreteWaveletFilter(4, 8);
				
				n_w.setHCoeff( 0,  0.176776695);
				n_w.setHCoeff( 1,  0.530330086);
				n_w.setHCoeff( 2,  0.530330086);
				n_w.setHCoeff( 3,  0.176776695);
				
				n_w.setGCoeff( 0, -0.066291261);
				n_w.setGCoeff( 1, -0.198873782);
				n_w.setGCoeff( 2,  0.154679608);
				n_w.setGCoeff( 3,  0.994368911);
				n_w.setGCoeff( 4, -0.994368911);
				n_w.setGCoeff( 5, -0.154679608);
				n_w.setGCoeff( 6,  0.198873782);
				n_w.setGCoeff( 7,  0.066291261);

				n_w.setScales(Math.sqrt(2.0) / 2.0, 1.0);
			}
			/*
			 * Ta falka jest chyba bardziej skomplikowana ...
			 * 
			else
			if(N == 4 && M == 4)
			{
				n_w = new DiscreteWaveletFilter(4, 5);
				
				n_w.setHCoeff( 0,  0.788485616614);
				n_w.setHCoeff( 1,  0.418092273333);
				n_w.setHCoeff( 2, -0.040689417620);
				n_w.setHCoeff( 3, -0.064538882646);
				
				n_w.setGCoeff( 0,  0.852698679009);
				n_w.setGCoeff( 1, -0.377402855613);
				n_w.setGCoeff( 2, -0.110624404418);
				n_w.setGCoeff( 3,  0.023849465019);
				n_w.setGCoeff( 4,  0.037828455507);

				n_w.setScales(0.869864452, 1.0); // = 1.0 / 1.14960439886
			}
			*/
			
			return n_w;
		}
	
		WaveletFilter MexicanHat()
		{
			MexicanHatWaveletFilter n_w;

			n_w = new MexicanHatWaveletFilter();

			return n_w;
		}
	}
