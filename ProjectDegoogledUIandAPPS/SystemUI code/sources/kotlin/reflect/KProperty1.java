package kotlin.reflect;

import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

/* compiled from: KProperty.kt */
public interface KProperty1<T, R> extends KProperty<R>, Function1<T, R> {

    /* compiled from: KProperty.kt */
    public interface Getter<T, R> extends KProperty.Getter<R>, Function1<T, R> {
    }

    R get(T t);

    Getter<T, R> getGetter();
}
