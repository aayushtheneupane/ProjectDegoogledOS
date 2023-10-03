package com.android.messaging.p041ui;

import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.p041ui.contact.AddContactsConfirmationDialog;

/* renamed from: com.android.messaging.ui.C */
class C1035C implements View.OnClickListener {
    final /* synthetic */ ContactIconView this$0;

    C1035C(ContactIconView contactIconView) {
        this.this$0 = contactIconView;
    }

    public void onClick(View view) {
        long a = this.this$0.f1613Rj;
        String b = this.this$0.f1614Sj;
        Uri c = this.this$0.mAvatarUri;
        String d = this.this$0.mNormalizedDestination;
        if (a > -1 && !TextUtils.isEmpty(b)) {
            ContactsContract.QuickContact.showQuickContact(view.getContext(), view, ContactsContract.Contacts.getLookupUri(a, b), 3, (String[]) null);
        } else if (!TextUtils.isEmpty(d)) {
            ParticipantData.m1842vh();
            if (!TextUtils.equals(d, "ʼUNKNOWN_SENDER!ʼ")) {
                new AddContactsConfirmationDialog(view.getContext(), c, d).show();
            }
        }
    }
}
