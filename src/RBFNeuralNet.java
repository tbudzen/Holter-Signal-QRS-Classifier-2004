import java.io.*;

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

	public class RBFNeuralNet 
	{		
		public int inputSize; // input has no layer, only input vector
		public RBFLayer hiddenLayer;
		private SigmoidLayer outputLayer;
	
		public double beta  = 0.7;		
		public double gamma = 0.6;
		
		RBFNeuralNet(int iSize, int hSize, int sSize) 
		{
			inputSize = iSize;
			hiddenLayer = new RBFLayer(hSize, iSize);	
			outputLayer = new SigmoidLayer(sSize, hSize);
		}
		
        void randomizeWeights()
        {
			hiddenLayer.randomizeWeights();
			outputLayer.randomizeWeights();
		}

        void eval(double [] w, double beta)
        {
            hiddenLayer.eval(w); 
            outputLayer.eval(hiddenLayer.output, beta); 
		}
                	
        double output(int i)
        {
			return outputLayer.output[i];
        }

        double error(int i)
        {
           	return outputLayer.neurons[i].dw;
       	}

		void propagate(double [] i_vector, double [] e_vector, double gamma) // (given vector, expected vector)
        {
	        RBFLayer l, l_next;
            RBFNeuron rn;
			SigmoidNeuron sn;
			int i, j;

	        // Sigmoid (output) layer

            for(i = 0; i < outputLayer.size; i++)
            {
				sn = outputLayer.neurons[i];
               	sn.value_d = (i_vector[i] - e_vector[i]) * sn.value_d;
            }

	        // RBF layer - computation of dValue

            for(i = 0; i < hiddenLayer.size; i++)
            {
                    rn = hiddenLayer.neurons[i];
                    rn.value_d = 0.0;
                                        
					for(j = 0; j < outputLayer.size; j++)
                    {
						sn = outputLayer.neurons[j];
                        rn.dw += rn.value_d * sn.dw * sn.weights[j];
                    }                                	
            }

            // Update of weights - w' = w + gamma * dw * f'(v)

            outputLayer.updateWeights(0.5);
            hiddenLayer.updateWeights(0.5);
        }
		
		void classify(QRSInfo info, double BETA)
		{
			double [] in;
			
			in = new double [2];
			
			in[0] = info.area;
			// in[1] = info.area;
			
			in = null;
			
			eval(in, BETA);
		}
		
		// Format of file:
		//		< Size of input vector (N) >
		// 		< Number of RBF neurons (M) >
		//		< Center of RBF neuron 1 >
		//		...
		//		< Center of RBF neuron N >
		// 		< RBF neuron 1, weight 1 >
		// 		...
		// 		< RBF neuron 1, weight N >
		//		...
		// 		< RBF neuron M, weight 1 >
		// 		...
		// 		< RBF neuron M, weight N >
		//		< Sigm. neuron weight 1 >
		//		...
		//		< Sigm. neuron weight M >
		
		// 		Total: 2 + N * M + M = 2 + (N + 1) * M rows
		
		//		Here: N = 2, M = 2, size = 8 rows
		
		void save(String fName)
		{
			int i, j;
			
			try
			{
				FileOutputStream os = new FileOutputStream(fName);				
				OutputStreamWriter ow = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(ow);
				
				try
				{
					bw.write(Integer.toString(inputSize)); bw.newLine();
					bw.write(Integer.toString(hiddenLayer.size)); bw.newLine();
					for(i = 0; i < hiddenLayer.size; i++)
					{
						for(j = 0; j < inputSize; j++)
						{
							bw.write(Double.toString(hiddenLayer.neurons[i].weights[j])); 
							bw.write(" ");
						}					
						bw.newLine();	
					}
					for(i = 0; i < outputLayer.size; i++)
					{
						bw.write(Double.toString(outputLayer.neurons[0].weights[i])); // WARNING: 0 !
					}
				}
				catch(IOException e0) {};
			}
			catch(FileNotFoundException e1) {};			
		}
		
		// Centers !!!
		
		void load(String fName)
		{
			int i, j;
			
			try
			{
				FileInputStream is = new FileInputStream(fName);				
				InputStreamReader ir = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(ir);
				
				try
				{
					inputSize = Integer.parseInt(br.readLine());
					
					hiddenLayer = new RBFLayer(Integer.parseInt(br.readLine()), inputSize);
					for(i = 0; i < hiddenLayer.size; i++)
					{
						for(j = 0; j < inputSize; j++)
						{
							hiddenLayer.neurons[i].weights[j] = Double.parseDouble(br.readLine());
						}				
					}
					
					outputLayer = new SigmoidLayer(1, hiddenLayer.size); // WARNING: 1 !
					for(i = 0; i < hiddenLayer.size; i++)
					{
						hiddenLayer.neurons[0].weights[i] = Double.parseDouble(br.readLine()); // WARNING: 0 !
					}
				}
				catch(IOException e0) {};
			}
			catch(FileNotFoundException e1) {};			
		}
    }
	