package com.rajeevjaiswal.mvp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rajeevjaiswal.mvp.R;
import com.rajeevjaiswal.mvp.data.model.Contact;
import com.rajeevjaiswal.mvp.ui.base.BaseActivity;
import com.rajeevjaiswal.mvp.ui.main.detail.UserDetailActivity;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView,ContactAdapter.Callback {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    ContactAdapter mContactAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.contact_recycler_view)
    RecyclerView  mRecyclerView;

    @BindView(R.id.tv_asc)
    TextView ascendingView;

    @BindView(R.id.tv_desc)
    TextView  descendingView;

    @BindView(R.id.ll_sort_header)
    LinearLayout sortContainer;


    public static final String USER_DETAIL = "user_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
        mContactAdapter.setCallback(this);
        setUp();
    }



    @Override
    protected void setUp() {

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mContactAdapter);

        loadContacts();
    }

    private void loadContacts() {

        if(isNetworkConnected()){
            mPresenter.fetchContacts();
        }
        else {
            onError(R.string.connection_error);
        }
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onContactClicked(Contact contact) {
        Intent startDetailScreen = new Intent(MainActivity.this, UserDetailActivity.class);
        startDetailScreen.putExtra(USER_DETAIL, contact);
        startActivity(startDetailScreen);
    }

    @Override
    public void updateContact(List<Contact> contactList) {
        sortContainer.setVisibility(View.VISIBLE);
        mContactAdapter.addItems(contactList);
    }


    @OnClick(R.id.tv_asc)
    public void sortAscending(View  view){

        ascendingView.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
        descendingView.setBackgroundColor(ContextCompat.getColor(this,R.color.gray_f1));

        Collections.sort(mContactAdapter.getContactList(),Contact.BY_NAME_ALPHABETICAL);
        mContactAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_desc)
    public void sortDescending(View  view){

        descendingView.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
        ascendingView.setBackgroundColor(ContextCompat.getColor(this,R.color.gray_f1));
        Collections.sort(mContactAdapter.getContactList(),Collections.reverseOrder(Contact.BY_NAME_ALPHABETICAL));
        mContactAdapter.notifyDataSetChanged();
    }
}

