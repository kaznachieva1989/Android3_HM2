package kg.nurzhamal.android3_hm2.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kg.nurzhamal.android3_hm2.App;
import kg.nurzhamal.android3_hm2.adapter.RoomAdapter;
import kg.nurzhamal.android3_hm2.data.model.Film;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel mViewModel;
    private kg.nurzhamal.android3_hm2.databinding.FavoriteFragmentBinding binding;
    RoomAdapter roomAdapter;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= kg.nurzhamal.android3_hm2.databinding.FavoriteFragmentBinding.inflate(inflater);
        roomAdapter = new RoomAdapter();
        binding.recyclerViewRoom.setAdapter(roomAdapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
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
        mViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        mViewModel.mutableLiveDataRoom.observe(getViewLifecycleOwner(), new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> filmList) {
                filmList.addAll(filmList);
                roomAdapter.notifyDataSetChanged();
            }
        });
    }
}
