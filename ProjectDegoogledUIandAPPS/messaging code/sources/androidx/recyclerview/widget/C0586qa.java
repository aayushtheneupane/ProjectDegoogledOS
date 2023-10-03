package androidx.recyclerview.widget;

import android.util.Log;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.qa */
public abstract class C0586qa {

    /* renamed from: et */
    private static final List f664et = Collections.emptyList();

    /* renamed from: at */
    private int f665at = 0;

    /* renamed from: bt */
    C0572ja f666bt = null;

    /* renamed from: ct */
    boolean f667ct = false;

    /* renamed from: dt */
    private int f668dt = 0;
    public final View itemView;
    int mFlags;
    long mItemId = -1;
    int mItemViewType = -1;
    WeakReference mNestedRecyclerView;
    int mOldPosition = -1;
    RecyclerView mOwnerRecyclerView;
    List mPayloads = null;
    int mPendingAccessibilityState = -1;
    int mPosition = -1;
    int mPreLayoutPosition = -1;
    C0586qa mShadowedHolder = null;
    C0586qa mShadowingHolder = null;
    List mUnmodifiedPayloads = null;

    public C0586qa(View view) {
        if (view != null) {
            this.itemView = view;
            return;
        }
        throw new IllegalArgumentException("itemView may not be null");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Wc */
    public boolean mo5190Wc() {
        return (this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Xc */
    public boolean mo5191Xc() {
        return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Yc */
    public boolean mo5192Yc() {
        return (this.mFlags & 16) != 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5193a(C0572ja jaVar, boolean z) {
        this.f666bt = jaVar;
        this.f667ct = z;
    }

    /* access modifiers changed from: package-private */
    public void addChangePayload(Object obj) {
        if (obj == null) {
            addFlags(1024);
        } else if ((1024 & this.mFlags) == 0) {
            if (this.mPayloads == null) {
                this.mPayloads = new ArrayList();
                this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
            }
            this.mPayloads.add(obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void addFlags(int i) {
        this.mFlags = i | this.mFlags;
    }

    /* access modifiers changed from: package-private */
    public void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }

    /* access modifiers changed from: package-private */
    public void clearPayload() {
        List list = this.mPayloads;
        if (list != null) {
            list.clear();
        }
        this.mFlags &= -1025;
    }

    /* access modifiers changed from: package-private */
    public void clearReturnedFromScrapFlag() {
        this.mFlags &= -33;
    }

    /* access modifiers changed from: package-private */
    public void clearTmpDetachFlag() {
        this.mFlags &= -257;
    }

    /* access modifiers changed from: package-private */
    public void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
        addFlags(8);
        offsetPosition(i2, z);
        this.mPosition = i;
    }

    public final int getAdapterPosition() {
        RecyclerView recyclerView = this.mOwnerRecyclerView;
        if (recyclerView == null) {
            return -1;
        }
        return recyclerView.mo4838c(this);
    }

    public final long getItemId() {
        return this.mItemId;
    }

    public final int getItemViewType() {
        return this.mItemViewType;
    }

    public final int getLayoutPosition() {
        int i = this.mPreLayoutPosition;
        return i == -1 ? this.mPosition : i;
    }

    public final int getOldPosition() {
        return this.mOldPosition;
    }

    /* access modifiers changed from: package-private */
    public List getUnmodifiedPayloads() {
        if ((this.mFlags & 1024) != 0) {
            return f664et;
        }
        List list = this.mPayloads;
        if (list == null || list.size() == 0) {
            return f664et;
        }
        return this.mUnmodifiedPayloads;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnyOfTheFlags(int i) {
        return (this.mFlags & i) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isAdapterPositionUnknown() {
        return (this.mFlags & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 || isInvalid();
    }

    /* access modifiers changed from: package-private */
    public boolean isBound() {
        return (this.mFlags & 1) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isInvalid() {
        return (this.mFlags & 4) != 0;
    }

    public final boolean isRecyclable() {
        return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
    }

    /* access modifiers changed from: package-private */
    public boolean isRemoved() {
        return (this.mFlags & 8) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isScrap() {
        return this.f666bt != null;
    }

    /* access modifiers changed from: package-private */
    public boolean isTmpDetached() {
        return (this.mFlags & 256) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isUpdated() {
        return (this.mFlags & 2) != 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public void mo5216k(RecyclerView recyclerView) {
        int i = this.mPendingAccessibilityState;
        if (i != -1) {
            this.f668dt = i;
        } else {
            this.f668dt = ViewCompat.getImportantForAccessibility(this.itemView);
        }
        recyclerView.setChildImportantForAccessibilityInternal(this, 4);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public void mo5217l(RecyclerView recyclerView) {
        recyclerView.setChildImportantForAccessibilityInternal(this, this.f668dt);
        this.f668dt = 0;
    }

    /* access modifiers changed from: package-private */
    public boolean needsUpdate() {
        return (this.mFlags & 2) != 0;
    }

    /* access modifiers changed from: package-private */
    public void offsetPosition(int i, boolean z) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (z) {
            this.mPreLayoutPosition += i;
        }
        this.mPosition += i;
        if (this.itemView.getLayoutParams() != null) {
            ((C0560da) this.itemView.getLayoutParams()).mInsetsDirty = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1;
        this.mPreLayoutPosition = -1;
        this.f665at = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        clearPayload();
        this.f668dt = 0;
        this.mPendingAccessibilityState = -1;
        RecyclerView.m695b(this);
    }

    /* access modifiers changed from: package-private */
    public void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }

    /* access modifiers changed from: package-private */
    public void setFlags(int i, int i2) {
        this.mFlags = (i & i2) | (this.mFlags & (~i2));
    }

    public final void setIsRecyclable(boolean z) {
        int i = this.f665at;
        this.f665at = z ? i - 1 : i + 1;
        int i2 = this.f665at;
        if (i2 < 0) {
            this.f665at = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
        } else if (!z && i2 == 1) {
            this.mFlags |= 16;
        } else if (z && this.f665at == 0) {
            this.mFlags &= -17;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldIgnore() {
        return (this.mFlags & 128) != 0;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("ViewHolder{");
        Pa.append(Integer.toHexString(hashCode()));
        Pa.append(" position=");
        Pa.append(this.mPosition);
        Pa.append(" id=");
        Pa.append(this.mItemId);
        Pa.append(", oldPos=");
        Pa.append(this.mOldPosition);
        Pa.append(", pLpos:");
        Pa.append(this.mPreLayoutPosition);
        StringBuilder sb = new StringBuilder(Pa.toString());
        if (isScrap()) {
            sb.append(" scrap ");
            sb.append(this.f667ct ? "[changeScrap]" : "[attachedScrap]");
        }
        if (isInvalid()) {
            sb.append(" invalid");
        }
        if (!isBound()) {
            sb.append(" unbound");
        }
        if (needsUpdate()) {
            sb.append(" update");
        }
        if (isRemoved()) {
            sb.append(" removed");
        }
        if (shouldIgnore()) {
            sb.append(" ignored");
        }
        if (isTmpDetached()) {
            sb.append(" tmpDetached");
        }
        if (!isRecyclable()) {
            StringBuilder Pa2 = C0632a.m1011Pa(" not recyclable(");
            Pa2.append(this.f665at);
            Pa2.append(")");
            sb.append(Pa2.toString());
        }
        if (isAdapterPositionUnknown()) {
            sb.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void unScrap() {
        this.f666bt.mo5147m(this);
    }

    /* access modifiers changed from: package-private */
    public boolean wasReturnedFromScrap() {
        return (this.mFlags & 32) != 0;
    }
}
