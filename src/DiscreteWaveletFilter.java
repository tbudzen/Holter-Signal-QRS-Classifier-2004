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

	public class DiscreteWaveletFilter implements WaveletFilter 
	{
		int h_size;
		int g_size;
		
		double [] h;
		double [] g;
		
		double h_scale;
		double g_scale;
				
		public DiscreteWaveletFilter(int N)
		{
			int i;
			
			h_size = N;
			g_size = N;
			h = new double [N];
			g = new double [N];
			for(i = 0; i < N; i++)
			{
				h[i] = 0.0;
				g[i] = 0.0;
			}
			h_scale = 1.0;
			g_scale = 1.0;
		}
			
		public DiscreteWaveletFilter(int hSize, int gSize)
		{
			int i;
			
			h_size = hSize;
			g_size = gSize;
			h = new double [hSize];
			g = new double [gSize];
			for(i = 0; i < hSize; i++)
			{
				h[i] = 0.0;
			}
			for(i = 0; i < gSize; i++)
			{
				g[i] = 0.0;
			}
			h_scale = 1.0;
			g_scale = 1.0;
		}
		
		void setScales(double hS, double gS)
		{
			h_scale = hS;
			g_scale = gS;
		}
		
		void setCoeffs(int i, double x)
		{			
			h[i] = x;
			if((i % 2) == 1)
			{
				g[i] = - h[h_size - i - 1];
			}
			else
			{
				g[i] = h[h_size - i - 1];
			}
		}		
	
		void setHCoeff(int i, double x)
		{
			h[i] = x;
		}

		void setGCoeff(int i, double x)
		{
			g[i] = x;
		}

		public Channel lowPassFilter(int k, Channel c)
		{	
			Channel n_c;
			int i, j, offset, level;
			double y;
			
			System.out.println("Channel::lowPassFilter(" + k + ")");
			
			n_c = new Channel(c.size);
			
			level = 1 << k; // 2 ^ k
			for(i = 0; i < c.size; i++)
			{
				y = 0.0;
		        for(j = 0; j < h_size; j++)
                {
					// offset = i + level * j - level * (h_size - 1) / 2;
					offset = i + level * (j - h_size / 2);	
                    y += c.getData(offset) * h[j];
                }
                y *= h_scale;
                n_c.data[i] = (byte) y;
			}
			
			return n_c;		
		}		

		public Channel highPassFilter(int k, Channel c)
		{	
			Channel n_c;
			int i, j, offset, level;
			double y;
			
			System.out.println("Channel::highPassFilter(" + k + ")");
			
			n_c = new Channel(c.size);
		
			level = 1 << k; // 2 ^ k	
			for(i = 0; i < c.size; i++)
			{
				y = 0.0;
		        for(j = 0; j < g_size; j++)
                {
					// offset = i + level * j - level * (g_size - 1) / 2;
					offset = i + level * (j - g_size / 2);		
                    y += c.getData(offset) * g[j];
                }
                y *= g_scale;
                n_c.data[i] = (byte) y;
			}
			
			return n_c;		
		}
		
		public Channel [] decompose(Channel c, int N)
		{
			Channel [] subBands;
			Channel c_tmp;
			int i;
			
			System.out.println("Channel::Decompose(" + N + ")");
			
			subBands = new Channel [N];	
			
			subBands[0] = highPassFilter(0, c);
			c_tmp = lowPassFilter(0, c);
			for(i = 1; i < N; i++)
			{
				subBands[i] = highPassFilter(i, c_tmp);				
				if(i < N - 1) 
				{
					c_tmp = lowPassFilter(i, c_tmp);
				}
			}			
			return subBands;
		}
	}	
