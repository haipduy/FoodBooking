package fptu.summer.skypeapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import fptu.summer.skypeapp.model.entity.AccountRoom;

@Dao
public interface AccountDAO {


    @Query("SELECT * FROM AccountRoom ORDER BY id DESC")
    AccountRoom getAccount();

    @Insert
    void insertAccountRoom(AccountRoom cart);

    @Query("DELETE FROM AccountRoom")
    void deleteAllAccount();

}
