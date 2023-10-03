package com.android.dialer.calldetails;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.FileProvider;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.calldetails.CallDetailsActivityCommon;
import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.calllogutils.CallTypeHelper;
import com.android.dialer.calllogutils.CallTypeIconsView;
import com.android.dialer.callrecord.CallRecording;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.callrecord.impl.CallRecorderService;
import com.android.dialer.common.LogUtil;
import com.android.dialer.constants.Constants;
import com.android.dialer.enrichedcall.historyquery.proto.HistoryResult;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.oem.MotorolaUtils;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CallDetailsEntryViewHolder extends RecyclerView.ViewHolder {
    private final CallDetailsEntryListener callDetailsEntryListener;
    private final TextView callDuration;
    private final TextView callTime;
    private final CallTypeIconsView callTypeIcon;
    private final TextView callTypeText;
    private final Context context;
    private final TextView multimediaDetails;
    private final View multimediaDetailsContainer;
    private final View multimediaDivider;
    private final ImageView multimediaImage;
    private final View multimediaImageContainer;
    private final TextView playbackButton;
    private final TextView postCallNote;
    private final TextView rttTranscript;

    interface CallDetailsEntryListener {
    }

    public CallDetailsEntryViewHolder(View view, CallDetailsEntryListener callDetailsEntryListener2) {
        super(view);
        this.context = view.getContext();
        this.callTypeIcon = (CallTypeIconsView) view.findViewById(R.id.call_direction);
        this.callTypeText = (TextView) view.findViewById(R.id.call_type);
        this.callTime = (TextView) view.findViewById(R.id.call_time);
        this.callDuration = (TextView) view.findViewById(R.id.call_duration);
        this.playbackButton = (TextView) view.findViewById(R.id.play_recordings);
        this.multimediaImageContainer = view.findViewById(R.id.multimedia_image_container);
        this.multimediaDetailsContainer = view.findViewById(R.id.ec_container);
        this.multimediaDivider = view.findViewById(R.id.divider);
        this.multimediaDetails = (TextView) view.findViewById(R.id.multimedia_details);
        this.postCallNote = (TextView) view.findViewById(R.id.post_call_note);
        this.multimediaImage = (ImageView) view.findViewById(R.id.multimedia_image);
        TextView textView = (TextView) view.findViewById(R.id.multimedia_attachments_number);
        this.rttTranscript = (TextView) view.findViewById(R.id.rtt_transcript);
        this.callDetailsEntryListener = callDetailsEntryListener2;
    }

    private void playRecording(Context context2, CallRecording callRecording) {
        Uri uriForFile = FileProvider.getUriForFile(context2, Constants.get().getFileProviderAuthority(), callRecording.getFile());
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uriForFile.toString());
        try {
            context2.startActivity(new Intent("android.intent.action.VIEW").setDataAndType(uriForFile, !TextUtils.isEmpty(fileExtensionFromUrl) ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl) : "audio/*").addFlags(1));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context2, R.string.call_playback_no_app_found_toast, 1).show();
        }
    }

    private void startSmsIntent(Context context2, String str) {
        DialerUtils.startActivityWithErrorToast(context2, CallUtil.getSendSmsIntent(str), R.string.activity_not_available);
    }

    public /* synthetic */ boolean lambda$handleRecordingClick$5$CallDetailsEntryViewHolder(Context context2, List list, MenuItem menuItem) {
        playRecording(context2, (CallRecording) list.get(menuItem.getItemId()));
        return true;
    }

    public /* synthetic */ void lambda$setCallDetails$0$CallDetailsEntryViewHolder(List list, View view) {
        Context context2 = view.getContext();
        if (list.size() == 1) {
            playRecording(context2, (CallRecording) list.get(0));
            return;
        }
        PopupMenu popupMenu = new PopupMenu(context2, view);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), DateFormat.is24HourFormat(context2) ? "Hmss" : "hmssa"));
        for (int i = 0; i < list.size(); i++) {
            popupMenu.getMenu().add(0, i, i, simpleDateFormat.format(new Date(((CallRecording) list.get(i)).startRecordingTime)));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(context2, list) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ List f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CallDetailsEntryViewHolder.this.lambda$handleRecordingClick$5$CallDetailsEntryViewHolder(this.f$1, this.f$2, menuItem);
            }
        });
        popupMenu.show();
    }

    public /* synthetic */ void lambda$setCallDetails$1$CallDetailsEntryViewHolder(CallDetailsEntries.CallDetailsEntry callDetailsEntry, String str, PhotoInfo photoInfo, View view) {
        ((CallDetailsActivityCommon.CallDetailsEntryListener) this.callDetailsEntryListener).showRttTranscript(callDetailsEntry.getCallMappingId(), str, photoInfo);
    }

    public /* synthetic */ void lambda$setMultimediaDetails$2$CallDetailsEntryViewHolder(String str, View view) {
        startSmsIntent(this.context, str);
    }

    public /* synthetic */ void lambda$setMultimediaDetails$3$CallDetailsEntryViewHolder(String str, View view) {
        startSmsIntent(this.context, str);
    }

    public /* synthetic */ void lambda$setMultimediaDetails$4$CallDetailsEntryViewHolder(String str, View view) {
        startSmsIntent(this.context, str);
    }

    /* access modifiers changed from: package-private */
    public void setCallDetails(String str, String str2, PhotoInfo photoInfo, CallDetailsEntries.CallDetailsEntry callDetailsEntry, CallTypeHelper callTypeHelper, CallRecordingDataStore callRecordingDataStore, boolean z) {
        int i;
        List<CallRecording> list;
        int i2;
        int i3;
        String str3 = str;
        CallDetailsEntries.CallDetailsEntry callDetailsEntry2 = callDetailsEntry;
        CallRecordingDataStore callRecordingDataStore2 = callRecordingDataStore;
        int callType = callDetailsEntry.getCallType();
        boolean z2 = (callDetailsEntry.getFeatures() & 1) == 1;
        boolean z3 = (callDetailsEntry.getFeatures() & 2) == 2;
        boolean isDuoCall = callDetailsEntry.getIsDuoCall();
        int i4 = Build.VERSION.SDK_INT;
        boolean z4 = (callDetailsEntry.getFeatures() & 32) == 32;
        TextView textView = this.callTime;
        Context context2 = this.context;
        if (callType == 1 || callType == 2 || callType == 4 || callType == 5 || callType == 6 || callType == 7) {
            i = ContextCompat.getColor(context2, R.color.dialer_secondary_text_color);
        } else {
            i = ContextCompat.getColor(context2, R.color.dialer_red);
        }
        textView.setTextColor(i);
        this.callTypeIcon.clear();
        this.callTypeIcon.add(callType);
        this.callTypeIcon.setShowVideo(z2);
        this.callTypeIcon.setShowHd((callDetailsEntry.getFeatures() & 4) == 4);
        this.callTypeIcon.setShowWifi(MotorolaUtils.shouldShowWifiIconInCallLog(this.context, callDetailsEntry.getFeatures()));
        int i5 = Build.VERSION.SDK_INT;
        this.callTypeIcon.setShowRtt((callDetailsEntry.getFeatures() & 32) == 32);
        this.callTypeText.setText(callTypeHelper.getCallTypeText(callType, z2, z3, isDuoCall));
        this.callTime.setText(CallLogDates.formatDate(this.context, callDetailsEntry.getDate()));
        if ((callType == 1 || callType == 2 || callType == 4 || callType == 7) ? false : true) {
            this.callDuration.setVisibility(8);
        } else {
            this.callDuration.setVisibility(0);
            this.callDuration.setText(CallLogDates.formatDurationAndDataUsage(this.context, callDetailsEntry.getDuration(), callDetailsEntry.getDataUsage()));
            this.callDuration.setContentDescription(CallLogDates.formatDurationAndDataUsageA11y(this.context, callDetailsEntry.getDuration(), callDetailsEntry.getDataUsage()));
        }
        if (CallRecorderService.isEnabled(this.context)) {
            callRecordingDataStore2.open(this.context);
            list = callRecordingDataStore2.getRecordings(str3, callDetailsEntry.getDate());
        } else {
            list = null;
        }
        int size = list != null ? list.size() : 0;
        this.playbackButton.setOnClickListener(new View.OnClickListener(list) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                CallDetailsEntryViewHolder.this.lambda$setCallDetails$0$CallDetailsEntryViewHolder(this.f$1, view);
            }
        });
        this.playbackButton.setText(this.context.getResources().getQuantityString(R.plurals.play_recordings, size, new Object[]{Integer.valueOf(size)}));
        this.playbackButton.setVisibility(size > 0 ? 0 : 8);
        this.multimediaDivider.setVisibility(z ? 0 : 8);
        if (callDetailsEntry.getHistoryResultsList().isEmpty()) {
            LogUtil.m9i("CallDetailsEntryViewHolder.setMultimediaDetails", "no data, hiding UI", new Object[0]);
            this.multimediaDetailsContainer.setVisibility(8);
        } else {
            HistoryResult historyResults = callDetailsEntry2.getHistoryResults(0);
            this.multimediaDetailsContainer.setVisibility(0);
            this.multimediaDetailsContainer.setOnClickListener(new View.OnClickListener(str3) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    CallDetailsEntryViewHolder.this.lambda$setMultimediaDetails$2$CallDetailsEntryViewHolder(this.f$1, view);
                }
            });
            this.multimediaImageContainer.setOnClickListener(new View.OnClickListener(str3) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    CallDetailsEntryViewHolder.this.lambda$setMultimediaDetails$3$CallDetailsEntryViewHolder(this.f$1, view);
                }
            });
            this.multimediaImageContainer.setClipToOutline(true);
            if (!TextUtils.isEmpty(historyResults.getImageUri())) {
                LogUtil.m9i("CallDetailsEntryViewHolder.setMultimediaDetails", "setting image", new Object[0]);
                this.multimediaImageContainer.setVisibility(0);
                this.multimediaImage.setImageURI(Uri.parse(historyResults.getImageUri()));
                this.multimediaDetails.setText(historyResults.getType() == HistoryResult.Type.INCOMING_POST_CALL || historyResults.getType() == HistoryResult.Type.INCOMING_CALL_COMPOSER ? R.string.received_a_photo : R.string.sent_a_photo);
                i3 = 0;
            } else {
                i3 = 0;
                LogUtil.m9i("CallDetailsEntryViewHolder.setMultimediaDetails", "no image", new Object[0]);
            }
            if (!TextUtils.isEmpty(historyResults.getText())) {
                LogUtil.m9i("CallDetailsEntryViewHolder.setMultimediaDetails", "showing text", new Object[i3]);
                TextView textView2 = this.multimediaDetails;
                Context context3 = this.context;
                Object[] objArr = new Object[1];
                objArr[i3] = historyResults.getText();
                textView2.setText(context3.getString(R.string.message_in_quotes, objArr));
            } else {
                LogUtil.m9i("CallDetailsEntryViewHolder.setMultimediaDetails", "no text", new Object[i3]);
            }
            if (callDetailsEntry.getHistoryResultsList().size() <= 1 || TextUtils.isEmpty(callDetailsEntry2.getHistoryResults(1).getText())) {
                LogUtil.m9i("CallDetailsEntryViewHolder.setMultimediaDetails", "no post call note", new Object[i3]);
            } else {
                LogUtil.m9i("CallDetailsEntryViewHolder.setMultimediaDetails", "showing post call note", new Object[i3]);
                this.postCallNote.setVisibility(i3);
                TextView textView3 = this.postCallNote;
                Context context4 = this.context;
                Object[] objArr2 = new Object[1];
                objArr2[i3] = callDetailsEntry2.getHistoryResults(1).getText();
                textView3.setText(context4.getString(R.string.message_in_quotes, objArr2));
                this.postCallNote.setOnClickListener(new View.OnClickListener(str3) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        CallDetailsEntryViewHolder.this.lambda$setMultimediaDetails$4$CallDetailsEntryViewHolder(this.f$1, view);
                    }
                });
            }
        }
        if (z4) {
            if (callDetailsEntry.getHasRttTranscript()) {
                this.rttTranscript.setText(R.string.rtt_transcript_link);
                this.rttTranscript.setTextAppearance(R.style.RttTranscriptLink);
                this.rttTranscript.setClickable(true);
                this.rttTranscript.setOnClickListener(new View.OnClickListener(callDetailsEntry2, str2, photoInfo) {
                    private final /* synthetic */ CallDetailsEntries.CallDetailsEntry f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ PhotoInfo f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void onClick(View view) {
                        CallDetailsEntryViewHolder.this.lambda$setCallDetails$1$CallDetailsEntryViewHolder(this.f$1, this.f$2, this.f$3, view);
                    }
                });
                i2 = 0;
            } else {
                this.rttTranscript.setText(R.string.rtt_transcript_not_available);
                this.rttTranscript.setTextAppearance(R.style.RttTranscriptMessage);
                i2 = 0;
                this.rttTranscript.setClickable(false);
            }
            this.rttTranscript.setVisibility(i2);
            return;
        }
        this.rttTranscript.setVisibility(8);
    }
}
