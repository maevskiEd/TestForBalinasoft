package ed.maevski.testbalinasoft.data.api

import ed.maevski.testbalinasoft.data.dto.ResponseDto
import ed.maevski.testbalinasoft.data.dto.comment.request.CommentDtoIn
import ed.maevski.testbalinasoft.data.dto.comment.responce.CommentDtoOut
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApi {
    @POST("/api/image/{imageId}/comment")
    suspend fun upload(
        @Header("Access-Token") token: String,
        @Body commentDtoIn: CommentDtoIn,
        @Path("imageId") imageId: Int
    ): Result<ResponseDto<CommentDtoOut>>

    @GET("/api/image/{imageId}/comment")
    suspend fun download(
        @Header("Access-Token") token: String,
        @Path("imageId") imageId: Int,
        @Query("page") page: Int
    ): Result<ResponseDto<CommentDtoOut>>
}
