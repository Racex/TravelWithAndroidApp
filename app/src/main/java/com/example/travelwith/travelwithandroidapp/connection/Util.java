package com.example.travelwith.travelwithandroidapp.connection;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;


public class Util {
    public static String getProperty(String key, Context context) {
        try {
            Properties properties = new Properties();
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Wystąpił błąd podczas generowania tłumaczenia";
    }

    public static String getTranslationProperty(String key, Context context) {
        try {
            Properties properties = new Properties();
            AssetManager assetManager = context.getAssets();
            InputStreamReader inputStreamWithUTF8 = new InputStreamReader(assetManager.open("translation.properties"), Charset.forName("UTF-8"));
            properties.load(inputStreamWithUTF8);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Wystąpił błąd podczas generowania tłumaczenia";
    }

}