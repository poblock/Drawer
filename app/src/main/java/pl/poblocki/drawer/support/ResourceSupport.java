package pl.poblocki.drawer.support;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class ResourceSupport {

    private Context context;

    public ResourceSupport(Context context) {
        this.context = context;
    }

    public int getDrawableId(String name) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name.toLowerCase(), "drawable", context.getPackageName());
        return resourceId;
    }

    public String getString(String name) {
        try {
            Resources resources = context.getResources();
            final int resourceId = resources.getIdentifier(name, "string", context.getPackageName());
            if(resourceId>0) {
                return context.getString(resourceId);
            }
        } catch (Exception e) {
            Log.e("ResourceSupport","Error "+e.getMessage());
        }
        return null;
    }
}
