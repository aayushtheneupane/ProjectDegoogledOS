package android.support.p002v7.widget;

import android.support.p000v4.util.Pools$Pool;
import android.support.p000v4.util.Pools$SimplePool;
import android.support.p002v7.widget.OpReorderer;
import android.support.p002v7.widget.RecyclerView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.AdapterHelper */
class AdapterHelper implements OpReorderer.Callback {
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes = 0;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates = new ArrayList<>();
    final ArrayList<UpdateOp> mPostponedList = new ArrayList<>();
    private Pools$Pool<UpdateOp> mUpdateOpPool = new Pools$SimplePool(30);

    /* renamed from: android.support.v7.widget.AdapterHelper$Callback */
    interface Callback {
    }

    /* renamed from: android.support.v7.widget.AdapterHelper$UpdateOp */
    static class UpdateOp {
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(int i, int i2, int i3, Object obj) {
            this.cmd = i;
            this.positionStart = i2;
            this.itemCount = i3;
            this.payload = obj;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || UpdateOp.class != obj.getClass()) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            int i = this.cmd;
            if (i != updateOp.cmd) {
                return false;
            }
            if (i == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                return true;
            }
            if (this.itemCount != updateOp.itemCount || this.positionStart != updateOp.positionStart) {
                return false;
            }
            Object obj2 = this.payload;
            if (obj2 != null) {
                if (!obj2.equals(updateOp.payload)) {
                    return false;
                }
            } else if (updateOp.payload != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append("[");
            int i = this.cmd;
            sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
            sb.append(",s:");
            sb.append(this.positionStart);
            sb.append("c:");
            sb.append(this.itemCount);
            sb.append(",p:");
            return GeneratedOutlineSupport.outline11(sb, this.payload, "]");
        }
    }

    AdapterHelper(Callback callback) {
        this.mCallback = callback;
        this.mDisableRecycler = false;
        this.mOpReorderer = new OpReorderer(this);
    }

    private boolean canFindInPreLayout(int i) {
        int size = this.mPostponedList.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = this.mPostponedList.get(i2);
            int i3 = updateOp.cmd;
            if (i3 == 8) {
                if (findPositionOffset(updateOp.itemCount, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = updateOp.positionStart;
                int i5 = updateOp.itemCount + i4;
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

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int i;
        int i2 = updateOp.cmd;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int updatePositionWithPostponed = updatePositionWithPostponed(updateOp.positionStart, i2);
        int i3 = updateOp.positionStart;
        int i4 = updateOp.cmd;
        if (i4 == 2) {
            i = 0;
        } else if (i4 == 4) {
            i = 1;
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("op should be remove or update.", updateOp));
        }
        int i5 = updatePositionWithPostponed;
        int i6 = i3;
        int i7 = 1;
        for (int i8 = 1; i8 < updateOp.itemCount; i8++) {
            int updatePositionWithPostponed2 = updatePositionWithPostponed((i * i8) + updateOp.positionStart, updateOp.cmd);
            int i9 = updateOp.cmd;
            if (i9 == 2 ? updatePositionWithPostponed2 == i5 : i9 == 4 && updatePositionWithPostponed2 == i5 + 1) {
                i7++;
            } else {
                UpdateOp obtainUpdateOp = obtainUpdateOp(updateOp.cmd, i5, i7, updateOp.payload);
                dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, i6);
                if (!this.mDisableRecycler) {
                    obtainUpdateOp.payload = null;
                    this.mUpdateOpPool.release(obtainUpdateOp);
                }
                if (updateOp.cmd == 4) {
                    i6 += i7;
                }
                i7 = 1;
                i5 = updatePositionWithPostponed2;
            }
        }
        Object obj = updateOp.payload;
        if (!this.mDisableRecycler) {
            updateOp.payload = null;
            this.mUpdateOpPool.release(updateOp);
        }
        if (i7 > 0) {
            UpdateOp obtainUpdateOp2 = obtainUpdateOp(updateOp.cmd, i5, i7, obj);
            dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, i6);
            if (!this.mDisableRecycler) {
                obtainUpdateOp2.payload = null;
                this.mUpdateOpPool.release(obtainUpdateOp2);
            }
        }
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        int i = updateOp.cmd;
        if (i == 1) {
            Callback callback = this.mCallback;
            RecyclerView.C02206 r4 = (RecyclerView.C02206) callback;
            RecyclerView.this.offsetPositionRecordsForInsert(updateOp.positionStart, updateOp.itemCount);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        } else if (i == 2) {
            Callback callback2 = this.mCallback;
            RecyclerView.C02206 r42 = (RecyclerView.C02206) callback2;
            RecyclerView.this.offsetPositionRecordsForRemove(updateOp.positionStart, updateOp.itemCount, false);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        } else if (i == 4) {
            ((RecyclerView.C02206) this.mCallback).markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
        } else if (i == 8) {
            Callback callback3 = this.mCallback;
            RecyclerView.C02206 r43 = (RecyclerView.C02206) callback3;
            RecyclerView.this.offsetPositionRecordsForMove(updateOp.positionStart, updateOp.itemCount);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown update op type for ", updateOp));
        }
    }

