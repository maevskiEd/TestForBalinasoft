package com.example.patients.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.patients.R
import com.example.patients.databinding.DialogConfirmationBinding
import com.example.patients.utils.uiextensions.hide
import com.example.patients.utils.uiextensions.show
import ed.maevski.testbalinasoft.dialogs.DialogType

class ConfirmationDialogFragment private constructor() : DialogFragment() {

    private lateinit var dialogType: DialogType
    private var confirmationListener: ConfirmationDialogListener? = null
    private var _binding: DialogConfirmationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogConfirmationBinding.inflate(layoutInflater)
        return AlertDialog.Builder(requireActivity(), R.style.RoundedCornersDialog)
            .setView(binding.root).create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() = with(binding) {
        //closeButton.setOnClickListener { dismiss() }
        dialogType.imageResId?.let {
            dialogImage.apply {
                setImageResource(it)
                show()
            }
        }
        dialogType.animationImageResId?.let {
            dialogAnimation.apply {
                setAnimation(it)
                show()
            }
        }
        dialogType.titleResId?.let {
            dialogTitle.apply {
                text = getString(it)
                show()
            }
        }
        dialogType.textResId?.let {
            dialogText.apply {
                text = getString(it)
                show()
            }
        }
        dialogButtonAccept.apply {
            text = getString(dialogType.acceptBtnResId)
            setOnClickListener {
                confirmationListener?.onAcceptClick(arguments)
                dismiss()
            }
        }
        dialogButtonCancel.apply {
            dialogType.cancelBtnResId?.let { resId ->
                text = getString(resId)
                setOnClickListener {
                    confirmationListener?.onCancelClick(arguments)
                    dismiss()
                }
            } ?: this.hide()
        }
    }

    companion object {
        fun newInstance(
            dialogType: DialogType, confirmationListener: ConfirmationDialogListener? = null
        ): ConfirmationDialogFragment = ConfirmationDialogFragment().apply {
            this.dialogType = dialogType
            if (confirmationListener != null) {
                this.confirmationListener = confirmationListener
            }
        }
    }
}