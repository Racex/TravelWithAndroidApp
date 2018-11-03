package com.example.travelwith.travelwithandroidapp.object;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;

import com.example.travelwith.travelwithandroidapp.connection.Util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@SuppressLint("GetInstance")
public class AESCrypt {
    private static final String ALGORITHM = "AES";

    public static String encrypt(Context context, String value) throws Exception {
        Key key = new SecretKeySpec(Util.getProperty("AESkey", context).getBytes(), AESCrypt.ALGORITHM);
        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        return Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
    }
}

//------------- Odszyfrowywanie jak by było potrzebne :D
//    public static String decrypt(String value) throws Exception
//    {
//        Key key = generateKey();
//        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
//        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
//        String decryptedValue = new String(decryptedByteValue,"utf-8");
//        return decryptedValue;
//
//    }
