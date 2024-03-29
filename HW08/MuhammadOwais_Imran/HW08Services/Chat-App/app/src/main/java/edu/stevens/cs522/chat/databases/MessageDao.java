package edu.stevens.cs522.chat.databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.stevens.cs522.chat.entities.Message;

// TODO add annotations for Repository pattern
// done TODO
@Dao
public interface MessageDao {

    @Query("SELECT * FROM message WHERE message.chatroom=:chatroom")
    public abstract LiveData<List<Message>> fetchAllMessages(String chatroom);

    @Query("SELECT * FROM message WHERE message.sender=:peerName")
    public LiveData<List<Message>> fetchMessagesFromPeer(String peerName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void persist(Message message);

}
