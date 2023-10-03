package com.android.messaging.p041ui.contact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.support.p016v4.media.session.C0107q;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.p041ui.ContactIconView;

/* renamed from: com.android.messaging.ui.contact.AddContactsConfirmationDialog */
public class AddContactsConfirmationDialog implements DialogInterface.OnClickListener {
    private final Uri mAvatarUri;
    private final Context mContext;
    private final String mNormalizedDestination;

    public AddContactsConfirmationDialog(Context context, Uri uri, String str) {
        this.mContext = context;
        this.mAvatarUri = uri;
        this.mNormalizedDestination = str;
    }

    private View createBodyView() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.add_contacts_confirmation_dialog_body, (ViewGroup) null);
        ((ContactIconView) inflate.findViewById(R.id.contact_icon)).mo6930f(this.mAvatarUri);
        TextView textView = (TextView) inflate.findViewById(R.id.participant_name);
        textView.setText(this.mNormalizedDestination);
        textView.setContentDescription(C0107q.m124a(this.mContext.getResources(), this.mNormalizedDestination));
        return inflate;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C1040Ea.get().mo6977h(this.mContext, this.mNormalizedDestination);
    }

    public void show() {
        AlertDialog create = new AlertDialog.Builder(this.mContext, R.style.BugleThemeDialog).setTitle(R.string.add_contact_confirmation_dialog_title).setView(createBodyView()).setPositiveButton(R.string.add_contact_confirmation, this).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
        create.show();
        Resources resources = this.mContext.getResources();
        Button button = create.getButton(-2);
        if (button != null) {
            button.setTextColor(resources.getColor(R.color.contact_picker_button_text_color));
        }
        Button button2 = create.getButton(-1);
        if (button2 != null) {
            button2.setTextColor(resources.getColor(R.color.contact_picker_button_text_color));
        }
    }
}
