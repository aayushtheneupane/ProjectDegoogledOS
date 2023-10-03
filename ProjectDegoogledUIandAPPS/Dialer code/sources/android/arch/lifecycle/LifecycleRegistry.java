package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.lifecycle.Lifecycle;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

public class LifecycleRegistry extends Lifecycle {
    private int mAddingObserverCounter = 0;
    private boolean mHandlingEvent = false;
    private final WeakReference<LifecycleOwner> mLifecycleOwner;
    private boolean mNewEventOccurred = false;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap = new FastSafeIterableMap<>();
    private ArrayList<Lifecycle.State> mParentStates = new ArrayList<>();
    private Lifecycle.State mState;

    static class ObserverWithState {
        GenericLifecycleObserver mLifecycleObserver;
        Lifecycle.State mState;

        ObserverWithState(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            this.mLifecycleObserver = Lifecycling.getCallback(lifecycleObserver);
            this.mState = state;
        }

        /* access modifiers changed from: package-private */
        public void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State stateAfter = LifecycleRegistry.getStateAfter(event);
            this.mState = LifecycleRegistry.min(this.mState, stateAfter);
            this.mLifecycleObserver.onStateChanged(lifecycleOwner, event);
            this.mState = stateAfter;
        }
    }

    public LifecycleRegistry(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = new WeakReference<>(lifecycleOwner);
        this.mState = Lifecycle.State.INITIALIZED;
    }

    private Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        Map.Entry<LifecycleObserver, ObserverWithState> ceil = this.mObserverMap.ceil(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State state2 = ceil != null ? ceil.getValue().mState : null;
        if (!this.mParentStates.isEmpty()) {
            ArrayList<Lifecycle.State> arrayList = this.mParentStates;
            state = arrayList.get(arrayList.size() - 1);
        }
        return min(min(this.mState, state2), state);
    }

    static Lifecycle.State getStateAfter(Lifecycle.Event event) {
        int ordinal = event.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return Lifecycle.State.RESUMED;
                }
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return Lifecycle.State.DESTROYED;
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unexpected event value ", event));
                    }
                }
            }
            return Lifecycle.State.STARTED;
        }
        return Lifecycle.State.CREATED;
    }

    static Lifecycle.State min(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    private void moveToState(Lifecycle.State state) {
        if (this.mState != state) {
            this.mState = state;
            if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
                this.mNewEventOccurred = true;
                return;
            }
            this.mHandlingEvent = true;
            sync();
            this.mHandlingEvent = false;
        }
    }

    private void popParentState() {
        ArrayList<Lifecycle.State> arrayList = this.mParentStates;
        arrayList.remove(arrayList.size() - 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001d, code lost:
        r1 = r8.mObserverMap.eldest().getValue().mState;
        r4 = r8.mObserverMap.newest().getValue().mState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sync() {
        /*
            r8 = this;
            java.lang.ref.WeakReference<android.arch.lifecycle.LifecycleOwner> r0 = r8.mLifecycleOwner
            java.lang.Object r0 = r0.get()
            android.arch.lifecycle.LifecycleOwner r0 = (android.arch.lifecycle.LifecycleOwner) r0
            if (r0 != 0) goto L_0x0012
            java.lang.String r8 = "LifecycleRegistry"
            java.lang.String r0 = "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it."
            android.util.Log.w(r8, r0)
            return
        L_0x0012:
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r1 = r8.mObserverMap
            int r1 = r1.size()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x001d
            goto L_0x003f
        L_0x001d:
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r1 = r8.mObserverMap
            java.util.Map$Entry r1 = r1.eldest()
            java.lang.Object r1 = r1.getValue()
            android.arch.lifecycle.LifecycleRegistry$ObserverWithState r1 = (android.arch.lifecycle.LifecycleRegistry.ObserverWithState) r1
            android.arch.lifecycle.Lifecycle$State r1 = r1.mState
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r4 = r8.mObserverMap
            java.util.Map$Entry r4 = r4.newest()
            java.lang.Object r4 = r4.getValue()
            android.arch.lifecycle.LifecycleRegistry$ObserverWithState r4 = (android.arch.lifecycle.LifecycleRegistry.ObserverWithState) r4
            android.arch.lifecycle.Lifecycle$State r4 = r4.mState
            if (r1 != r4) goto L_0x0041
            android.arch.lifecycle.Lifecycle$State r1 = r8.mState
            if (r1 != r4) goto L_0x0041
        L_0x003f:
            r1 = r2
            goto L_0x0042
        L_0x0041:
            r1 = r3
        L_0x0042:
            if (r1 != 0) goto L_0x013b
            r8.mNewEventOccurred = r3
            android.arch.lifecycle.Lifecycle$State r1 = r8.mState
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r3 = r8.mObserverMap
            java.util.Map$Entry r3 = r3.eldest()
            java.lang.Object r3 = r3.getValue()
            android.arch.lifecycle.LifecycleRegistry$ObserverWithState r3 = (android.arch.lifecycle.LifecycleRegistry.ObserverWithState) r3
            android.arch.lifecycle.Lifecycle$State r3 = r3.mState
            int r1 = r1.compareTo(r3)
            if (r1 >= 0) goto L_0x00d5
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r1 = r8.mObserverMap
            java.util.Iterator r1 = r1.descendingIterator()
        L_0x0062:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00d5
            boolean r3 = r8.mNewEventOccurred
            if (r3 != 0) goto L_0x00d5
            java.lang.Object r3 = r1.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getValue()
            android.arch.lifecycle.LifecycleRegistry$ObserverWithState r4 = (android.arch.lifecycle.LifecycleRegistry.ObserverWithState) r4
        L_0x0078:
            android.arch.lifecycle.Lifecycle$State r5 = r4.mState
            android.arch.lifecycle.Lifecycle$State r6 = r8.mState
            int r5 = r5.compareTo(r6)
            if (r5 <= 0) goto L_0x0062
            boolean r5 = r8.mNewEventOccurred
            if (r5 != 0) goto L_0x0062
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r5 = r8.mObserverMap
            java.lang.Object r6 = r3.getKey()
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x0062
            android.arch.lifecycle.Lifecycle$State r5 = r4.mState
            int r6 = r5.ordinal()
            if (r6 == 0) goto L_0x00cf
            if (r6 == r2) goto L_0x00c9
            r7 = 2
            if (r6 == r7) goto L_0x00b7
            r7 = 3
            if (r6 == r7) goto L_0x00b4
            r7 = 4
            if (r6 != r7) goto L_0x00a8
            android.arch.lifecycle.Lifecycle$Event r5 = android.arch.lifecycle.Lifecycle.Event.ON_PAUSE
            goto L_0x00b9
        L_0x00a8:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Unexpected state value "
            java.lang.String r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline6(r0, r5)
            r8.<init>(r0)
            throw r8
        L_0x00b4:
            android.arch.lifecycle.Lifecycle$Event r5 = android.arch.lifecycle.Lifecycle.Event.ON_STOP
            goto L_0x00b9
        L_0x00b7:
            android.arch.lifecycle.Lifecycle$Event r5 = android.arch.lifecycle.Lifecycle.Event.ON_DESTROY
        L_0x00b9:
            android.arch.lifecycle.Lifecycle$State r6 = getStateAfter(r5)
            java.util.ArrayList<android.arch.lifecycle.Lifecycle$State> r7 = r8.mParentStates
            r7.add(r6)
            r4.dispatchEvent(r0, r5)
            r8.popParentState()
            goto L_0x0078
        L_0x00c9:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>()
            throw r8
        L_0x00cf:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>()
            throw r8
        L_0x00d5:
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r1 = r8.mObserverMap
            java.util.Map$Entry r1 = r1.newest()
            boolean r2 = r8.mNewEventOccurred
            if (r2 != 0) goto L_0x0012
            if (r1 == 0) goto L_0x0012
            android.arch.lifecycle.Lifecycle$State r2 = r8.mState
            java.lang.Object r1 = r1.getValue()
            android.arch.lifecycle.LifecycleRegistry$ObserverWithState r1 = (android.arch.lifecycle.LifecycleRegistry.ObserverWithState) r1
            android.arch.lifecycle.Lifecycle$State r1 = r1.mState
            int r1 = r2.compareTo(r1)
            if (r1 <= 0) goto L_0x0012
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r1 = r8.mObserverMap
            android.arch.core.internal.SafeIterableMap$IteratorWithAdditions r1 = r1.iteratorWithAdditions()
        L_0x00f7:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0012
            boolean r2 = r8.mNewEventOccurred
            if (r2 != 0) goto L_0x0012
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getValue()
            android.arch.lifecycle.LifecycleRegistry$ObserverWithState r3 = (android.arch.lifecycle.LifecycleRegistry.ObserverWithState) r3
        L_0x010d:
            android.arch.lifecycle.Lifecycle$State r4 = r3.mState
            android.arch.lifecycle.Lifecycle$State r5 = r8.mState
            int r4 = r4.compareTo(r5)
            if (r4 >= 0) goto L_0x00f7
            boolean r4 = r8.mNewEventOccurred
            if (r4 != 0) goto L_0x00f7
            android.arch.core.internal.FastSafeIterableMap<android.arch.lifecycle.LifecycleObserver, android.arch.lifecycle.LifecycleRegistry$ObserverWithState> r4 = r8.mObserverMap
            java.lang.Object r5 = r2.getKey()
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x00f7
            android.arch.lifecycle.Lifecycle$State r4 = r3.mState
            java.util.ArrayList<android.arch.lifecycle.Lifecycle$State> r5 = r8.mParentStates
            r5.add(r4)
            android.arch.lifecycle.Lifecycle$State r4 = r3.mState
            android.arch.lifecycle.Lifecycle$Event r4 = upEvent(r4)
            r3.dispatchEvent(r0, r4)
            r8.popParentState()
            goto L_0x010d
        L_0x013b:
            r8.mNewEventOccurred = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.LifecycleRegistry.sync():void");
    }

    private static Lifecycle.Event upEvent(Lifecycle.State state) {
        int ordinal = state.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return Lifecycle.Event.ON_CREATE;
        }
        if (ordinal == 2) {
            return Lifecycle.Event.ON_START;
        }
        if (ordinal == 3) {
            return Lifecycle.Event.ON_RESUME;
        }
        if (ordinal != 4) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unexpected state value ", state));
        }
        throw new IllegalArgumentException();
    }

    public void addObserver(LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner;
        Lifecycle.State state = this.mState;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, state2);
        if (this.mObserverMap.putIfAbsent(lifecycleObserver, observerWithState) == null && (lifecycleOwner = (LifecycleOwner) this.mLifecycleOwner.get()) != null) {
            boolean z = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
            Lifecycle.State calculateTargetState = calculateTargetState(lifecycleObserver);
            this.mAddingObserverCounter++;
            while (observerWithState.mState.compareTo(calculateTargetState) < 0 && this.mObserverMap.contains(lifecycleObserver)) {
                this.mParentStates.add(observerWithState.mState);
                observerWithState.dispatchEvent(lifecycleOwner, upEvent(observerWithState.mState));
                popParentState();
                calculateTargetState = calculateTargetState(lifecycleObserver);
            }
            if (!z) {
                sync();
            }
            this.mAddingObserverCounter--;
        }
    }

    public Lifecycle.State getCurrentState() {
        return this.mState;
    }

    public void handleLifecycleEvent(Lifecycle.Event event) {
        moveToState(getStateAfter(event));
    }

    public void markState(Lifecycle.State state) {
        moveToState(state);
    }

    public void removeObserver(LifecycleObserver lifecycleObserver) {
        this.mObserverMap.remove(lifecycleObserver);
    }
}
