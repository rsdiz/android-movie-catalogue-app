package id.rosyid.moviecatalogue.utils

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate

class FormatterTest {
    private val localDate = LocalDate.parse("2021-05-02")
    private val expectedFormat = "02 May 2021"

    @Test
    fun toStringWithPattern() {
        assertEquals(expectedFormat, localDate.toStringWithPattern(FormatPattern.DEFAULT_PATTERN))
    }
}
