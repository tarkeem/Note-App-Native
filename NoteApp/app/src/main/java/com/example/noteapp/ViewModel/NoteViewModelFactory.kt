import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.data.repo.repo

class NoteViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val cxt:Context,
    private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return NoteViewModel(cxt) as T
    }


}