package net.iponweb.disthene.bean;

import net.iponweb.disthene.config.Rollup;

/**
 * @author Andrei Ivanov
 */
public class Metric {

    private MetricKey key;
    private double value;

    public Metric(String input, Rollup rollup) {
        String[] splitInput = input.split("\\s");
        // We are interning tenant and path here - we are going to store them all (or almost so) constantly anyhow in multiple places
        this.key = new MetricKey(
                splitInput.length >=4 ? splitInput[3].intern() : "NONE",
                splitInput[0].intern(),
                rollup.getRollup(),
                rollup.getPeriod(),
                normalizeTimestamp(Long.parseLong(splitInput[2]), rollup));
        this.value = Double.parseDouble(splitInput[1]);
    }

    public Metric(String tenant, String path, int rollup, int period, double value, long timestamp) {
        this.key = new MetricKey(tenant, path, rollup, period, timestamp);
        this.value = value;
    }

    public Metric(MetricKey key, double value) {
        this.key = key;
        this.value = value;
    }

    private long normalizeTimestamp(long timestamp, Rollup rollup) {
        return (timestamp / rollup.getRollup()) * rollup.getRollup();
    }

    public String getId() {
        return getTenant() + "_" + getPath();
    }

    public String getTenant() {
        return key.getTenant();
    }

    public String getPath() {
        return key.getPath();
    }

    public int getRollup() {
        return key.getRollup();
    }

    public int getPeriod() {
        return key.getPeriod();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTimestamp() {
        return key.getTimestamp();
    }

    @Override
    public String toString() {
        return "Metric{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
