package kg.nurzhamal.android3_hm2.local;

import java.util.List;

import kg.nurzhamal.android3_hm2.data.model.Film;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliApi {

    @GET(EndPoints.GET_FILMS)
    Call<List<Film>> getFilms();

    @GET(EndPoints.GET_FILM)
    Call<Film> getFilm(
            @Path("id") String id);
}
