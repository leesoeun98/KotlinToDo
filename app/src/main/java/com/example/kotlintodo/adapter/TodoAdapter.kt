package com.example.kotlintodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo.databinding.ItemTodoBinding
import com.example.kotlintodo.model.Todo

class TodoAdapter(var Todos: List<Todo>): RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {

    private lateinit var itemBinding: ItemTodoBinding

    inner class ToDoViewHolder(private val itemBinding: ItemTodoBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data:Todo) {
            itemBinding.tvTitle.text = data.title
            itemBinding.tvContent.text = data.content
            itemBinding.cbIsDone.isChecked = data.isDone
        }
    }

    // RecyclerView.Adapter 상속 시 무조건 override 해야하는 fun - viewHolder에 layout inflate 하는 함수 (ViewBinding 사용)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        itemBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(itemBinding)
    }

    // RecyclerView.Adapter 상속 시 무조건 override 해야하는 fun - viewHolder에 각 view를 bind하는 함수
    // ToDoViewHolder내에 bind함수 정의했으므로, 각 Todos[position]인 item data랑 view를 bind하면 됨
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(Todos[position])
    }

    // RecyclerView.Adapter 상속 시 무조건 override 해야하는 fun - 보통 Todos.size를 return, RecyclerView내의 item 개수 return하는 함수
    override fun getItemCount(): Int {
        return Todos.size
    }

    // Todos list의 각 item id return
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setDoneData(doneData: List<Todo>){
        Todos = doneData
        notifyDataSetChanged()
    }

    fun setPendingData(pendingData: List<Todo>){
        Todos = pendingData
        notifyDataSetChanged()
    }
}