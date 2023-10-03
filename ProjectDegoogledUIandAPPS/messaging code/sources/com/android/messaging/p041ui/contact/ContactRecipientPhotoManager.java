package com.android.messaging.p041ui.contact;

import android.content.Context;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.datamodel.p038b.C0839B;
import com.android.messaging.datamodel.p038b.C0864d;
import com.android.messaging.datamodel.p038b.C0865e;
import com.android.messaging.datamodel.p038b.C0881u;
import com.android.messaging.datamodel.p038b.C0883w;
import com.android.messaging.p041ui.contact.ContactListItemView;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1480va;
import com.android.p032ex.chips.C0699ra;
import com.android.p032ex.chips.C0706x;
import com.android.p032ex.chips.C0707y;

/* renamed from: com.android.messaging.ui.contact.ContactRecipientPhotoManager */
public class ContactRecipientPhotoManager implements C0707y {
    private static final String IMAGE_BYTES_REQUEST_STATIC_BINDING_ID = "imagebytes";
    private final ContactListItemView.HostInterface mClivHostInterface;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final int mIconSize;

    public ContactRecipientPhotoManager(Context context, ContactListItemView.HostInterface hostInterface) {
        this.mContext = context;
        this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.compose_message_chip_height) - (context.getResources().getDimensionPixelSize(R.dimen.compose_message_chip_padding) * 2);
        this.mClivHostInterface = hostInterface;
    }

    public void populatePhotoBytesAsync(final C0699ra raVar, final C0706x xVar) {
        C1480va.getMainThreadHandler().post(new Runnable() {
            public void run() {
                C0865e a = new C0864d(C1426c.m3601c(ParticipantData.m1833b(raVar)), ContactRecipientPhotoManager.this.mIconSize, ContactRecipientPhotoManager.this.mIconSize, true).mo6164a(ContactRecipientPhotoManager.this.mContext, new C0839B() {
                    public void onMediaResourceLoadError(C0883w wVar, Exception exc) {
                        C1430e.m3622e("MessagingApp", "Photo bytes loading failed due to " + exc + " request key=" + wVar.getKey());
                        xVar.onPhotoBytesAsyncLoadFailed();
                    }

                    public void onMediaResourceLoaded(C0883w wVar, C0881u uVar, boolean z) {
                        raVar.mo5658j(uVar.getBytes());
                        xVar.onPhotoBytesAsynchronouslyPopulated();
                    }
                });
                a.mo5925V(ContactRecipientPhotoManager.IMAGE_BYTES_REQUEST_STATIC_BINDING_ID);
                C0967f.get().mo6649Ld().mo6080a(a);
            }
        });
    }
}
