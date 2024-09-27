package ed.maevski.testbalinasoft.data.repository

import ed.maevski.testbalinasoft.data.cache.dao.ImagesDao
import ed.maevski.testbalinasoft.data.dto.user.request.SignUserDtoIn
import ed.maevski.testbalinasoft.domain.irepository.IImageRepository
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.models.User

class ImageRepository(
    private val imagesDao: ImagesDao
) : IImageRepository {

    override suspend fun save(image: Image): Boolean {
        return true
    }

    private fun mapperUserToUserDto(user: User): SignUserDtoIn {
        return SignUserDtoIn(
            login = user.login ?: "",
            password = user.password ?: ""
        )
    }
}