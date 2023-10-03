package com.android.dialer.lettertile;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class LetterTileDrawable extends Drawable {
    private int color;
    private final TypedArray colors;
    private int contactType = 1;
    private final Drawable defaultBusinessAvatar;
    private final int defaultColor;
    private final Drawable defaultConferenceAvatar;
    private final Drawable defaultPersonAvatar;
    private final Drawable defaultSpamAvatar;
    private final Drawable defaultVoicemailAvatar;
    private String displayName;
    private final char[] firstChar = new char[1];
    private boolean isCircle = false;
    private Character letter = null;
    private final float letterToTileRatio;
    private float offset = 0.0f;
    private final Paint paint = new Paint();
    private final Rect rect = new Rect();
    private float scale = 1.0f;
    private final int spamColor;
    private final int tileFontColor;

    public LetterTileDrawable(Resources resources) {
        this.colors = resources.obtainTypedArray(R.array.letter_tile_colors);
        this.spamColor = resources.getColor(R.color.spam_contact_background);
        this.defaultColor = resources.getColor(R.color.letter_tile_default_color);
        this.tileFontColor = resources.getColor(R.color.letter_tile_font_color);
        this.letterToTileRatio = resources.getFraction(R.dimen.letter_to_tile_ratio, 1, 1);
        this.defaultPersonAvatar = resources.getDrawable(R.drawable.product_logo_avatar_anonymous_white_color_120, (Resources.Theme) null);
        this.defaultBusinessAvatar = resources.getDrawable(R.drawable.quantum_ic_business_vd_theme_24, (Resources.Theme) null);
        this.defaultVoicemailAvatar = resources.getDrawable(R.drawable.quantum_ic_voicemail_vd_theme_24, (Resources.Theme) null);
        this.defaultSpamAvatar = resources.getDrawable(R.drawable.quantum_ic_report_vd_theme_24, (Resources.Theme) null);
        this.defaultConferenceAvatar = resources.getDrawable(R.drawable.quantum_ic_group_vd_theme_24, (Resources.Theme) null);
        this.paint.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setAntiAlias(true);
        this.paint.setFilterBitmap(true);
        this.paint.setDither(true);
        this.color = this.defaultColor;
    }

    public static int getContactTypeFromPrimitives(boolean z, boolean z2, boolean z3, int i, boolean z4) {
        if (z) {
            return 3;
        }
        if (z2) {
            return 5;
        }
        if (z3) {
            return 2;
        }
        if (i == 2) {
            return 4;
        }
        return z4 ? 6 : 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.dialer.lettertile.LetterTileDrawable setLetterAndColorFromContactDetails(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L_0x002f
            r0 = 0
            char r1 = r4.charAt(r0)
            r2 = 65
            if (r2 > r1) goto L_0x0013
            r2 = 90
            if (r1 <= r2) goto L_0x001b
        L_0x0013:
            r2 = 97
            if (r2 > r1) goto L_0x001d
            r2 = 122(0x7a, float:1.71E-43)
            if (r1 > r2) goto L_0x001d
        L_0x001b:
            r1 = 1
            goto L_0x001e
        L_0x001d:
            r1 = r0
        L_0x001e:
            if (r1 == 0) goto L_0x002f
            char r4 = r4.charAt(r0)
            char r4 = java.lang.Character.toUpperCase(r4)
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r3.letter = r4
            goto L_0x0032
        L_0x002f:
            r4 = 0
            r3.letter = r4
        L_0x0032:
            int r4 = r3.contactType
            r0 = 5
            if (r4 != r0) goto L_0x003a
            int r4 = r3.spamColor
            goto L_0x0061
        L_0x003a:
            r0 = 3
            if (r4 == r0) goto L_0x005f
            r0 = 2
            if (r4 == r0) goto L_0x005f
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 == 0) goto L_0x0047
            goto L_0x005f
        L_0x0047:
            int r4 = r5.hashCode()
            int r4 = java.lang.Math.abs(r4)
            android.content.res.TypedArray r5 = r3.colors
            int r5 = r5.length()
            int r4 = r4 % r5
            android.content.res.TypedArray r5 = r3.colors
            int r0 = r3.defaultColor
            int r4 = r5.getColor(r4, r0)
            goto L_0x0061
        L_0x005f:
            int r4 = r3.defaultColor
        L_0x0061:
            r3.color = r4
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.lettertile.LetterTileDrawable.setLetterAndColorFromContactDetails(java.lang.String, java.lang.String):com.android.dialer.lettertile.LetterTileDrawable");
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        Rect bounds = getBounds();
        if (isVisible() && !bounds.isEmpty()) {
            this.paint.setColor(this.color);
            Rect bounds2 = getBounds();
            int min = Math.min(bounds2.width(), bounds2.height());
            if (this.isCircle) {
                canvas.drawCircle((float) bounds2.centerX(), (float) bounds2.centerY(), (float) (min / 2), this.paint);
            } else {
                canvas.drawRect(bounds2, this.paint);
            }
            Character ch = this.letter;
            int i = 138;
            if (ch != null) {
                this.firstChar[0] = ch.charValue();
                this.paint.setTextSize(this.scale * this.letterToTileRatio * ((float) min));
                this.paint.getTextBounds(this.firstChar, 0, 1, this.rect);
                this.paint.setTypeface(Typeface.create("sans-serif", 0));
                this.paint.setColor(this.tileFontColor);
                this.paint.setAlpha(138);
                Canvas canvas2 = canvas;
                canvas2.drawText(this.firstChar, 0, 1, (float) bounds2.centerX(), ((this.offset * ((float) bounds2.height())) + ((float) bounds2.centerY())) - this.rect.exactCenterY(), this.paint);
                return;
            }
            int i2 = this.contactType;
            if (i2 == 2) {
                this.scale = 0.7f;
                drawable = this.defaultBusinessAvatar;
            } else if (i2 == 3) {
                this.scale = 0.7f;
                drawable = this.defaultVoicemailAvatar;
            } else if (i2 == 5) {
                this.scale = 0.7f;
                drawable = this.defaultSpamAvatar;
            } else if (i2 != 6) {
                drawable = this.defaultPersonAvatar;
            } else {
                this.scale = 0.7f;
                drawable = this.defaultConferenceAvatar;
            }
            if (drawable != null) {
                float f = this.scale;
                float f2 = this.offset;
                Rect copyBounds = copyBounds();
                int min2 = (int) ((f * ((float) Math.min(copyBounds.width(), copyBounds.height()))) / 2.0f);
                copyBounds.set(copyBounds.centerX() - min2, (int) ((((float) copyBounds.height()) * f2) + ((float) (copyBounds.centerY() - min2))), copyBounds.centerX() + min2, (int) ((f2 * ((float) copyBounds.height())) + ((float) (copyBounds.centerY() + min2))));
                drawable.setBounds(copyBounds);
                if (drawable == this.defaultSpamAvatar) {
                    i = 255;
                }
                drawable.setAlpha(i);
                drawable.draw(canvas);
                return;
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unable to find drawable for contact type ");
            outline13.append(this.contactType);
            throw new IllegalStateException(outline13.toString());
        }
    }

    public Bitmap getBitmap(int i, int i2) {
        Drawable drawable;
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        setBounds(0, 0, i, i2);
        Canvas canvas = new Canvas(createBitmap);
        Rect bounds = getBounds();
        if (isVisible() && !bounds.isEmpty()) {
            this.paint.setColor(this.color);
            Rect bounds2 = getBounds();
            int min = Math.min(bounds2.width(), bounds2.height());
            if (this.isCircle) {
                canvas.drawCircle((float) bounds2.centerX(), (float) bounds2.centerY(), (float) (min / 2), this.paint);
            } else {
                canvas.drawRect(bounds2, this.paint);
            }
            Character ch = this.letter;
            int i3 = 138;
            if (ch != null) {
                this.firstChar[0] = ch.charValue();
                this.paint.setTextSize(this.scale * this.letterToTileRatio * ((float) min));
                this.paint.getTextBounds(this.firstChar, 0, 1, this.rect);
                this.paint.setTypeface(Typeface.create("sans-serif", 0));
                this.paint.setColor(this.tileFontColor);
                this.paint.setAlpha(138);
                canvas.drawText(this.firstChar, 0, 1, (float) bounds2.centerX(), ((this.offset * ((float) bounds2.height())) + ((float) bounds2.centerY())) - this.rect.exactCenterY(), this.paint);
            } else {
                int i4 = this.contactType;
                if (i4 == 2) {
                    this.scale = 0.7f;
                    drawable = this.defaultBusinessAvatar;
                } else if (i4 == 3) {
                    this.scale = 0.7f;
                    drawable = this.defaultVoicemailAvatar;
                } else if (i4 == 5) {
                    this.scale = 0.7f;
                    drawable = this.defaultSpamAvatar;
                } else if (i4 != 6) {
                    drawable = this.defaultPersonAvatar;
                } else {
                    this.scale = 0.7f;
                    drawable = this.defaultConferenceAvatar;
                }
                if (drawable != null) {
                    float f = this.scale;
                    float f2 = this.offset;
                    Rect copyBounds = copyBounds();
                    int min2 = (int) ((f * ((float) Math.min(copyBounds.width(), copyBounds.height()))) / 2.0f);
                    copyBounds.set(copyBounds.centerX() - min2, (int) ((((float) copyBounds.height()) * f2) + ((float) (copyBounds.centerY() - min2))), copyBounds.centerX() + min2, (int) ((f2 * ((float) copyBounds.height())) + ((float) (copyBounds.centerY() + min2))));
                    drawable.setBounds(copyBounds);
                    if (drawable == this.defaultSpamAvatar) {
                        i3 = 255;
                    }
                    drawable.setAlpha(i3);
                    drawable.draw(canvas);
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unable to find drawable for contact type ");
                    outline13.append(this.contactType);
                    throw new IllegalStateException(outline13.toString());
                }
            }
        }
        return createBitmap;
    }

    public int getOpacity() {
        return -1;
    }

    public void getOutline(Outline outline) {
        if (this.isCircle) {
            outline.setOval(getBounds());
        } else {
            outline.setRect(getBounds());
        }
        outline.setAlpha(1.0f);
    }

    public void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    public LetterTileDrawable setCanonicalDialerLetterTileDetails(String str, String str2, int i, int i2) {
        setIsCircular(i == 1);
        if (i2 == 1 && ((str == null && str2 == null) || (str != null && str.equals(this.displayName)))) {
            return this;
        }
        this.displayName = str;
        this.contactType = i2;
        if (i2 != 1) {
            setLetterAndColorFromContactDetails((String) null, (String) null);
        } else if (str2 != null) {
            setLetterAndColorFromContactDetails(str, str2);
        } else {
            setLetterAndColorFromContactDetails(str, str);
        }
        return this;
    }

    public LetterTileDrawable setColor(int i) {
        this.color = i;
        return this;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public LetterTileDrawable setIsCircular(boolean z) {
        this.isCircle = z;
        return this;
    }

    public LetterTileDrawable setLetter(Character ch) {
        this.letter = ch;
        return this;
    }

    public LetterTileDrawable setOffset(float f) {
        Assert.checkArgument(f >= -0.5f && f <= 0.5f);
        this.offset = f;
        return this;
    }

    public LetterTileDrawable setScale(float f) {
        this.scale = f;
        return this;
    }
}
