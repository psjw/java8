package java.study;

import java.util.ArrayList;
import java.util.List;

public class MethodReferenceExample {
    public static MethodReferenceExample of(){
        return new MethodReferenceExample();
    }

    //데이터 처리 로직 정의
    public static void executeMethod(String entity){
        if(entity!=null && "".equals(entity)){
            System.out.println("Contents : "+entity);
            System.out.println("Length : "+entity.length());
        }
    }

    //대문자로 변경하는 코드
    public void toUpperCase(String entity){
        System.out.println(entity.toUpperCase());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        //정적 메서드 참조 : static으로 정의한 메서드를 참조할 때 사용한다. 가장 이해하기 쉽고 사용하기 편리하다.
        list.stream().forEach(MethodReferenceExample::executeMethod);
        System.out.println("---");
        //한정적 메소드 참조 : public 혹은 protected로 정의한 메서드를 참조할 때 사용하며 static 메서드를 호출하는 것과 유사하다.
        // 스트림에서 필터와 매핑용도로 많이 사용한다.
        //스트림에 포함된 항목과 참조하고자 하는 객체가 반드시 일치 해야한다.
        //한정적이란 단어를 사용한 이유는 참조하는 메서드가 특정 객체의 변수로 제한되기 때문이다.
        //Calendar.getInstance()::getTime
        //Calendar cal = Calendar.getInstance();를 람다로 변경 ()->cal.getTime() 로 가능 => 단점 : 참조에의해서 값이 처리될때마다 Calendar 객체를 생성
        //Calendar cal= Calendar.getInstance(); //객체생성 cal::getTime // 메서드 참조 구문. cal 변수를 참조한다.
        list.stream().forEach(MethodReferenceExample.of()::toUpperCase);
        System.out.println("---");
        //비한정적 메소드 참조 : 이미 외부에서 선언된 객체의 메서드를 호출하거나, 객체를 직접 생성해서 메서드를 참조할 때 사용한다.
        // 비한정적이란 표현은 작성하는 구문 자체가 특정한 객체를 참조하기위한 변수를 지정하지 않는다.
        //String::toUpperCase 에서 String 클래스의 toUpperCase메서드는 public메서드이며 static 메서드가 아니기 때문에 반드시 String 객체가 필요하다.
        //람다식은 (String str)->str.toUpperCase() 이다 str::toUpperCase 표현이 불가능하기에 String:toUpperCase로 표현
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        list.stream().sorted((String a, String b) -> a.compareTo(b));
        list.stream().sorted(String::compareTo);

        //메서드 참조는 실제 메서드가 실행된 결과를 리턴하는게 아니라, 코드 자체를 전달하는 것이지 실행결과를 전달하는 것은 아니다.

        // 클래스명::메서드명
        // 객체변수명::메서드명


    }
}
