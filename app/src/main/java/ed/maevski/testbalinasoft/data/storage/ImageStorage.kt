package ed.maevski.testbalinasoft.data.storage

import android.content.Context
import android.net.Uri
import ed.maevski.testbalinasoft.domain.istorage.IImageStorage
import javax.inject.Inject

class ImageStorage @Inject constructor(
    private val context: Context
) : IImageStorage {
    override fun get(uri: Uri): ByteArray? {
        val inputStream = context.contentResolver.openInputStream(uri)
//        val data = inputStream?.readBytes()
//        inputStream?.close()
        val data = inputStream?.use { it.readBytes() }
        return data
    }
}
