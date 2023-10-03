package androidx.recyclerview.widget;

import android.database.Observable;

/* renamed from: androidx.recyclerview.widget.Q */
class C0544Q extends Observable {
    C0544Q() {
    }

    public boolean hasObservers() {
        return !this.mObservers.isEmpty();
    }

    public void notifyChanged() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            ((C0545S) this.mObservers.get(size)).onChanged();
        }
    }
}
