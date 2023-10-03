package com.android.messaging.p041ui.conversationsettings;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.p016v4.media.session.C0107q;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.conversationsettings.a */
public class C1238a implements DialogInterface.OnClickListener {

    /* renamed from: NH */
    private final String f1962NH;
    private final Context mContext;

    public C1238a(Context context, String str) {
        this.mContext = context;
        this.f1962NH = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ((ClipboardManager) this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, this.f1962NH));
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        TextView textView = (TextView) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.copy_contact_dialog_view, (ViewGroup) null, false);
        textView.setText(this.f1962NH);
        textView.setContentDescription(C0107q.m124a(this.mContext.getResources(), this.f1962NH));
        builder.setView(textView).setTitle(R.string.copy_to_clipboard_dialog_title).setPositiveButton(R.string.copy_to_clipboard, this).show();
    }
}
