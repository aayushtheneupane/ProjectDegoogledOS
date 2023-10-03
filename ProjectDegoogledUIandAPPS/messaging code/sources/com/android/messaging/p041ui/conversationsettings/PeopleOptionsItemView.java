package com.android.messaging.p041ui.conversationsettings;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0907T;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversationsettings.PeopleOptionsItemView */
public class PeopleOptionsItemView extends LinearLayout {
    /* access modifiers changed from: private */
    public final C0907T mData;
    /* access modifiers changed from: private */
    public C1245h mHostInterface;
    private TextView mTitle;

    public PeopleOptionsItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mData = C0947h.get().mo6611i(context);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mTitle = (TextView) findViewById(R.id.title);
        setOnClickListener(new C1244g(this));
    }

    /* renamed from: a */
    public void mo7604a(Cursor cursor, int i, ParticipantData participantData, C1245h hVar) {
        C1424b.m3592ia(i < 2 && i >= 0);
        this.mData.mo6374a(cursor, participantData, i);
        this.mHostInterface = hVar;
        this.mTitle.setText(this.mData.getTitle());
    }
}
