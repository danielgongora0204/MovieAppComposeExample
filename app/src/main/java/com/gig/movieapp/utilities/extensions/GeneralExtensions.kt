package com.gig.movieapp.utilities.extensions

fun <T> T?.default(default: T): T {
    return this ?: default
}