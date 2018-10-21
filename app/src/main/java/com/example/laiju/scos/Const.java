package com.example.laiju.scos;

/**
 * Created by laiju on 2018/10/16.
 */

public class Const {
    // ButtonText
    public static class ButtonText {
        public static final String ORDER = "点菜";
        public static final String VIEW = "查看订单";
        public static final String LOGIN = "注册\\登录";
        public static final String HELP = "帮助";

        public static final String ORDER_THIS = "点菜";
        public static final String UNSUBSCRIBE = "退点";
        public static final String SUBMIT = "提交订单";
        public static final String SETTLE = "结账";
    }

    public  static  class FoodsTag {
        public static  final String COLDFOOD = "冷菜";
        public static  final String HOTFOOD = "热菜";
        public static  final String SEEFOOD = "海鲜";
        public static  final String DRINKING = "酒水";

        public static final String NO_ORDER = "未下单菜";
        public static final String ORDERED = "已下单菜";
    }


    public static  class BackInfo {
        public static final String STRINGKEY = "StringKey";
        public static final String USERKEY = "UserKey";
        public static final String FoodKey = "FoodKey";
        public static final String ENTRYFLING = "FromEntry";
        public static final String LOGINLOGIN = "LoginSuccess";
        public static final String LOGINBAKE = "Return";
        public static final String LOGINREGISTER = "RigisterSuccess";
        public static final String POSITION = "Position";
        public static final String PARLIST = "PARLIST";
    }

    public static class LayoutInfo {
        public static final int MAINSCREEN_GRIGITEM_NUM = 4;
        public static final String ARGS_PAGE = "args_page";
    }

    public static class PageId {
        public static  final int COLDFOOD = 0;
        public static  final int HOTFOOD = 1;
        public static  final int SEAFOOD = 2;
        public static  final int DRINKING = 3;
        public static  final int NO_ORDER = 0;
        public static  final int ORDERED = 1;
    }
}
