package kg.nurzhamal.android3_hm2.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kg.nurzhamal.android3_hm2.data.model.Film;

@Database(entities = {Film.class}, version = 1, exportSchema = false)
public abstract class FilmDatabase extends RoomDatabase {
    public abstract FilmDao filmDao();
}
