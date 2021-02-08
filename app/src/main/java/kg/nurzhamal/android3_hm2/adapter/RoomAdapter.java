package kg.nurzhamal.android3_hm2.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.nurzhamal.android3_hm2.data.model.Film;
import kg.nurzhamal.android3_hm2.databinding.ItemRoomFilmBinding;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    List<Film> favoriteList = new ArrayList<>();

    public RoomAdapter() {
    }

    public void addList(List<Film> filmList) {
        this.favoriteList = filmList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomViewHolder(ItemRoomFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.onBind(favoriteList.get(position));
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {

        private final ItemRoomFilmBinding binding;

        public RoomViewHolder(@NonNull ItemRoomFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(Film film) {
            binding.titleFilm.setText(film.getTitle());
        }
    }
}
