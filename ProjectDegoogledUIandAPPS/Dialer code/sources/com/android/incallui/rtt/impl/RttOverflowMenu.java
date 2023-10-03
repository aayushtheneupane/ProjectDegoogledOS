package com.android.incallui.rtt.impl;

import android.content.Context;
import android.telecom.CallAudioState;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.android.dialer.R;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.rtt.impl.RttCheckableButton;

public class RttOverflowMenu extends PopupWindow implements RttCheckableButton.OnCheckedChangeListener {
    private final RttCheckableButton addCallButton;
    private final RttCheckableButton dialpadButton;
    private final InCallButtonUiDelegate inCallButtonUiDelegate;
    private final InCallScreenDelegate inCallScreenDelegate;
    private boolean isSwapCallButtonEnabled;
    private boolean isSwitchToSecondaryButtonEnabled;
    private final RttCheckableButton muteButton;
    private final RttCheckableButton speakerButton;
    private final RttCheckableButton swapCallButton;

    RttOverflowMenu(Context context, InCallButtonUiDelegate inCallButtonUiDelegate2, InCallScreenDelegate inCallScreenDelegate2) {
        super(context, (AttributeSet) null, 0, R.style.OverflowMenu);
        this.inCallButtonUiDelegate = inCallButtonUiDelegate2;
        this.inCallScreenDelegate = inCallScreenDelegate2;
        View inflate = View.inflate(context, R.layout.overflow_menu, (ViewGroup) null);
        setContentView(inflate);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            public final void onDismiss() {
                RttOverflowMenu.this.dismiss();
            }
        });
        setFocusable(true);
        setWidth(context.getResources().getDimensionPixelSize(R.dimen.rtt_overflow_menu_width));
        this.muteButton = (RttCheckableButton) inflate.findViewById(R.id.menu_mute);
        this.muteButton.setOnCheckedChangeListener(this);
        this.speakerButton = (RttCheckableButton) inflate.findViewById(R.id.menu_speaker);
        this.speakerButton.setOnCheckedChangeListener(this);
        this.dialpadButton = (RttCheckableButton) inflate.findViewById(R.id.menu_keypad);
        this.dialpadButton.setOnCheckedChangeListener(this);
        this.addCallButton = (RttCheckableButton) inflate.findViewById(R.id.menu_add_call);
        this.addCallButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttOverflowMenu.this.lambda$new$0$RttOverflowMenu(view);
            }
        });
        this.swapCallButton = (RttCheckableButton) inflate.findViewById(R.id.menu_swap_call);
        this.swapCallButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttOverflowMenu.this.lambda$new$1$RttOverflowMenu(view);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void enableSwapCallButton(boolean z) {
        this.isSwapCallButtonEnabled = z;
        this.swapCallButton.setVisibility((this.isSwapCallButtonEnabled || this.isSwitchToSecondaryButtonEnabled) ? 0 : 8);
    }

    /* access modifiers changed from: package-private */
    public void enableSwitchToSecondaryButton(boolean z) {
        this.isSwitchToSecondaryButtonEnabled = z;
        this.swapCallButton.setVisibility((this.isSwapCallButtonEnabled || this.isSwitchToSecondaryButtonEnabled) ? 0 : 8);
    }

    public /* synthetic */ void lambda$new$0$RttOverflowMenu(View view) {
        this.inCallButtonUiDelegate.addCallClicked();
    }

    public /* synthetic */ void lambda$new$1$RttOverflowMenu(View view) {
        if (this.isSwapCallButtonEnabled) {
            this.inCallButtonUiDelegate.swapClicked();
        }
        if (this.isSwitchToSecondaryButtonEnabled) {
            this.inCallScreenDelegate.onSecondaryInfoClicked();
        }
    }

    public /* synthetic */ void lambda$setAudioState$2$RttOverflowMenu(View view) {
        this.inCallButtonUiDelegate.showAudioRouteSelector();
        dismiss();
    }

    public void onCheckedChanged(RttCheckableButton rttCheckableButton, boolean z) {
        if (rttCheckableButton == this.muteButton) {
            this.inCallButtonUiDelegate.muteClicked(z, true);
        } else if (rttCheckableButton == this.speakerButton) {
            this.inCallButtonUiDelegate.toggleSpeakerphone();
        } else if (rttCheckableButton == this.dialpadButton) {
            this.inCallButtonUiDelegate.showDialpadClicked(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setAudioState(CallAudioState callAudioState) {
        boolean z;
        int i;
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
        if (z2) {
            this.speakerButton.setChecked(z);
            this.speakerButton.setOnClickListener((View.OnClickListener) null);
            this.speakerButton.setOnCheckedChangeListener(this);
            return;
        }
        this.speakerButton.setText(i);
        this.speakerButton.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
        this.speakerButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RttOverflowMenu.this.lambda$setAudioState$2$RttOverflowMenu(view);
            }
        });
        this.speakerButton.setOnCheckedChangeListener((RttCheckableButton.OnCheckedChangeListener) null);
    }

    /* access modifiers changed from: package-private */
    public void setDialpadButtonChecked(boolean z) {
        this.dialpadButton.setChecked(z);
    }

    /* access modifiers changed from: package-private */
    public void setMuteButtonChecked(boolean z) {
        this.muteButton.setChecked(z);
    }
}
