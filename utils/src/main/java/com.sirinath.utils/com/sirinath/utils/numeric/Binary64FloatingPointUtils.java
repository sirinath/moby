package com.sirinath.utils.numeric;

import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.BitExtractors.extractNaNPayload;
import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.BitExtractors.extractSignificand;
import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.BitMasks.*;
import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.FormatInfo.*;

// https://github.com/abolz/Drachennest/
// https://github.com/jk-jeon/dragonbox/
// https://github.com/miloyip/itoa-benchmark
// https://stackoverflow.com/questions/18118408/what-is-the-difference-between-quiet-nan-and-signaling-nan
public final class Binary64FloatingPointUtils {
   private Binary64FloatingPointUtils() {}

   public static final class FormatInfo {
      public static final long significandBits       = 52;
      public static final long exponentBits          = 11;
      public static final long minExponent           = 1022;
      public static final long maxExponent           = 1023;
      public static final long exponentBias          = -1023;
      public static final long decimalDigits         = 17;
      public static final long bits                  = 64;
      public static final long NaNPayloadPortionSize = significandBits - Integer64Utils.Constants.one;
      public static final long bitsExcludingSign     = bits - Integer64Utils.Constants.one;
      public static final long halfBits              = (bits >> 1);

      private FormatInfo() {}
   }

   public static final class BitMasks {
      public static final long signMask              = Integer64Utils.Constants.one << (bitsExcludingSign);
      public static final long exponentMask          =
            ((Integer64Utils.Constants.one << exponentBits) - Integer64Utils.Constants.one) << significandBits;
      public static final long significandMask       =
            (Integer64Utils.Constants.one << significandBits) - Integer64Utils.Constants.one;
      public static final long NaNPayloadPortionMask = significandMask >>> Integer64Utils.Constants.one;
      public static final long NaNErrorHandlingMask  = (Integer64Utils.Constants.one << (NaNPayloadPortionSize));
      public static final long signedExponentMask    = exponentMask | signMask;
      public static final long quiteNaNMask          = exponentMask | NaNErrorHandlingMask;
      public static final long signedQuiteNaNMask    = quiteNaNMask | signMask;
      public static final long lowMask               = Integer64Utils.Constants.minusOne >>> halfBits;
      public static final long highMask              = ~lowMask;

      private BitMasks() {}
   }

   public static final class Constants {
      public static final  long POSITIVE_ZERO_BITS           = Integer64Utils.Constants.zero;
      public static final  long NEGATIVE_ZERO_BITS           = signMask;
      public static final  long POSITIVE_INFINITY_BITS       = exponentMask;
      public static final  long NEGATIVE_INFINITY_BITS       = signedExponentMask;
      public static final  long POSITIVE_QUITE_NaN_BITS      = quiteNaNMask;
      public static final  long NEGATIVE_QUITE_NaN_BITS      = signedQuiteNaNMask;
      private static final long POSITIVE_SIGNALLING_NaN_BITS = exponentMask; // POSITIVE_INFINITY_BITS
      private static final long NEGATIVE_SIGNALLING_NaN_BITS = signedExponentMask; // NEGATIVE_INFINITY_BITS

      private Constants() {}

      public static long POSITIVE_QUITE_NaN_BITS(final long payload) {
         return POSITIVE_QUITE_NaN_BITS | (NaNPayloadPortionMask & payload);
      }

      public static long NEGATIVE_QUITE_NaN_BITS(final long payload) {
         return NEGATIVE_QUITE_NaN_BITS | (NaNPayloadPortionMask & payload);
      }

      public static long POSITIVE_SIGNALLING_NaN_BITS(final long payload) { // payload must be non zero
         return POSITIVE_SIGNALLING_NaN_BITS | (NaNPayloadPortionMask & payload);
      }

      public static long NEGATIVE_SIGNALLING_NaN_BITS(final long payload) { // payload must be non zero
         return NEGATIVE_SIGNALLING_NaN_BITS | (NaNPayloadPortionMask & payload);
      }
   }

   public static final class BitExtractors {
      private BitExtractors() {}

      public static long maskLow(final double value) {
         return maskLow(Double.doubleToRawLongBits(value));
      }

      private static long maskLow(final long value) {
         return value & lowMask;
      }

      public static long extractLow(final double value) {
         return extractLow(Double.doubleToRawLongBits(value));
      }

      private static long extractLow(final long value) {
         return maskLow(value);
      }

      public static long maskHigh(final double value) {
         return maskHigh(Double.doubleToRawLongBits(value));
      }

      private static long maskHigh(final long value) {
         return value & highMask;
      }

      public static long extractHigh(final double value) {
         return extractHigh(Double.doubleToRawLongBits(value));
      }

      private static long extractHigh(final long value) {
         return value >>> halfBits;
      }

      public static long maskExponent(final double value) {
         return maskExponent(Double.doubleToRawLongBits(value));
      }

