import java.util.Arrays;
import java.io.File;

public class Driver { 
    public static void main(String [] args) { 
        Polynomial p = new Polynomial(); 
        // System.out.println(p.evaluate(3)); 
        
        // polynomial 1
        double [] c1 = {2, 1, -3};
        int [] e1 = {0, 3, 4}; 
        Polynomial p1 = new Polynomial(c1, e1);
        System.out.println("Polynomial 1: ");
        p1.printPoly();

        // polynomial 2 
        double [] c2 = {1, -3, 6}; 
        int [] e2 = {3, 5, 6};
        Polynomial p2 = new Polynomial(c2, e2); 
        System.out.println("Polynomial 2: ");
        p2.printPoly();
        
        // polynomial 3
        double [] c3 = {2, 2};
        int [] e3 = {0, 1};
        Polynomial p3 = new Polynomial(c3, e3);
        System.out.println("Polynomial 3: ");
        p3.printPoly();

        // test add
        Polynomial s = p1.add(p2);
        System.out.println("p1 + p2: ");
        s.printPoly();
        
        // test multiply
        Polynomial m2 = p1.multiply(p2);
        System.out.println("p2 * p3: ");
        m2.printPoly(); 

        // test evaluate, has root
        System.out.println("m2(1) = " + m2.evaluate(1)); 
        if(m2.hasRoot(1)) 
        System.out.println("1 is a root of m2"); 
        else 
        System.out.println("1 is not a root of m2"); 

        // test file constructor
        File file = new File("/Users/ankhaa/Desktop/poly.txt");
        Polynomial poly = new Polynomial(file);
        System.out.println(poly.evaluate(1));
        System.out.println("Reading from file: f(1): ");
        poly.printPoly();

        // test save to file
        System.out.println("Saving to file: ");
        p1.saveToFile("/Users/ankhaa/Desktop/test.txt");
    } 
} 