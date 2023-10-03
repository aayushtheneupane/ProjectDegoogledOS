package com.android.dialer.searchfragment.cp2;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import com.android.dialer.R;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.searchfragment.common.QueryBoldingUtil;
import com.android.dialer.searchfragment.common.RowClickListener;
import com.android.dialer.searchfragment.common.SearchCursor;
import com.android.dialer.widget.BidiTextView;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class SearchContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ImageView callToActionView;
    private final Context context;
    private int currentAction;
    private DialerContact dialerContact;
    private final RowClickListener listener;
    private final BidiTextView nameOrNumberView;
    private String number;
    private final BidiTextView numberView;
    private final QuickContactBadge photo;
    private int position;

    public SearchContactViewHolder(View view, RowClickListener rowClickListener) {
        super(view);
        this.listener = rowClickListener;
        view.setOnClickListener(this);
        this.photo = (QuickContactBadge) view.findViewById(R.id.photo);
        this.nameOrNumberView = (BidiTextView) view.findViewById(R.id.primary);
        this.numberView = (BidiTextView) view.findViewById(R.id.secondary);
        this.callToActionView = (ImageView) view.findViewById(R.id.call_to_action);
        this.context = view.getContext();
    }

    public void bind(SearchCursor searchCursor, String str) {
        String str2;
        String str3;
        int i;
        Uri uri;
        SearchCursor searchCursor2 = searchCursor;
        String str4 = str;
        Context context2 = this.context;
        DialerContact.Builder newBuilder = DialerContact.newBuilder();
        String string = searchCursor2.getString(4);
        String string2 = searchCursor2.getString(3);
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(searchCursor2.getLong(9), searchCursor2.getString(7));
        newBuilder.setNumber(string2);
        newBuilder.setPhotoId(searchCursor2.getLong(5));
        boolean z = true;
        newBuilder.setContactType(1);
        newBuilder.setNameOrNumber(string);
        newBuilder.setNumberLabel(ContactsContract.CommonDataKinds.Phone.getTypeLabel(context2.getResources(), searchCursor2.getInt(1), searchCursor2.getString(2)).toString());
        String string3 = searchCursor2.getString(6);
        if (string3 != null) {
            newBuilder.setPhotoUri(string3);
        }
        if (lookupUri != null) {
            newBuilder.setContactUri(lookupUri.toString());
        }
        if (!TextUtils.isEmpty(string)) {
            newBuilder.setDisplayNumber(string2);
        }
        this.dialerContact = (DialerContact) newBuilder.build();
        this.position = searchCursor.getPosition();
        this.number = searchCursor2.getString(3);
        String string4 = searchCursor2.getString(4);
        Resources resources = this.context.getResources();
        int i2 = searchCursor2.getInt(1);
        String string5 = searchCursor2.getString(2);
        if (i2 != 0 || !TextUtils.isEmpty(string5)) {
            str2 = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(resources, i2, string5);
        } else {
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str3 = this.number;
        } else {
            str3 = this.context.getString(R.string.call_subject_type_and_number, new Object[]{str2, this.number});
        }
        this.nameOrNumberView.setText(QueryBoldingUtil.getNameWithQueryBolded(str4, string4, this.context));
        this.numberView.setText(QueryBoldingUtil.getNumberWithQueryBolded(str4, str3));
        Context context3 = this.context;
        int i3 = searchCursor2.getInt(8);
        String string6 = searchCursor2.getString(3);
        if ((i3 & 1) == 1) {
            i = 1;
        } else {
            ((DuoStub) DuoComponent.get(context3).getDuo()).isReachable(context3, string6);
            ((EnrichedCallManagerStub) EnrichedCallComponent.get(context3).getEnrichedCallManager()).getCapabilities(string6);
            if (str4 == null || str.length() < 3) {
                searchCursor.getCount();
            }
            i = 0;
        }
        this.currentAction = i;
        int i4 = this.currentAction;
        if (i4 == 0) {
            this.callToActionView.setVisibility(8);
            this.callToActionView.setOnClickListener((View.OnClickListener) null);
        } else if (i4 == 1 || i4 == 2) {
            this.callToActionView.setVisibility(0);
            this.callToActionView.setImageDrawable(this.context.getDrawable(R.drawable.quantum_ic_videocam_vd_white_24));
            this.callToActionView.setContentDescription(this.context.getString(R.string.description_search_video_call));
            this.callToActionView.setOnClickListener(this);
        } else if (i4 == 3) {
            this.callToActionView.setVisibility(0);
            this.callToActionView.setImageDrawable(this.context.getDrawable(R.drawable.ic_phone_attach));
            this.callToActionView.setContentDescription(this.context.getString(R.string.description_search_call_and_share));
            this.callToActionView.setOnClickListener(this);
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid Call to action type: ");
            outline13.append(this.currentAction);
            throw new IllegalStateException(outline13.toString());
        }
        int position2 = searchCursor.getPosition();
        String string7 = searchCursor2.getString(7);
        searchCursor2.moveToPosition(position2 - 1);
        if (searchCursor.isHeader() || searchCursor.isBeforeFirst()) {
            searchCursor2.moveToPosition(position2);
        } else {
            String string8 = searchCursor2.getString(7);
            searchCursor2.moveToPosition(position2);
            z = true ^ string7.equals(string8);
        }
        if (z) {
            this.nameOrNumberView.setVisibility(0);
            this.photo.setVisibility(0);
            String string9 = searchCursor2.getString(6);
            ContactPhotoManager instance = ContactPhotoManager.getInstance(this.context);
            QuickContactBadge quickContactBadge = this.photo;
            Uri lookupUri2 = ContactsContract.Contacts.getLookupUri(searchCursor2.getLong(0), searchCursor2.getString(7));
            long j = searchCursor2.getLong(5);
            if (string9 == null) {
                uri = null;
            } else {
                uri = Uri.parse(string9);
            }
            instance.loadDialerThumbnailOrPhoto(quickContactBadge, lookupUri2, j, uri, string4, 1);
            return;
        }
        this.nameOrNumberView.setVisibility(8);
        this.photo.setVisibility(4);
    }

    public void onClick(View view) {
        if (view == this.callToActionView) {
            int i = this.currentAction;
            if (i == 1) {
                this.listener.placeVideoCall(this.number, this.position);
            } else if (i == 2) {
                this.listener.placeDuoCall(this.number);
            } else if (i == 3) {
                this.listener.openCallAndShare(this.dialerContact);
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid Call to action type: ");
                outline13.append(this.currentAction);
                throw new IllegalStateException(outline13.toString());
            }
        } else {
            this.listener.placeVoiceCall(this.number, this.position);
        }
    }
}
