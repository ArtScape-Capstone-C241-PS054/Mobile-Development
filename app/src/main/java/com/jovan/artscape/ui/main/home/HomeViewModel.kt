package com.jovan.artscape.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jovan.artscape.data.ProvideRepository
import com.jovan.artscape.data.pref.UserModel
import com.jovan.artscape.remote.response.painting.ErrorResponse
import com.jovan.artscape.remote.response.painting.PaintingDetails
import com.jovan.artscape.remote.response.painting.PaintingResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProvideRepository) : ViewModel() {
    private val paintingResponse = MutableLiveData<PaintingResponse<List<PaintingDetails>>>()

    fun getSesion(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun setAllPainting() {
        viewModelScope.launch {
            Log.d("PARAM", "SetAllPainting")
            val response = repository.getAllpainting()
            if (response.isSuccessful) {
                paintingResponse.value = PaintingResponse.Success(response.body()!!)
                Log.d("RESPONSE isSuccessful", "addUser: ${response.body()}")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                paintingResponse.value = PaintingResponse.Error(errorResponse.error)
                Log.d("RESPONSE notSuccessful", "addUser: ${errorResponse.error}")
            }
        }
    }

    fun getAllPainting(): MutableLiveData<PaintingResponse<List<PaintingDetails>>> {
        return paintingResponse
    }
}