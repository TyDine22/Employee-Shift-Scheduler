public class WorkingShift {

}
class FullTime extends WorkingShift {
    static class MorningShift {
        static final double START_HOUR = 7.0;
        static final double END_HOUR = 17.0;
    }

    static class NightShift {
        static final double START_HOUR = 23.0;
        static final double END_HOUR = 6.0;
    }
}

class PartTime extends WorkingShift {
    static class MorningShift {
        static final double START_HOUR = 8.0;
        static final double END_HOUR = 12.0;
    }

    static class AfternoonShift {
        static final double START_HOUR = 13.0;
        static final double END_HOUR = 17.0;
    }

    static class NightShift {
        static final double START_HOUR = 18.0;
        static final double END_HOUR = 22.0;
    }
}
