package com.sirinath.utils.numeric;

import com.sirinath.utils.cache.SimpleThreadLocalCache;

import static com.sirinath.utils.numeric.Integer64Utils.BitExtractors.extractHigh;
import static com.sirinath.utils.numeric.Integer64Utils.BitExtractors.extractLow;
import static com.sirinath.utils.numeric.Integer64Utils.BitMasks.*;
import static com.sirinath.utils.numeric.Integer64Utils.FormatInfo.*;

public final class Integer64Utils {
   public static final class BitExtractors {
      public static long extractHigh(final long value) {
         return value >>> HALF_SIZE;
      }

      public static long extractLow(final long value) {
         return maskLow(value);
      }

      public static long extractSign(final long value) {
         return value >>> valueBits;
      }

      public static long maskHigh(final long value) {
         return value & HIGH_MASK;
      }

      public static long maskLow(final long value) {
         return value & LOW_MASK;
      }

      public static long maskSign(final long value) {
         return value & SIGN_MASK;
      }

      public static long bitWidthMast(final long width) {
         return ((1L << width) - 1L);
      }

      public static long bitWidthMast(final long width, final long offset) {
         return bitWidthMast(width) << offset;
      }

      private BitExtractors() {}
   }

   public static final class BitMasks {
      public static final long LOW_MASK  = -1L >>> HALF_SIZE;
      public static final long HIGH_MASK = ~LOW_MASK;
      public static final long SIGN_MASK = 1L << (valueBits);

      private BitMasks() {}
   }

   public static final class Bits {
      private static final long h                     = 0x01010101; //the sum of 256 to the power of 0,1,2,3...
      // https://en.wikipedia.org/wiki/Hamming_weight
      // types and constants used in the functions below
      private static final long m1                    = 0x55555555; //binary: 0101...
      private static final long m2                    = 0x33333333; //binary: 00110011..
      private static final long m4                    = 0x0f0f0f0f; //binary:  4 zeros,  4 ones ...
      private static       long longBitsMinusByteBits = BITS - Byte.SIZE;

      // https://en.wikipedia.org/wiki/Hamming_weight
      //This uses fewer arithmetic operations than any other known
      //implementation on machines with fast multiplication.
      //This algorithm uses 12 arithmetic operations, one of which is a multiply.
      public static long countOnes(long x) {
         x -= (x >> 1) & m1;             //put count of each 2 bits into those 2 bits
         x = (x & m2) + ((x >> 2) & m2); //put count of each 4 bits into those 4 bits
         x = (x + (x >> 4)) & m4;        //put count of each 8 bits into those 8 bits
         return (x * h) >> longBitsMinusByteBits; //returns left 8 bits of x + (x<<8) + (x<<16) + (x<<24) + ...
      }

      public static long countOnesLeft(final long x) {
         return countZerosLeft(~x);
      }

      public static long countOnesRight(final long x) {
         return countZerosLeft(~x);
      }

      public static long countZeros(final long x) {
         return countOnes(~x);
      }

      public static long countZerosLeft(final long x) {
         long count;
         long half = extractHigh(x);

         if (half != 0) {
            count = HALF_SIZE_MINUS_ONE;
         } else {
            half = extractLow(x);

            if (half != 0) {
               count = valueBits;
            } else {
               count = BITS;
            }
         }

         if ((half & 0xffff0000) != 0) count -= 16;
         if ((half & 0xff00ff00) != 0) count -= 8;
         if ((half & 0xf0f0f0f0) != 0) count -= 4;
         if ((half & 0xcccccccc) != 0) count -= 2;
         if ((half & 0xaaaaaaaa) != 0) count -= 1;

         return count;
      }

