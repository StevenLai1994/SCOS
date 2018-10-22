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
    private AllFoods allFoods;
    public List<MyOrder> mOrdereds;
    public List<MyOrder> mNoOrders;

    public User(String userName, String password, boolean oldUser) {
        this.userName = userName;
        this.password = password;
        this.oldUser = oldUser;
        this.allFoods = new AllFoods();
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


    //加入未下单队列
    public void ortherThis(MyFood mFood, int count, String tips) {
        mFood.setOrdered(true);
        MyOrder mOrder = new MyOrder(mFood);
        mOrder.setCount(count);
        mOrder.setTips(tips);
        mOrder.setOrdered(true);
        mNoOrders.add(mOrder);
    }

    public void unsubscrib(MyFood mFood) {
        mFood.setOrdered(false);
        for(int i=0; i<mNoOrders.size(); i++) {
            if(mNoOrders.get(i).getImageId() == mFood.getImageId()) {
                mNoOrders.get(i).setOrdered(false);
                mNoOrders.remove(i);
                break;
            }
        }
    }

    public void payOrder() {
        for(int i=0; i<mNoOrders.size(); i++) {
            mOrdereds.add(mNoOrders.get(i));
        }
    }

    //操作全部食物
    public List<MyFood> getColdFoods() {
        return this.allFoods.getColdFoods();
    }
    public List<MyFood> getHotFoods() {
        return this.allFoods.getHotFoods();
    }
    public List<MyFood> getSeaFoods() {
        return this.allFoods.getSeaFoods();
    }
    public List<MyFood> getDrinkings() {
        return this.allFoods.getDrinkings();
    }

    public void addColdFood(String name, double price, int imageId, int buttonId) {
        allFoods.addColdFood(name, price, imageId, buttonId);

    }
    public void addHotFood(String name, double price, int imageId, int buttonId) {
        allFoods.addHotFood(name, price, imageId, buttonId);
    }
    public void addSeaFood(String name, double price, int imageId, int buttonId) {
        allFoods.addSeaFood(name, price, imageId, buttonId);

    }
    public void addDrinking(String name, double price, int imageId, int buttonId) {
        allFoods.addDrinking(name, price, imageId, buttonId);

    }

    public boolean getInitAllFoods () {
        return this.allFoods.isInit();
    }

    public void initAllFoods() {
        this.allFoods.setInit(true);
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
        dest.writeParcelable(this.allFoods,flags);
        dest.writeList(this.mOrdereds);
        dest.writeList(this.mNoOrders);
    }

    protected User(Parcel in) {
        this.userName = in.readString();
        this.password = in.readString();
        this.oldUser = in.readByte() == 1;
        this.allFoods = in.readParcelable(AllFoods.class.getClassLoader());
        if(mOrdereds == null) mOrdereds = new ArrayList<>();
        in.readList(mOrdereds, MyOrder.class.getClassLoader());

        if(mNoOrders == null) mNoOrders = new ArrayList<>();
        in.readList(mNoOrders, MyOrder.class.getClassLoader());
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

