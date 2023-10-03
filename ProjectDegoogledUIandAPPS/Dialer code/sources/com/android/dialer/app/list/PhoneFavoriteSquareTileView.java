package com.android.dialer.app.list;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.contacts.common.list.ContactEntry;
import com.android.dialer.R;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.widget.BidiTextView;

public class PhoneFavoriteSquareTileView extends PhoneFavoriteTileView {
    private ContactEntry contactEntry;
    private final float heightToWidthRatio = getResources().getFraction(R.dimen.contact_tile_height_to_width_ratio, 1, 1);
    private ImageButton secondaryButton;

    public PhoneFavoriteSquareTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public int getApproximateImageSize() {
        return getWidth();
    }

    public ContactEntry getContactEntry() {
        return this.contactEntry;
    }

    /* access modifiers changed from: protected */
    public String getNameForView(ContactEntry contactEntry2) {
        return contactEntry2.getPreferredDisplayName(getContext());
    }

    public void loadFromContact(ContactEntry contactEntry2) {
        super.loadFromContact(contactEntry2);
        this.secondaryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((LoggingBindingsStub) Logger.get(PhoneFavoriteSquareTileView.this.getContext())).logInteraction(InteractionEvent$Type.SPEED_DIAL_OPEN_CONTACT_CARD);
                ContactsContract.QuickContact.showQuickContact(PhoneFavoriteSquareTileView.this.getContext(), PhoneFavoriteSquareTileView.this, PhoneFavoriteSquareTileView.this.getLookupUri(), (String[]) null, "vnd.android.cursor.item/phone_v2");
            }
        });
        this.contactEntry = contactEntry2;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        ((BidiTextView) findViewById(R.id.contact_tile_name)).setElegantTextHeight(false);
        ((TextView) findViewById(R.id.contact_tile_phone_type)).setElegantTextHeight(false);
        this.secondaryButton = (ImageButton) findViewById(R.id.contact_tile_secondary_button);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int i3 = (int) (this.heightToWidthRatio * ((float) size));
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            getChildAt(i4).measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        }
        setMeasuredDimension(size, i3);
    }
}
