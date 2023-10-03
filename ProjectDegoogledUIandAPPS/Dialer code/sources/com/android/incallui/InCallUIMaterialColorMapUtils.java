package com.android.incallui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import com.android.contacts.common.util.MaterialColorMapUtils;
import com.android.dialer.R;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;

public class InCallUIMaterialColorMapUtils extends MaterialColorMapUtils {
    private final Context context;
    private final TypedArray primaryColors = this.resources.obtainTypedArray(R.array.background_colors);
    private final Resources resources;
    private final TypedArray secondaryColors = this.resources.obtainTypedArray(R.array.background_colors_dark);

    public InCallUIMaterialColorMapUtils(Context context2) {
        super(context2.getResources());
        this.resources = context2.getResources();
        this.context = context2;
    }

    public MaterialColorMapUtils.MaterialPalette calculatePrimaryAndSecondaryColor(int i) {
        if (i == 0) {
            Context context2 = this.context;
            return new MaterialColorMapUtils.MaterialPalette(((AospThemeImpl) ThemeComponent.get(context2).theme()).getColorPrimary(), ((AospThemeImpl) ThemeComponent.get(context2).theme()).getColorPrimaryDark());
        }
        for (int i2 = 0; i2 < this.primaryColors.length(); i2++) {
            if (this.primaryColors.getColor(i2, 0) == i) {
                return new MaterialColorMapUtils.MaterialPalette(this.primaryColors.getColor(i2, 0), this.secondaryColors.getColor(i2, 0));
            }
        }
        return super.calculatePrimaryAndSecondaryColor(i);
    }
}
