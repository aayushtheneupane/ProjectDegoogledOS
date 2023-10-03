package com.android.settingslib.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tile implements Parcelable {
    public static final Parcelable.Creator<Tile> CREATOR = new Parcelable.Creator<Tile>() {
        public Tile createFromParcel(Parcel parcel) {
            return new Tile(parcel);
        }

        public Tile[] newArray(int i) {
            return new Tile[i];
        }
    };
    public static final Comparator<Tile> TILE_COMPARATOR = $$Lambda$Tile$5_ETnVHzVG6DF0RKPoy76eRIQM.INSTANCE;
    private ActivityInfo mActivityInfo;
    private final String mActivityName;
    private final String mActivityPackage;
    private String mCategory;
    private final Intent mIntent;
    long mLastUpdateTime;
    private Bundle mMetaData;
    private CharSequence mSummaryOverride;
    public ArrayList<UserHandle> userHandle = new ArrayList<>();

    public int describeContents() {
        return 0;
    }

    public Tile(ActivityInfo activityInfo, String str) {
        this.mActivityInfo = activityInfo;
        this.mActivityPackage = this.mActivityInfo.packageName;
        this.mActivityName = this.mActivityInfo.name;
        this.mMetaData = activityInfo.metaData;
        this.mCategory = str;
        this.mIntent = new Intent().setClassName(this.mActivityPackage, this.mActivityName);
    }

    Tile(Parcel parcel) {
        this.mActivityPackage = parcel.readString();
        this.mActivityName = parcel.readString();
        this.mIntent = new Intent().setClassName(this.mActivityPackage, this.mActivityName);
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.userHandle.add((UserHandle) UserHandle.CREATOR.createFromParcel(parcel));
        }
        this.mCategory = parcel.readString();
        this.mMetaData = parcel.readBundle();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mActivityPackage);
        parcel.writeString(this.mActivityName);
        int size = this.userHandle.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.userHandle.get(i2).writeToParcel(parcel, i);
        }
        parcel.writeString(this.mCategory);
        parcel.writeBundle(this.mMetaData);
    }

    public String getDescription() {
        return this.mActivityPackage + "/" + this.mActivityName;
    }

    public String getPackageName() {
        return this.mActivityPackage;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public void setCategory(String str) {
        this.mCategory = str;
    }

    public int getOrder() {
        if (hasOrder()) {
            return this.mMetaData.getInt("com.android.settings.order");
        }
        return 0;
    }

    public boolean hasOrder() {
        return this.mMetaData.containsKey("com.android.settings.order") && (this.mMetaData.get("com.android.settings.order") instanceof Integer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence getTitle(android.content.Context r6) {
        /*
            r5 = this;
            r5.ensureMetadataNotStale(r6)
            android.content.pm.PackageManager r0 = r6.getPackageManager()
            android.os.Bundle r1 = r5.mMetaData
            java.lang.String r2 = "com.android.settings.title"
            boolean r1 = r1.containsKey(r2)
            r3 = 0
            if (r1 == 0) goto L_0x003d
            android.os.Bundle r1 = r5.mMetaData
            java.lang.Object r1 = r1.get(r2)
            boolean r1 = r1 instanceof java.lang.Integer
            if (r1 == 0) goto L_0x0036
            java.lang.String r1 = r5.mActivityPackage     // Catch:{ NameNotFoundException | NotFoundException -> 0x002d }
            android.content.res.Resources r1 = r0.getResourcesForApplication(r1)     // Catch:{ NameNotFoundException | NotFoundException -> 0x002d }
            android.os.Bundle r4 = r5.mMetaData     // Catch:{ NameNotFoundException | NotFoundException -> 0x002d }
            int r2 = r4.getInt(r2)     // Catch:{ NameNotFoundException | NotFoundException -> 0x002d }
            java.lang.String r1 = r1.getString(r2)     // Catch:{ NameNotFoundException | NotFoundException -> 0x002d }
            goto L_0x003e
        L_0x002d:
            r1 = move-exception
            java.lang.String r2 = "Tile"
            java.lang.String r4 = "Couldn't find info"
            android.util.Log.w(r2, r4, r1)
            goto L_0x003d
        L_0x0036:
            android.os.Bundle r1 = r5.mMetaData
            java.lang.String r1 = r1.getString(r2)
            goto L_0x003e
        L_0x003d:
            r1 = r3
        L_0x003e:
            if (r1 != 0) goto L_0x004b
            android.content.pm.ActivityInfo r5 = r5.getActivityInfo(r6)
            if (r5 != 0) goto L_0x0047
            return r3
        L_0x0047:
            java.lang.CharSequence r1 = r5.loadLabel(r0)
        L_0x004b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.drawer.Tile.getTitle(android.content.Context):java.lang.CharSequence");
    }

    public void overrideSummary(CharSequence charSequence) {
        this.mSummaryOverride = charSequence;
    }

    public CharSequence getSummary(Context context) {
        CharSequence charSequence = this.mSummaryOverride;
        if (charSequence != null) {
            return charSequence;
        }
        ensureMetadataNotStale(context);
        PackageManager packageManager = context.getPackageManager();
        Bundle bundle = this.mMetaData;
        if (bundle == null || bundle.containsKey("com.android.settings.summary_uri") || !this.mMetaData.containsKey("com.android.settings.summary")) {
            return null;
        }
        if (!(this.mMetaData.get("com.android.settings.summary") instanceof Integer)) {
            return this.mMetaData.getString("com.android.settings.summary");
        }
        try {
            return packageManager.getResourcesForApplication(this.mActivityPackage).getString(this.mMetaData.getInt("com.android.settings.summary"));
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
            Log.d("Tile", "Couldn't find info", e);
            return null;
        }
    }

    public void setMetaData(Bundle bundle) {
        this.mMetaData = bundle;
    }

    public Bundle getMetaData() {
        return this.mMetaData;
    }

    public String getKey(Context context) {
        if (!hasKey()) {
            return null;
        }
        ensureMetadataNotStale(context);
        if (this.mMetaData.get("com.android.settings.keyhint") instanceof Integer) {
            return context.getResources().getString(this.mMetaData.getInt("com.android.settings.keyhint"));
        }
        return this.mMetaData.getString("com.android.settings.keyhint");
    }

    public boolean hasKey() {
        Bundle bundle = this.mMetaData;
        return bundle != null && bundle.containsKey("com.android.settings.keyhint");
    }

    public Icon getIcon(Context context) {
        Icon icon = null;
        if (!(context == null || this.mMetaData == null)) {
            ensureMetadataNotStale(context);
            ActivityInfo activityInfo = getActivityInfo(context);
            if (activityInfo == null) {
                Log.w("Tile", "Cannot find ActivityInfo for " + getDescription());
                return null;
            }
            int i = this.mMetaData.getInt("com.android.settings.icon");
            if (i == 0 && !this.mMetaData.containsKey("com.android.settings.icon_uri")) {
                i = activityInfo.icon;
            }
            if (i != 0) {
                icon = Icon.createWithResource(activityInfo.packageName, i);
                if (isIconTintable(context)) {
                    TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{16843817});
                    int color = obtainStyledAttributes.getColor(0, 0);
                    obtainStyledAttributes.recycle();
                    icon.setTint(color);
                }
            }
        }
        return icon;
    }

    public boolean isIconTintable(Context context) {
        ensureMetadataNotStale(context);
        Bundle bundle = this.mMetaData;
        if (bundle == null || !bundle.containsKey("com.android.settings.icon_tintable")) {
            return false;
        }
        return this.mMetaData.getBoolean("com.android.settings.icon_tintable");
    }

    private void ensureMetadataNotStale(Context context) {
        try {
            long j = context.getApplicationContext().getPackageManager().getPackageInfo(this.mActivityPackage, 128).lastUpdateTime;
            if (j != this.mLastUpdateTime) {
                this.mActivityInfo = null;
                getActivityInfo(context);
                this.mLastUpdateTime = j;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d("Tile", "Can't find package, probably uninstalled.");
        }
    }

    private ActivityInfo getActivityInfo(Context context) {
        if (this.mActivityInfo == null) {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            Intent className = new Intent().setClassName(this.mActivityPackage, this.mActivityName);
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(className, 128);
            if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
                Log.e("Tile", "Cannot find package info for " + className.getComponent().flattenToString());
            } else {
                this.mActivityInfo = queryIntentActivities.get(0).activityInfo;
                this.mMetaData = this.mActivityInfo.metaData;
            }
        }
        return this.mActivityInfo;
    }

    public boolean isPrimaryProfileOnly() {
        String str;
        Bundle bundle = this.mMetaData;
        if (bundle != null) {
            str = bundle.getString("com.android.settings.profile");
        } else {
            str = "all_profiles";
        }
        if (str == null) {
            str = "all_profiles";
        }
        return TextUtils.equals(str, "primary_profile_only");
    }

    static /* synthetic */ int lambda$static$0(Tile tile, Tile tile2) {
        return tile2.getOrder() - tile.getOrder();
    }
}
