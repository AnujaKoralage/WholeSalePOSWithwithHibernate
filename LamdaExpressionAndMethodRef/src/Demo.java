public class Demo {

    public static void main(String[] args) {

        FuntionalInt i = Demo.A::new;

        //Lamda Expression
        /*FuntionalInt i = (something)->{
            new A(something);
        };*/

        //Anonymouse Inner Class
        /*FuntionalInt i = new FuntionalInt() {
            @Override
            public void print(String something) {
                System.out.println(something);
            }
        };*/

        i.print("LOL");
    }

    static class A{
        public A(String something) {
            System.out.println(something);
        }
    }

}