      public static long countZerosRight(final long x) {
         long count;
         long half = extractLow(x);

         if (half != 0) {
            count = HALF_SIZE_MINUS_ONE;
         } else {
            half = extractHigh(x);

            if (half != 0) {
               count = valueBits;
            } else {
               count = BITS;
            }
         }

         if ((half & 0x0000ffff) != 0) count -= 16;
         if ((half & 0x00ff00ff) != 0) count -= 8;
         if ((half & 0x0f0f0f0f) != 0) count -= 4;
         if ((half & 0x33333333) != 0) count -= 2;
         if ((half & 0x55555555) != 0) count -= 1;

         return count;
      }
   }

   public static final class FormatInfo {
      public static final long BITS                = 64;
      public static final long HALF_SIZE           = (BITS >> 1);
      public static final long HALF_SIZE_MINUS_ONE = HALF_SIZE - 1L;
      public static final long valueBits           = BITS - 1L;

      private FormatInfo() {}
   }

   public static final class Math {
      private Math() {}

      public static long countFactors(long number, final long factor) {
         long count;
         for (count = 0; number % factor == 0; count++)
            number /= factor;

         return count;
      }

      public static long integerPower(long base, long exp) {
         long result = 1;

         for (; ; ) {
            if ((exp & 1) != 0) result *= base;

            exp >>= 1;

            if (exp == 0) break;

            base *= base;
         }

         return result;
      }

      // https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
      public static long modInverse(final long a, final long n) {
         long old_Bezout_t  = 0;
         long old_remainder = n;

         long new_Bezout_t  = 1;
         long new_remainder = a;

         while (new_remainder != 0) {
            final long quotient = old_remainder / new_remainder;

            old_Bezout_t = new_Bezout_t;
            new_Bezout_t = old_Bezout_t - quotient * new_Bezout_t;

            old_remainder = new_remainder;
            new_remainder = old_remainder - quotient * new_remainder;
         }

         // old_remainder > 0 then a is not invertible

         if (old_remainder < 0) old_Bezout_t = old_Bezout_t + n;

         return old_Bezout_t;
      }

      public static boolean divisibleByPowerOf2(final long value, final long powOf2) {
         if (powOf2 > BITS) {
            return false;
         } else {
            return value == ((value >>> powOf2) << powOf2);
         }
      }

      public static long floorDivPowOf2(final long value, final long powOf2) {
         return value >>> powOf2;
      }

      // compute the next highest power of 2 of 64-bit n
      public static long nextPowerOf2(long n) {
         // decrement n (to handle cases when n itself is a power of 2)
         n--;

         // Set all bits after the last set bit
         n |= n >> 1;
         n |= n >> 2;
         n |= n >> 4;
         n |= n >> 8;
         n |= n >> 16;
         n |= n >> 32;

         // increment n and return
         return ++n;
      }
   }

   public static final class Sign {
      private Sign() {}

      public static boolean isNegative(final long value) {
         return value < 0;
      }

      public static boolean isPositive(final long value) {
         return value > 0;
      }

      public static boolean isZero(final long value) {
         return value == 0;
      }
   }

   public static final class WideNumbers {
      public static final class Int128 implements AutoCloseable {
         private long high = 0, low = 0;

         public final long getHigh() {
            return high;
         }

         public final void setHigh(final long high) {
            this.high = high;
         }

         public final long getLow() {
            return low;
         }

         public final void setLow(final long low) {
            this.low = low;
         }

         public Int128() {}

         public Int128(final long high, final long low) {
            this.high = high;
            this.low = low;
         }

         final void set(final long high, final long low) {
            this.high = high;
            this.low = low;
         }

         @Override
         public void close() throws Exception {
            bigMulResult128.put(this);
         }
      }

      public static final class Int196 implements AutoCloseable {
         private long high = 0, mid = 0, low = 0;

         public final long getHigh() {
            return high;
         }

         public final void setHigh(final long high) {
            this.high = high;
         }

         public final long getLow() {
            return low;
         }

         public final void setLow(final long low) {
            this.low = low;
         }

         public final long getMid() {
            return mid;
         }

         public final void setMid(long mid) {
            this.mid = mid;
         }

