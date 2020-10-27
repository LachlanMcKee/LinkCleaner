package net.lachlanmckee.linkcleaner.feature.home.viewmodel

import androidx.lifecycle.Observer
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import net.lachlanmckee.linkcleaner.ImmediateCoroutineExtension
import net.lachlanmckee.linkcleaner.InstantExecutorExtension
import net.lachlanmckee.linkcleaner.getOrAwaitValue
import net.lachlanmckee.linkcleaner.service.model.LinkData
import net.lachlanmckee.linkcleaner.service.repository.LinkRepository
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(value = [ImmediateCoroutineExtension::class, InstantExecutorExtension::class])
internal class HomeViewModelTest {
  private val linkRepository = mockk<LinkRepository>()
  private val observer = mockk<Observer<in State>>()

  @Test
  fun givenObserveState_whenNoLinks_thenExpectNoEmissions() {
    every { linkRepository.getLink() } returns emptyFlow()
    val viewModel = HomeViewModel(linkRepository)
    viewModel.state.observeForever(observer)

    confirmVerified(observer)
    verifySequence { linkRepository.getLink() }
  }

  @Test
  fun givenObserveState_whenNullLink_thenExpectEmptyStateEmission() {
    every { linkRepository.getLink() } returns flow {
      emit(null)
    }
    val viewModel = HomeViewModel(linkRepository)
    assertEquals(State.Empty, viewModel.state.getOrAwaitValue())
    verifySequence { linkRepository.getLink() }
  }

  @Test
  fun givenObserveState_whenNonNullLink_thenExpectLinkFoundStateEmission() {
    every { linkRepository.getLink() } returns flow {
      emit(validLinkData)
    }
    val viewModel = HomeViewModel(linkRepository)
    assertEquals(
      State.LinkFound(
        originalUrl = "https://www.original.com/",
        replacementUrl = "https://www.replacement.com/"
      ),
      viewModel.state.getOrAwaitValue()
    )
    verifySequence { linkRepository.getLink() }
  }

  @Test
  fun givenNoLinkData_whenUpdateLink_thenDoNotUpdateLink() {
    every { linkRepository.getLink() } returns emptyFlow()
    val viewModel = HomeViewModel(linkRepository)

    viewModel.updateLink()

    verifySequence { linkRepository.getLink() }
  }

  @Test
  fun givenLinkData_whenUpdateLink_thenUpdateLink() {
    every { linkRepository.getLink() } returns flow {
      emit(validLinkData)
    }
    every { linkRepository.updateLink(validLinkData) } returns Unit
    val viewModel = HomeViewModel(linkRepository)

    viewModel.state.getOrAwaitValue()
    viewModel.updateLink()

    verifySequence {
      linkRepository.getLink()
      linkRepository.updateLink(validLinkData)
    }
  }

  private companion object {
    private val validLinkData = LinkData(
      "https://www.original.com/".toHttpUrl(),
      "https://www.replacement.com/".toHttpUrl()
    )
  }
}