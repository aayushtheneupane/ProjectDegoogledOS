package androidx.slice;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Process;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import androidx.core.app.CoreComponentFactory;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.compat.CompatPermissionManager;
import androidx.slice.compat.SliceProviderCompat;
import androidx.slice.compat.SliceProviderWrapperContainer$SliceProviderWrapper;
import androidx.slice.core.R$drawable;
import androidx.slice.core.R$string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class SliceProvider extends ContentProvider implements CoreComponentFactory.CompatWrapped {
    private static Clock sClock;
    private static Set<SliceSpec> sSpecs;
    private final String[] mAutoGrantPermissions;
    private SliceProviderCompat mCompat;
    private List<Uri> mPinnedSliceUris;
    private final Object mPinnedSliceUrisLock;

    public final int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return 0;
    }

    public final Uri canonicalize(Uri uri) {
        return null;
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public abstract Slice onBindSlice(Uri uri);

    public PendingIntent onCreatePermissionRequest(Uri uri, String str) {
        return null;
    }

    public abstract boolean onCreateSliceProvider();

    public void onSlicePinned(Uri uri) {
    }

    public void onSliceUnpinned(Uri uri) {
    }

    public final Cursor query(Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) {
        return null;
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        return null;
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public SliceProvider(String... strArr) {
        this.mPinnedSliceUrisLock = new Object();
        this.mAutoGrantPermissions = strArr;
    }

    public SliceProvider() {
        this.mPinnedSliceUrisLock = new Object();
        this.mAutoGrantPermissions = new String[0];
    }

    public Object getWrapper() {
        if (Build.VERSION.SDK_INT >= 28) {
            return new SliceProviderWrapperContainer$SliceProviderWrapper(this, this.mAutoGrantPermissions);
        }
        return null;
    }

    public final boolean onCreate() {
        int i = Build.VERSION.SDK_INT;
        if (i < 19) {
            return false;
        }
        if (i < 28) {
            this.mCompat = new SliceProviderCompat(this, onCreatePermissionManager(this.mAutoGrantPermissions), getContext());
        }
        return onCreateSliceProvider();
    }

    /* access modifiers changed from: protected */
    public CompatPermissionManager onCreatePermissionManager(String[] strArr) {
        Context context = getContext();
        return new CompatPermissionManager(context, "slice_perms_" + getClass().getName(), Process.myUid(), strArr);
    }

    public final String getType(Uri uri) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        return "vnd.android.slice";
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        SliceProviderCompat sliceProviderCompat;
        if (Build.VERSION.SDK_INT >= 19 && (sliceProviderCompat = this.mCompat) != null) {
            return sliceProviderCompat.call(str, str2, bundle);
        }
        return null;
    }

    public Slice createPermissionSlice(Uri uri, String str) {
        Context context = getContext();
        PendingIntent onCreatePermissionRequest = onCreatePermissionRequest(uri, str);
        if (onCreatePermissionRequest == null) {
            onCreatePermissionRequest = createPermissionIntent(context, uri, str);
        }
        Slice.Builder builder = new Slice.Builder(uri);
        Slice.Builder builder2 = new Slice.Builder(builder);
        builder2.addIcon(IconCompat.createWithResource(context, R$drawable.abc_ic_permission), (String) null, new String[0]);
        builder2.addHints((List<String>) Arrays.asList(new String[]{"title", "shortcut"}));
        builder2.addAction(onCreatePermissionRequest, new Slice.Builder(builder).build(), (String) null);
        TypedValue typedValue = new TypedValue();
        new ContextThemeWrapper(context, 16974123).getTheme().resolveAttribute(16843829, typedValue, true);
        int i = typedValue.data;
        Slice.Builder builder3 = new Slice.Builder(uri.buildUpon().appendPath("permission").build());
        builder3.addIcon(IconCompat.createWithResource(context, R$drawable.abc_ic_arrow_forward), (String) null, new String[0]);
        builder3.addText(getPermissionString(context, str), (String) null, new String[0]);
        builder3.addInt(i, "color", new String[0]);
        builder3.addSubSlice(builder2.build(), (String) null);
        builder.addSubSlice(builder3.build(), (String) null);
        builder.addHints((List<String>) Arrays.asList(new String[]{"permission_request"}));
        return builder.build();
    }

    private static PendingIntent createPermissionIntent(Context context, Uri uri, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context.getPackageName(), "androidx.slice.compat.SlicePermissionActivity"));
        intent.putExtra("slice_uri", uri);
        intent.putExtra("pkg", str);
        intent.putExtra("provider_pkg", context.getPackageName());
        intent.setData(uri.buildUpon().appendQueryParameter("package", str).build());
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    private static CharSequence getPermissionString(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return context.getString(R$string.abc_slices_permission_request, new Object[]{packageManager.getApplicationInfo(str, 0).loadLabel(packageManager), context.getApplicationInfo().loadLabel(packageManager)});
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unknown calling app", e);
        }
    }

    public void handleSlicePinned(Uri uri) {
        List<Uri> pinnedSlices = getPinnedSlices();
        if (!pinnedSlices.contains(uri)) {
            pinnedSlices.add(uri);
        }
    }

    public void handleSliceUnpinned(Uri uri) {
        List<Uri> pinnedSlices = getPinnedSlices();
        if (pinnedSlices.contains(uri)) {
            pinnedSlices.remove(uri);
        }
    }

    public Uri onMapIntentToUri(Intent intent) {
        throw new UnsupportedOperationException("This provider has not implemented intent to uri mapping");
    }

    public Collection<Uri> onGetSliceDescendants(Uri uri) {
        return Collections.emptyList();
    }

    public List<Uri> getPinnedSlices() {
        synchronized (this.mPinnedSliceUrisLock) {
            if (this.mPinnedSliceUris == null) {
                this.mPinnedSliceUris = new ArrayList(SliceManager.getInstance(getContext()).getPinnedSlices());
            }
        }
        return this.mPinnedSliceUris;
    }

    public static void setSpecs(Set<SliceSpec> set) {
        sSpecs = set;
    }

    public static Set<SliceSpec> getCurrentSpecs() {
        return sSpecs;
    }

    public static Clock getClock() {
        return sClock;
    }
}
