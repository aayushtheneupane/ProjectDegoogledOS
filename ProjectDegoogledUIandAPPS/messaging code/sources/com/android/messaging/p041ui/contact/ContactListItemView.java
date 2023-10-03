package com.android.messaging.p041ui.contact;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0918c;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.p041ui.ContactIconView;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.p032ex.chips.C0699ra;

/* renamed from: com.android.messaging.ui.contact.ContactListItemView */
public class ContactListItemView extends LinearLayout implements View.OnClickListener {
    private TextView mAlphabetHeaderTextView;
    private ImageView mContactCheckmarkView;
    private TextView mContactDetailTypeTextView;
    private TextView mContactDetailsTextView;
    private ContactIconView mContactIconView;
    private TextView mContactNameTextView;
    final C0918c mData = C0947h.get().mo6592Yd();
    private HostInterface mHostInterface;
    private boolean mShouldShowAlphabetHeader;
    private ImageView mWorkProfileIcon;

    /* renamed from: com.android.messaging.ui.contact.ContactListItemView$HostInterface */
    public interface HostInterface {
        boolean isContactSelected(C0918c cVar);

        void onContactListItemClicked(C0918c cVar, ContactListItemView contactListItemView);
    }

    public ContactListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void updateViewAppearance() {
        this.mContactNameTextView.setText(this.mData.getDisplayName());
        this.mContactDetailsTextView.setText(this.mData.getDestination());
        this.mContactDetailTypeTextView.setText(ContactsContract.CommonDataKinds.Phone.getTypeLabel(getResources(), this.mData.mo6417ud(), this.mData.mo6416td()));
        C0699ra Ef = this.mData.mo6409Ef();
        String valueOf = String.valueOf(this.mData.getDestination());
        if (this.mData.mo6407Cf()) {
            this.mContactIconView.mo6929a(C1426c.m3601c(ParticipantData.m1833b(Ef)), this.mData.getContactId(), this.mData.mo6415m(), valueOf);
            this.mContactIconView.setVisibility(0);
            this.mContactCheckmarkView.setVisibility(8);
            this.mContactDetailTypeTextView.setVisibility(8);
            this.mContactDetailsTextView.setVisibility(8);
            this.mContactNameTextView.setVisibility(0);
        } else if (this.mData.mo6406Bf()) {
            this.mContactIconView.mo6929a(C1426c.m3601c(ParticipantData.m1833b(Ef)), this.mData.getContactId(), this.mData.mo6415m(), valueOf);
            this.mContactIconView.setVisibility(0);
            this.mContactNameTextView.setVisibility(0);
            boolean isContactSelected = this.mHostInterface.isContactSelected(this.mData);
            setSelected(isContactSelected);
            this.mContactCheckmarkView.setVisibility(isContactSelected ? 0 : 8);
            this.mContactDetailsTextView.setVisibility(0);
            this.mContactDetailTypeTextView.setVisibility(0);
        } else {
            this.mContactIconView.mo6930f((Uri) null);
            this.mContactIconView.setVisibility(4);
            this.mContactNameTextView.setVisibility(8);
            boolean isContactSelected2 = this.mHostInterface.isContactSelected(this.mData);
            setSelected(isContactSelected2);
            this.mContactCheckmarkView.setVisibility(isContactSelected2 ? 0 : 8);
            this.mContactDetailsTextView.setVisibility(0);
            this.mContactDetailTypeTextView.setVisibility(0);
        }
        if (this.mData.mo6408Df()) {
            this.mWorkProfileIcon.setVisibility(0);
        } else {
            this.mWorkProfileIcon.setVisibility(8);
        }
        if (this.mShouldShowAlphabetHeader) {
            this.mAlphabetHeaderTextView.setVisibility(0);
            this.mAlphabetHeaderTextView.setText(this.mData.mo6405Af());
            return;
        }
        this.mAlphabetHeaderTextView.setVisibility(8);
    }

    public void bind(Cursor cursor, HostInterface hostInterface, boolean z, String str) {
        this.mData.mo6410a(cursor, str);
        this.mHostInterface = hostInterface;
        this.mShouldShowAlphabetHeader = z;
        setOnClickListener(this);
        updateViewAppearance();
    }

    public void onClick(View view) {
        boolean z = true;
        C1424b.m3592ia(view == this);
        if (this.mHostInterface == null) {
            z = false;
        }
        C1424b.m3592ia(z);
        this.mHostInterface.onContactListItemClicked(this.mData, this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mContactNameTextView = (TextView) findViewById(R.id.contact_name);
        this.mContactDetailsTextView = (TextView) findViewById(R.id.contact_details);
        this.mContactDetailTypeTextView = (TextView) findViewById(R.id.contact_detail_type);
        this.mAlphabetHeaderTextView = (TextView) findViewById(R.id.alphabet_header);
        this.mContactIconView = (ContactIconView) findViewById(R.id.contact_icon);
        this.mContactCheckmarkView = (ImageView) findViewById(R.id.contact_checkmark);
        this.mWorkProfileIcon = (ImageView) findViewById(R.id.work_profile_icon);
    }

    public void setImageClickHandlerDisabled(boolean z) {
        this.mContactIconView.setImageClickHandlerDisabled(z);
    }

    public void bind(C0699ra raVar, CharSequence charSequence, CharSequence charSequence2, HostInterface hostInterface, boolean z, boolean z2) {
        this.mData.mo6411a(raVar, charSequence, charSequence2, z, z2);
        this.mHostInterface = hostInterface;
        this.mShouldShowAlphabetHeader = false;
        updateViewAppearance();
    }
}
