package com.android.dialer.app.list;

import android.content.ClipData;
import android.content.Context;
import android.content.Loader;
import android.graphics.Canvas;
import android.graphics.Point;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.android.contacts.common.MoreContactUtils;
import com.android.contacts.common.list.ContactEntry;
import com.android.contacts.common.list.ContactTileView;
import com.android.contacts.common.model.Contact;
import com.android.contacts.common.model.ContactLoader;
import com.android.dialer.R;
import com.android.dialer.app.list.OldSpeedDialFragment;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.callintent.SpeedDialContactType$Type;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;

public abstract class PhoneFavoriteTileView extends ContactTileView {
    private static final ClipData EMPTY_CLIP_DATA = ClipData.newPlainText("", "");
    /* access modifiers changed from: private */
    public boolean isPinned;
    /* access modifiers changed from: private */
    public boolean isStarred;
    private ContactLoader loader;
    /* access modifiers changed from: private */
    public String phoneNumberString;
    /* access modifiers changed from: private */
    public int position = -1;
    private View shadowOverlay;

    public static class EmptyDragShadowBuilder extends View.DragShadowBuilder {
        public void onDrawShadow(Canvas canvas) {
        }

        public void onProvideShadowMetrics(Point point, Point point2) {
            point.set(1, 1);
            point2.set(0, 0);
        }
    }

    public PhoneFavoriteTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void configureViewForImage(boolean z) {
        View view = this.shadowOverlay;
        if (view != null) {
            view.setVisibility(z ? 8 : 0);
        }
    }

    /* access modifiers changed from: protected */
    public View.OnClickListener createClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                if (PhoneFavoriteTileView.this.mListener != null) {
                    CallSpecificAppData.Builder newBuilder = CallSpecificAppData.newBuilder();
                    newBuilder.setAllowAssistedDialing(true);
                    newBuilder.setCallInitiationType(CallInitiationType$Type.SPEED_DIAL);
                    newBuilder.setSpeedDialContactPosition(PhoneFavoriteTileView.this.position);
                    if (PhoneFavoriteTileView.this.isStarred) {
                        newBuilder.addSpeedDialContactType(SpeedDialContactType$Type.STARRED_CONTACT);
                    } else {
                        newBuilder.addSpeedDialContactType(SpeedDialContactType$Type.FREQUENT_CONTACT);
                    }
                    if (PhoneFavoriteTileView.this.isPinned) {
                        newBuilder.addSpeedDialContactType(SpeedDialContactType$Type.PINNED_CONTACT);
                    }
                    if (TextUtils.isEmpty(PhoneFavoriteTileView.this.phoneNumberString)) {
                        ((LoggingBindingsStub) Logger.get(PhoneFavoriteTileView.this.getContext())).logInteraction(InteractionEvent$Type.SPEED_DIAL_CLICK_CONTACT_WITH_AMBIGUOUS_NUMBER);
                        ((OldSpeedDialFragment.ContactTileAdapterListener) PhoneFavoriteTileView.this.mListener).onContactSelected(PhoneFavoriteTileView.this.getLookupUri(), MoreContactUtils.getTargetRectFromView(PhoneFavoriteTileView.this), (CallSpecificAppData) newBuilder.build());
                        return;
                    }
                    ((OldSpeedDialFragment.ContactTileAdapterListener) PhoneFavoriteTileView.this.mListener).onCallNumberDirectly(PhoneFavoriteTileView.this.phoneNumberString, (CallSpecificAppData) newBuilder.build());
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public ContactPhotoManager.DefaultImageRequest getDefaultImageRequest(String str, String str2) {
        return new ContactPhotoManager.DefaultImageRequest(str, str2, 1, 0.7f, -0.12f, false);
    }

    /* access modifiers changed from: protected */
    public boolean isContactPhotoCircular() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isDarkTheme() {
        return false;
    }

    public /* synthetic */ void lambda$sendViewNotification$1$PhoneFavoriteTileView(Loader loader2, Contact contact) {
        this.loader.reset();
    }

    public void loadFromContact(ContactEntry contactEntry) {
        super.loadFromContact(contactEntry);
        this.phoneNumberString = null;
        this.isPinned = contactEntry.pinned != 0;
        this.isStarred = contactEntry.isFavorite;
        Context context = getContext();
        Uri uri = contactEntry.lookupUri;
        ContactLoader contactLoader = this.loader;
        if (contactLoader != null) {
            contactLoader.reset();
        }
        this.loader = new ContactLoader(context, uri, true);
        this.loader.registerListener(0, new Loader.OnLoadCompleteListener() {
            public final void onLoadComplete(Loader loader, Object obj) {
                PhoneFavoriteTileView.this.lambda$sendViewNotification$1$PhoneFavoriteTileView(loader, (Contact) obj);
            }
        });
        this.loader.startLoading();
        this.phoneNumberString = contactEntry.phoneNumber;
        if (contactEntry == ContactEntry.BLANK_ENTRY) {
            setVisibility(4);
            return;
        }
        ((ImageView) findViewById(R.id.contact_star_icon)).setVisibility(contactEntry.isFavorite ? 0 : 8);
        setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.shadowOverlay = findViewById(R.id.shadow_overlay);
        setOnLongClickListener($$Lambda$PhoneFavoriteTileView$GgkKvFY_MrCSF6IbS8ltmF6CMA.INSTANCE);
    }

    public void setPosition(int i) {
        this.position = i;
    }
}
