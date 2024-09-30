package ed.maevski.testbalinasoft.dialogs

import android.os.Bundle

interface ConfirmationDialogListener {
    fun onAcceptClick(args: Bundle?)
    fun onCancelClick(args: Bundle?) {}
}