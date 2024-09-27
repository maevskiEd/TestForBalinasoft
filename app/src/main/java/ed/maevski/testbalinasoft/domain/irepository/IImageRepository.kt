package ed.maevski.testbalinasoft.domain.irepository

import ed.maevski.testbalinasoft.domain.models.Image

interface IImageRepository {
    suspend fun save(image: Image): Boolean
}