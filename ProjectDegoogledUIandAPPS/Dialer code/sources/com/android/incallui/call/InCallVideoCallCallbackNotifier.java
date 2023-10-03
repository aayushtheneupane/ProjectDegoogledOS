package com.android.incallui.call;

import com.android.incallui.VideoCallPresenter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InCallVideoCallCallbackNotifier {
    private static InCallVideoCallCallbackNotifier instance = new InCallVideoCallCallbackNotifier();
    private final Set<SurfaceChangeListener> surfaceChangeListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));

    public interface SurfaceChangeListener {
    }

    private InCallVideoCallCallbackNotifier() {
    }

    public static InCallVideoCallCallbackNotifier getInstance() {
        return instance;
    }

    public void addSurfaceChangeListener(SurfaceChangeListener surfaceChangeListener) {
        Objects.requireNonNull(surfaceChangeListener);
        this.surfaceChangeListeners.add(surfaceChangeListener);
    }

    public void cameraDimensionsChanged(DialerCall dialerCall, int i, int i2) {
        Iterator<SurfaceChangeListener> it = this.surfaceChangeListeners.iterator();
        while (it.hasNext()) {
            ((VideoCallPresenter) it.next()).onCameraDimensionsChange(dialerCall, i, i2);
        }
    }

    public void peerDimensionsChanged(DialerCall dialerCall, int i, int i2) {
        Iterator<SurfaceChangeListener> it = this.surfaceChangeListeners.iterator();
        while (it.hasNext()) {
            ((VideoCallPresenter) it.next()).onUpdatePeerDimensions(dialerCall, i, i2);
        }
    }

    public void removeSurfaceChangeListener(SurfaceChangeListener surfaceChangeListener) {
        if (surfaceChangeListener != null) {
            this.surfaceChangeListeners.remove(surfaceChangeListener);
        }
    }
}
