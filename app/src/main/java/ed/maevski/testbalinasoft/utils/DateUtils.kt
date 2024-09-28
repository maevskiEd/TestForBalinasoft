package ed.maevski.testbalinasoft.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


fun Long.toTextDateByFormat(dateFormat: String): String {
    val df = SimpleDateFormat(dateFormat, Locale.getDefault())
    return df.format(Date(this))
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toStringByFormat(dateFormat: String): String =
    this.format(DateTimeFormatter.ofPattern(dateFormat))
