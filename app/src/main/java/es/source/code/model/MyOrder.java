package es.source.code.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by laiju on 2018/10/17.
 */

public class MyOrder  implements Parcelable{
    private MyFood mFood;
    private int count;
    private String tips;

    public MyOrder(MyFood mFood) {
        this.mFood = mFood;
        this.count = 0;
        this.tips = "";
    }

    public MyFood getmFood() {
        return mFood;
    }

    public String getName() {
        return mFood.getName();
    }

    public void setName(String name) {
        this.mFood.setName(name);
    }

    public double getPrice() {
        return mFood.getPrice();
    }

    public void setPrice(double price) {
        this.mFood.setPrice(price);
    }

    public int getImageId() {
        return mFood.getImageId();
    }

    public void setImageId(int imageId) {
        mFood.setImageId(imageId);
    }

    public int getButtonId() {
        return mFood.getButtonId();
    }

    public void setButtonId(int buttonId) {
        this.mFood.setButtonId(buttonId);
    }

    public boolean getIsOrdered() {
        return this.mFood.getIsOrdered();
    }

    public void setOrdered(boolean ordered) {
        this.mFood.setOrdered(ordered);
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mFood, flags);
        dest.writeInt(count);
        dest.writeString(tips);
    }

    protected MyOrder(Parcel in) {
        this.mFood = in.readParcelable(MyFood.class.getClassLoader());
        this.count = in.readInt();
        this.tips = in.readString();
    }

    public static final Parcelable.Creator<MyOrder> CREATOR = new Parcelable.Creator<MyOrder>() {
        @Override
        public MyOrder createFromParcel(Parcel source) {
            return new MyOrder(source);
        }

        @Override
        public MyOrder[] newArray(int size) {
            return new MyOrder[size];
        }
    };
}