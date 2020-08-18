package time;

import java.util.EnumMap;

public class Period {

    public static Integer periodDivisor(PeriodType type) {
        return calculationPeriods().get(type);
    }

    public enum PeriodType {
        WEEK,
        MONTH,
        YEAR;
    }


    public static EnumMap<PeriodType, Integer> calculationPeriods() {
        EnumMap<PeriodType, Integer> map = new EnumMap<>(PeriodType.class);
        map.put(PeriodType.WEEK, 52);
        map.put(PeriodType.MONTH, 12);
        map.put(PeriodType.YEAR, 1);
        return map;
    }
}