      private static long maskExponent(final long value) {
         return value & exponentMask;
      }

      public static long extractExponent(final double value) {
         return extractExponent(Double.doubleToRawLongBits(value));
      }

      private static long extractExponent(final long value) {
         return maskExponent(value) >>> significandBits;
      }

      public static long maskSignificand(final double value) {
         return maskSignificand(Double.doubleToRawLongBits(value));
      }

      private static long maskSignificand(final long value) {
         return value & significandMask;
      }

      public static long extractSignificand(final double value) {
         return extractSignificand(Double.doubleToRawLongBits(value));
      }

      private static long extractSignificand(final long value) {
         return maskSignificand(value);
      }

      public static long maskNaNPayload(final double value) {
         return maskNaNPayload(Double.doubleToRawLongBits(value));
      }

      private static long maskNaNPayload(final long value) {
         return value & NaNPayloadPortionMask;
      }

      public static long extractNaNPayload(final double value) {
         return extractNaNPayload(Double.doubleToRawLongBits(value));
      }

      private static long extractNaNPayload(final long value) {
         return maskNaNPayload(value);
      }

      public static long maskNaNErrorHandling(final double value) {
         return maskNaNErrorHandling(Double.doubleToRawLongBits(value));
      }

      private static long maskNaNErrorHandling(final long value) {
         return value & NaNErrorHandlingMask;
      }

      public static long extractNaNErrorHandling(final double value) {
         return extractNaNErrorHandling(Double.doubleToRawLongBits(value));
      }

      private static long extractNaNErrorHandling(final long value) {
         return maskNaNErrorHandling(value) >>> (NaNPayloadPortionSize);
      }

      public static long maskSign(final double value) {
         return maskSign(Double.doubleToRawLongBits(value));
      }

      private static long maskSign(final long value) {
         return value & signMask;
      }

      public static long extractSign(final double value) {
         return extractSign(Double.doubleToRawLongBits(value));
      }

      private static long extractSign(final long value) {
         return value >>> bitsExcludingSign;
      }
   }

   public static final class Sign {
      private Sign() {}

      // +ve numbers have MSB of 0, -ve number have MSB of 1

      public static boolean isPositive(final double value) {
         return isPositive(Double.doubleToRawLongBits(value));
      }

      private static boolean isPositive(final long value) {
         return value > Integer64Utils.Constants.zero;
      }

