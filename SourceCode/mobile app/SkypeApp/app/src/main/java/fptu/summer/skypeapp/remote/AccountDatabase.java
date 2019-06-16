package fptu.summer.skypeapp.remote;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import fptu.summer.skypeapp.model.AccountDAO;
import fptu.summer.skypeapp.model.AccountRoom;


@Database(entities = {AccountRoom.class}, exportSchema = false, version = 1)
public abstract class AccountDatabase extends RoomDatabase {
    private static final String DB_NAME = "AccountRoom";
    private static AccountDatabase instance;

    public static synchronized AccountDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AccountDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration().build();

        }
        return instance;
    }

    public abstract AccountDAO accountDAO();
}
