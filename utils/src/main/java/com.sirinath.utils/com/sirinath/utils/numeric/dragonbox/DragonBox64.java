package com.sirinath.utils.numeric.dragonbox;

import com.sirinath.utils.numeric.Binary64FloatingPointUtils.FormatInfo;
import com.sirinath.utils.numeric.Integer64Utils;

import static com.sirinath.utils.numeric.Integer64Utils.BitExtractors.bitWidthMast;
import static com.sirinath.utils.numeric.Integer64Utils.BitExtractors.extractLow;
import static com.sirinath.utils.numeric.Integer64Utils.FormatInfo.valueBits;

// https://github.com/jk-jeon/dragonbox
// https://github.com/jk-jeon/fp
// https://github.com/abolz/Drachennest
public final class DragonBox64 {
   public static final class Policy {
      public enum PolicyTypes {
         // Determines between shortest-roundtrip/fixed-precision output.
         precision,

         // Determines between scientific/fixed-point/general output.
         outputFormat,

         // Propagates or ignores the sign.
         sign,

         // Determines what to do with trailing zeros in the output.
         trailingZero,

         // Determines the rounding mode of binary IEEE-754 encoded data.
         binaryRounding,

         // Determines the rounding mode of decimal conversion.
         decimalRounding,

         // Determines which cache table to use.
         cache,

         // Determines what to do with invalid inputs.
         inputValidation;
      }

      public enum Sign {
         ignore(PolicyTypes.sign, false), propagate(PolicyTypes.sign, true);

         private final PolicyTypes policyType;
         private final boolean     returnHasSign;

         public final PolicyTypes getPolicyType() {
            return policyType;
         }

         public final boolean returnHasSign() {
            return returnHasSign;
         }

         Sign(final PolicyTypes policyType, final boolean returnHasSign) {
            this.policyType = policyType;
            this.returnHasSign = returnHasSign;
         }
      }

      public enum TrailingZero {
         allow(PolicyTypes.trailingZero, false),
         remove(PolicyTypes.trailingZero, false),
         report(PolicyTypes.trailingZero, true);

         private final PolicyTypes policyType;
         private final boolean     reportTrailingZeros;

         public final PolicyTypes getPolicyType() {
            return policyType;
         }

         public final boolean reportTrailingZeros() {
            return reportTrailingZeros;
         }

         TrailingZero(final PolicyTypes policyType, final boolean reportTrailingZeros) {
            this.policyType = policyType;
            this.reportTrailingZeros = reportTrailingZeros;
         }
      }

      public enum BinaryRounding {
         nearestToEven(PolicyTypes.binaryRounding),
         nearestToOdd(PolicyTypes.binaryRounding),
         nearestTowardsPlusInfinity(PolicyTypes.binaryRounding),
         nearestTowardsMinusInfinity(PolicyTypes.binaryRounding),
         nearestTowardsZero(PolicyTypes.binaryRounding),
         nearestAwayFromZero(PolicyTypes.binaryRounding),
         nearestToEvenStaticBoundary(PolicyTypes.binaryRounding),
         nearestToOddStaticBoundary(PolicyTypes.binaryRounding),
         nearestTowardsPlusInfinityStaticBoundary(PolicyTypes.binaryRounding),
         nearestTowardsMinusInfinityStaticBoundary(PolicyTypes.binaryRounding),
         towardsPlusInfinity(PolicyTypes.binaryRounding),
         towardsMinusInfinity(PolicyTypes.binaryRounding),
         towardsZero(PolicyTypes.binaryRounding),
         awayFromZero(PolicyTypes.binaryRounding);

         public enum BinaryRoundingType {
            toNearest, leftCloseDirect, rightCloseDirect;
         }

         public enum IntervalType {
            symmetricBoundary(true, false, false),
            asymmetricBoundary(false, false, true),
            closed(true, true, true),
            open(true, false, false),
            leftClosedRightOpen(false, true, false),
            rightClosedLeftOpen(false, false, true);

            private final boolean symmetric;
            private final boolean leftClosed;
            private final boolean rightClosed;
            private final boolean bothClosed;

            public final boolean isSymmetric() {
               return symmetric;
            }

            public final boolean isLeftClosed() {
               return leftClosed;
            }

            public final boolean isRightClosed() {
               return rightClosed;
            }

            public final boolean isBothClosed() {
               return bothClosed;
            }

            IntervalType(final boolean symmetric, final boolean leftClosed, final boolean rightClosed) {
               this.symmetric = symmetric;
               this.leftClosed = leftClosed;
               this.rightClosed = rightClosed;
               this.bothClosed = leftClosed && rightClosed;
            }

            public final boolean includeLeftEndpoint() {
               return leftClosed;
            }

            public final boolean includeRightEndpoint() {
               return rightClosed;
            }

            public final boolean includeBothEndpoint() {
               return bothClosed;
            }
         }

         private final PolicyTypes policyType;

         public final PolicyTypes getPolicyType() {
            return policyType;
         }

