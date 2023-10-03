package com.android.incallui.rtt.impl;

import android.content.Context;
import android.telecom.CallAudioState;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.android.dialer.R;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;

public class AudioSelectMenu extends PopupWindow {
    private final RttCheckableButton bluetoothButton;
    private final RttCheckableButton earpieceButton;
    private final RttCheckableButton headsetButton;
    private final InCallButtonUiDelegate inCallButtonUiDelegate;
    private final OnButtonClickListener onButtonClickListener;
    private final RttCheckableButton speakerButton;

    interface OnButtonClickListener {
        void onBackPressed();
    }

    AudioSelectMenu(Context context, InCallButtonUiDelegate inCallButtonUiDelegate2, OnButtonClickListener onButtonClickListener2) {
        super(context, (AttributeSet) null, 0, R.style.OverflowMenu);
        this.inCallButtonUiDelegate = inCallButtonUiDelegate2;
        this.onButtonClickListener = onButtonClickListener2;
        View inflate = View.inflate(context, R.layout.audio_route, (ViewGroup) null);
        setContentView(inflate);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            public final void onDismiss() {
                AudioSelectMenu.this.dismiss();
            }
        });
        setFocusable(true);
        setWidth(context.getResources().getDimensionPixelSize(R.dimen.rtt_overflow_menu_width));
        inflate.findViewById(R.id.audioroute_back).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AudioSelectMenu.this.lambda$new$0$AudioSelectMenu(view);
            }
        });
        CallAudioState currentAudioState = inCallButtonUiDelegate2.getCurrentAudioState();
        this.bluetoothButton = (RttCheckableButton) inflate.findViewById(R.id.audioroute_bluetooth);
        this.speakerButton = (RttCheckableButton) inflate.findViewById(R.id.audioroute_speaker);
        this.headsetButton = (RttCheckableButton) inflate.findViewById(R.id.audioroute_headset);
        this.earpieceButton = (RttCheckableButton) inflate.findViewById(R.id.audioroute_earpiece);
        initItem(this.bluetoothButton, 2, currentAudioState);
        initItem(this.speakerButton, 8, currentAudioState);
        initItem(this.headsetButton, 4, currentAudioState);
        initItem(this.earpieceButton, 1, currentAudioState);
    }

    private void initItem(RttCheckableButton rttCheckableButton, int i, CallAudioState callAudioState) {
        if ((callAudioState.getSupportedRouteMask() & i) == 0) {
            rttCheckableButton.setVisibility(8);
        } else if (callAudioState.getRoute() == i) {
            rttCheckableButton.setChecked(true);
        }
        rttCheckableButton.setOnClickListener(new View.OnClickListener(i) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                AudioSelectMenu.this.lambda$initItem$1$AudioSelectMenu(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initItem$1$AudioSelectMenu(int i, View view) {
        this.inCallButtonUiDelegate.setAudioRoute(i);
    }

    public /* synthetic */ void lambda$new$0$AudioSelectMenu(View view) {
        dismiss();
        this.onButtonClickListener.onBackPressed();
    }

    /* access modifiers changed from: package-private */
    public void setAudioState(CallAudioState callAudioState) {
        boolean z = false;
        this.bluetoothButton.setChecked(callAudioState.getRoute() == 2);
        this.speakerButton.setChecked(callAudioState.getRoute() == 8);
        this.headsetButton.setChecked(callAudioState.getRoute() == 4);
        RttCheckableButton rttCheckableButton = this.earpieceButton;
        if (callAudioState.getRoute() == 1) {
            z = true;
        }
        rttCheckableButton.setChecked(z);
    }
}
