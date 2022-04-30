package com.example.kotlintodo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintodo.adapter.TodoAdapter
import com.example.kotlintodo.databinding.FragmentDoneBinding
import com.example.kotlintodo.model.Todo
import com.example.kotlintodo.ui.TodoViewModel

class DoneFragment: Fragment() {

    private lateinit var viewModel: TodoViewModel
    private lateinit var binding: FragmentDoneBinding
    private lateinit var adapter : TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 1. View Model 설정
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()) .get(
            TodoViewModel::class.java)

        // 2. View Binding 설정
        binding = FragmentDoneBinding.inflate(inflater, container, false)

        // 3. adapter 설정
        var doneList = viewModel.doneList.value
        adapter = TodoAdapter(doneList?: emptyList<Todo>(),
             onClickDeleteButton={ viewModel.deleteTask(it)})
        adapter.setHasStableIds(true)
        binding.rvDone.adapter = adapter

        // 4. recyclerView에 Layout 꼭 설정하기 (안그러면 화면에 표시 안되고 skip됨)
        binding.rvDone.layoutManager = LinearLayoutManager(activity)

        // 5. return Fragment Layout View
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.todoList.observe(viewLifecycleOwner, Observer{
            binding.rvDone.post(Runnable { adapter.setDoneData(it.filter { x->x.isDone }) })
        })
    }
}