/*
 * Created on 2006-02-05
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

	public class MexicanHatWaveletFilter implements WaveletFilter 
	{				
		double f(double t, double T, double s) // T - translation, s - scale (power of 2)
		{
			double p, t1;

			// f(s, T, t) = 1 / sqrt(s) * f((t - T) / s)
			
			// pi ^ (-1 / 4) = 0.751125544 (A)
			// 2 / sqrt(3) 	 = 1.154700538 (B)
			// A * B 		 = 0.867325071 
			
			t1 = (t - T) / s;
			
			p = 0.867325071 * Math.pow((1.0 - t1), 2.0) * Math.exp(- t1 * t1 / 2.0);
			p /= Math.sqrt(s);
			//p /= s;	
			
			return p;
		}

		public Channel lowPassFilter(int k, Channel c)
		{
			Channel n_c;
			
			System.out.println("WARNING: MexicanHatWaveletFilter::lowPassFilter() is not implemented !");
			
			n_c = null;
			
			return n_c;
		}
		
		public Channel highPassFilter(int k, Channel c)
		{	
			Channel n_c;
			int i, j, offset, level;
			byte x;
			double y;
			
			System.out.println("MexicanHatWaveletFilter::highPassFilter(" + k + ")");
			
			n_c = new Channel(c.size);	
			
			level = 1 << k; // 2 ^ k
			for(i = 0; i < c.size; i++) // For each sample
			{
				y = 0.0;
		        for(j = -5; j < 5; j++) // For each coefficient
				{
                    offset = i + j;
                    x = c.getData(offset);
                    y += x * f(offset + j, offset, level);
                }    
                n_c.data[i] = (byte) y;
			}
			
			return n_c;
		}
		
		public Channel [] decompose(Channel c, int N)
		{
			Channel [] subBands;
			int i;
			
			System.out.println("Channel::Decompose(" + N + ")");
			
			subBands = new Channel [N];	
			for(i = 0; i < N; i++)
			{
				subBands[i] = highPassFilter(i, c); // Always 'c' - because it is continuous wavelet		
			}			
			return subBands;
		}
	}	
