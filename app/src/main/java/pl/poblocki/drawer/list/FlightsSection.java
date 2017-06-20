package pl.poblocki.drawer.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import pl.poblocki.drawer.R;
import pl.poblocki.drawer.model.Flight;
import pl.poblocki.drawer.section.StatelessSection;

/**
 * Created by krzysztof.poblocki on 2016-12-27.
 */

public class FlightsSection extends StatelessSection {

    private boolean isNextDay;
    private final List<Flight> mValues;
    private Context context;

    public FlightsSection(Context context, boolean isNextDay) {
        super(R.layout.section_header, R.layout.list_item);
        this.mValues = Content.makeMockList(isNextDay);
        this.isNextDay = isNextDay;
        this.context = context;
    }

    @Override
    public int getContentItemsTotal() {
        return mValues.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;
        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        itemHolder.mItem = mValues.get(position);
        itemHolder.mDestination.setText(mValues.get(position).getDestination());
        String airline = mValues.get(position).getFreighter();
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
        }else if(airline.equals("AlMasria")) {
            img = R.drawable.uj;
        }else if(airline.equals("Corendon")) {
            img = R.drawable.xc;
        }else if(airline.equals("Adria Airways")) {
            img = R.drawable.e4;
        }else if(airline.equals("Enter Air")) {
            img = R.drawable.jp;
        }
        itemHolder.mFreighter.setImageResource(img);
        itemHolder.mExpTime.setText(mValues.get(position).getExp_time());
        if(itemHolder.mFlight!=null) {
            itemHolder.mFlight.setText(mValues.get(position).getFlight());
        }
        if(itemHolder.mTime!=null) {
            itemHolder.mTime.setText(mValues.get(position).getTime());
        }
        if(itemHolder.mRemarks!=null) {
            itemHolder.mRemarks.setText(mValues.get(position).getStatus());
        }
        itemHolder.setBackground(position);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String titleString = null;
        if(isNextDay) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 1);
            titleString = context.getString(R.string.section_tomorrow)+" "+sdf.format(c.getTime());
        } else {
            titleString = context.getString(R.string.section_today)+" "+sdf.format(Calendar.getInstance().getTime());
        }
        if(titleString!=null) {
            headerHolder.title.setText(titleString);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        public HeaderViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final View rootView;
        public final View mView;
        public final TextView mDestination;
        public final TextView mFlight;
        public final ImageView mFreighter;
        public final TextView mTime;
        public final TextView mExpTime;
        public final TextView mRemarks;
        public Flight mItem;

        public ItemViewHolder(View view) {
            super(view);
            rootView = view;
            mView = view;
            mDestination = (TextView) view.findViewById(R.id.content_destination);
            mFlight = (TextView) view.findViewById(R.id.content_flight);
            mFreighter = (ImageView) view.findViewById(R.id.content_freighter);
            mTime = (TextView) view.findViewById(R.id.content_time);
            mExpTime = (TextView) view.findViewById(R.id.content_exp_time);
            mRemarks = (TextView) view.findViewById(R.id.content_remarks);
        }

        public void setBackground(int position) {
            mView.setBackgroundColor(position%2==0 ? ContextCompat.getColor(context, R.color.table_item_bg_even) : ContextCompat.getColor(context, R.color.table_item_bg_odd));
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDestination.getText() + "'";
        }
    }

}
