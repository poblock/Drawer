package pl.poblocki.drawer.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.poblocki.drawer.R;

/**
 * Created by IBM on 2016-12-10.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final List<Content.DummyItem> mValues;

    public Adapter() {
        mValues = Content.makeList(true);
    }

    public int getAirlineImg(String airline) {
        int img = R.drawable.airline;
        if(airline.equals("Wizzair")) {
            img = R.drawable.wizzair;
        } else if(airline.equals("Ryanair")) {
            img = R.drawable.ryanair;
        } else if(airline.equals("LOT")) {
            img = R.drawable.lot;
        } else if(airline.equals("Lufthansa")) {
            img = R.drawable.lufthansa;
        }else if(airline.equals("Air Berlin")) {
            img = R.drawable.air_berlin;
        }else if(airline.equals("Finnair")) {
            img = R.drawable.finnair;
        }else if(airline.equals("KLM")) {
            img = R.drawable.klm;
        }else if(airline.equals("Norwegian")) {
            img = R.drawable.norwegian;
        }else if(airline.equals("SAS")) {
            img = R.drawable.sas;
        }else if(airline.equals("UIA")) {
            img = R.drawable.uia;
        }else if(airline.equals("Travel Service")) {
            img = R.drawable.travelservice;
        }else if(airline.equals("7 Islands")) {
            img = R.drawable.sevenislands;
        }else if(airline.equals("Blue Bird")) {
            img = R.drawable.bluebird;
        }else if(airline.equals("Ecco")) {
            img = R.drawable.ecco;
        }else if(airline.equals("Exim")) {
            img = R.drawable.exim;
        }else if(airline.equals("Grecos")) {
            img = R.drawable.grecos;
        }else if(airline.equals("Itaka")) {
            img = R.drawable.itaka;
        }else if(airline.equals("Matimpex")) {
            img = R.drawable.matimpex;
        }else if(airline.equals("Neckermann")) {
            img = R.drawable.neckermann;
        }else if(airline.equals("Rainbow")) {
            img = R.drawable.rainbow;
        }else if(airline.equals("Small Planet")) {
            img = R.drawable.smallplanet;
        }else if(airline.equals("SunFun")) {
            img = R.drawable.sf;
        }else if(airline.equals("TUI")) {
            img = R.drawable.tui;
        }else if(airline.equals("Wezyr")) {
            img = R.drawable.wezyr;
        }else if(airline.equals("Wizz Tours")) {
            img = R.drawable.wizztours;
        }else if(airline.equals("Sprint Air")) {
            img = R.drawable.sprint;
        }
        return img;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mDestination.setText(mValues.get(position).destination);
        int img = getAirlineImg(mValues.get(position).freighter);
        holder.mFreighter.setImageResource(img);
        holder.mExpTime.setText(mValues.get(position).exp_time);
        if(holder.mFlight!=null) {
            holder.mFlight.setText(mValues.get(position).flight);
        }
        if(holder.mTime!=null) {
            holder.mTime.setText(mValues.get(position).time);
        }
        if(holder.mRemarks!=null) {
            holder.mRemarks.setText(mValues.get(position).remarks);
        }
        holder.setBackground(position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mDestination;
        public final TextView mFlight;
        public final ImageView mFreighter;
        public final TextView mTime;
        public final TextView mExpTime;
        public final TextView mRemarks;
        public Content.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDestination = (TextView) view.findViewById(R.id.content_destination);
            mFlight = (TextView) view.findViewById(R.id.content_flight);
            mFreighter = (ImageView) view.findViewById(R.id.content_freighter);
            mTime = (TextView) view.findViewById(R.id.content_time);
            mExpTime = (TextView) view.findViewById(R.id.content_exp_time);
            mRemarks = (TextView) view.findViewById(R.id.content_remarks);
        }

        public void setBackground(int position) {
            mView.setBackgroundColor(position%2==0 ? 0xFFFFFFFF : 0xFFF7F7F7);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDestination.getText() + "'";
        }
    }
}
