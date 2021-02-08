package kg.nurzhamal.android3_hm2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.android3_hm2.data.model.Film;
import kg.nurzhamal.android3_hm2.databinding.ItemFilmBinding;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    List<Film> filmList = new ArrayList<>();
    Listener listener;

    public FilmAdapter(List<Film> filmList, Listener listener) {
        this.filmList = filmList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilmViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.onBind(filmList.get(position));
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public Film getItem(int pos) {
        return filmList.get(pos);
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {

        private final ItemFilmBinding binding;

        public FilmViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.titleFilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTitleClick(filmList.get(getAdapterPosition()));
                }
            });
            binding.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSaveClick(getAdapterPosition());
                }
            });
        }

        void onBind(Film film) {
            binding.titleFilm.setText(film.getTitle());
        }
    }

    public interface Listener {
        void onTitleClick(Film film);

        void onSaveClick(int pos);
    }
}
