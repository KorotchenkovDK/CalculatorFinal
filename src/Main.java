import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScannerException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пример:");
        String input = scanner.nextLine();
        System.out.println(Main.calc(input));
    }

    public static String calc(String input) throws ScannerException {
        String result;
        int a, b;
        boolean latinTrue = false;
        String [] arabic = {"1","2","3","4","5","6","7","8","9","10"};
        String [] latin = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        List<String> arabicList = new ArrayList<>(List.of(arabic));
        List<String> latinList = new ArrayList<>(List.of(latin));
        String [] strings = input.split(" ");
        if (strings.length<3) {
            throw new ScannerException("т.к. строка не является математической операцией");
        } else if (strings.length>3) {
            throw new ScannerException("т.к. формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *)");
        }
        String operation = strings[1];
        if (arabicList.contains(strings[0]) && arabicList.contains(strings[2])) {
            a = Integer.parseInt(strings[0]);
            b = Integer.parseInt(strings[2]);
        } else if (latinList.contains(strings[0]) && latinList.contains(strings[2])) {
            latinTrue = true;
            Latin latinA = Latin.valueOf(strings[0]);
            a = latinA.getArabic();
            Latin latinB = Latin.valueOf(strings[2]);
            b = latinB.getArabic();
            if (strings[1].equals("-") && a<=b) {
                throw new ScannerException("т.к. в римской системе нет отрицательных чисел");
            }
        } else if ((latinList.contains(strings[0]) && arabicList.contains(strings[2])) ||
        (latinList.contains(strings[2]) && arabicList.contains(strings[0]))) {
            throw new ScannerException("т.к. используются одновременно разные системы счисления");
        }
        else {
            throw new ScannerException("т.к. неподходящие числа");
        }
        result = switch (operation) {
            case "+" -> Integer.toString(a + b);
            case "-" -> Integer.toString(a - b);
            case "*" -> Integer.toString(a * b);
            case "/" -> Integer.toString(a / b);
            default -> throw new ScannerException("т.к. строка не является математической операцией");
        };
        if (latinTrue) {
            for (int i = 0; i<100; i++){
                Latin latinResult = Latin.values()[i];
                String c = Integer.toString(latinResult.getArabic());
                if (c.equals(result)) {
                    result = latinResult.toString();
                }
            }
        }
        return result;
    }
}