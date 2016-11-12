package com.zdh.alphathink.imh.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Panda on 2016/11/1.
 */

public class VoteMsgTextCoder implements VoteMsgCode {
  /*
   * Wire Format "VOTEPROTO" <"v" | "i"> [<RESPFLAG>] <CANDIDATE> [<VOTECNT>]
   * Charset is fixed by the wire format.
   */

    // Manifest constants for encoding
    public static final String MAGIC = "IMH";
    public static final String VOTESTR = "v";
    public static final String INQSTR = "i";
    public static final String RESPONSESTR = "R";
    public static final String ID = "id";
    public static final String NAME= "name";
    public static final String COLOR = "color";
    public static final String TIME = "time";

    public static final String CHARSETNAME = "GBK";
    public static final String DELIMSTR = ",";
    public static final String MH = ":";
    public static final int MAX_WIRE_LENGTH = 2000;

    public byte[] toWire(VoteMsg msg) throws IOException {
        String msgString = MAGIC + DELIMSTR +
                Integer.toString(msg.getType())+MH+ID+MH+Integer.toString(msg.getId())
                +DELIMSTR+NAME+MH+msg.getName()+DELIMSTR+COLOR+MH+Integer.toString(msg.getColor())
                +DELIMSTR+TIME+MH+msg.getTime();
//                 (msg.isInquiry() ? INQSTR : VOTESTR)
//                + DELIMSTR + (msg.isResponse() ? RESPONSESTR + DELIMSTR : "")
//                + Integer.toString(msg.getCandidateID()) + DELIMSTR
//                + Long.toString(msg.getVoteCount());
        byte data[] = msgString.getBytes(CHARSETNAME);
        return data;
    }

    public VoteMsg fromWire(byte[] message) throws IOException {
        ByteArrayInputStream msgStream = new ByteArrayInputStream(message);

        //Scanner环境需要修改
        Scanner s = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));

        int type = 0;
        int id=0;
        int color=0;
        String time="";
        String name="";
        boolean isInquiry;
        boolean isResponse;
        int candidateID;
        long voteCount;
        String token;

        try {
            token = s.next();
            if (!token.equals(MAGIC)) {
                throw new IOException("Bad magic string: " + token);
            }
            token = s.next();
            if (token.equals(VOTESTR)) {
                isInquiry = false;
            } else if (!token.equals(INQSTR)) {
                throw new IOException("Bad vote/inq indicator: " + token);
            } else {
                isInquiry = true;
            }

            token = s.next();
            if (token.equals(RESPONSESTR)) {
                isResponse = true;
                token = s.next();
            } else {
                isResponse = false;
            }
            // Current token is candidateID
            // Note: isResponse now valid
            candidateID = Integer.parseInt(token);
            if (isResponse) {
                token = s.next();
                voteCount = Long.parseLong(token);
            } else {
                voteCount = 0;
            }
        } catch (IOException ioe) {
            throw new IOException("Parse error...");
        }
        return new VoteMsg(type, id,color,name,time);
    }
}
