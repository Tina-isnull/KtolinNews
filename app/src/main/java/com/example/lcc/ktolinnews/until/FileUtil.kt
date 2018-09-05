package com.example.lcc.ktolinnews.until

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object FileUtil {

    fun geFileFromAssets(context: Context, fileName: String): String {

        val s = StringBuilder("")
        try {
            val inn = InputStreamReader(context.getResources().getAssets().open(fileName))
            val br = BufferedReader(inn)
            while (br.readLine() != null) {
                s.append(br.readLine())

            }
            return s.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
    }
}