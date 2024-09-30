package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.Image
import kotlinx.coroutines.flow.Flow

interface IImageRepository {
    suspend fun save(image: Image): Pair<Boolean,Int>
    suspend fun getImages(): List<Image>
    suspend fun getImageByIdFromDb(id: Int): Image
    suspend fun delFromApi(id: Int): Boolean
    suspend fun upload(image: Image): Pair<Boolean,Int>
    suspend fun download(): Boolean
}