public class Main {
  public static void main(String arge[]) {
    Person p1 = new Person();
    p1.setName("Ahmed");
    p1.setAge(2);

    Person p2 = new Person("Abdullah", 12);


    p1.introduction();
    p2.introduction();
  }
}
