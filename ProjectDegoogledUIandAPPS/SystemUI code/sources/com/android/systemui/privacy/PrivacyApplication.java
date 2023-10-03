package com.android.systemui.privacy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: PrivacyItem.kt */
public final class PrivacyApplication implements Comparable<PrivacyApplication> {
    static final /* synthetic */ KProperty[] $$delegatedProperties;
    private final Lazy applicationInfo$delegate = LazyKt__LazyJVMKt.lazy(new PrivacyApplication$applicationInfo$2(this));
    private final Lazy applicationName$delegate = LazyKt__LazyJVMKt.lazy(new PrivacyApplication$applicationName$2(this));
    private final Context context;
    private final Lazy icon$delegate = LazyKt__LazyJVMKt.lazy(new PrivacyApplication$icon$2(this));
    private final String packageName;
    private final int uid;

    static {
        PropertyReference1Impl propertyReference1Impl = new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PrivacyApplication.class), "applicationInfo", "getApplicationInfo()Landroid/content/pm/ApplicationInfo;");
        Reflection.property1(propertyReference1Impl);
        PropertyReference1Impl propertyReference1Impl2 = new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PrivacyApplication.class), "icon", "getIcon()Landroid/graphics/drawable/Drawable;");
        Reflection.property1(propertyReference1Impl2);
        PropertyReference1Impl propertyReference1Impl3 = new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PrivacyApplication.class), "applicationName", "getApplicationName()Ljava/lang/String;");
        Reflection.property1(propertyReference1Impl3);
        $$delegatedProperties = new KProperty[]{propertyReference1Impl, propertyReference1Impl2, propertyReference1Impl3};
    }

    /* access modifiers changed from: private */
    public final ApplicationInfo getApplicationInfo() {
        Lazy lazy = this.applicationInfo$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ApplicationInfo) lazy.getValue();
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof PrivacyApplication) {
                PrivacyApplication privacyApplication = (PrivacyApplication) obj;
                if (Intrinsics.areEqual((Object) this.packageName, (Object) privacyApplication.packageName)) {
                    if (!(this.uid == privacyApplication.uid) || !Intrinsics.areEqual((Object) this.context, (Object) privacyApplication.context)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final String getApplicationName() {
        Lazy lazy = this.applicationName$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (String) lazy.getValue();
    }

    public int hashCode() {
        String str = this.packageName;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.uid)) * 31;
        Context context2 = this.context;
        if (context2 != null) {
            i = context2.hashCode();
        }
        return hashCode + i;
    }

    public PrivacyApplication(String str, int i, Context context2) {
        Intrinsics.checkParameterIsNotNull(str, "packageName");
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.packageName = str;
        this.uid = i;
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final int getUid() {
        return this.uid;
    }

    public int compareTo(PrivacyApplication privacyApplication) {
        Intrinsics.checkParameterIsNotNull(privacyApplication, "other");
        return getApplicationName().compareTo(privacyApplication.getApplicationName());
    }

    public String toString() {
        return "PrivacyApplication(packageName=" + this.packageName + ", uid=" + this.uid + ')';
    }
}
