package com.example.rick_and_morti

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.rick_and_morti.database.DataBase
import com.example.rick_and_morti.database.DataBaseDao
import com.example.rick_and_morti.databinding.ActivityMainBinding
import com.example.rick_and_morti.models.Results
import com.example.rick_and_morti.utils.Adapter
import com.example.rick_and_morti.viewmodels.MainActivityViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    var listTrx: ArrayList<Results> = ArrayList<Results>()
    lateinit var myListAdapter: Adapter
    private lateinit var charactersDAO: DataBaseDao
    var recyclerView: RecyclerView? = null
    var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val dataBase = Room.databaseBuilder(this, DataBase::class.java, "Database").build()
        charactersDAO = dataBase.dataBaseDao()
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        recyclerView = binding.recyclerview
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        myListAdapter = Adapter(listTrx,{ partItem : Results -> partItemClicked(partItem) })
        binding.recyclerview.adapter = myListAdapter
        viewModel.getCharacters(charactersDAO, currentPage)
        viewModel.charactersModel.observe(this, Observer {
            val gson = Gson()
            Log.i("MyTAG", "*****   ${gson.toJson(it)} books there *****")
            listTrx.addAll(it)
            myListAdapter.notifyDataSetChanged()
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    for (result in it) {
                        if (charactersDAO.getResult(result.id!!) == null) {
                            charactersDAO.addResult(result)
                        }
                    }
                } catch (e: Exception) {
                    Log.i("MyTAG", "*****   ${gson.toJson(e)} books there *****")
                }
            }
        })

        initScrollListener()
    }
    private fun partItemClicked(partItem : Results) {

        val gson = Gson()
        val jsonString = gson.toJson(partItem)
        val intent = Intent(this, DetailActivity::class.java).apply {      putExtra("character",jsonString)  }
        startActivity(intent)
    }



    private fun initScrollListener() {
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listTrx.size - 1) {
                    loadMore()
                }
            }
        })
    }


    private fun loadMore() {
        currentPage += 1
        Toast.makeText(
            applicationContext,
            "Cargando pagina: " + currentPage,
            Toast.LENGTH_SHORT
        ).show()
        viewModel.getCharacters(charactersDAO, currentPage)
    }
}