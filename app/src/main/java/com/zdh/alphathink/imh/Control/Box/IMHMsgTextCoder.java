package com.zdh.alphathink.imh.Control.Box;

import com.zdh.alphathink.imh.Bean.CodeTransformation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Panda on 2016/12/12.
 */

public class IMHMsgTextCoder implements IMHMsgCode {

    public static final String MAGIC = "IMH";
    public static final String INQSTR = "q";
    public static final String SETSTR = "s";
    public static final String ID = "i";
    public static final String NAME= "n";
    public static final String COLOR = "c";
    public static final String TIME = "t";
    public static final String CHARSETNAME = "GBK";
    public static final String DELIMSTR = " ";
    public static final String symbol = "@";

    public byte[] toWire(IMHMsg msg) throws IOException {
        String msgString = MAGIC + DELIMSTR +(msg.isInquiry() ? INQSTR : SETSTR)+DELIMSTR
                +DELIMSTR+ID+DELIMSTR+Integer.toString(msg.getId())
                +DELIMSTR+NAME+DELIMSTR+msg.getName()
                +DELIMSTR+COLOR+DELIMSTR+Integer.toString(msg.getColor())
                +DELIMSTR+TIME+DELIMSTR+msg.getTime()+symbol;
//                 (msg.isInquiry() ? INQSTR : VOTESTR)
//                + DELIMSTR + (msg.isResponse() ? RESPONSESTR + DELIMSTR : "")
//                + Integer.toString(msg.getCandidateID()) + DELIMSTR
//                + Long.toString(msg.getVoteCount());
        byte data[] = msgString.getBytes(CHARSETNAME);
        return data;
    }

    public IMHMsg fromWire(byte[] message) throws IOException {
        ByteArrayInputStream msgStream = new ByteArrayInputStream(message);

        //Scanner以空格为结束标志
        Scanner s = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));

        int type = 0;
        int id=0;
        int color=0;
        String time="";
        String name="";
        String namecode = "";
        boolean isInquiry;
        String token;

        try {
            token = s.next();//IMH判断
            if (!token.equals(MAGIC)) {
                throw new IOException("Bad magic string: " + token);
            }
            token = s.next();//q或s判断，查询类或者设置类
            if (token.equals(SETSTR)) {
                isInquiry = true;
            } else if (!token.equals(INQSTR)) {
                throw new IOException("Bad vote/inq indicator: " + token);
            } else {
                isInquiry = false;
            }


            if (isInquiry) {
                token = s.next();
                id = Integer.parseInt(s.next());//ID
                token = s.next();
                namecode = s.next();//名字
                name = CodeTransformation.Unicode2String(namecode);
                token = s.next();
                color = Integer.parseInt(s.next());//颜色
                token = s.next();
                time = s.next();//时间
            }
        } catch (IOException ioe) {
            throw new IOException("Parse error...");
        }
        return new IMHMsg(true,type, id,color,name,time);
    }
}

