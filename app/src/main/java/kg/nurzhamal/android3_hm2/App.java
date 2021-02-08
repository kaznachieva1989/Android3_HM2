package kg.nurzhamal.android3_hm2;

import android.app.Application;

import androidx.room.Room;

import kg.nurzhamal.android3_hm2.local.FilmDatabase;

public class App extends Application {

    public static FilmDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(
                getApplicationContext(),
                FilmDatabase.class,
                "FilmList")
                .allowMainThreadQueries()
                .build();
    }
}
