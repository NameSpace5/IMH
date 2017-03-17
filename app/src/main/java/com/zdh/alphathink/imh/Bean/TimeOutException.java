package com.zdh.alphathink.imh.Bean;


/**
 * Created by Panda on 2016/11/29.
 */

public class TimeOutException extends RuntimeException {
    private static final long serialVersionUID = -8078853655388692688L;
    public TimeOutException(String errMessage){
        super(errMessage);
    }
}
