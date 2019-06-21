package fptu.summer.skypeapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fptu.summer.skypeapp.model.AccountRoom;
import fptu.summer.skypeapp.model.Cart;

@Dao
public interface AccountDAO {


    @Query("SELECT * FROM AccountRoom ORDER BY id DESC")
    AccountRoom getAccount();

    @Insert
    void insertAccountRoom(AccountRoom cart);

    @Query("DELETE FROM AccountRoom")
    void deleteAllAccount();

}
