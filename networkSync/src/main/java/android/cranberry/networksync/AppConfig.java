package android.cranberry.networksync;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;


/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
public class AppConfig extends Application {
    private static final String DB_NAME = "default.realm";

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.enableLogging();
    }
}
