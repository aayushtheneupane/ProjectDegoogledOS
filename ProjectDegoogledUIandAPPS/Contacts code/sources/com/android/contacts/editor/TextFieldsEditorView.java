package com.android.contacts.editor;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.TtsSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.contacts.ContactsUtils;
import com.android.contacts.R;
import com.android.contacts.compat.PhoneNumberUtilsCompat;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contacts.util.PhoneNumberFormatter;
import java.util.List;

public class TextFieldsEditorView extends LabeledEditorView {
    protected String mCollapseButtonDescription;
    protected String mCollapsedAnnouncement;
    protected String mExpandButtonDescription;
    protected String mExpandedAnnouncement;
    protected ImageView mExpansionView;
    protected View mExpansionViewContainer;
    private EditText[] mFieldEditTexts = null;
    /* access modifiers changed from: private */
    public ViewGroup mFields = null;
    /* access modifiers changed from: private */
    public String mFixedDisplayName = "";
    /* access modifiers changed from: private */
    public String mFixedPhonetic = "";
    private boolean mHasShortAndLongForms;
    /* access modifiers changed from: private */
    public boolean mHideOptional = true;
    private int mHintTextColorUnfocused;
    private int mMinFieldHeight;
    /* access modifiers changed from: private */
    public int mPreviousViewHeight;
    private View.OnFocusChangeListener mTextFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean z) {
            if (TextFieldsEditorView.this.getEditorListener() != null) {
                TextFieldsEditorView.this.getEditorListener().onRequest(6);
            }
            TextFieldsEditorView.this.rebuildLabel();
            if (z) {
                boolean unused = TextFieldsEditorView.this.needInputInitialize = true;
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean needInputInitialize;

    public TextFieldsEditorView(Context context) {
        super(context);
    }

    public TextFieldsEditorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TextFieldsEditorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        setDrawingCacheEnabled(true);
        setAlwaysDrawnWithCacheEnabled(true);
        this.mMinFieldHeight = getContext().getResources().getDimensionPixelSize(R.dimen.editor_min_line_item_height);
        this.mFields = (ViewGroup) findViewById(R.id.editors);
        this.mHintTextColorUnfocused = getResources().getColor(R.color.editor_disabled_text_color);
        this.mExpansionView = (ImageView) findViewById(R.id.expansion_view);
        this.mCollapseButtonDescription = getResources().getString(R.string.collapse_fields_description);
        this.mCollapsedAnnouncement = getResources().getString(R.string.announce_collapsed_fields);
        this.mExpandButtonDescription = getResources().getString(R.string.expand_fields_description);
        this.mExpandedAnnouncement = getResources().getString(R.string.announce_expanded_fields);
        this.mExpansionViewContainer = findViewById(R.id.expansion_view_container);
        View view = this.mExpansionViewContainer;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int i;
                    TextFieldsEditorView textFieldsEditorView = TextFieldsEditorView.this;
                    int unused = textFieldsEditorView.mPreviousViewHeight = textFieldsEditorView.mFields.getHeight();
                    View findFocus = TextFieldsEditorView.this.findFocus();
                    if (findFocus == null) {
                        i = -1;
                    } else {
                        i = findFocus.getId();
                    }
                    TextFieldsEditorView textFieldsEditorView2 = TextFieldsEditorView.this;
                    boolean unused2 = textFieldsEditorView2.mHideOptional = !textFieldsEditorView2.mHideOptional;
                    TextFieldsEditorView.this.onOptionalFieldVisibilityChange();
                    TextFieldsEditorView.this.rebuildValues();
                    View findViewById = TextFieldsEditorView.this.findViewById(i);
                    if (findViewById == null || findViewById.getVisibility() == 8) {
                        findViewById = TextFieldsEditorView.this;
                    }
                    findViewById.requestFocus();
                    EditorAnimator.getInstance().slideAndFadeIn(TextFieldsEditorView.this.mFields, TextFieldsEditorView.this.mPreviousViewHeight);
                    TextFieldsEditorView textFieldsEditorView3 = TextFieldsEditorView.this;
                    textFieldsEditorView3.announceForAccessibility(textFieldsEditorView3.mHideOptional ? TextFieldsEditorView.this.mCollapsedAnnouncement : TextFieldsEditorView.this.mExpandedAnnouncement);
                }
            });
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        boolean z2 = true;
        if (this.mFieldEditTexts != null) {
            int i = 0;
            while (true) {
                EditText[] editTextArr = this.mFieldEditTexts;
                if (i >= editTextArr.length) {
                    break;
                }
                editTextArr[i].setEnabled(!isReadOnly() && z);
                i++;
            }
        }
        ImageView imageView = this.mExpansionView;
        if (imageView != null) {
            if (isReadOnly() || !z) {
                z2 = false;
            }
            imageView.setEnabled(z2);
        }
    }

    private void setupExpansionView(boolean z, boolean z2) {
        String str;
        this.mExpansionView.setImageDrawable(getContext().getDrawable(z2 ? R.drawable.quantum_ic_expand_more_vd_theme_24 : R.drawable.quantum_ic_expand_less_vd_theme_24));
        ImageView imageView = this.mExpansionView;
        if (z2) {
            str = this.mExpandButtonDescription;
        } else {
            str = this.mCollapseButtonDescription;
        }
        imageView.setContentDescription(str);
        this.mExpansionViewContainer.setVisibility(z ? 0 : 4);
    }

    /* access modifiers changed from: protected */
    public void requestFocusForFirstEditField() {
        EditText[] editTextArr = this.mFieldEditTexts;
        if (editTextArr != null && editTextArr.length != 0) {
            int length = editTextArr.length;
            boolean z = false;
            EditText editText = null;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                EditText editText2 = editTextArr[i];
                if (editText == null && editText2.getVisibility() == 0) {
                    editText = editText2;
                }
                if (editText2.hasFocus()) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z && editText != null) {
                editText.requestFocus();
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isUnFixed(Editable editable) {
        Object[] spans = editable.getSpans(0, editable.length(), Object.class);
        if (spans == null) {
            return false;
        }
        boolean z = false;
        for (Object spanFlags : spans) {
            if ((editable.getSpanFlags(spanFlags) & 256) == 256) {
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public String getNameField(String str) {
        EditText editText;
        if ("data3".equals(str)) {
            editText = (EditText) this.mFields.getChildAt(1);
        } else if ("data2".equals(str)) {
            editText = (EditText) this.mFields.getChildAt(3);
        } else {
            editText = "data5".equals(str) ? (EditText) this.mFields.getChildAt(2) : null;
        }
        return editText != null ? editText.getText().toString() : "";
    }

    public void setValues(DataKind dataKind, ValuesDelta valuesDelta, RawContactDelta rawContactDelta, boolean z, ViewIdGenerator viewIdGenerator) {
        DataKind dataKind2 = dataKind;
        ValuesDelta valuesDelta2 = valuesDelta;
        super.setValues(dataKind, valuesDelta, rawContactDelta, z, viewIdGenerator);
        EditText[] editTextArr = this.mFieldEditTexts;
        boolean z2 = false;
        if (editTextArr != null) {
            for (EditText removeView : editTextArr) {
                this.mFields.removeView(removeView);
            }
        }
        List<AccountType.EditField> list = dataKind2.fieldList;
        int size = list == null ? 0 : list.size();
        this.mFieldEditTexts = new EditText[size];
        int i = 0;
        boolean z3 = false;
        while (true) {
            boolean z4 = true;
            if (i >= size) {
                break;
            }
            AccountType.EditField editField = dataKind2.fieldList.get(i);
            EditText editText = new EditText(getContext());
            editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            editText.setTextSize(0, getResources().getDimension(R.dimen.editor_form_text_size));
            editText.setHintTextColor(this.mHintTextColorUnfocused);
            this.mFieldEditTexts[i] = editText;
            editText.setId(viewIdGenerator.getId(rawContactDelta, dataKind2, valuesDelta2, i));
            int i2 = editField.titleRes;
            if (i2 > 0) {
                editText.setHint(i2);
            }
            int i3 = editField.inputType;
            editText.setInputType(i3);
            if (i3 == 3) {
                PhoneNumberFormatter.setPhoneNumberFormattingTextWatcher(getContext(), editText, rawContactDelta.isContactInsert());
                editText.setTextDirection(3);
            }
            editText.setTextAlignment(5);
            int i4 = editField.minLines;
            if (i4 > 1) {
                editText.setMinLines(i4);
            } else {
                editText.setMinHeight(this.mMinFieldHeight);
            }
            editText.setImeOptions(33554437);
            final String str = editField.column;
            String asString = valuesDelta2.getAsString(str);
            if ("vnd.android.cursor.item/phone_v2".equals(dataKind2.mimeType)) {
                editText.setText(PhoneNumberUtilsCompat.createTtsSpannable(asString));
            } else {
                editText.setText(asString);
            }
            setDeleteButtonVisible(!TextUtils.isEmpty(asString));
            editText.addTextChangedListener(new TextWatcher() {
                private int mStart = 0;

                public void afterTextChanged(Editable editable) {
                    TextFieldsEditorView.this.onFieldChanged(str, editable.toString());
                    if (DataKind.PSEUDO_MIME_TYPE_NAME.equals(TextFieldsEditorView.this.getKind().mimeType)) {
                        String obj = editable.toString();
                        int length = obj.length() - TextFieldsEditorView.this.mFixedDisplayName.length();
                        if (TextFieldsEditorView.this.isUnFixed(editable) || length == 0) {
                            TextFieldsEditorView.this.updatePhonetic(str, TextFieldsEditorView.this.mFixedPhonetic + obj.substring(this.mStart, obj.length()));
                            return;
                        }
                        TextFieldsEditorView textFieldsEditorView = TextFieldsEditorView.this;
                        String unused = textFieldsEditorView.mFixedPhonetic = textFieldsEditorView.getPhonetic(str);
                        String unused2 = TextFieldsEditorView.this.mFixedDisplayName = obj;
                    }
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (DataKind.PSEUDO_MIME_TYPE_NAME.equals(TextFieldsEditorView.this.getKind().mimeType) && TextFieldsEditorView.this.needInputInitialize) {
                        TextFieldsEditorView textFieldsEditorView = TextFieldsEditorView.this;
                        String unused = textFieldsEditorView.mFixedPhonetic = textFieldsEditorView.getPhonetic(str);
                        TextFieldsEditorView textFieldsEditorView2 = TextFieldsEditorView.this;
                        String unused2 = textFieldsEditorView2.mFixedDisplayName = textFieldsEditorView2.getNameField(str);
                        boolean unused3 = TextFieldsEditorView.this.needInputInitialize = false;
                    }
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    this.mStart = i;
                    if ("vnd.android.cursor.item/phone_v2".equals(TextFieldsEditorView.this.getKind().mimeType) && (charSequence instanceof Spannable)) {
                        Spannable spannable = (Spannable) charSequence;
                        TtsSpan[] ttsSpanArr = (TtsSpan[]) spannable.getSpans(0, charSequence.length(), TtsSpan.class);
                        for (TtsSpan removeSpan : ttsSpanArr) {
                            spannable.removeSpan(removeSpan);
                        }
                        PhoneNumberUtilsCompat.addTtsSpan(spannable, 0, charSequence.length());
                    }
                }
            });
            editText.setEnabled(isEnabled() && !z);
            editText.setOnFocusChangeListener(this.mTextFocusChangeListener);
            int i5 = 8;
            if (editField.shortForm) {
                this.mHasShortAndLongForms = true;
                if (this.mHideOptional) {
                    i5 = 0;
                }
                editText.setVisibility(i5);
            } else if (editField.longForm) {
                this.mHasShortAndLongForms = true;
                if (!this.mHideOptional) {
                    i5 = 0;
                }
                editText.setVisibility(i5);
            } else {
                boolean z5 = !ContactsUtils.isGraphic(asString) && editField.optional;
                if (!(this.mHideOptional && z5)) {
                    i5 = 0;
                }
                editText.setVisibility(i5);
                if (!z3 && !z5) {
                    z4 = false;
                }
                z3 = z4;
                this.mFields.addView(editText);
                i++;
            }
            z3 = true;
            this.mFields.addView(editText);
            i++;
        }
        if (this.mExpansionView != null) {
            setupExpansionView(z3, this.mHideOptional);
            ImageView imageView = this.mExpansionView;
            if (!z && isEnabled()) {
                z2 = true;
            }
            imageView.setEnabled(z2);
        }
        updateEmptiness();
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.mFields.getChildCount(); i++) {
            if (!TextUtils.isEmpty(((EditText) this.mFields.getChildAt(i)).getText())) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mHideOptional = this.mHideOptional;
        EditText[] editTextArr = this.mFieldEditTexts;
        int length = editTextArr == null ? 0 : editTextArr.length;
        savedState.mVisibilities = new int[length];
        for (int i = 0; i < length; i++) {
            savedState.mVisibilities[i] = this.mFieldEditTexts[i].getVisibility();
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mHideOptional = savedState.mHideOptional;
        EditText[] editTextArr = this.mFieldEditTexts;
        int length = editTextArr == null ? 0 : editTextArr.length;
        int[] iArr = savedState.mVisibilities;
        int min = Math.min(length, iArr == null ? 0 : iArr.length);
        for (int i = 0; i < min; i++) {
            this.mFieldEditTexts[i].setVisibility(savedState.mVisibilities[i]);
        }
        rebuildValues();
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public boolean mHideOptional;
        public int[] mVisibilities;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mVisibilities = new int[parcel.readInt()];
            parcel.readIntArray(this.mVisibilities);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mVisibilities.length);
            parcel.writeIntArray(this.mVisibilities);
        }
    }

    public void clearAllFields() {
        EditText[] editTextArr = this.mFieldEditTexts;
        if (editTextArr != null) {
            for (EditText text : editTextArr) {
                text.setText("");
            }
        }
    }
}
