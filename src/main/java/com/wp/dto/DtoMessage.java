package com.wp.dto;

import lombok.Data;

public class DtoMessage {
   // private String senderId;
    private String receiverId;
    private String content;

    

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
