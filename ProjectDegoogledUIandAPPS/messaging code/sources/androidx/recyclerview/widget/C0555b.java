package androidx.recyclerview.widget;

import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.b */
class C0555b {

    /* renamed from: Tq */
    private Pools.Pool f624Tq = new Pools.SimplePool(30);

    /* renamed from: Uq */
    private int f625Uq = 0;
    final C0542O mCallback;
    final boolean mDisableRecycler;
    Runnable mOnItemProcessedCallback;
    final C0531F mOpReorderer;
    final ArrayList mPendingUpdates = new ArrayList();
    final ArrayList mPostponedList = new ArrayList();

    C0555b(C0542O o) {
        this.mCallback = o;
        this.mDisableRecycler = false;
        this.mOpReorderer = new C0531F(this);
    }

    /* renamed from: R */
    private int m812R(int i, int i2) {
        for (int size = this.mPostponedList.size() - 1; size >= 0; size--) {
            C0553a aVar = (C0553a) this.mPostponedList.get(size);
            int i3 = aVar.cmd;
            if (i3 == 8) {
                int i4 = aVar.positionStart;
                int i5 = aVar.itemCount;
                if (i4 >= i5) {
                    int i6 = i5;
                    i5 = i4;
                    i4 = i6;
                }
                if (i < i4 || i > i5) {
                    int i7 = aVar.positionStart;
                    if (i < i7) {
                        if (i2 == 1) {
                            aVar.positionStart = i7 + 1;
                            aVar.itemCount++;
                        } else if (i2 == 2) {
                            aVar.positionStart = i7 - 1;
                            aVar.itemCount--;
                        }
                    }
                } else {
                    int i8 = aVar.positionStart;
                    if (i4 == i8) {
                        if (i2 == 1) {
                            aVar.itemCount++;
                        } else if (i2 == 2) {
                            aVar.itemCount--;
                        }
                        i++;
                    } else {
                        if (i2 == 1) {
                            aVar.positionStart = i8 + 1;
                        } else if (i2 == 2) {
                            aVar.positionStart = i8 - 1;
                        }
                        i--;
                    }
                }
            } else {
                int i9 = aVar.positionStart;
                if (i9 <= i) {
                    if (i3 == 1) {
                        i -= aVar.itemCount;
                    } else if (i3 == 2) {
                        i += aVar.itemCount;
                    }
                } else if (i2 == 1) {
                    aVar.positionStart = i9 + 1;
                } else if (i2 == 2) {
                    aVar.positionStart = i9 - 1;
                }
            }
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            C0553a aVar2 = (C0553a) this.mPostponedList.get(size2);
            if (aVar2.cmd == 8) {
                int i10 = aVar2.itemCount;
                if (i10 == aVar2.positionStart || i10 < 0) {
                    this.mPostponedList.remove(size2);
                    if (!this.mDisableRecycler) {
                        aVar2.payload = null;
                        this.f624Tq.release(aVar2);
                    }
                }
            } else if (aVar2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                if (!this.mDisableRecycler) {
                    aVar2.payload = null;
                    this.f624Tq.release(aVar2);
                }
            }
        }
        return i;
    }

    /* renamed from: c */
    private void m813c(C0553a aVar) {
        int i;
        int i2 = aVar.cmd;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int R = m812R(aVar.positionStart, i2);
        int i3 = aVar.positionStart;
        int i4 = aVar.cmd;
        if (i4 == 2) {
            i = 0;
        } else if (i4 == 4) {
            i = 1;
        } else {
            throw new IllegalArgumentException(C0632a.m1014a("op should be remove or update.", aVar));
        }
        int i5 = R;
        int i6 = i3;
        int i7 = 1;
        for (int i8 = 1; i8 < aVar.itemCount; i8++) {
            int R2 = m812R((i * i8) + aVar.positionStart, aVar.cmd);
            int i9 = aVar.cmd;
            if (i9 == 2 ? R2 == i5 : i9 == 4 && R2 == i5 + 1) {
                i7++;
            } else {
                C0553a obtainUpdateOp = obtainUpdateOp(aVar.cmd, i5, i7, aVar.payload);
                mo4994a(obtainUpdateOp, i6);
                if (!this.mDisableRecycler) {
                    obtainUpdateOp.payload = null;
                    this.f624Tq.release(obtainUpdateOp);
                }
                if (aVar.cmd == 4) {
                    i6 += i7;
                }
                i7 = 1;
                i5 = R2;
            }
        }
        Object obj = aVar.payload;
        if (!this.mDisableRecycler) {
            aVar.payload = null;
            this.f624Tq.release(aVar);
        }
        if (i7 > 0) {
            C0553a obtainUpdateOp2 = obtainUpdateOp(aVar.cmd, i5, i7, obj);
            mo4994a(obtainUpdateOp2, i6);
            if (!this.mDisableRecycler) {
                obtainUpdateOp2.payload = null;
                this.f624Tq.release(obtainUpdateOp2);
            }
        }
    }

