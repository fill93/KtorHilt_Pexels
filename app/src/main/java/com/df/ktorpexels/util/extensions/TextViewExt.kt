package com.df.ktorpexels.util.extensions

import android.app.Activity
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import java.util.regex.Pattern

fun TextView.regexAndClickableLinks(activity: Activity) {
    val listLinks = this.regexLinks("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?")
    this.makeLinks(listLinks){
        activity.openUrl(it)
    }
}

fun TextView.regexLinks(regex: String) : MutableList<String>{
    val patter = Pattern.compile(regex)
    val listUrl = mutableListOf<String>()
    val text = this.text.toString()
    val matcher = patter.matcher(text)

    while (matcher.find()){
        listUrl.add(matcher.group().toString())
    }

    return listUrl
}

fun TextView.makeLinks(links : List<String> , act : (String) -> Unit) {
    val span = SpannableString(this.text)
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                act(link)
            }
        }
        val startIndexLink = this.text.toString().indexOf(link)
        span.setSpan(clickableSpan, startIndexLink, startIndexLink + link.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    this.movementMethod = LinkMovementMethod.getInstance()
    this.setText(span, TextView.BufferType.SPANNABLE)
}
