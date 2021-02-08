package kg.nurzhamal.android3_hm2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kg.nurzhamal.android3_hm2.App;
import kg.nurzhamal.android3_hm2.R;
import kg.nurzhamal.android3_hm2.adapter.RoomAdapter;
import kg.nurzhamal.android3_hm2.data.model.Film;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class FavoriteFragment extends Fragment {

        private FilmListViewModel.FavoriteViewModel mViewModel;
        private kg.nurzhamal.android3_hm2.databinding.FavoriteFragmentBinding binding;
        RoomAdapter roomAdapter;
        NavController navController;
        String id;

        public static FavoriteFragment newInstance() {
            return new FavoriteFragment();
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                id = getArguments().getString("filmId");
            } else {
                Log.d("ololo", "Oh no!");
            }
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            binding = kg.nurzhamal.android3_hm2.databinding.FavoriteFragmentBinding.inflate(inflater);
            roomAdapter = new RoomAdapter();
            binding.recyclerViewRoom.setAdapter(roomAdapter);
            return binding.getRoot();
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            App.database.filmDao().getFilmFromRoom().observe(requireActivity(), new Observer<List<Film>>() {
                @Override
                public void onChanged(List<Film> filmList) {
                    roomAdapter.addList(filmList);
                }
            });

        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            mViewModel = new ViewModelProvider(this).get(FilmListViewModel.FavoriteViewModel.class);
            mViewModel.mutableLiveDataRoom.observe(getViewLifecycleOwner(), new Observer<List<Film>>() {
                @Override
                public void onChanged(List<Film> filmList) {
                    filmList.addAll(filmList);
                    roomAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}