
package com.rajeevjaiswal.mvp.data.network;

import com.rajeevjaiswal.mvp.BuildConfig;
import com.rajeevjaiswal.mvp.data.model.Contact;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public interface ApiCall {


    @GET("users")
    Call<List<Contact>> getContacts();

    class Factory {

        private static final int NETWORK_CALL_TIMEOUT = 60;

        public static ApiCall create() {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(logging);

            builder.readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);

            builder.writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);

            OkHttpClient httpClient = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(ApiCall.class);
        }
    }
}
