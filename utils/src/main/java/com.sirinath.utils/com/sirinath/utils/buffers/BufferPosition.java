package com.sirinath.utils.buffers;

import java.nio.ByteOrder;

public interface BufferPosition extends Buffer {
   long getPosition();

   long getAndSetPosition(long position);

   boolean compareAndSetPosition(long expectedPosition, long newPosition);

   long getAndIncrementPosition();

   long getAndDecrementPosition();

   long getAndAddToPosition(long offset);

   default int getInt() {
      return getInt(getPosition());
   }

   default void putInt(int x) {
      putInt(getPosition(), x);
   }

   default Object getObject() {
      return getObject(getPosition());
   }

   default void putObject(Object x) {
      putObject(getPosition(), x);
   }

   default boolean getBoolean() {
      return getBoolean(getPosition());
   }

   default void putBoolean(boolean x) {
      putBoolean(getPosition(), x);
   }

   default byte getByte() {
      return getByte(getPosition());
   }

   default void putByte(byte x) {
      putByte(getPosition(), x);
   }

   default short getShort() {
      return getShort(getPosition());
   }

   default void putShort(short x) {
      putShort(getPosition(), x);
   }

   default char getChar() {
      return getChar(getPosition());
   }

   default void putChar(char x) {
      putChar(getPosition(), x);
   }

   default long getLong() {
      return getLong(getPosition());
   }

   default void putLong(long x) {
      putLong(getPosition(), x);
   }

   default float getFloat() {
      return getFloat(getPosition());
   }

   default void putFloat(float x) {
      putFloat(getPosition(), x);
   }

   default double getDouble() {
      return getDouble(getPosition());
   }

   default void putDouble(double x) {
      putDouble(getPosition(), x);
   }

   default int getIntVolatile() {
      return getIntVolatile(getPosition());
   }

   default void putIntVolatile(int x) {
      putIntVolatile(getPosition(), x);
   }

   default Object getObjectVolatile() {
      return getObjectVolatile(getPosition());
   }

   default void putObjectVolatile(Object x) {
      putObjectVolatile(getPosition(), x);
   }

   default boolean getBooleanVolatile() {
      return getBooleanVolatile(getPosition());
   }

   default void putBooleanVolatile(boolean x) {
      putBooleanVolatile(getPosition(), x);
   }

   default byte getByteVolatile() {
      return getByteVolatile(getPosition());
   }

   default void putByteVolatile(byte x) {
      putByteVolatile(getPosition(), x);
   }

   default short getShortVolatile() {
      return getShortVolatile(getPosition());
   }

   default void putShortVolatile(short x) {
      putShortVolatile(getPosition(), x);
   }

   default char getCharVolatile() {
      return getCharVolatile(getPosition());
   }

   default void putCharVolatile(char x) {
      putCharVolatile(getPosition(), x);
   }

   default long getLongVolatile() {
      return getLongVolatile(getPosition());
   }

   default void putLongVolatile(long x) {
      putLongVolatile(getPosition(), x);
   }

   default float getFloatVolatile() {
      return getFloatVolatile(getPosition());
   }

   default void putFloatVolatile(float x) {
      putFloatVolatile(getPosition(), x);
   }

   default double getDoubleVolatile() {
      return getDoubleVolatile(getPosition());
   }

   default void putDoubleVolatile(double x) {
      putDoubleVolatile(getPosition(), x);
   }

   default void putOrderedInt(int x) {
      putOrderedInt(getPosition(), x);
   }

   default void putOrderedObject(Object x) {
      putOrderedObject(getPosition(), x);
   }

   default void putOrderedLong(long x) {
      putOrderedLong(getPosition(), x);
   }

   default int getAndSetInt(int x) {
      return getAndSetInt(getPosition(), x);
   }

   default Object getAndSetObject(Object x) {
      return getAndSetObject(getPosition(), x);
   }

   default long getAndSetLong(long x) {
      return getAndSetLong(getPosition(), x);
   }

   default int getAndAddInt(int x) {
      return getAndAddInt(getPosition(), x);
   }

   default long getAndAddLong(long x) {
      return getAndAddLong(getPosition(), x);
   }

   default boolean compareAndSwapInt(int expected, int x) {
      return compareAndSwapInt(getPosition(), expected, x);
   }

   default boolean compareAndSwapObject(Object expected, Object x) {
      return compareAndSwapObject(getPosition(), expected, x);
   }

   default boolean compareAndSwapLong(long expected, long x) {
      return compareAndSwapLong(getPosition(), expected, x);
   }

   default void putOrderedByte(byte x) {
      putOrderedByte(getPosition(), x);
   }

   default void putOrderedBoolean(boolean x) {
      putOrderedBoolean(getPosition(), x);
   }

   default void putOrderedShort(short x) {
      putOrderedShort(getPosition(), x);
   }

   default void putOrderedChar(char x) {
      putOrderedChar(getPosition(), x);
   }

   default void putOrderedFloat(float x) {
      putOrderedFloat(getPosition(), x);
   }

   default void putOrderedDouble(double x) {
      putOrderedDouble(getPosition(), x);
   }

   default byte getAndSetByte(byte x) {
      return getAndSetByte(getPosition(), x);
   }

   default boolean getAndSetBoolean(boolean x) {
      return getAndSetBoolean(getPosition(), x);
   }

   default short getAndSetShort(short x) {
      return getAndSetShort(getPosition(), x);
   }

   default char getAndSetChar(char x) {
      return getAndSetChar(getPosition(), x);
   }

