import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class QRSInfoList
{	
	public static final int NOT_INITIALIZED = 0;
	public static final int ANNOTATED 		= 1; // Annotated by expert
	public static final int DETERMINED 		= 2; // Determined by neural net

	public int type;
	public int classifiedAs;
	public int size;

	public QRSInfoNode first;
	public QRSInfoNode last;

	QRSInfoList()
	{	
		type = NOT_INITIALIZED;
		size = 0;
	}

/*
#define ACMAX       41

#define NOTQRS      0 
#define NORMAL      1
#define LBBB        2
#define RBBB        3
#define ABERR       4
#define PVC         5
#define FUSION      6
#define NPC         7
#define APC         8
#define SVPB        9   
#define VESC        10
#define NESC        11
#define PACE        12
#define UNKNOWN     13
#define NOISE       14
#define ARFCT       16
#define CALPULSE    20
#define OTHERS      22
#define BBB         25
#define PACESP      26
#define RHYTHM      28
#define LEARN       30
#define FLAV        31
#define VFON        32
#define VFOFF       33
#define AESC        34
#define NAPC        37
#define PFUS        38
#define PQ          39
#define JPT         40
#define RONT        41

	typedef struct 
	{ 
        	unsigned int ann_num;	// 4
        	unsigned int ann_time;	// 4
        	unsigned char ann_type;	// 1
        	unsigned char sub_type;	// 1
        	unsigned char chan;	// 1
        	unsigned char clus_num;	// 1
        	unsigned char dx;	// 1
        	unsigned char width;	// 1
        	short spare;		// 2
	}

	TOTAL STRUCT SIZE = 4 + 4 + 1 + 1 + 1 + 1 + 1 + 1 + 2 = 16

char db_mp1[ACMAX + 1] =
{
	NOTQRS,   NORMAL,     NORMAL,     NORMAL,     SVPB,
	PVC,      FUSION,     SVPB,       SVPB,       SVPB,
	PVC,      NORMAL,     PACE,       UNKNOWN,    NOTQRS,
	NOTQRS,   ARFCT,      NOTQRS,     NOTQRS,     NOTQRS,
	CALPULSE, NOTQRS,     OTHERS,     NOTQRS,     NOTQRS,
	NORMAL,   NOTQRS,     NOTQRS,     NOTQRS,     NOTQRS,
	LEARN,    PVC,        NOTQRS,     NOTQRS,     NORMAL,
	NOTQRS,   NOTQRS,     NOTQRS,     NORMAL,     NOTQRS,
	NOTQRS,   PVC 
};

	N NORMAL
	S SVPB
	V PVC
	F FUSION     
	P PACE
	U UNKNOWN
	A ARFCT
	C CALPULSE
	X NOTQRS
	O OTHERS     
	L LEARN
*/
	void load(String fName)
	{
		File f;
		InputStream is;
		QRSInfo newInfo;		
		long blockSize, iCount;
		
		int 
			annTime;
		byte 
			annType,
			annSubType,
			channelNr,
			clusterNr,
			dx,
			width;
				
		System.out.println("QRSInfoList::Load(" + fName + ")");	
		try 
		{
			f = new File(fName);
			is = new DataInputStream(f); // WARNING: DataInputStream - not FileInputStream !
			
			blockSize = 16; // 4 + 4 + 1 + 1 + 1 + 1 + 1 + 1 + 2
			fSize = f.length();
			iCount = fSize / blockSize;		
			
			try 
			{
				for(int i = 0; i < iCount; i++)
				{
					// Reading single annotation block
					
					is.skipBytes(4); // SKIP ann_num
					annTime 	= is.readInt();
					annType 	= is.readByte();
					annSubType 	= is.readByte();
					channelNr 	= is.readByte();
					clusterNr 	= is.readByte(); 
					dx 			= is.readByte();
					width 		= is.readByte();
					i.skipBytes(2); // SKIP spare
						
					// Adding new annotation to list
						
					add(new QRSInfo(time0, timeR, time1, area));
				}						
				is.close();
			}
			catch(IOException e1) { e1.printStackTrace(); }				
		} 
		catch(FileNotFoundException e) { e.printStackTrace(); }
	}

	void add(QRSInfo i)
	{
		QRSInfoNode n;

		n = new QRSInfoNode();
		n.info = i;
		n.previous = null;
		n.next = null;

		if(first == null)
		{
			first = n;
		}
		else
		{
			last.next = n;
			n.previous = last;
		}
		last = n;
		
		size ++;
	}
}
