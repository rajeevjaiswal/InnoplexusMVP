
package com.rajeevjaiswal.mvp.data.network;

import com.rajeevjaiswal.mvp.data.model.Contact;

import java.util.List;

import retrofit2.Call;

/**
 * Created by rajeev on 16/12/17.
 */

public interface ApiHelper {


    Call<List<Contact>> getContacts();
}
