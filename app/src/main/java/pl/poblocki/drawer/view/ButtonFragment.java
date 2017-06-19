package pl.poblocki.drawer.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.R;
import pl.poblocki.drawer.di.module.ActivityModule;
import pl.poblocki.drawer.manager.FlightManager;

public class ButtonFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int pageID;
    private TextView txt;

    @Inject
    FlightManager manager;

    public ButtonFragment() {
        AirportApplication.component().plus(new ActivityModule(getActivity())).inject(this);
    }

    public static ButtonFragment newInstance(int param1) {
        ButtonFragment fragment = new ButtonFragment();
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

    private void fetchData() {
        manager.getAirports();
    }

    public interface OnDataLoaded {
        void OnUpdate(String msg);
    }

    private OnDataLoaded callback = new OnDataLoaded() {
        @Override
        public void OnUpdate(String msg) {
//            txt.append(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        Button b = (Button) view.findViewById(R.id.button);

        txt = (TextView) view.findViewById(R.id.textView);
        txt.setText("Strona "+pageID);

        switch (pageID) {
            case R.id.nav_before_flight :

            case R.id.nav_destinations_map :

            case R.id.nav_parking :

            case R.id.nav_terminal :
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar = Snackbar
                                .make(view, "Welcome in "+pageID, Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
                break;
            case R.id.nav_transportation :
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fetchData();
                    }
                });
                break;
        }

//        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
//
//        if(showTable) {
//            SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
//            pager.setAdapter(mSectionsPagerAdapter);
//            tabLayout.setupWithViewPager(pager);
//        } else {
//            tabLayout.setVisibility(View.GONE);
//            pager.setVisibility(View.GONE);
//        }
        return view;
    }

//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return ItemPagerFragment.newInstance("Pozycja "+position);
//        }
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0: return "Przyloty";
//                case 1: return "Odloty";
//                default: return null;
//            }
//        }
//    }
}
