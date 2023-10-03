package com.android.messaging.p041ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/* renamed from: com.android.messaging.ui.PlainTextEditText */
public class PlainTextEditText extends EditText {
    public PlainTextEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTextContextMenuItem(int i) {
        int i2;
        ClipData primaryClip;
        if (i != 16908322) {
            return super.onTextContextMenuItem(i);
        }
        int selectionStart = getSelectionStart();
        boolean onTextContextMenuItem = super.onTextContextMenuItem(i);
        CharSequence text = getText();
        int selectionStart2 = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        int i3 = selectionStart2 - 1;
        if (selectionStart2 - selectionStart == 1 && text.charAt(i3) == 65532 && (primaryClip = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip()) != null) {
            ClipData.Item itemAt = primaryClip.getItemAt(0);
            StringBuilder sb = new StringBuilder(text);
            String charSequence = itemAt.getText().toString();
            sb.replace(selectionStart, selectionStart2, charSequence);
            CharSequence sb2 = sb.toString();
            i2 = selectionStart + charSequence.length();
            selectionEnd = i2;
            text = sb2;
        } else {
            i2 = selectionStart2;
        }
        setText(text.toString(), TextView.BufferType.EDITABLE);
        setSelection(i2, selectionEnd);
        return onTextContextMenuItem;
    }
}
