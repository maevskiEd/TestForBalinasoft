package ed.maevski.testbalinasoft.domain.istorage

import android.net.Uri

interface IImageStorage {
    fun get(uri: Uri): ByteArray?
}
