package ed.maevski.testbalinasoft.view.imagedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.usecases.GetImageByIdFromDbUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ImageDetailViewModel(private val getImageByIdFromDbUseCase: GetImageByIdFromDbUseCase) : ViewModel() {
    private var _image = MutableSharedFlow<Image>()
    val image: SharedFlow<Image>
        get() = _image.asSharedFlow()

    fun getImageById(id: Long) {
        viewModelScope.launch {
            _image.emit(getImageByIdFromDbUseCase(id))
        }
    }

    class Factory(
        private val getImageByIdFromDbUseCase: GetImageByIdFromDbUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ImageDetailViewModel::class.java)) {
                return ImageDetailViewModel(getImageByIdFromDbUseCase = getImageByIdFromDbUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}