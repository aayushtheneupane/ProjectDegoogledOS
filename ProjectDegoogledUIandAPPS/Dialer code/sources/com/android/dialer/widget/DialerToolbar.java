package com.android.dialer.widget;

import android.app.Activity;
import android.content.Context;
import android.support.p002v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;

public class DialerToolbar extends Toolbar {
    private final BidiTextView subtitle = ((BidiTextView) findViewById(R.id.subtitle));
    private final TextView title = ((TextView) findViewById(R.id.title));

    public DialerToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.toolbarStyle);
        ViewGroup.inflate(context, R.layout.dialer_toolbar, this);
        setElevation((float) getResources().getDimensionPixelSize(R.dimen.toolbar_elevation));
        setBackgroundColor(((AospThemeImpl) ThemeComponent.get(context).theme()).getColorPrimary());
        setNavigationIcon((int) R.drawable.quantum_ic_close_white_24);
        setNavigationContentDescription((int) R.string.toolbar_close);
        setNavigationOnClickListener(new View.OnClickListener(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void onClick(View view) {
                ((Activity) this.f$0).finish();
            }
        });
        setPaddingRelative(getPaddingStart(), getPaddingTop(), getResources().getDimensionPixelSize(R.dimen.toolbar_end_padding), getPaddingBottom());
    }

    public void setSubtitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.subtitle.setText(charSequence);
            this.subtitle.setVisibility(0);
        }
    }

    public void setTitle(int i) {
        setTitle((CharSequence) getResources().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        this.title.setText(charSequence);
    }
}
