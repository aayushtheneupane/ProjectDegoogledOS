package com.android.p032ex.chips;

import android.database.Cursor;
import android.text.TextUtils;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.android.ex.chips.f */
public class C0674f extends Filter {

    /* renamed from: Al */
    private int f792Al;
    private final C0678h mParams;
    final /* synthetic */ C0684k this$0;

    public C0674f(C0684k kVar, C0678h hVar) {
        this.this$0 = kVar;
        this.mParams = hVar;
    }

    public synchronized int getLimit() {
        return this.f792Al;
    }

    /* access modifiers changed from: protected */
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        Filter.FilterResults filterResults = new Filter.FilterResults();
        Cursor cursor = null;
        filterResults.values = cursor;
        filterResults.count = 0;
        if (!TextUtils.isEmpty(charSequence)) {
            ArrayList arrayList = new ArrayList();
            try {
                cursor = this.this$0.doQuery(charSequence, getLimit(), Long.valueOf(this.mParams.f794Xu));
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        arrayList.add(new C0682j(cursor, Long.valueOf(this.mParams.f794Xu)));
                    }
                }
                if (!arrayList.isEmpty()) {
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return filterResults;
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        this.this$0.mDelayedMessageHandler.removeMessages(1);
        if (TextUtils.equals(charSequence, this.this$0.mCurrentConstraint)) {
            if (filterResults.count > 0) {
                Iterator it = ((ArrayList) filterResults.values).iterator();
                while (it.hasNext()) {
                    this.this$0.putOneEntry((C0682j) it.next(), this.mParams.f794Xu == 0);
                }
            }
            C0684k.access$1010(this.this$0);
            if (this.this$0.mRemainingDirectoryCount > 0) {
                C0672e access$900 = this.this$0.mDelayedMessageHandler;
                access$900.sendMessageDelayed(access$900.obtainMessage(1, 0, 0, (Object) null), 1000);
            }
            if (filterResults.count > 0 || this.this$0.mRemainingDirectoryCount == 0) {
                this.this$0.clearTempEntries();
            }
        }
        C0684k kVar = this.this$0;
        kVar.updateEntries(kVar.constructEntryList());
    }

    public synchronized void setLimit(int i) {
        this.f792Al = i;
    }
}
