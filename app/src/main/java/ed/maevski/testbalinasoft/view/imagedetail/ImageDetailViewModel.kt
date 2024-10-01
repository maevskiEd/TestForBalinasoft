package ed.maevski.testbalinasoft.view.imagedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ed.maevski.testbalinasoft.domain.models.Comment
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.domain.usecases.GetImageByIdFromDbUseCase
import ed.maevski.testbalinasoft.domain.usecases.SendCommentUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ImageDetailViewModel(
    private val getImageByIdFromDbUseCase: GetImageByIdFromDbUseCase,
    private val sendCommentUseCase: SendCommentUseCase,
    ) : ViewModel() {
    private var _image = MutableSharedFlow<Image>()
    val image: SharedFlow<Image>
        get() = _image.asSharedFlow()

    fun getImageById(id: Int) {
        viewModelScope.launch {
            val im = getImageByIdFromDbUseCase(id)

            println("ImageDetailViewModel getImageById $im")

            _image.emit(im)
        }
    }

    fun sendComment(comment: Comment) {
        viewModelScope.launch {
            sendCommentUseCase(comment)
        }
    }

    class Factory(
        private val getImageByIdFromDbUseCase: GetImageByIdFromDbUseCase,
        private val sendCommentUseCase: SendCommentUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ImageDetailViewModel::class.java)) {
                return ImageDetailViewModel(
                    getImageByIdFromDbUseCase = getImageByIdFromDbUseCase,
                    sendCommentUseCase = sendCommentUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}