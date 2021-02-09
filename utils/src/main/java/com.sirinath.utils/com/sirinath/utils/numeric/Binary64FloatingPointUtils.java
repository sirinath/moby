package com.sirinath.utils.numeric;

import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.BitExtractors.extractNaNPayload;
import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.BitExtractors.extractSignificand;
import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.BitMasks.*;
import static com.sirinath.utils.numeric.Binary64FloatingPointUtils.FormatInfo.*;

public final class Binary64FloatingPointUtils {
   public static final class BitExtractors {
      private BitExtractors() {}

      public static long extractExponent(final double value) {
         return extractExponent(Double.doubleToRawLongBits(value));
      }

      public static long extractHigh(final double value) {
         return extractHigh(Double.doubleToRawLongBits(value));
      }

      public static long extractLow(final double value) {
         return extractLow(Double.doubleToRawLongBits(value));
      }

      public static long extractNaNErrorHandling(final double value) {
         return extractNaNErrorHandling(Double.doubleToRawLongBits(value));
      }

      public static long extractNaNPayload(final double value) {
         return extractNaNPayload(Double.doubleToRawLongBits(value));
      }

      public static long extractSign(final double value) {
         return extractSign(Double.doubleToRawLongBits(value));
      }

      public static long extractSignificand(final double value) {
         return extractSignificand(Double.doubleToRawLongBits(value));
      }

      public static long maskExponent(final double value) {
         return maskExponent(Double.doubleToRawLongBits(value));
      }

      public static long maskHigh(final double value) {
         return maskHigh(Double.doubleToRawLongBits(value));
      }

      public static long maskLow(final double value) {
         return maskLow(Double.doubleToRawLongBits(value));
      }

      public static long maskNaNErrorHandling(final double value) {
         return maskNaNErrorHandling(Double.doubleToRawLongBits(value));
      }

      public static long maskNaNPayload(final double value) {
         return maskNaNPayload(Double.doubleToRawLongBits(value));
      }

      public static long maskSign(final double value) {
         return maskSign(Double.doubleToRawLongBits(value));
      }

      public static long maskSignificand(final double value) {
         return maskSignificand(Double.doubleToRawLongBits(value));
      }

      private static long extractExponent(final long value) {
         return maskExponent(value) >>> SIGNIFICAND_BITS;
      }

      private static long extractHigh(final long value) {
         return value >>> HALF_SIZE;
      }

      private static long extractLow(final long value) {
         return maskLow(value);
      }

      private static long extractNaNErrorHandling(final long value) {
         return maskNaNErrorHandling(value) >>> (NAN_PAYLOAD_PORTION_SIZE);
      }

      private static long extractNaNPayload(final long value) {
         return maskNaNPayload(value);
      }

      private static long extractSign(final long value) {
         return value >>> BITS_EXCLUDING_SIGN;
      }

      private static long extractSignificand(final long value) {
         return maskSignificand(value);
      }

      private static long maskExponent(final long value) {
         return value & EXPONENT_MASK;
      }

      private static long maskHigh(final long value) {
         return value & HIGH_MASK;
      }

      private static long maskLow(final long value) {
         return value & LOW_MASK;
      }

      private static long maskNaNErrorHandling(final long value) {
         return value & NAN_ERROR_HANDLING_MASK;
      }

      private static long maskNaNPayload(final long value) {
         return value & NAN_PAYLOAD_PORTION_MASK;
      }

      private static long maskSign(final long value) {
         return value & SIGN_MASK;
      }

      private static long maskSignificand(final long value) {
         return value & SIGNIFICAND_MASK;
      }
   }

