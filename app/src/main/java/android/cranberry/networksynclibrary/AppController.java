package android.cranberry.networksynclibrary;

import android.app.Application;
import android.cranberry.syncmanager.SyncManager;

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 27/7/21
 */
public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SyncManager.init(this);
    }
}
