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
import com.scbholderapp.api.PostService;
import com.scbholderapp.database.MainDatabase;
import com.scbholderapp.database.dao.PostDao;
import com.scbholderapp.database.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

import static com.scbholderapp.database.adapter.PostAdapter.parse;

public class PostFragment extends Fragment {
    private static PostFragment sFragment;
    private static final String ARG_ID = "ARG_ID";

    @Inject
    PostService postService;

    @Inject
    MainDatabase mainDatabase;

    public static PostFragment newInstance(long userId) {
        if (sFragment == null) {
            sFragment = new PostFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_ID, userId);
        sFragment.setArguments(bundle);

        return sFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getActivity().getApplication()).getComponentInstance().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long userId = getArguments().getLong(ARG_ID);
        EpoxyRecyclerView epoxyRecyclerView = (EpoxyRecyclerView) view.findViewById(R.id.epoxyRecyclerView);
        epoxyRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        epoxyRecyclerView.setLayoutManager(mLayoutManager);

        PostEpoxyController postController = new PostEpoxyController();
        epoxyRecyclerView.setController(postController);

        DaoViewModel<PostDao> viewModel = DaoViewModel.newInstance(this, PostDao.class, mainDatabase.postDao());
        viewModel.dao.findByUserId(userId).observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                postController.setData(posts);
            }
        });

        postService.getPostList(userId)
                .subscribeOn(Schedulers.io())
                .subscribe(in -> {
                    mainDatabase.postDao().insertAll(parse(in));
                });

    }

    static class PostEpoxyController extends TypedEpoxyController<List<Post>> {
        @Override
        protected void buildModels(List<Post> data) {
            for (Post post : data) {
                new PostModel_()
                        .id(post.getId())
                        .post(post)
                        .addTo(this);
            }
        }
    }
}
