
public class QRSInfo
{
	public static final int QRS_UNDEFINED = 0;
	public static final int QRS_UNKNOWN = 1;
	public static final int QRS_NORMAL = 2;
	public static final int QRS_PACE = 3; // Stimulator
	public static final int QRS_OTHER = 4;
	public static final int NOT_QRS = 5;

	public static final int MORPH_UNDEFINED = 0;
	public static final int MORPH_QRS = 1;
	public static final int MORPH_R = 2;
	public static final int MORPH_RSR = 3;
	public static final int MORPH_QS = 4;
	public static final int MORPH_RS = 5;
	
/* 
	QRS morphology types:
										Q	R	S	R'
		QRS		 	- +|- + -			1   1   1   0
		QRS-notch	- +|(- +) - + 		
		R		      +|- +				0 	1   0   0
		RSR'	(+ -) +|- +				0   1   1   1
		QS		    - +|-				1   0   1   0
		RS		    + -|+ -				0   1   1   0

*/
	// Type

	public long type;
	
	// Detection parameters
	
	public int timeStart;
	public int timeR;
	public int timeEnd;
	
	public long area;
	
	public int clusterNr;
	public int classifiedAs;
	
	
	QRSInfo() 
	{
		type = QRS_UNDEFINED;
		
		timeStart = 0;
		timeR = 0;
		timeEnd = 0;
		
		area = 0;
		
		clusterNr = 0;
		
		classifiedAs = QRS_UNDEFINED;
	}
	
	QRSInfo(long time_0, long time_R, long time_1, long a) 
	{
		type = NOT_QRS;
		
		timeStart = time_0;
		timeR = time_R;
		timeEnd = time_1;
		
		area = a;
		
		clusterNr = 0;
		
		classifiedAs = QRS_UNDEFINED;
	}

	double width() // In seconds
	{		
		return ((double) timeEnd - (double) timeStart) / 128.0;
	}
	
	boolean isQRS()
	{
		return (type > 0);
	}

	boolean intersectsRange(long t_0, long t_1)
	{
		return 
		(
			(t_0 < timeStart && timeStart < t_1) || 
			(t_0 < timeEnd   && timeEnd   < t_1)
		);
	}
}