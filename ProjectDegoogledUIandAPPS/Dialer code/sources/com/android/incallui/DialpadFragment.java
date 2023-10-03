package com.android.incallui;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.dialpadview.DialpadKeyButton;
import com.android.dialer.dialpadview.DialpadView;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.incallui.DialpadPresenter;
import com.android.incallui.baseui.BaseFragment;
import com.android.incallui.baseui.C0701Ui;
import com.android.incallui.baseui.Presenter;
import java.util.Map;

public class DialpadFragment extends BaseFragment<DialpadPresenter, DialpadPresenter.DialpadUi> implements DialpadPresenter.DialpadUi, View.OnKeyListener, View.OnClickListener, DialpadKeyButton.OnPressedListener {
    private static final Map<Integer, Character> displayMap = new ArrayMap();
    private final int[] buttonIds = {R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.star, R.id.pound};
    private int currentTextColor;
    private DialpadView dialpadView;
    private EditText dtmfDialerField;
    private DtmfKeyListener dtmfKeyListener;
    private View endCallSpace;
    private boolean shouldShowEndCallSpace = true;

    public static class DialpadSlidingLinearLayout extends LinearLayout {
        public DialpadSlidingLinearLayout(Context context) {
            super(context);
        }

        public DialpadSlidingLinearLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public DialpadSlidingLinearLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }
    }

    static {
        displayMap.put(Integer.valueOf(R.id.one), '1');
        displayMap.put(Integer.valueOf(R.id.two), '2');
        displayMap.put(Integer.valueOf(R.id.three), '3');
        displayMap.put(Integer.valueOf(R.id.four), '4');
        displayMap.put(Integer.valueOf(R.id.five), '5');
        displayMap.put(Integer.valueOf(R.id.six), '6');
        displayMap.put(Integer.valueOf(R.id.seven), '7');
        displayMap.put(Integer.valueOf(R.id.eight), '8');
        displayMap.put(Integer.valueOf(R.id.nine), '9');
        displayMap.put(Integer.valueOf(R.id.zero), '0');
        displayMap.put(Integer.valueOf(R.id.pound), '#');
        displayMap.put(Integer.valueOf(R.id.star), '*');
    }

    public void appendDigitsToField(char c) {
        EditText editText = this.dtmfDialerField;
        if (editText != null) {
            editText.getText().append(c);
        }
    }

    public Presenter createPresenter() {
        return new DialpadPresenter();
    }

    public String getDtmfText() {
        return this.dtmfDialerField.getText().toString();
    }

    public C0701Ui getUi() {
        return this;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.dialpad_back) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.IN_CALL_DIALPAD_CLOSE_BUTTON_PRESSED);
            getActivity().onBackPressed();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.cloneInContext(new ContextThemeWrapper(getActivity(), 2131886325)).inflate(R.layout.incall_dialpad_fragment, viewGroup, false);
        this.dialpadView = (DialpadView) inflate.findViewById(R.id.dialpad_view);
        this.dialpadView.setCanDigitsBeEdited(false);
        this.dialpadView.setBackgroundResource(R.color.incall_dialpad_background);
        this.dtmfDialerField = (EditText) inflate.findViewById(R.id.digits);
        if (this.dtmfDialerField != null) {
            LogUtil.m9i("DialpadFragment.onCreateView", "creating dtmfKeyListener", new Object[0]);
            this.dtmfKeyListener = new DtmfKeyListener((DialpadPresenter) getPresenter());
            this.dtmfDialerField.setKeyListener(this.dtmfKeyListener);
            this.dtmfDialerField.setLongClickable(false);
            this.dtmfDialerField.setElegantTextHeight(false);
            int i = 0;
            while (true) {
                int[] iArr = this.buttonIds;
                if (i >= iArr.length) {
                    break;
                }
                DialpadKeyButton dialpadKeyButton = (DialpadKeyButton) this.dialpadView.findViewById(iArr[i]);
                dialpadKeyButton.setOnKeyListener(this);
                dialpadKeyButton.setOnClickListener(this);
                dialpadKeyButton.setOnPressedListener(this);
                i++;
            }
        }
        View findViewById = this.dialpadView.findViewById(R.id.dialpad_back);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(this);
        this.endCallSpace = this.dialpadView.findViewById(R.id.end_call_space);
        return inflate;
    }

    public void onDestroyView() {
        this.dtmfKeyListener = null;
        super.onDestroyView();
    }

    /* access modifiers changed from: package-private */
    public boolean onDialerKeyDown(KeyEvent keyEvent) {
        Bindings.m34d(this, "Notifying dtmf key down.");
        DtmfKeyListener dtmfKeyListener2 = this.dtmfKeyListener;
        if (dtmfKeyListener2 != null) {
            return dtmfKeyListener2.onKeyDown(keyEvent);
        }
        return false;
    }

    public boolean onDialerKeyUp(KeyEvent keyEvent) {
        Bindings.m34d(this, "Notifying dtmf key up.");
        DtmfKeyListener dtmfKeyListener2 = this.dtmfKeyListener;
        if (dtmfKeyListener2 != null) {
            return dtmfKeyListener2.onKeyUp(keyEvent);
        }
        return false;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Bindings.m34d(this, "onKey:  keyCode " + i + ", view " + view);
        if (i != 23 && i != 66) {
            return false;
        }
        int id = view.getId();
        if (!displayMap.containsKey(Integer.valueOf(id))) {
            return false;
        }
        int action = keyEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                return false;
            }
            ((DialpadPresenter) getPresenter()).stopDtmf();
            return false;
        } else if (keyEvent.getRepeatCount() != 0) {
            return false;
        } else {
            ((DialpadPresenter) getPresenter()).processDtmf(displayMap.get(Integer.valueOf(id)).charValue());
            return false;
        }
    }

    public void onPressed(View view, boolean z) {
        if (z && displayMap.containsKey(Integer.valueOf(view.getId()))) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.IN_CALL_DIALPAD_NUMBER_BUTTON_PRESSED);
            Bindings.m34d(this, "onPressed: " + z + " " + displayMap.get(Integer.valueOf(view.getId())));
            ((DialpadPresenter) getPresenter()).processDtmf(displayMap.get(Integer.valueOf(view.getId())).charValue());
        }
        if (!z) {
            Bindings.m34d(this, "onPressed: " + z);
            ((DialpadPresenter) getPresenter()).stopDtmf();
        }
    }

    public void onResume() {
        super.onResume();
        int primaryColor = InCallPresenter.getInstance().getThemeColorManager().getPrimaryColor();
        int i = 0;
        if (this.currentTextColor != primaryColor) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.buttonIds;
                if (i2 >= iArr.length) {
                    break;
                }
                ((TextView) ((DialpadKeyButton) this.dialpadView.findViewById(iArr[i2])).findViewById(R.id.dialpad_key_number)).setTextColor(primaryColor);
                i2++;
            }
            this.currentTextColor = primaryColor;
        }
        View view = this.endCallSpace;
        if (!this.shouldShowEndCallSpace) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void setDtmfText(String str) {
        this.dtmfDialerField.setText(PhoneNumberUtils.createTtsSpannable(str));
    }

    public void setShouldShowEndCallSpace(boolean z) {
        this.shouldShowEndCallSpace = z;
    }
}
