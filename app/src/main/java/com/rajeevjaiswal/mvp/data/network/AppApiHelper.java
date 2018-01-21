
package com.rajeevjaiswal.mvp.data.network;

import android.database.Observable;

import com.rajeevjaiswal.mvp.data.model.Contact;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;


@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiCall mApiCall;

    @Inject
    public AppApiHelper( ApiCall apiCall) {
        mApiCall = apiCall;
    }



    @Override
    public Call<List<Contact>> getContacts() {
        return mApiCall.getContacts();
    }
}

