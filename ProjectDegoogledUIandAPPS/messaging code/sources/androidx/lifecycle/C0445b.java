package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: androidx.lifecycle.b */
class C0445b {
    final int mCallType;
    final Method mMethod;

    C0445b(int i, Method method) {
        this.mCallType = i;
        this.mMethod = method;
        this.mMethod.setAccessible(true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4448b(C0453j jVar, Lifecycle$Event lifecycle$Event, Object obj) {
        try {
            int i = this.mCallType;
            if (i == 0) {
                this.mMethod.invoke(obj, new Object[0]);
            } else if (i == 1) {
                this.mMethod.invoke(obj, new Object[]{jVar});
            } else if (i == 2) {
                this.mMethod.invoke(obj, new Object[]{jVar, lifecycle$Event});
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to call observer method", e.getCause());
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0445b.class != obj.getClass()) {
            return false;
        }
        C0445b bVar = (C0445b) obj;
        if (this.mCallType != bVar.mCallType || !this.mMethod.getName().equals(bVar.mMethod.getName())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mMethod.getName().hashCode() + (this.mCallType * 31);
    }
}
