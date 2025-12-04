package numerical;
import java.util.function.Function;
import numerical.Numerical;

public class testRapido {





    public static void main(String[] args) {



        Function<Double,Double> f = (x) -> x*x*x*4; 


        Function<Double,Double> x = Numerical.diff(f);
        System.out.println(x.apply(1.0));
        System.out.println(x.apply(10.0));
        System.out.println(x.apply(20.0));
        System.out.println(x.apply(30.0));

        System.out.println("---------------");

        System.out.println(Numerical.integrate(f, 0, 3));


        System.out.println("---------------");


        System.out.println(Numerical.NRroots(0.0, f, 0.0));



    }
}
