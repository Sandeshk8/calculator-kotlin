package com.example.clac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Button
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvi: TextView? = null
    var lastnum: Boolean = false
    var lastdot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvi = findViewById(R.id.tvi)
    }

    fun ondigit(view: View) {

        tvi?.append((view as Button).text)
        lastnum = true
    }

    fun onclear(view: View) {
        tvi?.text = ""
    }

    fun ondeci(view: View) {
        if (lastnum && !lastdot) {
            tvi?.append(".")
            lastdot = true
        }
    }
    fun onopr(view: View){
        tvi?.text?.let{
            if(lastnum && !isopadded(it.toString())){
                tvi?.append((view as Button).text)
                lastnum =  false
                lastdot = false
            }
        }

    }
    private fun isopadded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("+") || value.contains("-")||value.contains("*")
        }
    }
    fun onequal(view:View){
        if(lastnum){
            var tvvalue = tvi?.text.toString()
            var prefix = ""
            try{
                if(tvvalue.startsWith("-")) {
                    prefix = "-"
                    tvvalue = tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                var splitv = tvvalue.split("-")

                var one = splitv[0]
                var two = splitv[1]

                if(prefix.isNotEmpty()) {
                    one = prefix + one
                }

                tvi?.text = removezero((one.toDouble() -  two.toDouble()).toString())
                }else if(tvvalue.contains("/")){
                    var splitv = tvvalue.split("/")

                    var one = splitv[0]
                    var two = splitv[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvi?.text = removezero((one.toDouble() /  two.toDouble()).toString())
                }else if(tvvalue.contains("+")){
                    var splitv = tvvalue.split("+")

                    var one = splitv[0]
                    var two = splitv[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvi?.text = removezero((one.toDouble() +  two.toDouble()).toString())
                }else if(tvvalue.contains("*")){
                    var splitv = tvvalue.split("*")

                    var one = splitv[0]
                    var two = splitv[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvi?.text = removezero((one.toDouble() * two.toDouble()).toString())
                }
            }catch (e:ArithmeticException){
                e.printStackTrace()

            }
            }
        }
    private fun removezero(result: String): String{
        var value =  result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)

        }
        return value
        }

    }


