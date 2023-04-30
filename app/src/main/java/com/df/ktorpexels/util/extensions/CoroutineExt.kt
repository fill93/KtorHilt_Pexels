package com.df.ktorpexels.util.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal fun launchSilent (
    context: CoroutineContext = Dispatchers.IO,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    CoroutineScope(context).launch (context, start, block)
}