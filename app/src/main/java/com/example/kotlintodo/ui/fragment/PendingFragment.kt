package com.example.kotlintodo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintodo.R
import com.example.kotlintodo.adapter.TodoAdapter
import com.example.kotlintodo.databinding.FragmentPendingBinding
import com.example.kotlintodo.model.Todo
import com.example.kotlintodo.ui.MainActivity
import com.example.kotlintodo.ui.TodoViewModel

class PendingFragment: Fragment() {

    private lateinit var viewModel: TodoViewModel
    private lateinit var binding: FragmentPendingBinding
    private lateinit var adapter : TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. View Model 설정
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()) .get(
            TodoViewModel::class.java)

        // 2. View Binding 설정
        binding = FragmentPendingBinding.inflate(inflater, container, false)

        // 3. adapter 설정 (list를 인자로)
        var pendingList = viewModel.pendingList.value
        adapter = TodoAdapter(pendingList?: emptyList<Todo>())
        adapter.setHasStableIds(true)
        binding!!.rvPending.adapter = adapter

        // 4. recyclerView에 Layout 꼭 설정하기 (안그러면 화면에 표시 안되고 skip됨)
        binding!!.rvPending.layoutManager = LinearLayoutManager(activity)

        // 5. return Fragment Layout View
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.todoList.observe(viewLifecycleOwner, Observer{
            binding.rvPending.post(Runnable { it.filter { x -> !x.isDone } })
        })
    }

    override fun onStart() {
        super.onStart()
        binding.btnAddTask.setOnClickListener{
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, AddFragment())
            transaction.commit()
        }
    }
}