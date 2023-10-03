package com.android.messaging.p041ui.contact;

import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Rfc822Tokenizer;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.util.C1474sa;
import com.android.p032ex.chips.C0697qa;
import com.android.p032ex.chips.C0699ra;
import com.android.p032ex.chips.p033a.C0660b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* renamed from: com.android.messaging.ui.contact.ContactRecipientAutoCompleteView */
public class ContactRecipientAutoCompleteView extends C0697qa {
    private static final Executor SANITIZE_EXECUTOR = Executors.newSingleThreadExecutor();
    private static final String TEXT_HEIGHT_SAMPLE = "a";
    /* access modifiers changed from: private */
    public ContactChipsChangeListener mChipsChangeListener;
    /* access modifiers changed from: private */
    public AsyncContactChipSanitizeTask mCurrentSanitizeTask;
    private final int mTextHeight;

    /* renamed from: com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$AsyncContactChipSanitizeTask */
    class AsyncContactChipSanitizeTask extends AsyncTask {
        private AsyncContactChipSanitizeTask() {
        }

        /* synthetic */ AsyncContactChipSanitizeTask(C11261 r2) {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
            r12 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a5, code lost:
            if (r6 != null) goto L_0x00a7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            r6.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ab, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ac, code lost:
            r11.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00af, code lost:
            throw r12;
         */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x009e  */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x00c0 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Integer doInBackground(java.lang.Void... r12) {
            /*
                r11 = this;
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView r12 = com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.this
                android.text.Editable r12 = r12.getText()
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView r0 = com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.this
                android.text.Editable r0 = r0.getText()
                int r0 = r0.length()
                java.lang.Class<com.android.ex.chips.a.b> r1 = com.android.p032ex.chips.p033a.C0660b.class
                r2 = 0
                java.lang.Object[] r12 = r12.getSpans(r2, r0, r1)
                com.android.ex.chips.a.b[] r12 = (com.android.p032ex.chips.p033a.C0660b[]) r12
                int r0 = r12.length
                r1 = r2
                r3 = r1
            L_0x001c:
                if (r1 >= r0) goto L_0x00c4
                r4 = r12[r1]
                com.android.ex.chips.ra r5 = r4.getEntry()
                if (r5 == 0) goto L_0x00c0
                boolean r6 = r5.isValid()
                r7 = 0
                r8 = 1
                if (r6 == 0) goto L_0x00b0
                long r9 = r5.getContactId()
                boolean r6 = com.android.p032ex.chips.C0699ra.m1084b(r9)
                if (r6 != 0) goto L_0x003e
                boolean r6 = com.android.messaging.util.C1430e.m3624e(r5)
                if (r6 == 0) goto L_0x00c0
            L_0x003e:
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView r6 = com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.this
                android.content.Context r6 = r6.getContext()
                java.lang.String r9 = r5.getDestination()
                com.android.messaging.datamodel.g r6 = com.android.messaging.util.ContactUtil.m3537n(r6, r9)
                android.database.Cursor r6 = r6.mo6584Xd()
                if (r6 == 0) goto L_0x006b
                boolean r9 = r6.moveToNext()     // Catch:{ all -> 0x00a2 }
                if (r9 == 0) goto L_0x006b
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple[] r5 = new com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.ChipReplacementTuple[r8]     // Catch:{ all -> 0x00a2 }
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple r7 = new com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple     // Catch:{ all -> 0x00a2 }
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView r9 = com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.this     // Catch:{ all -> 0x00a2 }
                com.android.ex.chips.ra r8 = com.android.messaging.util.ContactUtil.m3532c(r6, r8)     // Catch:{ all -> 0x00a2 }
                r7.<init>(r4, r8)     // Catch:{ all -> 0x00a2 }
                r5[r2] = r7     // Catch:{ all -> 0x00a2 }
                r11.publishProgress(r5)     // Catch:{ all -> 0x00a2 }
                goto L_0x009c
            L_0x006b:
                java.lang.String r9 = r5.getDestination()     // Catch:{ all -> 0x00a2 }
                boolean r9 = com.android.messaging.util.C1474sa.m3792La(r9)     // Catch:{ all -> 0x00a2 }
                if (r9 == 0) goto L_0x008c
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple[] r7 = new com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.ChipReplacementTuple[r8]     // Catch:{ all -> 0x00a2 }
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple r8 = new com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple     // Catch:{ all -> 0x00a2 }
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView r9 = com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.this     // Catch:{ all -> 0x00a2 }
                java.lang.String r5 = r5.getDestination()     // Catch:{ all -> 0x00a2 }
                com.android.ex.chips.ra r5 = com.android.messaging.util.C1430e.m3629va(r5)     // Catch:{ all -> 0x00a2 }
                r8.<init>(r4, r5)     // Catch:{ all -> 0x00a2 }
                r7[r2] = r8     // Catch:{ all -> 0x00a2 }
                r11.publishProgress(r7)     // Catch:{ all -> 0x00a2 }
                goto L_0x009c
            L_0x008c:
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple[] r5 = new com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.ChipReplacementTuple[r8]     // Catch:{ all -> 0x00a2 }
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple r8 = new com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple     // Catch:{ all -> 0x00a2 }
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView r9 = com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.this     // Catch:{ all -> 0x00a2 }
                r8.<init>(r4, r7)     // Catch:{ all -> 0x00a2 }
                r5[r2] = r8     // Catch:{ all -> 0x00a2 }
                r11.publishProgress(r5)     // Catch:{ all -> 0x00a2 }
                int r3 = r3 + 1
            L_0x009c:
                if (r6 == 0) goto L_0x00c0
                r6.close()
                goto L_0x00c0
            L_0x00a2:
                r11 = move-exception
                throw r11     // Catch:{ all -> 0x00a4 }
            L_0x00a4:
                r12 = move-exception
                if (r6 == 0) goto L_0x00af
                r6.close()     // Catch:{ all -> 0x00ab }
                goto L_0x00af
            L_0x00ab:
                r0 = move-exception
                r11.addSuppressed(r0)
            L_0x00af:
                throw r12
            L_0x00b0:
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple[] r5 = new com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.ChipReplacementTuple[r8]
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple r6 = new com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple
                com.android.messaging.ui.contact.ContactRecipientAutoCompleteView r8 = com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.this
                r6.<init>(r4, r7)
                r5[r2] = r6
                r11.publishProgress(r5)
                int r3 = r3 + 1
            L_0x00c0:
                int r1 = r1 + 1
                goto L_0x001c
            L_0x00c4:
                java.lang.Integer r11 = java.lang.Integer.valueOf(r3)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.contact.ContactRecipientAutoCompleteView.AsyncContactChipSanitizeTask.doInBackground(java.lang.Void[]):java.lang.Integer");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer num) {
            AsyncContactChipSanitizeTask unused = ContactRecipientAutoCompleteView.this.mCurrentSanitizeTask = null;
            if (num.intValue() > 0) {
                ContactRecipientAutoCompleteView.this.mChipsChangeListener.onInvalidContactChipsPruned(num.intValue());
            }
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(ChipReplacementTuple... chipReplacementTupleArr) {
            for (ChipReplacementTuple chipReplacementTuple : chipReplacementTupleArr) {
                if (chipReplacementTuple.removedChip != null) {
                    Editable text = ContactRecipientAutoCompleteView.this.getText();
                    int spanStart = text.getSpanStart(chipReplacementTuple.removedChip);
                    int spanEnd = text.getSpanEnd(chipReplacementTuple.removedChip);
                    if (spanStart >= 0 && spanEnd >= 0) {
                        text.delete(spanStart, spanEnd);
                    }
                    C0699ra raVar = chipReplacementTuple.replacedChipEntry;
                    if (raVar != null) {
                        ContactRecipientAutoCompleteView.this.appendRecipientEntry(raVar);
                    }
                }
            }
        }
    }

    /* renamed from: com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ChipReplacementTuple */
    class ChipReplacementTuple {
        public final C0660b removedChip;
        public final C0699ra replacedChipEntry;

        public ChipReplacementTuple(C0660b bVar, C0699ra raVar) {
            this.removedChip = bVar;
            this.replacedChipEntry = raVar;
        }
    }

    /* renamed from: com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ContactChipsChangeListener */
    public interface ContactChipsChangeListener {
        void onContactChipsChanged(int i, int i2);

        void onEntryComplete();

        void onInvalidContactChipsPruned(int i);
    }

    public ContactRecipientAutoCompleteView(Context context, AttributeSet attributeSet) {
        super(new ContextThemeWrapper(context, R.style.ColorAccentGrayOverrideStyle), attributeSet);
        Rect rect = new Rect(0, 0, 0, 0);
        getPaint().getTextBounds(TEXT_HEIGHT_SAMPLE, 0, 1, rect);
        this.mTextHeight = rect.height();
        setTokenizer(new Rfc822Tokenizer());
        addTextChangedListener(new ContactChipsWatcher((C11261) null));
        setOnFocusListShrinkRecipients(false);
        setBackground(context.getResources().getDrawable(R.drawable.abc_textfield_search_default_mtrl_alpha));
    }

    private void sanitizeContactChips() {
        AsyncContactChipSanitizeTask asyncContactChipSanitizeTask = this.mCurrentSanitizeTask;
        if (asyncContactChipSanitizeTask != null && !asyncContactChipSanitizeTask.isCancelled()) {
            this.mCurrentSanitizeTask.cancel(false);
            this.mCurrentSanitizeTask = null;
        }
        this.mCurrentSanitizeTask = new AsyncContactChipSanitizeTask((C11261) null);
        this.mCurrentSanitizeTask.executeOnExecutor(SANITIZE_EXECUTOR, new Void[0]);
    }

    public ArrayList getRecipientParticipantDataForConversationCreation() {
        C0660b[] bVarArr = (C0660b[]) getText().getSpans(0, getText().length(), C0660b.class);
        ArrayList arrayList = new ArrayList(bVarArr.length);
        for (C0660b bVar : bVarArr) {
            C0699ra entry = bVar.getEntry();
            if (entry != null && entry.isValid() && entry.getDestination() != null && C1474sa.m3792La(entry.getDestination())) {
                arrayList.add(ParticipantData.m1833b(bVar.getEntry()));
            }
        }
        sanitizeContactChips();
        return arrayList;
    }

    public Set getSelectedDestinations() {
        HashSet hashSet = new HashSet();
        for (C0660b entry : (C0660b[]) getText().getSpans(0, getText().length(), C0660b.class)) {
            C0699ra entry2 = entry.getEntry();
            if (!(entry2 == null || !entry2.isValid() || entry2.getDestination() == null)) {
                hashSet.add(C1474sa.getDefault().mo8222Ka(entry2.getDestination()));
            }
        }
        return hashSet;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 6) {
            this.mChipsChangeListener.onEntryComplete();
        }
        return super.onEditorAction(textView, i, keyEvent);
    }

    public void setContactChipsListener(ContactChipsChangeListener contactChipsChangeListener) {
        this.mChipsChangeListener = contactChipsChangeListener;
    }

    /* renamed from: com.android.messaging.ui.contact.ContactRecipientAutoCompleteView$ContactChipsWatcher */
    class ContactChipsWatcher implements TextWatcher {
        private int mLastChipsCount = 0;

        private ContactChipsWatcher() {
        }

        public void afterTextChanged(Editable editable) {
            int length = ((C0660b[]) editable.getSpans(0, editable.length(), C0660b.class)).length;
            if (length != this.mLastChipsCount) {
                if (ContactRecipientAutoCompleteView.this.mChipsChangeListener != null && ContactRecipientAutoCompleteView.this.mCurrentSanitizeTask == null) {
                    ContactRecipientAutoCompleteView.this.mChipsChangeListener.onContactChipsChanged(this.mLastChipsCount, length);
                }
                this.mLastChipsCount = length;
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        /* synthetic */ ContactChipsWatcher(C11261 r2) {
        }
    }
}
