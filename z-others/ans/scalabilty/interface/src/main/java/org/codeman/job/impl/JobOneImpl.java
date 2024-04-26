package org.codeman.job.impl;

import org.codeman.job.JobInterface;
import org.springframework.stereotype.Component;

/**
 * @author hdgaadd
 * created on 2022/02/03
 */
@Component
public class JobOneImpl implements JobInterface {
    @Override
    public void task() {
        System.out.println("--------------------------job-one is running--------------------------");
    }
}