         BinaryRounding(final PolicyTypes policyType) {
            this.policyType = policyType;
         }
      }

      public enum DecimalRounding {
         doNotCare, toEven, toOdd, awayFromZero, towardZero;
      }

      public enum Cache {
         fast, compact;
      }

      public enum InputValidation {
         assertFinite, doNothing;
      }
   }

   public enum DecimalFloatingPoint {
      signedWithTrailingZeros(true, true),
      unsignedWithTrailingZeros(false, true),
      signedNoTrailingZeros(true, false),
      unsignedNoTrailingZeros(false, false);

      private final boolean isSigned;
      private final boolean hasTrailingZeros;

      public final boolean isSigned() {
         return isSigned;
      }

      public final boolean hasTrailingZeros() {
         return hasTrailingZeros;
      }

      DecimalFloatingPoint(final boolean isSigned, final boolean hasTrailingZeros) {
         this.isSigned = isSigned;
         this.hasTrailingZeros = hasTrailingZeros;
      }
   }

   public static final class Log {
      public static final long log10_2_FractionalDigits        = 0x4d10_4d42_7de7_fbccL;
      public static final long log10_4_Over_3_FractionalDigits = 0x1ffb_fc2b_bc78_0375L;
      public static final long floorLog10PowerOf2ShiftAmount   = 22;

      public static final long log10_5_FractionalDigits      = 0xb2ef_b2bd_8218_0433L;
      public static final long floorLog10PowerOf5ShiftAmount = 20;

      public static final long log2_10_FractionalDigits      = 0x5269_e12f_346e_2bf9L;
      public static final long floorLog2PowerOf10ShiftAmount = 19;

      public static final long log5_2_FractionalDigits      = 0x6e40_d1a4_143d_cb94L;
      public static final long log5_3_FractionalDigits      = 0xaebf_4791_5d44_3b24L;
      public static final long floorLog5PowerOf2ShiftAmount = 20;

      public static long floorShift(final long integerPart, final long fractionalDigits, final long shiftAmount) {
         return ((integerPart << shiftAmount) | (fractionalDigits >>> (Long.SIZE - shiftAmount)));
      }

      public static long floorShift(final long fractionalDigits, final long shiftAmount) {
         return (fractionalDigits >>> (Long.SIZE - shiftAmount));
      }

      public static long floorLog2(final long n) {
         return 63 - Long.numberOfLeadingZeros(n);
      }

      // Computes floor(e * c - s)
      public static long compute(final long cIntegerPart, final long cFractionalDigits, final long shiftAmount,
                                 final long sIntegerPart, final long sFractionalDigits, final long e) {
         final long c = floorShift(cIntegerPart, cFractionalDigits, shiftAmount);
         final long s = floorShift(sIntegerPart, sFractionalDigits, shiftAmount);

         return (e * c - s) >>> shiftAmount;
      }

      public static long compute(final long cIntegerPart, final long cFractionalDigits, final long shiftAmount,
                                 final long e) {
         final long c = floorShift(cIntegerPart, cFractionalDigits, shiftAmount);

         return (e * c) >>> shiftAmount;
      }

      public static long computeFractional(final long cFractionalDigits, final long shiftAmount,
                                           final long sFractionalDigits, final long e) {
         final long c = floorShift(cFractionalDigits, shiftAmount);
         final long s = floorShift(sFractionalDigits, shiftAmount);

         return (e * c - s) >>> shiftAmount;
      }

      public static long computeFractional(final long cFractionalDigits, final long shiftAmount, final long e) {
         final long c = floorShift(cFractionalDigits, shiftAmount);

         return (e * c) >>> shiftAmount;
      }

      public static long floorLog10PowerOf2(final long e) {
         return computeFractional(log10_2_FractionalDigits, floorLog10PowerOf2ShiftAmount, e);
      }

      public static long floorLog10PowerOf5(final long e) {
         return computeFractional(log10_5_FractionalDigits, floorLog10PowerOf5ShiftAmount, e);
      }

      public static long floorLog2PowerOf5(final long e) {
         return compute(2, log2_10_FractionalDigits, floorLog2PowerOf10ShiftAmount, e);
      }

      public static long floorLog2PowerOf10(final long e) {
         return compute(3, log2_10_FractionalDigits, floorLog2PowerOf10ShiftAmount, e);
      }

      public static long floorLog5PowerOf2(final long e) {
         return computeFractional(log5_2_FractionalDigits, floorLog5PowerOf2ShiftAmount, e);
      }

      public static long floorLog5PowerOf2MinusLog5_3(final long e) {
         return computeFractional(log5_2_FractionalDigits, floorLog5PowerOf2ShiftAmount, log5_3_FractionalDigits, e);
      }

      public static long floorLog10PowerOf2MinusLog10_4_Over_3(final long e) {
         return computeFractional(log10_2_FractionalDigits,
                                  floorLog10PowerOf2ShiftAmount,
                                  log10_4_Over_3_FractionalDigits,
                                  e);
      }
   }

