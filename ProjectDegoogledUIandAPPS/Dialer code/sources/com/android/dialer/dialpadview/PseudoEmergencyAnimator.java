package com.android.dialer.dialpadview;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.LightingColorFilter;
import android.os.Handler;
import android.os.Vibrator;
import com.android.dialer.dialpadview.DialpadFragment;
import com.android.dialer.dialpadview.PseudoEmergencyAnimator;

public class PseudoEmergencyAnimator {
    private ValueAnimator pseudoEmergencyColorAnimator;
    /* access modifiers changed from: private */
    public ViewProvider viewProvider;

    interface ViewProvider {
    }

    PseudoEmergencyAnimator(ViewProvider viewProvider2) {
        this.viewProvider = viewProvider2;
    }

    static /* synthetic */ void access$000(PseudoEmergencyAnimator pseudoEmergencyAnimator, long j) {
        Vibrator vibrator;
        Activity activity = DialpadFragment.this.getActivity();
        if (activity != null && (vibrator = (Vibrator) activity.getSystemService("vibrator")) != null) {
            vibrator.vibrate(j);
        }
    }

    public void destroy() {
        end();
        this.viewProvider = null;
    }

    public void end() {
        ValueAnimator valueAnimator = this.pseudoEmergencyColorAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.pseudoEmergencyColorAnimator.end();
        }
    }

    public /* synthetic */ void lambda$start$0$PseudoEmergencyAnimator(ValueAnimator valueAnimator) {
        try {
            LightingColorFilter lightingColorFilter = new LightingColorFilter(-16777216, ((Integer) valueAnimator.getAnimatedValue()).intValue());
            if (((DialpadFragment.C04763) this.viewProvider).getFab() != null) {
                ((DialpadFragment.C04763) this.viewProvider).getFab().getBackground().setColorFilter(lightingColorFilter);
            }
        } catch (Exception unused) {
            valueAnimator.cancel();
        }
    }

    public void start() {
        if (this.pseudoEmergencyColorAnimator == null) {
            this.pseudoEmergencyColorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{-16776961, -65536});
            this.pseudoEmergencyColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PseudoEmergencyAnimator.this.lambda$start$0$PseudoEmergencyAnimator(valueAnimator);
                }
            });
            this.pseudoEmergencyColorAnimator.addListener(new Animator.AnimatorListener() {
                public /* synthetic */ void lambda$onAnimationEnd$0$PseudoEmergencyAnimator$1() {
                    try {
                        PseudoEmergencyAnimator.access$000(PseudoEmergencyAnimator.this, 200);
                    } catch (Exception unused) {
                    }
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    try {
                        if (((DialpadFragment.C04763) PseudoEmergencyAnimator.this.viewProvider).getFab() != null) {
                            ((DialpadFragment.C04763) PseudoEmergencyAnimator.this.viewProvider).getFab().getBackground().clearColorFilter();
                        }
                        new Handler().postDelayed(new Runnable() {
                            public final void run() {
                                PseudoEmergencyAnimator.C04791.this.lambda$onAnimationEnd$0$PseudoEmergencyAnimator$1();
                            }
                        }, 1000);
                    } catch (Exception unused) {
                        animator.cancel();
                    }
                }

                public void onAnimationRepeat(Animator animator) {
                    try {
                        PseudoEmergencyAnimator.access$000(PseudoEmergencyAnimator.this, 200);
                    } catch (Exception unused) {
                        animator.cancel();
                    }
                }

                public void onAnimationStart(Animator animator) {
                }
            });
            this.pseudoEmergencyColorAnimator.setDuration(200);
            this.pseudoEmergencyColorAnimator.setRepeatMode(2);
            this.pseudoEmergencyColorAnimator.setRepeatCount(6);
        }
        if (!this.pseudoEmergencyColorAnimator.isStarted()) {
            this.pseudoEmergencyColorAnimator.start();
        }
    }
}
