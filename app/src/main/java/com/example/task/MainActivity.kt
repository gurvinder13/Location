package com.example.task

import ClickItemListener
import UserResponse
import ApiClient
import Users
import UserAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ClickItemListener {
    private lateinit var list: List<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    fun showData(userList: List<Users>) {

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = UserAdapter( userList,this)
    }

    fun getData() {
        val userApi = ApiClient.userAPI

        val call =
            userApi.getBookinglist("VI020PE0016", "All", "Employee", "2020-07-25")

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, userResponse: Response<UserResponse>) {
                if (userResponse.isSuccessful) {
                    if (userResponse.body()?.status_code == 200) {
                        val responseData = userResponse.body()
                        if (responseData?.unassigned != null) {
                            list = responseData.unassigned
                            showData(list)
                        }


                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }
        })
    }

    override fun onClick(section: Int, position: Int) {
        if (section == 0) {
            val user = list[position]

            val address =
                user.Address_Line_1 + "," + user.Address_Line_2 + "," + user.Land_Mark + "," + user.City + "," + user.State + "," + user.PIN.toString()
            intent = Intent(this, MapActivity::class.java)
            intent.putExtra("address", address)
            startActivity(intent)
        }
    }
}