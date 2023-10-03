package p000a.p013g.p014a.p015a;

import android.graphics.Path;
import androidx.core.graphics.PathParser;

/* renamed from: a.g.a.a.m */
abstract class C0060m extends C0059l {

    /* renamed from: fu */
    protected PathParser.PathDataNode[] f59fu = null;

    /* renamed from: gu */
    int f60gu = 0;
    int mChangingConfigurations;
    String mPathName;

    public C0060m() {
        super((C0055h) null);
    }

    public PathParser.PathDataNode[] getPathData() {
        return this.f59fu;
    }

    public String getPathName() {
        return this.mPathName;
    }

    /* renamed from: kd */
    public boolean mo316kd() {
        return false;
    }

    public void setPathData(PathParser.PathDataNode[] pathDataNodeArr) {
        if (!PathParser.canMorph(this.f59fu, pathDataNodeArr)) {
            this.f59fu = PathParser.deepCopyNodes(pathDataNodeArr);
        } else {
            PathParser.updateNodes(this.f59fu, pathDataNodeArr);
        }
    }

    public void toPath(Path path) {
        path.reset();
        PathParser.PathDataNode[] pathDataNodeArr = this.f59fu;
        if (pathDataNodeArr != null) {
            PathParser.PathDataNode.nodesToPath(pathDataNodeArr, path);
        }
    }

    public C0060m(C0060m mVar) {
        super((C0055h) null);
        this.mPathName = mVar.mPathName;
        this.mChangingConfigurations = mVar.mChangingConfigurations;
        this.f59fu = PathParser.deepCopyNodes(mVar.f59fu);
    }
}
