package ed.maevski.testbalinasoft.data.repository

import ed.maevski.testbalinasoft.data.cache.dao.ImagesDao
import ed.maevski.testbalinasoft.data.cache.entity.ImageEntity
import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepository(
    private val imagesDao: ImagesDao
) : IImageRepository {

    override suspend fun save(image: Image): Boolean {
        withContext(Dispatchers.IO) {
            imagesDao.save(mapperImageToImageEntity(image))
        }
        return true
    }

    private fun mapperImageToImageEntity(image: Image): ImageEntity {
        return ImageEntity(
            uri = image.uri.path,
            date = image.date,
            lat = image.lat,
            lng = image.lng
        )
    }
}