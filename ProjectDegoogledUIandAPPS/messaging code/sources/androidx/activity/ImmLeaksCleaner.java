package androidx.activity;

import android.app.Activity;
import androidx.lifecycle.C0449f;
import java.lang.reflect.Field;

final class ImmLeaksCleaner implements C0449f {

    /* renamed from: Cl */
    private static int f118Cl;

    /* renamed from: Dl */
    private static Field f119Dl;

    /* renamed from: El */
    private static Field f120El;

    /* renamed from: Fl */
    private static Field f121Fl;
    private Activity mActivity;

    /* JADX WARNING: Can't wrap try/catch for region: R(3:41|42|43) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0074, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0073 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo611a(androidx.lifecycle.C0453j r2, androidx.lifecycle.Lifecycle$Event r3) {
        /*
            r1 = this;
            androidx.lifecycle.Lifecycle$Event r2 = androidx.lifecycle.Lifecycle$Event.ON_DESTROY
            if (r3 == r2) goto L_0x0005
            return
        L_0x0005:
            int r2 = f118Cl
            r3 = 1
            if (r2 != 0) goto L_0x003c
            r2 = 2
            f118Cl = r2     // Catch:{ NoSuchFieldException -> 0x003c }
            java.lang.Class<android.view.inputmethod.InputMethodManager> r2 = android.view.inputmethod.InputMethodManager.class
            java.lang.String r0 = "mServedView"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r0)     // Catch:{ NoSuchFieldException -> 0x003c }
            f120El = r2     // Catch:{ NoSuchFieldException -> 0x003c }
            java.lang.reflect.Field r2 = f120El     // Catch:{ NoSuchFieldException -> 0x003c }
            r2.setAccessible(r3)     // Catch:{ NoSuchFieldException -> 0x003c }
            java.lang.Class<android.view.inputmethod.InputMethodManager> r2 = android.view.inputmethod.InputMethodManager.class
            java.lang.String r0 = "mNextServedView"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r0)     // Catch:{ NoSuchFieldException -> 0x003c }
            f121Fl = r2     // Catch:{ NoSuchFieldException -> 0x003c }
            java.lang.reflect.Field r2 = f121Fl     // Catch:{ NoSuchFieldException -> 0x003c }
            r2.setAccessible(r3)     // Catch:{ NoSuchFieldException -> 0x003c }
            java.lang.Class<android.view.inputmethod.InputMethodManager> r2 = android.view.inputmethod.InputMethodManager.class
            java.lang.String r0 = "mH"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r0)     // Catch:{ NoSuchFieldException -> 0x003c }
            f119Dl = r2     // Catch:{ NoSuchFieldException -> 0x003c }
            java.lang.reflect.Field r2 = f119Dl     // Catch:{ NoSuchFieldException -> 0x003c }
            r2.setAccessible(r3)     // Catch:{ NoSuchFieldException -> 0x003c }
            f118Cl = r3     // Catch:{ NoSuchFieldException -> 0x003c }
        L_0x003c:
            int r2 = f118Cl
            if (r2 != r3) goto L_0x007d
            android.app.Activity r1 = r1.mActivity
            java.lang.String r2 = "input_method"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.view.inputmethod.InputMethodManager r1 = (android.view.inputmethod.InputMethodManager) r1
            java.lang.reflect.Field r2 = f119Dl     // Catch:{ IllegalAccessException -> 0x007d }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ IllegalAccessException -> 0x007d }
            if (r2 != 0) goto L_0x0053
            return
        L_0x0053:
            monitor-enter(r2)
            java.lang.reflect.Field r3 = f120El     // Catch:{ IllegalAccessException -> 0x0079, ClassCastException -> 0x0077 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ IllegalAccessException -> 0x0079, ClassCastException -> 0x0077 }
            android.view.View r3 = (android.view.View) r3     // Catch:{ IllegalAccessException -> 0x0079, ClassCastException -> 0x0077 }
            if (r3 != 0) goto L_0x0060
            monitor-exit(r2)     // Catch:{ all -> 0x0075 }
            return
        L_0x0060:
            boolean r3 = r3.isAttachedToWindow()     // Catch:{ all -> 0x0075 }
            if (r3 == 0) goto L_0x0068
            monitor-exit(r2)     // Catch:{ all -> 0x0075 }
            return
        L_0x0068:
            java.lang.reflect.Field r3 = f121Fl     // Catch:{ IllegalAccessException -> 0x0073 }
            r0 = 0
            r3.set(r1, r0)     // Catch:{ IllegalAccessException -> 0x0073 }
            monitor-exit(r2)     // Catch:{ all -> 0x0075 }
            r1.isActive()
            goto L_0x007d
        L_0x0073:
            monitor-exit(r2)     // Catch:{ all -> 0x0075 }
            return
        L_0x0075:
            r1 = move-exception
            goto L_0x007b
        L_0x0077:
            monitor-exit(r2)     // Catch:{ all -> 0x0075 }
            return
        L_0x0079:
            monitor-exit(r2)     // Catch:{ all -> 0x0075 }
            return
        L_0x007b:
            monitor-exit(r2)     // Catch:{ all -> 0x0075 }
            throw r1
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.ImmLeaksCleaner.mo611a(androidx.lifecycle.j, androidx.lifecycle.Lifecycle$Event):void");
    }
}
