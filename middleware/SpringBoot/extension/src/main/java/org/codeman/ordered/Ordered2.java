package org.codeman.ordered;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author hdgaadd
 * created on 2022/12/17
 */
@Slf4j
@Component
public class Ordered2 implements ApplicationRunner, Ordered {

    @Override
    public void run(ApplicationArguments args) throws Exception {
       log.info("this is Ordered2");
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
