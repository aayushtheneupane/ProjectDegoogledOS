package com.android.dialer.dialpadview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.contacts.common.dialog.CallSubjectDialog;
import com.android.contacts.common.util.StopWatch;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.dialpadview.DialpadFragment;
import com.android.dialer.dialpadview.DialpadKeyButton;
import com.android.dialer.dialpadview.PseudoEmergencyAnimator;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.oem.MotorolaUtils;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.precall.PreCall;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.FloatingActionButtonController;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class DialpadFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener, View.OnKeyListener, AdapterView.OnItemClickListener, TextWatcher, PopupMenu.OnMenuItemClickListener, DialpadKeyButton.OnPressedListener {
    static final String KEY_EMERGENCY_NOTIFICATION_DELAY_INT = "emergency_notification_delay_int";
    private static Optional<String> currentCountryIsoForTesting = Optional.absent();
    private static Boolean showEmergencyCallWarningForTest = null;
    private boolean animate = false;
    private CallStateReceiver callStateReceiver;
    private boolean clearDigitsOnStop;
    private boolean dTMFToneEnabled;
    private View delete;
    private ListView dialpadChooser;
    private DialpadChooserAdapter dialpadChooserAdapter;
    private OnDialpadQueryChangedListener dialpadQueryListener;
    private int dialpadSlideInDuration;
    private DialpadView dialpadView;
    private EditText digits;
    private boolean digitsFilledByIntent;
    private TextView digitsHint;
    private boolean firstLaunch = false;
    /* access modifiers changed from: private */
    public FloatingActionButton floatingActionButton;
    /* access modifiers changed from: private */
    public FloatingActionButtonController floatingActionButtonController;
    private DialerExecutor<String> initPhoneNumberFormattingTextWatcherExecutor;
    private boolean isDialpadSlideUp;
    private boolean isLandscape;
    private boolean isLayoutRtl;
    private String lastNumberDialed = "";
    /* access modifiers changed from: private */
    public View overflowMenuButton;
    private PopupMenu overflowPopupMenu;
    private final HashSet<View> pressedDialpadKeys = new HashSet<>(12);
    private String prohibitedPhoneNumberRegexp;
    private PseudoEmergencyAnimator pseudoEmergencyAnimator;
    /* access modifiers changed from: private */
    public PhoneAccountHandle selectedAccount;
    private boolean startedFromNewIntent = false;
    private ToneGenerator toneGenerator;
    private final Object toneGeneratorLock = new Object();
    private boolean wasEmptyBeforeTextChange;

    private class CallStateReceiver extends BroadcastReceiver {
        /* synthetic */ CallStateReceiver(C04741 r2) {
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("state");
            if ((TextUtils.equals(stringExtra, TelephonyManager.EXTRA_STATE_IDLE) || TextUtils.equals(stringExtra, TelephonyManager.EXTRA_STATE_OFFHOOK)) && DialpadFragment.access$700(DialpadFragment.this)) {
                LogUtil.m9i("CallStateReceiver.onReceive", "hiding dialpad chooser, state: %s", stringExtra);
                DialpadFragment.this.showDialpadChooser(false);
            }
        }
    }

    public static class DialerPhoneNumberFormattingTextWatcher extends PhoneNumberFormattingTextWatcher {
        private static final Pattern AR_DOMESTIC_CALL_MOBILE_NUMBER_PATTERN = Pattern.compile("0?(  (   11|   2(     2(       02?|       [13]|       2[13-79]|       4[1-6]|       5[2457]|       6[124-8]|       7[1-4]|       8[13-6]|       9[1267]     )|     3(       02?|       1[467]|       2[03-6]|       3[13-8]|       [49][2-6]|       5[2-8]|       [67]     )|     4(       7[3-578]|       9     )|     6(       [0136]|       2[24-6]|       4[6-8]?|       5[15-8]     )|     80|     9(       0[1-3]|       [19]|       2\\d|       3[1-6]|       4[02568]?|       5[2-4]|       6[2-46]|       72?|       8[23]?     )   )|   3(     3(       2[79]|       6|       8[2578]     )|     4(       0[0-24-9]|       [12]|       3[5-8]?|       4[24-7]|       5[4-68]?|       6[02-9]|       7[126]|       8[2379]?|       9[1-36-8]     )|     5(       1|       2[1245]|       3[237]?|       4[1-46-9]|       6[2-4]|       7[1-6]|       8[2-5]?     )|     6[24]|     7(       [069]|       1[1568]|       2[15]|       3[145]|       4[13]|       5[14-8]|       7[2-57]|       8[126]     )|     8(       [01]|       2[15-7]|       3[2578]?|       4[13-6]|       5[4-8]?|       6[1-357-9]|       7[36-8]?|       8[5-8]?|       9[124]     )   ) )?15).*".replaceAll("\\s+", ""));
        private final String countryCode;

        DialerPhoneNumberFormattingTextWatcher(String str) {
            super(str);
            this.countryCode = str;
        }

        public synchronized void afterTextChanged(Editable editable) {
            String str = this.countryCode;
            int length = str.length();
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (R$style.isLowerCase(str.charAt(i))) {
                    char[] charArray = str.toCharArray();
                    while (i < length) {
                        char c = charArray[i];
                        if (R$style.isLowerCase(c)) {
                            charArray[i] = (char) (c & '_');
                        }
                        i++;
                    }
                    str = String.valueOf(charArray);
                } else {
                    i++;
                }
            }
            if (!str.equals("AR")) {
                super.afterTextChanged(editable);
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < editable.length(); i2++) {
                char charAt = editable.charAt(i2);
                if (PhoneNumberUtils.isNonSeparator(charAt)) {
                    sb.append(charAt);
                }
            }
            String sb2 = sb.toString();
            if (!AR_DOMESTIC_CALL_MOBILE_NUMBER_PATTERN.matcher(sb2).matches()) {
                super.afterTextChanged(editable);
            } else if (!sb2.contentEquals(editable)) {
                editable.replace(0, editable.length(), sb2);
                Selection.setSelection(editable, editable.length());
                PhoneNumberUtils.addTtsSpan(editable, 0, editable.length());
            }
        }
    }

    private static class DialpadChooserAdapter extends BaseAdapter {
        private ChoiceItem[] choiceItems = new ChoiceItem[3];
        private LayoutInflater inflater;

        static class ChoiceItem {
            Bitmap icon;

            /* renamed from: id */
            int f23id;
            String text;

            ChoiceItem(String str, Bitmap bitmap, int i) {
                this.text = str;
                this.icon = bitmap;
                this.f23id = i;
            }
        }

        DialpadChooserAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
            this.choiceItems[0] = new ChoiceItem(context.getString(R.string.dialer_useDtmfDialpad), BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_dialer_fork_tt_keypad), 101);
            this.choiceItems[1] = new ChoiceItem(context.getString(R.string.dialer_returnToInCallScreen), BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_dialer_fork_current_call), 102);
            this.choiceItems[2] = new ChoiceItem(context.getString(R.string.dialer_addAnotherCall), BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_dialer_fork_add_call), 103);
        }

        public int getCount() {
            return 3;
        }

        public Object getItem(int i) {
            return this.choiceItems[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.inflater.inflate(R.layout.dialpad_chooser_list_item, (ViewGroup) null);
            }
            ((TextView) view.findViewById(R.id.text)).setText(this.choiceItems[i].text);
            ((ImageView) view.findViewById(R.id.icon)).setImageBitmap(this.choiceItems[i].icon);
            return view;
        }
    }

    public interface DialpadListener {
        void getLastOutgoingCall(LastOutgoingCallCallback lastOutgoingCallCallback);

        void onCallPlacedFromDialpad();

        void onDialpadShown();
    }

    public static class DialpadSlidingRelativeLayout extends RelativeLayout {
        public DialpadSlidingRelativeLayout(Context context) {
            super(context);
        }

        public float getYFraction() {
            int height = getHeight();
            if (height == 0) {
                return 0.0f;
            }
            return getTranslationY() / ((float) height);
        }

        public void setYFraction(float f) {
            setTranslationY(f * ((float) getHeight()));
        }

        public DialpadSlidingRelativeLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public DialpadSlidingRelativeLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }
    }

    public static class ErrorDialogFragment extends DialogFragment {
        private int messageResId;
        private int titleResId;

        public static ErrorDialogFragment newInstance(int i) {
            ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("argTitleResId", 0);
            bundle.putInt("argMessageResId", i);
            errorDialogFragment.setArguments(bundle);
            return errorDialogFragment;
        }

        public /* synthetic */ void lambda$onCreateDialog$0$DialpadFragment$ErrorDialogFragment(DialogInterface dialogInterface, int i) {
            dismiss();
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.titleResId = getArguments().getInt("argTitleResId");
            this.messageResId = getArguments().getInt("argMessageResId");
        }

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            int i = this.titleResId;
            if (i != 0) {
                builder.setTitle(i);
            }
            int i2 = this.messageResId;
            if (i2 != 0) {
                builder.setMessage(i2);
            }
            builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DialpadFragment.ErrorDialogFragment.this.lambda$onCreateDialog$0$DialpadFragment$ErrorDialogFragment(dialogInterface, i);
                }
            });
            return builder.create();
        }
    }

    public interface HostInterface {
        boolean onDialpadSpacerTouchWithEmptyQuery();

        boolean shouldShowDialpadChooser();
    }

    private static class InitPhoneNumberFormattingTextWatcherWorker implements DialerExecutor.Worker<String, DialerPhoneNumberFormattingTextWatcher> {
        /* synthetic */ InitPhoneNumberFormattingTextWatcherWorker(C04741 r1) {
        }

        public Object doInBackground(Object obj) throws Throwable {
            return new DialerPhoneNumberFormattingTextWatcher((String) obj);
        }
    }

    public interface LastOutgoingCallCallback {
        void lastOutgoingCall(String str);
    }

    public interface OnDialpadQueryChangedListener {
        void onDialpadQueryChanged(String str);
    }

    static /* synthetic */ boolean access$700(DialpadFragment dialpadFragment) {
        return dialpadFragment.dialpadChooser.getVisibility() == 0;
    }

    static boolean canAddDigit(CharSequence charSequence, int i, int i2, char c) {
        if (c != ';' && c != ',') {
            throw new IllegalArgumentException("Should not be called for anything other than PAUSE & WAIT");
        } else if (i == -1 || i2 < i || i > charSequence.length() || i2 > charSequence.length() || i == 0) {
            return false;
        } else {
            if (c == ';') {
                if (charSequence.charAt(i - 1) == ';') {
                    return false;
                }
                if (charSequence.length() <= i2 || charSequence.charAt(i2) != ';') {
                    return true;
                }
                return false;
            }
            return true;
        }
    }

    private String getCurrentCountryIso() {
        if (currentCountryIsoForTesting.isPresent()) {
            return currentCountryIsoForTesting.get();
        }
        return R$style.getCurrentCountryIso(getActivity());
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getActivity().getSystemService("phone");
    }

    private void handleDialButtonPressed() {
        boolean z = false;
        if (isDigitsEmpty()) {
            PerformanceReport.recordClick(UiAction$Type.PRESS_CALL_BUTTON_WITHOUT_CALLING);
            if (getTelephonyManager().getPhoneType() == 2) {
                z = true;
            }
            if (z && isPhoneInUse()) {
                Intent build = new CallIntentBuilder("", CallInitiationType$Type.DIALPAD).build();
                build.putExtra("com.android.phone.extra.SEND_EMPTY_FLASH", true);
                startActivity(build);
            } else if (!TextUtils.isEmpty(this.lastNumberDialed)) {
                PerformanceReport.setIgnoreActionOnce(UiAction$Type.TEXT_CHANGE_WITH_INPUT);
                this.digits.setText(this.lastNumberDialed);
                EditText editText = this.digits;
                editText.setSelection(editText.getText().length());
            } else {
                playTone(26, 150);
            }
        } else {
            String obj = this.digits.getText().toString();
            if (obj == null || TextUtils.isEmpty(this.prohibitedPhoneNumberRegexp) || !obj.matches(this.prohibitedPhoneNumberRegexp)) {
                PreCall.start(getActivity(), new CallIntentBuilder(obj, CallInitiationType$Type.DIALPAD).setPhoneAccountHandle(this.selectedAccount));
                hideAndClearDialpad();
                return;
            }
            PerformanceReport.recordClick(UiAction$Type.PRESS_CALL_BUTTON_WITHOUT_CALLING);
            LogUtil.m9i("DialpadFragment.handleDialButtonPressed", "The phone number is prohibited explicitly by a rule.", new Object[0]);
            if (getActivity() != null) {
                ErrorDialogFragment.newInstance(R.string.dialog_phone_call_prohibited_message).show(getFragmentManager(), "phone_prohibited_dialog");
            }
            clearDialpad();
        }
    }

    private void hideAndClearDialpad() {
        LogUtil.enterBlock("DialpadFragment.hideAndClearDialpad");
        ((DialpadListener) FragmentUtils.getParentUnsafe((Fragment) this, DialpadListener.class)).onCallPlacedFromDialpad();
    }

    public static boolean isAddCallMode(Intent intent) {
        if (intent == null) {
            return false;
        }
        String action = intent.getAction();
        if ("android.intent.action.DIAL".equals(action) || "android.intent.action.VIEW".equals(action)) {
            return intent.getBooleanExtra("add_call_mode", false);
        }
        return false;
    }

    private boolean isDialpadChooserVisible() {
        return this.dialpadChooser.getVisibility() == 0;
    }

    /* access modifiers changed from: private */
    public boolean isDigitsEmpty() {
        return this.digits.length() == 0;
    }

    private boolean isLayoutReady() {
        return this.digits != null;
    }

    private boolean isPhoneInUse() {
        return getActivity() != null && TelecomUtil.isInManagedCall(getActivity()) && ((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).shouldShowDialpadChooser();
    }

    private void keyPressed(int i) {
        if (getView() != null && getView().getTranslationY() == 0.0f) {
            switch (i) {
                case 7:
                    playTone(0, -1);
                    break;
                case 8:
                    playTone(1, -1);
                    break;
                case 9:
                    playTone(2, -1);
                    break;
                case 10:
                    playTone(3, -1);
                    break;
                case 11:
                    playTone(4, -1);
                    break;
                case 12:
                    playTone(5, -1);
                    break;
                case 13:
                    playTone(6, -1);
                    break;
                case 14:
                    playTone(7, -1);
                    break;
                case 15:
                    playTone(8, -1);
                    break;
                case 16:
                    playTone(9, -1);
                    break;
                case 17:
                    playTone(10, -1);
                    break;
                case 18:
                    playTone(11, -1);
                    break;
            }
            getView().performHapticFeedback(1);
            this.digits.onKeyDown(i, new KeyEvent(0, i));
            int length = this.digits.length();
            if (length == this.digits.getSelectionStart() && length == this.digits.getSelectionEnd()) {
                this.digits.setCursorVisible(false);
            }
        }
    }

    private void playTone(int i, int i2) {
        int ringerMode;
        if (this.dTMFToneEnabled && (ringerMode = ((AudioManager) getActivity().getSystemService("audio")).getRingerMode()) != 0 && ringerMode != 1) {
            synchronized (this.toneGeneratorLock) {
                if (this.toneGenerator == null) {
                    LogUtil.m10w("DialpadFragment.playTone", "mToneGenerator == null, tone: " + i, new Object[0]);
                    return;
                }
                this.toneGenerator.startTone(i, i2);
            }
        }
    }

    private void removePreviousDigitIfPossible(char c) {
        int selectionStart = this.digits.getSelectionStart();
        if (selectionStart > 0) {
            int i = selectionStart - 1;
            if (c == this.digits.getText().charAt(i)) {
                this.digits.setSelection(selectionStart);
                this.digits.getText().delete(i, selectionStart);
            }
        }
    }

    public static void setCurrentCountryIsoForTesting(String str) {
        currentCountryIsoForTesting = Optional.m67of(str);
    }

    private void setFormattedDigits(String str, String str2) {
        String currentCountryIso = getCurrentCountryIso();
        String extractNetworkPortion = PhoneNumberUtils.extractNetworkPortion(str);
        String extractPostDialPortion = PhoneNumberUtils.extractPostDialPortion(str);
        if (!TextUtils.isEmpty(extractNetworkPortion)) {
            String formatNumber = PhoneNumberHelper.formatNumber(getActivity(), extractNetworkPortion, str2, currentCountryIso);
            if (TextUtils.isEmpty(extractPostDialPortion)) {
                extractPostDialPortion = formatNumber;
            } else {
                extractPostDialPortion = formatNumber.concat(extractPostDialPortion);
            }
        }
        if (!TextUtils.isEmpty(extractPostDialPortion)) {
            Editable text = this.digits.getText();
            text.replace(0, text.length(), extractPostDialPortion);
            if (!this.digitsFilledByIntent && SpecialCharSequenceMgr.handleChars(getActivity(), text.toString(), this.digits)) {
                this.digits.getText().clear();
            }
            if (isDigitsEmpty()) {
                this.digitsFilledByIntent = false;
                this.digits.setCursorVisible(false);
            }
            OnDialpadQueryChangedListener onDialpadQueryChangedListener = this.dialpadQueryListener;
            if (onDialpadQueryChangedListener != null) {
                onDialpadQueryChangedListener.onDialpadQueryChanged(this.digits.getText().toString());
            }
            updateDeleteButtonEnabledState();
        }
    }

    static void setShowEmergencyCallWarningForTest(Boolean bool) {
        showEmergencyCallWarningForTest = bool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r4 = (android.telephony.TelephonyManager) r4.getSystemService(android.telephony.TelephonyManager.class);
     */
    @android.annotation.TargetApi(26)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean shouldShowEmergencyCallWarning(android.content.Context r4) {
        /*
            java.lang.Boolean r0 = showEmergencyCallWarningForTest
            if (r0 == 0) goto L_0x0009
            boolean r4 = r0.booleanValue()
            return r4
        L_0x0009:
            int r0 = android.os.Build.VERSION.SDK_INT
            boolean r0 = com.android.dialer.util.PermissionsUtil.hasReadPhoneStatePermissions(r4)
            r1 = 0
            if (r0 != 0) goto L_0x0013
            return r1
        L_0x0013:
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.Object r4 = r4.getSystemService(r0)
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4
            android.os.PersistableBundle r0 = r4.getCarrierConfig()
            if (r0 == 0) goto L_0x005b
            r2 = -1
            java.lang.String r3 = "emergency_notification_delay_int"
            int r0 = r0.getInt(r3, r2)
            if (r0 != r2) goto L_0x002b
            goto L_0x005b
        L_0x002b:
            android.telephony.ServiceState r0 = r4.getServiceState()
            int r0 = r0.getState()
            if (r0 == 0) goto L_0x005b
            r2 = 1
            if (r0 == r2) goto L_0x005a
            r3 = 2
            if (r0 == r3) goto L_0x005b
            r1 = 3
            if (r0 != r1) goto L_0x003f
            goto L_0x005a
        L_0x003f:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "unknown state "
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r1)
            android.telephony.ServiceState r4 = r4.getServiceState()
            int r4 = r4.getState()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        L_0x005a:
            return r2
        L_0x005b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.dialpadview.DialpadFragment.shouldShowEmergencyCallWarning(android.content.Context):boolean");
    }

    /* access modifiers changed from: private */
    public void showDialpadChooser(boolean z) {
        if (getActivity() != null) {
            if (this.digits != null) {
                if (z) {
                    LogUtil.m9i("DialpadFragment.showDialpadChooser", "Showing dialpad chooser!", new Object[0]);
                    DialpadView dialpadView2 = this.dialpadView;
                    if (dialpadView2 != null) {
                        dialpadView2.setVisibility(8);
                    }
                    PopupMenu popupMenu = this.overflowPopupMenu;
                    if (popupMenu != null) {
                        popupMenu.dismiss();
                    }
                    this.floatingActionButtonController.scaleOut();
                    this.dialpadChooser.setVisibility(0);
                    if (this.dialpadChooserAdapter == null) {
                        this.dialpadChooserAdapter = new DialpadChooserAdapter(getActivity());
                    }
                    this.dialpadChooser.setAdapter(this.dialpadChooserAdapter);
                    return;
                }
                LogUtil.m9i("DialpadFragment.showDialpadChooser", "Displaying normal Dialer UI.", new Object[0]);
                if (this.dialpadView != null) {
                    LogUtil.m9i("DialpadFragment.showDialpadChooser", "mDialpadView not null", new Object[0]);
                    this.dialpadView.setVisibility(0);
                    if (this.isDialpadSlideUp) {
                        this.floatingActionButtonController.scaleIn();
                    }
                } else {
                    LogUtil.m9i("DialpadFragment.showDialpadChooser", "mDialpadView null", new Object[0]);
                    this.digits.setVisibility(0);
                }
                this.dialpadChooser.setVisibility(8);
            }
        }
    }

    private void stopTone() {
        if (this.dTMFToneEnabled) {
            synchronized (this.toneGeneratorLock) {
                if (this.toneGenerator == null) {
                    LogUtil.m10w("DialpadFragment.stopTone", "mToneGenerator == null", new Object[0]);
                } else {
                    this.toneGenerator.stopTone();
                }
            }
        }
    }

    private void updateDeleteButtonEnabledState() {
        if (getActivity() != null) {
            this.delete.setEnabled(!isDigitsEmpty());
        }
    }

    private void updateDialString(char c) {
        if (c == ';' || c == ',') {
            int selectionStart = this.digits.getSelectionStart();
            int selectionEnd = this.digits.getSelectionEnd();
            int min = Math.min(selectionStart, selectionEnd);
            int max = Math.max(selectionStart, selectionEnd);
            if (min == -1) {
                min = this.digits.length();
                max = min;
            }
            Editable text = this.digits.getText();
            if (canAddDigit(text, min, max, c)) {
                text.replace(min, max, Character.toString(c));
                if (min != max) {
                    this.digits.setSelection(min + 1);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Not expected for anything other than PAUSE & WAIT");
    }

    private void updateDialpadHint() {
        if (!TextUtils.isEmpty(this.digits.getText())) {
            this.digitsHint.setVisibility(8);
        } else if (shouldShowEmergencyCallWarning(getActivity())) {
            String string = getActivity().getString(R.string.dialpad_hint_emergency_calling_not_available);
            this.digits.setContentDescription(string);
            this.digitsHint.setText(string);
            this.digitsHint.setVisibility(0);
        } else {
            this.digits.setContentDescription((CharSequence) null);
            this.digitsHint.setVisibility(8);
        }
    }

    public void afterTextChanged(Editable editable) {
        if (!this.digitsFilledByIntent && SpecialCharSequenceMgr.handleChars(getActivity(), editable.toString(), this.digits)) {
            this.digits.getText().clear();
        }
        if (isDigitsEmpty()) {
            this.digitsFilledByIntent = false;
            this.digits.setCursorVisible(false);
        }
        OnDialpadQueryChangedListener onDialpadQueryChangedListener = this.dialpadQueryListener;
        if (onDialpadQueryChangedListener != null) {
            onDialpadQueryChangedListener.onDialpadQueryChanged(this.digits.getText().toString());
        }
        updateDeleteButtonEnabledState();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.wasEmptyBeforeTextChange = TextUtils.isEmpty(charSequence);
    }

    public void clearDialpad() {
        EditText editText = this.digits;
        if (editText != null) {
            editText.getText().clear();
        }
        this.selectedAccount = null;
    }

    public boolean getAnimate() {
        return this.animate;
    }

    public Context getContext() {
        return getActivity();
    }

    public EditText getDigitsWidget() {
        return this.digits;
    }

    public String getQuery() {
        return this.digits.getText().toString();
    }

    public boolean isDialpadSlideUp() {
        return this.isDialpadSlideUp;
    }

    public /* synthetic */ void lambda$onCreate$0$DialpadFragment(DialerPhoneNumberFormattingTextWatcher dialerPhoneNumberFormattingTextWatcher) {
        this.dialpadView.getDigits().addTextChangedListener(dialerPhoneNumberFormattingTextWatcher);
    }

    public /* synthetic */ boolean lambda$onCreateView$1$DialpadFragment(View view, MotionEvent motionEvent) {
        if (!isDigitsEmpty()) {
            return false;
        }
        if (getActivity() == null) {
            return true;
        }
        LogUtil.m9i("DialpadFragment.onCreateView", "dialpad spacer touched", new Object[0]);
        return ((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).onDialpadSpacerTouchWithEmptyQuery();
    }

    public /* synthetic */ void lambda$queryLastOutgoingCall$2$DialpadFragment(String str) {
        if (getActivity() != null) {
            this.lastNumberDialed = str;
            updateDeleteButtonEnabledState();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.isLayoutRtl = CallUtil.isRtl();
        this.isLandscape = getResources().getConfiguration().orientation == 2;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.dialpad_floating_action_button) {
            view.performHapticFeedback(1);
            handleDialButtonPressed();
        } else if (id == R.id.deleteButton) {
            keyPressed(67);
        } else if (id == R.id.digits) {
            if (!isDigitsEmpty()) {
                this.digits.setCursorVisible(true);
            }
        } else if (id == R.id.dialpad_overflow) {
            this.overflowPopupMenu.show();
        } else {
            LogUtil.m10w("DialpadFragment.onClick", GeneratedOutlineSupport.outline6("Unexpected event from: ", view), new Object[0]);
        }
    }

    public void onCreate(Bundle bundle) {
        Trace.beginSection("DialpadFragment onCreate");
        LogUtil.enterBlock("DialpadFragment.onCreate");
        super.onCreate(bundle);
        this.firstLaunch = bundle == null;
        this.prohibitedPhoneNumberRegexp = getResources().getString(R.string.config_prohibited_phone_number_regexp);
        if (bundle != null) {
            this.digitsFilledByIntent = bundle.getBoolean("pref_digits_filled_by_intent");
            this.isDialpadSlideUp = bundle.getBoolean("pref_is_dialpad_slide_out");
        }
        this.dialpadSlideInDuration = getResources().getInteger(R.integer.dialpad_slide_in_duration);
        if (this.callStateReceiver == null) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PHONE_STATE");
            this.callStateReceiver = new CallStateReceiver((C04741) null);
            getActivity().registerReceiver(this.callStateReceiver, intentFilter);
        }
        this.initPhoneNumberFormattingTextWatcherExecutor = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getActivity()).dialerExecutorFactory()).createUiTaskBuilder(getFragmentManager(), "DialpadFragment.initPhoneNumberFormattingTextWatcher", new InitPhoneNumberFormattingTextWatcherWorker((C04741) null)).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                DialpadFragment.this.lambda$onCreate$0$DialpadFragment((DialpadFragment.DialerPhoneNumberFormattingTextWatcher) obj);
            }
        }).build();
        Trace.endSection();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Trace.beginSection("DialpadFragment onCreateView");
        LogUtil.enterBlock("DialpadFragment.onCreateView");
        Trace.beginSection("DialpadFragment inflate view");
        View inflate = layoutInflater.inflate(R.layout.dialpad_fragment, viewGroup, false);
        Trace.endSection();
        Trace.beginSection("DialpadFragment buildLayer");
        inflate.buildLayer();
        Trace.endSection();
        Trace.beginSection("DialpadFragment setup views");
        this.dialpadView = (DialpadView) inflate.findViewById(R.id.dialpad_view);
        this.dialpadView.setCanDigitsBeEdited(true);
        this.digits = this.dialpadView.getDigits();
        this.digitsHint = this.dialpadView.getDigitsHint();
        this.digits.setKeyListener(UnicodeDialerKeyListener.INSTANCE);
        this.digits.setOnClickListener(this);
        this.digits.setOnKeyListener(this);
        this.digits.setOnLongClickListener(this);
        this.digits.addTextChangedListener(this);
        this.digits.setElegantTextHeight(false);
        if (!MotorolaUtils.shouldDisablePhoneNumberFormatting(getActivity())) {
            this.initPhoneNumberFormattingTextWatcherExecutor.executeSerial(getCurrentCountryIso());
        }
        if (inflate.findViewById(R.id.one) != null) {
            for (int findViewById : new int[]{R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.star, R.id.zero, R.id.pound}) {
                ((DialpadKeyButton) inflate.findViewById(findViewById)).setOnPressedListener(this);
            }
            ((DialpadKeyButton) inflate.findViewById(R.id.one)).setOnLongClickListener(this);
            ((DialpadKeyButton) inflate.findViewById(R.id.zero)).setOnLongClickListener(this);
        }
        this.delete = this.dialpadView.getDeleteButton();
        View view = this.delete;
        if (view != null) {
            view.setOnClickListener(this);
            this.delete.setOnLongClickListener(this);
        }
        inflate.findViewById(R.id.spacer).setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return DialpadFragment.this.lambda$onCreateView$1$DialpadFragment(view, motionEvent);
            }
        });
        this.digits.setCursorVisible(false);
        this.dialpadChooser = (ListView) inflate.findViewById(R.id.dialpadChooser);
        this.dialpadChooser.setOnItemClickListener(this);
        this.floatingActionButton = (FloatingActionButton) inflate.findViewById(R.id.dialpad_floating_action_button);
        this.floatingActionButton.setOnClickListener(this);
        this.floatingActionButtonController = new FloatingActionButtonController(getActivity(), this.floatingActionButton);
        Trace.endSection();
        Trace.endSection();
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        PseudoEmergencyAnimator pseudoEmergencyAnimator2 = this.pseudoEmergencyAnimator;
        if (pseudoEmergencyAnimator2 != null) {
            pseudoEmergencyAnimator2.destroy();
            this.pseudoEmergencyAnimator = null;
        }
        getActivity().unregisterReceiver(this.callStateReceiver);
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (getActivity() != null && getView() != null && !z) {
            if (!(this.dialpadChooser.getVisibility() == 0)) {
                if (this.animate) {
                    this.dialpadView.animateShow();
                }
                ((DialpadListener) FragmentUtils.getParentUnsafe((Fragment) this, DialpadListener.class)).onDialpadShown();
                this.digits.requestFocus();
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = ((DialpadChooserAdapter.ChoiceItem) adapterView.getItemAtPosition(i)).f23id;
        if (i2 == 101) {
            TelecomUtil.showInCallScreen(getActivity(), true);
            getActivity().finish();
        } else if (i2 == 102) {
            TelecomUtil.showInCallScreen(getActivity(), false);
            getActivity().finish();
        } else if (i2 == 103) {
            showDialpadChooser(false);
        } else {
            LogUtil.m10w("DialpadFragment.onItemClick", GeneratedOutlineSupport.outline5("Unexpected itemId: ", i2), new Object[0]);
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view.getId() != R.id.digits || i != 66) {
            return false;
        }
        handleDialButtonPressed();
        return true;
    }

    public boolean onLongClick(View view) {
        boolean z;
        boolean z2;
        Editable text = this.digits.getText();
        int id = view.getId();
        if (id == R.id.deleteButton) {
            text.clear();
            return true;
        }
        boolean z3 = false;
        if (id == R.id.one) {
            if (!isDigitsEmpty() && !TextUtils.equals(this.digits.getText(), "1") && !TextUtils.equals(this.digits.getText(), "11")) {
                return false;
            }
            removePreviousDigitIfPossible('1');
            removePreviousDigitIfPossible('1');
            List<PhoneAccountHandle> subscriptionPhoneAccounts = TelecomUtil.getSubscriptionPhoneAccounts(getActivity());
            if (!(subscriptionPhoneAccounts.size() > 1 && !subscriptionPhoneAccounts.contains(TelecomUtil.getDefaultOutgoingPhoneAccount(getActivity(), "voicemail")))) {
                try {
                    PhoneAccountHandle defaultOutgoingPhoneAccount = TelecomUtil.getDefaultOutgoingPhoneAccount(getActivity(), "voicemail");
                    if (defaultOutgoingPhoneAccount == null) {
                        z2 = TextUtils.isEmpty(getTelephonyManager().getVoiceMailNumber());
                    } else {
                        z2 = TextUtils.isEmpty(TelecomUtil.getVoicemailNumber(getActivity(), defaultOutgoingPhoneAccount));
                    }
                    z = !z2;
                } catch (SecurityException unused) {
                    LogUtil.m10w("DialpadFragment.isVoicemailAvailable", "SecurityException is thrown. Maybe privilege isn't sufficient.", new Object[0]);
                    z = false;
                }
                if (!z) {
                    if (getActivity() != null) {
                        if (Settings.System.getInt(getActivity().getContentResolver(), "airplane_mode_on", 0) != 0) {
                            z3 = true;
                        }
                        if (z3) {
                            ErrorDialogFragment.newInstance(R.string.dialog_voicemail_airplane_mode_message).show(getFragmentManager(), "voicemail_request_during_airplane_mode");
                        } else {
                            ErrorDialogFragment.newInstance(R.string.dialog_voicemail_not_ready_message).show(getFragmentManager(), "voicemail_not_ready");
                        }
                    }
                    return true;
                }
            }
            PreCall.start(getActivity(), CallIntentBuilder.forVoicemail((PhoneAccountHandle) null, CallInitiationType$Type.DIALPAD));
            hideAndClearDialpad();
            return true;
        } else if (id == R.id.zero) {
            if (this.pressedDialpadKeys.contains(view)) {
                removePreviousDigitIfPossible('0');
                removePreviousDigitIfPossible('0');
            }
            keyPressed(81);
            stopTone();
            this.pressedDialpadKeys.remove(view);
            return true;
        } else {
            if (id == R.id.digits) {
                this.digits.setCursorVisible(true);
            }
            return false;
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getGroupId() == 1) {
            this.selectedAccount = (PhoneAccountHandle) menuItem.getIntent().getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
            return true;
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_2s_pause) {
            updateDialString(',');
            return true;
        } else if (itemId == R.id.menu_add_wait) {
            updateDialString(';');
            return true;
        } else if (itemId != R.id.menu_call_with_note) {
            return false;
        } else {
            Activity activity = getActivity();
            String obj = this.digits.getText().toString();
            CallSubjectDialog.start(activity, -1, (Uri) null, (Uri) null, obj, obj, (String) null, (String) null, 1, (PhoneAccountHandle) null);
            hideAndClearDialpad();
            return true;
        }
    }

    public void onPause() {
        super.onPause();
        stopTone();
        this.pressedDialpadKeys.clear();
        this.lastNumberDialed = "";
        SpecialCharSequenceMgr.cleanup();
        this.overflowPopupMenu.dismiss();
    }

    public void onPressed(View view, boolean z) {
        if (z) {
            int id = view.getId();
            if (id == R.id.one) {
                keyPressed(8);
            } else if (id == R.id.two) {
                keyPressed(9);
            } else if (id == R.id.three) {
                keyPressed(10);
            } else if (id == R.id.four) {
                keyPressed(11);
            } else if (id == R.id.five) {
                keyPressed(12);
            } else if (id == R.id.six) {
                keyPressed(13);
            } else if (id == R.id.seven) {
                keyPressed(14);
            } else if (id == R.id.eight) {
                keyPressed(15);
            } else if (id == R.id.nine) {
                keyPressed(16);
            } else if (id == R.id.zero) {
                keyPressed(7);
            } else if (id == R.id.pound) {
                keyPressed(18);
            } else if (id == R.id.star) {
                keyPressed(17);
            } else {
                LogUtil.m8e("DialpadFragment.onPressed", GeneratedOutlineSupport.outline6("Unexpected onTouch(ACTION_DOWN) event from: ", view), new Object[0]);
            }
            this.pressedDialpadKeys.add(view);
            return;
        }
        this.pressedDialpadKeys.remove(view);
        if (this.pressedDialpadKeys.isEmpty()) {
            stopTone();
        }
    }

    public void onResume() {
        boolean z;
        Uri data;
        Cursor query;
        LogUtil.enterBlock("DialpadFragment.onResume");
        Trace.beginSection("DialpadFragment onResume");
        super.onResume();
        this.dialpadQueryListener = (OnDialpadQueryChangedListener) FragmentUtils.getParentUnsafe((Fragment) this, OnDialpadQueryChangedListener.class);
        StopWatch start = StopWatch.start("Dialpad.onResume");
        this.lastNumberDialed = "";
        if (PermissionsUtil.hasCallLogReadPermissions(getActivity())) {
            ((DialpadListener) FragmentUtils.getParentUnsafe((Fragment) this, DialpadListener.class)).getLastOutgoingCall(new LastOutgoingCallCallback() {
                public final void lastOutgoingCall(String str) {
                    DialpadFragment.this.lambda$queryLastOutgoingCall$2$DialpadFragment(str);
                }
            });
        }
        start.lap("qloc");
        this.dTMFToneEnabled = Settings.System.getInt(getActivity().getContentResolver(), "dtmf_tone", 1) == 1;
        start.lap("dtwd");
        start.lap("hptc");
        this.pressedDialpadKeys.clear();
        Intent intent = getActivity().getIntent();
        LogUtil.m9i("DialpadFragment.configureScreenFromIntent", "action: %s", intent.getAction());
        if (!isLayoutReady()) {
            LogUtil.m9i("DialpadFragment.configureScreenFromIntent", "Screen configuration is requested before onCreateView() is called. Ignored", new Object[0]);
        } else if (isAddCallMode(intent)) {
            LogUtil.m9i("DialpadFragment.configureScreenFromIntent", "Add call mode", new Object[0]);
            showDialpadChooser(false);
            this.startedFromNewIntent = true;
        } else {
            if (this.firstLaunch || this.startedFromNewIntent) {
                String action = intent.getAction();
                if (("android.intent.action.DIAL".equals(action) || "android.intent.action.VIEW".equals(action)) && (data = intent.getData()) != null) {
                    if ("tel".equals(data.getScheme())) {
                        String schemeSpecificPart = data.getSchemeSpecificPart();
                        this.digitsFilledByIntent = true;
                        setFormattedDigits(PhoneNumberUtils.convertKeypadLettersToDigits(PhoneNumberUtils.replaceUnicodeDigits(schemeSpecificPart)), (String) null);
                    } else if (PermissionsUtil.hasContactsReadPermissions(getActivity())) {
                        String type = intent.getType();
                        if (("vnd.android.cursor.item/person".equals(type) || "vnd.android.cursor.item/phone".equals(type)) && (query = getActivity().getContentResolver().query(intent.getData(), new String[]{"number", "number_key"}, (String) null, (String[]) null, (String) null)) != null) {
                            try {
                                if (query.moveToFirst()) {
                                    this.digitsFilledByIntent = true;
                                    setFormattedDigits(query.getString(0), query.getString(1));
                                } else {
                                    query.close();
                                }
                            } finally {
                                query.close();
                            }
                        }
                    }
                    z = true;
                    if ((this.startedFromNewIntent || !z) && isPhoneInUse()) {
                        LogUtil.m9i("DialpadFragment.configureScreenFromIntent", "Dialpad chooser mode", new Object[0]);
                        showDialpadChooser(true);
                        this.startedFromNewIntent = false;
                    } else {
                        LogUtil.m9i("DialpadFragment.configureScreenFromIntent", "Nothing to show", new Object[0]);
                        showDialpadChooser(false);
                        this.startedFromNewIntent = false;
                    }
                }
            }
            z = false;
            if (this.startedFromNewIntent) {
            }
            LogUtil.m9i("DialpadFragment.configureScreenFromIntent", "Dialpad chooser mode", new Object[0]);
            showDialpadChooser(true);
            this.startedFromNewIntent = false;
        }
        start.lap("fdin");
        if (!isPhoneInUse()) {
            LogUtil.m9i("DialpadFragment.onResume", "phone not in use", new Object[0]);
            showDialpadChooser(false);
        }
        start.lap("hnt");
        updateDeleteButtonEnabledState();
        start.lap("bes");
        start.stopAndLog("DialpadFragment", 50);
        this.overflowMenuButton = this.dialpadView.getOverflowMenuButton();
        C04741 r1 = new PopupMenu(getActivity(), this.overflowMenuButton) {
            public void show() {
                PhoneAccountHandle phoneAccountHandle;
                boolean z;
                TelecomManager telecomManager;
                Menu menu = getMenu();
                boolean z2 = !DialpadFragment.this.isDigitsEmpty();
                for (int i = 0; i < menu.size(); i++) {
                    MenuItem item = menu.getItem(i);
                    item.setEnabled(z2);
                    if (item.getItemId() == R.id.menu_call_with_note) {
                        Activity activity = DialpadFragment.this.getActivity();
                        if (PermissionsUtil.hasPermission(activity, "android.permission.READ_PHONE_STATE") && (telecomManager = (TelecomManager) activity.getSystemService("telecom")) != null) {
                            Iterator<PhoneAccountHandle> it = telecomManager.getCallCapablePhoneAccounts().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                PhoneAccount phoneAccount = telecomManager.getPhoneAccount(it.next());
                                if (phoneAccount != null && phoneAccount.hasCapabilities(64)) {
                                    z = true;
                                    break;
                                }
                            }
                            item.setVisible(z);
                        }
                        z = false;
                        item.setVisible(z);
                    }
                }
                MenuItem findItem = menu.findItem(R.id.call_with);
                List<PhoneAccount> callCapablePhoneAccounts = CallUtil.getCallCapablePhoneAccounts(DialpadFragment.this.getActivity(), "tel");
                if (callCapablePhoneAccounts == null || callCapablePhoneAccounts.size() <= 1) {
                    findItem.setVisible(false);
                } else {
                    if (DialpadFragment.this.selectedAccount != null) {
                        phoneAccountHandle = DialpadFragment.this.selectedAccount;
                    } else {
                        phoneAccountHandle = TelecomUtil.getDefaultOutgoingPhoneAccount(DialpadFragment.this.getActivity(), "tel");
                    }
                    SubMenu subMenu = findItem.getSubMenu();
                    subMenu.clear();
                    for (PhoneAccount next : callCapablePhoneAccounts) {
                        PhoneAccountHandle accountHandle = next.getAccountHandle();
                        subMenu.add(1, 0, 0, next.getLabel()).setIntent(new Intent().putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", accountHandle)).setChecked(accountHandle.equals(phoneAccountHandle));
                    }
                    subMenu.setGroupCheckable(1, true, true);
                    findItem.setVisible(subMenu.hasVisibleItems());
                }
                super.show();
            }
        };
        r1.inflate(R.menu.dialpad_options);
        r1.setOnMenuItemClickListener(this);
        this.overflowPopupMenu = r1;
        this.overflowMenuButton.setOnTouchListener(this.overflowPopupMenu.getDragToOpenListener());
        this.overflowMenuButton.setOnClickListener(this);
        this.overflowMenuButton.setVisibility(isDigitsEmpty() ? 4 : 0);
        updateDialpadHint();
        if (this.firstLaunch) {
            super.onHiddenChanged(false);
            if (!(getActivity() == null || getView() == null || isDialpadChooserVisible())) {
                if (this.animate) {
                    this.dialpadView.animateShow();
                }
                ((DialpadListener) FragmentUtils.getParentUnsafe((Fragment) this, DialpadListener.class)).onDialpadShown();
                this.digits.requestFocus();
            }
        }
        this.firstLaunch = false;
        Trace.endSection();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("pref_digits_filled_by_intent", this.digitsFilledByIntent);
        bundle.putBoolean("pref_is_dialpad_slide_out", this.isDialpadSlideUp);
    }

    public void onStart() {
        LogUtil.m9i("DialpadFragment.onStart", "first launch: %b", Boolean.valueOf(this.firstLaunch));
        Trace.beginSection("DialpadFragment onStart");
        super.onStart();
        this.floatingActionButtonController.changeIcon(getActivity(), MotorolaUtils.isWifiCallingAvailable(getActivity()) ? R.drawable.ic_wifi_calling : R.drawable.quantum_ic_call_vd_theme_24, getResources().getString(R.string.description_dial_button));
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.toneGeneratorLock) {
            if (this.toneGenerator == null) {
                try {
                    this.toneGenerator = new ToneGenerator(8, 80);
                } catch (RuntimeException e) {
                    LogUtil.m8e("DialpadFragment.onStart", "Exception caught while creating local tone generator: " + e, new Object[0]);
                    this.toneGenerator = null;
                }
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 50) {
            LogUtil.m9i("DialpadFragment.onStart", "Time for ToneGenerator creation: " + currentTimeMillis2, new Object[0]);
        }
        Trace.endSection();
    }

    public void onStop() {
        LogUtil.enterBlock("DialpadFragment.onStop");
        super.onStop();
        this.floatingActionButtonController.scaleOut();
        synchronized (this.toneGeneratorLock) {
            if (this.toneGenerator != null) {
                this.toneGenerator.release();
                this.toneGenerator = null;
            }
        }
        if (this.clearDigitsOnStop) {
            this.clearDigitsOnStop = false;
            clearDialpad();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.wasEmptyBeforeTextChange != TextUtils.isEmpty(charSequence)) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.invalidateOptionsMenu();
                boolean z = this.wasEmptyBeforeTextChange;
                this.overflowMenuButton = this.dialpadView.getOverflowMenuButton();
                if (z) {
                    AnimUtils.fadeIn(this.overflowMenuButton, -1);
                } else {
                    AnimUtils.fadeOut(this.overflowMenuButton, -1, new AnimUtils.AnimationCallback() {
                        public void onAnimationEnd() {
                            DialpadFragment.this.overflowMenuButton.setVisibility(4);
                        }
                    });
                }
            }
            updateDialpadHint();
        }
    }

    public void process_quote_emergency_unquote(String str) {
        if ("01189998819991197253".equals(str)) {
            if (this.pseudoEmergencyAnimator == null) {
                this.pseudoEmergencyAnimator = new PseudoEmergencyAnimator(new PseudoEmergencyAnimator.ViewProvider() {
                    public View getFab() {
                        return DialpadFragment.this.floatingActionButton;
                    }
                });
            }
            this.pseudoEmergencyAnimator.start();
            return;
        }
        PseudoEmergencyAnimator pseudoEmergencyAnimator2 = this.pseudoEmergencyAnimator;
        if (pseudoEmergencyAnimator2 != null) {
            pseudoEmergencyAnimator2.end();
        }
    }

    public void setAnimate(boolean z) {
        this.animate = z;
    }

    public void setStartedFromNewIntent(boolean z) {
        this.startedFromNewIntent = z;
    }

    public void slideDown(boolean z, Animation.AnimationListener animationListener) {
        Assert.checkArgument(this.isDialpadSlideUp);
        this.isDialpadSlideUp = false;
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), this.isLandscape ? this.isLayoutRtl ? R.anim.dialpad_slide_out_left : R.anim.dialpad_slide_out_right : R.anim.dialpad_slide_out_bottom);
        loadAnimation.setInterpolator(AnimUtils.EASE_OUT);
        loadAnimation.setAnimationListener(animationListener);
        loadAnimation.setDuration(z ? (long) this.dialpadSlideInDuration : 0);
        getView().startAnimation(loadAnimation);
        this.floatingActionButtonController.scaleOut();
    }

    public void slideUp(boolean z) {
        Assert.checkArgument(!this.isDialpadSlideUp);
        this.isDialpadSlideUp = true;
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), this.isLandscape ? this.isLayoutRtl ? R.anim.dialpad_slide_in_left : R.anim.dialpad_slide_in_right : R.anim.dialpad_slide_in_bottom);
        loadAnimation.setInterpolator(AnimUtils.EASE_IN);
        loadAnimation.setDuration(z ? (long) this.dialpadSlideInDuration : 0);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                DialpadFragment.this.floatingActionButtonController.scaleIn();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        getView().startAnimation(loadAnimation);
    }
}
