package com.example.kotlintodo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintodo.R
import com.example.kotlintodo.databinding.FragmentAddBinding
import com.example.kotlintodo.model.Todo
import com.example.kotlintodo.ui.MainActivity
import com.example.kotlintodo.ui.TodoViewModel

class AddFragment: Fragment() {

    private lateinit var viewModel: TodoViewModel
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. View Model 설정
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()) .get(
            TodoViewModel::class.java)
        // 2. View Binding 설정
        binding = FragmentAddBinding.inflate(inflater, container, false)
        // 3. return Fragment Layout View
        return binding.root
    }

    // Add Fragment -> Home Fragment intent && viewmodel addTask 원활하게 호출하기 위해
    // onStart (Activity 만들어진 후, 사용자에게 보여지는 시점) 에서 Add Button에 onClickListener 추가
    override fun onStart() {
        super.onStart()
        binding.btnAdd.setOnClickListener{
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            // 새로운 item 생성해서 viewmodel의 addTask 호출
            viewModel.addTask(Todo(title, content, false))

            // Add Fragment -> Home Fragment로 intent
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, HomeFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }
}