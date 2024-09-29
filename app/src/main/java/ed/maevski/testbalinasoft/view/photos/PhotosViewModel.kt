package ed.maevski.testbalinasoft.view.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.usecases.GetImagesFromDbUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PhotosViewModel(
    private val getImagesFromDbUseCase: GetImagesFromDbUseCase
) : ViewModel() {
    private var _images = MutableSharedFlow<List<Image>>()
    val images: SharedFlow<List<Image>>
        get() = _images.asSharedFlow()

    init {
        println("Init PhotosViewModel")
//        getImages()
    }

    fun getImages() {
        viewModelScope.launch {
            _images.emit(getImagesFromDbUseCase())
        }
    }

    class Factory(
        private val getImagesFromDbUseCase: GetImagesFromDbUseCase
        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
                return PhotosViewModel(
                    getImagesFromDbUseCase = getImagesFromDbUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}