         public Int196() {}

         public Int196(final long high, final long mid, final long low) {
            this.high = high;
            this.mid = mid;
            this.low = low;
         }

         final void set(final long high, final long mid, final long low) {
            this.high = high;
            this.mid = mid;
            this.low = low;
         }

         @Override
         public void close() throws Exception {
            bigMulResult196.put(this);
         }
      }

      public static final class Int256 implements AutoCloseable {
         private long high = 0, midHigh = 0, midLow = 0, low = 0;

         public final long getHigh() {
            return high;
         }

         public final void setHigh(final long high) {
            this.high = high;
         }

         public final long getLow() {
            return low;
         }

         public final void setLow(final long low) {
            this.low = low;
         }

         public final long getMidHigh() {
            return midHigh;
         }

         public final void setMidHigh(final long midHigh) {
            this.midHigh = midHigh;
         }

         public final long getMidLow() {
            return midLow;
         }

         public final void setMidLow(final long midLow) {
            this.midLow = midLow;
         }

         public Int256() {}

         public Int256(final long high, final long midHigh, final long midLow, final long low) {
            this.high = high;
            this.midHigh = midHigh;
            this.midLow = midLow;
            this.low = low;
         }

         final void set(final long high, final long midHigh, final long midLow, final long low) {
            this.high = high;
            this.midHigh = midHigh;
            this.midLow = midLow;
            this.low = low;
         }

         @Override
         public void close() throws Exception {
            bigMulResult256.put(this);
         }
      }

      private static final int                            CACHE_SIZE      = 8;
      private static final SimpleThreadLocalCache<Int128> bigMulResult128 = new SimpleThreadLocalCache<>(CACHE_SIZE,
                                                                                                         () -> new Int128());
      private static final SimpleThreadLocalCache<Int196> bigMulResult196 = new SimpleThreadLocalCache<>(CACHE_SIZE,
                                                                                                         () -> new Int196());
      private static final SimpleThreadLocalCache<Int256> bigMulResult256 = new SimpleThreadLocalCache<>(CACHE_SIZE,
                                                                                                         () -> new Int256());

      private WideNumbers() {}

      public static Int128 unsignedBigMul(final long x, final long y) {
         final Int128 result = bigMulResult128.get();
         unsignedBigMul(x, y, result);
         return result;
      }

      public static void unsignedBigMul(final long x, final long y, final Int128 result) {
         // Split high low
         final long xh = extractHigh(x);
         final long xl = extractLow(x);
         final long yh = extractHigh(y);
         final long yl = extractLow(y);

         // Multiply individual components to get intermediate result
         final long l  = xl * yl;
         final long lh = extractHigh(l);
         final long ll = extractLow(l);

         // Overlapping results
         final long o  = xh * yl + xl * yh + lh;
         final long oh = extractHigh(o);
         final long ol = extractLow(o);

         final long h = xh * yh + oh;

         // Final combined total
         final long tl = ll | (ol << HALF_SIZE);

         result.set(h, tl);
      }

      public static Int196 unsignedBigMul(final long x, final Int128 y) {
         final long yh = y.getHigh();
         final long yl = y.getLow();
         return unsignedBigMul(x, yh, yl);
      }

      public static Int196 unsignedBigMul(final long x, final long yh, final long yl) {
         final Int196 result = bigMulResult196.get();
         unsignedBigMul(x, yh, yl, result);
         return result;
      }

      public static void unsignedBigMul(final long x, final long yh, final long yl, final Int196 result) {
         final long xh = extractHigh(x);
         final long xl = extractLow(x);

         final long yhh = extractHigh(yh);
         final long yhl = extractLow(yh);
         final long ylh = extractHigh(yl);
         final long yll = extractLow(yl);

         // Multiply individual components to get intermediate result
         final long l  = xl * yll;
         final long lh = extractHigh(l);
         final long ll = extractLow(l);

         // Overlapping results
         final long o1  = xh * yll + xl * ylh + lh;
         final long o1h = extractHigh(o1);
         final long o1l = extractLow(o1);

         final long o2  = xh * yhl + xl * yhh + o1h;
         final long o2h = extractHigh(o2);
         final long o2l = extractLow(o2);

         final long h = xh * yhh + o2h;

         // Final combined total
         final long tl = ll | (o1l << HALF_SIZE);
         final long tm = o1h | (o2l << HALF_SIZE);

         result.set(h, tm, tl);
      }

