package ed.maevski.testbalinasoft.view.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.usecases.DelImageApiUseCase
import ed.maevski.testbalinasoft.domain.usecases.DownloadImagesUseCase
import ed.maevski.testbalinasoft.domain.usecases.GetImagesFromDbUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PhotosViewModel(
    private val getImagesFromDbUseCase: GetImagesFromDbUseCase,
    private val downloadImagesUseCase: DownloadImagesUseCase,
    private val delImageApiUseCase: DelImageApiUseCase,
) : ViewModel() {
    private var _images = MutableSharedFlow<List<Image>>()
    val images: SharedFlow<List<Image>>
        get() = _images.asSharedFlow()

    private var _idImage = MutableSharedFlow<Int>()
    val idImage: SharedFlow<Int>
        get() = _idImage.asSharedFlow()

    init {
        println("Init PhotosViewModel")
//        getImages()
    }

    fun getImages() {
        viewModelScope.launch {
//            downloadImagesUseCase()?.let { _images.emit(it) }
            if (downloadImagesUseCase()) _images.emit(getImagesFromDbUseCase())
        }
    }

    fun delImage(id: Int) {
        viewModelScope.launch {
            if (delImageApiUseCase(id)) _idImage.emit(id)
        }
    }

    class Factory(
        private val getImagesFromDbUseCase: GetImagesFromDbUseCase,
        private val downloadImagesUseCase: DownloadImagesUseCase,
        private val delImageApiUseCase: DelImageApiUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
                return PhotosViewModel(
                    getImagesFromDbUseCase = getImagesFromDbUseCase,
                    downloadImagesUseCase = downloadImagesUseCase,
                    delImageApiUseCase = delImageApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}