public class Polynomial {
	double [] coefficients;
	
	public Polynomial () {
		coefficients = new double[1];
		coefficients[0] = 0.0;
	}

	public Polynomial (double[] coefficients) {
		this.coefficients = coefficients;
	}

	public Polynomial add (Polynomial other) {
		double sumCoef [] = new double[this.coefficients.length + other.coefficients.length];
		if(this.coefficients.length > other.coefficients.length){
			for(int i = 0; i < other.coefficients.length; i++){
				sumCoef[i] = this.coefficients[i] + other.coefficients[i];
			}
			for(int i = other.coefficients.length; i < this.coefficients.length; i++){
				sumCoef[i] = this.coefficients[i];
			}
		}

		if(this.coefficients.length < other.coefficients.length){
			for(int i = 0; i < this.coefficients.length; i++){
				sumCoef[i] = this.coefficients[i] + other.coefficients[i];
			}
			for(int i = this.coefficients.length; i < other.coefficients.length; i++){
				sumCoef[i] = other.coefficients[i];
			}
		}

		if(this.coefficients.length == other.coefficients.length){
			for(int i = 0; i < this.coefficients.length; i++){
				sumCoef[i] = this.coefficients[i] + other.coefficients[i];
			}
		}
		Polynomial sum = new Polynomial(sumCoef);
		return sum;
	}

	public double evaluate (double x) {
		double result = 0.0;
		for(int i = 0; i < this.coefficients.length; i++) {
			result = result + coefficients[i] * Math.pow(x, i);
		}
		return result;
	}

	public boolean hasRoot (double value) {
		double answer = evaluate(value);
		return (answer == 0);
	}
}