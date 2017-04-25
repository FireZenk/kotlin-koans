package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val num: Int)

operator fun TimeInterval.times(num: Int) = RepeatedTimeInterval(this, num)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) =
        addTimeIntervals(timeIntervals.timeInterval, timeIntervals.num)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {

    class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {

        var current: MyDate = dateRange.start

        override fun next(): MyDate {
            val result = current
            current = current.nextDay()
            return result
        }

        override fun hasNext() = current <= dateRange.endInclusive
    }

    override fun iterator() = DateIterator(this)

    operator fun contains(d: MyDate) = start < d && d < endInclusive
}
