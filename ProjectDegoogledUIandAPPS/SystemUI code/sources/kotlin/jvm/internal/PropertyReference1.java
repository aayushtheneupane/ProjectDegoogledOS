package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty1;

public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    /* access modifiers changed from: protected */
    public KCallable computeReflected() {
        Reflection.property1(this);
        return this;
    }

    public Object invoke(Object obj) {
        return get(obj);
    }

    public KProperty1.Getter getGetter() {
        return ((KProperty1) getReflected()).getGetter();
    }
}