    private int updatePositionWithPostponed(int i, int i2) {
        for (int size = this.mPostponedList.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = this.mPostponedList.get(size);
            int i3 = updateOp.cmd;
            if (i3 == 8) {
                int i4 = updateOp.positionStart;
                int i5 = updateOp.itemCount;
                if (i4 >= i5) {
                    int i6 = i5;
                    i5 = i4;
                    i4 = i6;
                }
                if (i < i4 || i > i5) {
                    int i7 = updateOp.positionStart;
                    if (i < i7) {
                        if (i2 == 1) {
                            updateOp.positionStart = i7 + 1;
                            updateOp.itemCount++;
                        } else if (i2 == 2) {
                            updateOp.positionStart = i7 - 1;
                            updateOp.itemCount--;
                        }
                    }
                } else {
                    int i8 = updateOp.positionStart;
                    if (i4 == i8) {
                        if (i2 == 1) {
                            updateOp.itemCount++;
                        } else if (i2 == 2) {
                            updateOp.itemCount--;
                        }
                        i++;
                    } else {
                        if (i2 == 1) {
                            updateOp.positionStart = i8 + 1;
                        } else if (i2 == 2) {
                            updateOp.positionStart = i8 - 1;
                        }
                        i--;
                    }
                }
            } else {
                int i9 = updateOp.positionStart;
                if (i9 <= i) {
                    if (i3 == 1) {
                        i -= updateOp.itemCount;
                    } else if (i3 == 2) {
                        i += updateOp.itemCount;
                    }
                } else if (i2 == 1) {
                    updateOp.positionStart = i9 + 1;
                } else if (i2 == 2) {
                    updateOp.positionStart = i9 - 1;
                }
            }
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = this.mPostponedList.get(size2);
            if (updateOp2.cmd == 8) {
                int i10 = updateOp2.itemCount;
                if (i10 == updateOp2.positionStart || i10 < 0) {
                    this.mPostponedList.remove(size2);
                    if (!this.mDisableRecycler) {
                        updateOp2.payload = null;
                        this.mUpdateOpPool.release(updateOp2);
                    }
                }
            } else if (updateOp2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                if (!this.mDisableRecycler) {
                    updateOp2.payload = null;
                    this.mUpdateOpPool.release(updateOp2);
                }
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int i = 0; i < size; i++) {
            ((RecyclerView.C02206) this.mCallback).dispatchUpdate(this.mPostponedList.get(i));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    /* access modifiers changed from: package-private */
    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i);
            int i2 = updateOp.cmd;
            if (i2 == 1) {
                ((RecyclerView.C02206) this.mCallback).dispatchUpdate(updateOp);
                RecyclerView.C02206 r4 = (RecyclerView.C02206) this.mCallback;
                RecyclerView.this.offsetPositionRecordsForInsert(updateOp.positionStart, updateOp.itemCount);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            } else if (i2 == 2) {
                ((RecyclerView.C02206) this.mCallback).dispatchUpdate(updateOp);
                Callback callback = this.mCallback;
                int i3 = updateOp.positionStart;
                int i4 = updateOp.itemCount;
                RecyclerView.C02206 r42 = (RecyclerView.C02206) callback;
                RecyclerView.this.offsetPositionRecordsForRemove(i3, i4, true);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mItemsAddedOrRemoved = true;
                recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i4;
            } else if (i2 == 4) {
                ((RecyclerView.C02206) this.mCallback).dispatchUpdate(updateOp);
                ((RecyclerView.C02206) this.mCallback).markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            } else if (i2 == 8) {
                ((RecyclerView.C02206) this.mCallback).dispatchUpdate(updateOp);
                RecyclerView.C02206 r43 = (RecyclerView.C02206) this.mCallback;
                RecyclerView.this.offsetPositionRecordsForMove(updateOp.positionStart, updateOp.itemCount);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    /* access modifiers changed from: package-private */
    public void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i) {
        ((RecyclerView.C02206) this.mCallback).dispatchUpdate(updateOp);
        int i2 = updateOp.cmd;
        if (i2 == 2) {
            Callback callback = this.mCallback;
            int i3 = updateOp.itemCount;
            RecyclerView.C02206 r3 = (RecyclerView.C02206) callback;
            RecyclerView.this.offsetPositionRecordsForRemove(i, i3, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mItemsAddedOrRemoved = true;
            recyclerView.mState.mDeletedInvisibleItemCountSincePreviousLayout += i3;
        } else if (i2 == 4) {
            RecyclerView.C02206 r32 = (RecyclerView.C02206) this.mCallback;
            RecyclerView.this.viewRangeUpdate(i, updateOp.itemCount, updateOp.payload);
            RecyclerView.this.mItemsChanged = true;
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    /* access modifiers changed from: package-private */
    public int findPositionOffset(int i, int i2) {
        int size = this.mPostponedList.size();
        while (i2 < size) {
            UpdateOp updateOp = this.mPostponedList.get(i2);
            int i3 = updateOp.cmd;
            if (i3 == 8) {
                int i4 = updateOp.positionStart;
                if (i4 == i) {
                    i = updateOp.itemCount;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (updateOp.itemCount <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = updateOp.positionStart;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = updateOp.itemCount;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += updateOp.itemCount;
                }
            }
            i2++;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnyUpdateTypes(int i) {
        return (this.mExistingUpdateTypes & i) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    public UpdateOp obtainUpdateOp(int i, int i2, int i3, Object obj) {
        UpdateOp acquire = this.mUpdateOpPool.acquire();
        if (acquire == null) {
            return new UpdateOp(i, i2, i3, obj);
        }
        acquire.cmd = i;
        acquire.positionStart = i2;
        acquire.itemCount = i3;
        acquire.payload = obj;
        return acquire;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeChanged(int i, int i2, Object obj) {
        if (i2 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(4, i, i2, obj));
        this.mExistingUpdateTypes |= 4;
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeInserted(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(1, i, i2, (Object) null));
        this.mExistingUpdateTypes |= 1;
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeMoved(int i, int i2, int i3) {
        if (i == i2) {
            return false;
        }
        if (i3 == 1) {
            this.mPendingUpdates.add(obtainUpdateOp(8, i, i2, (Object) null));
            this.mExistingUpdateTypes |= 8;
            if (this.mPendingUpdates.size() == 1) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    /* access modifiers changed from: package-private */
    public boolean onItemRangeRemoved(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(2, i, i2, (Object) null));
        this.mExistingUpdateTypes |= 2;
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void preProcess() {
        boolean z;
        boolean z2;
        boolean z3;
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int size = this.mPendingUpdates.size();
        for (int i = 0; i < size; i++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i);
            int i2 = updateOp.cmd;
            if (i2 == 1) {
                postponeAndUpdateViewHolders(updateOp);
            } else if (i2 == 2) {
                int i3 = updateOp.positionStart;
                int i4 = updateOp.itemCount + i3;
                int i5 = 0;
                boolean z4 = true;
                int i6 = i3;
                while (i6 < i4) {
                    if (((RecyclerView.C02206) this.mCallback).findViewHolder(i6) != null || canFindInPreLayout(i6)) {
                        if (!z4) {
                            dispatchAndUpdateViewHolders(obtainUpdateOp(2, i3, i5, (Object) null));
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        z = true;
                    } else {
                        if (z4) {
                            postponeAndUpdateViewHolders(obtainUpdateOp(2, i3, i5, (Object) null));
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
                if (i5 != updateOp.itemCount) {
                    if (!this.mDisableRecycler) {
                        updateOp.payload = null;
                        this.mUpdateOpPool.release(updateOp);
                    }
                    updateOp = obtainUpdateOp(2, i3, i5, (Object) null);
                }
                if (!z4) {
                    dispatchAndUpdateViewHolders(updateOp);
                } else {
                    postponeAndUpdateViewHolders(updateOp);
                }
            } else if (i2 == 4) {
                int i7 = updateOp.positionStart;
                int i8 = updateOp.itemCount + i7;
                int i9 = i7;
                boolean z5 = true;
                int i10 = 0;
                while (i7 < i8) {
                    if (((RecyclerView.C02206) this.mCallback).findViewHolder(i7) != null || canFindInPreLayout(i7)) {
                        if (!z5) {
                            dispatchAndUpdateViewHolders(obtainUpdateOp(4, i9, i10, updateOp.payload));
                            i10 = 0;
                            i9 = i7;
                        }
                        z5 = true;
                    } else {
                        if (z5) {
                            postponeAndUpdateViewHolders(obtainUpdateOp(4, i9, i10, updateOp.payload));
                            i10 = 0;
                            i9 = i7;
                        }
                        z5 = false;
                    }
                    i10++;
                    i7++;
                }
                if (i10 != updateOp.itemCount) {
                    Object obj = updateOp.payload;
                    if (!this.mDisableRecycler) {
                        updateOp.payload = null;
                        this.mUpdateOpPool.release(updateOp);
                    }
                    updateOp = obtainUpdateOp(4, i9, i10, obj);
                }
                if (!z5) {
                    dispatchAndUpdateViewHolders(updateOp);
                } else {
                    postponeAndUpdateViewHolders(updateOp);
                }
            } else if (i2 == 8) {
                postponeAndUpdateViewHolders(updateOp);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    public void recycleUpdateOp(UpdateOp updateOp) {
        if (!this.mDisableRecycler) {
            updateOp.payload = null;
            this.mUpdateOpPool.release(updateOp);
        }
    }

    /* access modifiers changed from: package-private */
    public void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            recycleUpdateOp(list.get(i));
        }
        list.clear();
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }
}
