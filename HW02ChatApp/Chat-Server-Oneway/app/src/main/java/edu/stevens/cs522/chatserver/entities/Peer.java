package edu.stevens.cs522.chatserver.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import edu.stevens.cs522.base.DateUtils;

/**
 * Created by dduggan.
 */

public class Peer implements Parcelable {

    // Will be database key
    public long id;

    public String name;

    // Last time we heard from this peer.
    public Date timestamp;

    // Where we heard from them
    public Double latitude;

    public Double longitude;

    public Peer() {
    }

    @Override
    public String toString() {
        // TODO
        return name;
        // done TODO
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Peer(Parcel in) {
        // TODO
        id = in.readLong();
        name = in.readString();
        timestamp = new Date(in.readLong());
        latitude = in.readDouble();
        longitude = in.readDouble();
        // done TODO

    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        // TODO
        out.writeLong(id);
        out.writeString(name);
        out.writeLong(timestamp.getTime());
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        // done TODO

    }

    public static final Creator<Peer> CREATOR = new Creator<Peer>() {

        @Override
        public Peer createFromParcel(Parcel source) {
            // TODO
            return new Peer(source);
            // done TODO
        }

        @Override
        public Peer[] newArray(int size) {
            // TODO
            return new Peer[size];
            // done TODO
        }

    };
}
