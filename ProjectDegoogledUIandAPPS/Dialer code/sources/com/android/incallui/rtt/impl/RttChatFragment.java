package com.android.incallui.rtt.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.appcompat.R$style;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.telecom.CallAudioState;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.lettertile.LetterTileDrawable;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.rtt.RttTranscript;
import com.android.dialer.rtt.RttTranscriptMessage;
import com.android.incallui.RttCallPresenter;
import com.android.incallui.audioroute.AudioRouteSelectorDialogFragment;
import com.android.incallui.hold.OnHoldFragment;
import com.android.incallui.incall.protocol.InCallButtonUi;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.incall.protocol.InCallButtonUiDelegateFactory;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegateFactory;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;
import com.android.incallui.incall.protocol.SecondaryInfo;
import com.android.incallui.rtt.impl.AudioSelectMenu;
import com.android.incallui.rtt.impl.RttChatAdapter;
import com.android.incallui.rtt.protocol.RttCallScreen;
import com.android.incallui.rtt.protocol.RttCallScreenDelegate;
import com.android.incallui.rtt.protocol.RttCallScreenDelegateFactory;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

public class RttChatFragment extends Fragment implements TextView.OnEditorActionListener, TextWatcher, RttChatAdapter.MessageListener, RttCallScreen, InCallScreen, InCallButtonUi, AudioRouteSelectorDialogFragment.AudioRouteSelectorPresenter {
    private RttChatAdapter adapter;
    private AudioSelectMenu audioSelectMenu;
    private Chronometer chronometer;
    /* access modifiers changed from: private */
    public EditText editText;
    private View endCallButton;
    private InCallButtonUiDelegate inCallButtonUiDelegate;
    private InCallScreenDelegate inCallScreenDelegate;
    private boolean isClearingInput;
    private boolean isTimerStarted;
    /* access modifiers changed from: private */
    public boolean isUserScrolling;
    private TextView nameTextView;
    private RttOverflowMenu overflowMenu;
    private PrimaryCallState primaryCallState = PrimaryCallState.empty();
    private PrimaryInfo primaryInfo = PrimaryInfo.empty();
    private RecyclerView recyclerView;
    private RttCallScreenDelegate rttCallScreenDelegate;
    private SecondaryInfo savedSecondaryInfo;
    /* access modifiers changed from: private */
    public boolean shouldAutoScrolling;
    private TextView statusBanner;
    private ImageButton submitButton;

