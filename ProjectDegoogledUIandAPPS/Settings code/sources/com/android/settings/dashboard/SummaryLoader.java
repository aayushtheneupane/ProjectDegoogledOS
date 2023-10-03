package com.android.settings.dashboard;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import com.android.settingslib.utils.ThreadUtils;
import java.util.List;

public class SummaryLoader {
    public static final String SUMMARY_PROVIDER_FACTORY = "SUMMARY_PROVIDER_FACTORY";
    private final Activity mActivity;
    /* access modifiers changed from: private */
    public final String mCategoryKey;
    /* access modifiers changed from: private */
    public final DashboardFeatureProvider mDashboardFeatureProvider;
    private boolean mListening;
    private ArraySet<BroadcastReceiver> mReceivers = new ArraySet<>();
    private SummaryConsumer mSummaryConsumer;
    private final ArrayMap<SummaryProvider, ComponentName> mSummaryProviderMap = new ArrayMap<>();
    private final ArrayMap<String, CharSequence> mSummaryTextMap = new ArrayMap<>();
    private final Worker mWorker;
    private boolean mWorkerListening;
    private final HandlerThread mWorkerThread;

    public interface SummaryConsumer {
        void notifySummaryChanged(Tile tile);
    }

    public interface SummaryProvider {
        void setListening(boolean z);
    }

    public interface SummaryProviderFactory {
        SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader);
    }

    public SummaryLoader(Activity activity, String str) {
        this.mDashboardFeatureProvider = FeatureFactory.getFactory(activity).getDashboardFeatureProvider(activity);
        this.mCategoryKey = str;
        this.mWorkerThread = new HandlerThread("SummaryLoader", 10);
        this.mWorkerThread.start();
        this.mWorker = new Worker(this.mWorkerThread.getLooper());
        this.mActivity = activity;
    }

    public void release() {
        this.mWorkerThread.quitSafely();
        setListeningW(false);
    }

    public void setSummaryConsumer(SummaryConsumer summaryConsumer) {
        this.mSummaryConsumer = summaryConsumer;
    }

    public void setSummary(SummaryProvider summaryProvider, CharSequence charSequence) {
        ThreadUtils.postOnMainThread(new Runnable(this.mSummaryProviderMap.get(summaryProvider), charSequence) {
            private final /* synthetic */ ComponentName f$1;
            private final /* synthetic */ CharSequence f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                SummaryLoader.this.lambda$setSummary$0$SummaryLoader(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$setSummary$0$SummaryLoader(ComponentName componentName, CharSequence charSequence) {
        Tile tileFromCategory = getTileFromCategory(this.mDashboardFeatureProvider.getTilesForCategory(this.mCategoryKey), componentName);
        if (tileFromCategory != null) {
            updateSummaryIfNeeded(this.mActivity.getApplicationContext(), tileFromCategory, charSequence);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateSummaryIfNeeded(Context context, Tile tile, CharSequence charSequence) {
        if (!TextUtils.equals(tile.getSummary(context), charSequence)) {
            this.mSummaryTextMap.put(this.mDashboardFeatureProvider.getDashboardKeyForTile(tile), charSequence);
            tile.overrideSummary(charSequence);
            SummaryConsumer summaryConsumer = this.mSummaryConsumer;
            if (summaryConsumer != null) {
                summaryConsumer.notifySummaryChanged(tile);
            }
        }
    }

    public void setListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
            for (int i = 0; i < this.mReceivers.size(); i++) {
                this.mActivity.unregisterReceiver(this.mReceivers.valueAt(i));
            }
            this.mReceivers.clear();
            this.mWorker.removeMessages(3);
            if (!z) {
                this.mWorker.obtainMessage(3, 0).sendToTarget();
            } else if (!this.mSummaryProviderMap.isEmpty()) {
                this.mWorker.obtainMessage(3, 1).sendToTarget();
            } else if (!this.mWorker.hasMessages(1)) {
                this.mWorker.sendEmptyMessage(1);
            }
        }
    }

    private SummaryProvider getSummaryProvider(Tile tile) {
        if (!this.mActivity.getPackageName().equals(tile.getPackageName())) {
            return null;
        }
        Bundle metaData = tile.getMetaData();
        Intent intent = tile.getIntent();
        if (metaData == null) {
            Log.d("SummaryLoader", "No metadata specified for " + intent.getComponent());
            return null;
        }
        String string = metaData.getString("com.android.settings.FRAGMENT_CLASS");
        if (string == null) {
            Log.d("SummaryLoader", "No fragment specified for " + intent.getComponent());
            return null;
        }
        try {
            return ((SummaryProviderFactory) Class.forName(string).getField(SUMMARY_PROVIDER_FACTORY).get((Object) null)).createSummaryProvider(this.mActivity, this);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public synchronized void setListeningW(boolean z) {
        if (this.mWorkerListening != z) {
            this.mWorkerListening = z;
            for (SummaryProvider listening : this.mSummaryProviderMap.keySet()) {
                try {
                    listening.setListening(z);
                } catch (Exception e) {
                    Log.d("SummaryLoader", "Problem in setListening", e);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void makeProviderW(Tile tile) {
        SummaryProvider summaryProvider = getSummaryProvider(tile);
        if (summaryProvider != null) {
            this.mSummaryProviderMap.put(summaryProvider, tile.getIntent().getComponent());
        }
    }

    private Tile getTileFromCategory(DashboardCategory dashboardCategory, ComponentName componentName) {
        if (!(dashboardCategory == null || dashboardCategory.getTilesCount() == 0)) {
            List<Tile> tiles = dashboardCategory.getTiles();
            int size = tiles.size();
            for (int i = 0; i < size; i++) {
                Tile tile = tiles.get(i);
                if (componentName.equals(tile.getIntent().getComponent())) {
                    return tile;
                }
            }
        }
        return null;
    }

    private class Worker extends Handler {
        public Worker(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            boolean z = true;
            if (i == 1) {
                DashboardCategory tilesForCategory = SummaryLoader.this.mDashboardFeatureProvider.getTilesForCategory(SummaryLoader.this.mCategoryKey);
                if (tilesForCategory != null && tilesForCategory.getTilesCount() != 0) {
                    for (Tile access$200 : tilesForCategory.getTiles()) {
                        SummaryLoader.this.makeProviderW(access$200);
                    }
                    SummaryLoader.this.setListeningW(true);
                }
            } else if (i == 2) {
                SummaryLoader.this.makeProviderW((Tile) message.obj);
            } else if (i == 3) {
                Object obj = message.obj;
                if (obj == null || !obj.equals(1)) {
                    z = false;
                }
                SummaryLoader.this.setListeningW(z);
            }
        }
    }
}
