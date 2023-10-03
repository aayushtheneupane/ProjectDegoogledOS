package com.android.messaging.p041ui.contact;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.p041ui.contact.ContactListItemView;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.ContactUtil;
import com.android.p032ex.chips.C0645M;
import com.android.p032ex.chips.C0684k;
import com.android.p032ex.chips.C0699ra;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.android.messaging.ui.contact.ContactRecipientAdapter */
public final class ContactRecipientAdapter extends C0684k {
    private static final int ENTRY_TYPE_DIRECTORY = 2;
    private static final int WORD_DIRECTORY_HEADER_POS_NONE = -1;
    private final LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public int mWorkDirectoryHeaderPos;

    /* renamed from: com.android.messaging.ui.contact.ContactRecipientAdapter$ContactFilter */
    public class ContactFilter extends Filter {
        private final RecipientEntryComparator mComparator = new RecipientEntryComparator();

        /* renamed from: com.android.messaging.ui.contact.ContactRecipientAdapter$ContactFilter$ContactReceipientFilterResult */
        class ContactReceipientFilterResult {
            public final List recipientEntries;
            public final int workDirectoryPos;

            public ContactReceipientFilterResult(List list, int i) {
                this.recipientEntries = list;
                this.workDirectoryPos = i;
            }
        }

        /* renamed from: com.android.messaging.ui.contact.ContactRecipientAdapter$ContactFilter$CursorResult */
        class CursorResult {
            public Cursor enterpriseCursor;
            public final boolean isSorted;
            public final Cursor personalCursor;

            public CursorResult(Cursor cursor, boolean z) {
                this.personalCursor = cursor;
                this.isSorted = z;
            }
        }

        /* renamed from: com.android.messaging.ui.contact.ContactRecipientAdapter$ContactFilter$RecipientEntryComparator */
        class RecipientEntryComparator implements Comparator {
            private final Collator mCollator = Collator.getInstance(Locale.getDefault());

            public RecipientEntryComparator() {
                this.mCollator.setStrength(0);
            }

            public int compare(C0699ra raVar, C0699ra raVar2) {
                boolean e = C1430e.m3624e(raVar);
                boolean e2 = C1430e.m3624e(raVar);
                if (e != e2) {
                    if (e) {
                        return -1;
                    }
                    if (e2) {
                        return 1;
                    }
                }
                int compare = this.mCollator.compare(raVar.getDisplayName(), raVar2.getDisplayName());
                if (compare != 0) {
                    return compare;
                }
                int i = (raVar.getContactId() > raVar2.getContactId() ? 1 : (raVar.getContactId() == raVar2.getContactId() ? 0 : -1));
                int i2 = i < 0 ? -1 : i == 0 ? 0 : 1;
                if (i2 != 0) {
                    return i2;
                }
                if (raVar.mo5667zd()) {
                    return -1;
                }
                if (raVar2.mo5667zd()) {
                    return 1;
                }
                return 0;
            }
        }

        public ContactFilter() {
        }

