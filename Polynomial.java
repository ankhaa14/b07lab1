import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileWriter;

public class Polynomial {
	double [] coefficients;
	int [] exponents;
	
	public Polynomial () {
		coefficients = new double[1];
		coefficients[0] = 0.0;
		exponents = new int[1];
		exponents[0] = 0;
	}

	public Polynomial (double[] coefficients, int[] exponents) {
		this.coefficients = coefficients;
		this.exponents = exponents;
	}

	public Polynomial reduce (){
		int count = 0;
		int a = this.coefficients.length;
		for(int i = 0; i < a; i++){
		 	if(this.coefficients[i] == 0)
		 		count += 1;
		}
		double resultCoef [] = new double[a - count];
		int resultExp [] = new int[a - count];

		int b = 0;
		for(int i = 0; i < a; i++){
			if(this.coefficients[i] == 0)
				b += 1;
			else{
				resultCoef[i - b] = this.coefficients[i];
				resultExp[i - b] = this.exponents[i];
			}
		}
		Polynomial resultPoly = new Polynomial(resultCoef, resultExp);
		return resultPoly;
	}

	public int index(int key, int [] array){
		for(int i = 0; i < array.length; i++){
			if(array[i] == key)
				return i;
		}
		return -1;
	}

	public Polynomial add (Polynomial other) {
		int a = Math.max(this.exponents[this.exponents.length - 1], other.exponents[other.exponents.length - 1]);
		double sumCoef [] = new double[a + 1];
		int sumExp [] = new int[a + 1];

		for(int i = 0; i < this.exponents.length; i++){
			sumCoef[exponents[i]] += this.coefficients[i];
			sumExp[exponents[i]] = this.exponents[i];
		}

		for(int i = 0; i < other.exponents.length; i++){
			sumCoef[other.exponents[i]] += other.coefficients[i];
			sumExp[other.exponents[i]] = other.exponents[i];
		}
		Polynomial sum = new Polynomial(sumCoef, sumExp);
		return sum.reduce();
	}

	public Polynomial multiply (Polynomial other){
		int a = this.exponents[this.coefficients.length - 1] + other.exponents[other.coefficients.length - 1]; 
		int b = 0;
		double [] multCoef = new double[a];
		int [] multExp = new int[a];
		
		for(int i = 0; i < this.coefficients.length; i++){
			for(int j = 0; j < other.coefficients.length; j++){
				int c = index((this.exponents[i] + other.exponents[j]), multExp);
				if(c != -1)
					multCoef[c] += this.coefficients[i] * other.coefficients[j];
				else{
					multCoef[b] = this.coefficients[i] * other.coefficients[j];
					multExp[b] = this.exponents[i] + other.exponents[j];
					b++;
				}
			}
		}		
		Polynomial product = new Polynomial(multCoef, multExp);
		return product.reduce();
	}

	public Polynomial (File file){
		try {
	      	Scanner polyText = new Scanner(file);
	      	while (polyText.hasNextLine()){ 
	        	String polynomial = polyText.nextLine();
	        	String [] split = polynomial.split("(?=[-+])");
	        	this.coefficients = new double[split.length];
	        	this.exponents = new int[split.length];
	        	int pos = 0;
	        	for(int i = 0; i < split.length; i++){
	        		String [] split2 = split[i].split("x");
	        		if(split2.length == 1){
	        			this.coefficients[pos] = Double.parseDouble(split2[0]);
	        			this.exponents[pos] = 0;
	        		}
	        		else{
	        			this.coefficients[pos] = Double.parseDouble(split2[0]);
	        			this.exponents[pos] = Integer.parseInt(split2[1]);
	        		}
	        		pos++;
	        	}
	        
	      	}
	      	polyText.close();
	    } 
	    catch (FileNotFoundException e) {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
		}
	}

	public void saveToFile(String name){
		String polynomial = ""; 
		for(int i = 0; i < this.exponents.length; i++) {
			if(i != 0) {
				if(this.coefficients[i] > 0)
					polynomial = polynomial + "+";
			}
			polynomial = polynomial + (int)this.coefficients[i];
			if(this.exponents[i] == 0)
				continue;
			polynomial = polynomial + "x" + this.exponents[i];
		}
		try {
			FileWriter writer = new FileWriter(name);
			writer.write(polynomial);
			writer.close();
	    } 
		catch (IOException e) {
	     	System.out.println("An error occurred.");
	       	e.printStackTrace();
	    }
	}

	public Polynomial printPoly(){
		System.out.println(Arrays.toString(this.coefficients));
        System.out.println(Arrays.toString(this.exponents));
        return this;
	}

	public double evaluate (double x) {
		double result = 0.0;
		for(int i = 0; i < this.coefficients.length; i++) {
			result = result + this.coefficients[i] * Math.pow(x, this.exponents[i]);
		}
		return result;
	}
	

	public boolean hasRoot (double value) {
		double answer = evaluate(value);
		return (answer == 0.0);
	}
}