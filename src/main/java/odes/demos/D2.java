package odes.demos;

/**
 * Created by michal on 21.06.2019.
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart2d.Chart2d;
import org.jzy3d.colors.Color;
import org.jzy3d.plot2d.primitives.Serie2d;
import org.jzy3d.plot3d.primitives.ConcurrentLineStrip;
import org.jzy3d.ui.LookAndFeel;

public class D2 {
    public static float duration = 60f;
    public static int interval = 50;
    public static int maxfreq = 880;
    public static int nOctave = 5;

    public static void main(String[] args) throws Exception {
        PitchAmpliControlCharts log = new PitchAmpliControlCharts(duration, maxfreq, nOctave);
        new TimeChartWindow(log.getCharts());

        generateSamplesInTime(log);
        // generateSamples(log, 500000);
    }



    public static void generateSamplesInTime(PitchAmpliControlCharts log) throws InterruptedException {
        start();

        while (elapsed() < duration) {
            double ampli = Math.random();
            log.serieAmpli.add(elapsed(), ampli);
            Thread.sleep(interval);
        }
    }

    /** Hold 2 charts, 2 time series, and 2 drawable lines */
    public static class PitchAmpliControlCharts {
        public Chart2d ampliChart;
        public Serie2d serieAmpli;
        public ConcurrentLineStrip amplitudeLineStrip;

        public PitchAmpliControlCharts(float timeMax, int freqMax, int nOctave) {

            ampliChart = new Chart2d();
     //       ampliChart.asTimeChart(timeMax, 0, 1.1f, "Time", "Amplitude");
            serieAmpli = ampliChart.getSerie("amplitude", Serie2d.Type.LINE);
            serieAmpli.setColor(Color.RED);
            amplitudeLineStrip = (ConcurrentLineStrip) serieAmpli.getDrawable();
        }

        public List<Chart> getCharts() {
            List<Chart> charts = new ArrayList<>();
            charts.add(ampliChart);
            return charts;
        }
    }

    public static class TimeChartWindow extends JFrame {
        private static final long serialVersionUID = 7519209038396190502L;

        public TimeChartWindow(List<Chart> charts) throws IOException {
            LookAndFeel.apply();
            String lines = "[300px,grow]";
            String columns = "[500px,grow]";
            setLayout(new MigLayout("", columns, lines));
            int k = 0;
            for (Chart c : charts) {
                addChart(c, k++);
            }
            windowExitListener();
            this.pack();
            show();
            setVisible(true);
        }

        public void addChart(Chart chart, int id) {
            Component canvas = (java.awt.Component) chart.getCanvas();
            JPanel chartPanel = new JPanel(new BorderLayout());

            Border b = BorderFactory.createLineBorder(java.awt.Color.black);
            chartPanel.setBorder(b);
            chartPanel.add(canvas, BorderLayout.CENTER);
            add(chartPanel, "cell 0 " + id + ", grow");
        }

        public void windowExitListener() {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    TimeChartWindow.this.dispose();
                    System.exit(0);
                }
            });
        }
    }

    protected static long start;

    public static void start() {
        start = System.nanoTime();
    }

    public static double elapsed() {
        return (System.nanoTime() - start) / 1000000000.0;
    }
}