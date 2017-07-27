package pl.poblocki.drawer.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by krzysztof.poblocki on 2017-07-26.
 */

public class LocalStorage {

    private SharedPreferences mPrefs;
    private static final String KEY = "flights";

    public LocalStorage(Context context) {
        mPrefs = context.getSharedPreferences("pl.poblock.flights.pref", MODE_PRIVATE);
    }

    public void saveObserved(Set<String> flights) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putStringSet(KEY, flights);
        editor.commit();
    }

    public Set<String> getObserved() {
        return mPrefs.getStringSet(KEY, null);
    }
}
