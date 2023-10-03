package com.android.contacts.editor;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.android.contacts.R;
import com.android.contacts.datepicker.DatePicker;
import com.android.contacts.datepicker.DatePickerDialog;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contacts.util.DateUtils;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventFieldEditorView extends LabeledEditorView {
    private Button mDateView;
    private int mHintTextColor;
    private String mNoDateString;
    private int mPrimaryTextColor;

    public EventFieldEditorView(Context context) {
        super(context);
    }

    public EventFieldEditorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EventFieldEditorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        Resources resources = getContext().getResources();
        this.mPrimaryTextColor = resources.getColor(R.color.primary_text_color);
        this.mHintTextColor = resources.getColor(R.color.editor_disabled_text_color);
        this.mNoDateString = getContext().getString(R.string.event_edit_field_hint_text);
        this.mDateView = (Button) findViewById(R.id.date_view);
        this.mDateView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EventFieldEditorView.this.showDialog(R.id.dialog_event_date_picker);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void requestFocusForFirstEditField() {
        this.mDateView.requestFocus();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mDateView.setEnabled(!isReadOnly() && z);
    }

    public void setValues(DataKind dataKind, ValuesDelta valuesDelta, RawContactDelta rawContactDelta, boolean z, ViewIdGenerator viewIdGenerator) {
        boolean z2 = true;
        if (dataKind.fieldList.size() == 1) {
            super.setValues(dataKind, valuesDelta, rawContactDelta, z, viewIdGenerator);
            Button button = this.mDateView;
            if (!isEnabled() || z) {
                z2 = false;
            }
            button.setEnabled(z2);
            rebuildDateView();
            updateEmptiness();
            return;
        }
        throw new IllegalStateException("kind must have 1 field");
    }

    /* access modifiers changed from: private */
    public void rebuildDateView() {
        String formatDate = DateUtils.formatDate(getContext(), getEntry().getAsString(getKind().fieldList.get(0).column), false);
        if (TextUtils.isEmpty(formatDate)) {
            this.mDateView.setText(this.mNoDateString);
            this.mDateView.setTextColor(this.mHintTextColor);
            setDeleteButtonVisible(false);
            return;
        }
        this.mDateView.setText(formatDate);
        this.mDateView.setTextColor(this.mPrimaryTextColor);
        setDeleteButtonVisible(true);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(getEntry().getAsString(getKind().fieldList.get(0).column));
    }

    public Dialog createDialog(Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("bundle must not be null");
        } else if (bundle.getInt("dialog_id") == R.id.dialog_event_date_picker) {
            return createDatePickerDialog();
        } else {
            return super.createDialog(bundle);
        }
    }

    /* access modifiers changed from: protected */
    public AccountType.EventEditType getType() {
        return (AccountType.EventEditType) super.getType();
    }

    /* access modifiers changed from: protected */
    public void onLabelRebuilt() {
        Date date;
        String str = getKind().fieldList.get(0).column;
        String asString = getEntry().getAsString(str);
        DataKind kind = getKind();
        Calendar instance = Calendar.getInstance(DateUtils.UTC_TIMEZONE, Locale.US);
        boolean z = true;
        int i = instance.get(1);
        if (getType() == null || !getType().isYearOptional()) {
            z = false;
        }
        if (!z && !TextUtils.isEmpty(asString)) {
            ParsePosition parsePosition = new ParsePosition(0);
            SimpleDateFormat simpleDateFormat = kind.dateFormatWithoutYear;
            String str2 = null;
            if (simpleDateFormat == null) {
                date = null;
            } else {
                date = simpleDateFormat.parse(asString, parsePosition);
            }
            if (date != null) {
                instance.setTime(date);
                instance.set(i, instance.get(2), instance.get(5), 8, 0, 0);
                SimpleDateFormat simpleDateFormat2 = kind.dateFormatWithYear;
                if (simpleDateFormat2 != null) {
                    str2 = simpleDateFormat2.format(instance.getTime());
                }
                if (str2 != null) {
                    onFieldChanged(str, str2);
                    rebuildDateView();
                }
            }
        }
    }

    private Dialog createDatePickerDialog() {
        int i;
        int i2;
        final String str = getKind().fieldList.get(0).column;
        String asString = getEntry().getAsString(str);
        final DataKind kind = getKind();
        Calendar instance = Calendar.getInstance(DateUtils.UTC_TIMEZONE, Locale.US);
        int i3 = instance.get(1);
        final boolean isYearOptional = getType().isYearOptional();
        if (TextUtils.isEmpty(asString)) {
            i2 = instance.get(2);
            i = instance.get(5);
        } else {
            Calendar parseDate = DateUtils.parseDate(asString, false);
            if (parseDate == null) {
                return null;
            }
            if (DateUtils.isYearSet(parseDate)) {
                i3 = parseDate.get(1);
            } else if (isYearOptional) {
                i3 = DatePickerDialog.NO_YEAR;
            }
            int i4 = parseDate.get(2);
            i = parseDate.get(5);
            i2 = i4;
        }
        int i5 = i3;
        return new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                if (i != 0 || isYearOptional) {
                    Calendar instance = Calendar.getInstance(DateUtils.UTC_TIMEZONE, Locale.US);
                    instance.clear();
                    instance.set(i == DatePickerDialog.NO_YEAR ? 2000 : i, i2, i3, 8, 0, 0);
                    String str = null;
                    if (i == 0) {
                        SimpleDateFormat simpleDateFormat = kind.dateFormatWithoutYear;
                        if (simpleDateFormat != null) {
                            str = simpleDateFormat.format(instance.getTime());
                        }
                    } else {
                        SimpleDateFormat simpleDateFormat2 = kind.dateFormatWithYear;
                        if (simpleDateFormat2 != null) {
                            str = simpleDateFormat2.format(instance.getTime());
                        }
                    }
                    if (str != null) {
                        EventFieldEditorView.this.onFieldChanged(str, str);
                        EventFieldEditorView.this.rebuildDateView();
                        return;
                    }
                    return;
                }
                throw new IllegalStateException();
            }
        }, i5, i2, i, isYearOptional);
    }

    public void clearAllFields() {
        this.mDateView.setText(this.mNoDateString);
        this.mDateView.setTextColor(this.mHintTextColor);
        onFieldChanged(getKind().fieldList.get(0).column, "");
    }

    public void restoreBirthday() {
        saveValue(getKind().typeColumn, Integer.toString(3));
        rebuildValues();
    }

    public boolean isBirthdayType() {
        AccountType.EventEditType type = getType();
        if (type.rawValue == 3 && !type.secondary && type.specificMax == 1 && type.customColumn == null && type.isYearOptional()) {
            return true;
        }
        return false;
    }
}
