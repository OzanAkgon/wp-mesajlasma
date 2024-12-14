package com.wp.service;

import com.wp.model.Message;
import com.wp.model.MessageStatus;

import java.util.List;

public interface IMessageService {
	public List<String> getUniqueSendersByReceiverUsername(String username);
    public Message sendMessage(String senderId, String receiverId, String content);
    public List<Message> getMessages(String senderId, String receiverId);
    public void updateMessageStatus(Long messageId, MessageStatus status);
}
