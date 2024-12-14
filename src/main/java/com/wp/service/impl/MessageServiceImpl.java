package com.wp.service.impl;

import com.wp.model.Message;
import com.wp.model.MessageStatus;
import com.wp.repository.MessageRepository;
import com.wp.service.IMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {
	
	@Autowired
    private MessageRepository messageRepository;
	
	@Override
	public List<String> getUniqueSendersByReceiverUsername(String username) {
	        

	        // Gönderenleri al
	        return messageRepository.findDistinctSendersByReceiverId(username);
	    }

    

    @Override
    public Message sendMessage(String senderId, String receiverId, String content) {
        Message message = new Message(senderId, receiverId, content, LocalDateTime.now(), MessageStatus.SENT);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(String senderId, String receiverId) {
        return messageRepository.findMessagesByReceiverOrSender(senderId, receiverId);
    }

    @Override
    public void updateMessageStatus(Long messageId, MessageStatus status) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setStatus(status);
        messageRepository.save(message);
    }
}
