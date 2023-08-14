package com.test.hua.model;

/**
 * Parent message for both incoming messages and outgoing messages
 */
public class Message {

    private String content;
    private long timestamp;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
