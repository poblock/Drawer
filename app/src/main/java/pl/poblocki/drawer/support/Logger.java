package pl.poblocki.drawer.support;

import android.util.Log;

/**
 * Created by krzysztof.poblocki on 2017-07-19.
 */

public class Logger {
    public void i(String tag, String content) {
        Log.i(tag, content);
    }
    public void e(String tag, String content) {
        Log.e(tag, content);
    }
}
