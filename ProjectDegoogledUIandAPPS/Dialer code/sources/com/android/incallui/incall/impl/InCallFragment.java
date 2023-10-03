package com.android.incallui.incall.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.appcompat.R$style;
import android.telecom.CallAudioState;
import android.telephony.TelephonyManager;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.function.Supplier;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.multimedia.MultimediaData;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.dialer.widget.LockableViewPager;
import com.android.incallui.audioroute.AudioRouteSelectorDialogFragment;
import com.android.incallui.contactgrid.ContactGridManager;
import com.android.incallui.hold.OnHoldFragment;
import com.android.incallui.incall.impl.ButtonController;
import com.android.incallui.incall.impl.InCallButtonGridFragment;
import com.android.incallui.incall.protocol.InCallButtonUi;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.incall.protocol.InCallButtonUiDelegateFactory;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegateFactory;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;
import com.android.incallui.incall.protocol.SecondaryInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

public class InCallFragment extends Fragment implements InCallScreen, InCallButtonUi, View.OnClickListener, AudioRouteSelectorDialogFragment.AudioRouteSelectorPresenter, InCallButtonGridFragment.OnButtonGridCreatedListener {
    /* access modifiers changed from: private */
    public InCallPagerAdapter adapter;
    private ButtonChooser buttonChooser;
    private List<ButtonController> buttonControllers = new ArrayList();
    private ContactGridManager contactGridManager;
    private View endCallButton;
    private final Handler handler = new Handler();
    private InCallButtonGridFragment inCallButtonGridFragment;
    private InCallButtonUiDelegate inCallButtonUiDelegate;
    private InCallScreenDelegate inCallScreenDelegate;
    /* access modifiers changed from: private */
    public LockableViewPager pager;
    private final Runnable pagerRunnable = new Runnable() {
        public void run() {
            InCallFragment.this.pager.setCurrentItem(InCallFragment.this.adapter.getButtonGridPosition());
        }
    };
    private InCallPaginator paginator;
    private int phoneType;
    private SecondaryInfo savedSecondaryInfo;
    private boolean stateRestored;
    private int voiceNetworkType;

    private Fragment getLocationFragment() {
        return getChildFragmentManager().findFragmentById(R.id.incall_location_holder);
    }

    private static boolean isSupportedButton(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 8 || i == 9 || i == 12 || i == 14 || i == 16 || i == 15;
    }

    public void dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        this.contactGridManager.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public void enableButton(int i, boolean z) {
        Object[] objArr = {R$style.toString2(i), Boolean.valueOf(z)};
        if (isSupportedButton(i)) {
            getButtonController(i).setEnabled(z);
        }
    }

    public int getAnswerAndDialpadContainerResourceId() {
        return R.id.incall_dialpad_container;
    }

    public ButtonController getButtonController(int i) {
        for (ButtonController next : this.buttonControllers) {
            if (next.getInCallButtonId() == i) {
                return next;
            }
        }
        Assert.fail();
        throw null;
    }

    public Fragment getInCallButtonUiFragment() {
        return this;
    }

    public Fragment getInCallScreenFragment() {
        return this;
    }

    public boolean isManageConferenceVisible() {
        return getButtonController(12).isAllowed();
    }

