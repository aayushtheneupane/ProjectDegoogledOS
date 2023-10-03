package com.android.dialer.speeddial;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.glidephotomanager.GlidePhotoManager;
import com.android.dialer.glidephotomanager.GlidePhotoManagerComponent;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl;
import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.dialer.speeddial.draghelper.SpeedDialFavoritesViewHolderOnTouchListener;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;

public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, SpeedDialFavoritesViewHolderOnTouchListener.OnTouchFinishCallback {
    private final FrameLayout avatarContainer;
    private final FavoriteContactsListener listener;
    private final TextView nameView;
    private final TextView phoneType;
    private final QuickContactBadge photoView;
    private SpeedDialUiItem speedDialUiItem;
    private final FrameLayout videoCallIcon;

    public interface FavoriteContactsListener {
        void onAmbiguousContactClicked(SpeedDialUiItem speedDialUiItem);

        void onClick(SpeedDialEntry.Channel channel);

        void onRequestRemove(SpeedDialUiItem speedDialUiItem);

        void onTouchFinished(boolean z);

        void showContextMenu(View view, SpeedDialUiItem speedDialUiItem);
    }

    public FavoritesViewHolder(View view, ItemTouchHelper itemTouchHelper, FavoriteContactsListener favoriteContactsListener) {
        super(view);
        this.photoView = (QuickContactBadge) view.findViewById(R.id.avatar);
        this.nameView = (TextView) view.findViewById(R.id.name);
        this.phoneType = (TextView) view.findViewById(R.id.phone_type);
        this.videoCallIcon = (FrameLayout) view.findViewById(R.id.video_call_container);
        this.avatarContainer = (FrameLayout) view.findViewById(R.id.avatar_container);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        view.setOnTouchListener(new SpeedDialFavoritesViewHolderOnTouchListener(ViewConfiguration.get(view.getContext()), itemTouchHelper, this, this));
        this.photoView.setClickable(false);
        this.listener = favoriteContactsListener;
    }

    public void bind(Context context, SpeedDialUiItem speedDialUiItem2) {
        Assert.isNotNull(speedDialUiItem2);
        this.speedDialUiItem = speedDialUiItem2;
        Assert.checkArgument(speedDialUiItem2.isStarred());
        this.nameView.setText(speedDialUiItem2.name());
        SpeedDialEntry.Channel defaultChannel = speedDialUiItem2.defaultChannel();
        if (defaultChannel == null) {
            defaultChannel = speedDialUiItem2.getDefaultVoiceChannel();
        }
        int i = 8;
        if (defaultChannel != null) {
            this.phoneType.setText(defaultChannel.label());
            FrameLayout frameLayout = this.videoCallIcon;
            if (defaultChannel.isVideoTechnology()) {
                i = 0;
            }
            frameLayout.setVisibility(i);
        } else {
            this.phoneType.setText("");
            this.videoCallIcon.setVisibility(8);
        }
        GlidePhotoManager glidePhotoManager = GlidePhotoManagerComponent.get(context).glidePhotoManager();
        QuickContactBadge quickContactBadge = this.photoView;
        PhotoInfo.Builder newBuilder = PhotoInfo.newBuilder();
        newBuilder.setPhotoId(speedDialUiItem2.photoId());
        newBuilder.setPhotoUri(speedDialUiItem2.photoUri());
        newBuilder.setName(speedDialUiItem2.name());
        newBuilder.setLookupUri(ContactsContract.Contacts.getLookupUri(speedDialUiItem2.contactId(), speedDialUiItem2.lookupKey()).toString());
        ((GlidePhotoManagerImpl) glidePhotoManager).loadQuickContactBadge(quickContactBadge, (PhotoInfo) newBuilder.build());
    }

    /* access modifiers changed from: package-private */
    public FrameLayout getAvatarContainer() {
        return this.avatarContainer;
    }

    public void onClick(View view) {
        if (this.speedDialUiItem.defaultChannel() != null) {
            this.listener.onClick(this.speedDialUiItem.defaultChannel());
        } else {
            this.listener.onAmbiguousContactClicked(this.speedDialUiItem);
        }
    }

    public boolean onLongClick(View view) {
        this.listener.showContextMenu(this.photoView, this.speedDialUiItem);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onSelectedChanged(boolean z) {
        int i = 8;
        this.nameView.setVisibility(z ? 8 : 0);
        TextView textView = this.phoneType;
        if (!z) {
            i = 0;
        }
        textView.setVisibility(i);
    }

    public void onTouchFinished(boolean z) {
        this.listener.onTouchFinished(z);
    }
}
