package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class Barrier extends Helper {
    private boolean mAllowsGoneWidget = true;
    private int mBarrierType = 0;
    private ArrayList<ResolutionAnchor> mNodes = new ArrayList<>(4);

    public boolean allowedInBarrier() {
        return true;
    }

    public void setBarrierType(int i) {
        this.mBarrierType = i;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mAllowsGoneWidget = z;
    }

    public void resetResolutionNodes() {
        super.resetResolutionNodes();
        this.mNodes.clear();
    }

    public void analyze(int i) {
        ResolutionAnchor resolutionAnchor;
        ResolutionAnchor resolutionAnchor2;
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && ((ConstraintWidgetContainer) constraintWidget).optimizeFor(2)) {
            int i2 = this.mBarrierType;
            if (i2 == 0) {
                resolutionAnchor = this.mLeft.getResolutionNode();
            } else if (i2 == 1) {
                resolutionAnchor = this.mRight.getResolutionNode();
            } else if (i2 == 2) {
                resolutionAnchor = this.mTop.getResolutionNode();
            } else if (i2 == 3) {
                resolutionAnchor = this.mBottom.getResolutionNode();
            } else {
                return;
            }
            resolutionAnchor.setType(5);
            int i3 = this.mBarrierType;
            if (i3 == 0 || i3 == 1) {
                this.mTop.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
                this.mBottom.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
            } else {
                this.mLeft.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
                this.mRight.getResolutionNode().resolve((ResolutionAnchor) null, 0.0f);
            }
            this.mNodes.clear();
            for (int i4 = 0; i4 < this.mWidgetsCount; i4++) {
                ConstraintWidget constraintWidget2 = this.mWidgets[i4];
                if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                    int i5 = this.mBarrierType;
                    if (i5 == 0) {
                        resolutionAnchor2 = constraintWidget2.mLeft.getResolutionNode();
                    } else if (i5 == 1) {
                        resolutionAnchor2 = constraintWidget2.mRight.getResolutionNode();
                    } else if (i5 != 2) {
                        resolutionAnchor2 = i5 != 3 ? null : constraintWidget2.mBottom.getResolutionNode();
                    } else {
                        resolutionAnchor2 = constraintWidget2.mTop.getResolutionNode();
                    }
                    if (resolutionAnchor2 != null) {
                        this.mNodes.add(resolutionAnchor2);
                        resolutionAnchor2.addDependent(resolutionAnchor);
                    }
                }
            }
        }
    }

    public void resolve() {
        ResolutionAnchor resolutionAnchor;
        float f;
        ResolutionAnchor resolutionAnchor2;
        int i = this.mBarrierType;
        float f2 = Float.MAX_VALUE;
        if (i != 0) {
            if (i == 1) {
                resolutionAnchor = this.mRight.getResolutionNode();
            } else if (i == 2) {
                resolutionAnchor = this.mTop.getResolutionNode();
            } else if (i == 3) {
                resolutionAnchor = this.mBottom.getResolutionNode();
            } else {
                return;
            }
            f2 = 0.0f;
        } else {
            resolutionAnchor = this.mLeft.getResolutionNode();
        }
        int size = this.mNodes.size();
        ResolutionAnchor resolutionAnchor3 = null;
        int i2 = 0;
        while (i2 < size) {
            ResolutionAnchor resolutionAnchor4 = this.mNodes.get(i2);
            if (resolutionAnchor4.state == 1) {
                int i3 = this.mBarrierType;
                if (i3 == 0 || i3 == 2) {
                    f = resolutionAnchor4.resolvedOffset;
                    if (f < f2) {
                        resolutionAnchor2 = resolutionAnchor4.resolvedTarget;
                    } else {
                        i2++;
                    }
                } else {
                    f = resolutionAnchor4.resolvedOffset;
                    if (f > f2) {
                        resolutionAnchor2 = resolutionAnchor4.resolvedTarget;
                    } else {
                        i2++;
                    }
                }
                resolutionAnchor3 = resolutionAnchor2;
                f2 = f;
                i2++;
            } else {
                return;
            }
        }
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().barrierConnectionResolved++;
        }
        resolutionAnchor.resolvedTarget = resolutionAnchor3;
        resolutionAnchor.resolvedOffset = f2;
        resolutionAnchor.didResolve();
        int i4 = this.mBarrierType;
        if (i4 == 0) {
            this.mRight.getResolutionNode().resolve(resolutionAnchor3, f2);
        } else if (i4 == 1) {
            this.mLeft.getResolutionNode().resolve(resolutionAnchor3, f2);
        } else if (i4 == 2) {
            this.mBottom.getResolutionNode().resolve(resolutionAnchor3, f2);
        } else if (i4 == 3) {
            this.mTop.getResolutionNode().resolve(resolutionAnchor3, f2);
        }
    }

    public void addToSolver(LinearSystem linearSystem) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z;
        int i;
        int i2;
        ConstraintAnchor[] constraintAnchorArr2 = this.mListAnchors;
        constraintAnchorArr2[0] = this.mLeft;
        constraintAnchorArr2[2] = this.mTop;
        constraintAnchorArr2[1] = this.mRight;
        constraintAnchorArr2[3] = this.mBottom;
        int i3 = 0;
        while (true) {
            constraintAnchorArr = this.mListAnchors;
            if (i3 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i3].mSolverVariable = linearSystem.createObjectVariable(constraintAnchorArr[i3]);
            i3++;
        }
        int i4 = this.mBarrierType;
        if (i4 >= 0 && i4 < 4) {
            ConstraintAnchor constraintAnchor = constraintAnchorArr[i4];
            int i5 = 0;
            while (true) {
                if (i5 >= this.mWidgetsCount) {
                    z = false;
                    break;
                }
                ConstraintWidget constraintWidget = this.mWidgets[i5];
                if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i = this.mBarrierType) == 0 || i == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) || (((i2 = this.mBarrierType) == 2 || i2 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))) {
                    z = true;
                } else {
                    i5++;
                }
            }
            int i6 = this.mBarrierType;
            if (i6 == 0 || i6 == 1 ? getParent().getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT : getParent().getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                z = false;
            }
            for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
                ConstraintWidget constraintWidget2 = this.mWidgets[i7];
                if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                    SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                    ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.mListAnchors;
                    int i8 = this.mBarrierType;
                    constraintAnchorArr3[i8].mSolverVariable = createObjectVariable;
                    if (i8 == 0 || i8 == 2) {
                        linearSystem.addLowerBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                    } else {
                        linearSystem.addGreaterBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                    }
                }
            }
            int i9 = this.mBarrierType;
            if (i9 == 0) {
                linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 5);
                }
            } else if (i9 == 1) {
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 5);
                }
            } else if (i9 == 2) {
                linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 5);
                }
            } else if (i9 == 3) {
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 5);
                }
            }
        }
    }
}
