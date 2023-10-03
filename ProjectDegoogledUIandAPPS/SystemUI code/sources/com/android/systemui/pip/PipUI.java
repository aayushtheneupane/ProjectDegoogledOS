package com.android.systemui.pip;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.UserManager;
import com.android.systemui.SystemUI;
import com.android.systemui.pip.p004tv.PipManager;
import com.android.systemui.statusbar.CommandQueue;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class PipUI extends SystemUI implements CommandQueue.Callbacks {
    private BasePipManager mPipManager;
    private boolean mSupportsPip;

    public void start() {
        BasePipManager basePipManager;
        PackageManager packageManager = this.mContext.getPackageManager();
        this.mSupportsPip = packageManager.hasSystemFeature("android.software.picture_in_picture");
        if (this.mSupportsPip) {
            if (UserManager.get(this.mContext).getUserHandle() == 0) {
                if (packageManager.hasSystemFeature("android.software.leanback_only")) {
                    basePipManager = PipManager.getInstance();
                } else {
                    basePipManager = com.android.systemui.pip.phone.PipManager.getInstance();
                }
                this.mPipManager = basePipManager;
                this.mPipManager.initialize(this.mContext);
                ((CommandQueue) getComponent(CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
                putComponent(PipUI.class, this);
                return;
            }
            throw new IllegalStateException("Non-primary Pip component not currently supported.");
        }
    }

    public void showPictureInPictureMenu() {
        this.mPipManager.showPictureInPictureMenu();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        BasePipManager basePipManager = this.mPipManager;
        if (basePipManager != null) {
            basePipManager.onConfigurationChanged(configuration);
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        BasePipManager basePipManager = this.mPipManager;
        if (basePipManager != null) {
            basePipManager.dump(printWriter);
        }
    }
}
