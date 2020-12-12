package com.sirinath.utils.buffers;

class PaddedPosition0 {
    long h0 = 0, h1 = 0, h2 = 0, h3 = 0, h4 = 0, h5 = 0, h6 = 0, h7 = 0, h8 = 0;

    public long preventOptimisationOfPaddingInClassPaddedPosition0() {
        return h0 + h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8 +
                h0++ + h1++ + h2++ + h3++ + h4++ + h5++ + h6++ + h7++ + h8++ +
                ++h0 + ++h1 + ++h2 + ++h3 + ++h4 + ++h5 + ++h6 + ++h7 + ++h8;
    }
}

class PaddedPosition1 extends PaddedPosition0 {
    long p0 = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0, p7 = 0;
    private long position = 0;
    long q0 = 0, q1 = 0, q2 = 0, q3 = 0, q4 = 0, q5 = 0, q6 = 0, q7 = 0, q8 = 0;

    public final long getPosition() {
        return position;
    }

    public final void setPosition(final long position) {
        this.position = position;
    }

    public long preventOptimisationOfPaddingInClassPaddedPosition1() {
        return p0 + p1 + p2 + p3 + p4 + p5 + p6 + p7 +
                position +
                q0 + q1 + q2 + q3 + q4 + q5 + q6 + q7 + q8 +
                p0++ + p1++ + p2++ + p3++ + p4++ + p5++ + p6++ + p7++ +
                position +
                q0++ + q1++ + q2++ + q3++ + q4++ + q5++ + q6++ + q7++ + q8++ +
                ++p0 + ++p1 + ++p2 + ++p3 + ++p4 + ++p5 + ++p6 + ++p7 +
                position +
                ++q0 + ++q1 + ++q2 + ++q3 + ++q4 + ++q5 + ++q6 + ++q7 + ++q8;
    }
}

class PaddedPosition2 extends PaddedPosition1 {
    long t0 = 0, t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0, t7 = 0, t8 = 0;

    public long preventOptimisationOfPaddingInClassPaddedPosition2() {
        return t0 + t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 +
                t0++ + t1++ + t2++ + t3++ + t4++ + t5++ + t6++ + t7++ + t8++ +
                ++t0 + ++t1 + ++t2 + ++t3 + ++t4 + ++t5 + ++t6 + ++t7 + ++t8;
    }
}

public class PaddedPosition extends PaddedPosition2 {
    public long preventOptimisationOfPaddingInClassPaddedPosition() {
        return preventOptimisationOfPaddingInClassPaddedPosition0() +
                preventOptimisationOfPaddingInClassPaddedPosition1() +
                preventOptimisationOfPaddingInClassPaddedPosition2();
    }
}
