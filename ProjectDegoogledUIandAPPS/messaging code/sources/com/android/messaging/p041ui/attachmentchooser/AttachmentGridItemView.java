package com.android.messaging.p041ui.attachmentchooser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.C1063Q;
import com.android.messaging.p041ui.C1269l;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.attachmentchooser.AttachmentGridItemView */
public class AttachmentGridItemView extends FrameLayout {

    /* renamed from: Cf */
    private FrameLayout f1758Cf;
    /* access modifiers changed from: private */

    /* renamed from: Df */
    public CheckBox f1759Df;
    MessagePartData mAttachmentData;
    /* access modifiers changed from: private */
    public C1110f mHostInterface;

    public AttachmentGridItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: Ib */
    public void mo7203Ib() {
        this.f1759Df.setChecked(this.mHostInterface.mo7211a(this.mAttachmentData));
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1758Cf = (FrameLayout) findViewById(R.id.attachment_container);
        this.f1759Df = (CheckBox) findViewById(R.id.checkbox);
        this.f1759Df.setOnClickListener(new C1107c(this));
        setOnClickListener(new C1108d(this));
        addOnLayoutChangeListener(new C1109e(this));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    /* access modifiers changed from: package-private */
    public C1110f testGetHostInterface() {
        return this.mHostInterface;
    }

    /* renamed from: a */
    public void mo7204a(MessagePartData messagePartData, C1110f fVar) {
        C1424b.m3592ia(messagePartData.mo6300dh());
        this.mHostInterface = fVar;
        mo7203Ib();
        MessagePartData messagePartData2 = this.mAttachmentData;
        if (messagePartData2 == null || !messagePartData2.equals(messagePartData)) {
            this.mAttachmentData = messagePartData;
            this.f1758Cf.removeAllViews();
            this.f1758Cf.addView(C1269l.m3184a(LayoutInflater.from(getContext()), this.mAttachmentData, this.f1758Cf, 3, true, (C1063Q) null));
        }
    }
}
