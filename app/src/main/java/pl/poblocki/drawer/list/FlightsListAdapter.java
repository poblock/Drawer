package pl.poblocki.drawer.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import pl.poblocki.drawer.R;
import pl.poblocki.drawer.databinding.ListItemBinding;
import pl.poblocki.drawer.list.section.StatelessSection;
import pl.poblocki.drawer.viewmodel.FlightViewModel;
import pl.poblocki.drawer.viewmodel.FlightsListViewModel;

/**
 * Created by krzysztof.poblocki on 2017-06-12.
 */

public class FlightsListAdapter extends StatelessSection {
    private final FlightsListViewModel listViewModel;
    private final LayoutInflater layoutInflater;
    private Context context;
    private boolean isNextDay;

    public FlightsListAdapter(Context context, boolean isNextDay, FlightsListViewModel listViewModel) {
        super(R.layout.section_header, R.layout.list_item);
        this.listViewModel = listViewModel;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.isNextDay = isNextDay;
    }

//    @Inject
//    FlightsListAdapter(@ForActivity Context context, FlightsListViewModel todoListViewModel) {
//        super(R.layout.section_header, R.layout.list_item);
//        this.listViewModel = todoListViewModel;
//        this.layoutInflater = LayoutInflater.from(context);
//        this.context = context;
//    }

    private List<FlightViewModel> getFlights() {
        return listViewModel.getList();
    }

    @Override
    public int getContentItemsTotal() {
        return getFlights().size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        ListItemBinding itemFlightBinding = ListItemBinding.inflate(layoutInflater);
        return new ItemViewHolder(itemFlightBinding);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;
        FlightViewModel flightvm = getFlights().get(position);
        itemHolder.itemFlightBinding.setFlight(flightvm);
        itemHolder.itemFlightBinding.executePendingBindings();
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new FlightsListAdapter.HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        FlightsListAdapter.HeaderViewHolder headerHolder = (FlightsListAdapter.HeaderViewHolder) holder;
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
//        private final View rootView;
//        public final View mView;
//        public final TextView mDestination;
//        public final TextView mFlight;
//        public final ImageView mFreighter;
//        public final TextView mTime;
//        public final TextView mExpTime;
//        public final TextView mRemarks;
//        public Content.DummyItem mItem;

        final ListItemBinding itemFlightBinding;
        public ItemViewHolder(ListItemBinding itemFlightBinding) {
            super(itemFlightBinding.getRoot());
            this.itemFlightBinding = itemFlightBinding;
            setUpListeners();
        }

        private void setUpListeners() {
//            itemTodoBinding.cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) ->
//                    todoListViewModel.setCompleted(getAdapterPosition(), isChecked));
//            itemTodoBinding.tvRemove.setOnClickListener(v ->
//                    todoListViewModel.deleteTodo(getAdapterPosition()));
        }

//        public ItemViewHolder(View view) {
//            super(view);
//            rootView = view;
//            mView = view;
//            mDestination = (TextView) view.findViewById(R.id.content_destination);
//            mFlight = (TextView) view.findViewById(R.id.content_flight);
//            mFreighter = (ImageView) view.findViewById(R.id.content_freighter);
//            mTime = (TextView) view.findViewById(R.id.content_time);
//            mExpTime = (TextView) view.findViewById(R.id.content_exp_time);
//            mRemarks = (TextView) view.findViewById(R.id.content_remarks);
//        }

//        public void setBackground(int position) {
//            mView.setBackgroundColor(position%2==0 ? ContextCompat.getColor(context, R.color.table_item_bg_even) : ContextCompat.getColor(context, R.color.table_item_bg_odd));
//        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mDestination.getText() + "'";
//        }
    }
}