   public static final class BitMasks {
      public static final long NAN_ERROR_HANDLING_MASK  = (1L << (NAN_PAYLOAD_PORTION_SIZE));
      public static final long EXPONENT_MASK            = ((1L << EXPONENT_BITS) - 1L) << SIGNIFICAND_BITS;
      public static final long LOW_MASK                 = -1L >>> HALF_SIZE;
      public static final long HIGH_MASK                = ~LOW_MASK;
      public static final long QUITE_NAN_MASK           = EXPONENT_MASK | NAN_ERROR_HANDLING_MASK;
      public static final long SIGN_MASK                = 1L << (BITS_EXCLUDING_SIGN);
      public static final long SIGNED_EXPONENT_MASK     = EXPONENT_MASK | SIGN_MASK;
      public static final long SIGNED_QUITE_NAN_MASK    = QUITE_NAN_MASK | SIGN_MASK;
      public static final long SIGNIFICAND_MASK         = (1L << SIGNIFICAND_BITS) - 1L;
      public static final long NAN_PAYLOAD_PORTION_MASK = SIGNIFICAND_MASK >>> 1L;
      public static final long HIDDEN_MASK              = NAN_ERROR_HANDLING_MASK;

      private BitMasks() {}
   }

   public static final class Constants {
      public static final  long NEGATIVE_INFINITY_BITS       = SIGNED_EXPONENT_MASK;
      public static final  long NEGATIVE_QUITE_NAN_BITS      = SIGNED_QUITE_NAN_MASK;
      public static final  long NEGATIVE_ZERO_BITS           = SIGN_MASK;
      public static final  long POSITIVE_INFINITY_BITS       = EXPONENT_MASK;
      public static final  long POSITIVE_QUITE_NAN_BITS      = QUITE_NAN_MASK;
      public static final  long POSITIVE_ZERO_BITS           = 0L;
      private static final long NEGATIVE_SIGNALLING_NAN_BITS = SIGNED_EXPONENT_MASK; // NEGATIVE_INFINITY_BITS
      private static final long POSITIVE_SIGNALLING_NAN_BITS = EXPONENT_MASK; // POSITIVE_INFINITY_BITS
      private static final long HIDDEN_BIT                   = HIDDEN_MASK;

      private Constants() {}

      public static long NEGATIVE_QUITE_NaN_BITS(final long payload) {
         return NEGATIVE_QUITE_NAN_BITS | (NAN_PAYLOAD_PORTION_MASK & payload);
      }

      public static long NEGATIVE_SIGNALLING_NaN_BITS(final long payload) { // payload must be non zero
         return NEGATIVE_SIGNALLING_NAN_BITS | (NAN_PAYLOAD_PORTION_MASK & payload);
      }

      public static long POSITIVE_QUITE_NaN_BITS(final long payload) {
         return POSITIVE_QUITE_NAN_BITS | (NAN_PAYLOAD_PORTION_MASK & payload);
      }

      public static long POSITIVE_SIGNALLING_NaN_BITS(final long payload) { // payload must be non zero
         return POSITIVE_SIGNALLING_NAN_BITS | (NAN_PAYLOAD_PORTION_MASK & payload);
      }
   }

   public static final class FormatInfo {
      public static final long BITS                     = 64;
      public static final long BITS_EXCLUDING_SIGN      = BITS - 1L;
      public static final long DECIMAL_DIGITS           = 17;
      public static final long EXPONENT_BIAS            = -1023;
      public static final long EXPONENT_BITS            = 11;
      public static final long HALF_SIZE                = (BITS >> 1);
      public static final long MAX_EXPONENT             = 1023;
      public static final long MIN_EXPONENT             = 1022;
      public static final long SIGNIFICAND_BITS         = 52;
      public static final long NAN_PAYLOAD_PORTION_SIZE = SIGNIFICAND_BITS - 1L;

      private FormatInfo() {}
   }

   public static final class NoneFinite {
      private NoneFinite() {
      }

      public static boolean isFinite(final double value) {
         return isFinite(Double.doubleToRawLongBits(value));
      }

      public static boolean isInf(final double value) {
         return isInf(Double.doubleToRawLongBits(value));
      }

