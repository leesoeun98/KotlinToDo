package com.example.kotlintodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.kotlintodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentManager = supportFragmentManager
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. View Binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Main Fragment 설정
        transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, HomeFragment())
        transaction.commit()

        // 3. ToolBar Setting & Change ToolBar title
        setSupportActionBar(binding.toolBar)
        supportActionBar?.apply {
            title = "Tasks"
            setVisible(true)
        }
    }

    // ToolBar menu selector
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miSetting -> Toast.makeText(this, "Setting", Toast.LENGTH_LONG).show()
        }
        return true
    }

    // ToolBar menu create
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}