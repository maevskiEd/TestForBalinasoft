package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.Image
import kotlinx.coroutines.flow.Flow

interface IImageRepository {
    suspend fun save(image: Image): Boolean
    suspend fun getImages(): List<Image>
    suspend fun upload(image: Image): Boolean
}