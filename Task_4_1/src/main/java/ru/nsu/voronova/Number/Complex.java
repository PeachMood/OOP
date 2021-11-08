package ru.nsu.voronova.Number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex extends Number implements Comparable<Complex> {
  private double realPart;
  private double imaginaryPart;
  private double modulus;
  private double argument;

  public Complex(double realPart, double imaginaryPart) {
    this.realPart = realPart;
    this.imaginaryPart = imaginaryPart;
    this.modulus = calculateModulus(realPart, imaginaryPart);
    this.argument = calculateArgument(realPart, imaginaryPart);
  }

  public Complex(double imaginary) {
    this(0.0, imaginary);
  }

  public Complex(Complex complex) {
    this(complex.realPart, complex.imaginaryPart);
  }

  public Complex(String number) {
    this(parseComplex(number));
  }

  private double calculateModulus(double realPart, double imaginaryPart) {
    return Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
  }

  private double calculateArgument(double realPart, double imaginaryPart) {
    return Math.atan2(imaginaryPart, realPart);
  }

  public static Complex parseComplex(String number) throws NumberFormatException {
    number = number.trim();
    Pattern pattern = Pattern.compile("\\(\\s*\\d+\\.?\\d*\\s*,\\s*\\d+\\.?\\d*\\s*\\)");
    Matcher matcher = pattern.matcher(number);
    if (!matcher.matches()) {
      throw new NumberFormatException();
    }

    String[] complex = number.split("\\s*[(),]\\s*");
    double realPart = Double.parseDouble(complex[1]);
    double imaginaryPart = Double.parseDouble(complex[2]);
    return new Complex(realPart, imaginaryPart);
  }

  public double getRealPart() {
    return realPart;
  }

  public double getImaginaryPart() {
    return imaginaryPart;
  }

  public double getModulus() {
    return modulus;
  }

  public double getArgument() {
    return argument;
  }

  public void setRealPart(double realPart) {
    this.realPart = realPart;
    this.modulus = calculateModulus(this.realPart, this.imaginaryPart);
    this.argument = calculateArgument(this.realPart, this.imaginaryPart);
  }

  public void setImaginaryPart(double imaginaryPart) {
    this.imaginaryPart = imaginaryPart;
    this.modulus = calculateModulus(this.realPart, this.imaginaryPart);
    this.argument = calculateArgument(this.realPart, this.imaginaryPart);
  }

  @Override
  public String toString() {
    return "(" + realPart + ", " + imaginaryPart + ")";
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
