package pl.poblocki.drawer.demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.poblocki.drawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {


    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;

    public AlbumsFragment() {
    }

    public static AlbumsFragment newInstance() {
        AlbumsFragment fragment = new  AlbumsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo_fragment_albums, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.albumsRecycler);
        adapter = new AlbumsAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
