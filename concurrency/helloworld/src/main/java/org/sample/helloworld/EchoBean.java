/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.helloworld;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import org.glassfish.concurrent.ri.AbstractManagedExecutorService;
import org.glassfish.concurrent.ri.ManagedExecutorServiceImpl;
import org.glassfish.concurrent.ri.ManagedThreadFactoryImpl;

/**
 *
 * @author arungup
 */
@Stateless
public class EchoBean {

//    @Resource(name = "concurrent/BatchExecutor")
//    ManagedExecutorService executor;
    ManagedExecutorServiceImpl executor = new ManagedExecutorServiceImpl("concurrent/BatchExecutor",
            new ManagedThreadFactoryImpl("factory"),
            2000,
            true,
            5,
            10,
            1000,
            TimeUnit.MILLISECONDS,
            4,
            null,
            AbstractManagedExecutorService.RejectPolicy.ABORT,
            AbstractManagedExecutorService.RunLocation.LOCAL,
            true);
}
