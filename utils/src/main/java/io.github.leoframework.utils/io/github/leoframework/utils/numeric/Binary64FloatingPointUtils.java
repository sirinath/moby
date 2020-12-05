package io.github.leoframework.utils.numeric;

import static io.github.leoframework.utils.numeric.Binary64FloatingPointUtils.BitExtractors.extractNaNPayload;
import static io.github.leoframework.utils.numeric.Binary64FloatingPointUtils.BitExtractors.extractSignificand;
import static io.github.leoframework.utils.numeric.Binary64FloatingPointUtils.BitMasks.*;
import static io.github.leoframework.utils.numeric.Binary64FloatingPointUtils.FormatInfo.*;
import static io.github.leoframework.utils.numeric.Integer64Utils.Constants.*;

// https://github.com/abolz/Drachennest/
// https://github.com/jk-jeon/dragonbox/
// https://github.com/miloyip/itoa-benchmark
// https://stackoverflow.com/questions/18118408/what-is-the-difference-between-quiet-nan-and-signaling-nan
public final class Binary64FloatingPointUtils {
    private Binary64FloatingPointUtils() {}
    
    public static final class FormatInfo {
        private FormatInfo() {}

        public static final long significandBits = 52;
        public static final long exponentBits = 11;
        public static final long minExponent = 1022;
        public static final long maxExponent = 1023;
        public static final long exponentBias = -1023;
        public static final long decimalDigits = 17;
        public static final long bits = 64;

        public static final long NaNPayloadPortionSize = significandBits - one;
        public static final long bitsExcludingSign = bits - one;
        public static final long halfBits = (bits >> 1);
    }

    public static final class BitMasks {
        private BitMasks() {}

        public static final long signMask = one << (bitsExcludingSign);
        public static final long exponentMask = ((one << exponentBits) - one) << significandBits;
        public static final long significandMask = (one << significandBits) - one;
        public static final long NaNPayloadPortionMask = significandMask >>> one;
        public static final long NaNErrorHandlingMask = (one << (NaNPayloadPortionSize));
        public static final long signedExponentMask = exponentMask | signMask;
        public static final long quiteNaNMask = exponentMask | NaNErrorHandlingMask;
        public static final long signedQuiteNaNMask = quiteNaNMask | signMask;
        public static final long lowMask = minusOne >>> halfBits;
        public static final long highMask = ~lowMask;
    }

    public static final class Constants {
        private Constants() {}

        public static final long POSITIVE_ZERO_BITS = zero;
        public static final long NEGATIVE_ZERO_BITS = signMask;
        public static final long POSITIVE_INFINITY_BITS = exponentMask;
        public static final long NEGATIVE_INFINITY_BITS = signedExponentMask;

        public static final long POSITIVE_QUITE_NaN_BITS = quiteNaNMask;
        public static final long POSITIVE_QUITE_NaN_BITS(final long payload) {
            return POSITIVE_QUITE_NaN_BITS | (NaNPayloadPortionMask & payload);
        }

        public static final long NEGATIVE_QUITE_NaN_BITS = signedQuiteNaNMask;
        public static final long NEGATIVE_QUITE_NaN_BITS(final long payload) {
            return NEGATIVE_QUITE_NaN_BITS | (NaNPayloadPortionMask & payload);
        }

        private static final long POSITIVE_SIGNALLING_NaN_BITS = exponentMask; // POSITIVE_INFINITY_BITS
        public static final long POSITIVE_SIGNALLING_NaN_BITS(final long payload) { // payload must be non zero
            return POSITIVE_SIGNALLING_NaN_BITS | (NaNPayloadPortionMask & payload);
        }

        private static final long NEGATIVE_SIGNALLING_NaN_BITS = signedExponentMask; // NEGATIVE_INFINITY_BITS
        public static final long NEGATIVE_SIGNALLING_NaN_BITS(final long payload) { // payload must be non zero
            return NEGATIVE_SIGNALLING_NaN_BITS | (NaNPayloadPortionMask & payload);
        }
    }

    public static final class BitExtractors {
        private BitExtractors() {}

        private static final long maskLow(final long value) {
            return value & lowMask;
        }

        public static final long maskLow(final double value) {
            return maskLow(Double.doubleToRawLongBits(value));
        }

        private static final long extractLow(final long value) {
            return maskLow(value);
        }

        public static final long extractLow(final double value) {
            return extractLow(Double.doubleToRawLongBits(value));
        }

        private static final long maskHigh(final long value) {
            return value & highMask;
        }

        public static final long maskHigh(final double value) {
            return maskHigh(Double.doubleToRawLongBits(value));
        }

        private static final long extractHigh(final long value) {
            return value >>> halfBits;
        }

        public static final long extractHigh(final double value) {
            return extractHigh(Double.doubleToRawLongBits(value));
        }

        private static final long maskExponent(final long value) {
            return value & exponentMask;
        }

