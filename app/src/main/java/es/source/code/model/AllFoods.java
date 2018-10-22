package es.source.code.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiju on 2018/10/17.
 */

public class AllFoods  implements Parcelable{
    private List<MyFood> coldFoods;
    private List<MyFood> hotFoods;
    private List<MyFood> seaFoods;
    private List<MyFood> drinkings;
    private boolean isInit;

    public AllFoods() {
        this.coldFoods = new ArrayList<>();
        this.hotFoods = new ArrayList<>();
        this.seaFoods = new ArrayList<>();
        this.drinkings = new ArrayList<>();
        setInit(false);
    }

    public List<MyFood> getColdFoods() {
        return coldFoods;
    }
    public List<MyFood> getHotFoods() {
        return hotFoods;
    }
    public List<MyFood> getSeaFoods() {
        return seaFoods;
    }
    public List<MyFood> getDrinkings() {
        return drinkings;
    }
    public boolean isInit() {
        return this.isInit;
    }

    public void addColdFood(String name, double price, int imageId, int buttonId) {
        MyFood myFood = new MyFood(name, price, imageId, buttonId);
        coldFoods.add(myFood);
    }
    public void addHotFood(String name, double price, int imageId, int buttonId) {
        MyFood myFood = new MyFood(name, price, imageId, buttonId);
        hotFoods.add(myFood);
    }
    public void addSeaFood(String name, double price, int imageId, int buttonId) {
        MyFood myFood = new MyFood(name, price, imageId, buttonId);
        seaFoods.add(myFood);
    }
    public void addDrinking(String name, double price, int imageId, int buttonId) {
        MyFood myFood = new MyFood(name, price, imageId, buttonId);
        drinkings.add(myFood);
    }
    public void setInit(boolean init) {
        this.isInit = init;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.coldFoods);
        dest.writeList(this.hotFoods);
        dest.writeList(this.seaFoods);
        dest.writeList(this.drinkings);
        dest.writeByte(isInit ?(byte)1 :(byte)0);
    }

    protected AllFoods(Parcel in) {
        if(coldFoods == null) coldFoods = new ArrayList<>();
        in.readList(coldFoods, MyFood.class.getClassLoader());
        if(hotFoods == null) hotFoods = new ArrayList<>();
        in.readList(hotFoods, MyFood.class.getClassLoader());
        if(seaFoods == null) seaFoods = new ArrayList<>();
        in.readList(seaFoods, MyFood.class.getClassLoader());
        if(drinkings == null) drinkings = new ArrayList<>();
        in.readList(drinkings, MyFood.class.getClassLoader());
        this.isInit = in.readByte() == 1;
    }

    public static final Parcelable.Creator<AllFoods> CREATOR = new Parcelable.Creator<AllFoods>() {
        @Override
        public AllFoods createFromParcel(Parcel source) {
            return new AllFoods(source);
        }

        @Override
        public AllFoods[] newArray(int size) {
            return new AllFoods[size];
        }
    };
}