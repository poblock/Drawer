package pl.poblocki.drawer.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import pl.poblocki.drawer.R;
import pl.poblocki.drawer.list.FlightsSection;


public class ItemPagerFragment extends Fragment {

    public ItemPagerFragment() {}

    public static ItemPagerFragment newInstance() {
        ItemPagerFragment fragment = new ItemPagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_pager, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        Context context = view.getContext();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new FlightsSection(getContext(), false));
        sectionAdapter.addSection(new FlightsSection(getContext(), true));

        recyclerView.setAdapter(sectionAdapter);
        return view;
    }

}
