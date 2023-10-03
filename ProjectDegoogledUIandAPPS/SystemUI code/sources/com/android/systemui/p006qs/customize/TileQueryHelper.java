package com.android.systemui.p006qs.customize;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.widget.Button;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.QSTileHost;
import com.android.systemui.p006qs.external.CustomTile;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.systemui.qs.customize.TileQueryHelper */
public class TileQueryHelper {
    private final Handler mBgHandler;
    private final Context mContext;
    private boolean mFinished;
    private final TileStateListener mListener;
    private final Handler mMainHandler;
    private final ArraySet<String> mSpecs = new ArraySet<>();
    private final ArrayList<TileInfo> mTiles = new ArrayList<>();

    /* renamed from: com.android.systemui.qs.customize.TileQueryHelper$TileInfo */
    public static class TileInfo {
        public boolean isSystem;
        public String spec;
        public QSTile.State state;
    }

    /* renamed from: com.android.systemui.qs.customize.TileQueryHelper$TileStateListener */
    public interface TileStateListener {
        void onTilesChanged(List<TileInfo> list);
    }

    public TileQueryHelper(Context context, TileStateListener tileStateListener) {
        this.mContext = context;
        this.mListener = tileStateListener;
        this.mBgHandler = new Handler((Looper) Dependency.get(Dependency.BG_LOOPER));
        this.mMainHandler = (Handler) Dependency.get(Dependency.MAIN_HANDLER);
    }

    public void queryTiles(QSTileHost qSTileHost) {
        this.mTiles.clear();
        this.mSpecs.clear();
        this.mFinished = false;
        addCurrentAndStockTiles(qSTileHost);
        addPackageTiles(qSTileHost);
    }

    public boolean isFinished() {
        return this.mFinished;
    }

    private void addCurrentAndStockTiles(QSTileHost qSTileHost) {
        QSTile createTile;
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), "sysui_qs_tiles");
        String str = this.mContext.getString(C1784R$string.quick_settings_tiles_stock) + "," + this.mContext.getString(C1784R$string.quick_settings_tiles_extra) + "," + string;
        ArrayList arrayList = new ArrayList();
        if (string != null) {
            arrayList.addAll(Arrays.asList(string.split(",")));
        } else {
            string = "";
        }
        for (String str2 : str.split(",")) {
            if (!string.contains(str2)) {
                arrayList.add(str2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            if (!str3.startsWith("custom(") && (createTile = qSTileHost.createTile(str3)) != null) {
                if (!createTile.isAvailable()) {
                    createTile.destroy();
                } else {
                    createTile.setListening(this, true);
                    createTile.refreshState();
                    createTile.setListening(this, false);
                    createTile.setTileSpec(str3);
                    arrayList2.add(createTile);
                }
            }
        }
        this.mBgHandler.post(new Runnable(arrayList2) {
            private final /* synthetic */ ArrayList f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                TileQueryHelper.this.lambda$addCurrentAndStockTiles$0$TileQueryHelper(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$addCurrentAndStockTiles$0$TileQueryHelper(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            QSTile qSTile = (QSTile) it.next();
            QSTile.State copy = qSTile.getState().copy();
            copy.label = qSTile.getTileLabel();
            qSTile.destroy();
            addTile(qSTile.getTileSpec(), (CharSequence) null, copy, true);
        }
        notifyTilesChanged(false);
    }

    private void addPackageTiles(QSTileHost qSTileHost) {
        this.mBgHandler.post(new Runnable(qSTileHost) {
            private final /* synthetic */ QSTileHost f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                TileQueryHelper.this.lambda$addPackageTiles$1$TileQueryHelper(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$addPackageTiles$1$TileQueryHelper(QSTileHost qSTileHost) {
        Collection<QSTile> tiles = qSTileHost.getTiles();
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> queryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 0, ActivityManager.getCurrentUser());
        String string = this.mContext.getString(C1784R$string.quick_settings_tiles_stock);
        for (ResolveInfo resolveInfo : queryIntentServicesAsUser) {
            ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
            if (!string.contains(componentName.flattenToString())) {
                CharSequence loadLabel = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                String spec = CustomTile.toSpec(componentName);
                QSTile.State state = getState(tiles, spec);
                if (state != null) {
                    addTile(spec, loadLabel, state, false);
                } else if (resolveInfo.serviceInfo.icon != 0 || resolveInfo.serviceInfo.applicationInfo.icon != 0) {
                    Drawable loadIcon = resolveInfo.serviceInfo.loadIcon(packageManager);
                    if ("android.permission.BIND_QUICK_SETTINGS_TILE".equals(resolveInfo.serviceInfo.permission) && loadIcon != null) {
                        loadIcon.mutate();
                        loadIcon.setTint(this.mContext.getColor(17170443));
                        CharSequence loadLabel2 = resolveInfo.serviceInfo.loadLabel(packageManager);
                        createStateAndAddTile(spec, loadIcon, loadLabel2 != null ? loadLabel2.toString() : "null", loadLabel);
                    }
                }
            }
        }
        notifyTilesChanged(true);
    }

    private void notifyTilesChanged(boolean z) {
        this.mMainHandler.post(new Runnable(new ArrayList(this.mTiles), z) {
            private final /* synthetic */ ArrayList f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                TileQueryHelper.this.lambda$notifyTilesChanged$2$TileQueryHelper(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$notifyTilesChanged$2$TileQueryHelper(ArrayList arrayList, boolean z) {
        this.mListener.onTilesChanged(arrayList);
        this.mFinished = z;
    }

    private QSTile.State getState(Collection<QSTile> collection, String str) {
        for (QSTile next : collection) {
            if (str.equals(next.getTileSpec())) {
                return next.getState().copy();
            }
        }
        return null;
    }

    private void addTile(String str, CharSequence charSequence, QSTile.State state, boolean z) {
        if (!this.mSpecs.contains(str)) {
            TileInfo tileInfo = new TileInfo();
            tileInfo.state = state;
            QSTile.State state2 = tileInfo.state;
            state2.dualTarget = false;
            state2.expandedAccessibilityClassName = Button.class.getName();
            tileInfo.spec = str;
            QSTile.State state3 = tileInfo.state;
            if (z || TextUtils.equals(state.label, charSequence)) {
                charSequence = null;
            }
            state3.secondaryLabel = charSequence;
            tileInfo.isSystem = z;
            this.mTiles.add(tileInfo);
            this.mSpecs.add(str);
        }
    }

    private void createStateAndAddTile(String str, Drawable drawable, CharSequence charSequence, CharSequence charSequence2) {
        QSTile.State state = new QSTile.State();
        state.state = 1;
        state.label = charSequence;
        state.contentDescription = charSequence;
        state.icon = new QSTileImpl.DrawableIcon(drawable);
        addTile(str, charSequence2, state, false);
    }
}
