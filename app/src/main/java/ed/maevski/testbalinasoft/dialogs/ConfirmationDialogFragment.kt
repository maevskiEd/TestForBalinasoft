package ed.maevski.testbalinasoft.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.DialogConfirmationBinding
import ed.maevski.testbalinasoft.utils.hide
import ed.maevski.testbalinasoft.utils.show

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
//        dialogType.animationImageResId?.let {
//            dialogAnimation.apply {
//                setAnimation(it)
//                show()
//            }
//        }
        dialogType.titleResId?.let {
            dialogTitle.apply {
                text = it
                show()
            }
        }
        dialogType.textResId?.let {
            dialogText.apply {
                text = it
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