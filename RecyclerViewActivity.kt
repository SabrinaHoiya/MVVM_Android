package com.example.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.of
import androidx.lifecycle.ViewModelStores.of
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.mvvm.network.RetroInstance
import com.example.mvvm.network.RetroService
import com.example.mvvm.viewmodels.RecyclerActivityViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*
import retrofit2.Call
import retrofit2.Response
import java.util.Optional.of


class RecyclerViewActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)


        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }



    fun createData() {
        /*val item = ArrayList<RecyclerData>()
       item.add(RecyclerData("Java", "Java description"))
       item.add(RecyclerData("C++", "C++ description"))
       item.add(RecyclerData("Android", "Android description"))
       item.add(RecyclerData("iOS", "iOS description"))
       item.add(RecyclerData("PHP", "PHP description"))
       item.add(RecyclerData("Kotlin", "Kotlinb description"))
       recyclerViewAdapter.setListData(item)
       recyclerViewAdapter.notifyDataSetChanged()*/

        /* val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
         val call = retroInstance.getDataFromAPI("atlanta")
         call.enqueue(object : retrofit2.Callback<RecyclerList>{
             override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                 if(response.isSuccessful) {
                     recyclerViewAdapter.setListData(response.body()?.items!!)
                     recyclerViewAdapter.notifyDataSetChanged()
                 }
             }
             override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                 Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
             }
         })*/


        val viewModel = ViewModelProviders.of(this).get(RecyclerActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList>{

            if(it != null) {
                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()

            } else {
                Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }

        })
        searchButton.setOnClickListener {
            viewModel.makeApiCall(searchBoxId.text.toString())
        }

    }
}