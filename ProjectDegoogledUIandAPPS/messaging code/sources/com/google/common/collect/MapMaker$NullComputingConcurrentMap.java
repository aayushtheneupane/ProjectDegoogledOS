package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.base.C1547v;

final class MapMaker$NullComputingConcurrentMap extends MapMaker$NullConcurrentMap {
    private static final long serialVersionUID = 0;
    final C1547v computingFunction;

    MapMaker$NullComputingConcurrentMap(C1673la laVar, C1547v vVar) {
        super(laVar);
        if (vVar != null) {
            this.computingFunction = vVar;
            return;
        }
        throw new NullPointerException();
    }

    public Object get(Object obj) {
        if (obj != null) {
            try {
                Object apply = this.computingFunction.apply(obj);
                C1508E.checkNotNull(apply, "%s returned null for key %s.", this.computingFunction, obj);
                mo8829k(obj, apply);
                return apply;
            } catch (ComputationException e) {
                throw e;
            } catch (Throwable th) {
                throw new ComputationException(th);
            }
        } else {
            throw new NullPointerException();
        }
    }
}
