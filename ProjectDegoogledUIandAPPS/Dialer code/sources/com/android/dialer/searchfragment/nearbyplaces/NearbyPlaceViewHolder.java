package com.android.dialer.searchfragment.nearbyplaces;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.searchfragment.common.QueryBoldingUtil;
import com.android.dialer.searchfragment.common.RowClickListener;
import com.android.dialer.searchfragment.common.SearchCursor;

public final class NearbyPlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Context context;
    private final RowClickListener listener;
    private String number;
    private final QuickContactBadge photo;
    private final TextView placeAddress;
    private final TextView placeName;
    private int position;

    public NearbyPlaceViewHolder(View view, RowClickListener rowClickListener) {
        super(view);
        view.setOnClickListener(this);
        this.photo = (QuickContactBadge) view.findViewById(R.id.photo);
        this.placeName = (TextView) view.findViewById(R.id.primary);
        this.placeAddress = (TextView) view.findViewById(R.id.secondary);
        this.context = view.getContext();
        this.listener = rowClickListener;
    }

    public void bind(SearchCursor searchCursor, String str) {
        Uri uri;
        this.number = searchCursor.getString(3);
        this.position = searchCursor.getPosition();
        String string = searchCursor.getString(4);
        String string2 = searchCursor.getString(2);
        this.placeName.setText(QueryBoldingUtil.getNameWithQueryBolded(str, string, this.context));
        this.placeAddress.setText(QueryBoldingUtil.getNameWithQueryBolded(str, string2, this.context));
        String string3 = searchCursor.getString(6);
        ContactPhotoManager instance = ContactPhotoManager.getInstance(this.context);
        QuickContactBadge quickContactBadge = this.photo;
        Uri build = ContactsContract.Contacts.CONTENT_LOOKUP_URI.buildUpon().appendPath("encoded").appendQueryParameter("directory", String.valueOf(searchCursor.getDirectoryId())).encodedFragment(searchCursor.getString(7)).build();
        long j = searchCursor.getLong(5);
        if (string3 == null) {
            uri = null;
        } else {
            uri = Uri.parse(string3);
        }
        instance.loadDialerThumbnailOrPhoto(quickContactBadge, build, j, uri, string, 2);
    }

    public void onClick(View view) {
        this.listener.placeVoiceCall(this.number, this.position);
    }
}
