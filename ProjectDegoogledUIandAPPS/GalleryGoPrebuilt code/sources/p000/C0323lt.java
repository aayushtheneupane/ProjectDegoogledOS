package p000;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: lt */
/* compiled from: PG */
public final class C0323lt {

    /* renamed from: a */
    private static boolean f15209a = false;

    /* renamed from: b */
    private static Method f15210b = null;

    /* renamed from: c */
    private static boolean f15211c = false;

    /* renamed from: d */
    private static Field f15212d = null;

    /* renamed from: a */
    public static boolean m14636a(View view, KeyEvent keyEvent) {
        return C0340mj.m14704a(view, keyEvent);
    }

    /* renamed from: a */
    public static boolean m14637a(C0322ls lsVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (lsVar == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return lsVar.mo7972a(keyEvent);
        }
        KeyEvent.DispatcherState dispatcherState = null;
        if (callback instanceof Activity) {
            Activity activity = (Activity) callback;
            activity.onUserInteraction();
            Window window = activity.getWindow();
            if (window.hasFeature(8)) {
                ActionBar actionBar = activity.getActionBar();
                if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                    if (!f15209a) {
                        try {
                            f15210b = actionBar.getClass().getMethod("onMenuKeyEvent", new Class[]{KeyEvent.class});
                        } catch (NoSuchMethodException e) {
                        }
                        f15209a = true;
                    }
                    Method method = f15210b;
                    if (method != null) {
                        try {
                            if (((Boolean) method.invoke(actionBar, new Object[]{keyEvent})).booleanValue()) {
                                return true;
                            }
                        } catch (IllegalAccessException | InvocationTargetException e2) {
                        }
                    }
                }
            }
            if (window.superDispatchKeyEvent(keyEvent)) {
                return true;
            }
            View decorView = window.getDecorView();
            if (C0340mj.m14707b(decorView, keyEvent)) {
                return true;
            }
            if (decorView != null) {
                dispatcherState = decorView.getKeyDispatcherState();
            }
            return keyEvent.dispatch(activity, dispatcherState, activity);
        } else if (callback instanceof Dialog) {
            Dialog dialog = (Dialog) callback;
            DialogInterface.OnKeyListener a = m14635a(dialog);
            if (a != null && a.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
                return true;
            }
            Window window2 = dialog.getWindow();
            if (window2.superDispatchKeyEvent(keyEvent)) {
                return true;
            }
            View decorView2 = window2.getDecorView();
            if (C0340mj.m14707b(decorView2, keyEvent)) {
                return true;
            }
            if (decorView2 != null) {
                dispatcherState = decorView2.getKeyDispatcherState();
            }
            return keyEvent.dispatch(dialog, dispatcherState, dialog);
        } else if ((view == null || !C0340mj.m14707b(view, keyEvent)) && !lsVar.mo7972a(keyEvent)) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    private static DialogInterface.OnKeyListener m14635a(Dialog dialog) {
        if (!f15211c) {
            try {
                Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                f15212d = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            f15211c = true;
        }
        Field field = f15212d;
        if (field == null) {
            return null;
        }
        try {
            return (DialogInterface.OnKeyListener) field.get(dialog);
        } catch (IllegalAccessException e2) {
            return null;
        }
    }
}
