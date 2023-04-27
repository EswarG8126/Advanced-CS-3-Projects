public class GuitarHero {
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		StdDraw.setCanvasSize(1864, 260);
		StdDraw.picture(0.5, 0.5, "keyboard.png");
		GuitarString[] keys = new GuitarString[37];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new GuitarString(440 * Math.pow(1.05956, (i - 24)));
		}
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				int i = keyboard.indexOf(key);
				if (i >= 0) {
					keys[i].pluck();
				}
			}
			double sample = 0;
			for (GuitarString string : keys) {
				sample += string.sample();
				string.tic();
			}
			StdAudio.play(sample);
		}
	}
}