package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.Image
import kotlinx.coroutines.flow.Flow

interface IImageRepository {
    suspend fun save(image: Image): Pair<Boolean,Long>
    suspend fun getImages(): List<Image>
    suspend fun getImageByIdFromDb(id: Long): Image
    suspend fun upload(image: Image): Pair<Boolean,Long>
}