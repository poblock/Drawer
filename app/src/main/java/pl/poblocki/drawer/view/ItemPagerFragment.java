package pl.poblocki.drawer.view;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

//import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.R;
import pl.poblocki.drawer.databinding.FragmentItemPagerBinding;
import pl.poblocki.drawer.di.module.ActivityModule;
import pl.poblocki.drawer.list.FlightsListAdapter;
import pl.poblocki.drawer.list.FlightsSection;
import pl.poblocki.drawer.section.SectionedRecyclerViewAdapter;
import pl.poblocki.drawer.viewmodel.FlightsListViewModel;


public class ItemPagerFragment extends Fragment {

    public ItemPagerFragment() {
//        AirportApplication.component().plus(new ActivityModule(getActivity())).inject(this);
    }

    public static ItemPagerFragment newInstance() {
        ItemPagerFragment fragment = new ItemPagerFragment();
        return fragment;
    }

//    @Inject
//    FlightsListViewModel flightsListViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_pager, container, false);
//        FragmentItemPagerBinding fragmentItemPagerBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_item_pager);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
//        RecyclerView recyclerView = fragmentItemPagerBinding.list;

        Context context = view.getContext();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new FlightsSection(getContext(), false));
        sectionAdapter.addSection(new FlightsSection(getContext(), true));

//        sectionAdapter.addSection(new FlightsListAdapter(getContext(), false, flightsListViewModel));
//        sectionAdapter.addSection(new FlightsListAdapter(getContext(), true, flightsListViewModel));
        recyclerView.setAdapter(sectionAdapter);


//        fragmentItemPagerBinding.setViewModel(flightsListViewModel);
//        flightsListViewModel.initialize();
//        flightsListViewModel.scrollTo()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(pos -> recyclerView.smoothScrollToPosition(pos));

        return view;
    }

}
