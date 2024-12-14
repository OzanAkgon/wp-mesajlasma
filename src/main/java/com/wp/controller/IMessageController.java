package com.wp.controller;

import com.wp.dto.DtoMessage;
import com.wp.dto.DtoMessageUI;
import com.wp.model.Message;
import com.wp.model.MessageStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IMessageController {
   public ResponseEntity<String> sendMessage(String senderToken,String receiverId,String content);
   public ResponseEntity<List<String>> getUniqueSenders(String usernameToken);
   //public ResponseEntity<Message> sendMessage(DtoMessage request);
   public ResponseEntity<?> getMessages(String senderToken,String receiverId);
   public ResponseEntity<Void> updateMessageStatus(Long messageId, MessageStatus status);
}