      public static boolean isNaN(final double value) {
         return isNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isNaN(final double value, final long payload) {
         return isNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isNegativeInf(final double value) {
         return isNegativeInf(Double.doubleToRawLongBits(value));
      }

      public static boolean isNegativeNaN(final double value) {
         return isNegativeNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isNegativeNaN(final double value, final long payload) {
         return isNegativeNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isNegativeQuiteNaN(final double value) {
         return isNegativeQuiteNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isNegativeQuiteNaN(final double value, final long payload) {
         return isNegativeQuiteNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isNegativeSignallingNaN(final double value) {
         return isNegativeSignallingNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isNegativeSignallingNaN(final double value, final long payload) {
         return isNegativeSignallingNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isNoneFinite(final double value) {
         return isNoneFinite(Double.doubleToRawLongBits(value));
      }

      public static boolean isPositiveInf(final double value) {
         return isPositiveInf(Double.doubleToRawLongBits(value));
      }

      public static boolean isPositiveNaN(final double value) {
         return isPositiveNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isPositiveNaN(final double value, final long payload) {
         return isPositiveNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isPositiveQuiteNaN(final double value) {
         return isPositiveQuiteNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isPositiveQuiteNaN(final double value, final long payload) {
         return isPositiveQuiteNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isPositiveSignallingNaN(final double value) {
         return isPositiveSignallingNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isPositiveSignallingNaN(final double value, final long payload) {
         return isPositiveSignallingNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isQuiteNaN(final double value) {
         return isQuiteNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isQuiteNaN(final double value, final long payload) {
         return isQuiteNaN(Double.doubleToRawLongBits(value), payload);
      }

      public static boolean isSignallingNaN(final double value) {
         return isSignallingNaN(Double.doubleToRawLongBits(value));
      }

      public static boolean isSignallingNaN(final double value, final long payload) {
         return isSignallingNaN(Double.doubleToRawLongBits(value), payload);
      }

      private static boolean isFinite(final long value) {
         return (value & EXPONENT_MASK) != EXPONENT_MASK;
      }

      private static boolean isInf(final long value) {
         return isPositiveInf(value) || isNegativeInf(value);
      }

      // If payload is zero in a signalling NaN then this is same as Inf rules.
      // To avoid it check if payload is non zero.
      private static boolean isNaN(final long value) {
         return ((value & EXPONENT_MASK) == EXPONENT_MASK) && (extractSignificand(value) != 0L);
      }

      private static boolean isNaN(final long value, final long payload) {
         return ((value & EXPONENT_MASK) == EXPONENT_MASK) && (extractSignificand(value) != 0L) && (extractNaNPayload(
               value) == payload);
      }

      private static boolean isNegativeInf(final long value) {
         return value == SIGNED_EXPONENT_MASK;
      }

      private static boolean isNegativeNaN(final long value) {
         return ((value & SIGNED_EXPONENT_MASK) == SIGNED_EXPONENT_MASK) && (extractSignificand(value) != 0L);
      }

      private static boolean isNegativeNaN(final long value, final long payload) {
         return ((value & SIGNED_EXPONENT_MASK) == SIGNED_EXPONENT_MASK) && (extractSignificand(value) != 0L) &&
                (extractNaNPayload(value) == payload);
      }

      private static boolean isNegativeQuiteNaN(final long value) {
         return (value & SIGNED_QUITE_NAN_MASK) == SIGNED_QUITE_NAN_MASK;
      }

      private static boolean isNegativeQuiteNaN(final long value, final long payload) {
         return ((value & SIGNED_QUITE_NAN_MASK) == SIGNED_QUITE_NAN_MASK) && (extractNaNPayload(value) == payload);
      }

      private static boolean isNegativeSignallingNaN(final long value) {
         return ((value & SIGNED_QUITE_NAN_MASK) == SIGNED_EXPONENT_MASK) && (extractNaNPayload(value) != 0L);
      }

      private static boolean isNegativeSignallingNaN(final long value, final long payload) {
         return ((value & SIGNED_QUITE_NAN_MASK) == SIGNED_EXPONENT_MASK) && (extractNaNPayload(value) == payload) &&
                (payload != 0L);
      }

      private static boolean isNoneFinite(final long value) {
         return (value & EXPONENT_MASK) == EXPONENT_MASK;
      }

      private static boolean isPositiveInf(final long value) {
         return value == EXPONENT_MASK;
      }

      private static boolean isPositiveNaN(final long value) {
         return ((value & SIGNED_EXPONENT_MASK) == EXPONENT_MASK) && (extractSignificand(value) != 0L);
      }

      private static boolean isPositiveNaN(final long value, final long payload) {
         return ((value & SIGNED_EXPONENT_MASK) == EXPONENT_MASK) && (extractSignificand(value) != 0L) &&
                (extractNaNPayload(value) == payload);
      }

      private static boolean isPositiveQuiteNaN(final long value) {
         return (value & SIGNED_QUITE_NAN_MASK) == QUITE_NAN_MASK;
      }

      private static boolean isPositiveQuiteNaN(final long value, final long payload) {
         return ((value & SIGNED_QUITE_NAN_MASK) == QUITE_NAN_MASK) && (extractNaNPayload(value) == payload);
      }

      private static boolean isPositiveSignallingNaN(final long value) {
         return ((value & SIGNED_QUITE_NAN_MASK) == EXPONENT_MASK) && (extractNaNPayload(value) != 0L);
      }

      private static boolean isPositiveSignallingNaN(final long value, final long payload) {
         return ((value & SIGNED_QUITE_NAN_MASK) == EXPONENT_MASK) && (extractNaNPayload(value) == payload) &&
                (payload != 0L);
      }

      // Quite bit makes the Significand non zero
      private static boolean isQuiteNaN(final long value) {
         return (value & QUITE_NAN_MASK) == QUITE_NAN_MASK;
      }

      private static boolean isQuiteNaN(final long value, final long payload) {
         return ((value & QUITE_NAN_MASK) == QUITE_NAN_MASK) && (extractNaNPayload(value) == payload);
      }

      private static boolean isSignallingNaN(final long value) {
         return ((value & QUITE_NAN_MASK) == EXPONENT_MASK) && (extractNaNPayload(value) != 0L);
      }

      private static boolean isSignallingNaN(final long value, final long payload) {
         return ((value & QUITE_NAN_MASK) == EXPONENT_MASK) && (extractNaNPayload(value) == payload) && (payload != 0L);
      }
   }

   public static final class Sign {
      private Sign() {}

      // +ve numbers have MSB of 0, -ve number have MSB of 1

      public static boolean isNegative(final double value) {
         return isNegative(Double.doubleToRawLongBits(value));
      }

      public static boolean isPositive(final double value) {
         return isPositive(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegative(final long value) {
         return value < 0L;
      }

      private static boolean isPositive(final long value) {
         return value > 0L;
      }
   }

   public static final class Subnormal {
      private Subnormal() {}

      public static boolean isNegativeZero(final double value) {
         return isNegativeZero(Double.doubleToRawLongBits(value));
      }

      public static boolean isNonZero(final double value) {
         return isNonZero(Double.doubleToRawLongBits(value));
      }

      public static boolean isPositiveZero(final double value) {
         return isPositiveZero(Double.doubleToRawLongBits(value));
      }

      public static boolean isSubnormal(final double value) {
         return isSubnormal(Double.doubleToRawLongBits(value));
      }

      public static boolean isZero(final double value) {
         return isZero(Double.doubleToRawLongBits(value));
      }

      private static boolean isNegativeZero(final long value) {
         return value == 0L;
      }

      private static boolean isNonZero(final long value) {
         return (value << 1) != 0L;
      }

      private static boolean isPositiveZero(final long value) {
         return value == SIGN_MASK;
      }

      private static boolean isSubnormal(final long value) {
         return (value & EXPONENT_MASK) == 0L;
      }

      private static boolean isZero(final long value) {
         return (value << 1) == 0L;
      }
   }

   private Binary64FloatingPointUtils() {}
}