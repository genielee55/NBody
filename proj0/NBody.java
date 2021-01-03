
//Class that runs our simulation

public class NBody {
	
	//Given a file name, returns the radius of the universe in that file
	public static double readRadius(String file){
		In in = new In(file);
		int numBodies = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	//Given a file name, return array of bodies corresponding to bodies in file
	public static Body[] readBodies(String file){
		In in = new In(file);
		int numBodies = in.readInt();
		double radius = in.readDouble();

		Body[] bodies = new Body[numBodies];

		for (int i = 0; i < numBodies; i += 1){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return bodies;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		int numBodies = bodies.length;

		//Drawing the background
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		//Drawing bodies
		for (Body b: bodies){
			b.draw();
		}

		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time < T){
			double [] xForces = new double[numBodies];
			double [] yForces = new double[numBodies];

			for (int i = 0; i<numBodies; i+=1){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

		

			for (int i = 0; i< numBodies; i+=1){
				bodies[i].update(dt, xForces[i], yForces[i]);

			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Body body: bodies){
				body.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;

		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   


		}
		

	}

}