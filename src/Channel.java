import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

	public class Channel 
	{	
		public long size;
		public byte [] data; // unsigned
		public String name;
		
		public static final int
			RAW				= 0,
			ECG				= 1,
			ECG_SUBBAND		= 2,
			STIMULATOR		= 3,
			QRS_COMPLEXES	= 4;
				
		int channelType; 
		
		// private long range_0, range_1;
		
		Channel()
		{	
			channelType = RAW;
			name = "";
			size = 0;
		}
		
		Channel(long N)
		{
			alloc(N);
			size = N;
		}
		
		void treatAsRaw()			{ channelType = RAW; }
		void treatAsECG()			{ channelType = ECG; }
		void treatAsECGSubBand() 	{ channelType = ECG_SUBBAND; }
		void treatAsStimulator() 	{ channelType = STIMULATOR; }
		void treatAsQRSComplexes() 	{ channelType = QRS_COMPLEXES; }
		
		boolean isRaw() 			{ return (channelType == RAW); }
		boolean isECG() 			{ return (channelType == ECG); }
		boolean isECGSubBand() 		{ return (channelType == ECG_SUBBAND); }
		boolean isStimulator() 		{ return (channelType == STIMULATOR); }
		boolean isQRSComplexes() 	{ return (channelType == QRS_COMPLEXES); }
		
//		void SetRange(long r_0, long r_1)
//		{
//			range_0 = r_0;
//			range_1 = r_1;
//		}

		byte getData(int i)
		{
		    if(i < 0 || i >= size)
            {
                return 0;
            }
		    
            return data[i];
		}

		byte getAbsData(int i)
		{
		    if(i < 0 || i >= size)
            {
                return 0;
            }
            
		    return (byte) Math.abs(data[i]);
		}
		
		void clear()
		{
			int i;
			
			for(i = 0; i < size; i++)
			{				
				data[i] = 0;
			}
		}

		void abs()
		{
			int i;
			
			for(i = 0; i < size; i++)
			{				
				data[i] = (byte) Math.abs(data[i]);
			}
		}
		
		void copyFrom(Channel c)
		{
			int i;
			
			for(i = 0; i < size; i++)
			{				
				data[i] = c.data[i];
			}
		}
		
		void getSigns(Channel c)
		{
			int i;
			
			for(i = 0; i < size; i++)
			{				
				if(c.data[i] >= 0)
				{
					data[i] = 1;
				}
				else
				{
					data[i] = -1;
				}
			}			
		}
		
		void alloc(long N)
		{			
			System.out.println("Channel::Alloc(" + N + ")");
			
			data = new byte [(int) N];
			size = N;
		}
		
		void free()
		{
			data = null;
		}
		
		Channel fragment(int x_0, int x_1)
		{
			Channel n_c;
			int i, k;
			
			n_c = new Channel(x_1 - x_0 + 1);	

			for(i = x_0, k = 0; i <= x_1; i++, k++)
			{
				n_c.data[k] = data[i];
			}
			
			return n_c;
		}
		
		void load(String fName, int channelCount, int channelNr, int fragmentNr)
		{			
			File f;
			InputStream is;
			long N;
			
			System.out.println
			(
				"Channel::Load(" + fName + ", " + channelCount + ", " + channelNr + ", " + fragmentNr + ")"
			);
			
			free();	
			try 
			{
				f = new File(fName);
				is = new FileInputStream(f);		

				N = 230400; // 128 samples * 60 seconds * 30 minutes;
				
				alloc(N);
				name = fName;				
				try 
				{
					if(channelCount == 1)
					{						
						is.read(data);
					}
					else
					{
						is.skip(channelNr);	
						is.skip(3 * 230400 * fragmentNr);
						for(int i = 0; i < size; i++)
						{
							data[i] = (byte) (127 + is.read());
							is.skip(channelCount - 1);
						}						
					}
					is.close();
				} 
				catch(IOException e1) { e1.printStackTrace(); }				
			} 
			catch(FileNotFoundException e) { e.printStackTrace(); }
		}	

        	void sineWave(int N, double amp, double scale)
        	{
                	int i;

                	alloc(N);
                	for(i = 0; i < N; i++)
                	{
                        	data[i] = (byte) (amp * (                                        
                        		Math.sin(scale * N / ((i + 1) / 1500.0) / 10.0 * 3.1415926 / 180.0) +
                                Math.sin(scale * 5*i * 3.1415926 / 180.0) / 5.0) / 3.0);
                	}
        	}
           
		void save(String fName)
		{		
			File f;
			OutputStream os;	
			int i;			
			
			try 
			{
				System.out.println("Channel::Save(" + fName + ")");
				f = new File(fName);
				os = new FileOutputStream(f);
				try 
				{
					for(i = 0; i < size; i++)
					{
						os.write(data[i] - 127);
					}				
					os.close();
				} 
				catch (IOException e1) { e1.printStackTrace(); }				
			}
			catch(FileNotFoundException e) { e.printStackTrace(); }
		}
		
		Channel [] decompose(int N, WaveletFilter f)
		{
			Channel [] c;
			Channel c_tmp;
			int i;
			
			System.out.println("Channel::Decompose(" + N + ")");
			
			c = new Channel [N];	
			c[0] = f.lowPassFilter(0, this);
			c[1] = f.highPassFilter(0, this);
			c_tmp = c[0];
			for(i = 2; i < N; i++)
			{
				c[i] = f.highPassFilter(i - 1, c_tmp);				
				if(i < N - 1) 
				{
					c_tmp = f.lowPassFilter(i - 1, c_tmp);
				}
			}			
			return c;
		}
	}
