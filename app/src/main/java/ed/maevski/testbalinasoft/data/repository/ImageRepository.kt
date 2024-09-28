package ed.maevski.testbalinasoft.data.repository

import android.net.Uri
import ed.maevski.testbalinasoft.data.api.ImageApi
import ed.maevski.testbalinasoft.data.cache.dao.ImagesDao
import ed.maevski.testbalinasoft.data.cache.entity.ImageEntity
import ed.maevski.testbalinasoft.data.dto.photo.request.ImageDtoIn
import ed.maevski.testbalinasoft.data.dto.photo.responce.ImageDtoOut
import ed.maevski.testbalinasoft.data.storage.ImageStorage
import ed.maevski.testbalinasoft.data.storage.TokenStorage
import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.utils.toBase64String
import ed.maevski.testbalinasoft.utils.toJpeg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepository(
    private val imagesDao: ImagesDao,
    private val imageApi: ImageApi,
    private val tokenStorage: TokenStorage,
    private val imageStorage: ImageStorage,

    ) : IImageRepository {

    override suspend fun save(image: Image): Boolean {
        withContext(Dispatchers.IO) {
            imagesDao.save(mapperImageToImageEntity(image))
        }
        return true
    }

    override suspend fun upload(image: Image): Boolean {
        val token = tokenStorage.get()
        if (token.isEmpty()) return false
        val base64Image = imageStorage.get(image.uri)?.toJpeg() ?: return false
        val result = imageApi.upload(
            token = token,
            imageDtoIn = mapperImageToImageDtoIn(image = image, base64Image = base64Image)
        )
        println("upload: $result")
        if (result.isSuccess) {
            val response = result.getOrNull()
            if (response == null) imagesDao.save(mapperImageToImageEntity(image))
            else {
                val images = withContext(Dispatchers.IO) {
                    imagesDao.save(mapperImageToImageEntity(image = image, imageDtoOut = response.data))
                }
            }
        }
        return true
    }

    override suspend fun getImages(): List<Image> {
        val images = withContext(Dispatchers.IO) {
            imagesDao.getImages()
        }
        return mapperImagesEntityToImages(images)
    }

    private fun mapperImageToImageEntity(image: Image): ImageEntity {
        return ImageEntity(
            uri = image.uri.toString(),
            date = image.date,
            lat = image.lat,
            lng = image.lng
        )
    }

    private fun mapperImageToImageEntity(
        image: Image,
        imageDtoOut: ImageDtoOut
    ): ImageEntity {
        return ImageEntity(
            id = imageDtoOut.id.toLong(),
            uri = image.uri.toString(),
            url = imageDtoOut.url,
            date = image.date,
            lat = image.lat,
            lng = image.lng
        )
    }

    private fun mapperImageToImageDtoIn(image: Image, base64Image: ByteArray): ImageDtoIn {
        return ImageDtoIn(
            base64Image = base64Image.toBase64String(),
            date = image.date/1000,
            lat = image.lat ?: 0.0,
            lng = image.lng ?: 0.0
        )
    }

    private fun mapperImagesEntityToImages(listImageEntity: List<ImageEntity>): List<Image> {
        return listImageEntity.map {
            Image(
                uri = Uri.parse(it.uri),
                date = it.date ?: 0L,
                lat = it.lat,
                lng = it.lng
            )
        }
    }
}