package com.ashush.filmopoisk_raw.presentation.nav_items.launch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LaunchViewModel @Inject constructor(private var retrofitImpl: RetrofitImpl) : ViewModel() {

    val requestResult = MutableLiveData(false)
    val requestError = MutableLiveData<String>()

    fun doRequest() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val retrofitImpl = retrofitImpl.retrofitService
                retrofitImpl.getConfiguration(DataConfig.API_KEY).enqueue(
                    object : Callback<DataConfigurationModel> {
                        override fun onFailure(call: Call<DataConfigurationModel>, t: Throwable) {
                            requestError.value = t.toString()
                            requestResult.value = true
                        }

                        override fun onResponse(
                            call: Call<DataConfigurationModel>,
                            response: Response<DataConfigurationModel>
                        ) {
                            DataConfig.config = response.body()
                            requestResult.value = true
                        }

                    }
                )
            }
        }
    }
}