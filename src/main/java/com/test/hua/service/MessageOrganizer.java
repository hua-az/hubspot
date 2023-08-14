package com.test.hua.service;

import com.test.hua.model.incoming.IncomingMessage;
import com.test.hua.model.incoming.MessageHistory;
import com.test.hua.model.User;
import com.test.hua.model.outgoing.Conversation;
import com.test.hua.model.outgoing.LatestMessage;
import com.test.hua.model.outgoing.Summary;

import java.util.*;

/**
 * Hosts the core business logic of analyzing incoming data and organizing them into the required format
 */
public class MessageOrganizer {
    private MessageHistory messageHistory;
    private String ownerId;
    private Map<String, User> userMap;      // All users, with userId as key, and user as value
    private Map<String, PriorityQueue<IncomingMessage>> messageMap; // PriorityQueue to keep latest message first for each user

    public MessageOrganizer(MessageHistory messageHistory) {
        if (messageHistory == null) {
            throw new IllegalArgumentException("Message history cannot be null.");
        }
        this.messageHistory = messageHistory;
        organize();
    }

    private void organize() {
        ownerId = messageHistory.getUserId();
        organizeUsers();
        organizeMessages();
    }

    private void organizeUsers() {
        userMap = new HashMap<>();
        if (messageHistory.getUsers() != null) {
            for (User user : messageHistory.getUsers()) {
                userMap.put(user.getId(), user);
            }
        }
    }

    private void organizeMessages() {
        messageMap = new HashMap<>();
        if (messageHistory.getMessages() != null) {
            for (IncomingMessage message : messageHistory.getMessages()) {
                // Only records the ID of the other message user
                String otherUserId;
                if (message.getFromUserId().equals(ownerId)) {
                    otherUserId = message.getToUserId();
                } else {
                    otherUserId = message.getFromUserId();
                }
                PriorityQueue<IncomingMessage> messageList = messageMap.get(otherUserId);
                if (messageList == null) {
                    messageList = new PriorityQueue<>((msg1, msg2) -> Long.compare(msg2.getTimestamp(), msg1.getTimestamp()));
                }
                messageList.add(message);
                messageMap.put(otherUserId, messageList);
            }
        }
    }

    // Finding the latest message for each user
    public Summary getMessageSummary(){
        List<Conversation> conversations = new ArrayList<>();
        for (Map.Entry<String, PriorityQueue<IncomingMessage>> element : this.messageMap.entrySet()) {
            PriorityQueue<IncomingMessage> incomingMessageQueue = element.getValue();
            if (!incomingMessageQueue.isEmpty()) {
                IncomingMessage incomingMessage = incomingMessageQueue.peek();
                int messageCount = incomingMessageQueue.size();
                conversations.add(createLatestMessage(incomingMessage, messageCount));
            }
        }
        return new Summary(conversations);
    }

    private Conversation createLatestMessage(IncomingMessage incomingMessage, int messageCount) {
        Conversation conversation = new Conversation();

        LatestMessage latestMessage= new LatestMessage();
        latestMessage.setUserId(incomingMessage.getFromUserId());
        latestMessage.setTimestamp(incomingMessage.getTimestamp());
        latestMessage.setContent(incomingMessage.getContent());
        conversation.setMostRecentMessage(latestMessage);

        String otherUserId;
        if (!incomingMessage.getFromUserId().equals(this.ownerId)) {
            otherUserId = incomingMessage.getFromUserId();
        } else {
            otherUserId = incomingMessage.getToUserId();
        }

        User otherUser = userMap.get(otherUserId);
        conversation.setAvatar(otherUser.getAvatar());
        conversation.setFirstName(otherUser.getFirstName());
        conversation.setLastName(otherUser.getLastName());
        conversation.setTotalMessages(messageCount);
        conversation.setUserId(otherUserId);

        return conversation;
    }
}
