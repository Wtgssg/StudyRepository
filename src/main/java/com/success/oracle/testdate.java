package com.success.oracle;

import com.success.oracle.entity.User;
import com.success.oracle.exception.CustomException;
import com.success.oracle.util.CheckUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class testdate {
    public static void main(String[] args) {


        ExpressionParser parser = new SpelExpressionParser();
        User user = new User();
        String param = "";
        String helloWorld = (String) parser.parseExpression("#user'["+param+"]'").getValue();
//        double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
//        System.out.println(helloWorld);
//        System.out.println(avogadrosNumber);

//        LocalDateTime dt1 = LocalDateTime.now();
//        LocalDateTime dtbir = LocalDateTime.of(2026,8,5,0,0);
//        long hours = ChronoUnit.HOURS.between(dt1, dtbir);     // 28小时
//        long days = ChronoUnit.DAYS.between(dt1, dtbir);       // 1天
//        long minutes = ChronoUnit.MINUTES.between(dt1, dtbir); // 1710分钟
//        long seconds = ChronoUnit.SECONDS.between(dt1, dtbir); // 102600秒
//        long nanos = ChronoUnit.NANOS.between(dt1, dtbir);     // 纳秒数
//
//        System.out.println(hours+" "+days+" "+minutes+" "+seconds+" "+nanos);
//        System.out.println(dt);
//        System.out.println(dt.getYear());
//        System.out.println(dt.getMonth());
//        System.out.println(dt.getMonthValue());
//        System.out.println(dt.getDayOfMonth());
//        System.out.println(dt.getDayOfWeek());
//        System.out.println(dt.getDayOfYear());
//        System.out.println(dt.getHour());
//        System.out.println(dt.getMinute());
//        System.out.println(dt.getSecond());
//        System.out.println(dt.getNano());


//        dt.with(TemporalAdjusters.firstDayOfMonth());
//        dt.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
//        dt.with(TemporalAdjusters.)
        //User user;//Variable 'user' might not have been initialized
//        User nullUser ;
//        User userz = new User();
//        userz.setUsername("zhangsan");
//        Optional<User> nullOptional = Optional.empty();
//        Optional<User> userOptional = Optional.of(userz);
//        Optional<User> users = Optional.ofNullable(userz);
//
//        System.out.println(users.map(User::getUsername));
//        System.out.println(users.flatMap(users));
//        users.filter()
//        Object obj = userOptional.get();
//        //Object obj1 = nullOptional.get();//NoSuchElementException: No value present
//        System.out.println(obj.toString());

//        List<String> list = new ArrayList<>();
//        list.add("甲");
//        list.add("乙");
//        list.add("丙");
//
//        ListIterator<String> itlist = list.listIterator();
//        System.out.println(itlist.next());
//        System.out.println(itlist.next());
//        System.out.println(itlist.next());
//
//        System.out.println(itlist.previous());
//        System.out.println(itlist.previous());
//        System.out.println(itlist.previousIndex());
//        System.out.println(itlist.previousIndex());


//        System.out.println(StringUtils.isEmpty(" "));
//        System.out.println(StringUtils.defaultIfEmpty("","未知"));
//        System.out.println(StringUtils.substringAfter("zhangsan:张三", ":"));
//        System.out.println(StringUtils.join(list, ","));


//        for (String s :list) {
//            System.out.println(s);
//        }

//        //读取当前时间
//        LocalDate date = LocalDate.now();
//        System.out.println(date);
//
//        //两种指定时间的方式
//        LocalDate specificatedate1 = LocalDate.of(2008,2,1);
//        System.out.println(specificatedate1);
//        LocalDate specofocatedate2 = LocalDate.of(2008, Month.FEBRUARY,2);
//        System.out.println(specofocatedate2);
//
//        //从字符串解析日期
//        LocalDate prasedate = LocalDate.parse("2024-05-17");//此处不能用"5"代表月份,必须用"05"
//        System.out.println(prasedate);
//
//        //从localdate获取年月日
//        int year = date.getYear();
//        Month month = date.getMonth();//month枚举类
//        int day = date.getDayOfMonth();
//
//        System.out.println(year+"年"+month+"月"+day+"日");
//
//        DayOfWeek dayOfWeek = date.getDayOfWeek();//获取今天星期几
//        System.out.println(dayOfWeek);
//
//        int lengthOfMonth = specificatedate1.lengthOfMonth();
//        int lengthOfyear = specificatedate1.lengthOfYear();
//        System.out.println("2008年2月有"+lengthOfMonth+"天");
//        System.out.println("2008年有"+lengthOfyear+"天");

//        System.out.println("明天日期是"+date.plusDays(1));
//        System.out.println("下一个星期的日期是"+date.plusWeeks(1));
//        System.out.println("昨天是"+date.minusDays(1));

//        LocalDate date1 = LocalDate.of(2023, 5, 15);
//        LocalDate date2 = LocalDate.of(2023, 6, 20);
//
//        boolean isBefore = date1.isBefore(date2); // true
//        boolean isAfter = date1.isAfter(date2);   // false
//        boolean isEqual = date1.isEqual(date2);   // false

        // 1. 创建 LocalDate 实例
//        System.out.println("=== 创建 LocalDate 实例 ===");
//        LocalDate today = LocalDate.now();
//        LocalDate specificDate = LocalDate.of(2023, Month.JULY, 15);
//        LocalDate parsedDate = LocalDate.parse("2023-08-20");
//
//        System.out.println("当前日期: " + today);
//        System.out.println("特定日期: " + specificDate);
//        System.out.println("解析日期: " + parsedDate);
//
//        // 2. 获取日期信息
//        System.out.println("\n=== 获取日期信息 ===");
//        System.out.println("年: " + today.getYear());
//        System.out.println("月: " + today.getMonth() + " (" + today.getMonthValue() + ")");
//        System.out.println("日: " + today.getDayOfMonth());
//        System.out.println("星期: " + today.getDayOfWeek());
//        System.out.println("一年中的第几天: " + today.getDayOfYear());
//        System.out.println("月份天数: " + today.lengthOfMonth());
//        System.out.println("年份天数: " + today.lengthOfYear());
//        System.out.println("是否闰年: " + today.isLeapYear());
//
//        // 3. 日期计算
//        System.out.println("\n=== 日期计算 ===");
//        System.out.println("加5天: " + today.plusDays(5));
//        System.out.println("加2周: " + today.plusWeeks(2));
//        System.out.println("加3个月: " + today.plusMonths(3));
//        System.out.println("减1年: " + today.minusYears(1));
//
//        System.out.println("下个月的第一天: " + today.plusMonths(1).withDayOfMonth(1));
//        System.out.println("本月的最后一天: " + today.withDayOfMonth(today.lengthOfMonth()));
//
//        // 4. 日期比较
//        System.out.println("\n=== 日期比较 ===");
//        LocalDate date1 = LocalDate.of(2023, 6, 15);
//        LocalDate date2 = LocalDate.of(2023, 6, 20);
//
//        System.out.println(date1 + " 在 " + date2 + " 之前? " + date1.isBefore(date2));
//        System.out.println(date1 + " 在 " + date2 + " 之后? " + date1.isAfter(date2));
//        System.out.println(date1 + " 等于 " + date2 + "? " + date1.isEqual(date2));
//
//        // 5. 计算日期差
//        System.out.println("\n=== 计算日期差 ===");
//        LocalDate startDate = LocalDate.of(2023, 1, 1);
//        LocalDate endDate = LocalDate.of(2023, 12, 31);
//
//        System.out.println("两个日期之间的天数: " + ChronoUnit.DAYS.between(startDate, endDate));
//        System.out.println("两个日期之间的月数: " + ChronoUnit.MONTHS.between(startDate, endDate));
//        System.out.println("两个日期之间的年数: " + ChronoUnit.YEARS.between(startDate, endDate));
//
//        // 6. 日期格式化
//        System.out.println("\n=== 日期格式化 ===");
//        DateTimeFormatter basicFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        DateTimeFormatter chineseFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 EEEE", Locale.CHINA);
//        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
//
//        System.out.println("基本格式: " + today.format(basicFormatter));
//        System.out.println("中文格式: " + today.format(chineseFormatter));
//        System.out.println("英文格式: " + today.format(customFormatter));
//
//        // 7. 其他实用方法
//        System.out.println("\n=== 其他实用方法 ===");
//        System.out.println("调整到下个星期一: " + today.with(DayOfWeek.MONDAY));
//        System.out.println("是否是周末: " +
//                (today.getDayOfWeek() == DayOfWeek.SATURDAY || today.getDayOfWeek() == DayOfWeek.SUNDAY));

        // 8. 日期范围迭代
//        System.out.println("\n=== 日期范围迭代 ===");
//        System.out.println("接下来7天的日期:");
//        today.datesUntil(today.plusDays(7))
//                .forEach(System.out::println);


//        User user = new User();
//        user.setUsername("");
//        user.setUserId(1L);
//        try {
//            CheckUtil.check("username",user.getUsername()).notBlank().throwIfInvalid("404","username不能为空");
//        }catch (CustomException exception){
//            System.out.println(exception.getOutCode()+"\n"+exception.getOutMsg());
//        }




    }
}
