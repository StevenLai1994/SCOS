package es.source.code.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by laiju on 2018/10/17.
 */

public class MyFood  implements Parcelable{
    private String name;
    private double price;
    private int imageId;
    private int buttonId;

    public MyFood(String name, double price, int imageId, int buttonId) {
        this.name = name;
        this.price = price;
        this.imageId = imageId;
        this.buttonId = buttonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getButtonId() {
        return buttonId;
    }

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.price);
        dest.writeInt(this.imageId);
        dest.writeInt(this.buttonId);
    }

    protected MyFood(Parcel in) {
        this.name = in.readString();
        this.price = in.readDouble();
        this.imageId = in.readInt();
        this.buttonId = in.readInt();
    }

    public static final Parcelable.Creator<MyFood> CREATOR = new Parcelable.Creator<MyFood>() {
        @Override
        public MyFood createFromParcel(Parcel source) {
            return new MyFood(source);
        }

        @Override
        public MyFood[] newArray(int size) {
            return new MyFood[size];
        }
    };
}