package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private DoubleCheck(Provider<T> provider2) {
        this.provider = provider2;
    }

    public static <T> Lazy<T> lazy(Provider<T> provider2) {
        if (provider2 instanceof Lazy) {
            return (Lazy) provider2;
        }
        if (provider2 != null) {
            return new DoubleCheck(provider2);
        }
        throw new NullPointerException();
    }

    public static <T> Provider<T> provider(Provider<T> provider2) {
        if (provider2 == null) {
            throw new NullPointerException();
        } else if (provider2 instanceof DoubleCheck) {
            return provider2;
        } else {
            return new DoubleCheck(provider2);
        }
    }

    public T get() {
        T t = this.instance;
        if (t == UNINITIALIZED) {
            synchronized (this) {
                t = this.instance;
                if (t == UNINITIALIZED) {
                    t = this.provider.get();
                    T t2 = this.instance;
                    if (t2 != UNINITIALIZED) {
                        if (t2 != t) {
                            throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + t2 + " & " + t);
                        }
                    }
                    this.instance = t;
                    this.provider = null;
                }
            }
        }
        return t;
    }
}
