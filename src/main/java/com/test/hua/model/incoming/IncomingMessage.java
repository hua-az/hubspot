package com.test.hua.model.incoming;

import com.test.hua.model.Message;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class IncomingMessage extends Message {

    private String fromUserId;
    private String toUserId;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