    /* renamed from: d */
    private void m814d(C0553a aVar) {
        this.mPostponedList.add(aVar);
        int i = aVar.cmd;
        if (i == 1) {
            C0542O o = this.mCallback;
            o.this$0.offsetPositionRecordsForInsert(aVar.positionStart, aVar.itemCount);
            o.this$0.mItemsAddedOrRemoved = true;
        } else if (i == 2) {
            C0542O o2 = this.mCallback;
            o2.this$0.offsetPositionRecordsForRemove(aVar.positionStart, aVar.itemCount, false);
            o2.this$0.mItemsAddedOrRemoved = true;
        } else if (i == 4) {
            this.mCallback.markViewHoldersUpdated(aVar.positionStart, aVar.itemCount, aVar.payload);
        } else if (i == 8) {
            C0542O o3 = this.mCallback;
            o3.this$0.offsetPositionRecordsForMove(aVar.positionStart, aVar.itemCount);
            o3.this$0.mItemsAddedOrRemoved = true;
        } else {
            throw new IllegalArgumentException(C0632a.m1014a("Unknown update op type for ", aVar));
        }
    }

    /* renamed from: qb */
    private boolean m815qb(int i) {
        int size = this.mPostponedList.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0553a aVar = (C0553a) this.mPostponedList.get(i2);
            int i3 = aVar.cmd;
            if (i3 == 8) {
                if (findPositionOffset(aVar.itemCount, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = aVar.positionStart;
                int i5 = aVar.itemCount + i4;
                while (i4 < i5) {
                    if (findPositionOffset(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4994a(C0553a aVar, int i) {
        this.mCallback.mo4793b(aVar);
        int i2 = aVar.cmd;
        if (i2 == 2) {
            C0542O o = this.mCallback;
            int i3 = aVar.itemCount;
            o.this$0.offsetPositionRecordsForRemove(i, i3, true);
            RecyclerView recyclerView = o.this$0;
            recyclerView.mItemsAddedOrRemoved = true;
            recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i3;
        } else if (i2 == 4) {
            C0542O o2 = this.mCallback;
            o2.this$0.viewRangeUpdate(i, aVar.itemCount, aVar.payload);
            o2.this$0.mItemsChanged = true;
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    /* access modifiers changed from: package-private */
    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int i = 0; i < size; i++) {
            this.mCallback.mo4793b((C0553a) this.mPostponedList.get(i));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.f625Uq = 0;
    }

    /* access modifiers changed from: package-private */
    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int i = 0; i < size; i++) {
            C0553a aVar = (C0553a) this.mPendingUpdates.get(i);
            int i2 = aVar.cmd;
            if (i2 == 1) {
                this.mCallback.mo4793b(aVar);
                C0542O o = this.mCallback;
                o.this$0.offsetPositionRecordsForInsert(aVar.positionStart, aVar.itemCount);
                o.this$0.mItemsAddedOrRemoved = true;
            } else if (i2 == 2) {
                this.mCallback.mo4793b(aVar);
                C0542O o2 = this.mCallback;
                int i3 = aVar.positionStart;
                int i4 = aVar.itemCount;
                o2.this$0.offsetPositionRecordsForRemove(i3, i4, true);
                RecyclerView recyclerView = o2.this$0;
                recyclerView.mItemsAddedOrRemoved = true;
                recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i4;
            } else if (i2 == 4) {
                this.mCallback.mo4793b(aVar);
                this.mCallback.markViewHoldersUpdated(aVar.positionStart, aVar.itemCount, aVar.payload);
            } else if (i2 == 8) {
                this.mCallback.mo4793b(aVar);
                C0542O o3 = this.mCallback;
                o3.this$0.offsetPositionRecordsForMove(aVar.positionStart, aVar.itemCount);
                o3.this$0.mItemsAddedOrRemoved = true;
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.f625Uq = 0;
    }

    /* access modifiers changed from: package-private */
    public int findPositionOffset(int i, int i2) {
        int size = this.mPostponedList.size();
        while (i2 < size) {
            C0553a aVar = (C0553a) this.mPostponedList.get(i2);
            int i3 = aVar.cmd;
            if (i3 == 8) {
                int i4 = aVar.positionStart;
                if (i4 == i) {
                    i = aVar.itemCount;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (aVar.itemCount <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = aVar.positionStart;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = aVar.itemCount;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += aVar.itemCount;
                }
            }
            i2++;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnyUpdateTypes(int i) {
        return (this.f625Uq & i) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    public C0553a obtainUpdateOp(int i, int i2, int i3, Object obj) {
        C0553a aVar = (C0553a) this.f624Tq.acquire();
        if (aVar == null) {
            return new C0553a(i, i2, i3, obj);
        }
        aVar.cmd = i;
        aVar.positionStart = i2;
        aVar.itemCount = i3;
        aVar.payload = obj;
        return aVar;
    }

    /* access modifiers changed from: package-private */
    public void preProcess() {
        boolean z;
        boolean z2;
        boolean z3;
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int size = this.mPendingUpdates.size();
        for (int i = 0; i < size; i++) {
            C0553a aVar = (C0553a) this.mPendingUpdates.get(i);
            int i2 = aVar.cmd;
            if (i2 == 1) {
                m814d(aVar);
            } else if (i2 == 2) {
                int i3 = aVar.positionStart;
                int i4 = aVar.itemCount + i3;
                int i5 = 0;
                boolean z4 = true;
                int i6 = i3;
                while (i6 < i4) {
                    if (this.mCallback.findViewHolder(i6) != null || m815qb(i6)) {
                        if (!z4) {
                            m813c(obtainUpdateOp(2, i3, i5, (Object) null));
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z = true;
                    } else {
                        if (z4) {
                            m814d(obtainUpdateOp(2, i3, i5, (Object) null));
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z = false;
                    }
                    if (z2) {
                        i6 -= i5;
                        i4 -= i5;
                        i5 = 1;
                    } else {
                        i5++;
                    }
                    i6++;
                    z4 = z;
                }
                if (i5 != aVar.itemCount) {
                    if (!this.mDisableRecycler) {
                        aVar.payload = null;
                        this.f624Tq.release(aVar);
                    }
                    aVar = obtainUpdateOp(2, i3, i5, (Object) null);
                }
                if (!z4) {
                    m813c(aVar);
                } else {
                    m814d(aVar);
                }
            } else if (i2 == 4) {
                int i7 = aVar.positionStart;
                int i8 = aVar.itemCount + i7;
                int i9 = i7;
                boolean z5 = true;
                int i10 = 0;
                while (i7 < i8) {
                    if (this.mCallback.findViewHolder(i7) != null || m815qb(i7)) {
                        if (!z5) {
                            m813c(obtainUpdateOp(4, i9, i10, aVar.payload));
                            i10 = 0;
                            i9 = i7;
                        }
                        z5 = true;
                    } else {
                        if (z5) {
                            m814d(obtainUpdateOp(4, i9, i10, aVar.payload));
                            i10 = 0;
                            i9 = i7;
                        }
                        z5 = false;
                    }
                    i10++;
                    i7++;
                }
                if (i10 != aVar.itemCount) {
                    Object obj = aVar.payload;
                    if (!this.mDisableRecycler) {
                        aVar.payload = null;
                        this.f624Tq.release(aVar);
                    }
                    aVar = obtainUpdateOp(4, i9, i10, obj);
                }
                if (!z5) {
                    m813c(aVar);
                } else {
                    m814d(aVar);
                }
            } else if (i2 == 8) {
                m814d(aVar);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    /* access modifiers changed from: package-private */
    public void recycleUpdateOpsAndClearList(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            mo4993a((C0553a) list.get(i));
        }
        list.clear();
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.f625Uq = 0;
    }

    /* renamed from: a */
    public void mo4993a(C0553a aVar) {
        if (!this.mDisableRecycler) {
            aVar.payload = null;
            this.f624Tq.release(aVar);
        }
    }
}
