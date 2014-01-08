package cps.lab.signal.operation.base;

import cps.lab.signal.Signal;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 17:06
 */
public abstract class Transform {

	private long startTime;
	private long elapsedTime;
	
    protected abstract Signal transformBody(Signal signal);
    
    public int getTransformTime(){
    	return (int) this.elapsedTime;
    }
    
    public Signal transform(Signal signal){
		startTime = System.currentTimeMillis();
    	Signal returnSignal = transformBody(signal);
    	elapsedTime = System.currentTimeMillis() - startTime;
    	return returnSignal;
    	
    }
    
    
}
