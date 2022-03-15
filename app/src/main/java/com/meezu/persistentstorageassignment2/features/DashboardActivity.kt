package com.meezu.persistentstorageassignment2.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.meezu.persistentstorageassignment2.utils.PreferenceUtils
import com.meezu.persistentstorageassignment2.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.io.*
import java.lang.StringBuilder

class DashboardActivity : AppCompatActivity(), View.OnClickListener {

    private var fileName = "DummyFile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        getSharedPref()
        initListener()
    }

    private fun initListener(){
        btnWrite.setOnClickListener(this)
        btnRead.setOnClickListener(this)
        btnLogout.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnWrite->{
                writeFileContent()
            }
            btnRead ->{
                readFile()
            }
            btnLogout ->{
                logout()
            }
        }
    }

    private fun getSharedPref(){
        val username = PreferenceUtils.getUsername(this)
        val password = PreferenceUtils.getPassword(this)

        if(username == null && password == null){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            Toast.makeText(this, "username: $username and password: $password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logout() {
        PreferenceUtils.clearSharedPref(this)
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun readFile(){
        try{
            txvMessage.text = readFromFile(fileName)
        }catch(e: java.lang.Exception){
            Toast.makeText(this, "Error: Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFromFile(fileName: String): String?{
        var readString: String? = ""
        val fileInputStream : FileInputStream = openFileInput(fileName)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder(readString)

        while(bufferedReader.readLine().also { readString = it } != null){
            stringBuilder.append(readString)
        }
        inputStreamReader.close()
        return stringBuilder.toString()
    }

    private fun writeFileContent(){
        val message = edtMessage.text.toString()
        if(!checkIfStringISEmpty(message)){
            try{
                writeToFile(message)
//                Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
            }catch(e: java.lang.Exception){
                Toast.makeText(this, "Error: Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun writeToFile(textMessage: String){
        val fileOutputStream : FileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
        val outputStreamWriter = OutputStreamWriter(fileOutputStream)
            outputStreamWriter.write(textMessage)
            outputStreamWriter.flush()
            outputStreamWriter.close()
    }

    private fun checkIfStringISEmpty(string: String): Boolean{
        return string.isEmpty() || string.isBlank()

    }

}