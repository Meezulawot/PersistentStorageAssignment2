package com.meezu.persistentstorageassignment2.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.meezu.persistentstorageassignment2.utils.PreferenceUtils
import com.meezu.persistentstorageassignment2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener(){
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnLogin->{
                login()
            }
        }
    }

    private fun login(){
        PreferenceUtils.saveSharedPref(
            this,
            edtUsername.text.toString().trim(),
            edtPassword.text.toString().trim()
        )
        edtUsername?.text?.clear()
        edtPassword?.text?.clear()

        startActivity(Intent(this, DashboardActivity::class.java))
    }
}