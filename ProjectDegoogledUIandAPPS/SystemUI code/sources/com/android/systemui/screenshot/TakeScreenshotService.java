package com.android.systemui.screenshot;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import java.util.function.Consumer;

public class TakeScreenshotService extends Service {
    /* access modifiers changed from: private */
    public static GlobalScreenshot mScreenshot;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            final Messenger messenger = message.replyTo;
            C10111 r1 = new Consumer<Uri>() {
                public void accept(Uri uri) {
                    try {
                        messenger.send(Message.obtain((Handler) null, 1, uri));
                    } catch (RemoteException unused) {
                    }
                }
            };
            if (!((UserManager) TakeScreenshotService.this.getSystemService(UserManager.class)).isUserUnlocked()) {
                Log.w("TakeScreenshotService", "Skipping screenshot because storage is locked!");
                post(new Runnable(r1) {
                    private final /* synthetic */ Consumer f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void run() {
                        this.f$0.accept((Object) null);
                    }
                });
                return;
            }
            if (TakeScreenshotService.mScreenshot == null) {
                GlobalScreenshot unused = TakeScreenshotService.mScreenshot = new GlobalScreenshot(TakeScreenshotService.this);
            }
            int i = message.what;
            boolean z = false;
            if (i == 1) {
                GlobalScreenshot access$000 = TakeScreenshotService.mScreenshot;
                boolean z2 = message.arg1 > 0;
                if (message.arg2 > 0) {
                    z = true;
                }
                access$000.takeScreenshot(r1, z2, z);
            } else if (i != 2) {
                Log.d("TakeScreenshotService", "Invalid screenshot option: " + message.what);
            } else {
                GlobalScreenshot access$0002 = TakeScreenshotService.mScreenshot;
                boolean z3 = message.arg1 > 0;
                if (message.arg2 > 0) {
                    z = true;
                }
                access$0002.takeScreenshotPartial(r1, z3, z);
            }
        }
    };

    public IBinder onBind(Intent intent) {
        return new Messenger(this.mHandler).getBinder();
    }

    public boolean onUnbind(Intent intent) {
        GlobalScreenshot globalScreenshot = mScreenshot;
        if (globalScreenshot == null) {
            return true;
        }
        globalScreenshot.stopScreenshot();
        return true;
    }
}
