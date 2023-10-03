package org.apache.james.mime4j.stream;

public class ParserCursor {
    private final int lowerBound;
    private int pos;
    private final int upperBound;

    public ParserCursor(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        } else if (i <= i2) {
            this.lowerBound = i;
            this.upperBound = i2;
            this.pos = i;
        } else {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public int getPos() {
        return this.pos;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public String toString() {
        return '[' + Integer.toString(this.lowerBound) + '>' + Integer.toString(this.pos) + '>' + Integer.toString(this.upperBound) + ']';
    }

    public void updatePos(int i) {
        if (i < this.lowerBound) {
            throw new IndexOutOfBoundsException("pos: " + i + " < lowerBound: " + this.lowerBound);
        } else if (i <= this.upperBound) {
            this.pos = i;
        } else {
            throw new IndexOutOfBoundsException("pos: " + i + " > upperBound: " + this.upperBound);
        }
    }
}
