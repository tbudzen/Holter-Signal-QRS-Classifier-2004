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

	public interface WaveletFilter 
	{		
		Channel lowPassFilter(int k, Channel c);
		Channel highPassFilter(int k, Channel c);
		Channel [] decompose(Channel c, int k);
	}	
