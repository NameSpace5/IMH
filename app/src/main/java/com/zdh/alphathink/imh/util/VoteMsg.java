package com.zdh.alphathink.imh.util;

/**
 * Created by Panda on 2016/11/1.
 */

public class VoteMsg {
    private boolean isInquiry; // true 查询类; false 设置类
    private int type;
    private int id;
    private int color;
    private String name;
    private String time;



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    private boolean isResponse;// true if response from server
    private int candidateID;   // in [0,1000]
    private long voteCount;    // nonzero only in response

    public static final int MAX_CANDIDATE_ID = 1000;

    public VoteMsg(int type, int id, int color, String name,String time)
            throws IllegalArgumentException {
//        // check invariants
//        if (voteCount != 0 && !isResponse) {
//            throw new IllegalArgumentException("Request vote count must be zero");
//        }
//        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
//            throw new IllegalArgumentException("Bad Candidate ID: " + candidateID);
//        }
//        if (voteCount < 0) {
//            throw new IllegalArgumentException("Total must be >= zero");
//        }
        this.type = type;
        this.id = id;
        this.color = color;
        this.time = time;
        this.name = name;
    }

    public void setInquiry(boolean isInquiry) {
        this.isInquiry = isInquiry;
    }

    public void setResponse(boolean isResponse) {
        this.isResponse = isResponse;
    }

    public boolean isInquiry() {
        return isInquiry;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setCandidateID(int candidateID) throws IllegalArgumentException {
        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("Bad Candidate ID: " + candidateID);
        }
        this.candidateID = candidateID;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setVoteCount(long count) {
        if ((count != 0 && !isResponse) || count < 0) {
            throw new IllegalArgumentException("Bad vote count");
        }
        voteCount = count;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public String toStr() {
        String res = (isInquiry ? "inquiry" : "vote") + " for candidate " + candidateID;
        if (isResponse) {
            res = "response to " + res + " who now has " + voteCount + " vote(s)";
        }
        return res;
    }
}