package com.sirinath.utils.numeric.dragonbox;

import static com.sirinath.utils.numeric.Integer64Utils.FormatInfo.valueBits;

public final class DragonBox64 {
    private DragonBox64() {}

    public static final class Div {
        public static final long DEFAULT_TABLE_SIZE = 24;

        public static final void generateTable() {

        }

        public static final long modularInverse(final long a) {
            // By Euler's theorem, a^phi(2^n) == 1 (mod 2^n),
            // where phi(2^n) = 2^(n-1), so the modular inverse of a is
            // a^(2^(n-1) - 1) = a^(1 + 2 + 2^2 + ... + 2^(n-2)).
            long modularInverse = 1;

            for (long i = 1; i < valueBits; ++i) { // FIXME: this might overflow
                modularInverse = modularInverse * modularInverse * a;
            }

            return modularInverse;
        }
    }
}
