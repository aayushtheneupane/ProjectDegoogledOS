package com.android.dialer.searchfragment.directories;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.searchfragment.common.QueryBoldingUtil;
import com.android.dialer.searchfragment.common.RowClickListener;
import com.android.dialer.searchfragment.common.SearchCursor;

public final class DirectoryContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Context context;
    private final RowClickListener listener;
    private final TextView nameView;
    private String number;
    private final TextView numberView;
    private final QuickContactBadge photo;
    private int position;
    private final ImageView workBadge;

    public DirectoryContactViewHolder(View view, RowClickListener rowClickListener) {
        super(view);
        view.setOnClickListener(this);
        this.photo = (QuickContactBadge) view.findViewById(R.id.photo);
        this.nameView = (TextView) view.findViewById(R.id.primary);
        this.numberView = (TextView) view.findViewById(R.id.secondary);
        this.workBadge = (ImageView) view.findViewById(R.id.work_icon);
        this.context = view.getContext();
        this.listener = rowClickListener;
    }

    public void bind(SearchCursor searchCursor, String str) {
        String str2;
        String str3;
        Uri uri;
        this.number = searchCursor.getString(3);
        this.position = searchCursor.getPosition();
        String string = searchCursor.getString(4);
        Resources resources = this.context.getResources();
        boolean z = true;
        int i = searchCursor.getInt(1);
        String string2 = searchCursor.getString(2);
        if (i != 0 || !TextUtils.isEmpty(string2)) {
            str2 = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(resources, i, string2);
        } else {
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str3 = this.number;
        } else {
            str3 = this.context.getString(R.string.call_subject_type_and_number, new Object[]{str2, this.number});
        }
        this.nameView.setText(QueryBoldingUtil.getNameWithQueryBolded(str, string, this.context));
        this.numberView.setText(QueryBoldingUtil.getNameWithQueryBolded(str, str3, this.context));
        ImageView imageView = this.workBadge;
        long directoryId = searchCursor.getDirectoryId();
        imageView.setVisibility(ContactsContract.Directory.isEnterpriseDirectoryId(directoryId) && !ContactsContract.Directory.isRemoteDirectoryId(directoryId) ? 0 : 8);
        int position2 = searchCursor.getPosition();
        String string3 = searchCursor.getString(7);
        searchCursor.moveToPosition(position2 - 1);
        if (searchCursor.isHeader() || searchCursor.isBeforeFirst()) {
            searchCursor.moveToPosition(position2);
        } else {
            String string4 = searchCursor.getString(7);
            searchCursor.moveToPosition(position2);
            z = true ^ string3.equals(string4);
        }
        if (z) {
            this.nameView.setVisibility(0);
            this.photo.setVisibility(0);
            String string5 = searchCursor.getString(6);
            ContactPhotoManager instance = ContactPhotoManager.getInstance(this.context);
            QuickContactBadge quickContactBadge = this.photo;
            Uri build = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, searchCursor.getString(7)).buildUpon().appendQueryParameter("directory", String.valueOf(searchCursor.getDirectoryId())).build();
            long j = searchCursor.getLong(5);
            if (string5 == null) {
                uri = null;
            } else {
                uri = Uri.parse(string5);
            }
            instance.loadDialerThumbnailOrPhoto(quickContactBadge, build, j, uri, string, 1);
            return;
        }
        this.nameView.setVisibility(8);
        this.photo.setVisibility(4);
    }

    public void onClick(View view) {
        this.listener.placeVoiceCall(this.number, this.position);
    }
}
