package kg.nurzhamal.android3_hm2.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kg.nurzhamal.android3_hm2.data.model.Film;

@Dao
public interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilm(Film film);

    @Delete
    void deleteFilm(Film film);

    @Update
    void updateFilm(Film film);

    @Query("SELECT * FROM film_table")
    LiveData<List<Film>> getFilmFromRoom();
}
