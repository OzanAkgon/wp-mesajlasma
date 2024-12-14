package com.wp.repository;

import com.wp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);
    List<Message> findByReceiverId(String receiverId);

    @Query(value = "SELECT DISTINCT ON (sender_id) sender_id " +
            "FROM wp.message " +
            "WHERE receiver_id = :receiverId " +
            "ORDER BY sender_id, timestamp DESC", 
    nativeQuery = true)
List<String> findDistinctSendersByReceiverId(@Param("receiverId") String receiverId);
    
    @Query(value = "SELECT * FROM wp.message " +
            "WHERE " +
            "((receiver_id = :receiverId AND sender_id = :senderId) " +
            "OR (receiver_id = :senderId AND sender_id = :receiverId)) " +
            "ORDER BY timestamp DESC"
, 
    nativeQuery = true)
List<Message> findMessagesByReceiverOrSender(
 @Param("receiverId") String receiverId, 
 @Param("senderId") String senderId
);
}
