package com.android.incallui.audioroute;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.p000v4.app.Fragment;
import android.telecom.CallAudioState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.TelecomAdapter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class AudioRouteSelectorDialogFragment extends BottomSheetDialogFragment {

    public interface AudioRouteSelectorPresenter {
        void onAudioRouteSelected(int i);

        void onAudioRouteSelectorDismiss();
    }

    private void initItem(TextView textView, int i, CallAudioState callAudioState, DialerImpression$Type dialerImpression$Type) {
        int colorPrimary = ((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getColorPrimary();
        if ((callAudioState.getSupportedRouteMask() & i) == 0) {
            textView.setVisibility(8);
        } else if (callAudioState.getRoute() == i) {
            textView.setSelected(true);
            textView.setTextColor(colorPrimary);
            textView.setCompoundDrawableTintList(ColorStateList.valueOf(colorPrimary));
            textView.setCompoundDrawableTintMode(PorterDuff.Mode.SRC_ATOP);
        }
        textView.setOnClickListener(new View.OnClickListener(dialerImpression$Type, i) {
            private final /* synthetic */ DialerImpression$Type f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                AudioRouteSelectorDialogFragment.this.lambda$initItem$0$AudioRouteSelectorDialogFragment(this.f$1, this.f$2, view);
            }
        });
    }

    private void logCallAudioRouteImpression(DialerImpression$Type dialerImpression$Type) {
        DialerCall outgoingCall = CallList.getInstance().getOutgoingCall();
        if (outgoingCall == null) {
            outgoingCall = CallList.getInstance().getActiveOrBackgroundCall();
        }
        if (outgoingCall != null) {
            ((LoggingBindingsStub) Logger.get(getContext())).logCallImpression(dialerImpression$Type, outgoingCall.getUniqueCallId(), outgoingCall.getTimeAddedMs());
            return;
        }
        ((LoggingBindingsStub) Logger.get(getContext())).logImpression(dialerImpression$Type);
    }

    public static AudioRouteSelectorDialogFragment newInstance(CallAudioState callAudioState) {
        AudioRouteSelectorDialogFragment audioRouteSelectorDialogFragment = new AudioRouteSelectorDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("audio_state", callAudioState);
        audioRouteSelectorDialogFragment.setArguments(bundle);
        return audioRouteSelectorDialogFragment;
    }

    public /* synthetic */ void lambda$createBluetoothItem$1$AudioRouteSelectorDialogFragment(BluetoothDevice bluetoothDevice, View view) {
        logCallAudioRouteImpression(DialerImpression$Type.IN_CALL_SWITCH_AUDIO_ROUTE_BLUETOOTH);
        ((AudioRouteSelectorPresenter) FragmentUtils.getParentUnsafe((Fragment) this, AudioRouteSelectorPresenter.class)).onAudioRouteSelected(2);
        TelecomAdapter.getInstance().requestBluetoothAudio(bluetoothDevice);
        dismiss();
    }

    public /* synthetic */ void lambda$initItem$0$AudioRouteSelectorDialogFragment(DialerImpression$Type dialerImpression$Type, int i, View view) {
        logCallAudioRouteImpression(dialerImpression$Type);
        ((AudioRouteSelectorPresenter) FragmentUtils.getParentUnsafe((Fragment) this, AudioRouteSelectorPresenter.class)).onAudioRouteSelected(i);
        dismiss();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentUtils.checkParent(this, AudioRouteSelectorPresenter.class);
    }

    public void onCancel(DialogInterface dialogInterface) {
        ((AudioRouteSelectorPresenter) FragmentUtils.getParentUnsafe((Fragment) this, AudioRouteSelectorPresenter.class)).onAudioRouteSelectorDismiss();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        LogUtil.m9i("AudioRouteSelectorDialogFragment.onCreateDialog", (String) null, new Object[0]);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), getTheme());
        bottomSheetDialog.getWindow().addFlags(524288);
        if (Settings.canDrawOverlays(getContext())) {
            Window window = bottomSheetDialog.getWindow();
            int i = Build.VERSION.SDK_INT;
            window.setType(2038);
        }
        return bottomSheetDialog;
    }

    @SuppressLint({"NewApi"})
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str;
        View inflate = layoutInflater.inflate(R.layout.audioroute_selector, viewGroup, false);
        CallAudioState callAudioState = (CallAudioState) getArguments().getParcelable("audio_state");
        int i = Build.VERSION.SDK_INT;
        Collection<BluetoothDevice> supportedBluetoothDevices = callAudioState.getSupportedBluetoothDevices();
        for (BluetoothDevice next : supportedBluetoothDevices) {
            boolean z = callAudioState.getRoute() == 2 && (supportedBluetoothDevices.size() == 1 || next.equals(callAudioState.getActiveBluetoothDevice()));
            int colorPrimary = ((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getColorPrimary();
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.audioroute_item, (ViewGroup) null, false);
            try {
                Method declaredMethod = next.getClass().getDeclaredMethod("getAliasName", new Class[0]);
                declaredMethod.setAccessible(true);
                str = (String) declaredMethod.invoke(next, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                str = next.getName();
            }
            textView.setText(str);
            if (z) {
                textView.setSelected(true);
                textView.setTextColor(colorPrimary);
                textView.setCompoundDrawableTintList(ColorStateList.valueOf(colorPrimary));
                textView.setCompoundDrawableTintMode(PorterDuff.Mode.SRC_ATOP);
            }
            textView.setOnClickListener(new View.OnClickListener(next) {
                private final /* synthetic */ BluetoothDevice f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    AudioRouteSelectorDialogFragment.this.lambda$createBluetoothItem$1$AudioRouteSelectorDialogFragment(this.f$1, view);
                }
            });
            ((LinearLayout) inflate).addView(textView, 0);
        }
        initItem((TextView) inflate.findViewById(R.id.audioroute_speaker), 8, callAudioState, DialerImpression$Type.IN_CALL_SWITCH_AUDIO_ROUTE_SPEAKER);
        initItem((TextView) inflate.findViewById(R.id.audioroute_headset), 4, callAudioState, DialerImpression$Type.IN_CALL_SWITCH_AUDIO_ROUTE_WIRED_HEADSET);
        initItem((TextView) inflate.findViewById(R.id.audioroute_earpiece), 1, callAudioState, DialerImpression$Type.IN_CALL_SWITCH_AUDIO_ROUTE_EARPIECE);
        return inflate;
    }
}
