package ed.maevski.testbalinasoft.data.api

import ed.maevski.testbalinasoft.data.dto.user.request.SignUserDtoIn
import ed.maevski.testbalinasoft.data.dto.user.response.ResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/account/signup")
    suspend fun signup(@Body userDto: SignUserDtoIn): Result<ResponseDto>

    @POST("/api/account/signin")
    suspend fun signin(@Body userDto: SignUserDtoIn): Result<ResponseDto>
}
