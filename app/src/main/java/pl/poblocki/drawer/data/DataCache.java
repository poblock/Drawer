package pl.poblocki.drawer.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public abstract class DataCache<T> {

    private List<T> cachedItems;
    private boolean mCacheIsDirty;

    public List<T> getList() {
        if(!mCacheIsDirty && cachedItems!=null) {
            return cachedItems;
        } else {
            List<T> localData = localOperation();
            if(localData!=null) {
                refreshCache(localData);
                return cachedItems;
            } else {
                List<T> remoteData = remoteOperation();
                if(remoteData!=null) {
                    refreshLocalCache(remoteData);
                    refreshCache(remoteData);
                    return cachedItems;
                }
            }
        }
        return null;
    }

    private void refreshCache(List<T> data) {
        if (cachedItems == null) {
            cachedItems = new ArrayList<>();
        }
        cachedItems.clear();
        cachedItems.addAll(data);
        mCacheIsDirty = false;
    }

    abstract List<T> remoteOperation();
    abstract List<T> localOperation();
    abstract void refreshLocalCache(List<T> data);
}
