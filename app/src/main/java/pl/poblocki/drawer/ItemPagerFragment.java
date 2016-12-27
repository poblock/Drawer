package pl.poblocki.drawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import pl.poblocki.drawer.list.Adapter;
import pl.poblocki.drawer.list.Section;


public class ItemPagerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    private SectionedRecyclerViewAdapter sectionAdapter;

    public ItemPagerFragment() {
    }

    public static ItemPagerFragment newInstance(String param1) {
        ItemPagerFragment fragment = new ItemPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_pager, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new Section(getContext(), false));
        sectionAdapter.addSection(new Section(getContext(), true));

        recyclerView.setAdapter(sectionAdapter);
        return view;
    }

}
