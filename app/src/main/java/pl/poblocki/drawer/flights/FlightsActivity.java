package pl.poblocki.drawer.flights;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.R;
import pl.poblocki.drawer.details.FlightDetails;
import pl.poblocki.drawer.model.Flight;
import pl.poblocki.drawer.flights.service.FlightsIntentService;
import pl.poblocki.drawer.flights.service.FlightsReceiver;

public class FlightsActivity extends AppCompatActivity implements FlightsContract.View {

    private static final long TIME = 5 * 1000; // 5 sek.
    private TextView timeStatus;
    private LinearLayout table;
    private LinearLayout wait;

    private AlarmManager am;
    private FlightsReceiver alarmReceiver;
    private FlightsAdapter mListAdapter;
    private static final String TAG = "FlightsActivity";
    private FlightsContract.Presenter mPresenter;

    public FlightsActivity() {
        Log.d(TAG, "FlightsActivity()");
        ResultReceiver mReceiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                String result = resultData.getString(FlightsIntentService.BUNDLE_KEY_REQUEST_RESULT);
                Log.d(TAG, "onReceiveResult "+resultCode+" "+result);
                mPresenter.onReceiveResult(resultCode);
            }
        };
        alarmReceiver = new FlightsReceiver(mReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);
        timeStatus = (TextView) findViewById(R.id.text_alarm_status);
        timeStatus.setText("Oczekiwanie...");

        table = (LinearLayout) findViewById(R.id.tableLayout);
        wait = (LinearLayout) findViewById(R.id.waitLayout);
        mListAdapter = new FlightsAdapter(new ArrayList<Flight>(0), mClickListener);
        ListView listView = (ListView) findViewById(R.id.listaLotow);
        listView.setAdapter(mListAdapter);

        mPresenter = new FlightsPresenter(this);

        registerReceiver(alarmReceiver, new IntentFilter("com.eat.alarmreceiver"));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("com.eat.alarmreceiver"), PendingIntent.FLAG_UPDATE_CURRENT);
        am = (AlarmManager)(getSystemService( Context.ALARM_SERVICE ));
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, //SystemClock.elapsedRealtime() +
                TIME, TIME, pendingIntent);
        showLoading(true);
    }

    private FlightItemListener mClickListener = new FlightItemListener() {
        @Override
        public void onFlightClick(Flight clickedFlight) {
            mPresenter.showFlightDetails(clickedFlight);
        }

        @Override
        public void onObserveFlightClick(Flight observedFlight) {
            mPresenter.onObserveFlight(observedFlight);
        }
    };

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        unregisterReceiver(alarmReceiver);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();

    }

    @Override
    public void setPresenter(FlightsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void refreshUI(String arrivalsTime, String departuresTime, List<Flight> arrivals, List<Flight> departures) {
        Log.d(TAG, "Odswiezanie interfejsu uzytkownika na podstawie nowych danych");
        mListAdapter.replaceData(arrivals);
        String time = arrivalsTime.replace("T", " ");
        timeStatus.setText(time);
    }

    @Override
    public void showFlightDetailsUI(Flight flight) {
        Intent intent = new Intent(this, FlightDetails.class);
        intent.putExtra(FlightDetails.EXTRA_ID, flight.getId());
        startActivity(intent);
    }

    @Override
    public void showLoading(boolean show) {
        if(show) {
            table.setVisibility(View.GONE);
            wait.setVisibility(View.VISIBLE);
        } else {
            wait.setVisibility(View.GONE);
            table.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onShowErrorUI() {
        Log.d(TAG, "onShowErrorUI");
        View view = (table!=null && table.getVisibility()==View.VISIBLE ? table : (wait!=null ? wait : null));
        if(view!=null) {
            Snackbar.make(view, "Błąd pobierania danych", Snackbar.LENGTH_LONG).show();
        } else {
            Log.e(TAG, "Brak widoku table i wait do pokazania snackbara z bledem");
        }
    }

    private static class FlightsAdapter extends BaseAdapter {
        private List<Flight> flights;
        private FlightItemListener mItemListener;

        public FlightsAdapter(List<Flight> flights, FlightItemListener mItemListener) {
            this.flights = flights;
            this.mItemListener = mItemListener;
        }

        public void replaceData(List<Flight> flights) {
            this.flights = flights;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (flights == null){
                return 0;
            }
            return flights.size();
        }

        @Override
        public Object getItem(int position) {
            if (flights == null){
                return null;
            }
            return flights.get(position);
        }

        @Override
        public long getItemId(int position) {
            if (flights == null){
                return 0L;
            }
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.flight_table_row, parent, false);
            }
            TextView destination = (TextView) convertView.findViewById(R.id.row_destination);
            TextView time = (TextView) convertView.findViewById(R.id.row_time);
            TextView flight = (TextView) convertView.findViewById(R.id.row_flight);
            TextView freighter = (TextView) convertView.findViewById(R.id.row_freighter);
            TextView timeExp = (TextView) convertView.findViewById(R.id.row_exp_time);
            TextView status = (TextView) convertView.findViewById(R.id.row_status);
            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.observeFlightChk);

            final Flight model = flights.get(position);
            destination.setText(model.getDestination());
            time.setText(model.getTime());
            flight.setText(model.getFlight());
            freighter.setText(model.getFreighter());
            timeExp.setText(model.getExp_time());
            status.setText(model.getStatus());
            checkBox.setChecked(model.isObserved());

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onObserveFlightClick(model);
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onFlightClick(model);
                }
            });
            return convertView;
        }
    }

    public interface FlightItemListener {
        void onFlightClick(Flight clickedFlight);
        void onObserveFlightClick(Flight observedFlight);
    }
}
