package es.source.code.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiju on 2018/10/17.
 */

public class User implements Parcelable {
    private String userName;
    private String password;
    private boolean oldUser;
    public List<MyOrder> mOrdereds;
    public List<MyOrder> mNoOrders;

    public User(String userName, String password, boolean oldUser) {
        this.userName = userName;
        this.password = password;
        this.oldUser = oldUser;
        this.mOrdereds = new ArrayList<>();
        this.mNoOrders = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOldUser() {
        return oldUser;
    }

    public void setOldUser(boolean oldUser) {
        this.oldUser = oldUser;
    }

    public float getNeedToPay() {
        float count = 0;
        for(int i = 0; i< mOrdereds.size(); i++) {
            count += mOrdereds.get(i).getPrice();
        }
        return count;
    }

    public float getAllPrice() {
        float count = 0;
        for(int i = 0; i< mNoOrders.size(); i++) {
            count += mNoOrders.get(i).getPrice();
        }
        return count;
    }

    public int getNoOrderCount() {
        int count = 0;
        for(int i=0; i<mNoOrders.size(); i++)
            count += mNoOrders.get(i).getCount();
        return count;
    }

    public int getOrderedCount() {
        int count = 0;
        for(int i=0; i<mOrdereds.size(); i++)
            count += mOrdereds.get(i).getCount();
        return count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.password);
        dest.writeByte(this.oldUser ? (byte) 1 : (byte) 0);
        dest.writeList(this.mOrdereds);
        dest.writeList(this.mNoOrders);
    }

    protected User(Parcel in) {
        this.userName = in.readString();
        this.password = in.readString();
        this.oldUser = in.readByte() == 1;
        if(mOrdereds == null) mOrdereds = new ArrayList<MyOrder>();
        in.readList(mOrdereds, MyFood.class.getClassLoader());

        if(mNoOrders == null) mNoOrders = new ArrayList<MyOrder>();
        in.readList(mNoOrders, MyFood.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

