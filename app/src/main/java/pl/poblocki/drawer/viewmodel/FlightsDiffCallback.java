package pl.poblocki.drawer.viewmodel;

import javax.inject.Inject;

import pl.poblocki.drawer.support.DiffCallBack;

/**
 * Created by krzysztof.poblocki on 2017-06-12.
 */

public class FlightsDiffCallback  extends DiffCallBack<FlightViewModel> {

    @Inject
    public FlightsDiffCallback() {

    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        FlightViewModel oldItem = oldList.get(oldItemPosition);
        FlightViewModel newItem = newList.get(newItemPosition);
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        FlightViewModel oldItem = oldList.get(oldItemPosition);
        FlightViewModel newItem = newList.get(newItemPosition);
        return oldItem.equals(newItem);
    }
}
