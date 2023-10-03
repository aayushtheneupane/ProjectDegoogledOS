package com.android.systemui.recents;

import android.content.ContentResolver;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.provider.Settings;
import com.android.systemui.C1784R$string;
import com.android.systemui.SystemUI;
import com.android.systemui.statusbar.CommandQueue;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Recents extends SystemUI implements CommandQueue.Callbacks {
    private RecentsImplementation mImpl;

    public void start() {
        ((CommandQueue) getComponent(CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
        putComponent(Recents.class, this);
        this.mImpl = createRecentsImplementationFromConfig();
        this.mImpl.onStart(this.mContext, this);
    }

    public void onBootCompleted() {
        this.mImpl.onBootCompleted();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mImpl.onConfigurationChanged(configuration);
    }

    public void appTransitionFinished(int i) {
        if (this.mContext.getDisplayId() == i) {
            this.mImpl.onAppTransitionFinished();
        }
    }

    public void growRecents() {
        this.mImpl.growRecents();
    }

    public void showRecentApps(boolean z) {
        if (isUserSetup()) {
            this.mImpl.showRecentApps(z);
        }
    }

    public void hideRecentApps(boolean z, boolean z2) {
        if (isUserSetup()) {
            this.mImpl.hideRecentApps(z, z2);
        }
    }

    public void toggleRecentApps() {
        if (isUserSetup()) {
            this.mImpl.toggleRecentApps();
        }
    }

    public void preloadRecentApps() {
        if (isUserSetup()) {
            this.mImpl.preloadRecentApps();
        }
    }

    public void cancelPreloadRecentApps() {
        if (isUserSetup()) {
            this.mImpl.cancelPreloadRecentApps();
        }
    }

    public boolean splitPrimaryTask(int i, Rect rect, int i2) {
        if (!isUserSetup()) {
            return false;
        }
        return this.mImpl.splitPrimaryTask(i, rect, i2);
    }

    private boolean isUserSetup() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (Settings.Global.getInt(contentResolver, "device_provisioned", 0) == 0 || Settings.Secure.getInt(contentResolver, "user_setup_complete", 0) == 0) {
            return false;
        }
        return true;
    }

    private RecentsImplementation createRecentsImplementationFromConfig() {
        String string = this.mContext.getString(C1784R$string.config_recentsComponent);
        if (string == null || string.length() == 0) {
            throw new RuntimeException("No recents component configured", (Throwable) null);
        }
        try {
            try {
                return (RecentsImplementation) this.mContext.getClassLoader().loadClass(string).newInstance();
            } catch (Throwable th) {
                throw new RuntimeException("Error creating recents component: " + string, th);
            }
        } catch (Throwable th2) {
            throw new RuntimeException("Error loading recents component: " + string, th2);
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mImpl.dump(printWriter);
    }
}
