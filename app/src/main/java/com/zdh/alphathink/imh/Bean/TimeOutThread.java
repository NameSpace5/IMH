package com.zdh.alphathink.imh.Bean;

/**
 * Created by Panda on 2016/11/29.
 */

public class TimeOutThread extends Thread {
    private long timeOut;
    private boolean isCanceled = false;
    private TimeOutException timeOutException;
    public TimeOutThread(long timeOut, TimeOutException timeOutError){
        super();
        this.timeOut = timeOut;
        this.timeOutException = timeOutError;
        this.setDaemon(true);
    }
    public synchronized void cancel(){
        isCanceled = true;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(timeOut);
            if (!isCanceled)
                throw timeOutException;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
