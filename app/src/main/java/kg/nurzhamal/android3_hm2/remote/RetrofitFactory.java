package kg.nurzhamal.android3_hm2.remote;

import kg.nurzhamal.android3_hm2.remote.GhibliApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static GhibliApi instance;

    public static GhibliApi getInstance(){
        if(instance ==null){
            instance = new Retrofit.Builder()
                    .baseUrl("https://ghibliapi.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(GhibliApi.class);
        }
        return instance;
    }

}
