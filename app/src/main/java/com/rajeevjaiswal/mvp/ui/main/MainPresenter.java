
package com.rajeevjaiswal.mvp.ui.main;

import android.util.Log;
import android.view.View;

import com.rajeevjaiswal.mvp.R;
import com.rajeevjaiswal.mvp.data.DataManager;
import com.rajeevjaiswal.mvp.data.model.Contact;
import com.rajeevjaiswal.mvp.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by rajeev on 16/12/17.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();


    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void fetchContacts() {

            getMvpView().showLoading(R.string.loading_contacts);
            getmDataManager().getContacts().enqueue(new Callback<List<Contact>>() {

                @Override
                public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {

                    if(response.isSuccessful()){

                        getMvpView().updateContact(response.body());
                    }
                    getMvpView().hideLoading();
                }

                @Override
                public void onFailure(Call<List<Contact>> call, Throwable t) {

                    getMvpView().hideLoading();

                }
            });

    }

}