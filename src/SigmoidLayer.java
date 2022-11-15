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

	public class SigmoidLayer 
	{
		public int size;
		public SigmoidNeuron [] neurons;
		public double [] output;		

		SigmoidLayer(int I, int J)
		{	
			int i;
			
			size = I;
			neurons = new SigmoidNeuron [I];
			output = new double [I];
			for(i = 0; i < I; i++)
			{			
				neurons[i] = new SigmoidNeuron();
				neurons[i].init(J);
				output[i] = 0.0;
			}
		}
		
		public void eval(double [] input, double BETA)
		{
			int i;

			for(i = 0; i < size; i++)
			{
				neurons[i].eval(input, BETA);
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
			SigmoidNeuron sn;
			
            for(i = 0; i < size; i++)
            {
            	sn = neurons[i];
                for(j = 0; j < sn.size; j++)
                {
                	sn.weights[j] += GAMMA * sn.dw * sn.value_d;
                }
            }
		}
	}
