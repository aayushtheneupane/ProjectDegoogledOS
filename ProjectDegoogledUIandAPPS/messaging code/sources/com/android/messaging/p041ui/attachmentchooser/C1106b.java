package com.android.messaging.p041ui.attachmentchooser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MessagePartData;
import java.util.ArrayList;

/* renamed from: com.android.messaging.ui.attachmentchooser.b */
class C1106b extends ArrayAdapter {
    final /* synthetic */ AttachmentChooserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1106b(AttachmentChooserFragment attachmentChooserFragment, Context context) {
        super(context, R.layout.attachment_grid_item_view, new ArrayList());
        this.this$0 = attachmentChooserFragment;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        AttachmentGridItemView attachmentGridItemView;
        MessagePartData messagePartData = (MessagePartData) getItem(i);
        if (view == null || !(view instanceof AttachmentGridItemView)) {
            attachmentGridItemView = (AttachmentGridItemView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.attachment_grid_item_view, viewGroup, false);
        } else {
            attachmentGridItemView = (AttachmentGridItemView) view;
        }
        attachmentGridItemView.mo7204a(messagePartData, this.this$0.f1757Ga);
        return attachmentGridItemView;
    }
}
