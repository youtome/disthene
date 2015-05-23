package net.iponweb.disthene.service.aggregate;

import net.iponweb.disthene.bean.Metric;
import net.iponweb.disthene.service.general.GeneralStore;

/**
 * @author Andrei Ivanov
 */
public interface Aggregator {

    void aggregate(Metric metric);
    void flush();

}