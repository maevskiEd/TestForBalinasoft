package com.example.patients.view.dialogs

import android.os.Bundle
import ed.maevski.testbalinasoft.dialogs.DialogType

class ConfirmationDialogBuilder(
    private val listener: ConfirmationDialogListener? = null, private val canCancel: Boolean = false
) {
    private lateinit var type: DialogType
    private val args = Bundle()

    fun setDialogType(type: DialogType): ConfirmationDialogBuilder {
        this.type = type
        return this
    }

    fun putIntArgument(key: String, argument: Int): ConfirmationDialogBuilder {
        args.putInt(key, argument)
        return this
    }

    fun putStringArgument(key: String, argument: String): ConfirmationDialogBuilder {
        args.putString(key, argument)
        return this
    }

    fun build(): ConfirmationDialogFragment =
        ConfirmationDialogFragment.newInstance(type, listener).apply {
            arguments = args
            isCancelable = canCancel
        }
}