      public static boolean isNegative(final double value) {
         return isNegative(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegative(final long value) {
         return value < Integer64Utils.Constants.zero;
      }
   }

   public static final class Subnormal {
      private Subnormal() {}

      public static boolean isSubnormal(final double value) {
         return isSubnormal(Double.doubleToRawLongBits(value));
      }

      private static boolean isSubnormal(final long value) {
         return (value & exponentMask) == Integer64Utils.Constants.zero;
      }

      public static boolean isNonZero(final double value) {
         return isNonZero(Double.doubleToRawLongBits(value));
      }

      private static boolean isNonZero(final long value) {
         return (value << 1) != Integer64Utils.Constants.zero;
      }

      public static boolean isZero(final double value) {
         return isZero(Double.doubleToRawLongBits(value));
      }

      private static boolean isZero(final long value) {
         return (value << 1) == Integer64Utils.Constants.zero;
      }

      public static boolean isPositiveZero(final double value) {
         return isPositiveZero(Double.doubleToRawLongBits(value));
      }

      private static boolean isPositiveZero(final long value) {
         return value == signMask;
      }

      public static boolean isNegativeZero(final double value) {
         return isNegativeZero(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegativeZero(final long value) {
         return value == Integer64Utils.Constants.zero;
      }
   }

   public static final class NoneFinite {
      private NoneFinite() {
      }

      public static boolean isFinite(final double value) {
         return isFinite(Double.doubleToRawLongBits(value));
      }

      private static boolean isFinite(final long value) {
         return (value & exponentMask) != exponentMask;
      }

      public static boolean isNoneFinite(final double value) {
         return isNoneFinite(Double.doubleToRawLongBits(value));
      }

      private static boolean isNoneFinite(final long value) {
         return (value & exponentMask) == exponentMask;
      }

      // Quite bit makes the Significand non zero

      public static boolean isQuiteNaN(final double value) {
         return isQuiteNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isQuiteNaN(final long value) {
         return (value & quiteNaNMask) == quiteNaNMask;
      }

      public static boolean isQuiteNaN(final double value, final long payload) {
         return isQuiteNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isQuiteNaN(final long value, final long payload) {
         return ((value & quiteNaNMask) == quiteNaNMask) && (extractNaNPayload(value) == payload);
      }

      public static boolean isPositiveQuiteNaN(final double value) {
         return isPositiveQuiteNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isPositiveQuiteNaN(final long value) {
         return (value & signedQuiteNaNMask) == quiteNaNMask;
      }

      public static boolean isPositiveQuiteNaN(final double value, final long payload) {
         return isPositiveQuiteNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isPositiveQuiteNaN(final long value, final long payload) {
         return ((value & signedQuiteNaNMask) == quiteNaNMask) && (extractNaNPayload(value) == payload);
      }

      public static boolean isNegativeQuiteNaN(final double value) {
         return isNegativeQuiteNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegativeQuiteNaN(final long value) {
         return (value & signedQuiteNaNMask) == signedQuiteNaNMask;
      }

      public static boolean isNegativeQuiteNaN(final double value, final long payload) {
         return isNegativeQuiteNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isNegativeQuiteNaN(final long value, final long payload) {
         return ((value & signedQuiteNaNMask) == signedQuiteNaNMask) && (extractNaNPayload(value) == payload);
      }

      // If payload is zero in a signalling NaN then this is same as Inf rules.
      // To avoid it check if payload is +ve.

      public static boolean isSignallingNaN(final double value) {
         return isSignallingNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isSignallingNaN(final long value) {
         return ((value & quiteNaNMask) == exponentMask) && (extractNaNPayload(value) != Integer64Utils.Constants.zero);
      }

      public static boolean isSignallingNaN(final double value, final long payload) {
         return isSignallingNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isSignallingNaN(final long value, final long payload) {
         return ((value & quiteNaNMask) == exponentMask) && (extractNaNPayload(value) == payload) &&
                (payload != Integer64Utils.Constants.zero);
      }

      public static boolean isPositiveSignallingNaN(final double value) {
         return isPositiveSignallingNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isPositiveSignallingNaN(final long value) {
         return ((value & signedQuiteNaNMask) == exponentMask) &&
                (extractNaNPayload(value) != Integer64Utils.Constants.zero);
      }

      public static boolean isPositiveSignallingNaN(final double value, final long payload) {
         return isPositiveSignallingNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isPositiveSignallingNaN(final long value, final long payload) {
         return ((value & signedQuiteNaNMask) == exponentMask) && (extractNaNPayload(value) == payload) &&
                (payload != Integer64Utils.Constants.zero);
      }

      public static boolean isNegativeSignallingNaN(final double value) {
         return isNegativeSignallingNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegativeSignallingNaN(final long value) {
         return ((value & signedQuiteNaNMask) == signedExponentMask) &&
                (extractNaNPayload(value) != Integer64Utils.Constants.zero);
      }

      public static boolean isNegativeSignallingNaN(final double value, final long payload) {
         return isNegativeSignallingNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isNegativeSignallingNaN(final long value, final long payload) {
         return ((value & signedQuiteNaNMask) == signedExponentMask) && (extractNaNPayload(value) == payload) &&
                (payload != Integer64Utils.Constants.zero);
      }

      public static boolean isNaN(final double value) {
         return isNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isNaN(final long value) {
         return ((value & exponentMask) == exponentMask) &&
                (extractSignificand(value) != Integer64Utils.Constants.zero);
      }

      public static boolean isNaN(final double value, final long payload) {
         return isNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isNaN(final long value, final long payload) {
         return ((value & exponentMask) == exponentMask) &&
                (extractSignificand(value) != Integer64Utils.Constants.zero) && (extractNaNPayload(value) == payload);
      }

      public static boolean isPositiveNaN(final double value) {
         return isPositiveNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isPositiveNaN(final long value) {
         return ((value & signedExponentMask) == exponentMask) &&
                (extractSignificand(value) != Integer64Utils.Constants.zero);
      }

      public static boolean isPositiveNaN(final double value, final long payload) {
         return isPositiveNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isPositiveNaN(final long value, final long payload) {
         return ((value & signedExponentMask) == exponentMask) &&
                (extractSignificand(value) != Integer64Utils.Constants.zero) && (extractNaNPayload(value) == payload);
      }

      public static boolean isNegativeNaN(final double value) {
         return isNegativeNaN(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegativeNaN(final long value) {
         return ((value & signedExponentMask) == signedExponentMask) &&
                (extractSignificand(value) != Integer64Utils.Constants.zero);
      }

      public static boolean isNegativeNaN(final double value, final long payload) {
         return isNegativeNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isNegativeNaN(final long value, final long payload) {
         return ((value & signedExponentMask) == signedExponentMask) &&
                (extractSignificand(value) != Integer64Utils.Constants.zero) && (extractNaNPayload(value) == payload);
      }

      public static boolean isPositiveInf(final double value) {
         return isPositiveInf(Double.doubleToRawLongBits(value));
      }

      private static boolean isPositiveInf(final long value) {
         return value == exponentMask;
      }

      public static boolean isNegativeInf(final double value) {
         return isNegativeInf(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegativeInf(final long value) {
         return value == signedExponentMask;
      }

      public static boolean isInf(final double value) {
         return isInf(Double.doubleToRawLongBits(value));
      }

      private static boolean isInf(final long value) {
         return isPositiveInf(value) || isNegativeInf(value);
      }
   }

}