    public static RttChatFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("call_id", str);
        RttChatFragment rttChatFragment = new RttChatFragment();
        rttChatFragment.setArguments(bundle);
        return rttChatFragment;
    }

    private void resumeInput(String str) {
        this.isClearingInput = true;
        this.editText.setText(str);
        this.editText.setSelection(str.length());
        this.isClearingInput = false;
    }

    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(editable)) {
            this.submitButton.setEnabled(false);
        } else {
            this.submitButton.setEnabled(true);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    public void enableButton(int i, boolean z) {
    }

    public int getAnswerAndDialpadContainerResourceId() {
        return R.id.incall_dialpad_container;
    }

    public String getCallId() {
        String string = getArguments().getString("call_id");
        Assert.isNotNull(string);
        return string;
    }

    public Fragment getInCallButtonUiFragment() {
        return this;
    }

    public Fragment getInCallScreenFragment() {
        return this;
    }

    public Fragment getRttCallScreenFragment() {
        return this;
    }

    public List<RttTranscriptMessage> getRttTranscriptMessageList() {
        return this.adapter.getRttTranscriptMessageList();
    }

    public boolean isManageConferenceVisible() {
        return false;
    }

    public /* synthetic */ boolean lambda$onCreateView$0$RttChatFragment(View view, int i, KeyEvent keyEvent) {
        String retrieveLastLocalMessage;
        if (i != 67 || keyEvent.getAction() != 0 || !TextUtils.isEmpty(this.editText.getText()) || (retrieveLastLocalMessage = this.adapter.retrieveLastLocalMessage()) == null) {
            return false;
        }
        resumeInput(retrieveLastLocalMessage);
        ((RttCallPresenter) this.rttCallScreenDelegate).onLocalMessage("\b");
        return true;
    }

    public /* synthetic */ void lambda$onCreateView$1$RttChatFragment(View view) {
        ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.RTT_SEND_BUTTON_CLICKED);
        this.adapter.submitLocalMessage();
        resumeInput("");
        ((RttCallPresenter) this.rttCallScreenDelegate).onLocalMessage("\n");
        this.shouldAutoScrolling = true;
    }

    public /* synthetic */ void lambda$onCreateView$2$RttChatFragment(View view) {
        LogUtil.m9i("RttChatFragment.onClick", "end call button clicked", new Object[0]);
        this.inCallButtonUiDelegate.onEndCallClicked();
    }

    public /* synthetic */ void lambda$onCreateView$3$RttChatFragment(View view) {
        R$style.hideKeyboardFrom(getContext(), this.editText);
        this.overflowMenu.showAtLocation(view, 53, 0, 0);
    }

    public /* synthetic */ void lambda$showAudioRouteSelector$4$RttChatFragment() {
        this.overflowMenu.showAtLocation(getView(), 53, 0, 0);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        SecondaryInfo secondaryInfo = this.savedSecondaryInfo;
        if (secondaryInfo != null) {
            LogUtil.m9i("RttChatFragment.setSecondary", secondaryInfo.toString(), new Object[0]);
            if (!isAdded()) {
                this.savedSecondaryInfo = secondaryInfo;
                return;
            }
            this.savedSecondaryInfo = null;
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.rtt_on_hold_banner);
            if (secondaryInfo.shouldShow()) {
                OnHoldFragment newInstance = OnHoldFragment.newInstance(secondaryInfo);
                newInstance.setPadTopInset(false);
                beginTransaction.replace(R.id.rtt_on_hold_banner, newInstance);
            } else if (findFragmentById != null) {
                beginTransaction.remove(findFragmentById);
            }
            beginTransaction.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
            beginTransaction.commitNowAllowingStateLoss();
            this.overflowMenu.enableSwitchToSecondaryButton(secondaryInfo.shouldShow());
        }
    }

    public void onAudioRouteSelected(int i) {
        this.inCallButtonUiDelegate.setAudioRoute(i);
    }

    public void onAudioRouteSelectorDismiss() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.m9i("RttChatFragment.onCreate", (String) null, new Object[0]);
        this.inCallButtonUiDelegate = ((InCallButtonUiDelegateFactory) FragmentUtils.getParent((Fragment) this, InCallButtonUiDelegateFactory.class)).newInCallButtonUiDelegate();
        if (bundle != null) {
            this.inCallButtonUiDelegate.onRestoreInstanceState(bundle);
        }
        this.inCallScreenDelegate = ((InCallScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, InCallScreenDelegateFactory.class)).newInCallScreenDelegate();
        this.isClearingInput = true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frag_rtt_chat, viewGroup, false);
        inflate.setSystemUiVisibility(1808);
        this.editText = (EditText) inflate.findViewById(R.id.rtt_chat_input);
        this.editText.setOnEditorActionListener(this);
        this.editText.addTextChangedListener(this);
        this.editText.setOnKeyListener(new View.OnKeyListener() {
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return RttChatFragment.this.lambda$onCreateView$0$RttChatFragment(view, i, keyEvent);
            }
        });
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.rtt_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setHasFixedSize(false);
        this.adapter = new RttChatAdapter(getContext(), this);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                boolean z = true;
                if (i == 1) {
                    boolean unused = RttChatFragment.this.isUserScrolling = true;
                } else if (i == 0) {
                    boolean unused2 = RttChatFragment.this.isUserScrolling = false;
                    RttChatFragment rttChatFragment = RttChatFragment.this;
                    if (recyclerView.canScrollVertically(1)) {
                        z = false;
                    }
                    boolean unused3 = rttChatFragment.shouldAutoScrolling = z;
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (i2 < 0 && RttChatFragment.this.isUserScrolling) {
                    R$style.hideKeyboardFrom(RttChatFragment.this.getContext(), RttChatFragment.this.editText);
                }
            }
        });
        this.submitButton = (ImageButton) inflate.findViewById(R.id.rtt_chat_submit_button);
        this.submitButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttChatFragment.this.lambda$onCreateView$1$RttChatFragment(view);
            }
        });
        this.submitButton.setEnabled(false);
        this.endCallButton = inflate.findViewById(R.id.rtt_end_call_button);
        this.endCallButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttChatFragment.this.lambda$onCreateView$2$RttChatFragment(view);
            }
        });
        this.overflowMenu = new RttOverflowMenu(getContext(), this.inCallButtonUiDelegate, this.inCallScreenDelegate);
        inflate.findViewById(R.id.rtt_overflow_button).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttChatFragment.this.lambda$onCreateView$3$RttChatFragment(view);
            }
        });
        this.nameTextView = (TextView) inflate.findViewById(R.id.rtt_name_or_number);
        this.chronometer = (Chronometer) inflate.findViewById(R.id.rtt_timer);
        this.statusBanner = (TextView) inflate.findViewById(R.id.rtt_status_banner);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.enterBlock("RttChatFragment.onDestroyView");
        this.inCallButtonUiDelegate.onInCallButtonUiUnready();
        this.inCallScreenDelegate.onInCallScreenUnready();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        if (TextUtils.isEmpty(this.editText.getText())) {
            return true;
        }
        ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.RTT_KEYBOARD_SEND_BUTTON_CLICKED);
        this.submitButton.performClick();
        return true;
    }

    public void onInCallScreenDialpadVisibilityChange(boolean z) {
        this.overflowMenu.setDialpadButtonChecked(z);
    }

    public void onRemoteMessage(String str) {
        this.adapter.addRemoteMessage(str);
    }

    public void onRestoreRttChat(RttTranscript rttTranscript) {
        String onRestoreRttChat = this.adapter.onRestoreRttChat(rttTranscript);
        if (onRestoreRttChat != null) {
            resumeInput(onRestoreRttChat);
        }
    }

    public void onStart() {
        LogUtil.enterBlock("RttChatFragment.onStart");
        super.onStart();
        this.isClearingInput = false;
        ((RttCallPresenter) this.rttCallScreenDelegate).onRttCallScreenUiReady();
        FragmentActivity activity = getActivity();
        Window window = getActivity().getWindow();
        window.setStatusBarColor(activity.getColor(R.color.rtt_status_bar_color));
        window.setNavigationBarColor(activity.getColor(R.color.rtt_navigation_bar_color));
    }

    public void onStop() {
        LogUtil.enterBlock("RttChatFragment.onStop");
        super.onStop();
        this.isClearingInput = true;
        if (this.overflowMenu.isShowing()) {
            this.overflowMenu.dismiss();
        }
        FragmentActivity activity = getActivity();
        Window window = getActivity().getWindow();
        window.setStatusBarColor(activity.getColor(17170445));
        window.setNavigationBarColor(activity.getColor(17170445));
        ((RttCallPresenter) this.rttCallScreenDelegate).onRttCallScreenUiUnready();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.isClearingInput) {
            String computeChangeOfLocalMessage = this.adapter.computeChangeOfLocalMessage(charSequence.toString());
            if (!TextUtils.isEmpty(computeChangeOfLocalMessage)) {
                this.adapter.addLocalMessage(computeChangeOfLocalMessage);
                ((RttCallPresenter) this.rttCallScreenDelegate).onLocalMessage(computeChangeOfLocalMessage);
            }
        }
    }

    public void onUpdateLocalMessage(int i) {
        if (i >= 0) {
            this.recyclerView.smoothScrollToPosition(i);
        }
    }

    public void onUpdateRemoteMessage(int i) {
        if (i >= 0 && this.shouldAutoScrolling) {
            this.recyclerView.smoothScrollToPosition(i);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        LogUtil.m9i("RttChatFragment.onViewCreated", (String) null, new Object[0]);
        this.rttCallScreenDelegate = ((RttCallScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, RttCallScreenDelegateFactory.class)).newRttCallScreenDelegate(this);
        ((RttCallPresenter) this.rttCallScreenDelegate).initRttCallScreenDelegate(this);
        this.inCallScreenDelegate.onInCallScreenDelegateInit(this);
        this.inCallScreenDelegate.onInCallScreenReady();
        this.inCallButtonUiDelegate.onInCallButtonUiReady(this);
    }

    public void requestCallRecordingPermissions(String[] strArr) {
    }

    public void setAudioState(CallAudioState callAudioState) {
        LogUtil.m9i("RttChatFragment.setAudioState", GeneratedOutlineSupport.outline6("audioState: ", callAudioState), new Object[0]);
        this.overflowMenu.setMuteButtonChecked(callAudioState.isMuted());
        this.overflowMenu.setAudioState(callAudioState);
        AudioSelectMenu audioSelectMenu2 = this.audioSelectMenu;
        if (audioSelectMenu2 != null) {
            audioSelectMenu2.setAudioState(callAudioState);
        }
    }

    public void setCallRecordingDuration(long j) {
    }

    public void setCallRecordingState(boolean z) {
    }

    public void setCallState(PrimaryCallState primaryCallState2) {
        LogUtil.m9i("RttChatFragment.setCallState", primaryCallState2.toString(), new Object[0]);
        this.primaryCallState = primaryCallState2;
        if (!this.isTimerStarted && primaryCallState2.state() == 3) {
            LogUtil.m9i("RttChatFragment.setCallState", "starting timer with base: %d", Long.valueOf(this.chronometer.getBase()));
            this.chronometer.setBase(SystemClock.elapsedRealtime() + (primaryCallState2.connectTimeMillis() - System.currentTimeMillis()));
            this.chronometer.start();
            this.isTimerStarted = true;
            this.editText.setVisibility(0);
            this.submitButton.setVisibility(0);
            this.editText.setFocusableInTouchMode(true);
            if (this.editText.requestFocus()) {
                Context context = getContext();
                ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(this.editText, 0);
            }
            this.adapter.showAdvisory();
        }
        if (primaryCallState2.state() == 6) {
            this.statusBanner.setText(getString(R.string.rtt_status_banner_text, this.primaryInfo.name()));
            this.statusBanner.setVisibility(0);
        } else {
            this.statusBanner.setVisibility(8);
        }
        if (primaryCallState2.state() == 10) {
            ((RttCallPresenter) this.rttCallScreenDelegate).onSaveRttTranscript();
        }
    }

    public void setCameraSwitched(boolean z) {
    }

    public void setEnabled(boolean z) {
    }

    public void setEndCallButtonEnabled(boolean z, boolean z2) {
    }

    public void setHold(boolean z) {
    }

    public void setPrimary(PrimaryInfo primaryInfo2) {
        boolean z = false;
        LogUtil.m9i("RttChatFragment.setPrimary", primaryInfo2.toString(), new Object[0]);
        this.nameTextView.setText(primaryInfo2.name());
        if (primaryInfo2.photo() != null && primaryInfo2.photoType() == 2) {
            z = true;
        }
        if (z) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.rtt_avatar_size);
            this.adapter.setAvatarDrawable(R$style.getRoundedDrawable(getContext(), primaryInfo2.photo(), dimensionPixelSize, dimensionPixelSize));
        } else {
            LetterTileDrawable letterTileDrawable = new LetterTileDrawable(getResources());
            letterTileDrawable.setCanonicalDialerLetterTileDetails(primaryInfo2.name(), primaryInfo2.contactInfoLookupKey(), 1, LetterTileDrawable.getContactTypeFromPrimitives(this.primaryCallState.isVoiceMailNumber(), primaryInfo2.isSpam(), this.primaryCallState.isBusinessNumber(), primaryInfo2.numberPresentation(), this.primaryCallState.isConference()));
            this.adapter.setAvatarDrawable(letterTileDrawable);
        }
        this.primaryInfo = primaryInfo2;
    }

    public void setSecondary(SecondaryInfo secondaryInfo) {
        LogUtil.m9i("RttChatFragment.setSecondary", secondaryInfo.toString(), new Object[0]);
        if (!isAdded()) {
            this.savedSecondaryInfo = secondaryInfo;
            return;
        }
        this.savedSecondaryInfo = null;
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.rtt_on_hold_banner);
        if (secondaryInfo.shouldShow()) {
            OnHoldFragment newInstance = OnHoldFragment.newInstance(secondaryInfo);
            newInstance.setPadTopInset(false);
            beginTransaction.replace(R.id.rtt_on_hold_banner, newInstance);
        } else if (findFragmentById != null) {
            beginTransaction.remove(findFragmentById);
        }
        beginTransaction.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
        beginTransaction.commitNowAllowingStateLoss();
        this.overflowMenu.enableSwitchToSecondaryButton(secondaryInfo.shouldShow());
    }

    public void setVideoPaused(boolean z) {
    }

    public void showAudioRouteSelector() {
        this.audioSelectMenu = new AudioSelectMenu(getContext(), this.inCallButtonUiDelegate, new AudioSelectMenu.OnButtonClickListener() {
            public final void onBackPressed() {
                RttChatFragment.this.lambda$showAudioRouteSelector$4$RttChatFragment();
            }
        });
        this.audioSelectMenu.showAtLocation(getView(), 53, 0, 0);
    }

    public void showButton(int i, boolean z) {
        if (i == 4) {
            this.overflowMenu.enableSwapCallButton(z);
        }
    }

    public void showLocationUi(Fragment fragment) {
    }

    public void showManageConferenceCallButton(boolean z) {
    }

    public void showNoteSentToast() {
    }

    public void updateButtonStates() {
    }

    public void updateInCallButtonUiColors(int i) {
    }

    public void updateInCallScreenColors() {
    }
}
