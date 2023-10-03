package com.android.settings.slices;

import android.app.PendingIntent;
import android.app.slice.SliceManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.KeyValueListParser;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import com.android.settings.bluetooth.BluetoothSliceBuilder;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.notification.ZenModeSliceBuilder;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.SliceBroadcastRelay;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class SettingsSliceProvider extends SliceProvider {
    private static final KeyValueListParser KEY_VALUE_LIST_PARSER = new KeyValueListParser(',');
    final Map<Uri, SliceBackgroundWorker> mPinnedWorkers = new ArrayMap();
    Map<Uri, SliceData> mSliceWeakDataCache;
    SlicesDatabaseAccessor mSlicesDatabaseAccessor;

    public SettingsSliceProvider() {
        super("android.permission.READ_SEARCH_INDEXABLES");
    }

    public boolean onCreateSliceProvider() {
        this.mSlicesDatabaseAccessor = new SlicesDatabaseAccessor(getContext());
        this.mSliceWeakDataCache = new WeakHashMap();
        return true;
    }

    public void onSlicePinned(Uri uri) {
        if (CustomSliceRegistry.isValidUri(uri)) {
            Context context = getContext();
            CustomSliceable sliceableFromUri = FeatureFactory.getFactory(context).getSlicesFeatureProvider().getSliceableFromUri(context, uri);
            IntentFilter intentFilter = sliceableFromUri.getIntentFilter();
            if (intentFilter != null) {
                registerIntentToUri(intentFilter, uri);
            }
            ThreadUtils.postOnMainThread(new Runnable(sliceableFromUri, uri) {
                private final /* synthetic */ CustomSliceable f$1;
                private final /* synthetic */ Uri f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SettingsSliceProvider.this.lambda$onSlicePinned$0$SettingsSliceProvider(this.f$1, this.f$2);
                }
            });
        } else if (CustomSliceRegistry.ZEN_MODE_SLICE_URI.equals(uri)) {
            registerIntentToUri(ZenModeSliceBuilder.INTENT_FILTER, uri);
        } else if (CustomSliceRegistry.BLUETOOTH_URI.equals(uri)) {
            registerIntentToUri(BluetoothSliceBuilder.INTENT_FILTER, uri);
        } else {
            loadSliceInBackground(uri);
        }
    }

    public void onSliceUnpinned(Uri uri) {
        SliceBroadcastRelay.unregisterReceivers(getContext(), uri);
        ThreadUtils.postOnMainThread(new Runnable(uri) {
            private final /* synthetic */ Uri f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SettingsSliceProvider.this.lambda$onSliceUnpinned$1$SettingsSliceProvider(this.f$1);
            }
        });
    }

    public Slice onBindSlice(Uri uri) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            if (!ThreadUtils.isMainThread()) {
                threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            }
            if (getBlockedKeys().contains(uri.getLastPathSegment())) {
                Log.e("SettingsSliceProvider", "Requested blocked slice with Uri: " + uri);
                StrictMode.setThreadPolicy(threadPolicy);
                return null;
            } else if (CustomSliceRegistry.isValidUri(uri)) {
                Context context = getContext();
                Slice slice = FeatureFactory.getFactory(context).getSlicesFeatureProvider().getSliceableFromUri(context, uri).getSlice();
                StrictMode.setThreadPolicy(threadPolicy);
                return slice;
            } else if (CustomSliceRegistry.WIFI_CALLING_URI.equals(uri)) {
                Slice createWifiCallingSlice = FeatureFactory.getFactory(getContext()).getSlicesFeatureProvider().getNewWifiCallingSliceHelper(getContext()).createWifiCallingSlice(uri);
                StrictMode.setThreadPolicy(threadPolicy);
                return createWifiCallingSlice;
            } else if (CustomSliceRegistry.ZEN_MODE_SLICE_URI.equals(uri)) {
                Slice slice2 = ZenModeSliceBuilder.getSlice(getContext());
                StrictMode.setThreadPolicy(threadPolicy);
                return slice2;
            } else if (CustomSliceRegistry.BLUETOOTH_URI.equals(uri)) {
                Slice slice3 = BluetoothSliceBuilder.getSlice(getContext());
                StrictMode.setThreadPolicy(threadPolicy);
                return slice3;
            } else if (CustomSliceRegistry.ENHANCED_4G_SLICE_URI.equals(uri)) {
                Slice createEnhanced4gLteSlice = FeatureFactory.getFactory(getContext()).getSlicesFeatureProvider().getNewEnhanced4gLteSliceHelper(getContext()).createEnhanced4gLteSlice(uri);
                StrictMode.setThreadPolicy(threadPolicy);
                return createEnhanced4gLteSlice;
            } else if (CustomSliceRegistry.WIFI_CALLING_PREFERENCE_URI.equals(uri)) {
                Slice createWifiCallingPreferenceSlice = FeatureFactory.getFactory(getContext()).getSlicesFeatureProvider().getNewWifiCallingSliceHelper(getContext()).createWifiCallingPreferenceSlice(uri);
                StrictMode.setThreadPolicy(threadPolicy);
                return createWifiCallingPreferenceSlice;
            } else {
                SliceData sliceData = this.mSliceWeakDataCache.get(uri);
                if (sliceData == null) {
                    loadSliceInBackground(uri);
                    Slice sliceStub = getSliceStub(uri);
                    StrictMode.setThreadPolicy(threadPolicy);
                    return sliceStub;
                }
                if (!getPinnedSlices().contains(uri)) {
                    this.mSliceWeakDataCache.remove(uri);
                }
                Slice buildSlice = SliceBuilderUtils.buildSlice(getContext(), sliceData);
                StrictMode.setThreadPolicy(threadPolicy);
                return buildSlice;
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public Collection<Uri> onGetSliceDescendants(Uri uri) {
        ArrayList arrayList = new ArrayList();
        if (SliceBuilderUtils.getPathData(uri) != null) {
            arrayList.add(uri);
            return arrayList;
        }
        String authority = uri.getAuthority();
        String path = uri.getPath();
        boolean isEmpty = path.isEmpty();
        if (isEmpty && TextUtils.isEmpty(authority)) {
            List<String> sliceKeys = this.mSlicesDatabaseAccessor.getSliceKeys(true);
            List<String> sliceKeys2 = this.mSlicesDatabaseAccessor.getSliceKeys(false);
            arrayList.addAll(buildUrisFromKeys(sliceKeys, "android.settings.slices"));
            arrayList.addAll(buildUrisFromKeys(sliceKeys2, "com.android.settings.slices"));
            arrayList.addAll(getSpecialCaseUris(true));
            arrayList.addAll(getSpecialCaseUris(false));
            return arrayList;
        } else if (!isEmpty && !TextUtils.equals(path, "/action") && !TextUtils.equals(path, "/intent")) {
            return arrayList;
        } else {
            boolean equals = TextUtils.equals(authority, "android.settings.slices");
            arrayList.addAll(buildUrisFromKeys(this.mSlicesDatabaseAccessor.getSliceKeys(equals), authority));
            arrayList.addAll(getSpecialCaseUris(equals));
            grantWhitelistedPackagePermissions(getContext(), arrayList);
            return arrayList;
        }
    }

    public PendingIntent onCreatePermissionRequest(Uri uri, String str) {
        return PendingIntent.getActivity(getContext(), 0, new Intent("android.settings.SETTINGS").setPackage("com.android.settings"), 0);
    }

    static void grantWhitelistedPackagePermissions(Context context, List<Uri> list) {
        if (list == null) {
            Log.d("SettingsSliceProvider", "No descendants to grant permission with, skipping.");
        }
        String[] stringArray = context.getResources().getStringArray(C1715R.array.slice_whitelist_package_names);
        if (stringArray == null || stringArray.length == 0) {
            Log.d("SettingsSliceProvider", "No packages to whitelist, skipping.");
            return;
        }
        Log.d("SettingsSliceProvider", String.format("Whitelisting %d uris to %d pkgs.", new Object[]{Integer.valueOf(list.size()), Integer.valueOf(stringArray.length)}));
        SliceManager sliceManager = (SliceManager) context.getSystemService(SliceManager.class);
        for (Uri next : list) {
            for (String grantSlicePermission : stringArray) {
                sliceManager.grantSlicePermission(grantSlicePermission, next);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: startBackgroundWorker */
    public void lambda$onSlicePinned$0$SettingsSliceProvider(Sliceable sliceable, Uri uri) {
        if (sliceable.getBackgroundWorkerClass() != null && !this.mPinnedWorkers.containsKey(uri)) {
            Log.d("SettingsSliceProvider", "Starting background worker for: " + uri);
            SliceBackgroundWorker instance = SliceBackgroundWorker.getInstance(getContext(), sliceable, uri);
            this.mPinnedWorkers.put(uri, instance);
            instance.onSlicePinned();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: stopBackgroundWorker */
    public void lambda$onSliceUnpinned$1$SettingsSliceProvider(Uri uri) {
        SliceBackgroundWorker sliceBackgroundWorker = this.mPinnedWorkers.get(uri);
        if (sliceBackgroundWorker != null) {
            Log.d("SettingsSliceProvider", "Stopping background worker for: " + uri);
            sliceBackgroundWorker.onSliceUnpinned();
            this.mPinnedWorkers.remove(uri);
        }
    }

    public void shutdown() {
        ThreadUtils.postOnMainThread($$Lambda$SettingsSliceProvider$dtN4YKg1hM1DvH4evBK441R18PY.INSTANCE);
    }

    private List<Uri> buildUrisFromKeys(List<String> list, String str) {
        ArrayList arrayList = new ArrayList();
        Uri.Builder appendPath = new Uri.Builder().scheme("content").authority(str).appendPath("action");
        for (String str2 : list) {
            appendPath.path("action/" + str2);
            arrayList.add(appendPath.build());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: loadSlice */
    public void lambda$loadSliceInBackground$4$SettingsSliceProvider(Uri uri) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            SliceData sliceDataFromUri = this.mSlicesDatabaseAccessor.getSliceDataFromUri(uri);
            BasePreferenceController preferenceController = SliceBuilderUtils.getPreferenceController(getContext(), sliceDataFromUri);
            IntentFilter intentFilter = preferenceController.getIntentFilter();
            if (intentFilter != null) {
                registerIntentToUri(intentFilter, uri);
            }
            ThreadUtils.postOnMainThread(new Runnable(preferenceController, uri) {
                private final /* synthetic */ BasePreferenceController f$1;
                private final /* synthetic */ Uri f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SettingsSliceProvider.this.lambda$loadSlice$3$SettingsSliceProvider(this.f$1, this.f$2);
                }
            });
            this.mSliceWeakDataCache.put(uri, sliceDataFromUri);
            getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
            Log.d("SettingsSliceProvider", "Built slice (" + uri + ") in: " + (System.currentTimeMillis() - currentTimeMillis));
        } catch (IllegalStateException e) {
            Log.d("SettingsSliceProvider", "Could not create slicedata for uri: " + uri, e);
        }
    }

    /* access modifiers changed from: package-private */
    public void loadSliceInBackground(Uri uri) {
        ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(uri) {
            private final /* synthetic */ Uri f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SettingsSliceProvider.this.lambda$loadSliceInBackground$4$SettingsSliceProvider(this.f$1);
            }
        });
    }

    private Slice getSliceStub(Uri uri) {
        return new Slice.Builder(uri).build();
    }

    private List<Uri> getSpecialCaseUris(boolean z) {
        if (z) {
            return getSpecialCasePlatformUris();
        }
        return getSpecialCaseOemUris();
    }

    private List<Uri> getSpecialCasePlatformUris() {
        return Arrays.asList(new Uri[]{CustomSliceRegistry.WIFI_SLICE_URI, CustomSliceRegistry.BLUETOOTH_URI, CustomSliceRegistry.LOCATION_SLICE_URI});
    }

    private List<Uri> getSpecialCaseOemUris() {
        return Arrays.asList(new Uri[]{CustomSliceRegistry.FLASHLIGHT_SLICE_URI, CustomSliceRegistry.MOBILE_DATA_SLICE_URI, CustomSliceRegistry.ZEN_MODE_SLICE_URI});
    }

    /* access modifiers changed from: package-private */
    public void registerIntentToUri(IntentFilter intentFilter, Uri uri) {
        SliceBroadcastRelay.registerReceiver(getContext(), uri, SliceRelayReceiver.class, intentFilter);
    }

    /* access modifiers changed from: package-private */
    public Set<String> getBlockedKeys() {
        String string = Settings.Global.getString(getContext().getContentResolver(), "blocked_slices");
        ArraySet arraySet = new ArraySet();
        try {
            KEY_VALUE_LIST_PARSER.setString(string);
            Collections.addAll(arraySet, parseStringArray(string));
            return arraySet;
        } catch (IllegalArgumentException e) {
            Log.e("SettingsSliceProvider", "Bad Settings Slices Whitelist flags", e);
            return arraySet;
        }
    }

    private String[] parseStringArray(String str) {
        if (str != null) {
            String[] split = str.split(":");
            if (split.length > 0) {
                return split;
            }
        }
        return new String[0];
    }
}
