package com.android.incallui.answerproximitysensor;

import android.util.ArraySet;
import java.util.Set;

public class PseudoScreenState {
    private final Set<StateChangedListener> listeners = new ArraySet();

    /* renamed from: on */
    private boolean f42on = true;

    public interface StateChangedListener {
        void onPseudoScreenStateChanged(boolean z);
    }

    public void addListener(StateChangedListener stateChangedListener) {
        this.listeners.add(stateChangedListener);
    }

    public boolean isOn() {
        return this.f42on;
    }

    public void removeListener(StateChangedListener stateChangedListener) {
        this.listeners.remove(stateChangedListener);
    }

    public void setOn(boolean z) {
        if (this.f42on != z) {
            this.f42on = z;
            for (StateChangedListener onPseudoScreenStateChanged : this.listeners) {
                onPseudoScreenStateChanged.onPseudoScreenStateChanged(this.f42on);
            }
        }
    }
}
