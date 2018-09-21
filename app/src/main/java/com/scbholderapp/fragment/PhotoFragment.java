package com.scbholderapp.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.airbnb.epoxy.TypedEpoxyController;
import com.scbholderapp.DaoViewModel;
import com.scbholderapp.MainApplication;
import com.scbholderapp.R;
import com.scbholderapp.api.PhotoService;
import com.scbholderapp.database.MainDatabase;
import com.scbholderapp.database.adapter.UserAdapter;
import com.scbholderapp.database.dao.PhotoDao;
import com.scbholderapp.database.model.Photo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import static com.scbholderapp.database.adapter.PhotoAdapter.parse;

public class PhotoFragment extends Fragment {

    private static PhotoFragment sFragment;
    private static final String ARG_ID = "ARG_ID";

    @Inject
    PhotoService photoService;

    @Inject
    MainDatabase mainDatabase;

    public static PhotoFragment newInstance(long albumId) {
        if (sFragment == null) {
            sFragment = new PhotoFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_ID, albumId);
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
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EpoxyRecyclerView epoxyRecyclerView = (EpoxyRecyclerView) view.findViewById(R.id.epoxyRecyclerView);
        long albumId = getArguments().getLong(ARG_ID);
        int spanCount = 2;
        // use a linear layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        PhotoEpoxyController photoController = new PhotoEpoxyController();
        epoxyRecyclerView.setController(photoController);
        layoutManager.setSpanSizeLookup(photoController.getSpanSizeLookup());
        epoxyRecyclerView.setLayoutManager(layoutManager);
        DaoViewModel<PhotoDao> viewModel = DaoViewModel.newInstance(this, PhotoDao.class, mainDatabase.photoDao());
        viewModel.dao.findByAlbumId(albumId).observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(@Nullable List<Photo> photos) {
                photoController.setData(photos);
            }
        });

        photoService.getPhotoList(albumId)
                .subscribeOn(Schedulers.io())
                .subscribe(in -> {
                    mainDatabase.photoDao().insertAll(parse(in));
                });

    }

    static class PhotoEpoxyController extends TypedEpoxyController<List<Photo>> {
        @Override
        protected void buildModels(List<Photo> data) {
            for (Photo photo : data) {
                new PhotoModel_()
                        .id(photo.getId())
                        .photo(photo)
                        .addTo(this);
            }
        }
    }
}
