package id.rosyid.moviecatalogue.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toStringWithPattern(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}

object FormatPattern {
    const val DEFAULT_PATTERN = "dd MMMM yyyy"
}
