package android.support.p002v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: android.support.v7.view.menu.ListMenuItemView */
/* compiled from: PG */
public class ListMenuItemView extends LinearLayout implements AbsListView.SelectionBoundsAdjuster, C0487rv {

    /* renamed from: a */
    public C0475rj f870a;

    /* renamed from: b */
    public ImageView f871b;

    /* renamed from: c */
    public boolean f872c;

    /* renamed from: d */
    public boolean f873d;

    /* renamed from: e */
    public boolean f874e;

    /* renamed from: f */
    private ImageView f875f;

    /* renamed from: g */
    private RadioButton f876g;

    /* renamed from: h */
    private TextView f877h;

    /* renamed from: i */
    private CheckBox f878i;

    /* renamed from: j */
    private TextView f879j;

    /* renamed from: k */
    private ImageView f880k;

    /* renamed from: l */
    private LinearLayout f881l;

    /* renamed from: m */
    private Drawable f882m;

    /* renamed from: n */
    private int f883n;

    /* renamed from: o */
    private Context f884o;

    /* renamed from: p */
    private Drawable f885p;

    /* renamed from: q */
    private LayoutInflater f886q;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listMenuViewStyle);
    }

    /* renamed from: a */
    public final C0475rj mo762a() {
        return this.f870a;
    }

    /* renamed from: b */
    public final boolean mo764b() {
        return false;
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        C0684zc a = C0684zc.m16192a(getContext(), attributeSet, C0435px.f15590r, i, 0);
        this.f882m = a.mo10723a(5);
        this.f883n = a.mo10734f(1, -1);
        this.f872c = a.mo10725a(7, false);
        this.f884o = context;
        this.f885p = a.mo10723a(8);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes((AttributeSet) null, new int[]{16843049}, R.attr.dropDownListViewStyle, 0);
        this.f873d = obtainStyledAttributes.hasValue(0);
        a.mo10724a();
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    private final void m854a(View view) {
        m855a(view, -1);
    }

    /* renamed from: a */
    private final void m855a(View view, int i) {
        LinearLayout linearLayout = this.f881l;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f871b;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f871b.getLayoutParams();
            rect.top += this.f871b.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
    }

    /* renamed from: c */
    private final LayoutInflater m856c() {
        if (this.f886q == null) {
            this.f886q = LayoutInflater.from(getContext());
        }
        return this.f886q;
    }

    /* renamed from: a */
    public final void mo763a(C0475rj rjVar) {
        ImageView imageView;
        String str;
        int i;
        RadioButton radioButton;
        CheckBox checkBox;
        CheckBox checkBox2;
        this.f870a = rjVar;
        int i2 = 0;
        setVisibility(!rjVar.isVisible() ? 8 : 0);
        CharSequence a = rjVar.mo9885a((C0487rv) this);
        if (a != null) {
            this.f877h.setText(a);
            if (this.f877h.getVisibility() != 0) {
                this.f877h.setVisibility(0);
            }
        } else if (this.f877h.getVisibility() != 8) {
            this.f877h.setVisibility(8);
        }
        boolean isCheckable = rjVar.isCheckable();
        if (!(!isCheckable && this.f876g == null && this.f878i == null)) {
            if (!this.f870a.mo9894e()) {
                if (this.f878i == null) {
                    CheckBox checkBox3 = (CheckBox) m856c().inflate(R.layout.abc_list_menu_item_checkbox, this, false);
                    this.f878i = checkBox3;
                    m854a((View) checkBox3);
                }
                CheckBox checkBox4 = this.f878i;
                radioButton = this.f876g;
                checkBox = checkBox4;
                checkBox2 = checkBox4;
            } else {
                if (this.f876g == null) {
                    RadioButton radioButton2 = (RadioButton) m856c().inflate(R.layout.abc_list_menu_item_radio, this, false);
                    this.f876g = radioButton2;
                    m854a((View) radioButton2);
                }
                RadioButton radioButton3 = this.f876g;
                radioButton = this.f878i;
                checkBox = radioButton3;
                checkBox2 = radioButton;
            }
            if (isCheckable) {
                checkBox.setChecked(this.f870a.isChecked());
                if (checkBox.getVisibility() != 0) {
                    checkBox.setVisibility(0);
                }
                if (!(radioButton == null || radioButton.getVisibility() == 8)) {
                    radioButton.setVisibility(8);
                }
            } else {
                if (checkBox2 != null) {
                    checkBox2.setVisibility(8);
                }
                RadioButton radioButton4 = this.f876g;
                if (radioButton4 != null) {
                    radioButton4.setVisibility(8);
                }
            }
        }
        boolean d = rjVar.mo9893d();
        rjVar.mo9890c();
        int i3 = (!d || !this.f870a.mo9893d()) ? 8 : 0;
        if (i3 == 0) {
            TextView textView = this.f879j;
            C0475rj rjVar2 = this.f870a;
            char c = rjVar2.mo9890c();
            if (c != 0) {
                Resources resources = rjVar2.f15789j.f15749a.getResources();
                StringBuilder sb = new StringBuilder();
                if (ViewConfiguration.get(rjVar2.f15789j.f15749a).hasPermanentMenuKey()) {
                    sb.append(resources.getString(R.string.abc_prepend_shortcut_label));
                }
                if (!rjVar2.f15789j.mo9854c()) {
                    i = rjVar2.f15786g;
                } else {
                    i = rjVar2.f15788i;
                }
                C0475rj.m15267a(sb, i, 65536, resources.getString(R.string.abc_menu_meta_shortcut_label));
                C0475rj.m15267a(sb, i, 4096, resources.getString(R.string.abc_menu_ctrl_shortcut_label));
                C0475rj.m15267a(sb, i, 2, resources.getString(R.string.abc_menu_alt_shortcut_label));
                C0475rj.m15267a(sb, i, 1, resources.getString(R.string.abc_menu_shift_shortcut_label));
                C0475rj.m15267a(sb, i, 4, resources.getString(R.string.abc_menu_sym_shortcut_label));
                C0475rj.m15267a(sb, i, 8, resources.getString(R.string.abc_menu_function_shortcut_label));
                if (c == 8) {
                    sb.append(resources.getString(R.string.abc_menu_delete_shortcut_label));
                } else if (c == 10) {
                    sb.append(resources.getString(R.string.abc_menu_enter_shortcut_label));
                } else if (c != ' ') {
                    sb.append(c);
                } else {
                    sb.append(resources.getString(R.string.abc_menu_space_shortcut_label));
                }
                str = sb.toString();
            } else {
                str = "";
            }
            textView.setText(str);
        }
        if (this.f879j.getVisibility() != i3) {
            this.f879j.setVisibility(i3);
        }
        Drawable icon = rjVar.getIcon();
        boolean z = this.f874e;
        if ((z || this.f872c) && !((imageView = this.f875f) == null && icon == null && !this.f872c)) {
            if (imageView == null) {
                ImageView imageView2 = (ImageView) m856c().inflate(R.layout.abc_list_menu_item_icon, this, false);
                this.f875f = imageView2;
                m855a(imageView2, 0);
            }
            if (icon == null && !this.f872c) {
                this.f875f.setVisibility(8);
            } else {
                ImageView imageView3 = this.f875f;
                if (!z) {
                    icon = null;
                }
                imageView3.setImageDrawable(icon);
                if (this.f875f.getVisibility() != 0) {
                    this.f875f.setVisibility(0);
                }
            }
        }
        setEnabled(rjVar.isEnabled());
        boolean hasSubMenu = rjVar.hasSubMenu();
        ImageView imageView4 = this.f880k;
        if (imageView4 != null) {
            if (!hasSubMenu) {
                i2 = 8;
            }
            imageView4.setVisibility(i2);
        }
        setContentDescription(rjVar.f15791l);
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        C0340mj.m14694a((View) this, this.f882m);
        TextView textView = (TextView) findViewById(R.id.title);
        this.f877h = textView;
        int i = this.f883n;
        if (i != -1) {
            textView.setTextAppearance(this.f884o, i);
        }
        this.f879j = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.f880k = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f885p);
        }
        this.f871b = (ImageView) findViewById(R.id.group_divider);
        this.f881l = (LinearLayout) findViewById(R.id.content);
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        if (this.f875f != null && this.f872c) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f875f.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }
}
