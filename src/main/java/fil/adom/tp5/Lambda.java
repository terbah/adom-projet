package fil.adom.tp5;

public class Lambda {
	
	private int lambda1;
	private int lambda2;


	public Lambda() {
		// TODO Auto-generated constructor stub
	}


	public int getLambda1() {
		return lambda1;
	}


	public void setLambda1(int lambda1) {
		this.lambda1 = lambda1;
	}


	public int getLambda2() {
		return lambda2;
	}


	public void setLambda2(int lambda2) {
		this.lambda2 = lambda2;
	}


	public Lambda(int lambda1, int lambda2) {
		super();
		this.lambda1 = lambda1;
		this.lambda2 = lambda2;
	}


	@Override
	public String toString() {
		return "Lambda [lambda1=" + lambda1 + ", lambda2=" + lambda2 + "]";
	}
	
	
	
	

}
