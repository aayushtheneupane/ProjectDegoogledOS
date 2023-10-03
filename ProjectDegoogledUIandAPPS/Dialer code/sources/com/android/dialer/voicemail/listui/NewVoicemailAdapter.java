package com.android.dialer.voicemail.listui;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.p002v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.time.Clock;
import com.android.dialer.voicemail.listui.NewVoicemailAdapter;
import com.android.dialer.voicemail.listui.NewVoicemailViewHolder;
import com.android.dialer.voicemail.listui.error.OmtpVoicemailMessageCreator;
import com.android.dialer.voicemail.listui.error.VoicemailErrorMessage;
import com.android.dialer.voicemail.listui.error.VoicemailStatus;
import com.android.dialer.voicemail.listui.error.VoicemailStatusReader;
import com.android.dialer.voicemail.model.VoicemailEntry;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

final class NewVoicemailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NewVoicemailViewHolder.NewVoicemailViewHolderListener {
    private final Clock clock;
    /* access modifiers changed from: private */
    public long currentlyExpandedViewHolderId = -1;
    private Cursor cursor;
    Set<Integer> deletedVoicemailPosition = new ArraySet();
    private final FragmentManager fragmentManager;
    /* access modifiers changed from: private */
    public final NewVoicemailMediaPlayer mediaPlayer = new NewVoicemailMediaPlayer(new MediaPlayer());
    private final ArrayMap<Long, NewVoicemailViewHolder> newVoicemailViewHolderArrayMap = new ArrayMap<>();
    private final Set<NewVoicemailViewHolder> newVoicemailViewHolderSet = new ArraySet();
    private int olderHeaderPosition = Integer.MAX_VALUE;
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            Assert.checkArgument(NewVoicemailAdapter.this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(NewVoicemailAdapter.this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri()));
            boolean z = true;
            Assert.checkArgument(!NewVoicemailAdapter.this.mediaPlayer.isPlaying());
            LogUtil.m9i("NewVoicemailAdapter.onCompletionListener", "completed playing voicemailUri: %s, expanded viewholder is %d, visibility :%b", NewVoicemailAdapter.this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().toString(), Long.valueOf(NewVoicemailAdapter.this.currentlyExpandedViewHolderId), Boolean.valueOf(NewVoicemailAdapter.this.isCurrentlyExpandedViewHolderInViewHolderSet()));
            if (NewVoicemailAdapter.this.currentlyExpandedViewHolderId == -1) {
                z = false;
            }
            Assert.checkArgument(z, "a voicemail that was never expanded, should never be playing.", new Object[0]);
            NewVoicemailAdapter.this.mediaPlayer.reset();
        }
    };
    private final MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            LogUtil.m8e("NewVoicemailAdapter.onError", "onError, what:%d, extra:%d", Integer.valueOf(i), Integer.valueOf(i2));
            Assert.checkArgument(NewVoicemailAdapter.this.mediaPlayer.getMediaPlayer().equals(mediaPlayer), "there should always only be one instance of the media player", new Object[0]);
            Assert.checkArgument(NewVoicemailAdapter.this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(NewVoicemailAdapter.this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri()));
            LogUtil.m9i("NewVoicemailAdapter.onErrorListener", "error playing voicemailUri: %s", NewVoicemailAdapter.this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().toString());
            return false;
        }
    };
    private final MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        public void onPrepared(MediaPlayer mediaPlayer) {
            boolean z = true;
            LogUtil.m9i("NewVoicemailAdapter.onPrepared", "MPPreparedUri: %s, currentlyExpandedViewHolderId:%d, and its visibility on the screen is:%b", String.valueOf(NewVoicemailAdapter.this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri()), Long.valueOf(NewVoicemailAdapter.this.currentlyExpandedViewHolderId), Boolean.valueOf(NewVoicemailAdapter.this.isCurrentlyExpandedViewHolderInViewHolderSet()));
            NewVoicemailViewHolder access$400 = NewVoicemailAdapter.this.getCurrentlyExpandedViewHolder();
            if (access$400 == null) {
                z = false;
            }
            Assert.checkArgument(z);
            Assert.checkArgument(access$400.getViewHolderVoicemailUri().equals(NewVoicemailAdapter.this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri()), "should only have prepared the last expanded view holder.", new Object[0]);
            NewVoicemailAdapter.this.mediaPlayer.start(NewVoicemailAdapter.this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri());
            NewVoicemailAdapter.this.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder(access$400);
            Assert.checkArgument(NewVoicemailAdapter.this.mediaPlayer.isPlaying());
            LogUtil.m9i("NewVoicemailAdapter.onPrepared", "voicemail should be playing", new Object[0]);
        }
    };
    private int todayHeaderPosition = Integer.MAX_VALUE;
    private int voicemailAlertPosition = Integer.MAX_VALUE;
    private VoicemailErrorMessage voicemailErrorMessage;
    private int yesterdayHeaderPosition = Integer.MAX_VALUE;

    NewVoicemailAdapter(Cursor cursor2, Clock clock2, FragmentManager fragmentManager2) {
        LogUtil.enterBlock("NewVoicemailAdapter");
        this.cursor = cursor2;
        this.clock = clock2;
        this.fragmentManager = fragmentManager2;
        this.mediaPlayer.setOnCompletionListener(this.onCompletionListener);
        this.mediaPlayer.setOnPreparedListener(this.onPreparedListener);
        this.mediaPlayer.setOnErrorListener(this.onErrorListener);
        updateHeaderPositions();
    }

    /* access modifiers changed from: private */
    public NewVoicemailViewHolder getCurrentlyExpandedViewHolder() {
        boolean z = true;
        if (this.newVoicemailViewHolderArrayMap.containsKey(Long.valueOf(this.currentlyExpandedViewHolderId))) {
            if (this.newVoicemailViewHolderArrayMap.get(Long.valueOf(this.currentlyExpandedViewHolderId)).getViewHolderId() != this.currentlyExpandedViewHolderId) {
                z = false;
            }
            Assert.checkArgument(z);
            return this.newVoicemailViewHolderArrayMap.get(Long.valueOf(this.currentlyExpandedViewHolderId));
        }
        LogUtil.m9i("NewVoicemailAdapter.getCurrentlyExpandedViewHolder", "no view holder found in hashmap size:%d for %d", Integer.valueOf(this.newVoicemailViewHolderArrayMap.size()), Long.valueOf(this.currentlyExpandedViewHolderId));
        printHashSet();
        printArrayMap();
        return null;
    }

    /* access modifiers changed from: private */
    public boolean isCurrentlyExpandedViewHolderInViewHolderSet() {
        for (NewVoicemailViewHolder viewHolderId : this.newVoicemailViewHolderSet) {
            if (viewHolderId.getViewHolderId() == this.currentlyExpandedViewHolderId) {
                return true;
            }
        }
        return false;
    }

    private void printArrayMap() {
        LogUtil.m9i("NewVoicemailAdapter.printArrayMap", "hashMapSize: %d, currentlyExpandedViewHolderId:%d", Integer.valueOf(this.newVoicemailViewHolderArrayMap.size()), Long.valueOf(this.currentlyExpandedViewHolderId));
        if (!this.newVoicemailViewHolderArrayMap.isEmpty()) {
            String str = "";
            for (Long longValue : this.newVoicemailViewHolderArrayMap.keySet()) {
                str = str + longValue.longValue() + " ";
            }
            LogUtil.m9i("NewVoicemailAdapter.printArrayMap", GeneratedOutlineSupport.outline8("ids are ", str), new Object[0]);
        }
    }

    private void printHashSet() {
        LogUtil.m9i("NewVoicemailAdapter.printHashSet", "hashSetSize: %d, currentlyExpandedViewHolderId:%d", Integer.valueOf(this.newVoicemailViewHolderSet.size()), Long.valueOf(this.currentlyExpandedViewHolderId));
        if (!this.newVoicemailViewHolderSet.isEmpty()) {
            String str = "";
            for (NewVoicemailViewHolder viewHolderId : this.newVoicemailViewHolderSet) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
                outline13.append(viewHolderId.getViewHolderId());
                outline13.append(" ");
                str = outline13.toString();
            }
            LogUtil.m9i("NewVoicemailAdapter.printHashSet", GeneratedOutlineSupport.outline8("ids are ", str), new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void recursivelyUpdateMediaPlayerViewOfExpandedViewHolder(final NewVoicemailViewHolder newVoicemailViewHolder) {
        LogUtil.m9i("NewVoicemailAdapter.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder", "currentlyExpanded:%d", Long.valueOf(this.currentlyExpandedViewHolderId));
        if (getCurrentlyExpandedViewHolder() == null) {
            LogUtil.m9i("NewVoicemailAdapter.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder", "viewholder:%d media player view, no longer on screen, no need to update", Long.valueOf(newVoicemailViewHolder.getViewHolderId()));
        } else if (!getCurrentlyExpandedViewHolder().equals(newVoicemailViewHolder)) {
            LogUtil.m9i("NewVoicemailAdapter.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder", "currentlyExpandedViewHolderId:%d and the one we are attempting to update:%d aren't the same.", Long.valueOf(this.currentlyExpandedViewHolderId), Long.valueOf(newVoicemailViewHolder.getViewHolderId()));
        } else {
            Assert.checkArgument(newVoicemailViewHolder.isViewHolderExpanded());
            Assert.checkArgument(newVoicemailViewHolder.getViewHolderId() == getCurrentlyExpandedViewHolder().getViewHolderId());
            if (this.mediaPlayer.isPaused()) {
                Assert.checkArgument(newVoicemailViewHolder.getViewHolderVoicemailUri().equals(this.mediaPlayer.getLastPausedVoicemailUri()), "only the expanded viewholder can be paused.", new Object[0]);
                LogUtil.m9i("NewVoicemailAdapter.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder", "set the media player to a paused state", new Object[0]);
                newVoicemailViewHolder.setPausedStateOfMediaPlayerView(newVoicemailViewHolder.getViewHolderVoicemailUri(), this.mediaPlayer);
            } else if (this.mediaPlayer.isPlaying() && this.mediaPlayer.getCurrentPosition() < this.mediaPlayer.getDuration()) {
                Assert.checkArgument(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(getCurrentlyExpandedViewHolder().getViewHolderVoicemailUri()));
                LogUtil.m9i("NewVoicemailAdapter.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder", "recursely update the player, currentlyExpanded:%d", Long.valueOf(newVoicemailViewHolder.getViewHolderId()));
                Assert.checkArgument(newVoicemailViewHolder.getViewHolderVoicemailUri().equals(getCurrentlyExpandedViewHolder().getViewHolderVoicemailUri()));
                newVoicemailViewHolder.updateMediaPlayerViewWithPlayingState(newVoicemailViewHolder, this.mediaPlayer);
                DialerExecutorModule.postDelayedOnUiThread(new Runnable() {
                    public void run() {
                        NewVoicemailAdapter.this.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder(newVoicemailViewHolder);
                    }
                }, 33);
            } else if (!this.mediaPlayer.isPlaying() || (this.mediaPlayer.isPlaying() && this.mediaPlayer.getCurrentPosition() > this.mediaPlayer.getDuration())) {
                LogUtil.m9i("NewVoicemailAdapter.recursivelyUpdateMediaPlayerViewOfExpandedViewHolder", "resetting the player, currentlyExpanded:%d, MPPlaying:%b", Long.valueOf(getCurrentlyExpandedViewHolder().getViewHolderId()), Boolean.valueOf(this.mediaPlayer.isPlaying()));
                this.mediaPlayer.reset();
                Assert.checkArgument(newVoicemailViewHolder.getViewHolderVoicemailUri().equals(getCurrentlyExpandedViewHolder().getViewHolderVoicemailUri()));
                newVoicemailViewHolder.setMediaPlayerViewToResetState(newVoicemailViewHolder, this.mediaPlayer);
            } else {
                throw new AssertionError(GeneratedOutlineSupport.outline8("All cases should have been handled before. Error ", String.format("expandedViewHolderPossiblyPlaying:%d, expanded:%b, CurrentExpanded:%d, uri:%s, MPPlaying:%b, MPPaused:%b, MPPreparedUri:%s, MPPausedUri:%s", new Object[]{Long.valueOf(newVoicemailViewHolder.getViewHolderId()), Boolean.valueOf(newVoicemailViewHolder.isViewHolderExpanded()), Long.valueOf(this.currentlyExpandedViewHolderId), String.valueOf(newVoicemailViewHolder.getViewHolderVoicemailUri()), Boolean.valueOf(this.mediaPlayer.isPlaying()), Boolean.valueOf(this.mediaPlayer.isPaused()), String.valueOf(this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri()), String.valueOf(this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri())})));
            }
        }
    }

    private void updateHeaderPositions() {
        int i;
        LogUtil.m9i("NewVoicemailAdapter.updateHeaderPositions", "before updating todayPos:%d, yestPos:%d, olderPos:%d, alertPos:%d", Integer.valueOf(this.todayHeaderPosition), Integer.valueOf(this.yesterdayHeaderPosition), Integer.valueOf(this.olderHeaderPosition), Integer.valueOf(this.voicemailAlertPosition));
        int i2 = Integer.MAX_VALUE;
        if (!this.cursor.moveToFirst()) {
            this.todayHeaderPosition = Integer.MAX_VALUE;
            this.yesterdayHeaderPosition = Integer.MAX_VALUE;
            this.olderHeaderPosition = Integer.MAX_VALUE;
            return;
        }
        long currentTimeMillis = this.clock.currentTimeMillis();
        int i3 = 0;
        int i4 = 0;
        do {
            long dayDifference = (long) CallLogDates.getDayDifference(currentTimeMillis, VoicemailCursorLoader.getTimestamp(this.cursor));
            if (dayDifference != 0) {
                if (dayDifference != 1) {
                    break;
                }
                i4++;
            } else {
                i3++;
            }
        } while (this.cursor.moveToNext());
        if (i3 > 0) {
            i3++;
        }
        if (i4 > 0) {
            i4++;
        }
        int i5 = this.voicemailAlertPosition;
        if (i5 != Integer.MAX_VALUE) {
            Assert.checkArgument(i5 == 0, "voicemail alert can only be 0, when showing", new Object[0]);
            i = 1;
        } else {
            i = 0;
        }
        this.todayHeaderPosition = i3 > 0 ? i : Integer.MAX_VALUE;
        this.yesterdayHeaderPosition = i4 > 0 ? i3 + i : Integer.MAX_VALUE;
        if (!this.cursor.isAfterLast()) {
            i2 = i3 + i4 + i;
        }
        this.olderHeaderPosition = i2;
        LogUtil.m9i("NewVoicemailAdapter.updateHeaderPositions", "after updating todayPos:%d, yestPos:%d, olderPos:%d, alertOffSet:%d, alertPos:%d", Integer.valueOf(this.todayHeaderPosition), Integer.valueOf(this.yesterdayHeaderPosition), Integer.valueOf(this.olderHeaderPosition), Integer.valueOf(i), Integer.valueOf(this.voicemailAlertPosition));
    }

    public void checkAndPlayVoicemail() {
        LogUtil.m9i("NewVoicemailAdapter.checkAndPlayVoicemail", "expandedViewHolder:%d, inViewHolderSet:%b, MPRequestToDownload:%s", Long.valueOf(this.currentlyExpandedViewHolderId), Boolean.valueOf(isCurrentlyExpandedViewHolderInViewHolderSet()), String.valueOf(this.mediaPlayer.getVoicemailRequestedToDownload()));
        NewVoicemailViewHolder currentlyExpandedViewHolder = getCurrentlyExpandedViewHolder();
        if (this.currentlyExpandedViewHolderId == -1 || !isCurrentlyExpandedViewHolderInViewHolderSet() || currentlyExpandedViewHolder == null || this.mediaPlayer.getVoicemailRequestedToDownload() == null || !this.mediaPlayer.getVoicemailRequestedToDownload().equals(currentlyExpandedViewHolder.getViewHolderVoicemailUri())) {
            LogUtil.m9i("NewVoicemailAdapter.checkAndPlayVoicemail", "not playing downloaded voicemail", new Object[0]);
        } else {
            currentlyExpandedViewHolder.clickPlayButtonOfViewHoldersMediaPlayerView(currentlyExpandedViewHolder);
        }
    }

    public void collapseExpandedViewHolder(NewVoicemailViewHolder newVoicemailViewHolder) {
        Assert.checkArgument(newVoicemailViewHolder.getViewHolderId() == this.currentlyExpandedViewHolderId);
        newVoicemailViewHolder.collapseViewHolder();
        this.currentlyExpandedViewHolderId = -1;
        if (this.mediaPlayer.isPlaying()) {
            Assert.checkArgument(Objects.equals(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri(), newVoicemailViewHolder.getViewHolderVoicemailUri()), "the voicemail being played should have been of the recently collapsed view holder.", new Object[0]);
            this.mediaPlayer.reset();
        }
    }

    public void deleteViewHolder(final Context context, FragmentManager fragmentManager2, NewVoicemailViewHolder newVoicemailViewHolder, final Uri uri) {
        LogUtil.m9i("NewVoicemailAdapter.deleteViewHolder", "deleting adapter position %d, id:%d, uri:%s ", Integer.valueOf(newVoicemailViewHolder.getAdapterPosition()), Long.valueOf(newVoicemailViewHolder.getViewHolderId()), String.valueOf(uri));
        this.deletedVoicemailPosition.add(Integer.valueOf(newVoicemailViewHolder.getAdapterPosition()));
        Assert.checkArgument(newVoicemailViewHolder.getViewHolderVoicemailUri().equals(uri));
        Assert.checkArgument(this.currentlyExpandedViewHolderId == newVoicemailViewHolder.getViewHolderId());
        collapseExpandedViewHolder(newVoicemailViewHolder);
        NewVoicemailMediaPlayerView mediaPlayerView = newVoicemailViewHolder.getMediaPlayerView();
        final int adapterPosition = newVoicemailViewHolder.getAdapterPosition();
        LogUtil.m9i("NewVoicemailAdapter.showUndoSnackbar", "position:%d and uri:%s", Integer.valueOf(adapterPosition), String.valueOf(uri));
        Snackbar make = Snackbar.make((View) mediaPlayerView, (int) R.string.snackbar_voicemail_deleted, 3000);
        make.addCallback(new Snackbar.Callback() {
            /* access modifiers changed from: private */
            public Void deleteVoicemail(Pair<Context, Uri> pair) {
                Assert.isWorkerThread();
                Context context = (Context) pair.first;
                Uri uri = (Uri) pair.second;
                boolean z = true;
                LogUtil.m9i("NewVoicemailAdapter.deleteVoicemail", "deleting uri:%s", String.valueOf(uri));
                ContentValues contentValues = new ContentValues();
                contentValues.put("deleted", "1");
                int update = context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
                LogUtil.m9i("NewVoicemailAdapter.deleteVoicemail", "return value:%d", Integer.valueOf(update));
                if (update != 1) {
                    z = false;
                }
                Assert.checkArgument(z, "voicemail delete was not successful", new Object[0]);
                Intent intent = new Intent("com.android.voicemail.VoicemailClient.ACTION_UPLOAD");
                intent.setPackage(context.getPackageName());
                context.sendBroadcast(intent);
                return null;
            }

            public void onDismissed(Object obj, int i) {
                Snackbar snackbar = (Snackbar) obj;
                boolean z = true;
                LogUtil.m9i("NewVoicemailAdapter.showUndoSnackbar", "onDismissed for event:%d, position:%d and uri:%s", Integer.valueOf(i), Integer.valueOf(adapterPosition), String.valueOf(uri));
                if (!(i == 0 || i == 1)) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i > 4 || i < 0) {
                                    z = false;
                                }
                                Assert.checkArgument(z, "unknown event", new Object[0]);
                                return;
                            }
                        }
                    }
                    LogUtil.m9i("NewVoicemailAdapter.showUndoSnackbar", "Proceeding with deleting voicemail", new Object[0]);
                    ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new DialerExecutor.Worker() {
                        public final Object doInBackground(Object obj) {
                            return NewVoicemailAdapter.C05811.this.deleteVoicemail((Pair) obj);
                        }
                    }).build().executeSerial(new Pair(context, uri));
                    return;
                }
                LogUtil.m9i("NewVoicemailAdapter.showUndoSnackbar", "Not proceeding with deleting the voicemail", new Object[0]);
                NewVoicemailAdapter.this.deletedVoicemailPosition.remove(Integer.valueOf(adapterPosition));
                NewVoicemailAdapter.this.notifyItemChanged(adapterPosition);
            }

            public void onShown(Object obj) {
                Snackbar snackbar = (Snackbar) obj;
                NewVoicemailAdapter.this.notifyItemRemoved(adapterPosition);
                LogUtil.m9i("NewVoicemailAdapter.showUndoSnackbar", "onShown for position:%d and uri:%s", Integer.valueOf(adapterPosition), uri);
            }
        });
        make.setAction((int) R.string.snackbar_undo, (View.OnClickListener) new View.OnClickListener(this) {
            public void onClick(View view) {
            }
        });
        make.setActionTextColor(context.getResources().getColor(R.color.dialer_snackbar_action_text_color));
        make.show();
    }

    public void expandViewHolderFirstTimeAndCollapseAllOtherVisibleViewHolders(NewVoicemailViewHolder newVoicemailViewHolder, VoicemailEntry voicemailEntry, NewVoicemailViewHolder.NewVoicemailViewHolderListener newVoicemailViewHolderListener) {
        LogUtil.m9i("NewVoicemailAdapter.expandViewHolderFirstTimeAndCollapseAllOtherVisibleViewHolders", "viewholder id:%d being request to expand, isExpanded:%b, size of our view holder dataset:%d, hashmap size:%d", Long.valueOf(newVoicemailViewHolder.getViewHolderId()), Boolean.valueOf(newVoicemailViewHolder.isViewHolderExpanded()), Integer.valueOf(this.newVoicemailViewHolderSet.size()), Integer.valueOf(this.newVoicemailViewHolderArrayMap.size()));
        this.currentlyExpandedViewHolderId = newVoicemailViewHolder.getViewHolderId();
        for (NewVoicemailViewHolder next : this.newVoicemailViewHolderSet) {
            if (next.getViewHolderId() != newVoicemailViewHolder.getViewHolderId()) {
                next.collapseViewHolder();
            }
        }
        if (this.mediaPlayer.isPlaying() && !Objects.equals(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri(), newVoicemailViewHolder.getViewHolderVoicemailUri())) {
            LogUtil.m9i("NewVoicemailAdapter.expandViewHolderFirstTimeAndCollapseAllOtherVisibleViewHolders", "Reset the media player since we expanded something other that the playing voicemail, MP was playing:%s, viewholderExpanded:%d, MP.isPlaying():%b", String.valueOf(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri()), Long.valueOf(newVoicemailViewHolder.getViewHolderId()), Boolean.valueOf(this.mediaPlayer.isPlaying()));
            this.mediaPlayer.reset();
        }
        if (this.mediaPlayer.isPaused() && !Objects.equals(this.mediaPlayer.getLastPausedVoicemailUri(), newVoicemailViewHolder.getViewHolderVoicemailUri())) {
            LogUtil.m9i("NewVoicemailAdapter.expandViewHolderFirstTimeAndCollapseAllOtherVisibleViewHolders", "There was an existing paused viewholder, the media player should reset since we expanded something other that the paused voicemail, MP.paused:%s", String.valueOf(this.mediaPlayer.getLastPausedVoicemailUri()));
            this.mediaPlayer.reset();
        }
        Assert.checkArgument(!newVoicemailViewHolder.isViewHolderExpanded(), "cannot expand a voicemail that is not collapsed", new Object[0]);
        newVoicemailViewHolder.expandAndBindViewHolderAndMediaPlayerViewWithAdapterValues(voicemailEntry, this.fragmentManager, this.mediaPlayer, newVoicemailViewHolderListener);
        Assert.checkArgument(!this.mediaPlayer.isPlaying());
    }

    public int getItemCount() {
        LogUtil.enterBlock("NewVoicemailAdapter.getItemCount");
        int i = this.voicemailAlertPosition != Integer.MAX_VALUE ? 1 : 0;
        if (this.todayHeaderPosition != Integer.MAX_VALUE) {
            i++;
        }
        if (this.yesterdayHeaderPosition != Integer.MAX_VALUE) {
            i++;
        }
        if (this.olderHeaderPosition != Integer.MAX_VALUE) {
            i++;
        }
        LogUtil.m9i("NewVoicemailAdapter.getItemCount", "cursor cnt:%d, num of headers:%d, delete size:%d", Integer.valueOf(this.cursor.getCount()), Integer.valueOf(i), Integer.valueOf(this.deletedVoicemailPosition.size()));
        return (this.cursor.getCount() + i) - this.deletedVoicemailPosition.size();
    }

    public int getItemViewType(int i) {
        LogUtil.enterBlock("NewVoicemailAdapter.getItemViewType");
        int i2 = this.voicemailAlertPosition;
        if (i2 != Integer.MAX_VALUE && i == i2) {
            return 1;
        }
        int i3 = this.todayHeaderPosition;
        if (i3 != Integer.MAX_VALUE && i == i3) {
            return 2;
        }
        int i4 = this.yesterdayHeaderPosition;
        if (i4 != Integer.MAX_VALUE && i == i4) {
            return 2;
        }
        int i5 = this.olderHeaderPosition;
        if (i5 == Integer.MAX_VALUE || i != i5) {
            return 3;
        }
        return 2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        int i2 = i;
        LogUtil.enterBlock("NewVoicemailAdapter.onBindViewHolder, pos:" + i2);
        boolean z = true;
        if (this.deletedVoicemailPosition.contains(Integer.valueOf(i))) {
            LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "pos:%d contains deleted voicemail, re-bind. #of deleted voicemail positions: %d", Integer.valueOf(i), Integer.valueOf(this.deletedVoicemailPosition.size()));
            if (this.deletedVoicemailPosition.size() != 1) {
                z = false;
            }
            Assert.checkArgument(z, "multi-deletes not currently supported", new Object[0]);
            onBindViewHolder(viewHolder2, i2 + 1);
            return;
        }
        printHashSet();
        printArrayMap();
        if (viewHolder2 instanceof NewVoicemailHeaderViewHolder) {
            LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "view holder at pos:%d is a header", Integer.valueOf(i));
            NewVoicemailHeaderViewHolder newVoicemailHeaderViewHolder = (NewVoicemailHeaderViewHolder) viewHolder2;
            int itemViewType = getItemViewType(i2);
            if (i2 == this.todayHeaderPosition) {
                newVoicemailHeaderViewHolder.setHeader(R.string.new_voicemail_header_today);
            } else if (i2 == this.yesterdayHeaderPosition) {
                newVoicemailHeaderViewHolder.setHeader(R.string.new_voicemail_header_yesterday);
            } else if (i2 == this.olderHeaderPosition) {
                newVoicemailHeaderViewHolder.setHeader(R.string.new_voicemail_header_older);
            } else {
                throw new IllegalStateException("Unexpected view type " + itemViewType + " at position: " + i2);
            }
        } else if (viewHolder2 instanceof NewVoicemailAlertViewHolder) {
            LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "view holder at pos:%d is a alert", Integer.valueOf(i));
            LogUtil.m9i("NewVoicemailAdapter.onBindAlertViewHolder", "pos:%d, voicemailAlertPosition:%d", Integer.valueOf(i), Integer.valueOf(this.voicemailAlertPosition));
            NewVoicemailAlertViewHolder newVoicemailAlertViewHolder = (NewVoicemailAlertViewHolder) viewHolder2;
            int itemViewType2 = getItemViewType(i2);
            Assert.checkArgument(i2 == 0, "position is not 0", new Object[0]);
            Assert.checkArgument(i2 == this.voicemailAlertPosition, String.format(Locale.US, "position:%d and voicemailAlertPosition:%d are different", new Object[]{Integer.valueOf(i), Integer.valueOf(this.voicemailAlertPosition)}), new Object[0]);
            Assert.checkArgument(itemViewType2 == 1, GeneratedOutlineSupport.outline5("Invalid row type: ", itemViewType2), new Object[0]);
            boolean z2 = this.voicemailErrorMessage.getActions().size() <= 2;
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Too many actions: ");
            outline13.append(this.voicemailErrorMessage.getActions().size());
            Assert.checkArgument(z2, outline13.toString(), new Object[0]);
            newVoicemailAlertViewHolder.setTitle(this.voicemailErrorMessage.getTitle());
            newVoicemailAlertViewHolder.setDescription(this.voicemailErrorMessage.getDescription());
            if (!this.voicemailErrorMessage.getActions().isEmpty()) {
                newVoicemailAlertViewHolder.setPrimaryButton(this.voicemailErrorMessage.getActions().get(0));
            }
            if (this.voicemailErrorMessage.getActions().size() > 1) {
                newVoicemailAlertViewHolder.setSecondaryButton(this.voicemailErrorMessage.getActions().get(1));
            }
        } else {
            LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "view holder at pos:%d is a not a header or an alert", Integer.valueOf(i));
            NewVoicemailViewHolder newVoicemailViewHolder = (NewVoicemailViewHolder) viewHolder2;
            int i3 = this.voicemailAlertPosition;
            int i4 = (i3 == Integer.MAX_VALUE || i2 <= i3) ? 0 : 1;
            int i5 = this.todayHeaderPosition;
            if (i5 != Integer.MAX_VALUE && i2 > i5) {
                i4++;
            }
            int i6 = this.yesterdayHeaderPosition;
            if (i6 != Integer.MAX_VALUE && i2 > i6) {
                i4++;
            }
            int i7 = this.olderHeaderPosition;
            if (i7 != Integer.MAX_VALUE && i2 > i7) {
                i4++;
            }
            LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "view holder at pos:%d, nonVoicemailEntryHeaders:%d", Integer.valueOf(i), Integer.valueOf(i4));
            if (this.newVoicemailViewHolderArrayMap.containsKey(Long.valueOf(newVoicemailViewHolder.getViewHolderId()))) {
                LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "Removing from hashset:%d, hashsetSize:%d, currExpanded:%d", Long.valueOf(newVoicemailViewHolder.getViewHolderId()), Integer.valueOf(this.newVoicemailViewHolderArrayMap.size()), Long.valueOf(this.currentlyExpandedViewHolderId));
                this.newVoicemailViewHolderArrayMap.remove(Long.valueOf(newVoicemailViewHolder.getViewHolderId()));
                printHashSet();
                printArrayMap();
            }
            newVoicemailViewHolder.reset();
            this.cursor.moveToPosition(i2 - i4);
            newVoicemailViewHolder.bindViewHolderValuesFromAdapter(this.cursor, this.fragmentManager, this.mediaPlayer, i, this.currentlyExpandedViewHolderId);
            LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "Adding to hashset:%d, hashsetSize:%d, pos:%d, currExpanded:%d", Long.valueOf(newVoicemailViewHolder.getViewHolderId()), Integer.valueOf(this.newVoicemailViewHolderArrayMap.size()), Integer.valueOf(i), Long.valueOf(this.currentlyExpandedViewHolderId));
            this.newVoicemailViewHolderArrayMap.put(Long.valueOf(newVoicemailViewHolder.getViewHolderId()), newVoicemailViewHolder);
            printHashSet();
            printArrayMap();
            if (newVoicemailViewHolder.isViewHolderExpanded() && this.mediaPlayer.isPlaying()) {
                LogUtil.m9i("NewVoicemailAdapter.onBindViewHolder", "Adding to hashset:%d, hashsetSize:%d, pos:%d, currExpanded:%d", Integer.valueOf(this.newVoicemailViewHolderSet.size()), Integer.valueOf(this.newVoicemailViewHolderArrayMap.size()), Integer.valueOf(i), Long.valueOf(this.currentlyExpandedViewHolderId));
                Assert.checkArgument(newVoicemailViewHolder.getViewHolderVoicemailUri().equals(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri()), "only the expanded view holder can be playing.", new Object[0]);
                Assert.isNotNull(getCurrentlyExpandedViewHolder());
                Assert.checkArgument(getCurrentlyExpandedViewHolder().getViewHolderVoicemailUri().equals(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri()));
                recursivelyUpdateMediaPlayerViewOfExpandedViewHolder(newVoicemailViewHolder);
            }
            this.newVoicemailViewHolderArrayMap.put(Long.valueOf(newVoicemailViewHolder.getViewHolderId()), newVoicemailViewHolder);
            printHashSet();
            printArrayMap();
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.enterBlock("NewVoicemailAdapter.onCreateViewHolder");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return new NewVoicemailAlertViewHolder(from.inflate(R.layout.new_voicemail_entry_alert, viewGroup, false));
        }
        if (i == 2) {
            return new NewVoicemailHeaderViewHolder(from.inflate(R.layout.new_voicemail_entry_header, viewGroup, false));
        }
        if (i == 3) {
            NewVoicemailViewHolder newVoicemailViewHolder = new NewVoicemailViewHolder(from.inflate(R.layout.new_voicemail_entry, viewGroup, false), this.clock, this);
            this.newVoicemailViewHolderSet.add(newVoicemailViewHolder);
            return newVoicemailViewHolder;
        }
        throw new UnsupportedOperationException(GeneratedOutlineSupport.outline5("Unsupported view type: ", i));
    }

    public void pauseViewHolder(NewVoicemailViewHolder newVoicemailViewHolder) {
        Assert.isNotNull(getCurrentlyExpandedViewHolder(), "cannot have pressed pause if the viewholder wasn't expanded", new Object[0]);
        Assert.checkArgument(getCurrentlyExpandedViewHolder().getViewHolderVoicemailUri().equals(newVoicemailViewHolder.getViewHolderVoicemailUri()), "view holder whose pause button was pressed has to have been the expanded viewholder being tracked by the adapter.", new Object[0]);
        this.mediaPlayer.pauseMediaPlayer(newVoicemailViewHolder.getViewHolderVoicemailUri());
        newVoicemailViewHolder.setPausedStateOfMediaPlayerView(newVoicemailViewHolder.getViewHolderVoicemailUri(), this.mediaPlayer);
    }

    public void resumePausedViewHolder(NewVoicemailViewHolder newVoicemailViewHolder) {
        Assert.isNotNull(getCurrentlyExpandedViewHolder(), "cannot have pressed pause if the viewholder wasn't expanded", new Object[0]);
        Assert.checkArgument(getCurrentlyExpandedViewHolder().getViewHolderVoicemailUri().equals(newVoicemailViewHolder.getViewHolderVoicemailUri()), "view holder whose play button was pressed has to have been the expanded viewholder being tracked by the adapter.", new Object[0]);
        Assert.isNotNull(this.mediaPlayer.getLastPausedVoicemailUri(), "there should be be an pausedUri to resume", new Object[0]);
        Assert.checkArgument(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(newVoicemailViewHolder.getViewHolderVoicemailUri()), "only the last playing uri can be resumed", new Object[0]);
        Assert.checkArgument(this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri().equals(newVoicemailViewHolder.getViewHolderVoicemailUri()), "only the last prepared uri can be resumed", new Object[0]);
        Assert.checkArgument(this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri().equals(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri()), "the last prepared and playing voicemails have to be the same when resuming", new Object[0]);
        this.onPreparedListener.onPrepared(this.mediaPlayer.getMediaPlayer());
    }

    public void updateCursor(Cursor cursor2) {
        LogUtil.enterBlock("NewVoicemailAdapter.updateCursor");
        this.deletedVoicemailPosition.clear();
        this.cursor = cursor2;
        updateHeaderPositions();
        notifyDataSetChanged();
    }

    public void updateVoicemailAlertWithMostRecentStatus(Context context, ImmutableList<VoicemailStatus> immutableList) {
        VoicemailErrorMessage voicemailErrorMessage2;
        if (immutableList.isEmpty()) {
            LogUtil.m9i("NewVoicemailAdapter.updateVoicemailAlertWithMostRecentStatus", "voicemailStatuses was empty", new Object[0]);
            return;
        }
        this.voicemailErrorMessage = null;
        UnmodifiableIterator<VoicemailStatus> it = immutableList.iterator();
        while (it.hasNext()) {
            VoicemailStatus next = it.next();
            int i = Build.VERSION.SDK_INT;
            String str = next.type;
            char c = 65535;
            if (str.hashCode() == -1478600199 && str.equals("vvm_type_vvm3")) {
                c = 0;
            }
            if (c != 0) {
                voicemailErrorMessage2 = OmtpVoicemailMessageCreator.create(context, next, (VoicemailStatusReader) null);
            } else {
                voicemailErrorMessage2 = OmtpVoicemailMessageCreator.create1(context, next, (VoicemailStatusReader) null);
            }
            this.voicemailErrorMessage = voicemailErrorMessage2;
            if (this.voicemailErrorMessage != null) {
                break;
            }
        }
        if (this.voicemailErrorMessage != null) {
            LogUtil.m9i("NewVoicemailAdapter.updateVoicemailAlertWithMostRecentStatus", "showing alert", new Object[0]);
            this.voicemailAlertPosition = 0;
            updateHeaderPositions();
            notifyItemChanged(0);
        }
    }
}
