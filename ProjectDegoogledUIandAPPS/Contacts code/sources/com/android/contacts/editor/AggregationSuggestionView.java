package com.android.contacts.editor;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.editor.AggregationSuggestionEngine;

public class AggregationSuggestionView extends LinearLayout {
    private Listener mListener;
    private AggregationSuggestionEngine.Suggestion mSuggestion;

    public interface Listener {
        void onEditAction(Uri uri, long j);
    }

    public AggregationSuggestionView(Context context) {
        super(context);
    }

    public AggregationSuggestionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AggregationSuggestionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void bindSuggestion(AggregationSuggestionEngine.Suggestion suggestion) {
        this.mSuggestion = suggestion;
        ContactPhotoManager.DefaultImageRequest defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(suggestion.name, String.valueOf(suggestion.rawContactId), false);
        ContactPhotoManager.getInstance(getContext()).loadThumbnail((ImageView) findViewById(R.id.aggregation_suggestion_photo), suggestion.photoId, false, false, defaultImageRequest);
        ((TextView) findViewById(R.id.aggregation_suggestion_name)).setText(suggestion.name);
        TextView textView = (TextView) findViewById(R.id.aggregation_suggestion_data);
        String str = suggestion.nickname;
        if (str == null && (str = suggestion.emailAddress) == null) {
            str = suggestion.phoneNumber;
            if (str != null) {
                textView.setTextDirection(3);
            } else {
                str = null;
            }
        }
        textView.setText(str);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public boolean handleItemClickEvent() {
        if (this.mListener == null || !isEnabled() || TextUtils.isEmpty(this.mSuggestion.contactLookupKey)) {
            return false;
        }
        Listener listener = this.mListener;
        AggregationSuggestionEngine.Suggestion suggestion = this.mSuggestion;
        listener.onEditAction(ContactsContract.Contacts.getLookupUri(suggestion.contactId, suggestion.contactLookupKey), this.mSuggestion.rawContactId);
        return true;
    }
}
