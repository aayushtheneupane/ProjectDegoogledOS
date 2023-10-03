package com.android.dialer.contactsfragment;

import android.content.Context;
import android.net.Uri;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.contactsfragment.ContactsFragment;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.widget.BidiTextView;

final class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private long contactId;
    private Uri contactUri;
    private final Context context;
    private final TextView header;
    private final BidiTextView name;
    private final ContactsFragment.OnContactSelectedListener onContactSelectedListener;
    private final QuickContactBadge photo;

    ContactViewHolder(View view, ContactsFragment.OnContactSelectedListener onContactSelectedListener2) {
        super(view);
        Assert.isNotNull(onContactSelectedListener2);
        this.onContactSelectedListener = onContactSelectedListener2;
        this.context = view.getContext();
        view.findViewById(R.id.click_target).setOnClickListener(this);
        this.header = (TextView) view.findViewById(R.id.header);
        this.name = (BidiTextView) view.findViewById(R.id.contact_name);
        this.photo = (QuickContactBadge) view.findViewById(R.id.photo);
    }

    public void bind(String str, String str2, Uri uri, long j, boolean z) {
        Assert.checkArgument(!TextUtils.isEmpty(str2));
        this.contactUri = uri;
        this.contactId = j;
        this.name.setText(str2);
        this.header.setText(str);
        this.header.setVisibility(z ? 0 : 4);
        ((LoggingBindingsStub) Logger.get(this.context)).logQuickContactOnTouch(this.photo, InteractionEvent$Type.OPEN_QUICK_CONTACT_FROM_CONTACTS_FRAGMENT_BADGE, true);
    }

    public TextView getHeaderView() {
        return this.header;
    }

    public QuickContactBadge getPhoto() {
        return this.photo;
    }

    public void onClick(View view) {
        this.onContactSelectedListener.onContactSelected(this.photo, this.contactUri, this.contactId);
    }
}
