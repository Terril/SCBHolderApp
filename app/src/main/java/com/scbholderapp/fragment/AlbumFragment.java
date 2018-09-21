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
import com.scbholderapp.api.AlbumService;
import com.scbholderapp.api.PhotoService;
import com.scbholderapp.database.MainDatabase;
import com.scbholderapp.database.dao.AlbumDao;
import com.scbholderapp.database.model.Album;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

import static com.scbholderapp.database.adapter.AlbumAdapter.parse;


public class AlbumFragment extends Fragment {

    private static AlbumFragment sFragment;
    private static final String ARG_ID = "ARG_ID";

    @Inject
    AlbumService albumService;

    @Inject
    MainDatabase mainDatabase;

    public static AlbumFragment newInstance(long userId) {
        if (sFragment == null) {
            sFragment = new AlbumFragment();
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
        return inflater.inflate(R.layout.fragment_album, container, false);
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

        AlbumEpoxyController albumController = new AlbumEpoxyController();
        epoxyRecyclerView.setController(albumController);

        DaoViewModel<AlbumDao> viewModel = DaoViewModel.newInstance(this, AlbumDao.class, mainDatabase.albumDao());
        viewModel.dao.findByUserId(userId).observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable List<Album> albums) {
                albumController.setData(albums);
            }
        });

        albumService.getAlbumList(userId)
                .subscribeOn(Schedulers.io())
                .subscribe(in -> {
                    mainDatabase.albumDao().insertAll(parse(in));
                });

    }

    static class AlbumEpoxyController extends TypedEpoxyController<List<Album>> {
        @Override
        protected void buildModels(List<Album> data) {
            for (Album album : data) {
                new AlbumModel_()
                        .id(album.getId())
                        .album(album)
                        .addTo(this);
            }
        }
    }
}