        private CursorResult getFilteredResultsCursor(String str) {
            C1424b.m3584Gj();
            C1449g.get().getBoolean("bugle_always_autocomplete_email_address", false);
            CursorResult cursorResult = new CursorResult(ContactUtil.m3533j(ContactRecipientAdapter.this.getContext(), str).mo6584Xd(), true);
            if (C1464na.isAtLeastN()) {
                cursorResult.enterpriseCursor = ContactUtil.m3534k(ContactRecipientAdapter.this.getContext(), str).mo6584Xd();
            }
            return cursorResult;
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            C1424b.m3584Gj();
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (TextUtils.isEmpty(charSequence)) {
                ContactRecipientAdapter.this.clearTempEntries();
                return filterResults;
            }
            String charSequence2 = charSequence.toString();
            CursorResult filteredResultsCursor = getFilteredResultsCursor(charSequence2);
            ArrayList arrayList = new ArrayList();
            if (C1474sa.m3792La(charSequence2)) {
                arrayList.add(C1430e.m3632wa(charSequence2));
            }
            int i = -1;
            Cursor cursor = filteredResultsCursor.enterpriseCursor;
            if (!(cursor == null || cursor.getCount() <= 0 || filteredResultsCursor.personalCursor == null)) {
                i = arrayList.size() + filteredResultsCursor.personalCursor.getCount();
            }
            Cursor[] cursorArr = {filteredResultsCursor.personalCursor, filteredResultsCursor.enterpriseCursor};
            int length = cursorArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Cursor cursor2 = cursorArr[i2];
                if (cursor2 != null) {
                    try {
                        ArrayList arrayList2 = new ArrayList();
                        HashSet hashSet = new HashSet();
                        while (cursor2.moveToNext()) {
                            long j = cursor2.getLong(0);
                            boolean z = !hashSet.contains(Long.valueOf(j));
                            if (z) {
                                hashSet.add(Long.valueOf(j));
                            }
                            arrayList2.add(ContactUtil.m3532c(cursor2, z));
                        }
                        if (!filteredResultsCursor.isSorted) {
                            Collections.sort(arrayList2, this.mComparator);
                        }
                        arrayList.addAll(arrayList2);
                    } finally {
                        cursor2.close();
                    }
                }
            }
            filterResults.values = new ContactReceipientFilterResult(arrayList, i);
            filterResults.count = 1;
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            CharSequence unused = ContactRecipientAdapter.this.mCurrentConstraint = charSequence;
            ContactRecipientAdapter.this.clearTempEntries();
            ContactReceipientFilterResult contactReceipientFilterResult = (ContactReceipientFilterResult) filterResults.values;
            if (contactReceipientFilterResult != null) {
                int unused2 = ContactRecipientAdapter.this.mWorkDirectoryHeaderPos = contactReceipientFilterResult.workDirectoryPos;
                List list = contactReceipientFilterResult.recipientEntries;
                if (list != null) {
                    ContactRecipientAdapter.this.updateEntries(list);
                } else {
                    ContactRecipientAdapter.this.updateEntries(Collections.emptyList());
                }
            }
        }
    }

    public ContactRecipientAdapter(Context context, ContactListItemView.HostInterface hostInterface) {
        this(context, Integer.MAX_VALUE, 1, hostInterface);
    }

    private int fixPosition(int i) {
        if (!hasWorkDirectoryHeader()) {
            return i;
        }
        C1424b.m3592ia(i != this.mWorkDirectoryHeaderPos);
        return i > this.mWorkDirectoryHeaderPos ? i - 1 : i;
    }

    private boolean hasWorkDirectoryHeader() {
        return this.mWorkDirectoryHeaderPos != -1;
    }

    private boolean isDirectoryEntry(int i) {
        return i == this.mWorkDirectoryHeaderPos;
    }

    public boolean forceShowAddress() {
        return true;
    }

    public int getCount() {
        return super.getCount() + (hasWorkDirectoryHeader() ? 1 : 0);
    }

    public Filter getFilter() {
        return new ContactFilter();
    }

    public int getItemViewType(int i) {
        if (i == this.mWorkDirectoryHeaderPos) {
            return 2;
        }
        return super.getItemViewType(fixPosition(i));
    }

    public void getMatchingRecipients(ArrayList arrayList, C0645M m) {
        int min = Math.min(50, arrayList.size());
        HashSet hashSet = new HashSet();
        for (int i = 0; i < min; i++) {
            Rfc822Token[] rfc822TokenArr = Rfc822Tokenizer.tokenize(((String) arrayList.get(i)).toLowerCase());
            hashSet.add(rfc822TokenArr.length > 0 ? rfc822TokenArr[0].getAddress() : (String) arrayList.get(i));
        }
        HashMap hashMap = new HashMap();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Cursor Xd = ContactUtil.m3537n(getContext(), str).mo6584Xd();
            if (Xd != null) {
                try {
                    if (Xd.moveToNext()) {
                        hashMap.put(str, ContactUtil.m3532c(Xd, true));
                    }
                } finally {
                    Xd.close();
                }
            }
        }
        m.mo5444a((Map) hashMap);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!(i == this.mWorkDirectoryHeaderPos)) {
            return super.getView(fixPosition(i), view, viewGroup);
        }
        if (view == null) {
            return (TextView) this.mInflater.inflate(R.layout.work_directory_header, viewGroup, false);
        }
        return (TextView) view;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public boolean isEnabled(int i) {
        if (i == this.mWorkDirectoryHeaderPos) {
            return false;
        }
        return super.isEnabled(fixPosition(i));
    }

    public ContactRecipientAdapter(Context context, int i, int i2, ContactListItemView.HostInterface hostInterface) {
        super(context, i, i2);
        this.mWorkDirectoryHeaderPos = -1;
        setPhotoManager(new ContactRecipientPhotoManager(context, hostInterface));
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public C0699ra getItem(int i) {
        if (i == this.mWorkDirectoryHeaderPos) {
            return null;
        }
        return super.getItem(fixPosition(i));
    }
}
