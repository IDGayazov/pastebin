package service.CleanUpService;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CleanUpTaskScheduler implements ServletContextListener {

    private static final long INITIAL_DELAY = 0;
    private static final long PERIOD = 24;

    private static ScheduledExecutorService scheduler;
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new CleanUpTask(), INITIAL_DELAY, PERIOD, TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
