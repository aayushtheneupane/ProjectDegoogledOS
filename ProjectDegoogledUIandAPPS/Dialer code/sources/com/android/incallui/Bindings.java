package com.android.incallui;

import android.content.Context;
import com.android.dialer.common.LogUtil;
import com.android.incallui.bindings.InCallUiBindingsFactory;
import com.android.incallui.bindings.InCallUiBindingsStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Objects;

public class Bindings {
    private static InCallUiBindingsStub instance;

    /* renamed from: d */
    public static void m34d(Object obj, String str) {
        getPrefix(obj);
    }

    /* renamed from: e */
    public static void m38e(String str, String str2, Exception exc) {
        LogUtil.m7e(str, str2, (Throwable) exc);
    }

    public static InCallUiBindingsStub get(Context context) {
        Objects.requireNonNull(context);
        InCallUiBindingsStub inCallUiBindingsStub = instance;
        if (inCallUiBindingsStub != null) {
            return inCallUiBindingsStub;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof InCallUiBindingsFactory) {
            instance = ((InCallUiBindingsFactory) applicationContext).newInCallUiBindings();
        }
        if (instance == null) {
            instance = new InCallUiBindingsStub();
        }
        return instance;
    }

    private static String getPrefix(Object obj) {
        return obj == null ? "" : obj.getClass().getSimpleName();
    }

    /* renamed from: i */
    public static void m39i(Object obj, String str) {
        LogUtil.m9i(getPrefix(obj), str, new Object[0]);
    }

    static /* synthetic */ void lambda$startObtainPhotoAsync$0(ContactsAsyncHelper$WorkerArgs contactsAsyncHelper$WorkerArgs, Void voidR) {
        if (contactsAsyncHelper$WorkerArgs.listener != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("notifying listener: ");
            outline13.append(contactsAsyncHelper$WorkerArgs.listener);
            outline13.append(" image: ");
            outline13.append(contactsAsyncHelper$WorkerArgs.displayPhotoUri);
            outline13.append(" completed");
            outline13.toString();
            ((ContactInfoCache) contactsAsyncHelper$WorkerArgs.listener).onImageLoadComplete(contactsAsyncHelper$WorkerArgs.token, contactsAsyncHelper$WorkerArgs.photo, contactsAsyncHelper$WorkerArgs.photoIcon, contactsAsyncHelper$WorkerArgs.cookie);
        }
    }

    /* renamed from: v */
    public static void m40v(Object obj, String str) {
        getPrefix(obj);
    }

    /* renamed from: w */
    public static void m42w(Object obj, String str) {
        LogUtil.m10w(getPrefix(obj), str, new Object[0]);
    }

    /* renamed from: d */
    public static void m35d(Object obj, String str, Object obj2) {
        getPrefix(obj);
        str + obj2;
    }

    /* renamed from: e */
    public static void m37e(Object obj, String str, Exception exc) {
        LogUtil.m7e(getPrefix(obj), str, (Throwable) exc);
    }

    /* renamed from: v */
    public static void m41v(Object obj, String str, Object obj2) {
        getPrefix(obj);
        str + obj2;
    }

    /* renamed from: e */
    public static void m36e(Object obj, String str) {
        LogUtil.m8e(getPrefix(obj), str, new Object[0]);
    }
}
