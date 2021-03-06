package com.istiabudi.cataloguemoviedb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istiabudi.cataloguemoviedb.FaveAdapter;
import com.istiabudi.cataloguemoviedb.LoaderDatabase;
import com.istiabudi.cataloguemoviedb.MovieItem;
import com.istiabudi.cataloguemoviedb.R;

import java.util.ArrayList;


public class FaveFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>>{
    public static final String EXTRAS_MOVIE = "EXTRA MOVIE";
    static FaveAdapter adapter;
    RecyclerView recyclerViewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_fave, container, false);
        recyclerViewList = rootView.findViewById(R.id.rv_listview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new FaveAdapter(getActivity());
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerViewList.setAdapter(adapter);
        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(EXTRAS_MOVIE, "onCreate: "+"EXECUTE");
        Bundle bundle = new Bundle();
        getLoaderManager().initLoader(0, bundle, this);

    }


    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, Bundle bundle) {

        return new LoaderDatabase( getContext() );

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {

        adapter.setListMovie(data);
        adapter.notifyDataSetChanged();
        Log.d(getClass().getSimpleName(), "onLoadFinished1: " + data.size());

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItem>> loader) {
        adapter.setListMovie(null);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d(EXTRAS_MOVIE, "setUserVisibleHint: "+"VISIBLE");

            getLoaderManager().restartLoader(0, null, this);
        }
    }
}
