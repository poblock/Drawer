package pl.poblocki.drawer.view;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.poblocki.drawer.R;


public class FlightsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int pageID;
    public FlightsFragment() {}

    public static FlightsFragment newInstance(int param1) {
        FlightsFragment fragment = new FlightsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageID = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        switch (pageID) {
            case R.id.nav_arrivals :
                break;
            case R.id.nav_departures :
                break;
        }
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getChildFragmentManager());
        pager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(pager);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Context context;
        public SectionsPagerAdapter(Context c, FragmentManager fm) {
            super(fm);
            this.context = c;
        }

        @Override
        public Fragment getItem(int position) {
            return ItemPagerFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return context.getString(R.string.arrivals);
                case 1: return context.getString(R.string.departures);
                default: return null;
            }
        }
    }
}
