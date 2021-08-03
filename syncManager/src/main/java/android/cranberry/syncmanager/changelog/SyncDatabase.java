package android.cranberry.syncmanager.changelog;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@Database(entities = SyncDBEntity.class, exportSchema = false, version = 4)
public abstract class SyncDatabase extends RoomDatabase {
    public static final String DB_NAME = "changelog";
    public static SyncDatabase instance;

    public static synchronized SyncDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), SyncDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract SyncDBDao getDao();
}
