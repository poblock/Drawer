package pl.poblocki.drawer.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.R;
import pl.poblocki.drawer.data.AirportRepository;
import pl.poblocki.drawer.di.module.ActivityModule;
import pl.poblocki.drawer.manager.FlightManager;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.support.ResourceSupport;

public class ButtonFragment extends Fragment {
    private static final String PAGE_ID = "page_id";
    private int pageID;

    @Inject
    FlightManager manager;

    @Inject
    ResourceSupport support;

    @Inject
    AirportRepository repository;

    public ButtonFragment() {
        AirportApplication.component()//.plus(new ActivityModule(getActivity()))
                .inject(this);
    }

    public static ButtonFragment newInstance(int param1) {
        ButtonFragment fragment = new ButtonFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageID = getArguments().getInt(PAGE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        Button b = (Button) view.findViewById(R.id.button);

        TextView txt = (TextView) view.findViewById(R.id.textView);
        txt.setText("Strona "+pageID);

        Snackbar snackbar = Snackbar.make(view, "Welcome in "+pageID, Snackbar.LENGTH_LONG);

        switch (pageID) {
            case R.id.nav_before_flight :
            case R.id.nav_destinations_map :
                b.setOnClickListener(view1 -> {
                    if(repository!=null) {
                        List<Airport> result = repository.getAirports();
                        if(result!=null) {
                            String str = result.toString();
                            txt.setText(str);
                        } else {
                            txt.setText("NULL");
                        }
                    } else {
                        txt.setText("REPOSITORY NULL");
                    }
                });
                break;
            case R.id.nav_parking :
            case R.id.nav_terminal :
                b.setOnClickListener(view12 -> snackbar.show());
                break;
            case R.id.nav_transportation :
                b.setOnClickListener(view1 -> {
                      if(support!=null) {
                          String str = support.getString("WAW");
                          txt.setText(str);
                      }
                });
                break;
        }
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
}
