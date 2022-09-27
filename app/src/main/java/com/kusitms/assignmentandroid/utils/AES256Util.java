package com.kusitms.assignmentandroid.utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.kusitms.assignmentandroid.BuildConfig;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Util {
    private static final String TAG = "AES";
    public static String alg = "AES/CBC/PKCS5Padding";
    public static String server_key = BuildConfig.AES_SERVER_KEY;
    public static String client_key = BuildConfig.AES_CLIENT_KEY;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String encrypt(String text, String key) throws Exception {
        final String iv = key.substring(0, 16); // 16byte

        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String decrypt(String cipherText, String key) throws Exception {
        final String iv = key.substring(0, 16); // 16byte

        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String transferKey(String text) {
        // 보안을 위해 서버키로 복호화 후 클라이언트키로 암호화
        String serialNumber = null;

        Log.d(TAG, text);
        try {
            serialNumber = this.decrypt(text, server_key);
            serialNumber = this.encrypt(serialNumber, client_key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serialNumber;
    }
}
