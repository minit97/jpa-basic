package hellojpa;

public class ValueMain {

    public static void main(String[] args) {
        int a = 10;
        int b =10;
        System.out.println("a == b : "+ (a==b));    // true

        Address_basic address1 = new Address_basic("city", "street", "10000");
        Address_basic address2 = new Address_basic("city", "street", "10000");
        System.out.println("address1 == address2: " + (address1==address2));  //false
        System.out.println("address1 equals address2: " + (address1.equals(address2)));  // 기본으로 쓰면 false, 생성자에 equals 함수를 overriding하면 true
    }
}
