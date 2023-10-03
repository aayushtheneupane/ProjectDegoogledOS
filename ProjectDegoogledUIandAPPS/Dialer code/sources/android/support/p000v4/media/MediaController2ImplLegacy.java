package android.support.p000v4.media;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.ResultReceiver;
import android.support.p000v4.media.session.MediaControllerCompat$Callback;
import android.util.Log;
import java.util.concurrent.Executor;

@TargetApi(16)
/* renamed from: android.support.v4.media.MediaController2ImplLegacy */
class MediaController2ImplLegacy implements MediaController2$SupportLibraryImpl {
    static final Bundle sDefaultRootExtras = new Bundle();
    /* access modifiers changed from: private */
    public final Executor mCallbackExecutor;
    /* access modifiers changed from: private */
    public final HandlerThread mHandlerThread;

    /* renamed from: android.support.v4.media.MediaController2ImplLegacy$3 */
    class C01163 extends ResultReceiver {
        final /* synthetic */ MediaController2ImplLegacy this$0;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            if (null.mHandlerThread.isAlive()) {
                if (i == -1) {
                    null.mCallbackExecutor.execute(new Runnable() {
                        public void run() {
                            MediaController2ImplLegacy mediaController2ImplLegacy = C01163.this.this$0;
                            MediaController2ImplLegacy.access$100();
                            MediaController2ImplLegacy mediaController2ImplLegacy2 = C01163.this.this$0;
                            throw null;
                        }
                    });
                    throw null;
                } else if (i == 0) {
                    throw null;
                }
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaController2ImplLegacy$ControllerCompatCallback */
    private final class ControllerCompatCallback extends MediaControllerCompat$Callback {
        final /* synthetic */ MediaController2ImplLegacy this$0;

        /* renamed from: android.support.v4.media.MediaController2ImplLegacy$ControllerCompatCallback$1 */
        class C01181 extends ResultReceiver {
            final /* synthetic */ ControllerCompatCallback this$1;

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                MediaController2ImplLegacy mediaController2ImplLegacy = null.this$0;
                if (null.mHandlerThread.isAlive()) {
                    if (i == -1) {
                        MediaController2ImplLegacy mediaController2ImplLegacy2 = null.this$0;
                        null.mCallbackExecutor.execute(new Runnable() {
                            public void run() {
                                ControllerCompatCallback controllerCompatCallback = C01181.this.this$1;
                                throw null;
                            }
                        });
                        MediaController2ImplLegacy mediaController2ImplLegacy3 = null.this$0;
                        throw null;
                    } else if (i == 0) {
                        MediaController2ImplLegacy mediaController2ImplLegacy4 = null.this$0;
                        throw null;
                    }
                }
            }
        }
    }

    static {
        Log.isLoggable("MC2ImplLegacy", 3);
        sDefaultRootExtras.putBoolean("android.support.v4.media.root_default_root", true);
    }

    static /* synthetic */ void access$100() {
    }
}
