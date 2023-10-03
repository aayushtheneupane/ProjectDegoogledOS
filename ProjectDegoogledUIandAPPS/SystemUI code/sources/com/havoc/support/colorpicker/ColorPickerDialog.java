package com.havoc.support.colorpicker;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;
import com.havoc.support.colorpicker.ColorPickerView;
import com.havoc.support.util.VibrationUtils;

public class ColorPickerDialog extends AlertDialog implements ColorPickerView.OnColorChangedListener, View.OnClickListener, View.OnKeyListener {
    private ColorPickerView mColorPicker;
    private final Context mContext;
    private EditText mHex;
    private OnColorChangedListener mListener;
    private ColorPickerPanelView mNewColor;
    private ColorPickerPanelView mOldColor;

    public interface OnColorChangedListener {
        void onColorChanged(int i);
    }

    public ColorPickerDialog(Context context, int i) {
        super(context);
        init(i);
        this.mContext = context;
    }

    private void init(int i) {
        if (getWindow() != null) {
            getWindow().setFormat(1);
            requestWindowFeature(1);
            setUp(i);
        }
    }

    private void setColorFromHex() {
        try {
            this.mColorPicker.setColor(ColorPickerPreference.convertToColorInt(this.mHex.getText().toString()), true);
        } catch (Exception unused) {
        }
    }

    private void setUp(int i) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R$layout.preference_color_picker, (ViewGroup) null);
        this.mColorPicker = (ColorPickerView) inflate.findViewById(R$id.color_picker_view);
        this.mOldColor = (ColorPickerPanelView) inflate.findViewById(R$id.old_color_panel);
        this.mNewColor = (ColorPickerPanelView) inflate.findViewById(R$id.new_color_panel);
        this.mHex = (EditText) inflate.findViewById(R$id.hex);
        ((LinearLayout) this.mOldColor.getParent()).setPadding(Math.round(this.mColorPicker.getDrawingOffset()), 0, Math.round(this.mColorPicker.getDrawingOffset()), 0);
        this.mOldColor.setOnClickListener(this);
        this.mNewColor.setOnClickListener(this);
        this.mColorPicker.setOnColorChangedListener(this);
        this.mOldColor.setColor(i);
        this.mColorPicker.setColor(i, true);
        EditText editText = this.mHex;
        if (editText != null) {
            editText.setText(ColorPickerPreference.convertToRGB(i));
            this.mHex.setOnKeyListener(this);
        }
        setView(inflate);
    }

    public void onColorChanged(int i) {
        this.mNewColor.setColor(i);
        try {
            if (this.mHex != null) {
                this.mHex.setText(ColorPickerPreference.convertToRGB(i));
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void setAlphaSliderVisible(boolean z) {
        this.mColorPicker.setAlphaSliderVisible(z);
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.mListener = onColorChangedListener;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 66) {
            return false;
        }
        setColorFromHex();
        return true;
    }

    public void onClick(View view) {
        OnColorChangedListener onColorChangedListener;
        if (view.getId() == R$id.new_color_panel && (onColorChangedListener = this.mListener) != null) {
            onColorChangedListener.onColorChanged(this.mNewColor.getColor());
        }
        VibrationUtils.doHapticFeedback(this.mContext, 0);
        dismiss();
    }

    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putInt("old_color", this.mOldColor.getColor());
        onSaveInstanceState.putInt("new_color", this.mNewColor.getColor());
        dismiss();
        return onSaveInstanceState;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mOldColor.setColor(bundle.getInt("old_color"));
        this.mColorPicker.setColor(bundle.getInt("new_color"), true);
    }
}
