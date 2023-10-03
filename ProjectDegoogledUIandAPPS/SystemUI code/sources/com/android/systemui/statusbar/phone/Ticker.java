package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadata;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.TextSwitcher;
import android.widget.TextView;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.StatusBarIconView;
import java.util.ArrayList;

public abstract class Ticker implements DarkIconDispatcher.DarkReceiver {
    private Runnable mAdvanceTicker = new Runnable() {
        public void run() {
            while (true) {
                if (Ticker.this.mSegments.size() <= 0) {
                    break;
                }
                Segment segment = (Segment) Ticker.this.mSegments.get(0);
                if (segment.first) {
                    Ticker.this.setAppIconColor(segment.icon);
                }
                CharSequence advance = segment.advance();
                if (advance != null) {
                    Ticker.this.mTextSwitcher.setText(advance);
                    Ticker.this.mTextSwitcher.setTextColor(Ticker.this.mTextColor);
                    Ticker.this.scheduleAdvance();
                    break;
                }
                Ticker.this.mSegments.remove(0);
            }
            if (Ticker.this.mSegments.size() == 0) {
                Ticker.this.tickerDone();
            }
        }
    };
    private Animation mAnimationIn;
    private Animation mAnimationOut;
    private Context mContext;
    private Handler mHandler = new Handler();
    private float mIconScale;
    private ImageSwitcher mIconSwitcher;
    private int mIconTint = -1;
    private ContrastColorUtil mNotificationColorUtil;
    /* access modifiers changed from: private */
    public TextPaint mPaint;
    /* access modifiers changed from: private */
    public ArrayList<Segment> mSegments = new ArrayList<>();
    private MediaMetadata mShowingMediaMetadata;
    private String mShowingNotificationText;
    /* access modifiers changed from: private */
    public int mTextColor = -1;
    /* access modifiers changed from: private */
    public TextSwitcher mTextSwitcher;
    private int mTickerSegmentDelay = 3000;

    public abstract void tickerDone();

    public abstract void tickerHalting();

    public abstract void tickerStarting();

    public static boolean isGraphicOrEmoji(char c) {
        int type = Character.getType(c);
        return (type == 15 || type == 16 || type == 0 || type == 13 || type == 14 || type == 12) ? false : true;
    }

    private final class Segment {
        int current;
        boolean first;
        Drawable icon;
        int next;
        StatusBarNotification notification;
        CharSequence text;

