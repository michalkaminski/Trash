package complexsystems.visualisation;

import java.awt.*;

public class ColorPercentRange {

    private static float[] fractions = new float[]{0f, 0.25f, 0.5f, 1f};
    private static Color[] colors = new Color[]{Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED};
    private float direction = 0.05f;
    private float progress = 0f;

    public static void main(String[] args) {

        ColorPercentRange colorPercentRange = new ColorPercentRange();
        Color c1= colorPercentRange.getColor(0f);
        Color c2= colorPercentRange.getColor(0.5f);
        Color c3= colorPercentRange.getColor(1f);
    }

    public static Color getColor(float percentage)
    {
        Color color = blendColors(fractions, colors, percentage);
        return color;
    }

    private void colIterate() {

        Color startColor = blendColors(fractions, colors, progress);

        for (int index = 0; index < 100; index++) {
            float progress = (float) index / (float) 100;
            Color color = blendColors(fractions, colors, progress);

            if (progress + direction > 1f) {
                direction = -0.05f;
            } else if (progress + direction < 0f) {
                direction = 0.05f;
            }
            progress += direction;

        }

    }


    public static Color blendColors(float[] fractions, Color[] colors, float progress) {
        Color color = null;
        if (fractions != null) {
            if (colors != null) {
                if (fractions.length == colors.length) {
                    int[] indicies = getFractionIndicies(fractions, progress);

                    float[] range = new float[]{fractions[indicies[0]], fractions[indicies[1]]};
                    Color[] colorRange = new Color[]{colors[indicies[0]], colors[indicies[1]]};

                    float max = range[1] - range[0];
                    float value = progress - range[0];
                    float weight = value / max;

                    color = blend(colorRange[0], colorRange[1], 1f - weight);
                } else {
                    throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
                }
            } else {
                throw new IllegalArgumentException("Colours can't be null");
            }
        } else {
            throw new IllegalArgumentException("Fractions can't be null");
        }
        return color;
    }

    public static int[] getFractionIndicies(float[] fractions, float progress) {
        int[] range = new int[2];

        int startPoint = 0;
        while (startPoint < fractions.length && fractions[startPoint] <= progress) {
            startPoint++;
        }

        if (startPoint >= fractions.length) {
            startPoint = fractions.length - 1;
        }

        range[0] = startPoint - 1;
        range[1] = startPoint;

        return range;
    }

    public static Color blend(Color color1, Color color2, double ratio) {
        float r = (float) ratio;
        float ir = (float) 1.0 - r;

        float rgb1[] = new float[3];
        float rgb2[] = new float[3];

        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);

        float red = rgb1[0] * r + rgb2[0] * ir;
        float green = rgb1[1] * r + rgb2[1] * ir;
        float blue = rgb1[2] * r + rgb2[2] * ir;

        if (red < 0) {
            red = 0;
        } else if (red > 255) {
            red = 255;
        }
        if (green < 0) {
            green = 0;
        } else if (green > 255) {
            green = 255;
        }
        if (blue < 0) {
            blue = 0;
        } else if (blue > 255) {
            blue = 255;
        }

        Color color = null;
        try {
            color = new Color(red, green, blue);
        } catch (IllegalArgumentException exp) {
        }
        return color;
    }
}