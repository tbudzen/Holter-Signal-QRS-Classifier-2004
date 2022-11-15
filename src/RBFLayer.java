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

	public class RBFLayer 
	{
		public int size;
		public RBFNeuron [] neurons;
		public double [] output;		

		RBFLayer(int I, int J)
		{	
			int i;
			
			size = I;
			neurons = new RBFNeuron [I];
			output = new double [I];
			for(i = 0; i < I; i++)
			{				
				neurons[i] = new RBFNeuron();
				neurons[i].init(J);
				output[i] = 0.0;
			}
		}
		
		public void eval(double [] input)
		{
			int i;

			for(i = 0; i < size; i++)
			{
				neurons[i].eval(input);
				output[i] = neurons[i].value; // Fresh
			}
		}
		
		public void randomizeWeights()
		{
			int i;
			
			for(i = 0; i < size; i++)
			{
				neurons[i].randomizeWeights();
			}
		}
		
		public void updateWeights(double GAMMA)
		{
			int i, j;
			RBFNeuron rn;
			
            for(i = 0; i < size; i++)
            {
            	rn = neurons[i];
                for(j = 0; j < rn.size; j++)
                {
                	rn.weights[j] += GAMMA * rn.dw * rn.value_d;
                }
            }
		}
	}
