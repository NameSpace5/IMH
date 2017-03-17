package com.zdh.alphathink.imh.Control.Box;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Panda on 2016/12/12.
 */

public class SocketReadThread implements Runnable {
        private Socket client;

        public SocketReadThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            DataInputStream dis = null;
            try {
                while (true) {
                    //读取客户端数据
                    dis = new DataInputStream(client.getInputStream());
                    String receiver = dis.readUTF();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (dis != null) {
                        dis.close();
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

