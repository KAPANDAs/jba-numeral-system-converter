package converter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int sourceRadix = Integer.parseInt(scanner.nextLine());
            String number = scanner.nextLine();
            int targetRadix = Integer.parseInt(scanner.nextLine());
            if (number.contains(".")) {
                String[] partsOfNumber = number.split("\\.");
                System.out.println(convertIntegerPart(partsOfNumber[0], sourceRadix, targetRadix)
                        + "." + convertFractional(partsOfNumber[1], sourceRadix, targetRadix));
            } else {
                System.out.println(convertIntegerPart(number, sourceRadix, targetRadix));
            }
        } catch (Exception e){
            System.out.println("error");
        }

        System.out.println();
    }

    static String convertIntegerPart(String number, int inputRadix, int outputRadix) {
        if (outputRadix < 1 | outputRadix > 36) {
            throw new InputMismatchException();
        }
        int intNum;
        if (inputRadix == 1) {
            intNum = number.length();
        } else {
            intNum = Integer.parseInt(number, inputRadix);
        }

        if (outputRadix == 1) {
            return "1".repeat(Math.max(0, intNum));
        } else {
            return Integer.toString(intNum, outputRadix);
        }
    }

    static String convertFractional(String fPart, int inputRadix, int outputRadix) throws InputMismatchException {
        if (outputRadix < 1 | outputRadix > 36) {
            throw new InputMismatchException();
        }
        String num = convertFractionalPartToDec(fPart, inputRadix);
        StringBuilder outputString = new StringBuilder();
        int strLength = num.length() - 2;
        double dNum = Double.parseDouble(num);
        for (int i = 0; i < 5; i++) {
            dNum *= outputRadix;

            outputString.append(Character.forDigit((int) dNum, outputRadix));
            dNum -= (int) dNum;
        }
        return outputString.toString();
    }

    static String convertFractionalPartToDec(String fPart, int inputRadix) {
        double decValue = 0;
        if (inputRadix == 10) {
            decValue = Double.parseDouble("0." + fPart);
        } else {
            for (int i = 0; i < fPart.length(); i++) {
                decValue += Character.getNumericValue(fPart.charAt(i)) / (Math.pow(inputRadix, i + 1));
            }
        }
        return Double.toString(decValue);
    }


}
