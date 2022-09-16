package com.kusitms.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class CreateQR extends AppCompatActivity {

    EditText etQrCode;
    ImageView ivQrCode;
    Button btnQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);

        etQrCode = findViewById(R.id.etQR);
        ivQrCode = findViewById(R.id.ivQR);
        btnQrCode = findViewById(R.id.btnQR);

        btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serialNumber = etQrCode.getText().toString().trim();

                MultiFormatWriter writer = new MultiFormatWriter();

                try {
                    // 1. 바코드 생성
                    BitMatrix matrix = writer.encode(serialNumber, BarcodeFormat.QR_CODE,
                            350, 350);
                    // 2. 바코드 엔코더 생성
                    BarcodeEncoder encoder = new BarcodeEncoder();

                    //3. 엔코더로 생성한 코드를 비트맵 객체에 넣기
                    Bitmap bitmap = encoder.createBitmap(matrix);

                    //4. 비트맵을 이미지뷰에 넣기
                    ivQrCode.setImageBitmap(bitmap);

                    //5. 입출력 매니저 생성
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    // 매니저 객체로 키보드 숨기기 ??
                    manager.hideSoftInputFromWindow(etQrCode.getApplicationWindowToken(), 0);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}