package net.lachlanmckee.linkcleaner.service.model

import okhttp3.HttpUrl

data class LinkData(
    val originalHttpUrl: HttpUrl,
    val replacementHttpUrl: HttpUrl
)