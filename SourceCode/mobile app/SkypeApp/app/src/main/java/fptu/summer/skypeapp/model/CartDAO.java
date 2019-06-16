package fptu.summer.skypeapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fptu.summer.skypeapp.model.Cart;

@Dao
public interface CartDAO {
    @Query("SELECT * FROM cart")
    List<Cart> getAll();

    @Query("SELECT * FROM cart WHERE id IN (:userIds)")
    List<Cart> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM cart WHERE product_name LIKE :first LIMIT 1")
    Cart findByName(String first);

    @Insert
    void insertCart(Cart cart);

    @Update
    void update(Cart cart);

    @Delete
    void delete(Cart cart);
}
