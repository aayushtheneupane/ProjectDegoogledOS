package com.android.dialer.app.calllog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.support.p002v7.appcompat.R$style;
import android.support.p002v7.widget.CardView;
import android.support.p002v7.widget.RecyclerView;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneNumberUtils;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.contacts.common.dialog.CallSubjectDialog;
import com.android.dialer.R;
import com.android.dialer.app.calllog.CallLogAdapter;
import com.android.dialer.app.calllog.calllogcache.CallLogCache;
import com.android.dialer.app.voicemail.VoicemailPlaybackLayout;
import com.android.dialer.app.voicemail.VoicemailPlaybackPresenter;
import com.android.dialer.blocking.BlockedNumbersMigrator;
import com.android.dialer.blocking.FilteredNumberCompat;
import com.android.dialer.blocking.FilteredNumbersUtil;
import com.android.dialer.callcomposer.CallComposerActivity;
import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.calldetails.OldCallDetailsActivity;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.AsyncTaskExecutors;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.dialercontact.SimDetails;
import com.android.dialer.lettertile.LetterTileDrawable;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.phonenumbercache.PhoneNumberCache;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;

public final class CallLogListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, MenuItem.OnMenuItemClickListener, View.OnCreateContextMenuListener {
    public PhoneAccountHandle accountHandle;
    public View actionsView;
    public View addToExistingContactButtonView;
    public AsyncTask<Void, Void, ?> asyncTask;
    public Integer blockId;
    /* access modifiers changed from: private */
    public final OnClickListener blockReportListener;
    public View blockReportView;
    public View blockView;
    public View callButtonView;
    public View callComposeButtonView;
    private CallDetailsEntries callDetailsEntries;
    public long[] callIds;
    private final CallLogCache callLogCache;
    public final CardView callLogEntryView;
    private final CallLogListItemHelper callLogListItemHelper;
    public int callType;
    public CharSequence callTypeOrLocation;
    public View callWithNoteButtonView;
    public int callbackAction;
    public ImageView checkBoxView;
    private final Context context;
    public String countryIso;
    public View createNewContactButtonView;
    public final TextView dayGroupHeader;
    public CharSequence dayGroupHeaderText;
    public int dayGroupHeaderVisibility;
    private final PhoneAccountHandle defaultPhoneAccountHandle;
    public View detailsButtonView;
    public String displayNumber;
    private View.OnClickListener expandCollapseListener;
    private final int hostUi;
    public volatile ContactInfo info;
    public View inviteVideoButtonView;
    public boolean isCallComposerCapable;
    public boolean isLoaded;
    public boolean isSpam;
    public boolean isSpamFeatureEnabled;
    private final View.OnLongClickListener longPressListener;
    public CharSequence nameOrNumber;
    public String number;
    public int numberPresentation;
    public String numberType;
    private final CallLogAdapter.OnActionModeStateChangedListener onActionModeStateChangedListener;
    public final PhoneCallDetailsViews phoneCallDetailsViews;
    public String postDialDigits;
    public final ImageView primaryActionButtonView;
    public final View primaryActionView;
    public final DialerQuickContactBadge quickContactView;
    public View reportNotSpamView;
    public final View rootView;
    public long rowId;
    public View sendMessageView;
    public View sendVoicemailButtonView;
    public View setUpVideoButtonView;
    public View unblockView;
    public View videoCallButtonView;
    private final VoicemailPlaybackPresenter voicemailPlaybackPresenter;
    public VoicemailPlaybackLayout voicemailPlaybackView;
    private boolean voicemailPrimaryActionButtonClicked;
    public String voicemailUri;
    public ImageView workIconView;

    private static class DeleteCallTask extends AsyncTask<Void, Void, Void> {
        private final String callIdsStr;
        private final WeakReference<Context> contextWeakReference;

        DeleteCallTask(Context context, long[] jArr) {
            String str;
            this.contextWeakReference = new WeakReference<>(context);
            if (jArr == null || jArr.length == 0) {
                str = null;
            } else {
                StringBuilder sb = new StringBuilder();
                for (long j : jArr) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append(j);
                }
                str = sb.toString();
            }
            this.callIdsStr = str;
        }

