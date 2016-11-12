package com.zdh.alphathink.imh.util;

import java.io.IOException;

/**
 * Created by Panda on 2016/11/1.
 */

public interface VoteMsgCode {
    byte[] toWire(VoteMsg msg) throws IOException;
    VoteMsg fromWire(byte[] input) throws IOException;
}
