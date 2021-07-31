
package com.gkreduction.interview.utils

fun <T> lazyThreadSafetyNone(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)