package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
public class GuitarHero {
    public static final double CONCERT_A = 440.0;

    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int KEYBOARD_SIZE = KEYBOARD.length();

    private static double getFrequency(int index) {
        return CONCERT_A * Math.pow(2, (index - 24) / 12.0);
    }

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[KEYBOARD_SIZE];
        for (int i = 0; i < KEYBOARD_SIZE; i++) {
            strings[i] = new GuitarString(getFrequency(i));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyIndex = KEYBOARD.indexOf(key);
                if (keyIndex != -1) {
                    strings[keyIndex].pluck();
                }
            }

            double sample = 0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }
            StdAudio.play(sample);
            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}
