package android.cranberry.networksynclibrary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@Database(entities = DiffDb.class, exportSchema = false, version = 2)
public abstract class DiffDatabase extends RoomDatabase {
    public static final String DB_NAME = "diff_db";
    public static DiffDatabase instance;

    public static synchronized DiffDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DiffDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract DiffDbDao getDao();
}
