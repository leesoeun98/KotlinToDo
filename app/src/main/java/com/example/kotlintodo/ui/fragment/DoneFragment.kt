package com.example.kotlintodo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintodo.databinding.FragmentDoneBinding

class DoneFragment: Fragment() {
    private lateinit var binding: FragmentDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. View Binding 설정
        binding = FragmentDoneBinding.inflate(inflater, container, false)

        // 2. recyclerView에 Layout 꼭 설정하기 (안그러면 화면에 표시 안되고 skip됨)
        binding!!.rvDone.layoutManager = LinearLayoutManager(activity)

        // 3. return Fragment Layout View
        return binding.root
    }
}