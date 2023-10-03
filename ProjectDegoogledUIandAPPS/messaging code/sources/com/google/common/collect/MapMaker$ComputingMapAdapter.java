package com.google.common.collect;

import android.support.p016v4.media.session.C0107q;
import com.google.common.base.C1547v;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

final class MapMaker$ComputingMapAdapter extends ComputingConcurrentHashMap implements Serializable {
    private static final long serialVersionUID = 0;

    MapMaker$ComputingMapAdapter(C1673la laVar, C1547v vVar) {
        super(laVar, vVar);
    }

    public Object get(Object obj) {
        try {
            Object D = mo8604D(obj);
            if (D != null) {
                return D;
            }
            throw new NullPointerException(this.computingFunction + " returned null for key " + obj + ".");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            C0107q.propagateIfInstanceOf(cause, ComputationException.class);
            throw new ComputationException(cause);
        }
    }
}
