package CMS;

public class Person {

    String Name;
    String Number;

    public Person(String Name, String Number) {
        this.Name=Name;
        this.Number=Number;
    }


     public void Display(){
         System.out.println(this.Name);
         System.out.println(this.Number);
     }

     public String getName(){
        return this.Name;
     }

    public String getNumber(){
        return this.Number;
    }

}