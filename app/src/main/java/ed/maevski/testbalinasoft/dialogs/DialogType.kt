package ed.maevski.testbalinasoft.dialogs

import ed.maevski.testbalinasoft.R


enum class DialogType(
    val imageResId: Int? = null,
    val animationImageResId: Int? = null,
    val titleResId: String? = null,
    val textResId: String? = null,
    val acceptBtnResId: Int,
    val cancelBtnResId: Int? = null
) {
    CONFIRM_DELETE(
        titleResId = "Подтвердить удаление",
        textResId = "Удалить фото?",
        acceptBtnResId = R.string.yes,
        cancelBtnResId = R.string.no
    ),
}