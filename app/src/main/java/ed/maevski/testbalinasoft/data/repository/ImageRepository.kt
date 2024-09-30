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


/**** Вынести маппер в отдельный модуль*/
class ImageRepository(
    private val imagesDao: ImagesDao,
    private val imageApi: ImageApi,
    private val tokenStorage: TokenStorage,
    private val imageStorage: ImageStorage,
) : IImageRepository {

    override suspend fun save(image: Image): Pair<Boolean, Int> {
        val result = withContext(Dispatchers.IO) {
            imagesDao.save(mapperImageToImageEntity(image))
        }
        return Pair(true, result.toInt())
    }

    override suspend fun download(): Boolean {
        var count = 0
        val token = tokenStorage.get()
        if (token.isEmpty()) return false
        val result = imageApi.download(token = token, 0)

        println("ImageRepository: download result = $result")

        if (result.isSuccess) {
            val response = result.getOrNull() ?: return false
            val listId = withContext(Dispatchers.IO) {
                imagesDao.trunc()
                imagesDao.insertAll(mapperImagesDtoOutToImagesEntity(response.data))
            }
            count = listId.size
        }
        return count != 0
    }

    /**** Перенести логику в UseCases*/
    override suspend fun upload(image: Image): Pair<Boolean, Int> {
        var id = 0
        val token = tokenStorage.get()
        if (token.isEmpty()) return Pair(false, 0)
        val base64Image = image.uri?.let { imageStorage.get(it)?.toJpeg() } ?: return Pair(false, 0)
        val result = imageApi.upload(
            token = token,
            imageDtoIn = mapperImageToImageDtoIn(image = image, base64Image = base64Image)
        )
        println("upload: $result")
        if (result.isSuccess) {
            val response = result.getOrNull()
            if (response == null) {
                id = imagesDao.save(mapperImageToImageEntity(image)).toInt()
            } else {
                id = withContext(Dispatchers.IO) {
                    imagesDao.save(
                        mapperImageToImageEntity(
                            image = image,
                            imageDtoOut = response.data
                        )
                    )
                }.toInt()

                println("upload: id = $id")

                id = response.data.id

                println("upload: response.data.id = $id")

            }
        }
        return Pair(result.isSuccess, id)
    }

    override suspend fun getImages(): List<Image> {
        val images = withContext(Dispatchers.IO) {
            imagesDao.getImages()
        }
        return mapperImagesEntityToImages(images)
    }

    override suspend fun getImageByIdFromDb(id: Int): Image {
        val image = withContext(Dispatchers.IO) {
            imagesDao.getImageById(id)
        }
        return mapperImageEntityToImage(image)
    }

    override suspend fun delFromApi(id: Int): Boolean {
        val token = tokenStorage.get()
        if (token.isEmpty()) return false
        val result = imageApi.del(id = id, token = token)
        return result.isSuccess
    }

    private fun mapperImagesDtoOutToImagesEntity(listImageDtoOut: List<ImageDtoOut>): List<ImageEntity> {
        return listImageDtoOut.map {
            ImageEntity(
                id = it.id,
                url = it.url,
                date = it.date,
                lat = it.lat,
                lng = it.lng
            )
        }
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
            id = imageDtoOut.id,
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
            date = image.date / 1000,
            lat = image.lat ?: 0.0,
            lng = image.lng ?: 0.0
        )
    }

    private fun mapperImagesEntityToImages(listImageEntity: List<ImageEntity>): List<Image> {
        return listImageEntity.map {
            Image(
                id = it.id,
                uri = it.uri?.let { uri -> Uri.parse(uri) },
                url = it.url,
                date = it.date ?: 0L,
                lat = it.lat,
                lng = it.lng
            )
        }
    }

    private fun mapperImageEntityToImage(imageEntity: ImageEntity): Image {
        return Image(
            id = imageEntity.id,
            uri =imageEntity.uri?.let { uri -> Uri.parse(uri) },
            url = imageEntity.url,
            date = imageEntity.date ?: 0L,
            lat = imageEntity.lat,
            lng = imageEntity.lng
        )

    }
}