    public /* synthetic */ boolean lambda$onCreateView$1$InCallFragment(View view, MotionEvent motionEvent) {
        this.handler.removeCallbacks(this.pagerRunnable);
        return false;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        SecondaryInfo secondaryInfo = this.savedSecondaryInfo;
        if (secondaryInfo != null) {
            LogUtil.m9i("InCallFragment.setSecondary", secondaryInfo.toString(), new Object[0]);
            updateButtonStates();
            if (!isAdded()) {
                this.savedSecondaryInfo = secondaryInfo;
                return;
            }
            this.savedSecondaryInfo = null;
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.incall_on_hold_banner);
            if (secondaryInfo.shouldShow()) {
                beginTransaction.replace(R.id.incall_on_hold_banner, OnHoldFragment.newInstance(secondaryInfo));
            } else if (findFragmentById != null) {
                beginTransaction.remove(findFragmentById);
            }
            beginTransaction.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
            beginTransaction.commitNowAllowingStateLoss();
        }
    }

    public void onAudioRouteSelected(int i) {
        this.inCallButtonUiDelegate.setAudioRoute(i);
    }

    public void onAudioRouteSelectorDismiss() {
    }

    public void onButtonGridCreated(InCallButtonGridFragment inCallButtonGridFragment2) {
        LogUtil.m9i("InCallFragment.onButtonGridCreated", "InCallUiReady", new Object[0]);
        this.inCallButtonGridFragment = inCallButtonGridFragment2;
        this.inCallButtonUiDelegate.onInCallButtonUiReady(this);
        updateButtonStates();
    }

    public void onButtonGridDestroyed() {
        LogUtil.m9i("InCallFragment.onButtonGridCreated", "InCallUiUnready", new Object[0]);
        this.inCallButtonUiDelegate.onInCallButtonUiUnready();
        this.inCallButtonGridFragment = null;
    }

    public void onClick(View view) {
        if (view == this.endCallButton) {
            LogUtil.m9i("InCallFragment.onClick", "end call button clicked", new Object[0]);
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.IN_CALL_DIALPAD_HANG_UP_BUTTON_PRESSED);
            this.inCallScreenDelegate.onEndCallClicked();
            return;
        }
        LogUtil.m8e("InCallFragment.onClick", GeneratedOutlineSupport.outline6("unknown view: ", view), new Object[0]);
        Assert.fail();
        throw null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.inCallButtonUiDelegate = ((InCallButtonUiDelegateFactory) FragmentUtils.getParent((Fragment) this, InCallButtonUiDelegateFactory.class)).newInCallButtonUiDelegate();
        if (bundle != null) {
            this.inCallButtonUiDelegate.onRestoreInstanceState(bundle);
            this.stateRestored = true;
        }
    }

    @SuppressLint({"MissingPermission"})
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.m9i("InCallFragment.onCreateView", (String) null, new Object[0]);
        getActivity().setTheme(2131886535);
        View view = (View) StrictModeUtils.bypass(new Supplier(layoutInflater, viewGroup) {
            private final /* synthetic */ LayoutInflater f$0;
            private final /* synthetic */ ViewGroup f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object get() {
                return this.f$0.inflate(R.layout.frag_incall_voice, this.f$1, false);
            }
        });
        this.contactGridManager = new ContactGridManager(view, (ImageView) view.findViewById(R.id.contactgrid_avatar), getResources().getDimensionPixelSize(R.dimen.incall_avatar_size), true);
        this.contactGridManager.onMultiWindowModeChanged(getActivity().isInMultiWindowMode());
        this.paginator = (InCallPaginator) view.findViewById(R.id.incall_paginator);
        this.pager = (LockableViewPager) view.findViewById(R.id.incall_pager);
        this.pager.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return InCallFragment.this.lambda$onCreateView$1$InCallFragment(view, motionEvent);
            }
        });
        this.endCallButton = view.findViewById(R.id.incall_end_call);
        this.endCallButton.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.READ_PHONE_STATE") != 0) {
            this.voiceNetworkType = 0;
        } else {
            this.voiceNetworkType = ((TelephonyManager) getContext().getSystemService(TelephonyManager.class)).getVoiceNetworkType();
        }
        this.phoneType = ((TelephonyManager) getContext().getSystemService(TelephonyManager.class)).getPhoneType();
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener(this) {
            public void onViewAttachedToWindow(View view) {
                View findViewById = view.findViewById(R.id.incall_ui_container);
                int systemWindowInsetTop = view.getRootWindowInsets().getSystemWindowInsetTop();
                int systemWindowInsetBottom = view.getRootWindowInsets().getSystemWindowInsetBottom();
                if (systemWindowInsetTop != findViewById.getPaddingTop()) {
                    TransitionManager.beginDelayedTransition((ViewGroup) findViewById.getParent());
                    findViewById.setPadding(0, systemWindowInsetTop, 0, systemWindowInsetBottom);
                }
            }

            public void onViewDetachedFromWindow(View view) {
            }
        });
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.inCallScreenDelegate.onInCallScreenUnready();
    }

    public void onInCallScreenDialpadVisibilityChange(boolean z) {
        LogUtil.m9i("InCallFragment.onInCallScreenDialpadVisibilityChange", GeneratedOutlineSupport.outline10("isShowing: ", z), new Object[0]);
        getButtonController(2).setChecked(z);
        InCallButtonGridFragment inCallButtonGridFragment2 = this.inCallButtonGridFragment;
        if (inCallButtonGridFragment2 != null) {
            inCallButtonGridFragment2.onInCallScreenDialpadVisibilityChange(z);
        }
        FragmentActivity activity = getActivity();
        activity.getWindow().setNavigationBarColor(activity.getColor(z ? 17170446 : 17170445));
    }

    public void onMultiWindowModeChanged(boolean z) {
        Fragment fragment;
        Fragment locationFragment = getLocationFragment();
        boolean z2 = true;
        if (z == (locationFragment != null && locationFragment.isVisible())) {
            LogUtil.m9i("InCallFragment.onMultiWindowModeChanged", GeneratedOutlineSupport.outline10("hide = ", z), new Object[0]);
            if (z) {
                fragment = null;
            } else {
                fragment = getLocationFragment();
            }
            Fragment locationFragment2 = getLocationFragment();
            if (locationFragment2 == null || !locationFragment2.isVisible()) {
                z2 = false;
            }
            if (fragment != null && !z2) {
                FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.incall_location_holder, fragment);
                beginTransaction.commitAllowingStateLoss();
            } else if (fragment == null && z2) {
                FragmentTransaction beginTransaction2 = getChildFragmentManager().beginTransaction();
                beginTransaction2.remove(getLocationFragment());
                beginTransaction2.commitAllowingStateLoss();
            }
        }
        this.contactGridManager.onMultiWindowModeChanged(z);
    }

    public void onPause() {
        super.onPause();
        this.inCallScreenDelegate.onInCallScreenPaused();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1000) {
            boolean z = iArr.length > 0;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                z &= iArr[i2] == 0;
            }
            if (z) {
                this.inCallButtonUiDelegate.callRecordClicked(true);
            }
        }
    }

    public void onResume() {
        super.onResume();
        this.inCallScreenDelegate.onInCallScreenResumed();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.inCallButtonUiDelegate.onSaveInstanceState(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        LogUtil.m9i("InCallFragment.onViewCreated", (String) null, new Object[0]);
        this.inCallScreenDelegate = ((InCallScreenDelegateFactory) FragmentUtils.getParent((Fragment) this, InCallScreenDelegateFactory.class)).newInCallScreenDelegate();
        Assert.isNotNull(this.inCallScreenDelegate);
        this.buttonControllers.add(new ButtonController.MuteButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.SpeakerButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.DialpadButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.HoldButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.AddCallButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.SwapButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.MergeButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.SwapSimButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.UpgradeToVideoButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.UpgradeToRttButtonController(this.inCallButtonUiDelegate));
        this.buttonControllers.add(new ButtonController.ManageConferenceButtonController(this.inCallScreenDelegate));
        this.buttonControllers.add(new ButtonController.SwitchToSecondaryButtonController(this.inCallScreenDelegate));
        this.buttonControllers.add(new ButtonController.CallRecordButtonController(this.inCallButtonUiDelegate));
        this.inCallScreenDelegate.onInCallScreenDelegateInit(this);
        this.inCallScreenDelegate.onInCallScreenReady();
    }

    public void requestCallRecordingPermissions(String[] strArr) {
        requestPermissions(strArr, 1000);
    }

    public void setAudioState(CallAudioState callAudioState) {
        LogUtil.m9i("InCallFragment.setAudioState", GeneratedOutlineSupport.outline6("audioState: ", callAudioState), new Object[0]);
        ((ButtonController.SpeakerButtonController) getButtonController(0)).setAudioState(callAudioState);
        getButtonController(1).setChecked(callAudioState.isMuted());
    }

    public void setCallRecordingDuration(long j) {
        ((ButtonController.CallRecordButtonController) getButtonController(15)).setRecordingDuration(j);
    }

    public void setCallRecordingState(boolean z) {
        ((ButtonController.CallRecordButtonController) getButtonController(15)).setRecordingState(z);
    }

    public void setCallState(PrimaryCallState primaryCallState) {
        boolean z = false;
        LogUtil.m9i("InCallFragment.setCallState", primaryCallState.toString(), new Object[0]);
        this.contactGridManager.setCallState(primaryCallState);
        getButtonController(13).setAllowed(primaryCallState.swapToSecondaryButtonState() != 0);
        ButtonController buttonController = getButtonController(13);
        if (primaryCallState.swapToSecondaryButtonState() == 2) {
            z = true;
        }
        buttonController.setEnabled(z);
        this.buttonChooser = ButtonController.Controllers.newButtonChooser(this.voiceNetworkType, primaryCallState.isWifi(), this.phoneType);
        updateButtonStates();
    }

    public void setCameraSwitched(boolean z) {
    }

    public void setEnabled(boolean z) {
        "enabled: " + z;
        for (ButtonController enabled : this.buttonControllers) {
            enabled.setEnabled(z);
        }
    }

    public void setEndCallButtonEnabled(boolean z, boolean z2) {
        View view = this.endCallButton;
        if (view != null) {
            view.setEnabled(z);
        }
    }

    public void setHold(boolean z) {
        getButtonController(3).setChecked(z);
    }

    public void setPrimary(PrimaryInfo primaryInfo) {
        LogUtil.m9i("InCallFragment.setPrimary", primaryInfo.toString(), new Object[0]);
        MultimediaData multimediaData = primaryInfo.multimediaData();
        boolean showInCallButtonGrid = primaryInfo.showInCallButtonGrid();
        InCallPagerAdapter inCallPagerAdapter = this.adapter;
        if (inCallPagerAdapter == null) {
            this.adapter = new InCallPagerAdapter(getChildFragmentManager(), multimediaData, showInCallButtonGrid);
            this.pager.setAdapter(this.adapter);
        } else {
            inCallPagerAdapter.setAttachments(multimediaData);
        }
        if (this.adapter.getCount() <= 1 || getResources().getInteger(R.integer.incall_num_rows) <= 1) {
            this.paginator.setVisibility(8);
        } else {
            this.paginator.setVisibility(0);
            this.paginator.setupWithViewPager(this.pager);
            this.pager.setSwipingLocked(false);
            if (!this.stateRestored) {
                this.handler.postDelayed(this.pagerRunnable, 4000);
            } else {
                this.pager.setCurrentItem(this.adapter.getButtonGridPosition(), false);
            }
        }
        this.contactGridManager.setPrimary(primaryInfo);
        if (primaryInfo.shouldShowLocation()) {
            this.contactGridManager.setAvatarHidden(true);
            View findViewById = getView().findViewById(R.id.incall_dialpad_container);
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) layoutParams).removeRule(3);
            }
            findViewById.setLayoutParams(layoutParams);
        }
    }

    public void setSecondary(SecondaryInfo secondaryInfo) {
        LogUtil.m9i("InCallFragment.setSecondary", secondaryInfo.toString(), new Object[0]);
        updateButtonStates();
        if (!isAdded()) {
            this.savedSecondaryInfo = secondaryInfo;
            return;
        }
        this.savedSecondaryInfo = null;
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.incall_on_hold_banner);
        if (secondaryInfo.shouldShow()) {
            beginTransaction.replace(R.id.incall_on_hold_banner, OnHoldFragment.newInstance(secondaryInfo));
        } else if (findFragmentById != null) {
            beginTransaction.remove(findFragmentById);
        }
        beginTransaction.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
        beginTransaction.commitNowAllowingStateLoss();
    }

    public void setVideoPaused(boolean z) {
    }

    public void showAudioRouteSelector() {
        AudioRouteSelectorDialogFragment.newInstance(this.inCallButtonUiDelegate.getCurrentAudioState()).show(getChildFragmentManager(), (String) null);
    }

    public void showButton(int i, boolean z) {
        Object[] objArr = {R$style.toString2(i), Boolean.valueOf(z)};
        if (isSupportedButton(i)) {
            getButtonController(i).setAllowed(z);
            if (i == 5 && z) {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.UPGRADE_TO_VIDEO_CALL_BUTTON_SHOWN);
            }
        }
    }

    public void showLocationUi(Fragment fragment) {
        Fragment locationFragment = getLocationFragment();
        boolean z = locationFragment != null && locationFragment.isVisible();
        if (fragment != null && !z) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.incall_location_holder, fragment);
            beginTransaction.commitAllowingStateLoss();
        } else if (fragment == null && z) {
            FragmentTransaction beginTransaction2 = getChildFragmentManager().beginTransaction();
            beginTransaction2.remove(getLocationFragment());
            beginTransaction2.commitAllowingStateLoss();
        }
    }

    public void showManageConferenceCallButton(boolean z) {
        getButtonController(12).setAllowed(z);
        getButtonController(12).setEnabled(z);
        updateButtonStates();
    }

    public void showNoteSentToast() {
        LogUtil.m9i("InCallFragment.showNoteSentToast", (String) null, new Object[0]);
        Toast.makeText(getContext(), R.string.incall_note_sent, 1).show();
    }

    public void updateButtonStates() {
        InCallButtonGridFragment inCallButtonGridFragment2 = this.inCallButtonGridFragment;
        if (inCallButtonGridFragment2 != null) {
            this.pager.setVisibility(inCallButtonGridFragment2.updateButtonStates(this.buttonControllers, this.buttonChooser, this.voiceNetworkType, this.phoneType) == 0 ? 8 : 0);
            InCallPagerAdapter inCallPagerAdapter = this.adapter;
            if (inCallPagerAdapter == null || inCallPagerAdapter.getCount() <= 1 || getResources().getInteger(R.integer.incall_num_rows) <= 1) {
                this.paginator.setVisibility(8);
                if (this.adapter != null) {
                    this.pager.setSwipingLocked(true);
                    this.pager.setCurrentItem(this.adapter.getButtonGridPosition());
                    return;
                }
                return;
            }
            this.paginator.setVisibility(0);
            this.pager.setSwipingLocked(false);
        }
    }

    public void updateInCallButtonUiColors(int i) {
        this.inCallButtonGridFragment.updateButtonColor(i);
    }

    public void updateInCallScreenColors() {
    }
}
