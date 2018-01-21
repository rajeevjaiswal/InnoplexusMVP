package com.rajeevjaiswal.mvp.ui.main;

import com.rajeevjaiswal.mvp.data.model.Contact;
import com.rajeevjaiswal.mvp.ui.base.MvpView;

import java.util.List;

/**
 * Created by rajeevjaiswal on 16/12/17.
 */

public interface MainMvpView extends MvpView {

    void updateContact(List<Contact> contactList);
}
