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

		public class RBFNeuron 
		{
        	public double [] weights; // INPUT weights
        	public int size;

        	public double value;   // Computed output value (with activation function)
        	public double value_d; // Computed output value (with 1st derivative of a. f.)
        	public double dw;   // Difference of weight  (while propagating error)

        	public double rCenter;
        	public double rRadius;
        	
			double f(double x)
			{
				double d, p;
				
				d = x - rCenter;
				p = Math.exp( - d * d / (rRadius * rRadius));
				
				return p;
			}

			double fd(double x)
			{
				double d, p;
				
				d = x - rCenter;				
				p = f(x) * - 2.0 * d / (rRadius * rRadius); // f(x)
				
				return p;
			}
		
			public void setParams(double c, double r)
			{
				rCenter = c;
				rRadius = r;
			}
			
	        public void init(int N)
        	{
        		int i;
        	
        		size = N;
        		weights = new double [N];
        		for(i = 0; i < N; i++)
        		{
        			weights[i] = 0.0;
        		}
        	
        		value = 0.0;
        		value_d = 0.0;
        		dw = 0.0;
        	}
        
        	public void randomizeWeights() // Can't be in init() -> multiple training-passes
        	{
        		int i;
        	
        		for(i = 0; i < size; i++)
        		{
        			weights[i] = Math.random();
        		}
        	}
		
			public void eval(double [] input)
			{
				double v;
				int i;
	
				v = 0.0;
				for(i = 0; i < size; i++)
				{
					v += weights[i] * input[i];
				}

				value = f(v); // TODO
				value_d = fd(v); // TODO
			}	
		}
