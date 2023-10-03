package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0902N;

/* renamed from: com.android.messaging.ui.BlockedParticipantListItemView */
public class BlockedParticipantListItemView extends LinearLayout {
    private ContactIconView mContactIconView;
    /* access modifiers changed from: private */
    public C0902N mData;

    /* renamed from: mh */
    private TextView f1600mh;

    public BlockedParticipantListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.f1600mh = (TextView) findViewById(R.id.name);
        this.mContactIconView = (ContactIconView) findViewById(R.id.contact_icon);
        setOnClickListener(new C1379s(this));
    }

    /* renamed from: a */
    public void mo6900a(C0902N n) {
        this.mData = n;
        this.f1600mh.setText(BidiFormatter.getInstance().unicodeWrap(n.getDisplayName(), TextDirectionHeuristicsCompat.LTR));
        this.mContactIconView.mo6929a(n.mo6132rf(), n.getContactId(), n.mo6131m(), n.mo6133sf());
        this.f1600mh.setText(n.getDisplayName());
    }
}
