package com.dafian.android.androidmvpboilerplate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Dafian on 13/03/18
 */
public class Helper {

    private static final String PREF_NAME = "android_mvp_boilerplate_pref";

    public static void hideVirtualKeyboard(Context context, View v) {
        InputMethodManager keyboard = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (keyboard != null) {
            keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static void displayVirtualKeyboard(Context context, View v) {
        InputMethodManager keyboard = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (keyboard != null) {
            keyboard.showSoftInput(v, 0);
        }
    }

    public static SharedPreferences getDefaultPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }
}
