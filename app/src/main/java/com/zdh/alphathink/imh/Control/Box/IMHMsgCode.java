package com.zdh.alphathink.imh.Control.Box;

import java.io.IOException;

/**
 * Created by Panda on 2016/11/1.
 */

public interface IMHMsgCode {
    byte[] toWire(IMHMsg msg) throws IOException;
    IMHMsg fromWire(byte[] input) throws IOException;
}
