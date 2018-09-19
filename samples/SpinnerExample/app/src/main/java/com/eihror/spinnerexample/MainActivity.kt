package com.eihror.spinnerexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureView()
    }

    private fun configureView() {

        val users = ArrayList<UserModel>()
        users.add(UserModel(id = -1, name = "Select an user"))
        users.add(UserModel(id = 1, name = "User 1"))
        users.add(UserModel(id = 2, name = "User 2"))
        users.add(UserModel(id = 3, name = "User 3"))
        users.add(UserModel(id = 4, name = "User 4"))
        users.add(UserModel(id = 5, name = "User 5"))
        users.add(UserModel(id = 6, name = "User 6"))

        spinner.createAdapter(
                R.layout.item_spinner,
                R.layout.item_spinner_dropdown,
                users,
                {
                    it.let {
                        Toast.makeText(this, users[it].toString() + " selected!", Toast.LENGTH_SHORT).show()
                    }
                },
                { })

    }
}
