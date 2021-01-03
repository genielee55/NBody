public class TestBody {
	
	public static void main(String[] args){

		//Creates two bodies, b1 and b2
		Body b1 = new Body(1, 0, 10, 12, 7E5, "img");
		Body b2 = new Body(3, 4, 11, 12, 9E5, "img");
		Body[] bodyArray = new Body[2];
		bodyArray[0] = b1;
		bodyArray[1] = b2;

		//Print pairwise force between b1 and b2
		System.out.println(b1.calcNetForceExertedByX(bodyArray));

	}
}