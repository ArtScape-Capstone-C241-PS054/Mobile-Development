package com.jovan.artscape.data

import com.jovan.artscape.data.pref.ProvidePreference
import com.jovan.artscape.data.pref.UserModel
import com.jovan.artscape.remote.api.RetrofiClient
import com.jovan.artscape.remote.request.AddUserRequest
import com.jovan.artscape.remote.request.LoginRequest
import com.jovan.artscape.remote.request.RecommendationsPaintingRequest
import com.jovan.artscape.remote.request.UpdateUserRequest
import com.jovan.artscape.remote.response.ClasificationListResponse
import com.jovan.artscape.remote.response.RecommendationsPaintingResponse
import com.jovan.artscape.remote.response.SearchResponse
import com.jovan.artscape.remote.response.address.DistrictResponse
import com.jovan.artscape.remote.response.address.ProvinceResponse
import com.jovan.artscape.remote.response.address.RegenciesResponse
import com.jovan.artscape.remote.response.address.VillageResponse
import com.jovan.artscape.remote.response.painting.AllPaintingResponse
import com.jovan.artscape.remote.response.painting.PaintingDetailsResponse
import com.jovan.artscape.remote.response.painting.UploadResponseSuccess
import com.jovan.artscape.remote.response.user.AllUserResponse
import com.jovan.artscape.remote.response.user.UserResponseSuccess
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class ProvideRepository private constructor(
    private var providePreference: ProvidePreference,
) {
    // Session DataStore
    suspend fun saveSession(user: UserModel) {
        providePreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> = providePreference.getSession()

    suspend fun logout() {
        providePreference.logout()
    }

    // API Region
    suspend fun getProvinces(): List<ProvinceResponse> = RetrofiClient.getApiRegion().getProvince()

    suspend fun getRegencies(id: String): List<RegenciesResponse> = RetrofiClient.getApiRegion().getRegencies(id)

    suspend fun getDistricts(id: String): List<DistrictResponse> = RetrofiClient.getApiRegion().getDistrict(id)

    suspend fun getVillages(id: String): List<VillageResponse> = RetrofiClient.getApiRegion().getVillage(id)

    suspend fun addUserData(addUserRequest: AddUserRequest): Response<UserResponseSuccess> =
        RetrofiClient.getApiArtSpace().addUserData(addUserRequest)

    suspend fun setlogin(loginRequest: LoginRequest): Response<UserResponseSuccess> = RetrofiClient.getApiArtSpace().login(loginRequest)

    suspend fun getUserData(userId: String): Response<AllUserResponse> = RetrofiClient.getApiArtSpace().getUserData(userId)

    suspend fun editUser(
        id: String,
        updateUserRequest: UpdateUserRequest,
    ): Response<UserResponseSuccess> =
        RetrofiClient.getApiArtSpace().editUser(
            id = id,
            updateUserRequest,
        )

    suspend fun uploadPainting(
        photo: MultipartBody.Part,
        title: RequestBody,
        description: RequestBody,
        media: RequestBody,
        genre: RequestBody,
        price: RequestBody,
        yearCreated: RequestBody,
        artistId: RequestBody,
    ): Response<UploadResponseSuccess> =
        RetrofiClient.getApiArtSpace().uploadPainting(
            photo = photo,
            title = title,
            description = description,
            media = media,
            genre = genre,
            price = price,
            yearCreated = yearCreated,
            artistId = artistId,
        )

    suspend fun getAllpainting(): Response<List<AllPaintingResponse>> = RetrofiClient.getApiArtSpace().getAllPainting()

    suspend fun getPaintingDetail(id: String): Response<PaintingDetailsResponse> =
        RetrofiClient.getApiArtSpace().getPaintingDetail(
            id = id,
        )

    suspend fun search(query: String): Response<SearchResponse> = RetrofiClient.getApiArtSpace().search(query)

    suspend fun genreClasification(painting: MultipartBody.Part): Response<ClasificationListResponse> =
        RetrofiClient.getApiGenreClasification().genreClasification(painting)

    suspend fun recommendPainting(recommendPaintingRequest: RecommendationsPaintingRequest): Response<RecommendationsPaintingResponse> =
        RetrofiClient.getApiPaintingRecomendation().recommendPainting(recommendPaintingRequest)

    companion object {
        @Volatile
        private var instance: ProvideRepository? = null

        fun getInstance(providePreference: ProvidePreference): ProvideRepository =
            instance ?: synchronized(this) {
                instance ?: ProvideRepository(providePreference)
            }.also { instance = it }
    }
}
