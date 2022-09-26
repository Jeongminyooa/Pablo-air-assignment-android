package com.kusitms.assignmentandroid.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeHelper {


    public static Bitmap getQRImage(String serialNumber) {
        MultiFormatWriter writer = new MultiFormatWriter();

        try {
            // 바코드 생성
            BitMatrix matrix = writer.encode(serialNumber, BarcodeFormat.QR_CODE,
                    350, 350);

            // 바코드 엔코더 생성
            BarcodeEncoder encoder = new BarcodeEncoder();

            return encoder.createBitmap(matrix);
        } catch(WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
