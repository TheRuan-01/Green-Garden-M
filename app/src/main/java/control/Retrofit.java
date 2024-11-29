package control;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private static retrofit2.Retrofit retrofit;
    private static final String URL = "http://10.0.2.2:5003/api/";

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
