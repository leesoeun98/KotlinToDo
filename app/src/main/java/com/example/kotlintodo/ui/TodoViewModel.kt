package com.example.kotlintodo.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlintodo.model.Todo

class TodoViewModel : ViewModel() {

    val todoList = MediatorLiveData<List<Todo>>()
    private var datas = mutableListOf<Todo>()
    val doneList = MutableLiveData<List<Todo>>()
    val pendingList = MutableLiveData<List<Todo>>()

    init{
        todoList.addSource(pendingList){
            value->todoList.value = value
        }
        todoList.addSource(doneList){
            value -> todoList.value = value
        }
    }

    private fun setData(data: ArrayList<Todo>){
        pendingList.value = data.filter { x-> !x.isDone }.toList()
        doneList.value = data.filter { x->x.isDone }.toList()
        todoList.value = data
    }
}