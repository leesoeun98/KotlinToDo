package com.example.kotlintodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlintodo.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 1. View Binding 설정
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 2. View Pager의 FragmentStateAdapter 설정
        binding.vpTodo.adapter = activity?.let { ViewPagerFragmentStateAdapter(it) }

        // 3. View Pager의 Orientation 설정
        binding.vpTodo.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 4. TabLayout + ViewPager2 연동 (ViewPager2에 Adapter 연동 후에)
         TabLayoutMediator(binding.tabLayout, binding.vpTodo){ tab, position ->
             tab.text = getTabTitle(position)
         }.attach()

        // 5. return Fragment Layout View
        return binding.root
    }

    // Tab & ViewPager 연동 및 Tab title 설정
    private fun getTabTitle(position: Int): String? {
        return when (position) {
            0 -> "PENDING"
            1 -> "DONE"
            else -> null
        }
    }
}