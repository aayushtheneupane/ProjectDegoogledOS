package com.android.systemui.classifier.brightline;

public class TypeClassifier extends FalsingClassifier {
    TypeClassifier(FalsingDataProvider falsingDataProvider) {
        super(falsingDataProvider);
    }

    public boolean isFalseTouch() {
        boolean isVertical = isVertical();
        boolean isUp = isUp();
        boolean isRight = isRight();
        int interactionType = getInteractionType();
        if (interactionType != 0) {
            if (interactionType == 1) {
                return isVertical;
            }
            if (interactionType != 2) {
                if (interactionType != 4) {
                    if (interactionType == 5) {
                        return !isRight || !isUp;
                    }
                    if (interactionType == 6) {
                        return isRight || !isUp;
                    }
                    if (interactionType != 8) {
                        if (interactionType != 9) {
                            return true;
                        }
                    }
                }
                return !isVertical || !isUp;
            }
        }
        return !isVertical || isUp;
    }
}
