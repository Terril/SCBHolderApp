package com.scbholderapp.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.airbnb.epoxy.TypedEpoxyController;
import com.scbholderapp.DaoViewModel;
import com.scbholderapp.MainApplication;
import com.scbholderapp.R;
import com.scbholderapp.api.UserService;
import com.scbholderapp.database.MainDatabase;
import com.scbholderapp.database.adapter.UserAdapter;
import com.scbholderapp.database.dao.UserDao;
import com.scbholderapp.database.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class UserFragment extends Fragment {

    @Inject
    UserService userService;

    @Inject
    MainDatabase mainDatabase;

    private DaoViewModel<UserDao> viewModel;

    UserEpoxyController userController = new UserEpoxyController();

    private static UserFragment sUserFragment;

    public static UserFragment newInstance() {
        if (sUserFragment == null) {
            sUserFragment = new UserFragment();
        }
        return sUserFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplication()).getComponentInstance().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EpoxyRecyclerView epoxyRecyclerView = (EpoxyRecyclerView) view.findViewById(R.id.recycler_view);
        epoxyRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        epoxyRecyclerView.setLayoutManager(mLayoutManager);
        epoxyRecyclerView.setController(userController);

        viewModel = DaoViewModel.newInstance(this, UserDao.class, mainDatabase.userDao());
        viewModel.dao.getAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                userController.setData(users);
            }
        });

        userService.getUserList().
                subscribeOn(Schedulers.io()).
                subscribe(in -> {
                    mainDatabase.userDao().insertAll(UserAdapter.parse(in));
                });
    }

    class UserEpoxyController extends TypedEpoxyController<List<User>> {

        @Override
        protected void buildModels(List<User> users) {
            for (User user : users) {
                new UserModel_()
                        .id(user.getId())
                        .user(user)
                        .addTo(this);
            }
        }
    }
}
