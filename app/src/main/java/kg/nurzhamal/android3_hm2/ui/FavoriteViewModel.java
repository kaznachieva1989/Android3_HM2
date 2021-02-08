package kg.nurzhamal.android3_hm2.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kg.nurzhamal.android3_hm2.App;
import kg.nurzhamal.android3_hm2.data.model.Film;

public class FavoriteViewModel extends ViewModel {
    MutableLiveData<List<Film>> mutableLiveDataRoom = new MutableLiveData<>();

    public FavoriteViewModel(){
        App.database.filmDao().getFilmFromRoom().observeForever(new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> filmList) {
                mutableLiveDataRoom.setValue(filmList);
            }
        });
    }
}