        /* access modifiers changed from: protected */
        @SuppressLint({"MissingPermission"})
        public Object doInBackground(Object[] objArr) {
            Void[] voidArr = (Void[]) objArr;
            Context context = (Context) this.contextWeakReference.get();
            if (!(context == null || this.callIdsStr == null)) {
                context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("_id IN ("), this.callIdsStr, ")"), (String[]) null);
            }
            return null;
        }

        public void onPostExecute(Object obj) {
            Void voidR = (Void) obj;
        }
    }

    public interface OnClickListener {
    }

    private CallLogListItemViewHolder(Context context2, OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener, CallLogAdapter.OnActionModeStateChangedListener onActionModeStateChangedListener2, CallLogCache callLogCache2, CallLogListItemHelper callLogListItemHelper2, VoicemailPlaybackPresenter voicemailPlaybackPresenter2, View view, DialerQuickContactBadge dialerQuickContactBadge, View view2, PhoneCallDetailsViews phoneCallDetailsViews2, CardView cardView, TextView textView, ImageView imageView) {
        super(view);
        this.context = context2;
        this.expandCollapseListener = onClickListener2;
        this.onActionModeStateChangedListener = onActionModeStateChangedListener2;
        this.longPressListener = onLongClickListener;
        this.callLogCache = callLogCache2;
        this.callLogListItemHelper = callLogListItemHelper2;
        this.voicemailPlaybackPresenter = voicemailPlaybackPresenter2;
        this.blockReportListener = onClickListener;
        PhoneNumberCache.get(this.context).getCachedNumberLookupService();
        this.defaultPhoneAccountHandle = TelecomUtil.getDefaultOutgoingPhoneAccount(context2, "tel");
        this.rootView = view;
        this.quickContactView = dialerQuickContactBadge;
        this.primaryActionView = view2;
        this.phoneCallDetailsViews = phoneCallDetailsViews2;
        this.callLogEntryView = cardView;
        this.dayGroupHeader = textView;
        this.primaryActionButtonView = imageView;
        this.workIconView = (ImageView) view.findViewById(R.id.work_profile_icon);
        this.checkBoxView = (ImageView) view.findViewById(R.id.quick_contact_checkbox);
        phoneCallDetailsViews2.nameView.setElegantTextHeight(false);
        phoneCallDetailsViews2.callLocationAndDate.setElegantTextHeight(false);
        Context context3 = this.context;
        if (context3 instanceof CallLogActivity) {
            this.hostUi = 1;
            ((LoggingBindingsStub) Logger.get(context3)).logQuickContactOnTouch(this.quickContactView, InteractionEvent$Type.OPEN_QUICK_CONTACT_FROM_CALL_HISTORY, true);
        } else if (this.voicemailPlaybackPresenter == null) {
            this.hostUi = 0;
            ((LoggingBindingsStub) Logger.get(context3)).logQuickContactOnTouch(this.quickContactView, InteractionEvent$Type.OPEN_QUICK_CONTACT_FROM_CALL_LOG, true);
        } else {
            this.hostUi = 2;
            ((LoggingBindingsStub) Logger.get(context3)).logQuickContactOnTouch(this.quickContactView, InteractionEvent$Type.OPEN_QUICK_CONTACT_FROM_VOICEMAIL, false);
        }
        this.quickContactView.setOverlay((Drawable) null);
        this.quickContactView.setPrioritizedMimeType("vnd.android.cursor.item/phone_v2");
        imageView.setOnClickListener(this);
        imageView.setOnLongClickListener(this);
        view2.setOnClickListener(this.expandCollapseListener);
        if (this.voicemailPlaybackPresenter == null || !((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("enable_call_log_multiselect", true)) {
            view2.setOnCreateContextMenuListener(this);
            return;
        }
        view2.setOnLongClickListener(this.longPressListener);
        this.quickContactView.setOnLongClickListener(this.longPressListener);
        this.quickContactView.setMulitSelectListeners(this.expandCollapseListener, this.onActionModeStateChangedListener);
    }

    private DialerContact buildContact() {
        DialerContact.Builder newBuilder = DialerContact.newBuilder();
        newBuilder.setPhotoId(this.info.photoId);
        if (this.info.photoUri != null) {
            newBuilder.setPhotoUri(this.info.photoUri.toString());
        }
        if (this.info.lookupUri != null) {
            newBuilder.setContactUri(this.info.lookupUri.toString());
        }
        CharSequence charSequence = this.nameOrNumber;
        if (charSequence != null) {
            newBuilder.setNameOrNumber((String) charSequence);
        }
        newBuilder.setContactType(getContactType());
        String str = this.number;
        if (str != null) {
            newBuilder.setNumber(str);
        }
        if (!TextUtils.isEmpty(this.postDialDigits)) {
            newBuilder.setPostDialDigits(this.postDialDigits);
        }
        if (!TextUtils.isEmpty(this.info.name)) {
            newBuilder.setDisplayNumber(this.displayNumber);
        }
        newBuilder.setNumberLabel(this.numberType);
        String accountLabel = this.callLogCache.getAccountLabel(this.accountHandle);
        if (!TextUtils.isEmpty(accountLabel)) {
            SimDetails.Builder newBuilder2 = SimDetails.newBuilder();
            newBuilder2.setNetwork(accountLabel);
            newBuilder2.setColor(this.callLogCache.getAccountColor(this.accountHandle));
            newBuilder.setSimDetails((SimDetails) newBuilder2.build());
        }
        return (DialerContact) newBuilder.build();
    }

    private boolean canSupportAssistedDialing() {
        return (this.info == null || this.info.lookupKey == null) ? false : true;
    }

    public static CallLogListItemViewHolder create(View view, Context context2, OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener, CallLogAdapter.OnActionModeStateChangedListener onActionModeStateChangedListener2, CallLogCache callLogCache2, CallLogListItemHelper callLogListItemHelper2, VoicemailPlaybackPresenter voicemailPlaybackPresenter2) {
        View view2 = view;
        return new CallLogListItemViewHolder(context2, onClickListener, onClickListener2, onLongClickListener, onActionModeStateChangedListener2, callLogCache2, callLogListItemHelper2, voicemailPlaybackPresenter2, view2, (DialerQuickContactBadge) view2.findViewById(R.id.quick_contact_photo), view2.findViewById(R.id.primary_action_view), PhoneCallDetailsViews.fromView(view), (CardView) view2.findViewById(R.id.call_log_row), (TextView) view2.findViewById(R.id.call_log_day_group_label), (ImageView) view2.findViewById(R.id.primary_action_button));
    }

    private int getContactType() {
        return LetterTileDrawable.getContactTypeFromPrimitives(this.callLogCache.isVoicemailNumber(this.accountHandle, this.number), this.isSpam, false, this.numberPresentation, false);
    }

    private void maybeShowBlockNumberMigrationDialog(BlockedNumbersMigrator.Listener listener) {
        Context context2 = this.context;
        if (!FilteredNumberCompat.maybeShowBlockNumberMigrationDialog(context2, ((Activity) context2).getFragmentManager(), listener)) {
            listener.onComplete();
        }
    }

    public CallDetailsEntries getDetailedPhoneDetails() {
        return this.callDetailsEntries;
    }

    public void inflateActionViewStub() {
        ViewStub viewStub = (ViewStub) this.rootView.findViewById(R.id.call_log_entry_actions_stub);
        if (viewStub != null) {
            this.actionsView = viewStub.inflate();
            this.voicemailPlaybackView = (VoicemailPlaybackLayout) this.actionsView.findViewById(R.id.voicemail_playback_layout);
            this.voicemailPlaybackView.setViewHolder(this);
            this.callButtonView = this.actionsView.findViewById(R.id.call_action);
            this.callButtonView.setOnClickListener(this);
            this.videoCallButtonView = this.actionsView.findViewById(R.id.video_call_action);
            this.videoCallButtonView.setOnClickListener(this);
            this.setUpVideoButtonView = this.actionsView.findViewById(R.id.set_up_video_action);
            this.setUpVideoButtonView.setOnClickListener(this);
            this.inviteVideoButtonView = this.actionsView.findViewById(R.id.invite_video_action);
            this.inviteVideoButtonView.setOnClickListener(this);
            this.createNewContactButtonView = this.actionsView.findViewById(R.id.create_new_contact_action);
            this.createNewContactButtonView.setOnClickListener(this);
            this.addToExistingContactButtonView = this.actionsView.findViewById(R.id.add_to_existing_contact_action);
            this.addToExistingContactButtonView.setOnClickListener(this);
            this.sendMessageView = this.actionsView.findViewById(R.id.send_message_action);
            this.sendMessageView.setOnClickListener(this);
            this.blockReportView = this.actionsView.findViewById(R.id.block_report_action);
            this.blockReportView.setOnClickListener(this);
            this.blockView = this.actionsView.findViewById(R.id.block_action);
            this.blockView.setOnClickListener(this);
            this.unblockView = this.actionsView.findViewById(R.id.unblock_action);
            this.unblockView.setOnClickListener(this);
            this.reportNotSpamView = this.actionsView.findViewById(R.id.report_not_spam_action);
            this.reportNotSpamView.setOnClickListener(this);
            this.detailsButtonView = this.actionsView.findViewById(R.id.details_action);
            this.detailsButtonView.setOnClickListener(this);
            this.callWithNoteButtonView = this.actionsView.findViewById(R.id.call_with_note_action);
            this.callWithNoteButtonView.setOnClickListener(this);
            this.callComposeButtonView = this.actionsView.findViewById(R.id.call_compose_action);
            this.callComposeButtonView.setOnClickListener(this);
            this.sendVoicemailButtonView = this.actionsView.findViewById(R.id.share_voicemail);
            this.sendVoicemailButtonView.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.primary_action_button) {
            CallLogAsyncTaskUtil.markCallAsRead(this.context, this.callIds);
        }
        if (view.getId() == R.id.primary_action_button && !TextUtils.isEmpty(this.voicemailUri)) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VOICEMAIL_PLAY_AUDIO_DIRECTLY);
            this.voicemailPrimaryActionButtonClicked = true;
            this.expandCollapseListener.onClick(this.primaryActionView);
        } else if (view.getId() == R.id.call_with_note_action) {
            CallSubjectDialog.start((Activity) this.context, this.info.photoId, this.info.photoUri, this.info.lookupUri, (String) this.nameOrNumber, this.number, TextUtils.isEmpty(this.info.name) ? null : this.displayNumber, this.numberType, getContactType(), this.accountHandle);
        } else if (view.getId() == R.id.block_report_action) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_BLOCK_REPORT_SPAM);
            maybeShowBlockNumberMigrationDialog(new BlockedNumbersMigrator.Listener() {
                public void onComplete() {
                    OnClickListener access$000 = CallLogListItemViewHolder.this.blockReportListener;
                    CallLogListItemViewHolder callLogListItemViewHolder = CallLogListItemViewHolder.this;
                    ((BlockReportSpamListener) access$000).onBlockReportSpam(callLogListItemViewHolder.displayNumber, callLogListItemViewHolder.number, callLogListItemViewHolder.countryIso, callLogListItemViewHolder.callType, callLogListItemViewHolder.info.sourceType);
                }
            });
        } else if (view.getId() == R.id.block_action) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_BLOCK_NUMBER);
            maybeShowBlockNumberMigrationDialog(new BlockedNumbersMigrator.Listener() {
                public void onComplete() {
                    OnClickListener access$000 = CallLogListItemViewHolder.this.blockReportListener;
                    CallLogListItemViewHolder callLogListItemViewHolder = CallLogListItemViewHolder.this;
                    ((BlockReportSpamListener) access$000).onBlock(callLogListItemViewHolder.displayNumber, callLogListItemViewHolder.number, callLogListItemViewHolder.countryIso, callLogListItemViewHolder.callType, callLogListItemViewHolder.info.sourceType);
                }
            });
        } else if (view.getId() == R.id.unblock_action) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_UNBLOCK_NUMBER);
            ((BlockReportSpamListener) this.blockReportListener).onUnblock(this.displayNumber, this.number, this.countryIso, this.callType, this.info.sourceType, this.isSpam, this.blockId);
        } else if (view.getId() == R.id.report_not_spam_action) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_REPORT_AS_NOT_SPAM);
            ((BlockReportSpamListener) this.blockReportListener).onReportNotSpam(this.displayNumber, this.number, this.countryIso, this.callType, this.info.sourceType);
        } else if (view.getId() == R.id.call_compose_action) {
            LogUtil.m9i("CallLogListItemViewHolder.onClick", "share and call pressed", new Object[0]);
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_SHARE_AND_CALL);
            Activity activity = (Activity) this.context;
            activity.startActivityForResult(CallComposerActivity.newIntent(activity, buildContact()), 2);
        } else if (view.getId() == R.id.share_voicemail) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VVM_SHARE_PRESSED);
            this.voicemailPlaybackPresenter.shareVoicemail();
        } else {
            int id = view.getId();
            if (id == R.id.send_message_action) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_SEND_MESSAGE);
            } else if (id == R.id.add_to_existing_contact_action) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_ADD_TO_CONTACT);
                int i = this.hostUi;
                if (i == 0) {
                    ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.ADD_TO_A_CONTACT_FROM_CALL_LOG);
                } else if (i == 1) {
                    ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.ADD_TO_A_CONTACT_FROM_CALL_HISTORY);
                } else if (i == 2) {
                    ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.ADD_TO_A_CONTACT_FROM_VOICEMAIL);
                } else {
                    throw new IllegalStateException();
                }
            } else if (id == R.id.create_new_contact_action) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_CREATE_NEW_CONTACT);
                int i2 = this.hostUi;
                if (i2 == 0) {
                    ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CREATE_NEW_CONTACT_FROM_CALL_LOG);
                } else if (i2 == 1) {
                    ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CREATE_NEW_CONTACT_FROM_CALL_HISTORY);
                } else if (i2 == 2) {
                    ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CREATE_NEW_CONTACT_FROM_VOICEMAIL);
                } else {
                    throw new IllegalStateException();
                }
            }
            IntentProvider intentProvider = (IntentProvider) view.getTag();
            if (intentProvider != null) {
                intentProvider.logInteraction(this.context);
                Intent clickIntent = intentProvider.getClickIntent(this.context);
                if (clickIntent != null) {
                    if (OldCallDetailsActivity.isLaunchIntent(clickIntent)) {
                        PerformanceReport.recordClick(UiAction$Type.OPEN_CALL_DETAIL);
                        ((Activity) this.context).startActivityForResult(clickIntent, 4);
                        return;
                    }
                    if ("android.intent.action.CALL".equals(clickIntent.getAction()) && clickIntent.getIntExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", -1) == 3) {
                        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.IMS_VIDEO_REQUESTED_FROM_CALL_LOG);
                    }
                    DialerUtils.startActivityWithErrorToast(this.context, clickIntent, R.string.activity_not_available);
                }
            }
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        if (!TextUtils.isEmpty(this.number)) {
            if (this.callType == 4) {
                contextMenu.setHeaderTitle(this.context.getResources().getText(R.string.voicemail));
            } else {
                contextMenu.setHeaderTitle(PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(this.number, TextDirectionHeuristics.LTR)));
            }
            contextMenu.add(0, R.id.context_menu_copy_to_clipboard, 0, R.string.action_copy_number_text).setOnMenuItemClickListener(this);
            boolean z = true;
            if (PhoneNumberHelper.canPlaceCallsTo(this.number, this.numberPresentation) && !this.callLogCache.isVoicemailNumber(this.accountHandle, this.number)) {
                String str = this.number;
                if (!(str != null && PhoneNumberHelper.isUriNumber(str.toString()))) {
                    contextMenu.add(0, R.id.context_menu_edit_before_call, 0, R.string.action_edit_number_before_call).setOnMenuItemClickListener(this);
                }
            }
            if (this.callType == 4 && this.phoneCallDetailsViews.voicemailTranscriptionView.length() > 0) {
                contextMenu.add(0, R.id.context_menu_copy_transcript_to_clipboard, 0, R.string.copy_transcript_text).setOnMenuItemClickListener(this);
            }
            String formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(this.number, this.countryIso);
            boolean isVoicemailNumber = this.callLogCache.isVoicemailNumber(this.accountHandle, this.number);
            if (PhoneNumberHelper.canPlaceCallsTo(this.number, this.numberPresentation) && !isVoicemailNumber && FilteredNumbersUtil.canBlockNumber(this.context, formatNumberToE164, this.number) && FilteredNumberCompat.canAttemptBlockOperations(this.context)) {
                if (this.blockId == null) {
                    z = false;
                }
                if (z) {
                    contextMenu.add(0, R.id.context_menu_unblock, 0, R.string.call_log_action_unblock_number).setOnMenuItemClickListener(this);
                } else if (!this.isSpamFeatureEnabled) {
                    contextMenu.add(0, R.id.context_menu_block, 0, R.string.call_log_action_block_number).setOnMenuItemClickListener(this);
                } else if (this.isSpam) {
                    contextMenu.add(0, R.id.context_menu_report_not_spam, 0, R.string.call_log_action_remove_spam).setOnMenuItemClickListener(this);
                    contextMenu.add(0, R.id.context_menu_block, 0, R.string.call_log_action_block_number).setOnMenuItemClickListener(this);
                } else {
                    contextMenu.add(0, R.id.context_menu_block_report_spam, 0, R.string.call_log_action_block_report_number).setOnMenuItemClickListener(this);
                }
            }
            if (this.callType != 4) {
                contextMenu.add(0, R.id.context_menu_delete, 0, R.string.delete).setOnMenuItemClickListener(this);
            }
            ((LoggingBindingsStub) Logger.get(this.context)).logScreenView(ScreenEvent$Type.CALL_LOG_CONTEXT_MENU, (Activity) this.context);
        }
    }

    public boolean onLongClick(View view) {
        IntentProvider intentProvider = (IntentProvider) view.getTag();
        Intent longClickIntent = intentProvider != null ? intentProvider.getLongClickIntent(this.context) : null;
        if (longClickIntent == null) {
            return false;
        }
        DialerUtils.startActivityWithErrorToast(this.context, longClickIntent, R.string.activity_not_available);
        return true;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.context_menu_copy_to_clipboard) {
            R$style.copyText(this.context, (CharSequence) null, this.number, true);
            return true;
        } else if (itemId == R.id.context_menu_copy_transcript_to_clipboard) {
            R$style.copyText(this.context, (CharSequence) null, this.phoneCallDetailsViews.voicemailTranscriptionView.getText(), true);
            return true;
        } else if (itemId == R.id.context_menu_edit_before_call) {
            DialerUtils.startActivityWithErrorToast(this.context, new Intent("android.intent.action.DIAL", CallUtil.getCallUri(this.number)), R.string.activity_not_available);
            return true;
        } else {
            if (itemId == R.id.context_menu_block_report_spam) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_CONTEXT_MENU_BLOCK_REPORT_SPAM);
                maybeShowBlockNumberMigrationDialog(new BlockedNumbersMigrator.Listener() {
                    public void onComplete() {
                        OnClickListener access$000 = CallLogListItemViewHolder.this.blockReportListener;
                        CallLogListItemViewHolder callLogListItemViewHolder = CallLogListItemViewHolder.this;
                        ((BlockReportSpamListener) access$000).onBlockReportSpam(callLogListItemViewHolder.displayNumber, callLogListItemViewHolder.number, callLogListItemViewHolder.countryIso, callLogListItemViewHolder.callType, callLogListItemViewHolder.info.sourceType);
                    }
                });
            } else if (itemId == R.id.context_menu_block) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_CONTEXT_MENU_BLOCK_NUMBER);
                maybeShowBlockNumberMigrationDialog(new BlockedNumbersMigrator.Listener() {
                    public void onComplete() {
                        OnClickListener access$000 = CallLogListItemViewHolder.this.blockReportListener;
                        CallLogListItemViewHolder callLogListItemViewHolder = CallLogListItemViewHolder.this;
                        ((BlockReportSpamListener) access$000).onBlock(callLogListItemViewHolder.displayNumber, callLogListItemViewHolder.number, callLogListItemViewHolder.countryIso, callLogListItemViewHolder.callType, callLogListItemViewHolder.info.sourceType);
                    }
                });
            } else if (itemId == R.id.context_menu_unblock) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_CONTEXT_MENU_UNBLOCK_NUMBER);
                ((BlockReportSpamListener) this.blockReportListener).onUnblock(this.displayNumber, this.number, this.countryIso, this.callType, this.info.sourceType, this.isSpam, this.blockId);
            } else if (itemId == R.id.context_menu_report_not_spam) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CALL_LOG_CONTEXT_MENU_REPORT_AS_NOT_SPAM);
                ((BlockReportSpamListener) this.blockReportListener).onReportNotSpam(this.displayNumber, this.number, this.countryIso, this.callType, this.info.sourceType);
            } else if (itemId == R.id.context_menu_delete) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.USER_DELETED_CALL_LOG_ITEM);
                AsyncTaskExecutors.createAsyncTaskExecutor().submit("task_delete", new DeleteCallTask(this.context, this.callIds), new Void[0]);
            }
            return false;
        }
    }

    public void setDetailedPhoneDetails(CallDetailsEntries callDetailsEntries2) {
        this.callDetailsEntries = callDetailsEntries2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:84:0x023c, code lost:
        if (((!r0.callLogCache.canRelyOnVideoPresence() || r0.info == null || (r0.info.carrierPresence & 1) == 0) ? false : true) != false) goto L_0x023e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0152  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showActions(boolean r19) {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.callType
            r2 = 4
            r3 = 0
            r4 = 1
            r5 = 8
            if (r1 == r2) goto L_0x000c
            goto L_0x0048
        L_0x000c:
            com.android.dialer.app.calllog.PhoneCallDetailsViews r1 = r0.phoneCallDetailsViews
            android.view.View r6 = r1.transcriptionView
            android.widget.TextView r7 = r1.voicemailTranscriptionView
            android.widget.TextView r1 = r1.voicemailTranscriptionBrandingView
            if (r19 != 0) goto L_0x001a
            r6.setVisibility(r5)
            goto L_0x0048
        L_0x001a:
            java.lang.CharSequence r8 = r7.getText()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L_0x0029
            r7.setVisibility(r5)
            r7 = r3
            goto L_0x002d
        L_0x0029:
            r7.setVisibility(r3)
            r7 = r4
        L_0x002d:
            java.lang.CharSequence r8 = r1.getText()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L_0x003b
            r1.setVisibility(r5)
            goto L_0x003f
        L_0x003b:
            r1.setVisibility(r3)
            r7 = r4
        L_0x003f:
            if (r7 == 0) goto L_0x0045
            r6.setVisibility(r3)
            goto L_0x0048
        L_0x0045:
            r6.setVisibility(r5)
        L_0x0048:
            r1 = 3
            r6 = 2
            java.lang.String r7 = ""
            if (r19 == 0) goto L_0x03e3
            boolean r8 = r0.isLoaded
            if (r8 != 0) goto L_0x005f
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>()
            java.lang.String r1 = "CallLogListItemViewHolder.showActions"
            java.lang.String r2 = "called before item is loaded"
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
            return
        L_0x005f:
            r18.inflateActionViewStub()
            java.lang.String r8 = r0.number
            int r9 = r0.numberPresentation
            boolean r8 = com.android.dialer.phonenumberutil.PhoneNumberHelper.canPlaceCallsTo(r8, r9)
            android.view.View r9 = r0.callButtonView
            r9.setVisibility(r5)
            android.view.View r9 = r0.videoCallButtonView
            r9.setVisibility(r5)
            android.view.View r9 = r0.setUpVideoButtonView
            r9.setVisibility(r5)
            android.view.View r9 = r0.inviteVideoButtonView
            r9.setVisibility(r5)
            android.content.Context r9 = r0.context
            java.lang.String r10 = r0.number
            boolean r9 = com.android.dialer.phonenumberutil.PhoneNumberHelper.isLocalEmergencyNumber(r9, r10)
            if (r9 == 0) goto L_0x00d0
            android.view.View r2 = r0.createNewContactButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.addToExistingContactButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.sendMessageView
            r2.setVisibility(r5)
            android.view.View r2 = r0.callWithNoteButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.callComposeButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.blockReportView
            r2.setVisibility(r5)
            android.view.View r2 = r0.blockView
            r2.setVisibility(r5)
            android.view.View r2 = r0.unblockView
            r2.setVisibility(r5)
            android.view.View r2 = r0.reportNotSpamView
            r2.setVisibility(r5)
            com.android.dialer.app.voicemail.VoicemailPlaybackLayout r2 = r0.voicemailPlaybackView
            r2.setVisibility(r5)
            android.view.View r2 = r0.detailsButtonView
            r2.setVisibility(r3)
            android.view.View r2 = r0.detailsButtonView
            com.android.dialer.calldetails.CallDetailsEntries r8 = r0.callDetailsEntries
            com.android.dialer.dialercontact.DialerContact r9 = r18.buildContact()
            com.android.dialer.app.calllog.IntentProvider r8 = com.android.dialer.app.calllog.IntentProvider.getCallDetailIntentProvider(r8, r9, r3, r3)
            r2.setTag(r8)
            goto L_0x03d6
        L_0x00d0:
            int r9 = r0.callType
            if (r9 != r2) goto L_0x00fb
            java.lang.String r9 = r0.number
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 == 0) goto L_0x00dd
            goto L_0x00f6
        L_0x00dd:
            java.lang.String r9 = r9.toString()
            char[] r9 = r9.toCharArray()
            int r10 = r9.length
            r11 = r3
        L_0x00e7:
            if (r11 >= r10) goto L_0x00f6
            char r12 = r9[r11]
            boolean r12 = android.telephony.PhoneNumberUtils.isDialable(r12)
            if (r12 == 0) goto L_0x00f3
            r9 = r4
            goto L_0x00f7
        L_0x00f3:
            int r11 = r11 + 1
            goto L_0x00e7
        L_0x00f6:
            r9 = r3
        L_0x00f7:
            if (r9 != 0) goto L_0x00fb
            r9 = r4
            goto L_0x00fc
        L_0x00fb:
            r9 = r3
        L_0x00fc:
            if (r9 == 0) goto L_0x0152
            android.view.View r2 = r0.detailsButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.createNewContactButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.addToExistingContactButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.sendMessageView
            r2.setVisibility(r5)
            android.view.View r2 = r0.callWithNoteButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.callComposeButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.blockReportView
            r2.setVisibility(r5)
            android.view.View r2 = r0.blockView
            r2.setVisibility(r5)
            android.view.View r2 = r0.unblockView
            r2.setVisibility(r5)
            android.view.View r2 = r0.reportNotSpamView
            r2.setVisibility(r5)
            com.android.dialer.app.voicemail.VoicemailPlaybackLayout r2 = r0.voicemailPlaybackView
            r2.setVisibility(r3)
            java.lang.String r2 = r0.voicemailUri
            android.net.Uri r2 = android.net.Uri.parse(r2)
            com.android.dialer.app.voicemail.VoicemailPlaybackPresenter r8 = r0.voicemailPlaybackPresenter
            com.android.dialer.app.voicemail.VoicemailPlaybackLayout r9 = r0.voicemailPlaybackView
            long r10 = r0.rowId
            boolean r13 = r0.voicemailPrimaryActionButtonClicked
            android.view.View r14 = r0.sendVoicemailButtonView
            r12 = r2
            r8.setPlaybackView(r9, r10, r12, r13, r14)
            r0.voicemailPrimaryActionButtonClicked = r3
            android.content.Context r8 = r0.context
            com.android.dialer.app.calllog.CallLogAsyncTaskUtil.markVoicemailAsRead(r8, r2)
            goto L_0x03d6
        L_0x0152:
            android.view.View r9 = r0.callButtonView
            r10 = 2131296373(0x7f090075, float:1.821066E38)
            android.view.View r9 = r9.findViewById(r10)
            android.widget.TextView r9 = (android.widget.TextView) r9
            if (r8 == 0) goto L_0x0189
            boolean r10 = r18.canSupportAssistedDialing()
            if (r10 == 0) goto L_0x017b
            android.view.View r10 = r0.callButtonView
            java.lang.String r11 = r0.number
            android.content.Context r12 = r0.context
            java.lang.Class<android.telephony.TelephonyManager> r13 = android.telephony.TelephonyManager.class
            java.lang.Object r12 = r12.getSystemService(r13)
            android.telephony.TelephonyManager r12 = (android.telephony.TelephonyManager) r12
            com.android.dialer.app.calllog.IntentProvider r11 = com.android.dialer.app.calllog.IntentProvider.getAssistedDialIntentProvider(r11)
            r10.setTag(r11)
            goto L_0x0186
        L_0x017b:
            android.view.View r10 = r0.callButtonView
            java.lang.String r11 = r0.number
            com.android.dialer.app.calllog.IntentProvider r11 = com.android.dialer.app.calllog.IntentProvider.getReturnCallIntentProvider(r11)
            r10.setTag(r11)
        L_0x0186:
            r9.setVisibility(r5)
        L_0x0189:
            java.lang.String r10 = r0.voicemailUri
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x01d0
            if (r8 == 0) goto L_0x01d0
            android.view.View r10 = r0.callButtonView
            r11 = 2131296338(0x7f090052, float:1.821059E38)
            android.view.View r10 = r10.findViewById(r11)
            android.widget.TextView r10 = (android.widget.TextView) r10
            android.content.Context r11 = r0.context
            r12 = 2131820721(0x7f1100b1, float:1.9274165E38)
            java.lang.String r11 = r11.getString(r12)
            java.lang.CharSequence[] r12 = new java.lang.CharSequence[r4]
            java.lang.CharSequence r13 = r0.nameOrNumber
            if (r13 != 0) goto L_0x01ae
            r13 = r7
        L_0x01ae:
            r12[r3] = r13
            java.lang.CharSequence r11 = android.text.TextUtils.expandTemplate(r11, r12)
            r10.setText(r11)
            int r10 = r0.callType
            if (r10 != r2) goto L_0x01cb
            java.lang.CharSequence r10 = r0.callTypeOrLocation
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x01cb
            java.lang.CharSequence r10 = r0.callTypeOrLocation
            r9.setText(r10)
            r9.setVisibility(r3)
        L_0x01cb:
            android.view.View r9 = r0.callButtonView
            r9.setVisibility(r3)
        L_0x01d0:
            com.android.dialer.app.calllog.calllogcache.CallLogCache r9 = r0.callLogCache
            android.telecom.PhoneAccountHandle r10 = r0.accountHandle
            java.lang.String r11 = r0.number
            boolean r9 = r9.isVoicemailNumber(r10, r11)
            int r10 = r0.callbackAction
            if (r10 == r4) goto L_0x025e
            if (r10 == r6) goto L_0x025e
            if (r10 == r1) goto L_0x01ee
            android.view.View r10 = r0.callButtonView
            r10.setVisibility(r5)
            android.view.View r10 = r0.videoCallButtonView
            r10.setVisibility(r5)
            goto L_0x0268
        L_0x01ee:
            android.content.Context r10 = r0.context
            com.android.dialer.duo.DuoComponent r10 = com.android.dialer.duo.DuoComponent.get(r10)
            com.android.dialer.duo.Duo r10 = r10.getDuo()
            android.content.Context r11 = r0.context
            boolean r11 = com.android.dialer.util.CallUtil.isVideoEnabled(r11)
            if (r11 == 0) goto L_0x024f
            com.android.dialer.app.calllog.PhoneCallDetailsViews r11 = r0.phoneCallDetailsViews
            com.android.dialer.calllogutils.CallTypeIconsView r11 = r11.callTypeIcons
            boolean r11 = r11.isVideoShown()
            if (r11 != 0) goto L_0x020b
            goto L_0x0214
        L_0x020b:
            android.telecom.PhoneAccountHandle r11 = r0.accountHandle
            if (r11 != 0) goto L_0x0210
            goto L_0x0214
        L_0x0210:
            android.telecom.PhoneAccountHandle r12 = r0.defaultPhoneAccountHandle
            if (r12 != 0) goto L_0x0216
        L_0x0214:
            r11 = r3
            goto L_0x0224
        L_0x0216:
            android.content.ComponentName r11 = r11.getComponentName()
            android.telecom.PhoneAccountHandle r12 = r0.defaultPhoneAccountHandle
            android.content.ComponentName r12 = r12.getComponentName()
            boolean r11 = r11.equals(r12)
        L_0x0224:
            if (r11 != 0) goto L_0x023e
            com.android.dialer.app.calllog.calllogcache.CallLogCache r11 = r0.callLogCache
            boolean r11 = r11.canRelyOnVideoPresence()
            if (r11 == 0) goto L_0x023b
            com.android.dialer.phonenumbercache.ContactInfo r11 = r0.info
            if (r11 == 0) goto L_0x023b
            com.android.dialer.phonenumbercache.ContactInfo r11 = r0.info
            int r11 = r11.carrierPresence
            r11 = r11 & r4
            if (r11 == 0) goto L_0x023b
            r11 = r4
            goto L_0x023c
        L_0x023b:
            r11 = r3
        L_0x023c:
            if (r11 == 0) goto L_0x024f
        L_0x023e:
            android.view.View r10 = r0.videoCallButtonView
            java.lang.String r11 = r0.number
            com.android.dialer.app.calllog.IntentProvider r11 = com.android.dialer.app.calllog.IntentProvider.getReturnVideoCallIntentProvider(r11)
            r10.setTag(r11)
            android.view.View r10 = r0.videoCallButtonView
            r10.setVisibility(r3)
            goto L_0x0268
        L_0x024f:
            if (r9 == 0) goto L_0x0252
            goto L_0x0268
        L_0x0252:
            boolean r11 = r0.isSpamFeatureEnabled
            android.content.Context r11 = r0.context
            java.lang.String r12 = r0.number
            com.android.dialer.duo.stub.DuoStub r10 = (com.android.dialer.duo.stub.DuoStub) r10
            r10.isReachable(r11, r12)
            goto L_0x0268
        L_0x025e:
            android.view.View r10 = r0.callButtonView
            r10.setVisibility(r3)
            android.view.View r10 = r0.videoCallButtonView
            r10.setVisibility(r5)
        L_0x0268:
            int r10 = r0.callType
            if (r10 != r2) goto L_0x029d
            com.android.dialer.app.voicemail.VoicemailPlaybackPresenter r10 = r0.voicemailPlaybackPresenter
            if (r10 == 0) goto L_0x029d
            java.lang.String r10 = r0.voicemailUri
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x029d
            com.android.dialer.app.voicemail.VoicemailPlaybackLayout r10 = r0.voicemailPlaybackView
            r10.setVisibility(r3)
            java.lang.String r10 = r0.voicemailUri
            android.net.Uri r10 = android.net.Uri.parse(r10)
            com.android.dialer.app.voicemail.VoicemailPlaybackPresenter r11 = r0.voicemailPlaybackPresenter
            com.android.dialer.app.voicemail.VoicemailPlaybackLayout r12 = r0.voicemailPlaybackView
            long r13 = r0.rowId
            boolean r15 = r0.voicemailPrimaryActionButtonClicked
            android.view.View r1 = r0.sendVoicemailButtonView
            r16 = r15
            r15 = r10
            r17 = r1
            r11.setPlaybackView(r12, r13, r15, r16, r17)
            r0.voicemailPrimaryActionButtonClicked = r3
            android.content.Context r1 = r0.context
            com.android.dialer.app.calllog.CallLogAsyncTaskUtil.markVoicemailAsRead(r1, r10)
            goto L_0x02a7
        L_0x029d:
            com.android.dialer.app.voicemail.VoicemailPlaybackLayout r1 = r0.voicemailPlaybackView
            r1.setVisibility(r5)
            android.view.View r1 = r0.sendVoicemailButtonView
            r1.setVisibility(r5)
        L_0x02a7:
            int r1 = r0.callType
            if (r1 != r2) goto L_0x02b1
            android.view.View r1 = r0.detailsButtonView
            r1.setVisibility(r5)
            goto L_0x02c9
        L_0x02b1:
            android.view.View r1 = r0.detailsButtonView
            r1.setVisibility(r3)
            android.view.View r1 = r0.detailsButtonView
            com.android.dialer.calldetails.CallDetailsEntries r2 = r0.callDetailsEntries
            com.android.dialer.dialercontact.DialerContact r10 = r18.buildContact()
            boolean r11 = r18.canSupportAssistedDialing()
            com.android.dialer.app.calllog.IntentProvider r2 = com.android.dialer.app.calllog.IntentProvider.getCallDetailIntentProvider(r2, r10, r3, r11)
            r1.setTag(r2)
        L_0x02c9:
            java.lang.Integer r1 = r0.blockId
            if (r1 != 0) goto L_0x02d8
            boolean r1 = r0.isSpamFeatureEnabled
            if (r1 == 0) goto L_0x02d6
            boolean r1 = r0.isSpam
            if (r1 == 0) goto L_0x02d6
            goto L_0x02d8
        L_0x02d6:
            r1 = r3
            goto L_0x02d9
        L_0x02d8:
            r1 = r4
        L_0x02d9:
            if (r1 != 0) goto L_0x0326
            com.android.dialer.phonenumbercache.ContactInfo r2 = r0.info
            if (r2 == 0) goto L_0x0326
            com.android.dialer.phonenumbercache.ContactInfo r2 = r0.info
            android.net.Uri r2 = r2.lookupUri
            boolean r2 = android.support.p002v7.appcompat.R$style.isEncodedContactUri(r2)
            if (r2 == 0) goto L_0x0326
            android.view.View r2 = r0.createNewContactButtonView
            com.android.dialer.phonenumbercache.ContactInfo r10 = r0.info
            android.net.Uri r10 = r10.lookupUri
            com.android.dialer.phonenumbercache.ContactInfo r11 = r0.info
            java.lang.String r11 = r11.name
            com.android.dialer.phonenumbercache.ContactInfo r12 = r0.info
            java.lang.String r12 = r12.number
            com.android.dialer.phonenumbercache.ContactInfo r13 = r0.info
            int r13 = r13.type
            com.android.dialer.app.calllog.IntentProvider r10 = com.android.dialer.app.calllog.IntentProvider.getAddContactIntentProvider(r10, r11, r12, r13, r4)
            r2.setTag(r10)
            android.view.View r2 = r0.createNewContactButtonView
            r2.setVisibility(r3)
            android.view.View r2 = r0.addToExistingContactButtonView
            com.android.dialer.phonenumbercache.ContactInfo r10 = r0.info
            android.net.Uri r10 = r10.lookupUri
            com.android.dialer.phonenumbercache.ContactInfo r11 = r0.info
            java.lang.String r11 = r11.name
            com.android.dialer.phonenumbercache.ContactInfo r12 = r0.info
            java.lang.String r12 = r12.number
            com.android.dialer.phonenumbercache.ContactInfo r13 = r0.info
            int r13 = r13.type
            com.android.dialer.app.calllog.IntentProvider r10 = com.android.dialer.app.calllog.IntentProvider.getAddContactIntentProvider(r10, r11, r12, r13, r3)
            r2.setTag(r10)
            android.view.View r2 = r0.addToExistingContactButtonView
            r2.setVisibility(r3)
            goto L_0x0330
        L_0x0326:
            android.view.View r2 = r0.createNewContactButtonView
            r2.setVisibility(r5)
            android.view.View r2 = r0.addToExistingContactButtonView
            r2.setVisibility(r5)
        L_0x0330:
            if (r8 == 0) goto L_0x0348
            if (r1 != 0) goto L_0x0348
            if (r9 != 0) goto L_0x0348
            android.view.View r1 = r0.sendMessageView
            java.lang.String r2 = r0.number
            com.android.dialer.app.calllog.IntentProvider$9 r10 = new com.android.dialer.app.calllog.IntentProvider$9
            r10.<init>(r2)
            r1.setTag(r10)
            android.view.View r1 = r0.sendMessageView
            r1.setVisibility(r3)
            goto L_0x034d
        L_0x0348:
            android.view.View r1 = r0.sendMessageView
            r1.setVisibility(r5)
        L_0x034d:
            com.android.dialer.app.calllog.CallLogListItemHelper r1 = r0.callLogListItemHelper
            r1.setActionContentDescriptions(r0)
            com.android.dialer.app.calllog.calllogcache.CallLogCache r1 = r0.callLogCache
            android.telecom.PhoneAccountHandle r2 = r0.accountHandle
            boolean r1 = r1.doesAccountSupportCallSubject(r2)
            android.view.View r2 = r0.callWithNoteButtonView
            if (r1 == 0) goto L_0x0366
            if (r9 != 0) goto L_0x0366
            com.android.dialer.phonenumbercache.ContactInfo r1 = r0.info
            if (r1 == 0) goto L_0x0366
            r1 = r3
            goto L_0x0367
        L_0x0366:
            r1 = r5
        L_0x0367:
            r2.setVisibility(r1)
            android.view.View r1 = r0.callComposeButtonView
            boolean r2 = r0.isCallComposerCapable
            if (r2 == 0) goto L_0x0372
            r2 = r3
            goto L_0x0373
        L_0x0372:
            r2 = r5
        L_0x0373:
            r1.setVisibility(r2)
            android.view.View r1 = r0.blockReportView
            r1.setVisibility(r5)
            android.view.View r1 = r0.blockView
            r1.setVisibility(r5)
            android.view.View r1 = r0.unblockView
            r1.setVisibility(r5)
            android.view.View r1 = r0.reportNotSpamView
            r1.setVisibility(r5)
            java.lang.String r1 = r0.number
            java.lang.String r2 = r0.countryIso
            java.lang.String r1 = android.telephony.PhoneNumberUtils.formatNumberToE164(r1, r2)
            if (r8 == 0) goto L_0x03d6
            if (r9 != 0) goto L_0x03d6
            android.content.Context r2 = r0.context
            java.lang.String r8 = r0.number
            boolean r1 = com.android.dialer.blocking.FilteredNumbersUtil.canBlockNumber(r2, r1, r8)
            if (r1 == 0) goto L_0x03d6
            android.content.Context r1 = r0.context
            boolean r1 = com.android.dialer.blocking.FilteredNumberCompat.canAttemptBlockOperations(r1)
            if (r1 != 0) goto L_0x03a9
            goto L_0x03d6
        L_0x03a9:
            java.lang.Integer r1 = r0.blockId
            if (r1 == 0) goto L_0x03af
            r1 = r4
            goto L_0x03b0
        L_0x03af:
            r1 = r3
        L_0x03b0:
            if (r1 == 0) goto L_0x03b8
            android.view.View r1 = r0.unblockView
            r1.setVisibility(r3)
            goto L_0x03d6
        L_0x03b8:
            boolean r1 = r0.isSpamFeatureEnabled
            if (r1 == 0) goto L_0x03d1
            boolean r1 = r0.isSpam
            if (r1 == 0) goto L_0x03cb
            android.view.View r1 = r0.blockView
            r1.setVisibility(r3)
            android.view.View r1 = r0.reportNotSpamView
            r1.setVisibility(r3)
            goto L_0x03d6
        L_0x03cb:
            android.view.View r1 = r0.blockReportView
            r1.setVisibility(r3)
            goto L_0x03d6
        L_0x03d1:
            android.view.View r1 = r0.blockView
            r1.setVisibility(r3)
        L_0x03d6:
            android.view.View r1 = r0.actionsView
            r1.setVisibility(r3)
            android.view.View r1 = r0.actionsView
            r2 = 1065353216(0x3f800000, float:1.0)
            r1.setAlpha(r2)
            goto L_0x03ea
        L_0x03e3:
            android.view.View r1 = r0.actionsView
            if (r1 == 0) goto L_0x03ea
            r1.setVisibility(r5)
        L_0x03ea:
            java.lang.CharSequence r1 = r0.nameOrNumber
            if (r1 != 0) goto L_0x03f7
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r2 = "CallLogListItemViewHolder.updatePrimaryActionButton"
            java.lang.String r8 = "name or number is null"
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r2, (java.lang.String) r8, (java.lang.Object[]) r1)
        L_0x03f7:
            java.lang.CharSequence r1 = r0.nameOrNumber
            if (r1 != 0) goto L_0x03fc
            r1 = r7
        L_0x03fc:
            java.lang.String r2 = r0.voicemailUri
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            r7 = 0
            if (r2 != 0) goto L_0x0438
            if (r19 != 0) goto L_0x0431
            android.widget.ImageView r2 = r0.primaryActionButtonView
            r5 = 2131231050(0x7f08014a, float:1.807817E38)
            r2.setImageResource(r5)
            android.widget.ImageView r2 = r0.primaryActionButtonView
            android.content.Context r5 = r0.context
            r6 = 2131820890(0x7f11015a, float:1.9274508E38)
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence[] r4 = new java.lang.CharSequence[r4]
            r4[r3] = r1
            java.lang.CharSequence r1 = android.text.TextUtils.expandTemplate(r5, r4)
            r2.setContentDescription(r1)
            android.widget.ImageView r1 = r0.primaryActionButtonView
            r1.setTag(r7)
            android.widget.ImageView r0 = r0.primaryActionButtonView
            r0.setVisibility(r3)
            goto L_0x0553
        L_0x0431:
            android.widget.ImageView r0 = r0.primaryActionButtonView
            r0.setVisibility(r5)
            goto L_0x0553
        L_0x0438:
            java.lang.String r2 = r0.number
            int r8 = r0.numberPresentation
            boolean r2 = com.android.dialer.phonenumberutil.PhoneNumberHelper.canPlaceCallsTo(r2, r8)
            if (r2 != 0) goto L_0x044e
            android.widget.ImageView r1 = r0.primaryActionButtonView
            r1.setTag(r7)
            android.widget.ImageView r0 = r0.primaryActionButtonView
            r0.setVisibility(r5)
            goto L_0x0553
        L_0x044e:
            int r2 = r0.callbackAction
            r8 = 2131231077(0x7f080165, float:1.8078225E38)
            r9 = 2131820888(0x7f110158, float:1.9274504E38)
            if (r2 == r4) goto L_0x0529
            if (r2 == r6) goto L_0x04ed
            r6 = 3
            if (r2 == r6) goto L_0x0469
            android.widget.ImageView r1 = r0.primaryActionButtonView
            r1.setTag(r7)
            android.widget.ImageView r0 = r0.primaryActionButtonView
            r0.setVisibility(r5)
            goto L_0x0553
        L_0x0469:
            com.android.dialer.app.calllog.calllogcache.CallLogCache r2 = r0.callLogCache
            android.telecom.PhoneAccountHandle r5 = r0.accountHandle
            java.lang.String r6 = r0.number
            boolean r2 = r2.isVoicemailNumber(r5, r6)
            if (r2 == 0) goto L_0x0480
            android.widget.ImageView r2 = r0.primaryActionButtonView
            com.android.dialer.app.calllog.IntentProvider$8 r5 = new com.android.dialer.app.calllog.IntentProvider$8
            r5.<init>(r7)
            r2.setTag(r5)
            goto L_0x04c9
        L_0x0480:
            boolean r2 = r18.canSupportAssistedDialing()
            if (r2 == 0) goto L_0x04ad
            android.widget.ImageView r2 = r0.primaryActionButtonView
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r0.number
            r5.append(r6)
            java.lang.String r6 = r0.postDialDigits
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.content.Context r6 = r0.context
            java.lang.Class<android.telephony.TelephonyManager> r7 = android.telephony.TelephonyManager.class
            java.lang.Object r6 = r6.getSystemService(r7)
            android.telephony.TelephonyManager r6 = (android.telephony.TelephonyManager) r6
            com.android.dialer.app.calllog.IntentProvider r5 = com.android.dialer.app.calllog.IntentProvider.getAssistedDialIntentProvider(r5)
            r2.setTag(r5)
            goto L_0x04c9
        L_0x04ad:
            android.widget.ImageView r2 = r0.primaryActionButtonView
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r0.number
            r5.append(r6)
            java.lang.String r6 = r0.postDialDigits
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.android.dialer.app.calllog.IntentProvider r5 = com.android.dialer.app.calllog.IntentProvider.getReturnCallIntentProvider(r5)
            r2.setTag(r5)
        L_0x04c9:
            android.widget.ImageView r2 = r0.primaryActionButtonView
            android.content.Context r5 = r0.context
            r6 = 2131820843(0x7f11012b, float:1.9274412E38)
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence[] r4 = new java.lang.CharSequence[r4]
            r4[r3] = r1
            java.lang.CharSequence r1 = android.text.TextUtils.expandTemplate(r5, r4)
            r2.setContentDescription(r1)
            android.widget.ImageView r1 = r0.primaryActionButtonView
            r2 = 2131230988(0x7f08010c, float:1.8078044E38)
            r1.setImageResource(r2)
            android.widget.ImageView r0 = r0.primaryActionButtonView
            r0.setVisibility(r3)
            goto L_0x0553
        L_0x04ed:
            android.content.Context r2 = r0.context
            com.android.dialer.duo.DuoComponent r2 = com.android.dialer.duo.DuoComponent.get(r2)
            com.android.dialer.duo.Duo r2 = r2.getDuo()
            android.telecom.PhoneAccountHandle r5 = r0.accountHandle
            if (r5 == 0) goto L_0x0500
            com.android.dialer.duo.stub.DuoStub r2 = (com.android.dialer.duo.stub.DuoStub) r2
            r2.isDuoAccount((android.telecom.PhoneAccountHandle) r5)
        L_0x0500:
            android.widget.ImageView r2 = r0.primaryActionButtonView
            java.lang.String r5 = r0.number
            com.android.dialer.app.calllog.IntentProvider r5 = com.android.dialer.app.calllog.IntentProvider.getReturnVideoCallIntentProvider(r5)
            r2.setTag(r5)
            android.widget.ImageView r2 = r0.primaryActionButtonView
            android.content.Context r5 = r0.context
            java.lang.String r5 = r5.getString(r9)
            java.lang.CharSequence[] r4 = new java.lang.CharSequence[r4]
            r4[r3] = r1
            java.lang.CharSequence r1 = android.text.TextUtils.expandTemplate(r5, r4)
            r2.setContentDescription(r1)
            android.widget.ImageView r1 = r0.primaryActionButtonView
            r1.setImageResource(r8)
            android.widget.ImageView r0 = r0.primaryActionButtonView
            r0.setVisibility(r3)
            goto L_0x0553
        L_0x0529:
            android.widget.ImageView r2 = r0.primaryActionButtonView
            java.lang.String r5 = r0.number
            android.telecom.PhoneAccountHandle r6 = r0.accountHandle
            com.android.dialer.app.calllog.IntentProvider r5 = com.android.dialer.app.calllog.IntentProvider.getReturnVideoCallIntentProvider(r5, r6)
            r2.setTag(r5)
            android.widget.ImageView r2 = r0.primaryActionButtonView
            android.content.Context r5 = r0.context
            java.lang.String r5 = r5.getString(r9)
            java.lang.CharSequence[] r4 = new java.lang.CharSequence[r4]
            r4[r3] = r1
            java.lang.CharSequence r1 = android.text.TextUtils.expandTemplate(r5, r4)
            r2.setContentDescription(r1)
            android.widget.ImageView r1 = r0.primaryActionButtonView
            r1.setImageResource(r8)
            android.widget.ImageView r0 = r0.primaryActionButtonView
            r0.setVisibility(r3)
        L_0x0553:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogListItemViewHolder.showActions(boolean):void");
    }

    public void updatePhoto() {
        this.quickContactView.assignContactUri(this.info.lookupUri);
        if (!this.isSpamFeatureEnabled || !this.isSpam) {
            ContactPhotoManager.getInstance(this.context).loadDialerThumbnailOrPhoto(this.quickContactView, this.info.lookupUri, this.info.photoId, this.info.photoUri, TextUtils.isEmpty(this.info.name) ? this.displayNumber : this.info.name, getContactType());
        } else {
            this.quickContactView.setImageDrawable(this.context.getDrawable(R.drawable.blocked_contact));
        }
    }
}
