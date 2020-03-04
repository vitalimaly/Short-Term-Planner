package com.vitaliimalone.shorttermplanner.presentation.dialogs.addnewtask

import android.text.InputType
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.presentation.base.BaseBottomSheetDialogFragment
import com.vitaliimalone.shorttermplanner.presentation.popups.duedatepopup.DueDatePopup
import com.vitaliimalone.shorttermplanner.presentation.utils.DateTimeUtils
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.hideKeyboard
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.setEnabledWithAlpha
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.setOnClickListenerWithPoint
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.trimmed
import kotlinx.android.synthetic.main.add_new_task_dialog.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNewTaskDialog : BaseBottomSheetDialogFragment(R.layout.add_new_task_dialog) {
    private val viewModel: AddNewTaskViewModel by viewModel()
    private val args: AddNewTaskDialogArgs by navArgs()

    override fun onDialogCreated(dialog: BottomSheetDialog) {
        val task = Task(dueTo = DateTimeUtils.getAddNewTaskDate(args.HomeTab))
        dialog.apply {
            titleEditText.requestFocus()
            addImageView.setOnClickListener {
                viewModel.addNewTask(task)
                findNavController().popBackStack()
            }
            addImageView.setEnabledWithAlpha(false)
            titleEditText.apply {
                hint = Res.string(R.string.add_task_dialog_title_hint)
                addTextChangedListener(onTextChanged = { text, _, _, _ ->
                    addImageView.setEnabledWithAlpha(text.trimmed.isNotEmpty())
                    task.title = text.trimmed
                })
                setOnEditorActionListener { v, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (text.trimmed.isNotEmpty()) {
                            v.clearFocus()
                            v.hideKeyboard()
                            viewModel.addNewTask(task)
                            findNavController().popBackStack()
                        }
                        true
                    } else {
                        false
                    }
                }
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                isSingleLine = true
                maxLines = 4
                setHorizontallyScrolling(false)
                imeOptions = EditorInfo.IME_ACTION_DONE
            }
            dueDateTextView.text = Res.string(R.string.due_to_date, DateTimeUtils.getTaskDueDateText(task.dueTo))
            dueDateTextView.setOnClickListenerWithPoint {
                DueDatePopup(context, childFragmentManager, task.dueTo) { pickedDate ->
                    task.dueTo = pickedDate
                    dueDateTextView.text =
                        Res.string(R.string.due_to_date, DateTimeUtils.getTaskDueDateText(task.dueTo))
                }.run {
                    showAtLocation(dueDateTextView, Gravity.NO_GRAVITY, it.x, it.y)
                }
            }
        }
    }
}