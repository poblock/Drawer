package pl.poblocki.drawer.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import pl.poblocki.drawer.data.FlightManager;
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

    private BackgroundThread mBackgroundThread;
    private TextView txt;

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
        mBackgroundThread = new BackgroundThread();
        mBackgroundThread.start();
    }

    private final Handler mUiHandler = new Handler() {
        public void handleMessage(Message msg) {
            String content = (String) msg.obj;
            txt.setText(content);
        }
    };

    private class BackgroundThread extends Thread {

        private Handler mBackgroundHandler;

        public void run() {
            Looper.prepare();
            mBackgroundHandler = new Handler();
            Looper.loop();
        }

        public void exit() {
            mBackgroundHandler.getLooper().quit();
        }

        public void fetchAirportsData() {
            mBackgroundHandler.post(new Runnable() {
                @Override
                public void run() {
                    String str = "";
                    Message uiMsg = mUiHandler.obtainMessage(0, 0, 0, null);
                    if(repository!=null) {
                        List<Airport> result = repository.getAirports();
                        if(result!=null) {
                            for(Airport a : result) {
                                str += support.getString(a.getCode())+" -> "+a.toString() + "\n";
                            }
                        } else {
                            str = "NULL";
                        }
                    } else {
                        str = "REPOSITORY NULL";
                    }
                    uiMsg.obj = str;
                    mUiHandler.sendMessage(uiMsg);
                }
            });
        }

        public void fetchFlightsData() {
            mBackgroundHandler.post(new Runnable() {
                @Override
                public void run() {
                    String str = "";
                    Message uiMsg = mUiHandler.obtainMessage(0, 0, 0, null);
                    if(repository!=null) {
                        repository.getFlights();
                    } else {
                        str = "REPOSITORY NULL";
                    }
                    uiMsg.obj = str;
                    mUiHandler.sendMessage(uiMsg);
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        Button b = (Button) view.findViewById(R.id.button);

        txt = (TextView) view.findViewById(R.id.textView);
        txt.setText("Strona "+pageID);

        final Snackbar snackbar = Snackbar.make(view, "Welcome in "+pageID, Snackbar.LENGTH_LONG);

        switch (pageID) {
            case R.id.nav_before_flight :
            case R.id.nav_destinations_map :
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBackgroundThread.fetchAirportsData();
                    }
                });
                break;
            case R.id.nav_parking :
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBackgroundThread.fetchFlightsData();
                    }
                });
                break;
            case R.id.nav_terminal :
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.show();
                    }
                });
                break;
            case R.id.nav_transportation :
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(support!=null) {
                            String str = support.getString("WAW");
                            txt.setText(str);
                        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBackgroundThread.exit();
    }
}
