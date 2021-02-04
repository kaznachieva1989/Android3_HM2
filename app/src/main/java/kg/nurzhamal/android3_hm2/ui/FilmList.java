package kg.nurzhamal.android3_hm2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.android3_hm2.R;
import kg.nurzhamal.android3_hm2.adapter.FilmAdapter;
import kg.nurzhamal.android3_hm2.data.model.Film;
import kg.nurzhamal.android3_hm2.databinding.FilmListFragmentBinding;

public class FilmList extends Fragment implements FilmAdapter.Listener {

    private FilmListViewModel mViewModel;
    private FilmListFragmentBinding binding;
    FilmAdapter filmAdapter;
    List<Film> filmList = new ArrayList<>();
    NavController navController;

    public static FilmList newInstance() {
        return new FilmList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FilmListFragmentBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filmAdapter = new FilmAdapter(filmList, this);
        binding.recyclerView.setAdapter(filmAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FilmListViewModel.class);
        mViewModel.getFilms();
        mViewModel.listMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                if (films != null) {
                    filmList.addAll(films);
                    filmAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onTitleClick(int position) {
        Log.d("tag", "onClick");
        Bundle b = new Bundle();
        b.putString("filmId", filmAdapter.getItem(position).getId());
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_filmList_to_filmDetails, b);
    }
}