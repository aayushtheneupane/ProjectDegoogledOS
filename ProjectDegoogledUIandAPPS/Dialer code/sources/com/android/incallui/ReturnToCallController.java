package com.android.incallui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.provider.Settings;
import android.telecom.CallAudioState;
import android.text.TextUtils;
import com.android.bubble.Bubble;
import com.android.bubble.BubbleInfo;
import com.android.bubble.stub.BubbleStub;
import com.android.dialer.R;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.contacts.ContactsComponent;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.lettertile.LetterTileDrawable;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.incallui.ContactInfoCache;
import com.android.incallui.InCallPresenter;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReturnToCallController implements InCallPresenter.InCallUiListener, CallList.Listener, AudioModeProvider.AudioModeListener {
    private static Boolean canShowBubblesForTesting;
    private CallAudioState audioState;
    Bubble bubble;
    private final ContactInfoCache contactInfoCache;
    private final Context context;
    private final PendingIntent endCall = createActionIntent("endCallV2");
    private final PendingIntent fullScreen = createActionIntent("returnToCallV2");
    private InCallPresenter.InCallState inCallState;
    private final PendingIntent showSpeakerSelect = createActionIntent("showAudioRouteSelectorV2");
    private final PendingIntent toggleMute = createActionIntent("toggleMuteV2");
    private final PendingIntent toggleSpeaker = createActionIntent("toggleSpeakerV2");

    private static class ReturnToCallContactInfoCacheCallback implements ContactInfoCache.ContactInfoCacheCallback {
        private final WeakReference<ReturnToCallController> returnToCallControllerWeakReference;

        /* synthetic */ ReturnToCallContactInfoCacheCallback(ReturnToCallController returnToCallController, C06461 r2) {
            this.returnToCallControllerWeakReference = new WeakReference<>(returnToCallController);
        }

        public void onContactInfoComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
            ReturnToCallController returnToCallController = (ReturnToCallController) this.returnToCallControllerWeakReference.get();
            if (returnToCallController != null) {
                Drawable drawable = contactCacheEntry.photo;
                if (drawable != null) {
                    Bubble bubble = returnToCallController.bubble;
                    if (bubble != null) {
                        ((BubbleStub) bubble).updatePhotoAvatar(drawable);
                        return;
                    }
                    return;
                }
                DialerCall callById = CallList.getInstance().getCallById(str);
                if (callById != null) {
                    LetterTileDrawable access$200 = ReturnToCallController.access$200(returnToCallController, callById, contactCacheEntry);
                    Bubble bubble2 = returnToCallController.bubble;
                    if (bubble2 != null) {
                        ((BubbleStub) bubble2).updateAvatar(access$200);
                    }
                }
            }
        }

        public void onImageLoadComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
            ReturnToCallController returnToCallController = (ReturnToCallController) this.returnToCallControllerWeakReference.get();
            if (returnToCallController != null) {
                Drawable drawable = contactCacheEntry.photo;
                if (drawable != null) {
                    Bubble bubble = returnToCallController.bubble;
                    if (bubble != null) {
                        ((BubbleStub) bubble).updatePhotoAvatar(drawable);
                        return;
                    }
                    return;
                }
                DialerCall callById = CallList.getInstance().getCallById(str);
                if (callById != null) {
                    LetterTileDrawable access$200 = ReturnToCallController.access$200(returnToCallController, callById, contactCacheEntry);
                    Bubble bubble2 = returnToCallController.bubble;
                    if (bubble2 != null) {
                        ((BubbleStub) bubble2).updateAvatar(access$200);
                    }
                }
            }
        }
    }

    public ReturnToCallController(Context context2, ContactInfoCache contactInfoCache2) {
        this.context = context2;
        this.contactInfoCache = contactInfoCache2;
        AudioModeProvider.getInstance().addListener(this);
        this.audioState = AudioModeProvider.getInstance().getAudioState();
        InCallPresenter.getInstance().addInCallUiListener(this);
        CallList.getInstance().addListener(this);
    }

    static /* synthetic */ LetterTileDrawable access$200(ReturnToCallController returnToCallController, DialerCall dialerCall, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        String displayName = ContactsComponent.get(returnToCallController.context).contactDisplayPreferences().getDisplayName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
        if (TextUtils.isEmpty(displayName)) {
            displayName = contactCacheEntry.number;
        }
        LetterTileDrawable letterTileDrawable = new LetterTileDrawable(returnToCallController.context.getResources());
        letterTileDrawable.setCanonicalDialerLetterTileDetails(dialerCall.updateNameIfRestricted(displayName), contactCacheEntry.lookupKey, 1, LetterTileDrawable.getContactTypeFromPrimitives(dialerCall.isVoiceMailNumber(), dialerCall.isSpam(), contactCacheEntry.isBusiness, dialerCall.getNumberPresentation(), dialerCall.isConferenceCall()));
        return letterTileDrawable;
    }

    private PendingIntent createActionIntent(String str) {
        Intent intent = new Intent(this.context, ReturnToCallActionReceiver.class);
        intent.setAction(str);
        return PendingIntent.getBroadcast(this.context, 0, intent, 0);
    }

    private List<BubbleInfo.Action> generateActions() {
        int i;
        boolean z;
        Drawable drawable;
        ArrayList arrayList = new ArrayList();
        CallAudioState callAudioState = this.audioState;
        int supportedRouteMask = callAudioState.getSupportedRouteMask() & 2;
        int i2 = R.drawable.quantum_ic_volume_up_vd_theme_24;
        boolean z2 = true;
        if (supportedRouteMask == 2) {
            i = R.string.incall_label_audio;
            if ((callAudioState.getRoute() & 2) == 2) {
                i2 = R.drawable.volume_bluetooth;
            } else if ((callAudioState.getRoute() & 8) != 8) {
                if ((callAudioState.getRoute() & 4) == 4) {
                    i2 = R.drawable.quantum_ic_headset_vd_theme_24;
                } else {
                    i2 = R.drawable.quantum_ic_phone_in_talk_vd_theme_24;
                    z = false;
                    z2 = false;
                }
            }
            z = true;
            z2 = false;
        } else {
            z = callAudioState.getRoute() == 8;
            i = R.string.incall_label_speaker;
        }
        BubbleInfo.Action.Builder builder = BubbleInfo.Action.builder();
        builder.setIconDrawable(this.context.getDrawable(R.drawable.quantum_ic_exit_to_app_flip_vd_theme_24));
        builder.setIntent(this.fullScreen);
        builder.setName(this.context.getText(R.string.bubble_return_to_call));
        builder.setCheckable(false);
        arrayList.add(builder.build());
        BubbleInfo.Action.Builder builder2 = BubbleInfo.Action.builder();
        builder2.setIconDrawable(this.context.getDrawable(R.drawable.quantum_ic_mic_off_vd_theme_24));
        builder2.setChecked(this.audioState.isMuted());
        builder2.setIntent(this.toggleMute);
        builder2.setName(this.context.getText(R.string.incall_label_mute));
        arrayList.add(builder2.build());
        BubbleInfo.Action.Builder builder3 = BubbleInfo.Action.builder();
        builder3.setIconDrawable(this.context.getDrawable(i2));
        if (z2) {
            drawable = null;
        } else {
            drawable = this.context.getDrawable(R.drawable.quantum_ic_arrow_drop_down_vd_theme_24);
        }
        builder3.setSecondaryIconDrawable(drawable);
        builder3.setName(this.context.getText(i));
        builder3.setCheckable(z2);
        builder3.setChecked(z);
        builder3.setIntent(z2 ? this.toggleSpeaker : this.showSpeakerSelect);
        arrayList.add(builder3.build());
        BubbleInfo.Action.Builder builder4 = BubbleInfo.Action.builder();
        builder4.setIconDrawable(this.context.getDrawable(R.drawable.quantum_ic_call_end_vd_theme_24));
        builder4.setIntent(this.endCall);
        builder4.setName(this.context.getText(R.string.incall_label_end_call));
        builder4.setCheckable(false);
        arrayList.add(builder4.build());
        return arrayList;
    }

    private DialerCall getCall() {
        DialerCall outgoingCall = CallList.getInstance().getOutgoingCall();
        return outgoingCall == null ? CallList.getInstance().getActiveOrBackgroundCall() : outgoingCall;
    }

    private void hide() {
        Bubble bubble2 = this.bubble;
        if (bubble2 != null) {
            ((BubbleStub) bubble2).hide();
        } else {
            LogUtil.m9i("ReturnToCallController.hide", "hide() called without calling show()", new Object[0]);
        }
    }

    public static boolean isEnabled(Context context2) {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(context2).getConfigProvider()).getBoolean("enable_return_to_call_bubble_v2", false);
    }

    static void setCanShowBubblesForTesting(boolean z) {
        canShowBubblesForTesting = Boolean.valueOf(z);
    }

    private void show() {
        boolean z;
        BubbleStub bubbleStub;
        int i;
        Bubble bubble2 = this.bubble;
        if (bubble2 == null) {
            Context context2 = this.context;
            Boolean bool = canShowBubblesForTesting;
            if (bool != null) {
                z = bool.booleanValue();
            } else {
                z = Settings.canDrawOverlays(context2);
            }
            if (!z) {
                LogUtil.m9i("ReturnToCallController.startBubble", "can't show bubble, no permission", new Object[0]);
                bubbleStub = null;
            } else {
                Bubble bubble3 = ((DaggerAospDialerRootComponent) ((HasRootComponent) this.context.getApplicationContext()).component()).bubbleComponent().getBubble();
                BubbleInfo.Builder builder = BubbleInfo.builder();
                builder.setPrimaryColor(((AospThemeImpl) ThemeComponent.get(this.context).theme()).getColorPrimary());
                builder.setPrimaryIcon(Icon.createWithResource(this.context, R.drawable.on_going_call));
                if (InCallPresenter.getInstance().shouldStartInBubbleMode()) {
                    i = this.context.getResources().getDisplayMetrics().heightPixels / 2;
                } else {
                    i = this.context.getResources().getDimensionPixelOffset(R.dimen.return_to_call_initial_offset_y);
                }
                builder.setStartingYPosition(i);
                builder.setActions(generateActions());
                bubbleStub = (BubbleStub) bubble3;
                bubbleStub.setBubbleInfo(builder.build());
                bubbleStub.show();
            }
            this.bubble = bubbleStub;
        } else {
            ((BubbleStub) bubble2).show();
        }
        startContactInfoSearch();
    }

    private void startContactInfoSearch() {
        DialerCall call = getCall();
        if (call != null) {
            this.contactInfoCache.findInfo(call, false, new ReturnToCallContactInfoCacheCallback(this, (C06461) null));
        }
    }

    public void onAudioStateChanged(CallAudioState callAudioState) {
        if (!isEnabled(this.context)) {
            hide();
            return;
        }
        this.audioState = callAudioState;
        Bubble bubble2 = this.bubble;
        if (bubble2 != null) {
            ((BubbleStub) bubble2).updateActions(generateActions());
        }
    }

    public void onCallListChange(CallList callList) {
        if (!isEnabled(this.context)) {
            hide();
            return;
        }
        boolean shouldStartInBubbleMode = InCallPresenter.getInstance().shouldStartInBubbleMode();
        InCallPresenter.InCallState potentialStateFromCallList = InCallPresenter.getInstance().getPotentialStateFromCallList(callList);
        boolean z = potentialStateFromCallList != this.inCallState && potentialStateFromCallList == InCallPresenter.InCallState.OUTGOING && shouldStartInBubbleMode;
        Bubble bubble2 = this.bubble;
        if (bubble2 != null) {
            ((BubbleStub) bubble2).isVisible();
            ((BubbleStub) this.bubble).isDismissed();
        }
        Bubble bubble3 = this.bubble;
        if (bubble3 != null && z) {
            BubbleInfo.Builder builder = BubbleInfo.builder();
            builder.setPrimaryColor(((AospThemeImpl) ThemeComponent.get(this.context).theme()).getColorPrimary());
            builder.setPrimaryIcon(Icon.createWithResource(this.context, R.drawable.on_going_call));
            builder.setStartingYPosition(this.context.getResources().getDisplayMetrics().heightPixels / 2);
            builder.setActions(generateActions());
            ((BubbleStub) bubble3).setBubbleInfo(builder.build());
        }
        if ((potentialStateFromCallList != InCallPresenter.InCallState.OUTGOING || z) && getCall() != null && !InCallPresenter.getInstance().isShowingInCallUi()) {
            LogUtil.m9i("ReturnToCallController.onCallListChange", "going to show bubble", new Object[0]);
            show();
        } else {
            startContactInfoSearch();
        }
        this.inCallState = potentialStateFromCallList;
    }

    public void onDisconnect(DialerCall dialerCall) {
        if (!isEnabled(this.context)) {
            hide();
            return;
        }
        LogUtil.enterBlock("ReturnToCallController.onDisconnect");
        Bubble bubble2 = this.bubble;
        if (bubble2 != null) {
            ((BubbleStub) bubble2).isVisible();
        }
        startContactInfoSearch();
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onIncomingCall(DialerCall dialerCall) {
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onUiShowing(boolean z) {
        if (!isEnabled(this.context)) {
            hide();
            return;
        }
        LogUtil.m9i("ReturnToCallController.onUiShowing", GeneratedOutlineSupport.outline10("showing: ", z), new Object[0]);
        if (z) {
            LogUtil.m9i("ReturnToCallController.onUiShowing", "going to hide", new Object[0]);
            hide();
        } else if (getCall() != null) {
            LogUtil.m9i("ReturnToCallController.onUiShowing", "going to show", new Object[0]);
            show();
        }
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
    }

    public void tearDown() {
        hide();
        InCallPresenter.getInstance().removeInCallUiListener(this);
        CallList.getInstance().removeListener(this);
        AudioModeProvider.getInstance().removeListener(this);
    }
}
