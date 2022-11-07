package com.example.shake.api.auto;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Scheduler {

    public static final String SEOUL_ZONE = "Asia/Seoul";
    public static final int ONE_DAY = 1;
    public static final int ONE_DAY_AS_SECOND = 24 * 60 * 60;
    public static final int SINGLE_POOL_SIZE = 1;

    private final ScheduledExecutorService scheduler;

    // 생성자 초기화
    public Scheduler() {
        this.scheduler = Executors.newScheduledThreadPool(SINGLE_POOL_SIZE);
    }

    // 현재 시간과 실행 시간을 구한다.
    public void execute(Runnable command, int hour, int minute, int second) {
        ZonedDateTime now = ZonedDateTime.of(now(), ZoneId.of(SEOUL_ZONE));
        ZonedDateTime nextExecutionTime = this.getNextExecutionTime(hour, minute, second, now);
        scheduler.scheduleAtFixedRate(command, this.getInitialExecutionTime(now, nextExecutionTime), ONE_DAY_AS_SECOND, SECONDS);
    }

    private ZonedDateTime getNextExecutionTime(int hour, int minute, int second, ZonedDateTime now) {
        ZonedDateTime nextExecutionTime;
        nextExecutionTime = now
                .withHour(hour)
                .withMinute(minute)
                .withSecond(second);

        if (this.isOverDay(now, nextExecutionTime))
            nextExecutionTime = nextExecutionTime.plusDays(ONE_DAY);

        return nextExecutionTime;
    }

    private boolean isOverDay(ZonedDateTime zonedNow, ZonedDateTime nextExecutionTime) {
        return zonedNow.compareTo(nextExecutionTime) > 0;
    }
    // 두 시간 차이를 구한다. -> 지금부터 실행까지 남은 시간
    private long getInitialExecutionTime(ZonedDateTime now, ZonedDateTime nextExecutionTime) {
        Duration duration = Duration.between(now, nextExecutionTime);
        return duration.getSeconds();
    }

}