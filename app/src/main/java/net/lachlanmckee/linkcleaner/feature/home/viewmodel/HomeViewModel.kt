package net.lachlanmckee.linkcleaner.feature.home.viewmodel

import androidx.lifecycle.*
import net.lachlanmckee.linkcleaner.service.model.LinkData
import net.lachlanmckee.linkcleaner.service.repository.LinkRepository
import okhttp3.HttpUrl
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val linkRepository: LinkRepository
) : ViewModel() {

    private val _linkData = linkRepository
        .getLink()
        .asLiveData(viewModelScope.coroutineContext)

    val state: LiveData<State>
        get() {
            return _linkData
                .map { data ->
                    if (data != null) {
                        State.LinkFound(
                            originalUrl = data.originalHttpUrl.toString(),
                            replacementUrl = data.replacementHttpUrl.toString()
                        )
                    } else {
                        State.Empty
                    }
                }
        }

    fun updateLink() {
        val currentLinkData: LinkData? = _linkData.value
        currentLinkData?.apply(linkRepository::updateLink)
    }
}

sealed class State {
    object Empty : State()
    data class LinkFound(val originalUrl: String, val replacementUrl: String) : State()
}
