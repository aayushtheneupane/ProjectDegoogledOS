package androidx.recyclerview.widget;

import p000a.p005b.C0015b;
import p000a.p005b.C0019f;

/* renamed from: androidx.recyclerview.widget.Ga */
class C0534Ga {
    final C0015b mLayoutHolderMap = new C0015b();
    final C0019f mOldChangedHolders = new C0019f();

    C0534Ga() {
    }

    /* renamed from: c */
    private C0548V m584c(C0586qa qaVar, int i) {
        C0532Fa fa;
        C0548V v;
        int indexOfKey = this.mLayoutHolderMap.indexOfKey(qaVar);
        if (indexOfKey >= 0 && (fa = (C0532Fa) this.mLayoutHolderMap.valueAt(indexOfKey)) != null) {
            int i2 = fa.flags;
            if ((i2 & i) != 0) {
                fa.flags = (~i) & i2;
                if (i == 4) {
                    v = fa.preInfo;
                } else if (i == 8) {
                    v = fa.postInfo;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((fa.flags & 12) == 0) {
                    this.mLayoutHolderMap.removeAt(indexOfKey);
                    C0532Fa.m575a(fa);
                }
                return v;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: A */
    public boolean mo4693A(C0586qa qaVar) {
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.get(qaVar);
        return (fa == null || (fa.flags & 4) == 0) ? false : true;
    }

    /* renamed from: B */
    public void mo4694B(C0586qa qaVar) {
        mo4697E(qaVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: C */
    public C0548V mo4695C(C0586qa qaVar) {
        return m584c(qaVar, 8);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: D */
    public C0548V mo4696D(C0586qa qaVar) {
        return m584c(qaVar, 4);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: E */
    public void mo4697E(C0586qa qaVar) {
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.get(qaVar);
        if (fa != null) {
            fa.flags &= -2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: F */
    public void mo4698F(C0586qa qaVar) {
        int size = this.mOldChangedHolders.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (qaVar == this.mOldChangedHolders.valueAt(size)) {
                this.mOldChangedHolders.removeAt(size);
                break;
            } else {
                size--;
            }
        }
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.remove(qaVar);
        if (fa != null) {
            C0532Fa.m575a(fa);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4699a(long j, C0586qa qaVar) {
        this.mOldChangedHolders.put(j, qaVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4701b(C0586qa qaVar, C0548V v) {
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.get(qaVar);
        if (fa == null) {
            fa = C0532Fa.obtain();
            this.mLayoutHolderMap.put(qaVar, fa);
        }
        fa.flags |= 2;
        fa.preInfo = v;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.mLayoutHolderMap.clear();
        this.mOldChangedHolders.clear();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo4704d(C0586qa qaVar, C0548V v) {
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.get(qaVar);
        if (fa == null) {
            fa = C0532Fa.obtain();
            this.mLayoutHolderMap.put(qaVar, fa);
        }
        fa.preInfo = v;
        fa.flags |= 4;
    }

    /* access modifiers changed from: package-private */
    public C0586qa getFromOldChangeHolders(long j) {
        return (C0586qa) this.mOldChangedHolders.get(j, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public void onDetach() {
        do {
        } while (C0532Fa.sPool.acquire() != null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: y */
    public void mo4707y(C0586qa qaVar) {
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.get(qaVar);
        if (fa == null) {
            fa = C0532Fa.obtain();
            this.mLayoutHolderMap.put(qaVar, fa);
        }
        fa.flags |= 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: z */
    public boolean mo4708z(C0586qa qaVar) {
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.get(qaVar);
        if (fa == null || (fa.flags & 1) == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4700a(C0540M m) {
        int size = this.mLayoutHolderMap.size();
        while (true) {
            size--;
            if (size >= 0) {
                C0586qa qaVar = (C0586qa) this.mLayoutHolderMap.keyAt(size);
                C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.removeAt(size);
                int i = fa.flags;
                if ((i & 3) == 3) {
                    RecyclerView recyclerView = m.this$0;
                    recyclerView.mLayout.mo5015a(qaVar.itemView, recyclerView.mRecycler);
                } else if ((i & 1) != 0) {
                    C0548V v = fa.preInfo;
                    if (v == null) {
                        RecyclerView recyclerView2 = m.this$0;
                        recyclerView2.mLayout.mo5015a(qaVar.itemView, recyclerView2.mRecycler);
                    } else {
                        C0548V v2 = fa.postInfo;
                        m.this$0.mRecycler.mo5147m(qaVar);
                        m.this$0.mo4837b(qaVar, v, v2);
                    }
                } else if ((i & 14) == 14) {
                    m.this$0.mo4827a(qaVar, fa.preInfo, fa.postInfo);
                } else if ((i & 12) == 12) {
                    m.mo4785c(qaVar, fa.preInfo, fa.postInfo);
                } else if ((i & 4) != 0) {
                    C0548V v3 = fa.preInfo;
                    m.this$0.mRecycler.mo5147m(qaVar);
                    m.this$0.mo4837b(qaVar, v3, (C0548V) null);
                } else if ((i & 8) != 0) {
                    m.this$0.mo4827a(qaVar, fa.preInfo, fa.postInfo);
                }
                C0532Fa.m575a(fa);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo4702c(C0586qa qaVar, C0548V v) {
        C0532Fa fa = (C0532Fa) this.mLayoutHolderMap.get(qaVar);
        if (fa == null) {
            fa = C0532Fa.obtain();
            this.mLayoutHolderMap.put(qaVar, fa);
        }
        fa.postInfo = v;
        fa.flags |= 8;
    }
}
