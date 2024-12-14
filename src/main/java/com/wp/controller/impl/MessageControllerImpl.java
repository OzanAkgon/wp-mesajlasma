package com.wp.controller.impl;

import com.wp.controller.IMessageController;
import com.wp.dto.DtoMessage;
import com.wp.dto.DtoMessageUI;
import com.wp.jwt.JwtService;
import com.wp.model.Message;
import com.wp.model.MessageStatus;
import com.wp.service.IMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageControllerImpl implements IMessageController {
	
	@Autowired
    private IMessageService messageService;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	@GetMapping("/senders")
    public ResponseEntity<List<String>> getUniqueSenders(@RequestHeader("Authorization") String usernameToken) {
		String token = usernameToken.replace("Bearer ", "");
    	String username = jwtService.getUsernameByToken(token);
        try {
            List<String> uniqueSenders = messageService.getUniqueSendersByReceiverUsername(username);
            return ResponseEntity.ok(uniqueSenders);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    	

	

    //@Override
    //@PostMapping("/send")
   // public ResponseEntity<Message> sendMessage(@RequestBody DtoMessage request) {
    //    Message message = messageService.sendMessage(
         //   request.getSenderId(),
        //    request.getReceiverId(),
           // request.getContent()
       // );
     //   return ResponseEntity.ok(message);
 //   }


    @Override
    @GetMapping("/conversation")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getMessages(@RequestHeader("Authorization") String usernameToken, @RequestParam String receiverId) {
    	String token = usernameToken.replace("Bearer ", "");
    	String username = jwtService.getUsernameByToken(token);
        List<Message> messages = messageService.getMessages(
        		username,
        		receiverId
        		);
        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No messages found between sender and receiver.");
        }
        return ResponseEntity.ok(messages);
    }

    @Override
    @PutMapping("/{messageId}/status")
    public ResponseEntity<Void> updateMessageStatus(@PathVariable Long messageId,
                                                     @RequestParam MessageStatus status) {
        messageService.updateMessageStatus(messageId, status);
        return ResponseEntity.ok().build();
    }

	@Override
	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestHeader("Authorization") String senderToken,@RequestParam String receiverId,@RequestParam String content) {
		    	String token = senderToken.replace("Bearer ", "");
		    	String username = jwtService.getUsernameByToken(token);
		    	Message message = messageService.sendMessage(
		    	            username,
		    	            receiverId,
		    	            content
		    	        );
		    	 return ResponseEntity.ok("You have successfully joined the event");
		    }
		
	}

