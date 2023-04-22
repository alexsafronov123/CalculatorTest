import java.util.Scanner;

public class Main {
    enum Operator {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        UNDEFINED
    }

    private static int[] getValues(String value) throws Exception {
        int values[] = new int[2];
        int digitLastIndex = 0; int counter = 0; int firstValueIndex = 0;

        if (!Character.isDigit(value.charAt(0))) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор, диапазон чисел от 1 до 10 включительно");
        }
        while (counter < value.length()) {
            if (Character.isDigit(value.charAt(counter))) {
                digitLastIndex++;
                counter++;
            } else {
                try {
                    values[0] = Integer.parseInt(value.substring(0, digitLastIndex));
                }   catch (NumberFormatException ex) {}
                break;
            }
        };
        counter++; digitLastIndex++; firstValueIndex  = counter;
        if (!Character.isDigit(value.charAt(counter))) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор, диапазон чисел от 1 до 10 включительно");
        }
        while (counter < value.length()) {
            if (Character.isDigit(value.charAt(counter)) && (counter < value.length()-1)) {
                digitLastIndex++;
                counter++;
            } else {
                try {
                    values[1] = Integer.parseInt(value.substring(firstValueIndex, digitLastIndex+1));
                } catch (NumberFormatException exc) {}
                break;
            }
        };
        return values;
    }

    private static Operator getOperator(String value) throws Exception {
        // по условиям задачи от 1 до 10 включительно, значит минус считаем за оператор а не признак отрицательного числа
        for (int i = 0; i < value.length(); i++) {
            switch (value.charAt(i)) {
                case ('/'): return Operator.DIVIDE;
                case ('*'): return Operator.MULTIPLY;
                case ('+'): return Operator.PLUS;
                case ('-'): return Operator.MINUS;
            }
        }
        return Operator.UNDEFINED;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String trimmedValue = input.replaceAll(" ", "");
        Operator operator = getOperator(trimmedValue);

        //Блок синтаксических контролей
        if (
                trimmedValue.length()<3 ||
                        trimmedValue.length()>5 ||
                        operator == Operator.UNDEFINED
        ) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор, диапазон чисел от 1 до 10 включительно");

        int values[] = getValues(trimmedValue);

        //Блок бизнес контролей
        if (
                values[0] > 10 || values[0] < 1 ||
                        values[1] > 10 || values[1] < 1
        ) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор, диапазон чисел от 1 до 10 включительно");

        switch (operator){
            case PLUS : System.out.println(values[0]+values[1]); break;
            case MINUS : System.out.println(values[0]-values[1]);break;
            case DIVIDE: System.out.println(values[0]/values[1]);break;
            case MULTIPLY: System.out.println(values[0]*values[1]);break;
        }
    }

}