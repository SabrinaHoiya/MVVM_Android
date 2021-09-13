package com.example.mvvm.viewmodels


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.RecyclerList
import com.example.mvvm.network.RetroInstance
import com.example.mvvm.network.RetroService
import retrofit2.Call
import retrofit2.Response

class RecyclerActivityViewModel: ViewModel() {

    lateinit var recyclerListData: MutableLiveData<RecyclerList>


    init {
        recyclerListData = MutableLiveData()
    }


    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    fun makeApiCall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI(input)
        call.enqueue(object : retrofit2.Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful) {
                    //recyclerViewAdapter.setListData(response.body()?.items!!)
                    //recyclerViewAdapter.notifyDataSetChanged()
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()

                recyclerListData.postValue(null)
            }
        })
    }
}