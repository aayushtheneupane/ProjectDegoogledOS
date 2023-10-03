package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.C0126R;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.ViewCompat;

public class ListMenuItemView extends LinearLayout implements C0213F, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: Df */
    private CheckBox f210Df;

    /* renamed from: Sg */
    private C0241t f211Sg;

    /* renamed from: Tg */
    private RadioButton f212Tg;

    /* renamed from: Ug */
    private TextView f213Ug;

    /* renamed from: Vg */
    private ImageView f214Vg;

    /* renamed from: Wg */
    private ImageView f215Wg;

    /* renamed from: Xg */
    private int f216Xg;

    /* renamed from: Yg */
    private Context f217Yg;

    /* renamed from: Zg */
    private boolean f218Zg;

    /* renamed from: _g */
    private Drawable f219_g;

    /* renamed from: ah */
    private boolean f220ah;
    private Drawable mBackground;
    private LinearLayout mContent;
    private boolean mForceShowIcon;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private TextView mTitleView;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0126R.attr.listMenuViewStyle);
    }

    /* renamed from: Um */
    private LayoutInflater m200Um() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getContext());
        }
        return this.mInflater;
    }

    /* renamed from: d */
    private void m201d(View view, int i) {
        LinearLayout linearLayout = this.mContent;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    /* renamed from: i */
    private void m202i(View view) {
        LinearLayout linearLayout = this.mContent;
        if (linearLayout != null) {
            linearLayout.addView(view, -1);
        } else {
            addView(view, -1);
        }
    }

    /* renamed from: a */
    public void mo1367a(C0241t tVar, int i) {
        this.f211Sg = tVar;
        int i2 = 0;
        setVisibility(tVar.isVisible() ? 0 : 8);
        setTitle(tVar.mo1643a((C0213F) this));
        setCheckable(tVar.isCheckable());
        setShortcut(tVar.shouldShowShortcut(), tVar.getShortcut());
        setIcon(tVar.getIcon());
        setEnabled(tVar.isEnabled());
        boolean hasSubMenu = tVar.hasSubMenu();
        ImageView imageView = this.f214Vg;
        if (imageView != null) {
            if (!hasSubMenu) {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }
        setContentDescription(tVar.getContentDescription());
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f215Wg;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f215Wg.getLayoutParams();
            rect.top = this.f215Wg.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
        }
    }

    public C0241t getItemData() {
        return this.f211Sg;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        ViewCompat.setBackground(this, this.mBackground);
        this.mTitleView = (TextView) findViewById(C0126R.C0128id.title);
        int i = this.f216Xg;
        if (i != -1) {
            this.mTitleView.setTextAppearance(this.f217Yg, i);
        }
        this.f213Ug = (TextView) findViewById(C0126R.C0128id.shortcut);
        this.f214Vg = (ImageView) findViewById(C0126R.C0128id.submenuarrow);
        ImageView imageView = this.f214Vg;
        if (imageView != null) {
            imageView.setImageDrawable(this.f219_g);
        }
        this.f215Wg = (ImageView) findViewById(C0126R.C0128id.group_divider);
        this.mContent = (LinearLayout) findViewById(C0126R.C0128id.content);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mIconView != null && this.f218Zg) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (z || this.f212Tg != null || this.f210Df != null) {
            if (this.f211Sg.isExclusiveCheckable()) {
                if (this.f212Tg == null) {
                    this.f212Tg = (RadioButton) m200Um().inflate(C0126R.layout.abc_list_menu_item_radio, this, false);
                    m202i(this.f212Tg);
                }
                compoundButton2 = this.f212Tg;
                compoundButton = this.f210Df;
            } else {
                if (this.f210Df == null) {
                    this.f210Df = (CheckBox) m200Um().inflate(C0126R.layout.abc_list_menu_item_checkbox, this, false);
                    m202i(this.f210Df);
                }
                compoundButton2 = this.f210Df;
                compoundButton = this.f212Tg;
            }
            if (z) {
                compoundButton2.setChecked(this.f211Sg.isChecked());
                if (compoundButton2.getVisibility() != 0) {
                    compoundButton2.setVisibility(0);
                }
                if (compoundButton != null && compoundButton.getVisibility() != 8) {
                    compoundButton.setVisibility(8);
                    return;
                }
                return;
            }
            CheckBox checkBox = this.f210Df;
            if (checkBox != null) {
                checkBox.setVisibility(8);
            }
            RadioButton radioButton = this.f212Tg;
            if (radioButton != null) {
                radioButton.setVisibility(8);
            }
        }
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
        this.f218Zg = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.f215Wg;
        if (imageView != null) {
            imageView.setVisibility((this.f220ah || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f211Sg.mMenu.getOptionalIconsVisible() || this.mForceShowIcon;
        if (!z && !this.f218Zg) {
            return;
        }
        if (this.mIconView != null || drawable != null || this.f218Zg) {
            if (this.mIconView == null) {
                this.mIconView = (ImageView) m200Um().inflate(C0126R.layout.abc_list_menu_item_icon, this, false);
                m201d(this.mIconView, 0);
            }
            if (drawable != null || this.f218Zg) {
                ImageView imageView = this.mIconView;
                if (!z) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.mIconView.getVisibility() != 0) {
                    this.mIconView.setVisibility(0);
                    return;
                }
                return;
            }
            this.mIconView.setVisibility(8);
        }
    }

    public void setShortcut(boolean z, char c) {
        int i = (!z || !this.f211Sg.shouldShowShortcut()) ? 8 : 0;
        if (i == 0) {
            this.f213Ug.setText(this.f211Sg.getShortcutLabel());
        }
        if (this.f213Ug.getVisibility() != i) {
            this.f213Ug.setVisibility(i);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.mTitleView.setText(charSequence);
            if (this.mTitleView.getVisibility() != 0) {
                this.mTitleView.setVisibility(0);
            }
        } else if (this.mTitleView.getVisibility() != 8) {
            this.mTitleView.setVisibility(8);
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, C0126R.styleable.MenuView, i, 0);
        this.mBackground = obtainStyledAttributes.getDrawable(C0126R.styleable.MenuView_android_itemBackground);
        this.f216Xg = obtainStyledAttributes.getResourceId(C0126R.styleable.MenuView_android_itemTextAppearance, -1);
        this.f218Zg = obtainStyledAttributes.getBoolean(C0126R.styleable.MenuView_preserveIconSpacing, false);
        this.f217Yg = context;
        this.f219_g = obtainStyledAttributes.getDrawable(C0126R.styleable.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes((AttributeSet) null, new int[]{16843049}, C0126R.attr.dropDownListViewStyle, 0);
        this.f220ah = obtainStyledAttributes2.hasValue(0);
        obtainStyledAttributes.recycle();
        obtainStyledAttributes2.recycle();
    }
}