   public static final class Div {
      public static final class TableItem {
         private final long modularInverse;
         private final long maxQuotient;

         public final long getMaxQuotient() {
            return maxQuotient;
         }

         public final long getModularInverse() {
            return modularInverse;
         }

         public TableItem(final long modularInverse, final long maxQuotient) {
            this.modularInverse = modularInverse;
            this.maxQuotient = maxQuotient;
         }
      }

      // Replace n by floor(n / 5^N)
      // Returns true if and only if n is divisible by 5^N
      // Precondition: n <= 2 * 5^(N+1)
      public static final class DivisibilityAndDivideByPowerOf5 {
         public static final long magicNumber       = 0xa429;
         public static final long bitsForComparison = 8;
         public static final long threshold         = 0x0a;
         public static final long shiftAmount       = 20;

         public static long format(final long number) {
            return number * magicNumber;
         }

         public static boolean divisible(final long formattedNumber) {
            return extractLow(formattedNumber) <= threshold;
         }

         public static long divide(final long formattedNumber) {
            return formattedNumber >>> shiftAmount;
         }

         private DivisibilityAndDivideByPowerOf5() {}
      }

      // Compute floor(n / 10^N) for small n and N
      // Precondition: n <= 10^(N+1)
      public static final class SmallDivisionByPowerOf10 {
         public static final long magicNumber = 0xa3d8;
         public static final long shiftAmount = 22;

         public static long smallDivisionByPowOf10(final long n) {
            return (n * magicNumber) >>> shiftAmount;
         }

         private SmallDivisionByPowerOf10() {}
      }

      public static final long A                  = 5;
      public static final long N                  = kappa + 1;
      public static final int  DEFAULT_TABLE_SIZE = 24;

      public static final TableItem[] table = generateTable(A, DEFAULT_TABLE_SIZE);

      public static TableItem[] generateTable(final long a, final int n) {
         final TableItem[] table = new TableItem[n];

         final long modInverse = modularInverse(a);

         long powerOfModInverse = 1;
         long powerOfA          = 1;

         for (int i = 0; i < n; i++) {
            table[i] = new TableItem(powerOfModInverse, powerOfA);

            powerOfModInverse *= modInverse;
            powerOfA *= a;
         }

         return table;
      }

      public static long modularInverse(final long a) {
         // By Euler's theorem, a^phi(2^n) == 1 (mod 2^n),
         // where phi(2^n) = 2^(n-1), so the modular inverse of a is
         // a^(2^(n-1) - 1) = a^(1 + 2 + 2^2 + ... + 2^(n-2)).
         long modularInverse = 1;

         for (long i = 1; i < valueBits; ++i) {
            modularInverse = modularInverse * modularInverse * a;
         }

         return modularInverse;
      }

      public static long modularInverse(final long a, final long width) {
         return modularInverse(a) & bitWidthMast(width);
      }

      public static boolean divisibleByPowerOf5(final long x, final int exp) {
         final TableItem item = table[exp];
         return (x * item.getModularInverse()) <= item.getMaxQuotient();
      }

      public static boolean divisibleByPowerOf2(final long x, final int exp) {
         return x == ((x << exp) >> exp);
      }

      public static boolean check64BitDivisionBy1000(final long maxPowerOf2, final long maxPowerOf5, final long n) {
         return (maxPowerOf2 + (Log.floorLog2PowerOf10(N + maxPowerOf5) - (N + maxPowerOf5))) < 70;
      }

      public static long x64BitDivisionBy1000(final long maxPowerOf2, final long maxPowerOf5, final long n) {
         return Math.multiplyHigh(n, 0x8312_6e97_8d4f_df3cL) >>> 9;
      }

      // Compute floor(n / 10^N) for small N
      // Precondition: n <= 2^a * 5^b (a = max_pow2, b = max_pow5)
      // N = kappa + 1 == 3
      public static long divideByPowerOf10(final long maxPowerOf2, final long maxPowerOf5, final long n) {
         final long divisor = computePowerKappaPlus1(10);
         return n / divisor;
      }

      public static long computePowerKappa(final long exp) {
         return Integer64Utils.Math.integerPower(kappa, exp);
      }

      public static long computePowerKappaPlus1(final long exp) {
         return Integer64Utils.Math.integerPower(N, exp);
      }

      private Div() {}
   }

   public static final long kappa = 2;
   public static final long minK  = minK();
   public static final long maxK  = maxK();

   private static long minK() {
      final long x = FormatInfo.maxExponent - FormatInfo.significandBits;
      final long a = -Log.floorLog10PowerOf2MinusLog10_4_Over_3(x);
      final long b = -Log.floorLog10PowerOf2(x) + kappa;

      return a < b ? a : b;
   }

   private static long maxK() {
      final long x = FormatInfo.maxExponent - FormatInfo.significandBits;
      final long a = -Log.floorLog10PowerOf2MinusLog10_4_Over_3(x);
      final long b = -Log.floorLog10PowerOf2(x) + kappa;

      return a > b ? a : b;
   }

   private DragonBox64() {}
}