        public static final long maskExponent(final double value) {
            return maskExponent(Double.doubleToRawLongBits(value));
        }

        private static final long extractExponent(final long value) {
            return maskExponent(value) >>> significandBits;
        }

        public static final long extractExponent(final double value) {
            return extractExponent(Double.doubleToRawLongBits(value));
        }

        private static final long maskSignificand(final long value) {
            return value & significandMask;
        }

        public static final long maskSignificand(final double value) {
            return maskSignificand(Double.doubleToRawLongBits(value));
        }

        private static final long extractSignificand(final long value) {
            return maskSignificand(value);
        }

        public static final long extractSignificand(final double value) {
            return extractSignificand(Double.doubleToRawLongBits(value));
        }

        private static final long maskNaNPayload(final long value) {
            return value & NaNPayloadPortionMask;
        }

        public static final long maskNaNPayload(final double value) {
            return maskNaNPayload(Double.doubleToRawLongBits(value));
        }

        private static final long extractNaNPayload(final long value) {
            return maskNaNPayload(value);
        }

        public static final long extractNaNPayload(final double value) {
            return extractNaNPayload(Double.doubleToRawLongBits(value));
        }

        private static final long maskNaNErrorHandling(final long value) {
            return value & NaNErrorHandlingMask;
        }

        public static final long maskNaNErrorHandling(final double value) {
            return maskNaNErrorHandling(Double.doubleToRawLongBits(value));
        }

        private static final long extractNaNErrorHandling(final long value) {
            return maskNaNErrorHandling(value) >>> (NaNPayloadPortionSize);
        }

        public static final long extractNaNErrorHandling(final double value) {
            return extractNaNErrorHandling(Double.doubleToRawLongBits(value));
        }

        private static final long maskSign(final long value) {
            return value & signMask;
        }

        public static final long maskSign(final double value) {
            return maskSign(Double.doubleToRawLongBits(value));
        }

        private static final long extractSign(final long value) {
            return value >>> bitsExcludingSign;
        }

        public static final long extractSign(final double value) {
            return extractSign(Double.doubleToRawLongBits(value));
        }
    }

    public static final class Sign {
        private Sign() {}

        // +ve numbers have MSB of 0, -ve number have MSB of 1

        private static boolean isPositive(final long value) {
            return value > zero;
        }

        public static boolean isPositive(final double value) {
            return isPositive(Double.doubleToRawLongBits(value));
        }

        private static boolean isNegative(final long value) {
            return value < zero;
        }

        public static boolean isNegative(final double value) {
            return isNegative(Double.doubleToRawLongBits(value));
        }
    }

    public static final class Subnormal {
        private Subnormal() {}

        private static boolean isSubnormal(final long value) {
            return (value & exponentMask) == zero;
        }

        public static boolean isSubnormal(final double value) {
            return isSubnormal(Double.doubleToRawLongBits(value));
        }

        private static boolean isNonZero(final long value) {
            return (value << 1) != zero;
        }

        public static boolean isNonZero(final double value) {
            return isNonZero(Double.doubleToRawLongBits(value));
        }

        private static boolean isZero(final long value) {
            return (value << 1) == zero;
        }

        public static boolean isZero(final double value) {
            return isZero(Double.doubleToRawLongBits(value));
        }

        private static boolean isPositiveZero(final long value) {
            return value == signMask;
        }

        public static boolean isPositiveZero(final double value) {
            return isPositiveZero(Double.doubleToRawLongBits(value));
        }

        private static boolean isNegativeZero(final long value) {
            return value == zero;
        }

        public static boolean isNegativeZero(final double value) {
            return isNegativeZero(Double.doubleToRawLongBits(value));
        }
    }

    public static final class NoneFinite {
        private NoneFinite() {
        }

        private static boolean isFinite(final long value) {
            return (value & exponentMask) != exponentMask;
        }

        public static boolean isFinite(final double value) {
            return isFinite(Double.doubleToRawLongBits(value));
        }

        private static boolean isNoneFinite(final long value) {
            return (value & exponentMask) == exponentMask;
        }

        public static boolean isNoneFinite(final double value) {
            return isNoneFinite(Double.doubleToRawLongBits(value));
        }

        // Quite bit makes the Significand non zero

        private static boolean isQuiteNaN(final long value) {
            return (value & quiteNaNMask) == quiteNaNMask;
        }

