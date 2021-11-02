package ru.nsu.voronova;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex extends Number implements Comparable<Complex> {
  private double real;
  private double imaginary;
  private double modulus;

  private double calculateModulus(double real, double imaginary) {
    return Math.sqrt(real * real + imaginary * imaginary);
  }

  public Complex(double real, double imaginary) {
    this.real = real;
    this.imaginary = imaginary;
    this.modulus = calculateModulus(real, imaginary);
  }

  public Complex(double imaginary) {
    this(0.0, imaginary);
  }

  public Complex(Complex complex) {
    this(complex.real, complex.imaginary);
  }

  public Complex(String number) {
    this(parseComplex(number));
  }

  public static Complex parseComplex(String number) throws NumberFormatException {
    number = number.trim();
    Pattern pattern = Pattern.compile("\\(\\s*\\d+\\.?\\d*\\s*,\\s*\\d+\\.?\\d*\\s*\\)");
    Matcher matcher = pattern.matcher(number);
    if (!matcher.matches()) {
      throw new NumberFormatException();
    }

    String[] complex = number.split("\\s*[(),]\\s*");
    double real = Double.parseDouble(complex[1]);
    double imaginary = Double.parseDouble(complex[2]);
    return new Complex(real, imaginary);

  }

  public double getReal() {
    return real;
  }

  public double getImaginary() {
    return imaginary;
  }

  public double getModulus() {
    return modulus;
  }

  public void setReal(double real) {
    this.real = real;
    this.modulus = calculateModulus(this.real, this.imaginary);
  }

  public void setImaginary(double imaginary) {
    this.imaginary = imaginary;
    this.modulus = calculateModulus(this.real, this.imaginary);
  }

  @Override
  public String toString() {
    return "(" + real + ", " + imaginary + ")";
  }

  @Override
  public int intValue() {
    return (int) modulus;
  }

  @Override
  public long longValue() {
    return (long) modulus;
  }

  @Override
  public float floatValue() {
    return (float) modulus;
  }

  @Override
  public double doubleValue() {
    return modulus;
  }

  @Override
  public int compareTo(Complex anotherComplex) {
    double anotherModulus = anotherComplex.getModulus();
    return Double.compare(modulus, anotherModulus);
  }
}
