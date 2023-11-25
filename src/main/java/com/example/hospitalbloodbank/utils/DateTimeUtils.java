package com.example.hospitalbloodbank.utils;

import java.time.*;

/**
 * @author MaZeYuan
 * @date 2023/10/6 16:46
 */
public class DateTimeUtils {

    public static LocalDate startOfWeek() {
        // 计算本周的开始日期（周一）
        LocalDate localDate = LocalDate.now();
        while (localDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            localDate = localDate.minusDays(1);
        }
        return localDate;
    }

    public static LocalDate endOfWeek() {
        // 计算本周的结束日期（周日）
        LocalDate localDate = LocalDate.now();
        while (localDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
            localDate = localDate.plusDays(1);
        }
        return localDate;
    }

    public static LocalDate endOfWeekToNextMonday() {
        // 计算本周的结束日期（下周一）
        LocalDate localDate = LocalDate.now();
        while (localDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            localDate = localDate.plusDays(1);
        }
        return localDate;
    }

    // 计算从当前时间戳到第二天00:00:00的时间差（毫秒）
    public static long getTimeDifferenceToNextDay(long currentTimestampMillis) {
        // 获取当前日期
        LocalDate currentDate = Instant.ofEpochMilli(currentTimestampMillis).atZone(ZoneId.systemDefault()).toLocalDate();

        // 获取第二天的日期
        LocalDate nextDay = currentDate.plusDays(1);

        // 创建第二天的00:00:00的时间戳
        LocalDateTime nextDayStart = nextDay.atTime(LocalTime.MIDNIGHT);
        Instant nextDayInstant = nextDayStart.atZone(ZoneId.systemDefault()).toInstant();

        // 计算时间差（毫秒）
        return nextDayInstant.toEpochMilli() - currentTimestampMillis;
    }

    // 将 long 转化为 LocalDateTime
    public static LocalDateTime longChangeLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().getDayOfWeek());
        System.out.println(startOfWeek());
        System.out.println(endOfWeekToNextMonday());
    }
}
