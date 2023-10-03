package com.google.common.eventbus;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Subscriber {
    private final Method method;
    final Object target;

    static final class SynchronizedSubscriber extends Subscriber {
        /* access modifiers changed from: package-private */
        public void invokeSubscriberMethod(Object obj) throws InvocationTargetException {
            synchronized (this) {
                Subscriber.super.invokeSubscriberMethod(obj);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber subscriber = (Subscriber) obj;
        if (this.target != subscriber.target || !this.method.equals(subscriber.method)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return System.identityHashCode(this.target) + ((this.method.hashCode() + 31) * 31);
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriberMethod(Object obj) throws InvocationTargetException {
        try {
            Method method2 = this.method;
            Object obj2 = this.target;
            Object[] objArr = new Object[1];
            if (obj != null) {
                objArr[0] = obj;
                method2.invoke(obj2, objArr);
                return;
            }
            throw new NullPointerException();
        } catch (IllegalArgumentException e) {
            throw new Error(GeneratedOutlineSupport.outline6("Method rejected target/argument: ", obj), e);
        } catch (IllegalAccessException e2) {
            throw new Error(GeneratedOutlineSupport.outline6("Method became inaccessible: ", obj), e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }
}
