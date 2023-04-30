package com.df.ktorpexels.util.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Activity.openUrl(url: String) {
    try {
        val uri = Uri.parse(url)
        val i = Intent(Intent.ACTION_VIEW, uri)
        startActivity(i)
    } catch (e: Exception) {
        Toast.makeText(this, "NO HAS", Toast.LENGTH_LONG).show()
    }
}