   default float getAndSetFloat(float x) {
      return getAndSetFloat(getPosition(), x);
   }

   default double getAndSetDouble(double x) {
      return getAndSetDouble(getPosition(), x);
   }

   default byte getAndAddByte(byte x) {
      return getAndAddByte(getPosition(), x);
   }

   default short getAndAddShort(short x) {
      return getAndAddShort(getPosition(), x);
   }

   default float getAndAddFloat(float x) {
      return getAndAddFloat(getPosition(), x);
   }

   default double getAndAddDouble(double x) {
      return getAndAddDouble(getPosition(), x);
   }

   default boolean compareAndSwapByte(byte expected, byte x) {
      return compareAndSwapByte(getPosition(), expected, x);
   }

   default boolean compareAndSwapBoolean(boolean expected, boolean x) {
      return compareAndSwapBoolean(getPosition(), expected, x);
   }

   default boolean compareAndSwapShort(short expected, short x) {
      return compareAndSwapShort(getPosition(), expected, x);
   }

   default boolean compareAndSwapChar(char expected, char x) {
      return compareAndSwapChar(getPosition(), expected, x);
   }

   default boolean compareAndSwapFloat(float expected, float x) {
      return compareAndSwapFloat(getPosition(), expected, x);
   }

   default boolean compareAndSwapDouble(double expected, double x) {
      return compareAndSwapDouble(getPosition(), expected, x);
   }

   default int getInt(ByteOrder byteOrder) {
      return getInt(getPosition(), byteOrder);
   }

   default void putInt(int x, ByteOrder byteOrder) {
      putInt(getPosition(), x, byteOrder);
   }

   default short getShort(ByteOrder byteOrder) {
      return getShort(getPosition(), byteOrder);
   }

   default void putShort(short x, ByteOrder byteOrder) {
      putShort(getPosition(), x, byteOrder);
   }

   default char getChar(ByteOrder byteOrder) {
      return getChar(getPosition(), byteOrder);
   }

   default void putChar(char x, ByteOrder byteOrder) {

   }

   default long getLong(ByteOrder byteOrder);

   default void putLong(long x, ByteOrder byteOrder);

   default float getFloat(ByteOrder byteOrder);

   default void putFloat(float x, ByteOrder byteOrder);

   default double getDouble(ByteOrder byteOrder);

   default void putDouble(double x, ByteOrder byteOrder);

   default int getIntVolatile(ByteOrder byteOrder);

   default void putIntVolatile(int x, ByteOrder byteOrder);

   default short getShortVolatile(ByteOrder byteOrder);

   default void putShortVolatile(short x, ByteOrder byteOrder);

   default char getCharVolatile(ByteOrder byteOrder);

   default void putCharVolatile(char x, ByteOrder byteOrder);

   default long getLongVolatile(ByteOrder byteOrder);

   default void putLongVolatile(long x, ByteOrder byteOrder);

   default float getFloatVolatile(ByteOrder byteOrder);

   default void putFloatVolatile(float x, ByteOrder byteOrder);

   default double getDoubleVolatile(ByteOrder byteOrder);

   default void putDoubleVolatile(double x, ByteOrder byteOrder);

   default void putOrderedInt(int x, ByteOrder byteOrder);

   default void putOrderedLong(long x, ByteOrder byteOrder);

   default int getAndSetInt(int x, ByteOrder byteOrder);

   default long getAndSetLong(long x, ByteOrder byteOrder);

   default int getAndAddInt(int x, ByteOrder byteOrder);

   default long getAndAddLong(long x, ByteOrder byteOrder);

   default boolean compareAndSwapInt(int expected, int x, ByteOrder byteOrder);

   default boolean compareAndSwapLong(long expected, long x, ByteOrder byteOrder);

   default void putOrderedShort(short x, ByteOrder byteOrder);

   default void putOrderedChar(char x, ByteOrder byteOrder);

   default void putOrderedFloat(float x, ByteOrder byteOrder);

   default void putOrderedDouble(double x, ByteOrder byteOrder);

   default short getAndSetShort(short x, ByteOrder byteOrder);

   default char getAndSetChar(char x, ByteOrder byteOrder);

   default float getAndSetFloat(float x, ByteOrder byteOrder);

   default double getAndSetDouble(double x, ByteOrder byteOrder);

   default short getAndAddShort(short x, ByteOrder byteOrder);

   default float getAndAddFloat(float x, ByteOrder byteOrder);

   default double getAndAddDouble(double x, ByteOrder byteOrder);

   default boolean compareAndSwapShort(short expected, short x, ByteOrder byteOrder);

   default boolean compareAndSwapChar(char expected, char x, ByteOrder byteOrder);

   default boolean compareAndSwapFloat(float expected, float x, ByteOrder byteOrder);

   default  boolean compareAndSwapDouble(double expected, double x, ByteOrder byteOrder);

   default byte getAndApply(OpByte op);

   default boolean getAndApply(OpBoolean op);

   default short getAndApply(OpShort op);

   default short getAndApply(OpShort op, ByteOrder byteOrder);

   default char getAndApply(OpChar op);

   default char getAndApply(OpChar op, ByteOrder byteOrder);

   default int getAndApply(OpInt op);

   default int getAndApply(OpInt op, ByteOrder byteOrder);

   default long getAndApply(OpLong op);

   default long getAndApply(OpLong op, ByteOrder byteOrder);

   default float getAndApply(OpFloat op);

   default float getAndApply(OpFloat op, ByteOrder byteOrder);

   default double getAndApply(OpDouble op);

   default double getAndApply(OpDouble op, ByteOrder byteOrder);
}