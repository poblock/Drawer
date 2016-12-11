package pl.poblocki.drawer;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


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
        Button b = (Button) view.findViewById(R.id.button);
        TextView txt = (TextView) view.findViewById(R.id.textView);
        txt.setText("Strona "+pageID);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(view, "Welcome in "+pageID, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        switch (pageID) {
            case R.id.nav_arrivals :
                break;
            case R.id.nav_departures :
                break;
        }
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        pager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(pager);
        return view;
    }

//    public void onButtonPressed(Uri uri) {
////        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
////        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof ButtonFragment.OnFragmentInteractionListener) {
//            mListener = (ButtonFragment.OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ItemPagerFragment.newInstance("Pozycja "+position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Przyloty";
                case 1: return "Odloty";
                default: return null;
            }
        }
    }
}
