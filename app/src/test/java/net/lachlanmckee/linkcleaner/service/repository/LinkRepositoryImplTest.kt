package net.lachlanmckee.linkcleaner.service.repository

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import net.lachlanmckee.linkcleaner.ImmediateCoroutineExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(value = [ImmediateCoroutineExtension::class])
internal class LinkRepositoryImplTest {

  class ClipboardManagerStub(private val initialText: String?) : ClipboardManager {
    private var clipboardListener: (() -> Unit)? = null

    fun getListener(): (() -> Unit)? {
      return clipboardListener
    }

    override val clipText: String?
      get() = initialText

    override fun addClipChangedListener(clipboardListener: () -> Unit) {
      this.clipboardListener = clipboardListener
    }

    override fun removeClipChangedListener(clipboardListener: () -> Unit) {
      this.clipboardListener = null
    }

    override fun setClipText(label: String, content: String) {
    }
  }

  @Test
  fun foo() = runBlocking {
    val clipboardManager = ClipboardManagerStub(null)
    val linkRepository = LinkRepositoryImpl(clipboardManager)

    val results = linkRepository.getLink().toList()

    Assertions.assertEquals(listOf(null), results)
  }
}