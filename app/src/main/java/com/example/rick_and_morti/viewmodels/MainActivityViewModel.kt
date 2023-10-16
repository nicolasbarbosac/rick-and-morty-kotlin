package com.example.rick_and_morti.viewmodels

import androidx.lifecycle.*
import com.example.rick_and_morti.database.DataBaseDao
import com.example.rick_and_morti.retrofit.ServiceClient
import com.example.rick_and_morti.models.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel constructor(

) : ViewModel() {


    val charactersModel = MutableLiveData<ArrayList<Results>>()

    fun getCharacters(charactersDao: DataBaseDao, currentPage: Int) {

        ServiceClient.getCharacters(currentPage) {
            if (it != null) {
                charactersModel.postValue(it.results)
            } else {
                if (currentPage == 1) {
                    getresults(charactersDao)
                }
            }
        }
    }


    fun getresults(bookDao: DataBaseDao) {
        GlobalScope.launch(Dispatchers.IO) {
            charactersModel.postValue(bookDao.getResults() as ArrayList<Results>?)
        }
    }
}