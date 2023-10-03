package com.android.dialer.contactsfragment;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;

final class AddContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Context context;

    AddContactViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        this.context = view.getContext();
    }

    public void onClick(View view) {
        DialerUtils.startActivityWithErrorToast(this.context, CallUtil.getNewContactIntent(), R.string.add_contact_not_available);
    }
}