      public static void unsignedBigMul(final long x, final Int128 y, final Int196 result) {
         final long yh = y.getHigh();
         final long yl = y.getLow();
         unsignedBigMul(x, yh, yl, result);
      }

      public static Int256 unsignedBigMul(final long x, final Int196 y) {
         final long yh = y.getHigh();
         final long ym = y.getMid();
         final long yl = y.getLow();
         return unsignedBigMul(x, yh, ym, yl);
      }

      public static Int256 unsignedBigMul(final long x, final long yh, final long ym, final long yl) {
         final Int256 result = bigMulResult256.get();
         unsignedBigMul(x, yh, ym, yl, result);
         return result;
      }

      public static void unsignedBigMul(final long x,
                                        final long yh,
                                        final long ym,
                                        final long yl,
                                        final Int256 result) {
         final long xh = extractHigh(x);
         final long xl = extractLow(x);

         final long yhh = extractHigh(yh);
         final long yhl = extractLow(yh);
         final long ymh = extractHigh(ym);
         final long yml = extractLow(ym);
         final long ylh = extractHigh(yl);
         final long yll = extractLow(yl);

         final long l  = xl * yll;
         final long lh = extractHigh(l);
         final long ll = extractLow(l);

         // Overlapping results
         final long o1  = xh * yll + xl * ylh + lh;
         final long o1h = extractHigh(o1);
         final long o1l = extractLow(o1);

         final long o2  = xh * yml + xl * ymh + o1h;
         final long o2h = extractHigh(o2);
         final long o2l = extractLow(o2);

         final long o3  = xh * yhl + xl * yhh + o2h;
         final long o3h = extractHigh(o3);
         final long o3l = extractLow(o3);

         final long h = xh * yhh + o3h;

         // Final combined total
         final long tl  = ll | (o1l << HALF_SIZE);
         final long tml = o1h | (o2l << HALF_SIZE);
         final long tmh = o2h | (o3l << HALF_SIZE);

         result.set(h, tmh, tml, tl);
      }

      public static void unsignedBigMul(final long x, final Int196 y, final Int256 result) {
         final long yh = y.getHigh();
         final long ym = y.getMid();
         final long yl = y.getLow();
         unsignedBigMul(x, yh, ym, yl, result);
      }

      public static long unsignedBigMulHigh(final long x, final long y) {
         // Split high low
         final long xh = extractHigh(x);
         final long xl = extractLow(x);
         final long yh = extractHigh(y);
         final long yl = extractLow(y);

         // Multiply individual components to get intermediate result
         final long l  = xl * yl;
         final long lh = extractHigh(l);

         // Overlapping results
         final long o  = xh * yl + xl * yh + lh;
         final long oh = extractHigh(o);

         final long h = xh * yh + oh;

         return h;
      }

      public static long unsignedBigMulLow(final long x, final long y) {
         // Split high low
         final long xh = extractHigh(x);
         final long xl = extractLow(x);
         final long yh = extractHigh(y);
         final long yl = extractLow(y);

         // Multiply individual components to get intermediate result
         final long l  = xl * yl;
         final long lh = extractHigh(l);
         final long ll = extractLow(l);

         // Overlapping results
         final long o  = xh * yl + xl * yh + lh;
         final long ol = extractLow(o);

         // Final combined total
         final long tl = ll | (ol << HALF_SIZE);

         return tl;
      }
   }

   private Integer64Utils() {}
}