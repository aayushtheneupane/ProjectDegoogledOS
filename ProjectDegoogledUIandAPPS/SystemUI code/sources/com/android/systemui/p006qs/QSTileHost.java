package com.android.systemui.p006qs;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.DumpController;
import com.android.systemui.Dumpable;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.external.CustomTile;
import com.android.systemui.p006qs.external.TileLifecycleManager;
import com.android.systemui.p006qs.external.TileServices;
import com.android.systemui.p006qs.tileimpl.QSFactoryImpl;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.p005qs.QSFactory;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.plugins.p005qs.QSTileView;
import com.android.systemui.shared.plugins.PluginManager;
import com.android.systemui.statusbar.phone.AutoTileManager;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.tuner.TunerService;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.inject.Provider;

/* renamed from: com.android.systemui.qs.QSTileHost */
public class QSTileHost implements QSHost, TunerService.Tunable, PluginListener<QSFactory>, Dumpable {
    private static final boolean DEBUG = Log.isLoggable("QSTileHost", 3);
    private AutoTileManager mAutoTiles;
    private final List<QSHost.Callback> mCallbacks = new ArrayList();
    private final Context mContext;
    private int mCurrentUser;
    private final DumpController mDumpController;
    private final StatusBarIconController mIconController;
    private final BroadcastReceiver mLiveDisplayReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String value = ((TunerService) Dependency.get(TunerService.class)).getValue("sysui_qs_tiles");
            QSTileHost.this.onTuningChanged("sysui_qs_tiles", "");
            QSTileHost.this.onTuningChanged("sysui_qs_tiles", value);
        }
    };
    private final PluginManager mPluginManager;
    private final ArrayList<QSFactory> mQsFactories = new ArrayList<>();
    private final TileServices mServices;
    private StatusBar mStatusBar;
    protected final ArrayList<String> mTileSpecs = new ArrayList<>();
    private final LinkedHashMap<String, QSTile> mTiles = new LinkedHashMap<>();
    private final TunerService mTunerService;

    public void warn(String str, Throwable th) {
    }

    public QSTileHost(Context context, StatusBarIconController statusBarIconController, QSFactoryImpl qSFactoryImpl, Handler handler, Looper looper, PluginManager pluginManager, TunerService tunerService, Provider<AutoTileManager> provider, DumpController dumpController) {
        this.mIconController = statusBarIconController;
        this.mContext = context;
        this.mTunerService = tunerService;
        this.mPluginManager = pluginManager;
        this.mDumpController = dumpController;
        this.mServices = new TileServices(this, looper);
        qSFactoryImpl.setHost(this);
        this.mQsFactories.add(qSFactoryImpl);
        pluginManager.addPluginListener(this, (Class<?>) QSFactory.class, true);
        this.mDumpController.addListener(this);
        handler.post(new Runnable(tunerService, provider) {
            private final /* synthetic */ TunerService f$1;
            private final /* synthetic */ Provider f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                QSTileHost.this.lambda$new$0$QSTileHost(this.f$1, this.f$2);
            }
        });
        this.mContext.registerReceiver(this.mLiveDisplayReceiver, new IntentFilter("lineageos.intent.action.INITIALIZE_LIVEDISPLAY"));
    }

    public /* synthetic */ void lambda$new$0$QSTileHost(TunerService tunerService, Provider provider) {
        tunerService.addTunable(this, "sysui_qs_tiles");
        this.mAutoTiles = (AutoTileManager) provider.get();
    }

    public StatusBarIconController getIconController() {
        return this.mIconController;
    }

    public void onPluginConnected(QSFactory qSFactory, Context context) {
        this.mQsFactories.add(0, qSFactory);
        String value = this.mTunerService.getValue("sysui_qs_tiles");
        onTuningChanged("sysui_qs_tiles", "");
        onTuningChanged("sysui_qs_tiles", value);
    }

    public void onPluginDisconnected(QSFactory qSFactory) {
        this.mQsFactories.remove(qSFactory);
        String value = this.mTunerService.getValue("sysui_qs_tiles");
        onTuningChanged("sysui_qs_tiles", "");
        onTuningChanged("sysui_qs_tiles", value);
    }

    public void addCallback(QSHost.Callback callback) {
        this.mCallbacks.add(callback);
    }

    public void removeCallback(QSHost.Callback callback) {
        this.mCallbacks.remove(callback);
    }

    public Collection<QSTile> getTiles() {
        return this.mTiles.values();
    }

    public void collapsePanels() {
        if (this.mStatusBar == null) {
            this.mStatusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        }
        this.mStatusBar.postAnimateCollapsePanels();
    }

    public void forceCollapsePanels() {
        if (this.mStatusBar == null) {
            this.mStatusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        }
        this.mStatusBar.postAnimateForceCollapsePanels();
    }

    public void openPanels() {
        if (this.mStatusBar == null) {
            this.mStatusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        }
        this.mStatusBar.postAnimateOpenPanels();
    }

    public Context getContext() {
        return this.mContext;
    }

    public TileServices getTileServices() {
        return this.mServices;
    }

    public int indexOf(String str) {
        return this.mTileSpecs.indexOf(str);
    }

    public void reloadAllTiles() {
        String value = ((TunerService) Dependency.get(TunerService.class)).getValue("sysui_qs_tiles");
        onTuningChanged("sysui_qs_tiles", "");
        onTuningChanged("sysui_qs_tiles", value);
    }

    public void onTuningChanged(String str, String str2) {
        boolean z;
        if ("sysui_qs_tiles".equals(str)) {
            if (DEBUG) {
                Log.d("QSTileHost", "Recreating tiles");
            }
            if (str2 == null && UserManager.isDeviceInDemoMode(this.mContext)) {
                str2 = this.mContext.getResources().getString(C1784R$string.quick_settings_tiles_retail_mode);
            }
            List<String> loadTileSpecs = loadTileSpecs(this.mContext, str2);
            int currentUser = ActivityManager.getCurrentUser();
            if (!loadTileSpecs.equals(this.mTileSpecs) || currentUser != this.mCurrentUser) {
                this.mTiles.entrySet().stream().filter(new Predicate(loadTileSpecs) {
                    private final /* synthetic */ List f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final boolean test(Object obj) {
                        return QSTileHost.lambda$onTuningChanged$2(this.f$0, (Map.Entry) obj);
                    }
                }).forEach($$Lambda$QSTileHost$_TW3g9Ui2otBinO5ZHSBKxrVFI.INSTANCE);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (String next : loadTileSpecs) {
                    QSTile qSTile = this.mTiles.get(next);
                    if (qSTile == null || (z && ((CustomTile) qSTile).getUser() != currentUser)) {
                        if (DEBUG) {
                            Log.d("QSTileHost", "Creating tile: " + next);
                        }
                        try {
                            QSTile createTile = createTile(next);
                            if (createTile != null) {
                                if (createTile.isAvailable()) {
                                    createTile.setTileSpec(next);
                                    linkedHashMap.put(next, createTile);
                                } else {
                                    createTile.destroy();
                                }
                            }
                        } catch (Throwable th) {
                            Log.w("QSTileHost", "Error creating tile for spec: " + next, th);
                        }
                    } else if (qSTile.isAvailable()) {
                        if (DEBUG) {
                            Log.d("QSTileHost", "Adding " + qSTile);
                        }
                        qSTile.removeCallbacks();
                        if (!((z = qSTile instanceof CustomTile)) && this.mCurrentUser != currentUser) {
                            qSTile.userSwitch(currentUser);
                        }
                        linkedHashMap.put(next, qSTile);
                    } else {
                        qSTile.destroy();
                    }
                }
                this.mCurrentUser = currentUser;
                ArrayList arrayList = new ArrayList(this.mTileSpecs);
                this.mTileSpecs.clear();
                this.mTileSpecs.addAll(loadTileSpecs);
                this.mTiles.clear();
                this.mTiles.putAll(linkedHashMap);
                if (!linkedHashMap.isEmpty() || loadTileSpecs.isEmpty()) {
                    for (int i = 0; i < this.mCallbacks.size(); i++) {
                        this.mCallbacks.get(i).onTilesChanged();
                    }
                    return;
                }
                if (DEBUG) {
                    Log.d("QSTileHost", "No valid tiles on tuning changed. Setting to default.");
                }
                changeTiles(arrayList, loadTileSpecs(this.mContext, ""));
            }
        }
    }

    static /* synthetic */ boolean lambda$onTuningChanged$2(List list, Map.Entry entry) {
        return !list.contains(entry.getKey());
    }

    static /* synthetic */ void lambda$onTuningChanged$3(Map.Entry entry) {
        if (DEBUG) {
            Log.d("QSTileHost", "Destroying tile: " + ((String) entry.getKey()));
        }
        ((QSTile) entry.getValue()).destroy();
    }

    public void removeTile(String str) {
        changeTileSpecs(new Predicate(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final boolean test(Object obj) {
                return ((List) obj).remove(this.f$0);
            }
        });
    }

    public void unmarkTileAsAutoAdded(String str) {
        AutoTileManager autoTileManager = this.mAutoTiles;
        if (autoTileManager != null) {
            autoTileManager.unmarkTileAsAutoAdded(str);
        }
    }

    public void addTile(String str) {
        changeTileSpecs(new Predicate(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final boolean test(Object obj) {
                return ((List) obj).add(this.f$0);
            }
        });
    }

    private void changeTileSpecs(Predicate<List<String>> predicate) {
        List<String> loadTileSpecs = loadTileSpecs(this.mContext, Settings.Secure.getStringForUser(this.mContext.getContentResolver(), "sysui_qs_tiles", ActivityManager.getCurrentUser()));
        if (predicate.test(loadTileSpecs)) {
            Settings.Secure.putStringForUser(this.mContext.getContentResolver(), "sysui_qs_tiles", TextUtils.join(",", loadTileSpecs), ActivityManager.getCurrentUser());
        }
    }

    public void addTile(ComponentName componentName) {
        ArrayList arrayList = new ArrayList(this.mTileSpecs);
        arrayList.add(0, CustomTile.toSpec(componentName));
        changeTiles(this.mTileSpecs, arrayList);
    }

    public void removeTile(ComponentName componentName) {
        ArrayList arrayList = new ArrayList(this.mTileSpecs);
        arrayList.remove(CustomTile.toSpec(componentName));
        changeTiles(this.mTileSpecs, arrayList);
    }

    public void changeTiles(List<String> list, List<String> list2) {
        int size = list.size();
        list2.size();
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            if (str.startsWith("custom(") && !list2.contains(str)) {
                ComponentName componentFromSpec = CustomTile.getComponentFromSpec(str);
                TileLifecycleManager tileLifecycleManager = new TileLifecycleManager(new Handler(), this.mContext, this.mServices, new Tile(), new Intent().setComponent(componentFromSpec), new UserHandle(ActivityManager.getCurrentUser()));
                tileLifecycleManager.onStopListening();
                tileLifecycleManager.onTileRemoved();
                TileLifecycleManager.setTileAdded(this.mContext, componentFromSpec, false);
                tileLifecycleManager.flushMessagesAndUnbind();
            }
        }
        if (DEBUG) {
            Log.d("QSTileHost", "saveCurrentTiles " + list2);
        }
        Settings.Secure.putStringForUser(getContext().getContentResolver(), "sysui_qs_tiles", TextUtils.join(",", list2), ActivityManager.getCurrentUser());
    }

    public QSTile createTile(String str) {
        for (int i = 0; i < this.mQsFactories.size(); i++) {
            QSTile createTile = this.mQsFactories.get(i).createTile(str);
            if (createTile != null) {
                return createTile;
            }
        }
        return null;
    }

    public QSTileView createTileView(QSTile qSTile, boolean z) {
        for (int i = 0; i < this.mQsFactories.size(); i++) {
            QSTileView createTileView = this.mQsFactories.get(i).createTileView(qSTile, z);
            if (createTileView != null) {
                return createTileView;
            }
        }
        throw new RuntimeException("Default factory didn't create view for " + qSTile.getTileSpec());
    }

    protected static List<String> loadTileSpecs(Context context, String str) {
        Resources resources = context.getResources();
        String string = resources.getString(C1784R$string.quick_settings_tiles_default);
        if (TextUtils.isEmpty(str)) {
            str = resources.getString(C1784R$string.quick_settings_tiles);
            if (DEBUG) {
                Log.d("QSTileHost", "Loaded tile specs from config: " + str);
            }
        } else if (DEBUG) {
            Log.d("QSTileHost", "Loaded tile specs from setting: " + str);
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (String trim : str.split(",")) {
            String trim2 = trim.trim();
            if (!trim2.isEmpty()) {
                if (!trim2.equals("default")) {
                    arrayList.add(trim2);
                } else if (!z) {
                    arrayList.addAll(Arrays.asList(string.split(",")));
                    if (Build.IS_DEBUGGABLE) {
                        arrayList.add("dbg:mem");
                    }
                    z = true;
                }
            }
        }
        return arrayList;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("QSTileHost:");
        this.mTiles.values().stream().filter($$Lambda$QSTileHost$w0YHlhMwIm7qnoeEO7kRZCq47o8.INSTANCE).forEach(new Consumer(fileDescriptor, printWriter, strArr) {
            private final /* synthetic */ FileDescriptor f$0;
            private final /* synthetic */ PrintWriter f$1;
            private final /* synthetic */ String[] f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void accept(Object obj) {
                ((Dumpable) ((QSTile) obj)).dump(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ boolean lambda$dump$6(QSTile qSTile) {
        return qSTile instanceof Dumpable;
    }
}
