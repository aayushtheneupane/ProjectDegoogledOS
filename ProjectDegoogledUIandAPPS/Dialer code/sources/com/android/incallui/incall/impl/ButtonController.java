package com.android.incallui.incall.impl;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.support.p000v4.util.ArrayMap;
import android.telecom.CallAudioState;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.incallui.incall.impl.CheckableLabeledButton;
import com.android.incallui.incall.impl.MappedButtonConfig;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import java.util.Map;

interface ButtonController {

    public static class AddCallButtonController extends SimpleNonCheckableButtonController {
        public AddCallButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 8, 0, R.string.incall_label_add_call, R.drawable.ic_addcall_white);
            Assert.isNotNull(inCallButtonUiDelegate);
        }

        public void onClick(View view) {
            this.delegate.addCallClicked();
        }
    }

    public static class CallRecordButtonController implements ButtonController, View.OnClickListener {
        private CheckableLabeledButton button;
        private final InCallButtonUiDelegate delegate;
        private boolean isAllowed;
        private boolean isChecked;
        private boolean isEnabled;
        private long recordingSeconds;

        public CallRecordButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            this.delegate = inCallButtonUiDelegate;
        }

        public int getInCallButtonId() {
            return 15;
        }

        public boolean isAllowed() {
            return this.isAllowed;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public void onClick(View view) {
            this.delegate.callRecordClicked(!this.isChecked);
        }

        public void setAllowed(boolean z) {
            this.isAllowed = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setVisibility(z ? 0 : 4);
            }
        }

        public void setButton(CheckableLabeledButton checkableLabeledButton) {
            this.button = checkableLabeledButton;
            if (checkableLabeledButton != null) {
                Resources resources = checkableLabeledButton.getContext().getResources();
                boolean z = this.isChecked;
                int i = R.string.onscreenCallRecordText;
                if (z) {
                    checkableLabeledButton.setLabelText((CharSequence) resources.getString(R.string.onscreenCallRecordingText, new Object[]{DateUtils.formatElapsedTime(this.recordingSeconds)}));
                } else {
                    checkableLabeledButton.setLabelText((int) R.string.onscreenCallRecordText);
                }
                checkableLabeledButton.setEnabled(this.isEnabled);
                checkableLabeledButton.setVisibility(this.isAllowed ? 0 : 4);
                checkableLabeledButton.setChecked(this.isChecked);
                checkableLabeledButton.setOnClickListener(this);
                checkableLabeledButton.setIconDrawable(R.drawable.quantum_ic_record_white_36);
                if (this.isChecked) {
                    i = R.string.onscreenStopCallRecordText;
                }
                checkableLabeledButton.setContentDescription(resources.getText(i));
                checkableLabeledButton.setShouldShowMoreIndicator(false);
            }
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setChecked(z);
            }
        }

        public void setEnabled(boolean z) {
            this.isEnabled = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(z);
            }
        }

        public void setRecordingDuration(long j) {
            this.recordingSeconds = (j + 500) / 1000;
            setButton(this.button);
        }

        public void setRecordingState(boolean z) {
            this.isChecked = z;
            setButton(this.button);
        }
    }

    public static abstract class CheckableButtonController implements ButtonController, CheckableLabeledButton.OnCheckedChangeListener {
        protected CheckableLabeledButton button;
        protected final int buttonId;
        protected final int checkedDescription;
        protected final InCallButtonUiDelegate delegate;
        protected boolean isAllowed;
        protected boolean isChecked;
        protected boolean isEnabled;
        protected final int uncheckedDescription;

        protected CheckableButtonController(InCallButtonUiDelegate inCallButtonUiDelegate, int i, int i2, int i3) {
            Assert.isNotNull(inCallButtonUiDelegate);
            this.delegate = inCallButtonUiDelegate;
            this.buttonId = i;
            this.checkedDescription = i2;
            this.uncheckedDescription = i3;
        }

        /* access modifiers changed from: protected */
        public abstract void doCheckedChanged(boolean z);

        public int getInCallButtonId() {
            return this.buttonId;
        }

        public boolean isAllowed() {
            return this.isAllowed;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public void onCheckedChanged(CheckableLabeledButton checkableLabeledButton, boolean z) {
            CheckableLabeledButton checkableLabeledButton2 = this.button;
            checkableLabeledButton2.setContentDescription(checkableLabeledButton2.getContext().getText(z ? this.checkedDescription : this.uncheckedDescription));
            doCheckedChanged(z);
        }

        public void setAllowed(boolean z) {
            this.isAllowed = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setVisibility(z ? 0 : 4);
            }
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setChecked(z);
            }
        }

        public void setEnabled(boolean z) {
            this.isEnabled = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(z);
            }
        }
    }

    public static final class Controllers {
        static /* synthetic */ void access$000(CheckableLabeledButton checkableLabeledButton) {
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setOnCheckedChangeListener((CheckableLabeledButton.OnCheckedChangeListener) null);
                checkableLabeledButton.setOnClickListener((View.OnClickListener) null);
            }
        }

        private static Map<Integer, MappedButtonConfig.MappingInfo> createCommonMapping() {
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put(1, MappedButtonConfig.MappingInfo.builder(0).build());
            arrayMap.put(2, MappedButtonConfig.MappingInfo.builder(1).build());
            arrayMap.put(0, MappedButtonConfig.MappingInfo.builder(2).build());
            arrayMap.put(15, MappedButtonConfig.MappingInfo.builder(3).build());
            MappedButtonConfig.MappingInfo.Builder builder = MappedButtonConfig.MappingInfo.builder(4);
            builder.setSlotOrder(5);
            arrayMap.put(9, builder.build());
            arrayMap.put(8, MappedButtonConfig.MappingInfo.builder(4).build());
            arrayMap.put(14, MappedButtonConfig.MappingInfo.builder(5).build());
            return arrayMap;
        }

        public static ButtonChooser newButtonChooser(int i, boolean z, int i2) {
            if (i == 13 || z) {
                return newImsAndWiFiButtonChooser();
            }
            if (i2 == 2) {
                Map<Integer, MappedButtonConfig.MappingInfo> createCommonMapping = createCommonMapping();
                MappedButtonConfig.MappingInfo.Builder builder = MappedButtonConfig.MappingInfo.builder(4);
                builder.setSlotOrder(0);
                createCommonMapping.put(12, builder.build());
                MappedButtonConfig.MappingInfo.Builder builder2 = MappedButtonConfig.MappingInfo.builder(4);
                builder2.setSlotOrder(10);
                createCommonMapping.put(5, builder2.build());
                MappedButtonConfig.MappingInfo.Builder builder3 = MappedButtonConfig.MappingInfo.builder(5);
                builder3.setSlotOrder(0);
                createCommonMapping.put(4, builder3.build());
                MappedButtonConfig.MappingInfo.Builder builder4 = MappedButtonConfig.MappingInfo.builder(5);
                builder4.setSlotOrder(5);
                createCommonMapping.put(3, builder4.build());
                MappedButtonConfig.MappingInfo.Builder builder5 = MappedButtonConfig.MappingInfo.builder(5);
                builder5.setSlotOrder(Integer.MAX_VALUE);
                builder5.setMutuallyExclusiveButton(4);
                createCommonMapping.put(13, builder5.build());
                return new ButtonChooser(new MappedButtonConfig(createCommonMapping));
            } else if (i2 != 1) {
                return newImsAndWiFiButtonChooser();
            } else {
                Map<Integer, MappedButtonConfig.MappingInfo> createCommonMapping2 = createCommonMapping();
                MappedButtonConfig.MappingInfo.Builder builder6 = MappedButtonConfig.MappingInfo.builder(4);
                builder6.setSlotOrder(0);
                createCommonMapping2.put(13, builder6.build());
                MappedButtonConfig.MappingInfo.Builder builder7 = MappedButtonConfig.MappingInfo.builder(4);
                builder7.setSlotOrder(10);
                createCommonMapping2.put(5, builder7.build());
                MappedButtonConfig.MappingInfo.Builder builder8 = MappedButtonConfig.MappingInfo.builder(5);
                builder8.setSlotOrder(0);
                createCommonMapping2.put(12, builder8.build());
                MappedButtonConfig.MappingInfo.Builder builder9 = MappedButtonConfig.MappingInfo.builder(5);
                builder9.setSlotOrder(5);
                createCommonMapping2.put(3, builder9.build());
                return new ButtonChooser(new MappedButtonConfig(createCommonMapping2));
            }
        }

        private static ButtonChooser newImsAndWiFiButtonChooser() {
            Map<Integer, MappedButtonConfig.MappingInfo> createCommonMapping = createCommonMapping();
            MappedButtonConfig.MappingInfo.Builder builder = MappedButtonConfig.MappingInfo.builder(4);
            builder.setSlotOrder(0);
            createCommonMapping.put(12, builder.build());
            MappedButtonConfig.MappingInfo.Builder builder2 = MappedButtonConfig.MappingInfo.builder(3);
            builder2.setSlotOrder(0);
            createCommonMapping.put(16, builder2.build());
            MappedButtonConfig.MappingInfo.Builder builder3 = MappedButtonConfig.MappingInfo.builder(4);
            builder3.setSlotOrder(10);
            createCommonMapping.put(5, builder3.build());
            MappedButtonConfig.MappingInfo.Builder builder4 = MappedButtonConfig.MappingInfo.builder(5);
            builder4.setSlotOrder(0);
            createCommonMapping.put(13, builder4.build());
            MappedButtonConfig.MappingInfo.Builder builder5 = MappedButtonConfig.MappingInfo.builder(5);
            builder5.setSlotOrder(10);
            createCommonMapping.put(3, builder5.build());
            return new ButtonChooser(new MappedButtonConfig(createCommonMapping));
        }
    }

    public static class DialpadButtonController extends SimpleCheckableButtonController {
        public DialpadButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 2, 0, 0, R.string.incall_label_dialpad, R.drawable.quantum_ic_dialpad_vd_theme_24);
        }

        public void doCheckedChanged(boolean z) {
            this.delegate.showDialpadClicked(z);
        }
    }

    public static class HoldButtonController extends SimpleCheckableButtonController {
        public HoldButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 3, R.string.incall_content_description_unhold, R.string.incall_content_description_hold, R.string.incall_label_hold, R.drawable.quantum_ic_pause_vd_theme_24);
        }

        public void doCheckedChanged(boolean z) {
            this.delegate.holdClicked(z);
        }
    }

    public static class ManageConferenceButtonController extends SimpleNonCheckableButtonController {
        private final InCallScreenDelegate inCallScreenDelegate;

        public ManageConferenceButtonController(InCallScreenDelegate inCallScreenDelegate2) {
            super((InCallButtonUiDelegate) null, 12, R.string.a11y_description_incall_label_manage_content, R.string.incall_label_manage, R.drawable.quantum_ic_group_vd_theme_24);
            Assert.isNotNull(inCallScreenDelegate2);
            this.inCallScreenDelegate = inCallScreenDelegate2;
        }

        public void onClick(View view) {
            this.inCallScreenDelegate.onManageConferenceClicked();
        }
    }

    public static class MergeButtonController extends SimpleNonCheckableButtonController {
        public MergeButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 9, R.string.incall_content_description_merge_calls, R.string.incall_label_merge, R.drawable.quantum_ic_call_merge_vd_theme_24);
            Assert.isNotNull(inCallButtonUiDelegate);
        }

        public void onClick(View view) {
            this.delegate.mergeClicked();
        }
    }

    public static class MuteButtonController extends SimpleCheckableButtonController {
        public MuteButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 1, R.string.incall_content_description_muted, R.string.incall_content_description_unmuted, R.string.incall_label_mute, R.drawable.quantum_ic_mic_off_vd_theme_24);
        }

        public void doCheckedChanged(boolean z) {
            this.delegate.muteClicked(z, true);
        }
    }

    public static abstract class NonCheckableButtonController implements ButtonController, View.OnClickListener {
        protected CheckableLabeledButton button;
        protected final int buttonId;
        protected final int contentDescription;
        protected final InCallButtonUiDelegate delegate;
        protected boolean isAllowed;
        protected boolean isEnabled;

        protected NonCheckableButtonController(InCallButtonUiDelegate inCallButtonUiDelegate, int i, int i2) {
            this.delegate = inCallButtonUiDelegate;
            this.buttonId = i;
            this.contentDescription = i2;
        }

        public int getInCallButtonId() {
            return this.buttonId;
        }

        public boolean isAllowed() {
            return this.isAllowed;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public void setAllowed(boolean z) {
            this.isAllowed = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setVisibility(z ? 0 : 4);
            }
        }

        public void setChecked(boolean z) {
            Assert.fail();
            throw null;
        }

        public void setEnabled(boolean z) {
            this.isEnabled = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(z);
            }
        }
    }

    public static abstract class SimpleCheckableButtonController extends CheckableButtonController {
        private final int icon;
        private final int label;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected SimpleCheckableButtonController(InCallButtonUiDelegate inCallButtonUiDelegate, int i, int i2, int i3, int i4, int i5) {
            super(inCallButtonUiDelegate, i, i2 == 0 ? i4 : i2, i3 == 0 ? i4 : i3);
            this.label = i4;
            this.icon = i5;
        }

        public void setButton(CheckableLabeledButton checkableLabeledButton) {
            Controllers.access$000(this.button);
            this.button = checkableLabeledButton;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(this.isEnabled);
                checkableLabeledButton.setVisibility(this.isAllowed ? 0 : 4);
                checkableLabeledButton.setChecked(this.isChecked);
                checkableLabeledButton.setOnClickListener((View.OnClickListener) null);
                checkableLabeledButton.setOnCheckedChangeListener(this);
                checkableLabeledButton.setContentDescription(checkableLabeledButton.getContext().getText(this.isChecked ? this.checkedDescription : this.uncheckedDescription));
                checkableLabeledButton.setShouldShowMoreIndicator(false);
            }
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setLabelText(this.label);
                checkableLabeledButton.setIconDrawable(this.icon);
            }
        }
    }

    public static abstract class SimpleNonCheckableButtonController extends NonCheckableButtonController {
        private final int icon;
        private final int label;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected SimpleNonCheckableButtonController(InCallButtonUiDelegate inCallButtonUiDelegate, int i, int i2, int i3, int i4) {
            super(inCallButtonUiDelegate, i, i2 == 0 ? i3 : i2);
            this.label = i3;
            this.icon = i4;
        }

        public void setButton(CheckableLabeledButton checkableLabeledButton) {
            Controllers.access$000(this.button);
            this.button = checkableLabeledButton;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(this.isEnabled);
                checkableLabeledButton.setVisibility(this.isAllowed ? 0 : 4);
                checkableLabeledButton.setChecked(false);
                checkableLabeledButton.setOnCheckedChangeListener((CheckableLabeledButton.OnCheckedChangeListener) null);
                checkableLabeledButton.setOnClickListener(this);
                checkableLabeledButton.setContentDescription(checkableLabeledButton.getContext().getText(this.contentDescription));
                checkableLabeledButton.setShouldShowMoreIndicator(false);
            }
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setLabelText(this.label);
                checkableLabeledButton.setIconDrawable(this.icon);
            }
        }
    }

    public static class SpeakerButtonController implements ButtonController, CheckableLabeledButton.OnCheckedChangeListener, View.OnClickListener {
        private CheckableLabeledButton button;
        private CharSequence contentDescription;
        private final InCallButtonUiDelegate delegate;
        private int icon = R.drawable.quantum_ic_volume_up_vd_theme_24;
        private boolean isAllowed;
        private boolean isChecked;
        private boolean isEnabled;
        private CharSequence isOffContentDescription;
        private CharSequence isOnContentDescription;
        private int label = R.string.incall_label_speaker;
        private boolean nonBluetoothMode;

        public SpeakerButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            this.delegate = inCallButtonUiDelegate;
        }

        public int getInCallButtonId() {
            return 0;
        }

        public boolean isAllowed() {
            return this.isAllowed;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public void onCheckedChanged(CheckableLabeledButton checkableLabeledButton, boolean z) {
            checkableLabeledButton.setContentDescription(z ? this.isOnContentDescription : this.isOffContentDescription);
            this.delegate.toggleSpeakerphone();
        }

        public void onClick(View view) {
            this.delegate.showAudioRouteSelector();
        }

        public void setAllowed(boolean z) {
            this.isAllowed = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(this.isEnabled && z);
            }
        }

        public void setAudioState(CallAudioState callAudioState) {
            boolean z;
            int i;
            int i2;
            boolean z2;
            int supportedRouteMask = callAudioState.getSupportedRouteMask() & 2;
            int i3 = R.string.incall_content_description_speaker;
            int i4 = R.drawable.quantum_ic_volume_up_vd_theme_24;
            if (supportedRouteMask == 2) {
                if ((callAudioState.getRoute() & 2) == 2) {
                    i4 = R.drawable.volume_bluetooth;
                    i3 = R.string.incall_content_description_bluetooth;
                } else if ((callAudioState.getRoute() & 8) != 8) {
                    if ((callAudioState.getRoute() & 4) == 4) {
                        i4 = R.drawable.quantum_ic_headset_vd_theme_24;
                        i3 = R.string.incall_content_description_headset;
                    } else {
                        i4 = R.drawable.quantum_ic_phone_in_talk_vd_theme_24;
                        i = R.string.incall_content_description_earpiece;
                        z = false;
                        i2 = R.string.incall_label_audio;
                        z2 = false;
                    }
                }
                i = i3;
                z = false;
                i2 = R.string.incall_label_audio;
                z2 = true;
            } else {
                boolean z3 = callAudioState.getRoute() == 8;
                i = R.string.incall_content_description_speaker;
                i2 = R.string.incall_label_speaker;
                z2 = z3;
                z = true;
            }
            this.nonBluetoothMode = z;
            this.isChecked = z2;
            this.label = i2;
            this.icon = i4;
            this.contentDescription = this.delegate.getContext().getText(i);
            this.isOnContentDescription = TextUtils.concat(new CharSequence[]{this.contentDescription, this.delegate.getContext().getText(R.string.incall_talkback_speaker_on)});
            this.isOffContentDescription = TextUtils.concat(new CharSequence[]{this.contentDescription, this.delegate.getContext().getText(R.string.incall_talkback_speaker_off)});
            CheckableLabeledButton checkableLabeledButton = this.button;
            this.button = checkableLabeledButton;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(this.isEnabled && this.isAllowed);
                checkableLabeledButton.setVisibility(0);
                checkableLabeledButton.setChecked(this.isChecked);
                SpeakerButtonController speakerButtonController = null;
                checkableLabeledButton.setOnClickListener(this.nonBluetoothMode ? null : this);
                if (this.nonBluetoothMode) {
                    speakerButtonController = this;
                }
                checkableLabeledButton.setOnCheckedChangeListener(speakerButtonController);
                checkableLabeledButton.setLabelText(this.label);
                checkableLabeledButton.setIconDrawable(this.icon);
                checkableLabeledButton.setContentDescription((!this.nonBluetoothMode || this.isChecked) ? this.isOnContentDescription : this.isOffContentDescription);
                checkableLabeledButton.setShouldShowMoreIndicator(!this.nonBluetoothMode);
            }
        }

        public void setButton(CheckableLabeledButton checkableLabeledButton) {
            this.button = checkableLabeledButton;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(this.isEnabled && this.isAllowed);
                checkableLabeledButton.setVisibility(0);
                checkableLabeledButton.setChecked(this.isChecked);
                SpeakerButtonController speakerButtonController = null;
                checkableLabeledButton.setOnClickListener(this.nonBluetoothMode ? null : this);
                if (this.nonBluetoothMode) {
                    speakerButtonController = this;
                }
                checkableLabeledButton.setOnCheckedChangeListener(speakerButtonController);
                checkableLabeledButton.setLabelText(this.label);
                checkableLabeledButton.setIconDrawable(this.icon);
                checkableLabeledButton.setContentDescription((!this.nonBluetoothMode || this.isChecked) ? this.isOnContentDescription : this.isOffContentDescription);
                checkableLabeledButton.setShouldShowMoreIndicator(!this.nonBluetoothMode);
            }
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setChecked(z);
            }
        }

        public void setEnabled(boolean z) {
            this.isEnabled = z;
            CheckableLabeledButton checkableLabeledButton = this.button;
            if (checkableLabeledButton != null) {
                checkableLabeledButton.setEnabled(z && this.isAllowed);
            }
        }
    }

    public static class SwapButtonController extends SimpleNonCheckableButtonController {
        public SwapButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 4, R.string.incall_content_description_swap_calls, R.string.incall_label_swap, R.drawable.quantum_ic_swap_calls_vd_theme_24);
            Assert.isNotNull(inCallButtonUiDelegate);
        }

        public void onClick(View view) {
            this.delegate.swapClicked();
        }
    }

    public static class SwapSimButtonController extends SimpleNonCheckableButtonController {
        public SwapSimButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 14, R.string.incall_content_description_swap_sim, R.string.incall_label_swap_sim, R.drawable.ic_sim_change_white);
        }

        public void onClick(View view) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.button.getIconDrawable();
            animationDrawable.stop();
            animationDrawable.start();
            this.delegate.swapSimClicked();
        }
    }

    public static class SwitchToSecondaryButtonController extends SimpleNonCheckableButtonController {
        private final InCallScreenDelegate inCallScreenDelegate;

        public SwitchToSecondaryButtonController(InCallScreenDelegate inCallScreenDelegate2) {
            super((InCallButtonUiDelegate) null, 13, R.string.incall_content_description_swap_calls, R.string.incall_label_swap, R.drawable.quantum_ic_swap_calls_vd_theme_24);
            Assert.isNotNull(inCallScreenDelegate2);
            this.inCallScreenDelegate = inCallScreenDelegate2;
        }

        public void onClick(View view) {
            this.inCallScreenDelegate.onSecondaryInfoClicked();
        }
    }

    public static class UpgradeToRttButtonController extends SimpleNonCheckableButtonController {
        public UpgradeToRttButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 16, 0, R.string.incall_label_rttcall, R.drawable.quantum_ic_rtt_vd_theme_24);
            Assert.isNotNull(inCallButtonUiDelegate);
        }

        public void onClick(View view) {
            this.delegate.changeToRttClicked();
        }
    }

    public static class UpgradeToVideoButtonController extends SimpleNonCheckableButtonController {
        public UpgradeToVideoButtonController(InCallButtonUiDelegate inCallButtonUiDelegate) {
            super(inCallButtonUiDelegate, 5, 0, R.string.incall_label_videocall, R.drawable.quantum_ic_videocam_vd_theme_24);
            Assert.isNotNull(inCallButtonUiDelegate);
        }

        public void onClick(View view) {
            this.delegate.changeToVideoClicked();
        }
    }

    int getInCallButtonId();

    boolean isAllowed();

    boolean isEnabled();

    void setAllowed(boolean z);

    void setButton(CheckableLabeledButton checkableLabeledButton);

    void setChecked(boolean z);

    void setEnabled(boolean z);
}
