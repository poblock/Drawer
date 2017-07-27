package pl.poblocki.drawer.details;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import pl.poblocki.drawer.R;
import pl.poblocki.drawer.data.Flights;
import pl.poblocki.drawer.model.Flight;

public class FlightDetails extends FragmentActivity implements OnMapReadyCallback {

    public static final String EXTRA_ID = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        String flightID = getIntent().getStringExtra(EXTRA_ID);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView destination = (TextView) findViewById(R.id.detail_destination);
        TextView time = (TextView) findViewById(R.id.detail_time);
        TextView flight = (TextView) findViewById(R.id.detail_flight);
        TextView freighter = (TextView) findViewById(R.id.detail_freighter);
        TextView timeExp = (TextView) findViewById(R.id.detail_exp_time);
        TextView status = (TextView) findViewById(R.id.detail_status);
        CheckBox checkBox = (CheckBox) findViewById(R.id.detailFlightChk);

        final Flight model = Flights.getInstance().getFlightByID(flightID);
        if(model!=null) {
            destination.setText(model.getDestination());
            time.setText(model.getTime());
            flight.setText(model.getFlight());
            freighter.setText(model.getFreighter());
            timeExp.setText(model.getExp_time());
            status.setText(model.getStatus());
            checkBox.setChecked(model.isObserved());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng gdansk = new LatLng(54.37760,18.46619);
        LatLng malaga = new LatLng(36.67490, -4.49911);
        PolylineOptions polylineOptions = new PolylineOptions().add(gdansk, malaga).geodesic(true).color(0xFF3B6C8E);
        Log.i("DETAILS", "Width: "+polylineOptions.getWidth()+" ZIndex: "+polylineOptions.getZIndex());
        googleMap.addPolyline(polylineOptions);
        BitmapDescriptor blueIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        googleMap.addMarker(new MarkerOptions().position(gdansk).title("Gdansk").icon(blueIcon));
        googleMap.addMarker(new MarkerOptions().position(malaga).title("Malaga").icon(blueIcon));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.0,15.0), 3));
    }
}
