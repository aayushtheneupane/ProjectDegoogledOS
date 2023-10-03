package com.android.p032ex.chips;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.android.p032ex.chips.p033a.C0660b;

/* renamed from: com.android.ex.chips.pa */
class C0695pa implements TextWatcher {
    final /* synthetic */ C0697qa this$0;

    /* synthetic */ C0695pa(C0697qa qaVar, C0650S s) {
        this.this$0 = qaVar;
    }

    public void afterTextChanged(Editable editable) {
        char c;
        int i = 0;
        if (TextUtils.isEmpty(editable)) {
            Spannable spannable = this.this$0.getSpannable();
            C0660b[] bVarArr = (C0660b[]) spannable.getSpans(0, this.this$0.getText().length(), C0660b.class);
            int length = bVarArr.length;
            while (i < length) {
                spannable.removeSpan(bVarArr[i]);
                i++;
            }
            if (this.this$0.mMoreChip != null) {
                spannable.removeSpan(this.this$0.mMoreChip);
            }
            this.this$0.clearSelectedChip();
        } else if (!this.this$0.chipsPending()) {
            if (this.this$0.mSelectedChip != null) {
                C0697qa qaVar = this.this$0;
                if (!qaVar.isGeneratedContact(qaVar.mSelectedChip)) {
                    this.this$0.setCursorVisible(true);
                    C0697qa qaVar2 = this.this$0;
                    qaVar2.setSelection(qaVar2.getText().length());
                    this.this$0.clearSelectedChip();
                } else {
                    return;
                }
            }
            if (editable.length() <= 1) {
                return;
            }
            if (this.this$0.lastCharacterIsCommitCharacter(editable)) {
                this.this$0.commitByCharacter();
                return;
            }
            if (this.this$0.getSelectionEnd() != 0) {
                i = this.this$0.getSelectionEnd() - 1;
            }
            int length2 = this.this$0.length() - 1;
            if (i != length2) {
                c = editable.charAt(i);
            } else {
                c = editable.charAt(length2);
            }
            if (c == ' ' && !this.this$0.isPhoneQuery()) {
                String obj = this.this$0.getText().toString();
                int findTokenStart = this.this$0.mTokenizer.findTokenStart(obj, this.this$0.getSelectionEnd());
                if (this.this$0.isValidEmailAddress(obj.substring(findTokenStart, this.this$0.mTokenizer.findTokenEnd(obj, findTokenStart)))) {
                    this.this$0.commitByCharacter();
                }
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i2 - i3 == 1) {
            int selectionStart = this.this$0.getSelectionStart();
            C0660b[] bVarArr = (C0660b[]) this.this$0.getSpannable().getSpans(selectionStart, selectionStart, C0660b.class);
            if (bVarArr.length > 0) {
                C0660b bVar = bVarArr[0];
                Editable text = this.this$0.getText();
                int spanStart = text.getSpanStart(bVar);
                int spanEnd = text.getSpanEnd(bVar) + 1;
                if (spanEnd > text.length()) {
                    spanEnd = text.length();
                }
                boolean access$3000 = this.this$0.mNoChipMode;
                text.removeSpan(bVar);
                text.delete(spanStart, spanEnd);
            }
        } else if (i3 > i2 && this.this$0.mSelectedChip != null) {
            C0697qa qaVar = this.this$0;
            if (qaVar.isGeneratedContact(qaVar.mSelectedChip) && this.this$0.lastCharacterIsCommitCharacter(charSequence)) {
                this.this$0.commitByCharacter();
            }
        }
    }
}