        /* access modifiers changed from: package-private */
        public StaticLayout getLayout(CharSequence charSequence) {
            int width = (Ticker.this.mTextSwitcher.getWidth() - Ticker.this.mTextSwitcher.getPaddingLeft()) - Ticker.this.mTextSwitcher.getPaddingRight();
            if (width <= 0) {
                return null;
            }
            return new StaticLayout(charSequence, Ticker.this.mPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        }

        /* access modifiers changed from: package-private */
        public CharSequence rtrim(CharSequence charSequence, int i, int i2) {
            while (i2 > i && !Ticker.isGraphicOrEmoji(charSequence.charAt(i2 - 1))) {
                i2--;
            }
            if (i2 > i) {
                return charSequence.subSequence(i, i2);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public CharSequence getText() {
            if (this.current > this.text.length()) {
                return null;
            }
            CharSequence charSequence = this.text;
            CharSequence subSequence = charSequence.subSequence(this.current, charSequence.length());
            StaticLayout layout = getLayout(subSequence);
            if (layout == null) {
                return null;
            }
            int lineCount = layout.getLineCount();
            if (lineCount > 0) {
                int lineStart = layout.getLineStart(0);
                int lineEnd = layout.getLineEnd(0);
                this.next = this.current + lineEnd;
                return rtrim(subSequence, lineStart, lineEnd);
            }
            throw new RuntimeException("lineCount=" + lineCount + " current=" + this.current + " text=" + this.text);
        }

        /* access modifiers changed from: package-private */
        public CharSequence advance() {
            this.first = false;
            int i = this.next;
            int length = this.text.length();
            while (i < length && !Ticker.isGraphicOrEmoji(this.text.charAt(i))) {
                i++;
            }
            if (i >= length) {
                return null;
            }
            CharSequence charSequence = this.text;
            CharSequence subSequence = charSequence.subSequence(i, charSequence.length());
            StaticLayout layout = getLayout(subSequence);
            if (layout == null) {
                return null;
            }
            int lineCount = layout.getLineCount();
            for (int i2 = 0; i2 < lineCount; i2++) {
                int lineStart = layout.getLineStart(i2);
                int lineEnd = layout.getLineEnd(i2);
                if (i2 == lineCount - 1) {
                    this.next = length;
                } else {
                    this.next = layout.getLineStart(i2 + 1) + i;
                }
                CharSequence rtrim = rtrim(subSequence, lineStart, lineEnd);
                if (rtrim != null) {
                    this.current = i + lineStart;
                    return rtrim;
                }
            }
            this.current = length;
            return null;
        }

        Segment(StatusBarNotification statusBarNotification, Drawable drawable, CharSequence charSequence) {
            this.notification = statusBarNotification;
            this.icon = drawable;
            this.text = charSequence;
            int length = charSequence.length();
            int i = 0;
            while (i < length && !Ticker.isGraphicOrEmoji(charSequence.charAt(i))) {
                i++;
            }
            this.current = i;
            this.next = i;
            this.first = true;
        }
    }

    public Ticker(Context context, View view, int i, int i2) {
        this.mContext = context;
        Resources resources = context.getResources();
        this.mIconScale = ((float) resources.getDimensionPixelSize(C1775R$dimen.status_bar_icon_drawing_size)) / ((float) resources.getDimensionPixelSize(C1775R$dimen.status_bar_icon_size));
        updateAnimation(i);
        updateTickDuration(i2);
        this.mNotificationColorUtil = ContrastColorUtil.getInstance(this.mContext);
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
    }

    public void updateAnimation(int i) {
        if (i == 1) {
            this.mAnimationIn = AnimationUtils.loadAnimation(this.mContext, 17432722);
            this.mAnimationOut = AnimationUtils.loadAnimation(this.mContext, 17432723);
        } else {
            this.mAnimationIn = new AlphaAnimation(0.0f, 1.0f);
            this.mAnimationIn.setInterpolator(AnimationUtils.loadInterpolator(this.mContext, 17563649));
            this.mAnimationIn.setDuration(350);
            this.mAnimationOut = new AlphaAnimation(1.0f, 0.0f);
            this.mAnimationOut.setInterpolator(AnimationUtils.loadInterpolator(this.mContext, 17563648));
            this.mAnimationOut.setDuration(350);
        }
        if (this.mTextSwitcher != null && this.mIconSwitcher != null) {
            setViewAnimations();
        }
    }

    public void updateTickDuration(int i) {
        this.mTickerSegmentDelay = i;
    }

    public void addEntry(StatusBarNotification statusBarNotification, boolean z, MediaMetadata mediaMetadata, String str) {
        int size = this.mSegments.size();
        this.mContext.getContentResolver();
        if (z && mediaMetadata != null) {
            CharSequence text = mediaMetadata.getText("android.media.metadata.ARTIST");
            CharSequence text2 = mediaMetadata.getText("android.media.metadata.ALBUM");
            CharSequence text3 = mediaMetadata.getText("android.media.metadata.TITLE");
            if (text == null && text2 == null && text3 == null) {
                if (str != null) {
                    String str2 = this.mShowingNotificationText;
                    if (str2 == null || !str.equals(str2)) {
                        this.mShowingNotificationText = str;
                        statusBarNotification.getNotification().tickerText = str;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else if (this.mShowingMediaMetadata != mediaMetadata) {
                this.mShowingMediaMetadata = mediaMetadata;
                String charSequence = text != null ? text.toString() : "";
                if (!(text == null || text2 == null)) {
                    charSequence = charSequence + " - ";
                }
                if (text2 != null) {
                    charSequence = charSequence + text2.toString();
                }
                if (!((text == null && text2 == null) || text3 == null)) {
                    charSequence = charSequence + " - ";
                }
                if (text3 != null) {
                    charSequence = charSequence + text3.toString();
                }
                statusBarNotification.getNotification().tickerText = charSequence;
            } else {
                return;
            }
        }
        if (size > 0) {
            Segment segment = this.mSegments.get(0);
            if (statusBarNotification.getPackageName().equals(segment.notification.getPackageName()) && statusBarNotification.getNotification().icon == segment.notification.getNotification().icon && statusBarNotification.getNotification().iconLevel == segment.notification.getNotification().iconLevel && charSequencesEqual(segment.notification.getNotification().tickerText, statusBarNotification.getNotification().tickerText)) {
                return;
            }
        }
        Segment segment2 = new Segment(statusBarNotification, StatusBarIconView.getIcon(this.mContext, new StatusBarIcon(statusBarNotification.getPackageName(), statusBarNotification.getUser(), statusBarNotification.getNotification().icon, statusBarNotification.getNotification().iconLevel, 0, statusBarNotification.getNotification().tickerText)), statusBarNotification.getNotification().tickerText);
        int i = 0;
        while (i < this.mSegments.size()) {
            Segment segment3 = this.mSegments.get(i);
            if (statusBarNotification.getId() == segment3.notification.getId() && statusBarNotification.getPackageName().equals(segment3.notification.getPackageName())) {
                this.mSegments.remove(i);
                i--;
            }
            i++;
        }
        this.mSegments.add(segment2);
        if (size == 0 && this.mSegments.size() > 0) {
            Segment segment4 = this.mSegments.get(0);
            segment4.first = false;
            this.mIconSwitcher.setAnimateFirstView(false);
            this.mIconSwitcher.reset();
            setAppIconColor(segment4.icon);
            this.mTextSwitcher.setAnimateFirstView(false);
            this.mTextSwitcher.reset();
            this.mTextSwitcher.setText(segment4.getText());
            this.mTextSwitcher.setTextColor(this.mTextColor);
            tickerStarting();
            scheduleAdvance();
        }
    }

    private static boolean charSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence.length() != charSequence2.length()) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public void removeEntry(StatusBarNotification statusBarNotification) {
        for (int size = this.mSegments.size() - 1; size >= 0; size--) {
            Segment segment = this.mSegments.get(size);
            if (statusBarNotification.getId() == segment.notification.getId() && statusBarNotification.getPackageName().equals(segment.notification.getPackageName())) {
                this.mSegments.remove(size);
            }
        }
    }

    public void halt() {
        this.mHandler.removeCallbacks(this.mAdvanceTicker);
        this.mSegments.clear();
        tickerHalting();
    }

    public void resetShownMediaMetadata() {
        this.mShowingMediaMetadata = null;
        this.mShowingNotificationText = null;
    }

    public void setViews(TextSwitcher textSwitcher, ImageSwitcher imageSwitcher) {
        this.mTextSwitcher = textSwitcher;
        this.mPaint = ((TextView) this.mTextSwitcher.getChildAt(0)).getPaint();
        this.mIconSwitcher = imageSwitcher;
        this.mIconSwitcher.setScaleX(this.mIconScale);
        this.mIconSwitcher.setScaleY(this.mIconScale);
        setViewAnimations();
    }

    private void setViewAnimations() {
        this.mTextSwitcher.setInAnimation(this.mAnimationIn);
        this.mTextSwitcher.setOutAnimation(this.mAnimationOut);
        this.mIconSwitcher.setInAnimation(this.mAnimationIn);
        this.mIconSwitcher.setOutAnimation(this.mAnimationOut);
    }

    public void reflowText() {
        if (this.mSegments.size() > 0) {
            this.mTextSwitcher.setCurrentText(this.mSegments.get(0).getText());
            this.mTextSwitcher.setTextColor(this.mTextColor);
        }
    }

    /* access modifiers changed from: private */
    public void scheduleAdvance() {
        this.mHandler.postDelayed(this.mAdvanceTicker, (long) this.mTickerSegmentDelay);
    }

    public void applyDarkIntensity(Rect rect, View view, int i) {
        this.mTextColor = DarkIconDispatcher.getTint(rect, view, i);
        this.mIconTint = this.mTextColor;
        if (this.mSegments.size() > 0) {
            this.mTextSwitcher.setTextColor(this.mTextColor);
            this.mIconSwitcher.reset();
            setAppIconColor(this.mSegments.get(0).icon);
        }
    }

    /* access modifiers changed from: private */
    public void setAppIconColor(Drawable drawable) {
        this.mIconSwitcher.setImageDrawableTint(drawable, this.mIconTint, this.mNotificationColorUtil.isGrayscaleIcon(drawable));
    }
}
