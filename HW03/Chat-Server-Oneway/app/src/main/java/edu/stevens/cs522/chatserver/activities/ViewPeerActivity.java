package edu.stevens.cs522.chatserver.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import edu.stevens.cs522.chatserver.R;
import edu.stevens.cs522.chatserver.contracts.MessageContract;
import edu.stevens.cs522.chatserver.entities.Peer;

/**
 * Created by dduggan.
 */

public class ViewPeerActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ViewPeerActivity.class.getCanonicalName();

    public static final String PEER_KEY = "peer";

    private Peer peer;

    /*
     * UI for messages sent by this peer
     */
    private ListView messageList;

    private SimpleCursorAdapter messagesAdapter;

    static final private int LOADER_ID = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_peer);

        peer = getIntent().getParcelableExtra(PEER_KEY);
        if (peer == null) {
            throw new IllegalArgumentException("Expected peer as intent extra");
        }

        // TODO Set the fields of the UI
        TextView username = findViewById(R.id.view_user_name);
        TextView timeStamp = findViewById(R.id.view_timestamp);
        TextView geoLocation = findViewById(R.id.view_location);

        username.setText(String.format("Sender: %s", peer.name));
        timeStamp.setText(String.format("TimeStamp: %s", formatTimestamp(peer.timestamp)));
        geoLocation.setText(String.format("Location: %s %s", peer.latitude, peer.longitude));

        // done TODO


        // TODO use SimpleCursorAdapter (with flags=0 and null initial cursor) to display the messages received.
        // You can use android.R.simple_list_item_1 as layout for each row.
        String[] reciever = new String[]{MessageContract.MESSAGE_TEXT};
        int[] sender = new int[]{R.id.message};

        messagesAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, reciever, sender, 0);
        messageList = findViewById(R.id.message_list);
        messageList.setAdapter(messagesAdapter);

        // done TODO


        // TODO Use loader manager to initiate a query of the database
        // Make sure to use the Jetpack library, not the deprecated core implementation.

        Bundle peerBundle = new Bundle();
        peerBundle.putLong("peer_id", peer.id);

        LoaderManager.getInstance(this).initLoader(LOADER_ID, peerBundle, this);

        // done TODO

    }

    private static String formatTimestamp(Date timestamp) {
        LocalDateTime dateTime = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID:
                String selection = (MessageContract.SENDER + "=?");
                String[] selectionArgs = { peer.name };
                // TODO use a CursorLoader to initiate a query on the database
                return new CursorLoader(this, MessageContract.CONTENT_URI, null, selection, selectionArgs, null);

                // done TODO

            default:
                throw new IllegalStateException(("Unexpected loader id: " + id));
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        // TODO populate the UI with the result of querying the provider
        messagesAdapter.swapCursor(data);

        // done TODO

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        // TODO reset the UI when the cursor is empty
        messagesAdapter.swapCursor(null);

        // done TODO

    }
}
