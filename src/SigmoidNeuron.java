
	public class SigmoidNeuron 
	{
    	public double [] weights; // Wagi wejsciowe
    	public int size; // Liczba wag
    	public double value; // Wyliczona wartosc wg f. aktywacji
    	public double value_d; // Wyliczona wartosc wg pochodnej f. aktywacji
    	public double dw;   // Przyrost wagi (w trakcie propagacji)
                
		double f(double x, double beta)
        {
			return 2.0 / (1.0 + Math.exp(- beta * x)) - 1.0;
        }

        double fd(double x, double beta)
        {
            return 2.0 * beta * Math.exp(- beta * x) / (1.0 + Math.exp(- beta * x));
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
        
    	public void randomizeWeights()
    	{
    		int i;
    	
    		for(i = 0; i < size; i++)
    		{
    			weights[i] = Math.random();
    		}
    	}
    
        public void eval(double [] input, double beta)
        {
        	int i;
			double v;
			
			v = 0.0;
    		for(i = 0; i < size; i++)
    		{
    			v += weights[i] * input[i];
    		}
			
			value = f(v, beta);
			value_d = fd(v, beta);
        }
	}
	