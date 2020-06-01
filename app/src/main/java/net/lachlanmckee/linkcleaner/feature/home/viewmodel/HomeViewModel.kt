package net.lachlanmckee.linkcleaner.feature.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import net.lachlanmckee.linkcleaner.service.repository.LinkRepository
import okhttp3.HttpUrl
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val linkRepository: LinkRepository
) : ViewModel() {

    private val _httpUrl = linkRepository
        .getLink()
        .asLiveData(viewModelScope.coroutineContext)

    val httpUrl: LiveData<HttpUrl?>
        get() = _httpUrl

    fun updateLink() {
        val currentHttpUrl: HttpUrl? = _httpUrl.value
        currentHttpUrl?.apply(linkRepository::updateLink)
    }
}