package com.kusitms.assignmentandroid;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

public class GlobalApplication extends Application {
    private static GlobalApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        // 네이티브 앱 키로 초기화
        KakaoSdk.init(this, getString(R.string.kakao_app_api));

        // 싱글톤 패턴으로 SharedPreference 관리
        PrefsHelper.init(getApplicationContext());
    }
}