        public static boolean isQuiteNaN(final double value) {
            return isQuiteNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isQuiteNaN(final long value, final long payload) {
            return ((value & quiteNaNMask) == quiteNaNMask) && (extractNaNPayload(value) == payload);
        }

        public static boolean isQuiteNaN(final double value, final long payload) {
            return isQuiteNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isPositiveQuiteNaN(final long value) {
            return (value & signedQuiteNaNMask) == quiteNaNMask;
        }

        public static boolean isPositiveQuiteNaN(final double value) {
            return isPositiveQuiteNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isPositiveQuiteNaN(final long value, final long payload) {
            return ((value & signedQuiteNaNMask) == quiteNaNMask) && (extractNaNPayload(value) == payload);
        }

        public static boolean isPositiveQuiteNaN(final double value, final long payload) {
            return isPositiveQuiteNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isNegativeQuiteNaN(final long value) {
            return (value & signedQuiteNaNMask) == signedQuiteNaNMask;
        }

        public static boolean isNegativeQuiteNaN(final double value) {
            return isNegativeQuiteNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isNegativeQuiteNaN(final long value, final long payload) {
            return ((value & signedQuiteNaNMask) == signedQuiteNaNMask) && (extractNaNPayload(value) == payload);
        }

        public static boolean isNegativeQuiteNaN(final double value, final long payload) {
            return isNegativeQuiteNaN(Double.doubleToRawLongBits(value), payload);
        }

        // If payload is zero in a signalling NaN then this is same as Inf rules.
        // To avoid it check if payload is +ve.

        private static boolean isSignallingNaN(final long value) {
            return ((value & quiteNaNMask) == exponentMask) && (extractNaNPayload(value) != zero);
        }

        public static boolean isSignallingNaN(final double value) {
            return isSignallingNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isSignallingNaN(final long value, final long payload) {
            return ((value & quiteNaNMask) == exponentMask) && (extractNaNPayload(value) == payload) && (payload != zero);
        }

        public static boolean isSignallingNaN(final double value, final long payload) {
            return isSignallingNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isPositiveSignallingNaN(final long value) {
            return ((value & signedQuiteNaNMask) == exponentMask) && (extractNaNPayload(value) != zero);
        }

        public static boolean isPositiveSignallingNaN(final double value) {
            return isPositiveSignallingNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isPositiveSignallingNaN(final long value, final long payload) {
            return ((value & signedQuiteNaNMask) == exponentMask) && (extractNaNPayload(value) == payload) && (payload != zero);
        }

        public static boolean isPositiveSignallingNaN(final double value, final long payload) {
            return isPositiveSignallingNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isNegativeSignallingNaN(final long value) {
            return ((value & signedQuiteNaNMask) == signedExponentMask) && (extractNaNPayload(value) != zero);
        }

        public static boolean isNegativeSignallingNaN(final double value) {
            return isNegativeSignallingNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isNegativeSignallingNaN(final long value, final long payload) {
            return ((value & signedQuiteNaNMask) == signedExponentMask) && (extractNaNPayload(value) == payload) && (payload != zero);
        }

        public static boolean isNegativeSignallingNaN(final double value, final long payload) {
            return isNegativeSignallingNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isNaN(final long value) {
            return ((value & exponentMask) == exponentMask) && (extractSignificand(value) != zero);
        }

        public static boolean isNaN(final double value) {
            return isNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isNaN(final long value, final long payload) {
            return ((value & exponentMask) == exponentMask) && (extractSignificand(value) != zero) && (extractNaNPayload(value) == payload);
        }

        public static boolean isNaN(final double value, final long payload) {
            return isNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isPositiveNaN(final long value) {
            return ((value & signedExponentMask) == exponentMask) && (extractSignificand(value) != zero);
        }

        public static boolean isPositiveNaN(final double value) {
            return isPositiveNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isPositiveNaN(final long value, final long payload) {
            return ((value & signedExponentMask) == exponentMask) && (extractSignificand(value) != zero) && (extractNaNPayload(value) == payload);
        }

        public static boolean isPositiveNaN(final double value, final long payload) {
            return isPositiveNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isNegativeNaN(final long value) {
            return ((value & signedExponentMask) == signedExponentMask) && (extractSignificand(value) != zero);
        }

        public static boolean isNegativeNaN(final double value) {
            return isNegativeNaN(Double.doubleToRawLongBits(value));
        }

        private static boolean isNegativeNaN(final long value, final long payload) {
            return ((value & signedExponentMask) == signedExponentMask) && (extractSignificand(value) != zero) && (extractNaNPayload(value) == payload);
        }

        public static boolean isNegativeNaN(final double value, final long payload) {
            return isNegativeNaN(Double.doubleToRawLongBits(value), payload);
        }

        private static boolean isPositiveInf(final long value) {
            return value == exponentMask;
        }

        public static boolean isPositiveInf(final double value) {
            return isPositiveInf(Double.doubleToRawLongBits(value));
        }

        private static boolean isNegativeInf(final long value) {
            return value == signedExponentMask;
        }

        public static boolean isNegativeInf(final double value) {
            return isNegativeInf(Double.doubleToRawLongBits(value));
        }

        private static boolean isInf(final long value) {
            return isPositiveInf(value) || isNegativeInf(value);
        }

        public static boolean isInf(final double value) {
            return isInf(Double.doubleToRawLongBits(value));
        }
    }

}