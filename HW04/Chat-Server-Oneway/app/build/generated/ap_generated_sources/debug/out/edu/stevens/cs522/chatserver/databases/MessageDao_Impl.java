package edu.stevens.cs522.chatserver.databases;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.stevens.cs522.chatserver.entities.DateConverter;
import edu.stevens.cs522.chatserver.entities.Message;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  public MessageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `Message` (`id`,`chatroom`,`messageText`,`timestamp`,`latitude`,`longitude`,`sender`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Message entity) {
        statement.bindLong(1, entity.id);
        if (entity.chatroom == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.chatroom);
        }
        if (entity.messageText == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.messageText);
        }
        final Long _tmp = DateConverter.dateToTimestamp(entity.timestamp);
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        if (entity.latitude == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.latitude);
        }
        if (entity.longitude == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.longitude);
        }
        if (entity.sender == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.sender);
        }
      }
    };
  }

  @Override
  public void persist(final Message message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Message>> fetchAllMessages() {
    final String _sql = "SELECT * FROM message";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"message"}, false, new Callable<List<Message>>() {
      @Override
      @Nullable
      public List<Message> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChatroom = CursorUtil.getColumnIndexOrThrow(_cursor, "chatroom");
          final int _cursorIndexOfMessageText = CursorUtil.getColumnIndexOrThrow(_cursor, "messageText");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Message _item;
            _item = new Message();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfChatroom)) {
              _item.chatroom = null;
            } else {
              _item.chatroom = _cursor.getString(_cursorIndexOfChatroom);
            }
            if (_cursor.isNull(_cursorIndexOfMessageText)) {
              _item.messageText = null;
            } else {
              _item.messageText = _cursor.getString(_cursorIndexOfMessageText);
            }
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _item.timestamp = DateConverter.fromTimestamp(_tmp);
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _item.latitude = null;
            } else {
              _item.latitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _item.longitude = null;
            } else {
              _item.longitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _item.sender = null;
            } else {
              _item.sender = _cursor.getString(_cursorIndexOfSender);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Message>> fetchMessagesFromPeer(final String peerName) {
    final String _sql = "SELECT * from message WHERE message.sender=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (peerName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, peerName);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"message"}, false, new Callable<List<Message>>() {
      @Override
      @Nullable
      public List<Message> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChatroom = CursorUtil.getColumnIndexOrThrow(_cursor, "chatroom");
          final int _cursorIndexOfMessageText = CursorUtil.getColumnIndexOrThrow(_cursor, "messageText");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Message _item;
            _item = new Message();
            _item.id = _cursor.getLong(_cursorIndexOfId);
            if (_cursor.isNull(_cursorIndexOfChatroom)) {
              _item.chatroom = null;
            } else {
              _item.chatroom = _cursor.getString(_cursorIndexOfChatroom);
            }
            if (_cursor.isNull(_cursorIndexOfMessageText)) {
              _item.messageText = null;
            } else {
              _item.messageText = _cursor.getString(_cursorIndexOfMessageText);
            }
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _item.timestamp = DateConverter.fromTimestamp(_tmp);
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _item.latitude = null;
            } else {
              _item.latitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _item.longitude = null;
            } else {
              _item.longitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _item.sender = null;
            } else {
              _item.sender = _cursor.getString(_cursorIndexOfSender);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
