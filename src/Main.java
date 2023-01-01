import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение через пробел:");
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine();

        System.out.println(calc(exp));
    }
    public static String calc(String input) throws Exception {
        String result;
        int a, b;
        int value;
        boolean isRoman = false;
        Converter oper;
        String[] expElements = input.split(" ");

        if (expElements.length != 3) throw new Exception();

        CheckForRomanDigit check1 = new CheckForRomanDigit(expElements[0]);
        CheckForRomanDigit check2 = new CheckForRomanDigit(expElements[2]);

        if (check1.isRoman() && check2.isRoman()) {
            oper = new Converter(expElements[0]);
            a = oper.getArabianDigit();
            oper = new Converter(expElements[2]);
            b = oper.getArabianDigit();
            if (a>0 && b>0 && a<11 && b<11) isRoman = true;
            else throw new Exception();
        }
        else if (expElements[0].matches("[1-9]|10") &&
                expElements[2].matches("[1-9]|10")) {
            a = Integer.parseInt(expElements[0]);
            b = Integer.parseInt(expElements[2]);
        }
        else throw new Exception();

        value = calculate(a, b, expElements[1]);

        if (isRoman) {
            if (value > 0) {
                oper = new Converter(String.valueOf(value));
                result = oper.getRomanDigit();
            }
            else throw new Exception();
        }
        else result = String.valueOf(value);

        return result;
    }
    static class RomanDigit {
        String[] romanDigits = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
                "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
                "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
                "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
                "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI",
                "XCVII", "XCVIII", "XCIX", "C"
        };
        String romanDigit;
        RomanDigit(String value){
            romanDigit = value;
        }
    }
    static class CheckForRomanDigit extends RomanDigit {
        CheckForRomanDigit(String value) {
            super(value);
        }
        boolean isRoman() {
            for (String digit: romanDigits) {
                if (digit.equals(romanDigit)) return true;
            }
            return false;
        }
    }
    static class Converter extends RomanDigit {
        Converter(String value) {
            super(value);
        }
        int getArabianDigit() {
            int index = 0;
            for (int i = 0; i <= romanDigits.length; i++) {
                if (romanDigit.equals(romanDigits[i])) {
                    index = i;
                    break;
                }
            }
            return index;
        }
        String getRomanDigit() {
            String totalValue = null;
            for (int i = 0; i <= romanDigits.length; i++) {
                if (Integer.parseInt(romanDigit) == i) {
                    totalValue = romanDigits[i];
                    break;
                }
            }
            return totalValue;
        }
    }
    static int calculate(int a, int b, String operator) throws Exception {
        return switch (operator) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> throw new Exception();
        };
    }
}
