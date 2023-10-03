package com.android.dialer.voicemail.listui;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.time.Clock;
import com.android.dialer.voicemail.model.VoicemailEntry;
import com.android.dialer.widget.ContactPhotoView;

final class NewVoicemailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Clock clock;
    private final ContactPhotoView contactPhotoView;
    private final Context context;
    private boolean isViewHolderExpanded = false;
    private final NewVoicemailMediaPlayerView mediaPlayerView;
    private final ImageView menuButton;
    private final TextView primaryTextView;
    private final TextView secondaryTextView;
    private final TextView transcriptionBrandingTextView;
    private final TextView transcriptionTextView;
    private long viewHolderId = -1;
    private Uri viewHolderVoicemailUri = null;
    private VoicemailEntry voicemailEntryOfViewHolder;
    private final NewVoicemailViewHolderListener voicemailViewHolderListener;

    interface NewVoicemailViewHolderListener {
    }

    NewVoicemailViewHolder(View view, Clock clock2, NewVoicemailViewHolderListener newVoicemailViewHolderListener) {
        super(view);
        LogUtil.enterBlock("NewVoicemailViewHolder");
        this.context = view.getContext();
        this.primaryTextView = (TextView) view.findViewById(R.id.primary_text);
        this.secondaryTextView = (TextView) view.findViewById(R.id.secondary_text);
        this.transcriptionTextView = (TextView) view.findViewById(R.id.transcription_text);
        this.transcriptionBrandingTextView = (TextView) view.findViewById(R.id.transcription_branding);
        this.contactPhotoView = (ContactPhotoView) view.findViewById(R.id.contact_photo_view);
        this.mediaPlayerView = (NewVoicemailMediaPlayerView) view.findViewById(R.id.new_voicemail_media_player);
        this.menuButton = (ImageView) view.findViewById(R.id.menu_button);
        this.clock = clock2;
        this.voicemailViewHolderListener = newVoicemailViewHolderListener;
    }

    /* access modifiers changed from: private */
    public Integer markVoicemailAsRead(Pair<Context, Uri> pair) {
        Assert.isWorkerThread();
        LogUtil.enterBlock("NewVoicemailAdapter.markVoicemailAsRead");
        Uri uri = (Uri) pair.second;
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_read", true);
        contentValues.put("dirty", 1);
        LogUtil.m9i("NewVoicemailAdapter.markVoicemailAsRead", "marking as read uri:%s", String.valueOf(uri));
        return Integer.valueOf(((Context) pair.first).getContentResolver().update(uri, contentValues, (String) null, (String[]) null));
    }

    /* access modifiers changed from: private */
    public void onVoicemailMarkedAsRead(Integer num) {
        boolean z = true;
        LogUtil.m9i("NewVoicemailAdapter.markVoicemailAsRead", "return value:%d", num);
        if (num.intValue() <= 0) {
            z = false;
        }
        Assert.checkArgument(z, "marking voicemail read was not successful", new Object[0]);
        Intent intent = new Intent("com.android.voicemail.VoicemailClient.ACTION_UPLOAD");
        intent.setPackage(this.context.getPackageName());
        this.context.sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    public void bindViewHolderValuesFromAdapter(Cursor cursor, FragmentManager fragmentManager, NewVoicemailMediaPlayer newVoicemailMediaPlayer, int i, long j) {
        boolean z = false;
        LogUtil.m9i("NewVoicemailViewHolder.bindViewHolderValuesFromAdapter", "view holder at pos:%d, adapterPos:%d, cursorPos:%d, cursorSize:%d", Integer.valueOf(i), Integer.valueOf(getAdapterPosition()), Integer.valueOf(cursor.getPosition()), Integer.valueOf(cursor.getCount()));
        this.voicemailEntryOfViewHolder = VoicemailCursorLoader.toVoicemailEntry(cursor);
        this.viewHolderId = this.voicemailEntryOfViewHolder.getId();
        LogUtil.m9i("NewVoicemailViewHolder.bindViewHolderValuesFromAdapter", "viewholderId:%d", Long.valueOf(this.viewHolderId));
        this.viewHolderVoicemailUri = Uri.parse(this.voicemailEntryOfViewHolder.getVoicemailUri());
        TextView textView = this.primaryTextView;
        Context context2 = this.context;
        VoicemailEntry voicemailEntry = this.voicemailEntryOfViewHolder;
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(voicemailEntry.getNumberAttributes().getName())) {
            sb.append(voicemailEntry.getNumberAttributes().getName());
        } else if (!TextUtils.isEmpty(voicemailEntry.getFormattedNumber())) {
            sb.append(voicemailEntry.getFormattedNumber());
        } else {
            sb.append(context2.getText(R.string.voicemail_entry_unknown));
        }
        textView.setText(sb.toString());
        TextView textView2 = this.secondaryTextView;
        Context context3 = this.context;
        Clock clock2 = this.clock;
        VoicemailEntry voicemailEntry2 = this.voicemailEntryOfViewHolder;
        StringBuilder sb2 = new StringBuilder();
        String geocodedLocation = voicemailEntry2.getGeocodedLocation();
        if (!TextUtils.isEmpty(geocodedLocation)) {
            sb2.append(geocodedLocation);
        }
        if (sb2.length() > 0) {
            sb2.append(" • ");
        }
        String str = " • ";
        StringBuilder sb3 = sb2;
        sb3.append(CallLogDates.newCallLogTimestampLabel(context3, clock2.currentTimeMillis(), voicemailEntry2.getTimestamp(), true));
        if (voicemailEntry2.getDuration() >= 0) {
            sb3.append(str);
            sb3.append(VoicemailEntryText.getVoicemailDuration(context3, voicemailEntry2));
        }
        textView2.setText(sb3.toString());
        String transcription = this.voicemailEntryOfViewHolder.getTranscription();
        if (TextUtils.isEmpty(transcription)) {
            this.transcriptionTextView.setVisibility(8);
            this.transcriptionTextView.setText((CharSequence) null);
        } else {
            this.transcriptionTextView.setVisibility(0);
            this.transcriptionTextView.setText(transcription);
        }
        Object[] objArr = {Long.valueOf(this.voicemailEntryOfViewHolder.getId()), Integer.valueOf(this.voicemailEntryOfViewHolder.getIsRead())};
        if (this.voicemailEntryOfViewHolder.getIsRead() == 0) {
            this.primaryTextView.setTypeface(Typeface.DEFAULT, 1);
            this.secondaryTextView.setTypeface(Typeface.DEFAULT, 1);
            this.transcriptionTextView.setTypeface(Typeface.DEFAULT, 1);
        }
        this.itemView.setOnClickListener(this);
        this.menuButton.setOnClickListener(new View.OnClickListener(this.context, this.voicemailEntryOfViewHolder) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ VoicemailEntry f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                NewVoicemailMenu.lambda$createOnClickListener$0(this.f$0, this.f$1, view);
            }
        });
        this.contactPhotoView.setPhoto((PhotoInfo) CallLogDates.fromVoicemailEntry(this.voicemailEntryOfViewHolder).build());
        long j2 = this.viewHolderId;
        if (j2 == j) {
            LogUtil.m9i("NewVoicemailViewHolder.bindViewHolderValuesFromAdapter", "viewHolderId:%d is expanded, update its mediaplayer view", Long.valueOf(j2));
            expandAndBindViewHolderAndMediaPlayerViewWithAdapterValues(this.voicemailEntryOfViewHolder, fragmentManager, newVoicemailMediaPlayer, this.voicemailViewHolderListener);
            LogUtil.m9i("NewVoicemailViewHolder.bindViewHolderValuesFromAdapter", "After 2nd updating the MPPlayerView: viewHolderId:%d, uri:%s, MediaplayerView(after updated):%s, adapter position passed down:%d, getAdapterPos:%d", Long.valueOf(this.viewHolderId), String.valueOf(this.viewHolderVoicemailUri), String.valueOf(this.mediaPlayerView.getVoicemailUri()), Integer.valueOf(i), Integer.valueOf(getAdapterPosition()));
            Assert.checkArgument(this.mediaPlayerView.getVisibility() == 0, "a expanded viewholder should have its media player view visible", new Object[0]);
        } else {
            LogUtil.m9i("NewVoicemailViewHolder.bindViewHolderValuesFromAdapter", "viewHolderId:%d is not the expanded one, collapse it and don't update the MpView", Long.valueOf(j2));
            collapseViewHolder();
            Assert.checkArgument(this.mediaPlayerView.getVisibility() == 8, "a collapsed viewholder should not have its media player view visible", new Object[0]);
        }
        Object[] objArr2 = new Object[6];
        objArr2[0] = Long.valueOf(this.viewHolderId);
        objArr2[1] = String.valueOf(this.viewHolderVoicemailUri);
        objArr2[2] = String.valueOf(this.mediaPlayerView.getVoicemailUri());
        objArr2[3] = Integer.valueOf(i);
        objArr2[4] = Integer.valueOf(getAdapterPosition());
        if (this.mediaPlayerView.getVisibility() == 0) {
            z = true;
        }
        objArr2[5] = Boolean.valueOf(z);
        LogUtil.m9i("NewVoicemailViewHolder.bindViewHolderValuesFromAdapter", "Final value after updating: viewHolderId:%d, uri:%s, MediaplayerView(not updated):%s, adapter position passed down:%d, getAdapterPos:%d, MPPlayerVisibility:%b", objArr2);
    }

    public void clickPlayButtonOfViewHoldersMediaPlayerView(NewVoicemailViewHolder newVoicemailViewHolder) {
        boolean z = true;
        LogUtil.m9i("NewVoicemailViewHolder.clickPlayButtonOfViewHoldersMediaPlayerView", "expandedViewHolderID:%d", Long.valueOf(newVoicemailViewHolder.getViewHolderId()));
        Assert.checkArgument(this.mediaPlayerView.getVoicemailUri().equals(newVoicemailViewHolder.getViewHolderVoicemailUri()));
        Assert.checkArgument(newVoicemailViewHolder.getViewHolderVoicemailUri().equals(getViewHolderVoicemailUri()));
        if (this.mediaPlayerView.getVisibility() != 0) {
            z = false;
        }
        Assert.checkArgument(z, "the media player must be visible for viewholder id:%d, before we attempt to play", new Object[0]);
        this.mediaPlayerView.clickPlayButton();
    }

    /* access modifiers changed from: package-private */
    public void collapseViewHolder() {
        LogUtil.m9i("NewVoicemailViewHolder.collapseViewHolder", "viewHolderId:%d is being collapsed, its MPViewUri:%s, its Uri is :%s", Long.valueOf(this.viewHolderId), String.valueOf(this.mediaPlayerView.getVoicemailUri()), String.valueOf(this.viewHolderVoicemailUri));
        this.transcriptionTextView.setMaxLines(1);
        this.transcriptionBrandingTextView.setVisibility(8);
        this.isViewHolderExpanded = false;
        this.mediaPlayerView.reset();
        this.mediaPlayerView.setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void expandAndBindViewHolderAndMediaPlayerViewWithAdapterValues(VoicemailEntry voicemailEntry, FragmentManager fragmentManager, NewVoicemailMediaPlayer newVoicemailMediaPlayer, NewVoicemailViewHolderListener newVoicemailViewHolderListener) {
        Assert.isNotNull(newVoicemailViewHolderListener);
        Assert.checkArgument(voicemailEntry.getId() == this.viewHolderId, "ensure that the adapter binding has taken place", new Object[0]);
        Assert.checkArgument(Uri.parse(voicemailEntry.getVoicemailUri()).equals(this.viewHolderVoicemailUri), "ensure that the adapter binding has taken place", new Object[0]);
        LogUtil.m9i("NewVoicemailViewHolder.expandAndBindViewHolderAndMediaPlayerViewWithAdapterValues", "voicemail id: %d, value of isViewHolderExpanded:%b, before setting it to be true, and value of ViewholderUri:%s, MPView:%s, VoicemailRead:%d, before updating it", Long.valueOf(this.viewHolderId), Boolean.valueOf(this.isViewHolderExpanded), String.valueOf(this.viewHolderVoicemailUri), String.valueOf(this.mediaPlayerView.getVoicemailUri()), Integer.valueOf(voicemailEntry.getIsRead()));
        if (voicemailEntry.getIsRead() == 0) {
            this.primaryTextView.setTypeface(Typeface.DEFAULT, 0);
            this.secondaryTextView.setTypeface(Typeface.DEFAULT, 0);
            this.transcriptionTextView.setTypeface(Typeface.DEFAULT, 0);
            Uri parse = Uri.parse(voicemailEntry.getVoicemailUri());
            FragmentManager fragmentManager2 = fragmentManager;
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(this.context).dialerExecutorFactory()).createUiTaskBuilder(fragmentManager, "mark_voicemail_read", new DialerExecutor.Worker() {
                public final Object doInBackground(Object obj) {
                    return NewVoicemailViewHolder.this.markVoicemailAsRead((Pair) obj);
                }
            }).onSuccess(new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    NewVoicemailViewHolder.this.onVoicemailMarkedAsRead((Integer) obj);
                }
            }).build().executeSerial(new Pair(this.context, parse));
        } else {
            FragmentManager fragmentManager3 = fragmentManager;
        }
        this.transcriptionTextView.setMaxLines(999);
        this.isViewHolderExpanded = true;
        if (voicemailEntry.getTranscriptionState() != 3 || TextUtils.isEmpty(voicemailEntry.getTranscription())) {
            this.transcriptionBrandingTextView.setVisibility(8);
        } else {
            this.transcriptionBrandingTextView.setVisibility(0);
        }
        this.mediaPlayerView.setVisibility(0);
        this.mediaPlayerView.bindValuesFromAdapterOfExpandedViewHolderMediaPlayerView(this, voicemailEntry, fragmentManager, newVoicemailMediaPlayer, newVoicemailViewHolderListener);
        LogUtil.m9i("NewVoicemailViewHolder.expandAndBindViewHolderAndMediaPlayerViewWithAdapterValues", "voicemail id: %d, value of isViewHolderExpanded:%b, after setting it to be true, and value of ViewholderUri:%s, MPView:%s, after updating it", Long.valueOf(this.viewHolderId), Boolean.valueOf(this.isViewHolderExpanded), String.valueOf(this.viewHolderVoicemailUri), String.valueOf(this.mediaPlayerView.getVoicemailUri()));
    }

    public NewVoicemailMediaPlayerView getMediaPlayerView() {
        NewVoicemailMediaPlayerView newVoicemailMediaPlayerView = this.mediaPlayerView;
        Assert.isNotNull(newVoicemailMediaPlayerView);
        return newVoicemailMediaPlayerView;
    }

    public long getViewHolderId() {
        return this.viewHolderId;
    }

    public Uri getViewHolderVoicemailUri() {
        return this.viewHolderVoicemailUri;
    }

    /* access modifiers changed from: package-private */
    public boolean isViewHolderExpanded() {
        return this.isViewHolderExpanded;
    }

    public void onClick(View view) {
        LogUtil.m9i("NewVoicemailViewHolder.onClick", "voicemail id: %d, isViewHolderCurrentlyExpanded:%b", Long.valueOf(this.viewHolderId), Boolean.valueOf(this.isViewHolderExpanded));
        if (this.isViewHolderExpanded) {
            ((NewVoicemailAdapter) this.voicemailViewHolderListener).collapseExpandedViewHolder(this);
            return;
        }
        NewVoicemailViewHolderListener newVoicemailViewHolderListener = this.voicemailViewHolderListener;
        VoicemailEntry voicemailEntry = this.voicemailEntryOfViewHolder;
        Assert.isNotNull(voicemailEntry);
        NewVoicemailViewHolderListener newVoicemailViewHolderListener2 = this.voicemailViewHolderListener;
        Assert.isNotNull(newVoicemailViewHolderListener2);
        ((NewVoicemailAdapter) newVoicemailViewHolderListener).expandViewHolderFirstTimeAndCollapseAllOtherVisibleViewHolders(this, voicemailEntry, newVoicemailViewHolderListener2);
    }

    public void reset() {
        LogUtil.m9i("NewVoicemailViewHolder.reset()", "Reset the viewholder, currently viewHolderId:%d, uri:%s, isViewHolderExpanded:%b, its MediaPlayerViewUri:%s", Long.valueOf(this.viewHolderId), String.valueOf(this.viewHolderVoicemailUri), Boolean.valueOf(this.isViewHolderExpanded), String.valueOf(this.mediaPlayerView.getVoicemailUri()));
        this.viewHolderId = -1;
        this.isViewHolderExpanded = false;
        this.viewHolderVoicemailUri = null;
        this.primaryTextView.setTypeface((Typeface) null, 0);
        this.secondaryTextView.setTypeface((Typeface) null, 0);
        this.transcriptionTextView.setTypeface((Typeface) null, 0);
        this.transcriptionBrandingTextView.setVisibility(8);
        this.mediaPlayerView.reset();
        LogUtil.m9i("NewVoicemailViewHolder.reset()", "Reset the viewholder, after resetting viewHolderId:%d, uri:%s, isViewHolderExpanded:%b", Long.valueOf(this.viewHolderId), String.valueOf(this.viewHolderVoicemailUri), Boolean.valueOf(this.isViewHolderExpanded));
    }

    public void setMediaPlayerViewToResetState(NewVoicemailViewHolder newVoicemailViewHolder, NewVoicemailMediaPlayer newVoicemailMediaPlayer) {
        Assert.isNotNull(newVoicemailViewHolder);
        this.mediaPlayerView.setToResetState(newVoicemailViewHolder, newVoicemailMediaPlayer);
    }

    public void setPausedStateOfMediaPlayerView(Uri uri, NewVoicemailMediaPlayer newVoicemailMediaPlayer) {
        Assert.checkArgument(this.viewHolderVoicemailUri.equals(uri));
        Assert.checkArgument(this.mediaPlayerView.getVoicemailUri().equals(uri));
        Assert.checkArgument(this.mediaPlayerView.getVoicemailUri().equals(this.viewHolderVoicemailUri));
        this.mediaPlayerView.setToPausedState(uri, newVoicemailMediaPlayer);
    }

    public void updateMediaPlayerViewWithPlayingState(NewVoicemailViewHolder newVoicemailViewHolder, NewVoicemailMediaPlayer newVoicemailMediaPlayer) {
        LogUtil.m9i("NewVoicemailViewHolder.updateMediaPlayerViewWithPlayingState", "viewholderUri:%s, mediaPlayerViewUri:%s, MPPosition:%d, MpDuration:%d, MpIsPlaying:%b", newVoicemailViewHolder.getViewHolderVoicemailUri().toString(), this.mediaPlayerView.getVoicemailUri().toString(), Integer.valueOf(newVoicemailMediaPlayer.getCurrentPosition()), Integer.valueOf(newVoicemailMediaPlayer.getDuration()), Boolean.valueOf(newVoicemailMediaPlayer.isPlaying()));
        Assert.checkArgument(newVoicemailMediaPlayer.isPlaying(), "this method is only called when we are certain that the media player is playing", new Object[0]);
        LogUtil.m9i("NewVoicemailViewHolder.updateMediaPlayerViewWithPlayingState", "viewholderUri:%s, mediaPlayerViewUri:%s", newVoicemailViewHolder.getViewHolderVoicemailUri().toString(), this.mediaPlayerView.getVoicemailUri().toString());
        Assert.checkArgument(newVoicemailViewHolder.getViewHolderVoicemailUri().equals(this.mediaPlayerView.getVoicemailUri()), "the mediaplayer view must be that of the viewholder we are updating", new Object[0]);
        Assert.checkArgument(newVoicemailMediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(newVoicemailMediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri()), "the media player view we are attempting to update should be of the currently prepared and playing voicemail", new Object[0]);
        this.mediaPlayerView.updateSeekBarDurationAndShowPlayButton(newVoicemailMediaPlayer);
    }
}
