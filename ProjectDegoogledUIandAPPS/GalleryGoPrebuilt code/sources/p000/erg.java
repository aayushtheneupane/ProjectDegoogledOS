package p000;

import android.os.IBinder;
import java.lang.reflect.Field;

/* renamed from: erg */
/* compiled from: PG */
public final class erg extends ere {

    /* renamed from: a */
    private final Object f8868a;

    private erg(Object obj) {
        this.f8868a = obj;
    }

    /* renamed from: a */
    public static Object m8052a(erf erf) {
        if (erf instanceof erg) {
            return ((erg) erf).f8868a;
        }
        IBinder asBinder = erf.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        Field field = null;
        int i = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i++;
                field = field2;
            }
        }
        if (i != 1) {
            int length = declaredFields.length;
            StringBuilder sb = new StringBuilder(64);
            sb.append("Unexpected number of IObjectWrapper declared fields: ");
            sb.append(length);
            throw new IllegalArgumentException(sb.toString());
        } else if (!field.isAccessible()) {
            field.setAccessible(true);
            try {
                return field.get(asBinder);
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("Binder object is null.", e);
            } catch (IllegalAccessException e2) {
                throw new IllegalArgumentException("Could not access the field in remoteBinder.", e2);
            }
        } else {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
    }

    /* renamed from: a */
    public static erf m8051a(Object obj) {
        return new erg(obj);
    }
}
