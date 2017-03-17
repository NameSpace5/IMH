package com.zdh.alphathink.imh.Control.Box;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Panda on 2016/12/12.
 */

public class SocketWriteThread implements Runnable {
    private Socket client;
    public SocketWriteThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataOutputStream dos = null;
        BufferedReader br = null;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            IMHMsgTextCoder voteMsgTextCoder = new IMHMsgTextCoder();
            IMHMsg voteMsg = new IMHMsg(true,2, 2, 200, "阿莫西林", "1250");
            byte[] bytes = new byte[20];
            try {
                bytes = voteMsgTextCoder.toWire(voteMsg);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            dos.write(bytes);
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
                try {
                    if (dos != null) {
                        dos.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                    if (client != null) {
                        client = null;
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
    }
}
