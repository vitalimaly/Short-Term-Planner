package com.vitaliimalone.shorttermplanner.presentation.dialogs

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.shorttermplanner.presentation.screens.taskdetails.TaskDetailsViewModel
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
import kotlinx.android.synthetic.main.delete_task_dialog.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeleteTaskDialog : BaseBottomSheetDialogFragment(R.layout.delete_task_dialog) {
    private val viewModel: DeleteTaskViewModel by viewModel()
    private val taskDetailsViewModel: TaskDetailsViewModel by sharedViewModel()
    private val args: DeleteTaskDialogArgs by navArgs()

    override fun onDialogCreated(dialog: BottomSheetDialog) {
        dialog.apply {
            titleTextView.text = Res.string(R.string.delete_task_dialog_title)
            positiveButton.text = Res.string(R.string.yes)
            negativeButton.text = Res.string(R.string.cancel)
            positiveButton.setOnClickListener {
                viewModel.deleteTask(args.task)
                taskDetailsViewModel.taskDeletedEvent.call()
                findNavController().popBackStack()
            }
            negativeButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}