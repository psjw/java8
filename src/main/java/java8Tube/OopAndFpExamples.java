package java8Tube;

public class OopAndFpExamples {
    public static void main(String[] args) {
        final CalculatorService calculatorService = new CalculatorService(new Addtion(), new Subtraction());
        /*final int additionResult = calculatorService.calculate('+', 1, 1);
        System.out.println(additionResult);
        final int subtractionResult = calculatorService.calculate('-', 1, 1);
        System.out.println(subtractionResult);
        final int multiplicationResult = calculatorService.calculate('*', 1, 1);
        System.out.println(multiplicationResult);
        final int divisionResult = calculatorService.calculate('/', 8, 4);
        System.out.println(divisionResult);*/
        final int additionResult = calculatorService.calculate(11, 4);
        System.out.println(additionResult);
        final int subtractionResult = calculatorService.calculate(11, 1);
        System.out.println(subtractionResult);
        final int multiplicationResult = calculatorService.calculate(11, 2);
        System.out.println(multiplicationResult);
        final int divisionResult = calculatorService.calculate(20, 4);
        System.out.println(divisionResult);

        FpCalculatorService fpCalculatorService = new FpCalculatorService();
        //First Class Citizen : Element가 Function의 파라미터로 넘길수 있으며, Function의 결과값 반환 가능, Data Structure에 저장, Variable에 할당(assign)
        // Element : elm
        //1.  getNumber(elm); //파라미터로 넘겨줄수 있음
        //2. Element elm = getResult() ; //결과값으로 받을 수 있음
        //3-1. List<Element> list  = Arrays.asList(elm or result); //Data Structure에 저장 가능
        //3-2. Element e = elm; // Variable에 assign 가능
        System.out.println("addition :" + fpCalculatorService.calculate(new Addtion(), 11, 4));
        System.out.println("substraction :" + fpCalculatorService.calculate(new Subtraction(), 11, 1));
        System.out.println("multiplication :" + fpCalculatorService.calculate(new Multiplication(), 11, 2));
        System.out.println("division :" + fpCalculatorService.calculate(new Division(), 20, 4));
        // public String  getName(){}
        // case 1
        // findByName(getName()); //String을 파라미터로 넘김 X
        // findByName(getName); // 파라미터로 메서드를 넘김 자바에서는 안됨
        // case2
        // public dosomething(){ return getName(); } //String을 결과로 넘김 X
        // public dosomething(){ return getName; } //메서드를 결과로 넘김 자바에서는 안됨
        // case3
        // List<?> list = Arrays.asList(getName()) ; //String을 로 넘김 X
        // List<?> list = Arrays.asList(getName) ; //메서드를 넘김  자바에서는 안됨
        //case4
        // SomeMethod m = getName ; // 자바에서는 안됨
        // 다른언어의 Function에 해당하는 Java의 Method는 First Class Citizen이 아님
        // 지원한다면 First Class Function 이다.
        final Calculation addtion = (i1, i2) -> i1 + i2;
        System.out.println("addition :" + fpCalculatorService.calculate(addtion, 11, 4));
        System.out.println("substraction :" + fpCalculatorService.calculate((i1, i2) -> i1 - i2, 11, 1));
        System.out.println("multiplication :" + fpCalculatorService.calculate((i1, i2) -> i1 * i2, 11, 2));
        System.out.println("division :" + fpCalculatorService.calculate((i1, i2) -> i1 / i2, 20, 4));
        System.out.println("custom calc.  :" + fpCalculatorService.calculate((i1, i2) -> ((i1 + i2) *2)/i2, 20, 4));
    }
}

// 전략 패턴
interface Calculation {
    int calculate(final int num1, final int num2);
}

class Addtion implements Calculation {

    @Override
    public int calculate(final int num1, final int num2) {
        return num1 + num2;
    }
}

class Subtraction implements Calculation {

    @Override
    public int calculate(final int num1, final int num2) {
        return num1 - num2;
    }
}

class Multiplication implements Calculation {

    @Override
    public int calculate(final int num1, final int num2) {
        return num1 * num2;
    }
}

class Division implements Calculation {

    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}

//단일 책임 원칙 : 클래스는 하나의 책임만 가진다.(object 변화가 있을 경우 한가지 변화만 가져야 한다)
class CalculatorService {
    private final Calculation calculation;
    private final Calculation calculation2;

    public CalculatorService(Calculation calculation, Calculation calculation2) {
        this.calculation = calculation;
        this.calculation2 = calculation2;
    }

    public int calculate(int num1, int num2) {
        if (num1 > 10 && num2 < num1)
            return calculation.calculate(num1, num2);
        else
            throw new IllegalArgumentException("Invalid Input num1 : " + num1 + ", num2 : " + num2);
    }

    public int compute(int num1, int num2) {
        if (num1 > 10 && num2 < num1)
            return calculation2.calculate(num1, num2);
        else
            throw new IllegalArgumentException("Invalid Input num1 : " + num1 + ", num2 : " + num2);
    }

    // step1
    /*
        public int calculate(int num1, int num2){
            return num1 + num2;
        }*/
    //step2
    /*
    public int calculate(char calculation, int num1, int num2) {
        if (calculation == '+') {
            return num1 + num2;
        } else if (calculation == '-') {
            return num1 - num2;
        } else if (calculation == '*') {
            return num1 * num2;
        } else if (calculation == '/') {
            return num1 / num2;
        } else {
            throw new IllegalArgumentException("Unknown calculation : " + calculation);
        }
*/
}

//Functional Programing
class FpCalculatorService {
    public int calculate(Calculation calculation, int num1, int num2) {
        if (num1 > 10 && num2 < num1)
            return calculation.calculate(num1, num2);
        else
            throw new IllegalArgumentException("Invalid Input num1 : " + num1 + ", num2 : " + num2);
    }
}
