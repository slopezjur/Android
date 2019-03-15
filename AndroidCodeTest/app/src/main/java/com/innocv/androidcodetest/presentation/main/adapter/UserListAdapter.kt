package com.innocv.androidcodetest.presentation.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.innocv.androidcodetest.R
import com.innocv.androidcodetest.domain.User
import com.innocv.androidcodetest.presentation.base.utils.formatDate
import com.innocv.androidcodetest.presentation.base.utils.showCircleImage

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var originalUserList: MutableList<User> = mutableListOf()
    private var userList: MutableList<User> = mutableListOf()
    var onItemClick: ((User) -> Unit)? = null
    var onClickDelete: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(userList[position])

    override fun getItemCount(): Int = userList.size

    fun swapData(userList: List<User>) {
        this.originalUserList = userList.toMutableList()
        this.userList = userList.toMutableList()
        notifyDataSetChanged()
    }

    fun notifyItemDeleted(user: User) {
        this.userList.remove(user)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var name: TextView = itemView.findViewById(R.id.textName)
        private var birthdate: TextView = itemView.findViewById(R.id.textBirthdate)
        private var imageProfile: ImageView = itemView.findViewById(R.id.imageProfile)
        private var textOptionMenu: TextView = itemView.findViewById(R.id.textOptionMenu)

        fun bind(user: User) {
            name.text = user.name
            birthdate.text = user.birthdate.formatDate()
            imageProfile.showCircleImage(itemView.context)

            itemView.setOnClickListener { onItemClick?.invoke(user) }

            val menu = configureMenu(user)
            textOptionMenu.setOnClickListener { menu.show() }
        }

        private fun configureMenu(user: User): PopupMenu {

            val popupMenu = PopupMenu(itemView.context, textOptionMenu)
            popupMenu.inflate(R.menu.menu_user_item)

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_delete -> {
                        onClickDelete?.invoke(user)
                        true
                    }
                    else -> false
                }
            }

            return popupMenu
        }
    }
}