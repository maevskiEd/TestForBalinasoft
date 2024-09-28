package ed.maevski.testbalinasoft.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


fun ByteArray.toJpeg(): ByteArray? {
    val bitmap: Bitmap? = BitmapFactory.decodeByteArray(this, 0, this.size)
    return if (bitmap != null) {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)
        outputStream.toByteArray()
    } else {
        null
    }
}

fun ByteArray.toBase64String(): String {
    return Base64.encodeToString(this, Base64.DEFAULT)
}