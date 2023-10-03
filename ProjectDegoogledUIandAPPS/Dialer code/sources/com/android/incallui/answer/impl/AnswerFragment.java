package com.android.incallui.answer.impl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.appcompat.R$style;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.contacts.common.widget.SelectPhoneAccountDialogFragment;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.multimedia.MultimediaData;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.ViewUtil$ViewRunnable;
import com.android.incallui.answer.impl.CreateCustomSmsDialogFragment;
import com.android.incallui.answer.impl.SmsBottomSheetFragment;
import com.android.incallui.answer.impl.affordance.SwipeButtonHelper;
import com.android.incallui.answer.impl.affordance.SwipeButtonView;
import com.android.incallui.answer.impl.answermethod.AnswerMethod;
import com.android.incallui.answer.impl.answermethod.AnswerMethodFactory;
import com.android.incallui.answer.impl.answermethod.AnswerMethodHolder;
import com.android.incallui.answer.impl.utils.Interpolators;
import com.android.incallui.answer.protocol.AnswerScreen;
import com.android.incallui.answer.protocol.AnswerScreenDelegate;
import com.android.incallui.answer.protocol.AnswerScreenDelegateFactory;
import com.android.incallui.contactgrid.ContactGridManager;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegateFactory;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;
import com.android.incallui.incall.protocol.SecondaryInfo;
import com.android.incallui.incalluilock.InCallUiLock;
import com.android.incallui.sessiondata.AvatarPresenter;
import com.android.incallui.sessiondata.MultimediaFragment;
import com.android.incallui.speakeasy.SpeakEasyComponent;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"ClickableViewAccessibility"})
public class AnswerFragment extends Fragment implements AnswerScreen, InCallScreen, SmsBottomSheetFragment.SmsSheetHolder, CreateCustomSmsDialogFragment.CreateCustomSmsHolder, AnswerMethodHolder, MultimediaFragment.Holder {
    static final String ARG_CALL_ID = "call_id";
    static final String ARG_IS_SELF_MANAGED_CAMERA = "is_self_managed_camera";
    static final String ARG_IS_VIDEO_CALL = "is_video_call";
    static final String ARG_IS_VIDEO_UPGRADE_REQUEST = "is_video_upgrade_request";
    private final View.AccessibilityDelegate accessibilityDelegate = new View.AccessibilityDelegate() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            if (view == AnswerFragment.this.secondaryButton) {
                AnswerFragment answerFragment = AnswerFragment.this;
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, answerFragment.getText(answerFragment.secondaryBehavior.accessibilityLabel)));
            } else if (view == AnswerFragment.this.answerAndReleaseButton) {
                AnswerFragment answerFragment2 = AnswerFragment.this;
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, answerFragment2.getText(answerFragment2.answerAndReleaseBehavior.accessibilityLabel)));
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i == 16) {
                if (view == AnswerFragment.this.secondaryButton) {
                    AnswerFragment.this.secondaryBehavior.performAction(AnswerFragment.this);
                    return true;
                } else if (view == AnswerFragment.this.answerAndReleaseButton) {
                    AnswerFragment.this.answerAndReleaseBehavior.performAction(AnswerFragment.this);
                    return true;
                }
            }
            return super.performAccessibilityAction(view, i, bundle);
        }
    };
    private final SwipeButtonHelper.Callback affordanceCallback = new SwipeButtonHelper.Callback() {
        public float getAffordanceFalsingFactor() {
            return 1.0f;
        }

        public SwipeButtonView getLeftIcon() {
            return AnswerFragment.this.secondaryButton;
        }

        public View getLeftPreview() {
            return null;
        }

        public float getMaxTranslationDistance() {
            View view = AnswerFragment.this.getView();
            if (view == null) {
                return 0.0f;
            }
            return (float) Math.hypot((double) view.getWidth(), (double) view.getHeight());
        }

        public SwipeButtonView getRightIcon() {
            return AnswerFragment.this.answerAndReleaseButton;
        }

        public View getRightPreview() {
            return null;
        }

        public void onAnimationToSideEnded(boolean z) {
            if (z) {
                AnswerFragment.this.answerAndReleaseBehavior.performAction(AnswerFragment.this);
            } else {
                AnswerFragment.this.secondaryBehavior.performAction(AnswerFragment.this);
            }
        }

        public void onAnimationToSideStarted(boolean z, float f, float f2) {
        }

        public void onIconClicked(boolean z) {
            CharSequence charSequence;
            AnswerFragment.this.affordanceHolderLayout.startHintAnimation(z, (Runnable) null);
            AnswerMethod access$1100 = AnswerFragment.this.getAnswerMethod();
            if (z) {
                AnswerFragment answerFragment = AnswerFragment.this;
                charSequence = answerFragment.getText(answerFragment.answerAndReleaseBehavior.hintText);
            } else {
                AnswerFragment answerFragment2 = AnswerFragment.this;
                charSequence = answerFragment2.getText(answerFragment2.secondaryBehavior.hintText);
            }
            access$1100.setHintText(charSequence);
            AnswerFragment.this.handler.removeCallbacks(AnswerFragment.this.swipeHintRestoreTimer);
            AnswerFragment.this.handler.postDelayed(AnswerFragment.this.swipeHintRestoreTimer, 5000);
        }

        public void onSwipingAborted() {
        }

        public void onSwipingStarted(boolean z) {
        }
    };
    /* access modifiers changed from: private */
    public AffordanceHolderLayout affordanceHolderLayout;
    /* access modifiers changed from: private */
    public SecondaryBehavior answerAndReleaseBehavior;
    /* access modifiers changed from: private */
    public SwipeButtonView answerAndReleaseButton;
    private AnswerScreenDelegate answerScreenDelegate;
    private VideoCallScreen answerVideoCallScreen;
    private boolean buttonAcceptClicked;
    private boolean buttonRejectClicked;
    private LinearLayout chipContainer;
    private ContactGridManager contactGridManager;
    private CreateCustomSmsDialogFragment createCustomSmsDialogFragment;
    /* access modifiers changed from: private */
    public Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean hasAnimatedEntry;
    /* access modifiers changed from: private */
    public View importanceBadge;
    private InCallScreenDelegate inCallScreenDelegate;
    private PrimaryCallState primaryCallState;
    private PrimaryInfo primaryInfo = PrimaryInfo.empty();
    /* access modifiers changed from: private */
    public SecondaryBehavior secondaryBehavior = SecondaryBehavior.REJECT_WITH_SMS;
    /* access modifiers changed from: private */
    public SwipeButtonView secondaryButton;
    /* access modifiers changed from: private */
    public Runnable swipeHintRestoreTimer = new Runnable() {
        public final void run() {
            AnswerFragment.this.restoreSwipeHintTexts();
        }
    };
    private ArrayList<CharSequence> textResponses;
    private SmsBottomSheetFragment textResponsesFragment;

    public static class AvatarFragment extends Fragment implements AvatarPresenter {
        private ImageView avatarImageView;

        public ImageView getAvatarImageView() {
            return this.avatarImageView;
        }

        public int getAvatarSize() {
            return getResources().getDimensionPixelSize(R.dimen.answer_avatar_size);
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.fragment_avatar, viewGroup, false);
        }

        public void onViewCreated(View view, Bundle bundle) {
            this.avatarImageView = (ImageView) view.findViewById(R.id.contactgrid_avatar);
            ((AnswerFragment) FragmentUtils.getParentUnsafe((Fragment) this, MultimediaFragment.Holder.class)).updateAvatar(this);
        }

        public boolean shouldShowAnonymousAvatar() {
            return false;
        }
    }

    private enum SecondaryBehavior {
        REJECT_WITH_SMS(R.drawable.quantum_ic_message_white_24, R.string.a11y_description_incoming_call_reject_with_sms, R.string.a11y_incoming_call_reject_with_sms, R.string.call_incoming_swipe_to_decline_with_message) {
            public void performAction(AnswerFragment answerFragment) {
                answerFragment.showMessageMenu();
            }
        },
        ANSWER_VIDEO_AS_AUDIO(R.drawable.quantum_ic_videocam_off_vd_theme_24, R.string.a11y_description_incoming_call_answer_video_as_audio, R.string.a11y_incoming_call_answer_video_as_audio, R.string.call_incoming_swipe_to_answer_video_as_audio) {
            public void performAction(AnswerFragment answerFragment) {
                answerFragment.acceptCallByUser(true);
            }
        },
        ANSWER_AND_RELEASE(R.drawable.ic_end_answer_32, R.string.a11y_description_incoming_call_answer_and_release, R.string.a11y_incoming_call_answer_and_release, R.string.call_incoming_swipe_to_answer_and_release) {
            public void performAction(AnswerFragment answerFragment) {
                AnswerFragment.access$300(answerFragment);
            }
        };
        
        public final int accessibilityLabel;
        public final int contentDescription;
        public final int hintText;
        public int icon;

        public void applyToView(ImageView imageView) {
            imageView.setImageResource(this.icon);
            imageView.setContentDescription(imageView.getContext().getText(this.contentDescription));
        }

        public abstract void performAction(AnswerFragment answerFragment);

        private SecondaryBehavior(int i, int i2, int i3, int i4) {
            this.icon = i;
            this.contentDescription = i2;
            this.accessibilityLabel = i3;
            this.hintText = i4;
        }
    }

    /* access modifiers changed from: private */
    public void acceptCallByUser(boolean z) {
        LogUtil.m9i("AnswerFragment.acceptCallByUser", z ? " answerVideoAsAudio" : "", new Object[0]);
        if (!this.buttonAcceptClicked) {
            this.answerScreenDelegate.onAnswer(z);
            this.buttonAcceptClicked = true;
        }
    }

    static /* synthetic */ void access$300(AnswerFragment answerFragment) {
        answerFragment.answerAndReleaseButton.animate().alpha(0.0f).withEndAction(new Runnable() {
            public void run() {
                AnswerFragment.this.affordanceHolderLayout.reset(false);
                AnswerFragment.this.secondaryButton.animate().alpha(1.0f);
            }
        });
        answerFragment.answerScreenDelegate.onAnswerAndReleaseCall();
        answerFragment.buttonAcceptClicked = true;
    }

    /* access modifiers changed from: private */
    public void animateEntry(View view) {
        if (!isAdded()) {
            LogUtil.m9i("AnswerFragment.animateEntry", "Not currently added to Activity. Will not start entry animation.", new Object[0]);
            return;
        }
        this.contactGridManager.getContainerView().setAlpha(0.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.contactGridManager.getContainerView(), View.ALPHA, new float[]{0.0f, 1.0f});
        ObjectAnimator createTranslation = createTranslation(view.findViewById(R.id.contactgrid_top_row));
        ObjectAnimator createTranslation2 = createTranslation(view.findViewById(R.id.contactgrid_contact_name));
        ObjectAnimator createTranslation3 = createTranslation(view.findViewById(R.id.contactgrid_bottom_row));
        ObjectAnimator createTranslation4 = createTranslation(this.importanceBadge);
        ObjectAnimator createTranslation5 = createTranslation(view.findViewById(R.id.incall_data_container));
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder play = animatorSet.play(ofFloat);
        play.with(createTranslation).with(createTranslation2).with(createTranslation3).with(createTranslation4).with(createTranslation5);
        if (isShowingLocationUi()) {
            play.with(createTranslation(view.findViewById(R.id.incall_location_holder)));
        }
        animatorSet.setDuration((long) view.getResources().getInteger(R.integer.answer_animate_entry_millis));
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                boolean unused = AnswerFragment.this.hasAnimatedEntry = true;
            }
        });
        animatorSet.start();
    }

    private boolean canRejectCallWithSms() {
        PrimaryCallState primaryCallState2 = this.primaryCallState;
        return (primaryCallState2 == null || primaryCallState2.state() == 10 || this.primaryCallState.state() == 9 || this.primaryCallState.state() == 2) ? false : true;
    }

    private ObjectAnimator createTranslation(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{((float) view.getTop()) * 0.5f, 0.0f});
        ofFloat.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public AnswerMethod getAnswerMethod() {
        return (AnswerMethod) getChildFragmentManager().findFragmentById(R.id.answer_method_container);
    }

    private MultimediaData getSessionData() {
        if (this.primaryInfo != null && !isVideoUpgradeRequest()) {
            return this.primaryInfo.multimediaData();
        }
        return null;
    }

    public static /* synthetic */ void lambda$mZjScH1bneX9UepVKvtc2WwAnU0(AnswerFragment answerFragment, View view) {
        answerFragment.answerScreenDelegate.onSpeakEasyCall();
        answerFragment.buttonAcceptClicked = true;
    }

    public static AnswerFragment newInstance(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        Bundle bundle = new Bundle();
        Assert.isNotNull(str);
        bundle.putString(ARG_CALL_ID, str);
        bundle.putBoolean("is_rtt_call", z);
        bundle.putBoolean(ARG_IS_VIDEO_CALL, z2);
        bundle.putBoolean(ARG_IS_VIDEO_UPGRADE_REQUEST, z3);
        bundle.putBoolean(ARG_IS_SELF_MANAGED_CAMERA, z4);
        bundle.putBoolean("allow_answer_and_release", z5);
        bundle.putBoolean("has_call_on_hold", z6);
        bundle.putBoolean("allow_speak_easy", z7);
        AnswerFragment answerFragment = new AnswerFragment();
        answerFragment.setArguments(bundle);
        return answerFragment;
    }

    private void rejectCall() {
        LogUtil.m9i("AnswerFragment.rejectCall", (String) null, new Object[0]);
        if (!this.buttonRejectClicked) {
            Context context = getContext();
            if (context == null) {
                LogUtil.m10w("AnswerFragment.rejectCall", "Null context when rejecting call. Logger call was skipped", new Object[0]);
            } else {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.REJECT_INCOMING_CALL_FROM_ANSWER_SCREEN);
            }
            this.buttonRejectClicked = true;
            this.answerScreenDelegate.onReject();
        }
    }

    /* access modifiers changed from: private */
    public void restoreSwipeHintTexts() {
        if (getAnswerMethod() == null) {
            return;
        }
        if (!getArguments().getBoolean("allow_answer_and_release")) {
            getAnswerMethod().setHintText((CharSequence) null);
        } else if (getArguments().getBoolean("has_call_on_hold")) {
            getAnswerMethod().setHintText(getText(R.string.call_incoming_default_label_answer_and_release_third));
        } else if (this.primaryCallState.supportsCallOnHold()) {
            getAnswerMethod().setHintText(getText(R.string.call_incoming_default_label_answer_and_release_second));
        }
    }

    /* access modifiers changed from: private */
    public void showCustomSmsDialog() {
        this.createCustomSmsDialogFragment = new CreateCustomSmsDialogFragment();
        this.createCustomSmsDialogFragment.showNow(getChildFragmentManager(), (String) null);
    }

    /* access modifiers changed from: private */
    public void showMessageMenu() {
        LogUtil.m9i("AnswerFragment.showMessageMenu", "Show sms menu.", new Object[0]);
        if (getContext() != null && !isDetached() && !getChildFragmentManager().isDestroyed()) {
            ArrayList<CharSequence> arrayList = this.textResponses;
            SmsBottomSheetFragment smsBottomSheetFragment = new SmsBottomSheetFragment();
            Bundle bundle = new Bundle();
            bundle.putCharSequenceArrayList(SelectPhoneAccountDialogFragment.ARG_OPTIONS, arrayList);
            smsBottomSheetFragment.setArguments(bundle);
            this.textResponsesFragment = smsBottomSheetFragment;
            this.textResponsesFragment.show(getChildFragmentManager(), (String) null);
            this.secondaryButton.animate().alpha(0.0f).withEndAction(new Runnable() {
                public void run() {
                    AnswerFragment.this.affordanceHolderLayout.reset(false);
                    AnswerFragment.this.secondaryButton.animate().alpha(1.0f);
                }
            });
            TelecomUtil.silenceRinger(getContext());
        }
    }

    private void updateImportanceBadgeVisibility() {
        if (isAdded() && getView() != null) {
            int i = 8;
            if (!getResources().getBoolean(R.bool.answer_important_call_allowed) || this.primaryInfo.isSpam()) {
                this.importanceBadge.setVisibility(8);
                return;
            }
            MultimediaData sessionData = getSessionData();
            boolean z = sessionData != null && sessionData.isImportant();
            TransitionManager.beginDelayedTransition((ViewGroup) this.importanceBadge.getParent());
            View view = this.importanceBadge;
            if (z) {
                i = 0;
            }
            view.setVisibility(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a8, code lost:
        if (java.util.Objects.equals(r2.getLocation(), r9) != false) goto L_0x0101;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0103  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updatePrimaryUI() {
        /*
            r11 = this;
            android.view.View r0 = r11.getView()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            com.android.incallui.contactgrid.ContactGridManager r0 = r11.contactGridManager
            com.android.incallui.incall.protocol.PrimaryInfo r1 = r11.primaryInfo
            r0.setPrimary(r1)
            com.android.incallui.answer.impl.answermethod.AnswerMethod r0 = r11.getAnswerMethod()
            com.android.incallui.incall.protocol.PrimaryInfo r1 = r11.primaryInfo
            boolean r1 = r1.answeringDisconnectsOngoingCall()
            r0.setShowIncomingWillDisconnect(r1)
            com.android.incallui.answer.impl.answermethod.AnswerMethod r0 = r11.getAnswerMethod()
            com.android.incallui.incall.protocol.PrimaryInfo r1 = r11.primaryInfo
            int r1 = r1.photoType()
            r2 = 2
            r3 = 0
            if (r1 != r2) goto L_0x0030
            com.android.incallui.incall.protocol.PrimaryInfo r1 = r11.primaryInfo
            android.graphics.drawable.Drawable r1 = r1.photo()
            goto L_0x0031
        L_0x0030:
            r1 = r3
        L_0x0031:
            r0.setContactPhoto(r1)
            boolean r0 = r11.isAdded()
            r1 = 1
            if (r0 != 0) goto L_0x003d
            goto L_0x0111
        L_0x003d:
            java.lang.String r0 = "AnswerFragment.updateDataFragment"
            com.android.dialer.common.LogUtil.enterBlock(r0)
            android.support.v4.app.FragmentManager r2 = r11.getChildFragmentManager()
            r4 = 2131296602(0x7f09015a, float:1.8211125E38)
            android.support.v4.app.Fragment r2 = r2.findFragmentById(r4)
            com.android.dialer.multimedia.MultimediaData r5 = r11.getSessionData()
            r6 = 0
            if (r5 == 0) goto L_0x00c3
            java.lang.String r7 = r5.getText()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L_0x007a
            android.net.Uri r7 = r5.getImageUri()
            if (r7 != 0) goto L_0x007a
            android.location.Location r5 = r5.getLocation()
            if (r5 == 0) goto L_0x00c3
            android.content.Context r5 = r11.getContext()
            com.android.incallui.maps.MapsComponent r5 = com.android.incallui.maps.MapsComponent.get(r5)
            com.android.incallui.maps.Maps r5 = r5.getMaps()
            r5.isAvailable()
            goto L_0x00c3
        L_0x007a:
            java.lang.String r7 = r5.getText()
            android.net.Uri r8 = r5.getImageUri()
            android.location.Location r9 = r5.getLocation()
            boolean r10 = r2 instanceof com.android.incallui.sessiondata.MultimediaFragment
            if (r10 == 0) goto L_0x00aa
            com.android.incallui.sessiondata.MultimediaFragment r2 = (com.android.incallui.sessiondata.MultimediaFragment) r2
            java.lang.String r10 = r2.getSubject()
            boolean r7 = java.util.Objects.equals(r10, r7)
            if (r7 == 0) goto L_0x00aa
            android.net.Uri r7 = r2.getImageUri()
            boolean r7 = java.util.Objects.equals(r7, r8)
            if (r7 == 0) goto L_0x00aa
            android.location.Location r2 = r2.getLocation()
            boolean r2 = java.util.Objects.equals(r2, r9)
            if (r2 != 0) goto L_0x0101
        L_0x00aa:
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r3 = "Replacing multimedia fragment"
            com.android.dialer.common.LogUtil.m9i(r0, r3, r2)
            com.android.incallui.incall.protocol.PrimaryInfo r0 = r11.primaryInfo
            boolean r0 = r0.isSpam()
            r0 = r0 ^ r1
            com.android.incallui.incall.protocol.PrimaryInfo r2 = r11.primaryInfo
            boolean r2 = r2.isSpam()
            com.android.incallui.sessiondata.MultimediaFragment r3 = com.android.incallui.sessiondata.MultimediaFragment.newInstance(r5, r6, r0, r2)
            goto L_0x0101
        L_0x00c3:
            boolean r5 = r11.isVideoCall()
            if (r5 != 0) goto L_0x00d1
            boolean r5 = r11.isVideoUpgradeRequest()
            if (r5 != 0) goto L_0x00d1
            r5 = r1
            goto L_0x00d2
        L_0x00d1:
            r5 = r6
        L_0x00d2:
            if (r5 == 0) goto L_0x00e5
            boolean r2 = r2 instanceof com.android.incallui.answer.impl.AnswerFragment.AvatarFragment
            if (r2 != 0) goto L_0x0101
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r3 = "Replacing avatar fragment"
            com.android.dialer.common.LogUtil.m9i(r0, r3, r2)
            com.android.incallui.answer.impl.AnswerFragment$AvatarFragment r3 = new com.android.incallui.answer.impl.AnswerFragment$AvatarFragment
            r3.<init>()
            goto L_0x0101
        L_0x00e5:
            if (r2 == 0) goto L_0x00fc
            java.lang.Object[] r5 = new java.lang.Object[r6]
            java.lang.String r7 = "Removing current fragment"
            com.android.dialer.common.LogUtil.m9i(r0, r7, r5)
            android.support.v4.app.FragmentManager r0 = r11.getChildFragmentManager()
            android.support.v4.app.FragmentTransaction r0 = r0.beginTransaction()
            r0.remove(r2)
            r0.commitNow()
        L_0x00fc:
            com.android.incallui.contactgrid.ContactGridManager r0 = r11.contactGridManager
            r0.setAvatarImageView(r3, r6, r6)
        L_0x0101:
            if (r3 == 0) goto L_0x0111
            android.support.v4.app.FragmentManager r0 = r11.getChildFragmentManager()
            android.support.v4.app.FragmentTransaction r0 = r0.beginTransaction()
            r0.replace(r4, r3)
            r0.commitNow()
        L_0x0111:
            com.android.incallui.incall.protocol.PrimaryInfo r0 = r11.primaryInfo
            boolean r0 = r0.shouldShowLocation()
            if (r0 == 0) goto L_0x011e
            com.android.incallui.contactgrid.ContactGridManager r11 = r11.contactGridManager
            r11.setAvatarHidden(r1)
        L_0x011e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.answer.impl.AnswerFragment.updatePrimaryUI():void");
    }

    private void updateUI() {
        if (getView() != null) {
            if (this.primaryInfo != null) {
                updatePrimaryUI();
            }
            PrimaryCallState primaryCallState2 = this.primaryCallState;
            if (primaryCallState2 != null) {
                this.contactGridManager.setCallState(primaryCallState2);
            }
            this.answerScreenDelegate.updateWindowBackgroundColor(0.0f);
        }
    }

    public InCallUiLock acquireInCallUiLock(String str) {
        return this.answerScreenDelegate.acquireInCallUiLock(str);
    }

    public boolean allowAnswerAndRelease() {
        return getArguments().getBoolean("allow_answer_and_release");
    }

    public void answerFromMethod() {
        acceptCallByUser(false);
    }

    public void customSmsCreated(CharSequence charSequence) {
        LogUtil.m9i("AnswerFragment.customSmsCreated", (String) null, new Object[0]);
        this.createCustomSmsDialogFragment = null;
        if (this.primaryCallState != null && canRejectCallWithSms()) {
            rejectCall();
            this.answerScreenDelegate.onRejectCallWithMessage(charSequence.toString());
        }
    }

    public void customSmsDismissed() {
        LogUtil.m9i("AnswerFragment.customSmsDismissed", (String) null, new Object[0]);
        this.createCustomSmsDialogFragment = null;
    }

    public void dismissPendingDialogs() {
        LogUtil.m9i("AnswerFragment.dismissPendingDialogs", (String) null, new Object[0]);
        SmsBottomSheetFragment smsBottomSheetFragment = this.textResponsesFragment;
        if (smsBottomSheetFragment != null) {
            smsBottomSheetFragment.dismiss();
            this.textResponsesFragment = null;
        }
        CreateCustomSmsDialogFragment createCustomSmsDialogFragment2 = this.createCustomSmsDialogFragment;
        if (createCustomSmsDialogFragment2 != null) {
            createCustomSmsDialogFragment2.dismiss();
            this.createCustomSmsDialogFragment = null;
        }
    }

    public void dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        this.contactGridManager.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        if (R$style.isTouchExplorationEnabled(getContext())) {
            accessibilityEvent.getText().add(getResources().getString(R.string.a11y_incoming_call_swipe_gesture_prompt));
        }
    }

    public int getAnswerAndDialpadContainerResourceId() {
        throw new UnsupportedOperationException();
    }

    public Fragment getAnswerScreenFragment() {
        return this;
    }

    public String getCallId() {
        String string = getArguments().getString(ARG_CALL_ID);
        Assert.isNotNull(string);
        return string;
    }

    public Fragment getInCallScreenFragment() {
        return this;
    }

    public boolean isActionTimeout() {
        return (this.buttonAcceptClicked || this.buttonRejectClicked) && this.answerScreenDelegate.isActionTimeout();
    }

    public boolean isManageConferenceVisible() {
        return false;
    }

    public boolean isShowingLocationUi() {
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.incall_location_holder);
        return findFragmentById != null && findFragmentById.isVisible();
    }

    public boolean isVideoCall() {
        return getArguments().getBoolean(ARG_IS_VIDEO_CALL);
    }

    public boolean isVideoUpgradeRequest() {
        return getArguments().getBoolean(ARG_IS_VIDEO_UPGRADE_REQUEST);
    }

    public void onAnswerProgressUpdate(float f) {
        if (this.primaryCallState.state() == 4 && !isVideoCall()) {
            this.answerScreenDelegate.updateWindowBackgroundColor(f);
        }
        float max = Math.max(0.0f, ((Math.abs(f) - 1.0f) / 0.75f) + 1.0f);
        View containerView = this.contactGridManager.getContainerView();
        containerView.setAlpha(R$style.lerp(containerView.getAlpha(), 1.0f - max, 0.5f));
        View containerView2 = this.contactGridManager.getContainerView();
        float lerp = R$style.lerp(1.0f, 0.75f, max);
        containerView2.setScaleX(R$style.lerp(containerView2.getScaleX(), lerp, 0.5f));
        containerView2.setScaleY(R$style.lerp(containerView2.getScaleY(), lerp, 0.5f));
        if (((double) Math.abs(f)) >= 1.0E-4d) {
            this.affordanceHolderLayout.animateHideLeftRightIcon();
            this.handler.removeCallbacks(this.swipeHintRestoreTimer);
            restoreSwipeHintTexts();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentUtils.checkParent(this, InCallScreenDelegateFactory.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SecondaryBehavior secondaryBehavior2;
        Trace.beginSection("AnswerFragment.onCreateView");
        Bundle arguments = getArguments();
        Assert.checkState(arguments.containsKey(ARG_CALL_ID));
        Assert.checkState(arguments.containsKey("is_rtt_call"));
        Assert.checkState(arguments.containsKey(ARG_IS_VIDEO_CALL));
        Assert.checkState(arguments.containsKey(ARG_IS_VIDEO_UPGRADE_REQUEST));
        this.buttonAcceptClicked = false;
        this.buttonRejectClicked = false;
        View inflate = layoutInflater.inflate(R.layout.fragment_incoming_call, viewGroup, false);
        this.secondaryButton = (SwipeButtonView) inflate.findViewById(R.id.incoming_secondary_button);
        this.answerAndReleaseButton = (SwipeButtonView) inflate.findViewById(R.id.incoming_secondary_button2);
        this.affordanceHolderLayout = (AffordanceHolderLayout) inflate.findViewById(R.id.incoming_container);
        this.affordanceHolderLayout.setAffordanceCallback(this.affordanceCallback);
        this.chipContainer = (LinearLayout) inflate.findViewById(R.id.incall_data_container_chip_container);
        this.importanceBadge = inflate.findViewById(R.id.incall_important_call_badge);
        this.importanceBadge.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int height = AnswerFragment.this.importanceBadge.getHeight() / 2;
                AnswerFragment.this.importanceBadge.setPadding(height, AnswerFragment.this.importanceBadge.getPaddingTop(), height, AnswerFragment.this.importanceBadge.getPaddingBottom());
            }
        });
        updateImportanceBadgeVisibility();
        this.contactGridManager = new ContactGridManager(inflate, (ImageView) null, 0, false);
        boolean isInMultiWindowMode = getActivity().isInMultiWindowMode();
        this.contactGridManager.onMultiWindowModeChanged(isInMultiWindowMode);
        if (AnswerMethodFactory.needsReplacement(getChildFragmentManager().findFragmentById(R.id.answer_method_container))) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.answer_method_container, AnswerMethodFactory.createAnswerMethod(getActivity()));
            beginTransaction.commitNow();
        }
        this.answerScreenDelegate = ((AnswerScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, AnswerScreenDelegateFactory.class)).newAnswerScreenDelegate(this);
        if (isVideoCall() || isVideoUpgradeRequest()) {
            secondaryBehavior2 = SecondaryBehavior.ANSWER_VIDEO_AS_AUDIO;
        } else {
            secondaryBehavior2 = SecondaryBehavior.REJECT_WITH_SMS;
        }
        this.secondaryBehavior = secondaryBehavior2;
        this.secondaryBehavior.applyToView(this.secondaryButton);
        this.secondaryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AnswerFragment.this.secondaryBehavior.performAction(AnswerFragment.this);
            }
        });
        this.secondaryButton.setClickable(R$style.isAccessibilityEnabled(getContext()));
        this.secondaryButton.setFocusable(R$style.isAccessibilityEnabled(getContext()));
        this.secondaryButton.setAccessibilityDelegate(this.accessibilityDelegate);
        if (isVideoUpgradeRequest()) {
            this.secondaryButton.setVisibility(4);
        } else if (isVideoCall()) {
            this.secondaryButton.setVisibility(0);
        }
        this.answerAndReleaseBehavior = SecondaryBehavior.ANSWER_AND_RELEASE;
        this.answerAndReleaseBehavior.applyToView(this.answerAndReleaseButton);
        this.answerAndReleaseButton.setClickable(R$style.isAccessibilityEnabled(getContext()));
        this.answerAndReleaseButton.setFocusable(R$style.isAccessibilityEnabled(getContext()));
        this.answerAndReleaseButton.setAccessibilityDelegate(this.accessibilityDelegate);
        if (allowAnswerAndRelease()) {
            this.answerAndReleaseButton.setVisibility(0);
            this.answerScreenDelegate.onAnswerAndReleaseButtonEnabled();
        } else {
            this.answerAndReleaseButton.setVisibility(4);
            this.answerScreenDelegate.onAnswerAndReleaseButtonDisabled();
        }
        this.answerAndReleaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AnswerFragment.this.answerAndReleaseBehavior.performAction(AnswerFragment.this);
            }
        });
        if (!getArguments().getBoolean("allow_speak_easy")) {
            this.chipContainer.setVisibility(8);
        } else {
            this.chipContainer.setVisibility(0);
            Optional<Integer> speakEasyChip = SpeakEasyComponent.get(getContext()).speakEasyChip();
            if (speakEasyChip.isPresent()) {
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(speakEasyChip.get().intValue(), (ViewGroup) null);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        AnswerFragment.lambda$mZjScH1bneX9UepVKvtc2WwAnU0(AnswerFragment.this, view);
                    }
                });
                this.chipContainer.addView(linearLayout);
            }
        }
        int i = 4098;
        if (!isInMultiWindowMode && getActivity().checkSelfPermission("android.permission.STATUS_BAR") == 0) {
            LogUtil.m9i("AnswerFragment.onCreateView", "STATUS_BAR permission granted, disabling nav bar", new Object[0]);
            i = 23072770;
        }
        inflate.setSystemUiVisibility(i);
        if (isVideoCall() || isVideoUpgradeRequest()) {
            if (!R$style.hasCameraPermissionAndShownPrivacyToast(getContext())) {
                inflate.findViewById(R.id.videocall_video_off).setVisibility(0);
            } else if (getArguments().getBoolean(ARG_IS_SELF_MANAGED_CAMERA)) {
                this.answerVideoCallScreen = new SelfManagedAnswerVideoCallScreen(getCallId(), this, inflate);
            } else {
                this.answerVideoCallScreen = new AnswerVideoCallScreen(getCallId(), this, inflate);
            }
        }
        Trace.endSection();
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        LogUtil.m9i("AnswerFragment.onDestroyView", (String) null, new Object[0]);
        if (this.answerVideoCallScreen != null) {
            this.answerVideoCallScreen = null;
        }
        super.onDestroyView();
        this.inCallScreenDelegate.onInCallScreenUnready();
        this.answerScreenDelegate.onAnswerScreenUnready();
    }

    public void onInCallScreenDialpadVisibilityChange(boolean z) {
    }

    public void onPause() {
        Trace.beginSection("AnswerFragment.onPause");
        super.onPause();
        LogUtil.m9i("AnswerFragment.onPause", (String) null, new Object[0]);
        this.inCallScreenDelegate.onInCallScreenPaused();
        Trace.endSection();
    }

    public void onResume() {
        Trace.beginSection("AnswerFragment.onResume");
        super.onResume();
        LogUtil.m9i("AnswerFragment.onResume", (String) null, new Object[0]);
        restoreSwipeHintTexts();
        this.inCallScreenDelegate.onInCallScreenResumed();
        Trace.endSection();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("hasAnimated", this.hasAnimatedEntry);
    }

    public void onStart() {
        Trace.beginSection("AnswerFragment.onStart");
        super.onStart();
        LogUtil.m9i("AnswerFragment.onStart", (String) null, new Object[0]);
        updateUI();
        VideoCallScreen videoCallScreen = this.answerVideoCallScreen;
        if (videoCallScreen != null) {
            videoCallScreen.onVideoScreenStart();
        }
        Trace.endSection();
    }

    public void onStop() {
        Trace.beginSection("AnswerFragment.onStop");
        super.onStop();
        LogUtil.m9i("AnswerFragment.onStop", (String) null, new Object[0]);
        this.handler.removeCallbacks(this.swipeHintRestoreTimer);
        VideoCallScreen videoCallScreen = this.answerVideoCallScreen;
        if (videoCallScreen != null) {
            videoCallScreen.onVideoScreenStop();
        }
        Trace.endSection();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Trace.beginSection("AnswerFragment.onViewCreated");
        this.inCallScreenDelegate = ((InCallScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, InCallScreenDelegateFactory.class)).newInCallScreenDelegate();
        Assert.isNotNull(this.inCallScreenDelegate);
        this.inCallScreenDelegate.onInCallScreenDelegateInit(this);
        this.inCallScreenDelegate.onInCallScreenReady();
        updateUI();
        if (bundle == null || !bundle.getBoolean("hasAnimated")) {
            CallUtil.doOnGlobalLayout(view, new ViewUtil$ViewRunnable() {
                public final void run(View view) {
                    AnswerFragment.this.animateEntry(view);
                }
            });
        }
        Trace.endSection();
    }

    public void rejectFromMethod() {
        rejectCall();
    }

    public void resetAnswerProgress() {
        this.affordanceHolderLayout.reset(true);
        this.answerScreenDelegate.updateWindowBackgroundColor(0.0f);
    }

    public void setCallState(PrimaryCallState primaryCallState2) {
        LogUtil.m9i("AnswerFragment.setCallState", primaryCallState2.toString(), new Object[0]);
        this.primaryCallState = primaryCallState2;
        this.contactGridManager.setCallState(primaryCallState2);
    }

    public void setEndCallButtonEnabled(boolean z, boolean z2) {
    }

    public void setPrimary(PrimaryInfo primaryInfo2) {
        LogUtil.m9i("AnswerFragment.setPrimary", primaryInfo2.toString(), new Object[0]);
        this.primaryInfo = primaryInfo2;
        updatePrimaryUI();
        updateImportanceBadgeVisibility();
    }

    public void setSecondary(SecondaryInfo secondaryInfo) {
    }

    public void setTextResponses(List<String> list) {
        if (isVideoCall() || isVideoUpgradeRequest()) {
            LogUtil.m9i("AnswerFragment.setTextResponses", "no-op for video calls", new Object[0]);
        } else if (list == null) {
            LogUtil.m9i("AnswerFragment.setTextResponses", "no text responses, hiding secondary button", new Object[0]);
            this.textResponses = null;
            this.secondaryButton.setVisibility(4);
        } else if (getActivity().isInMultiWindowMode()) {
            LogUtil.m9i("AnswerFragment.setTextResponses", "in multiwindow, hiding secondary button", new Object[0]);
            this.textResponses = null;
            this.secondaryButton.setVisibility(4);
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("textResponses.size: ");
            outline13.append(list.size());
            LogUtil.m9i("AnswerFragment.setTextResponses", outline13.toString(), new Object[0]);
            this.textResponses = new ArrayList<>(list);
            this.secondaryButton.setVisibility(0);
        }
    }

    public void showLocationUi(Fragment fragment) {
        boolean isShowingLocationUi = isShowingLocationUi();
        if (!isShowingLocationUi && fragment != null) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.incall_location_holder, fragment);
            beginTransaction.commitAllowingStateLoss();
        } else if (isShowingLocationUi && fragment == null) {
            Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.incall_location_holder);
            FragmentTransaction beginTransaction2 = getChildFragmentManager().beginTransaction();
            beginTransaction2.remove(findFragmentById);
            beginTransaction2.commitAllowingStateLoss();
        }
    }

    public void showManageConferenceCallButton(boolean z) {
    }

    public void showNoteSentToast() {
    }

    public void smsDismissed() {
        LogUtil.m9i("AnswerFragment.smsDismissed", (String) null, new Object[0]);
        this.textResponsesFragment = null;
    }

    @TargetApi(26)
    public void smsSelected(CharSequence charSequence) {
        LogUtil.m9i("AnswerFragment.smsSelected", (String) null, new Object[0]);
        this.textResponsesFragment = null;
        if (charSequence == null) {
            int i = Build.VERSION.SDK_INT;
            if (!((KeyguardManager) getContext().getSystemService(KeyguardManager.class)).isKeyguardLocked()) {
                LogUtil.m9i("AnswerFragment.smsSelected", "not locked, showing dialog directly", new Object[0]);
                showCustomSmsDialog();
                return;
            }
            LogUtil.m9i("AnswerFragment.smsSelected", "dismissing keyguard", new Object[0]);
            ((KeyguardManager) getContext().getSystemService(KeyguardManager.class)).requestDismissKeyguard(getActivity(), new KeyguardManager.KeyguardDismissCallback() {
                public void onDismissCancelled() {
                    LogUtil.m9i("AnswerFragment.smsSelected", "onDismissCancelled", new Object[0]);
                }

                public void onDismissError() {
                    LogUtil.m9i("AnswerFragment.smsSelected", "onDismissError", new Object[0]);
                }

                public void onDismissSucceeded() {
                    LogUtil.m9i("AnswerFragment.smsSelected", "onDismissSucceeded", new Object[0]);
                    AnswerFragment.this.showCustomSmsDialog();
                }
            });
        } else if (this.primaryCallState != null && canRejectCallWithSms()) {
            rejectCall();
            this.answerScreenDelegate.onRejectCallWithMessage(charSequence.toString());
        }
    }

    public void updateAvatar(AvatarPresenter avatarPresenter) {
        this.contactGridManager.setAvatarImageView(avatarPresenter.getAvatarImageView(), avatarPresenter.getAvatarSize(), avatarPresenter.shouldShowAnonymousAvatar());
    }

    public void updateInCallScreenColors() {
    }
}
