package com.sirinath.utils.numeric.dragonbox;

import com.sirinath.utils.numeric.Binary64FloatingPointUtils.FormatInfo;

import static com.sirinath.utils.numeric.Integer64Utils.BitExtractors.bitWidthMast;
import static com.sirinath.utils.numeric.Integer64Utils.BitExtractors.extractLow;
import static com.sirinath.utils.numeric.Integer64Utils.FormatInfo.valueBits;

// https://github.com/jk-jeon/dragonbox
// https://github.com/jk-jeon/fp
// https://github.com/abolz/Drachennest
public final class DragonBox64 {
   public static final class Log {
      public  static final long log10_2_FractionalDigits = 0x4d10_4d42_7de7_fbccL;
      public static final long log10_4_Over_3_FractionalDigits = 0x1ffb_fc2b_bc78_0375L;
      public static final long floorLog10PowerOf2ShiftAmount = 22;

      public static final long log10_5_FractionalDigits = 0xb2ef_b2bd_8218_0433L;
      public static final long floorLog10PowerOf5ShiftAmount = 20;

      public static final long log2_10_FractionalDigits = 0x5269_e12f_346e_2bf9L;
      public static final long floorLog2PowerOf10ShiftAmount = 19;

      public static final long log5_2_FractionalDigits = 0x6e40_d1a4_143d_cb94L;
      public static final long log5_3_FractionalDigits = 0xaebf_4791_5d44_3b24L;
      public static final long floorLog5PowerOf2ShiftAmount = 20;

      public static long floorShift(final long integerPart, final long fractionalDigits, final long shiftAmount) {
         return ((integerPart << shiftAmount) | (fractionalDigits >>> (Long.SIZE - shiftAmount)));
      }

      public static long floorShift(final long fractionalDigits, final long shiftAmount) {
         return (fractionalDigits >>> (Long.SIZE - shiftAmount));
      }

      public  static long floorLog2(final long n) {
         return 63 - Long.numberOfLeadingZeros(n);
      }

      // Computes floor(e * c - s)
      public static long compute(final long cIntegerPart, final long cFractionalDigits,
                                 final long shiftAmount, final long sIntegerPart,
                                 final long sFractionalDigits, final long e) {
         final long c = floorShift(cIntegerPart, cFractionalDigits, shiftAmount);
         final long s = floorShift(sIntegerPart, sFractionalDigits, shiftAmount);

         return (e * c - s) >>> shiftAmount;
      }

      public static long compute(final long cIntegerPart, final long cFractionalDigits,
                                 final long shiftAmount, final long e) {
         final long c = floorShift(cIntegerPart, cFractionalDigits, shiftAmount);

         return (e * c) >>> shiftAmount;
      }

      public static long computeFractional(final long cFractionalDigits,
                                 final long shiftAmount,
                                 final long sFractionalDigits, final long e) {
         final long c = floorShift(cFractionalDigits, shiftAmount);
         final long s = floorShift(sFractionalDigits, shiftAmount);

         return (e * c - s) >>> shiftAmount;
      }

      public static long computeFractional(final long cFractionalDigits,
                                 final long shiftAmount, final long e) {
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
         return computeFractional(log5_2_FractionalDigits, floorLog5PowerOf2ShiftAmount,
                                  log5_3_FractionalDigits, e);
      }

      public static long floorLog10PowerOf2MinusLog10_4_Over_3(final long e) {
         return computeFractional(log10_2_FractionalDigits, floorLog10PowerOf2ShiftAmount,
                                  log10_4_Over_3_FractionalDigits, e);
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

      public static final long A                  = 5;
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

      private Div() {}
   }

   public static final long kappa = 2;

   private DragonBox64() {}
}
