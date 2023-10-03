package com.android.p032ex.chips;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.util.Rfc822Tokenizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* renamed from: com.android.ex.chips.v */
public class C0704v {
    private int mAutocompleteDividerMarginStart;
    private final Context mContext;
    /* access modifiers changed from: private */
    public C0702t mDeleteListener;
    private final LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public C0703u mPermissionRequestDismissedListener;
    private C0634B mQuery;

    public C0704v(LayoutInflater layoutInflater, Context context) {
        this.mInflater = layoutInflater;
        this.mContext = context;
        this.mAutocompleteDividerMarginStart = context.getResources().getDimensionPixelOffset(C0637E.chip_wrapper_start_padding);
    }

    private static boolean isAllWhitespace(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void bindDrawableToDeleteView(StateListDrawable stateListDrawable, String str, ImageView imageView) {
        if (imageView != null) {
            if (stateListDrawable == null) {
                imageView.setVisibility(8);
                return;
            }
            Resources resources = this.mContext.getResources();
            imageView.setImageDrawable(stateListDrawable);
            imageView.setContentDescription(resources.getString(C0642J.dropdown_delete_button_desc, new Object[]{str}));
            if (this.mDeleteListener != null) {
                imageView.setOnClickListener(new C0698r(this, stateListDrawable));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void bindIconToView(boolean z, C0699ra raVar, ImageView imageView, DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        if (imageView != null) {
            if (z) {
                int ordinal = dropdownChipLayouter$AdapterType.ordinal();
                if (ordinal == 0) {
                    byte[] xd = raVar.mo5665xd();
                    if (xd == null || xd.length <= 0) {
                        imageView.setImageResource(getDefaultPhotoResId());
                    } else {
                        imageView.setImageBitmap(BitmapFactory.decodeByteArray(xd, 0, xd.length));
                    }
                } else if (ordinal == 1) {
                    Uri yd = raVar.mo5666yd();
                    if (yd != null) {
                        imageView.setImageURI(yd);
                    } else {
                        imageView.setImageResource(getDefaultPhotoResId());
                    }
                }
                imageView.setVisibility(0);
                return;
            }
            imageView.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void bindIndicatorToView(int i, String str, TextView textView) {
        Drawable drawable;
        if (textView == null) {
            return;
        }
        if (str == null && i == 0) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(str);
        textView.setVisibility(0);
        if (i != 0) {
            drawable = this.mContext.getDrawable(i).mutate();
            drawable.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
        } else {
            drawable = null;
        }
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    /* access modifiers changed from: protected */
    public void bindPermissionRequestDismissView(ImageView imageView) {
        if (imageView != null) {
            imageView.setOnClickListener(new C0700s(this));
        }
    }

    /* access modifiers changed from: protected */
    public void bindTextToView(CharSequence charSequence, TextView textView) {
        if (textView != null) {
            if (charSequence != null) {
                textView.setText(charSequence);
                textView.setVisibility(0);
                return;
            }
            textView.setVisibility(8);
        }
    }

    public View bindView(View view, ViewGroup viewGroup, C0699ra raVar, int i, DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType, String str) {
        return bindView(view, viewGroup, raVar, i, dropdownChipLayouter$AdapterType, str, (StateListDrawable) null);
    }

    /* access modifiers changed from: protected */
    public int getAlternateItemLayoutResId(DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        int ordinal = dropdownChipLayouter$AdapterType.ordinal();
        if (ordinal == 0) {
            return C0641I.chips_autocomplete_recipient_dropdown_item;
        }
        if (ordinal != 1) {
            return C0641I.chips_recipient_dropdown_item;
        }
        return C0641I.chips_recipient_dropdown_item;
    }

    /* access modifiers changed from: protected */
    public int getDefaultPhotoResId() {
        return C0638F.ic_contact_picture;
    }

    /* access modifiers changed from: protected */
    public int getDeleteResId() {
        return 16908295;
    }

    /* access modifiers changed from: protected */
    public int getDestinationResId() {
        return 16908308;
    }

    /* access modifiers changed from: protected */
    public CharSequence getDestinationType(C0699ra raVar) {
        return this.mQuery.getTypeLabel(this.mContext.getResources(), raVar.mo5662ud(), raVar.mo5660td()).toString().toUpperCase();
    }

    /* access modifiers changed from: protected */
    public int getDestinationTypeResId() {
        return 16908309;
    }

    /* access modifiers changed from: protected */
    public int getDisplayNameResId() {
        return 16908310;
    }

    /* access modifiers changed from: protected */
    public int getItemLayoutResId(DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        int ordinal = dropdownChipLayouter$AdapterType.ordinal();
        if (ordinal == 0) {
            return C0641I.chips_autocomplete_recipient_dropdown_item;
        }
        if (ordinal != 1) {
            return C0641I.chips_recipient_dropdown_item;
        }
        return C0641I.chips_recipient_dropdown_item;
    }

    /* access modifiers changed from: protected */
    public int getPermissionGroupResId() {
        return C0639G.chip_permission_wrapper;
    }

    /* access modifiers changed from: protected */
    public int getPermissionRequestDismissResId() {
        return 16908296;
    }

    /* access modifiers changed from: protected */
    public int getPersonGroupResId() {
        return C0639G.chip_person_wrapper;
    }

    /* access modifiers changed from: protected */
    public int getPhotoResId() {
        return 16908294;
    }

    /* access modifiers changed from: protected */
    public CharSequence[] getStyledResults(String str, C0699ra raVar) {
        return getStyledResults(str, raVar.getDisplayName(), raVar.getDestination());
    }

    public View newView(DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        return this.mInflater.inflate(getItemLayoutResId(dropdownChipLayouter$AdapterType), (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public View reuseOrInflateView(View view, ViewGroup viewGroup, DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType) {
        int itemLayoutResId = getItemLayoutResId(dropdownChipLayouter$AdapterType);
        int ordinal = dropdownChipLayouter$AdapterType.ordinal();
        if (!(ordinal == 0 || ordinal == 1 || ordinal != 2)) {
            itemLayoutResId = getAlternateItemLayoutResId(dropdownChipLayouter$AdapterType);
        }
        return view != null ? view : this.mInflater.inflate(itemLayoutResId, viewGroup, false);
    }

    public void setAutocompleteDividerMarginStart(int i) {
        this.mAutocompleteDividerMarginStart = i;
    }

    public void setDeleteListener(C0702t tVar) {
        this.mDeleteListener = tVar;
    }

    public void setPermissionRequestDismissedListener(C0703u uVar) {
        this.mPermissionRequestDismissedListener = uVar;
    }

    public void setQuery(C0634B b) {
        this.mQuery = b;
    }

    /* access modifiers changed from: protected */
    public void setViewVisibility(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    public View bindView(View view, ViewGroup viewGroup, C0699ra raVar, int i, DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType, String str, StateListDrawable stateListDrawable) {
        boolean z;
        CharSequence charSequence;
        CharSequence charSequence2;
        C0699ra raVar2 = raVar;
        DropdownChipLayouter$AdapterType dropdownChipLayouter$AdapterType2 = dropdownChipLayouter$AdapterType;
        CharSequence[] styledResults = getStyledResults(str, raVar2);
        CharSequence charSequence3 = styledResults[0];
        CharSequence charSequence4 = styledResults[1];
        CharSequence destinationType = getDestinationType(raVar2);
        View reuseOrInflateView = reuseOrInflateView(view, viewGroup, dropdownChipLayouter$AdapterType2);
        TextView textView = (TextView) reuseOrInflateView.findViewById(getDisplayNameResId());
        TextView textView2 = (TextView) reuseOrInflateView.findViewById(getDestinationResId());
        TextView textView3 = (TextView) reuseOrInflateView.findViewById(getDestinationTypeResId());
        ImageView imageView = (ImageView) reuseOrInflateView.findViewById(getPhotoResId());
        ImageView imageView2 = (ImageView) reuseOrInflateView.findViewById(getDeleteResId());
        View findViewById = reuseOrInflateView.findViewById(C0639G.chip_autocomplete_top_divider);
        View findViewById2 = reuseOrInflateView.findViewById(C0639G.chip_autocomplete_bottom_divider);
        View findViewById3 = reuseOrInflateView.findViewById(C0639G.chip_permission_bottom_divider);
        CharSequence charSequence5 = destinationType;
        TextView textView4 = (TextView) reuseOrInflateView.findViewById(C0639G.chip_indicator_text);
        View view2 = findViewById3;
        ViewGroup viewGroup2 = (ViewGroup) reuseOrInflateView.findViewById(getPermissionGroupResId());
        ImageView imageView3 = (ImageView) reuseOrInflateView.findViewById(getPermissionRequestDismissResId());
        View view3 = reuseOrInflateView;
        int ordinal = dropdownChipLayouter$AdapterType.ordinal();
        ViewGroup viewGroup3 = (ViewGroup) reuseOrInflateView.findViewById(getPersonGroupResId());
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    if (!C0705w.isPhoneNumber(raVar.getDestination())) {
                        charSequence4 = Rfc822Tokenizer.tokenize(raVar.getDestination())[0].getAddress();
                    }
                    charSequence = null;
                    z = true;
                }
            } else if (i != 0) {
                charSequence = charSequence5;
                charSequence3 = null;
                z = false;
            }
            charSequence = charSequence5;
            z = true;
        } else {
            if (TextUtils.isEmpty(charSequence3) || TextUtils.equals(charSequence3, charSequence4)) {
                charSequence2 = raVar.mo5667zd() ? null : charSequence4;
            } else {
                CharSequence charSequence6 = charSequence3;
                charSequence2 = charSequence4;
                charSequence4 = charSequence6;
            }
            if (!raVar.mo5667zd()) {
                charSequence4 = null;
                z = false;
            } else {
                z = true;
            }
            if (findViewById != null) {
                findViewById.setVisibility(i == 0 ? 0 : 8);
                int i2 = this.mAutocompleteDividerMarginStart;
                int i3 = Build.VERSION.SDK_INT;
                ((ViewGroup.MarginLayoutParams) findViewById.getLayoutParams()).setMarginStart(i2);
            }
            if (findViewById2 != null) {
                int i4 = this.mAutocompleteDividerMarginStart;
                int i5 = Build.VERSION.SDK_INT;
                ((ViewGroup.MarginLayoutParams) findViewById2.getLayoutParams()).setMarginStart(i4);
            }
            charSequence = charSequence5;
            CharSequence charSequence7 = charSequence2;
            charSequence3 = charSequence4;
            charSequence4 = charSequence7;
        }
        bindTextToView(charSequence3, textView);
        bindTextToView(charSequence4, textView2);
        bindTextToView(charSequence, textView3);
        bindIconToView(z, raVar2, imageView, dropdownChipLayouter$AdapterType2);
        bindDrawableToDeleteView(stateListDrawable, raVar.getDisplayName(), imageView2);
        bindIndicatorToView(raVar.mo5663vd(), raVar.mo5664wd(), textView4);
        bindPermissionRequestDismissView(imageView3);
        int entryType = raVar.getEntryType();
        if (entryType == 0) {
            setViewVisibility(viewGroup3, 0);
            setViewVisibility(viewGroup2, 8);
            setViewVisibility(view2, 8);
        } else {
            View view4 = view2;
            ViewGroup viewGroup4 = viewGroup2;
            ViewGroup viewGroup5 = viewGroup3;
            if (entryType == 1) {
                setViewVisibility(viewGroup5, 8);
                setViewVisibility(viewGroup4, 0);
                setViewVisibility(view4, 0);
            }
        }
        return view3;
    }

    /* access modifiers changed from: protected */
    public CharSequence[] getStyledResults(String str, String... strArr) {
        int indexOf;
        if (isAllWhitespace(str)) {
            return strArr;
        }
        CharSequence[] charSequenceArr = new CharSequence[strArr.length];
        boolean z = false;
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            if (str2 != null) {
                if (z || (indexOf = str2.toLowerCase().indexOf(str.toLowerCase())) == -1) {
                    charSequenceArr[i] = str2;
                } else {
                    SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(str2);
                    valueOf.setSpan(new ForegroundColorSpan(this.mContext.getResources().getColor(C0636D.chips_dropdown_text_highlighted)), indexOf, str.length() + indexOf, 33);
                    charSequenceArr[i] = valueOf;
                    z = true;
                }
            }
        }
        return charSequenceArr;
    }
}
