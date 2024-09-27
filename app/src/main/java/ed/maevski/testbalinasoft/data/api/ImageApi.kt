package ed.maevski.testbalinasoft.data.api

import ed.maevski.testbalinasoft.data.dto.photo.request.ImageDtoIn
import ed.maevski.testbalinasoft.data.dto.photo.responce.ImageDtoOut
import ed.maevski.testbalinasoft.data.dto.user.response.ResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageApi {
    @POST("/api/image")
    suspend fun save(
        @Header("Access-Token") token: String,
        @Body imageDtoIn: ImageDtoIn
    ): Result<ImageDtoOut>

    @GET("/api/image")
    suspend fun get(
        @Header("Access-Token") token: String,
        @Query("page") from: Int
    ): Result<ResponseDto>

    @DELETE("/api/image/{id}")
    suspend fun del(
        @Header("Access-Token") token: String,
        @Path("id") from: Int
    ): Result<ResponseDto>
}
