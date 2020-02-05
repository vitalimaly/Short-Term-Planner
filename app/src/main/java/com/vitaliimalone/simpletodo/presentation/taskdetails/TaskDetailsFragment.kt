package com.vitaliimalone.simpletodo.presentation.taskdetails

import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.MotionEvent
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.popups.duedatepopup.DueDatePopup
import com.vitaliimalone.simpletodo.presentation.taskdetails.common.SubtasksAdapter
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.DialogUtils
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.extensions.clearFocusOnDoneClick
import com.vitaliimalone.simpletodo.presentation.utils.extensions.setOnClickListenerWithPoint
import com.vitaliimalone.simpletodo.presentation.utils.extensions.trimmed
import kotlinx.android.synthetic.main.task_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.OffsetDateTime

class TaskDetailsFragment : BaseFragment(R.layout.task_details_fragment) {
    private val viewModel: TaskDetailsViewModel by viewModel()
    private val args: TaskDetailsFragmentArgs by navArgs()
    private val task: Task by lazy { args.task }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupClickListeners() {
        deleteImageView.setOnClickListener {
            DialogUtils.showDeleteTaskDialog(requireContext()) {
                viewModel.deleteTask(task)
                saveAndFinish()
            }
        }
        toolbar.setNavigationOnClickListener {
            saveAndFinish()
        }
    }

    private fun saveAndFinish() {
        viewModel.updateTask(task)
        findNavController().popBackStack()
    }

    private fun setupViews() {
        toolbar.title = Res.string(R.string.task_details_toolbar_title)
        subtasksRecyclerView.adapter = SubtasksAdapter(task) {
            drawSubtasksBotLine()
            viewModel.updateTask(task)
        }
        taskTitleEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        taskTitleEditText.setText(task.title)
        taskTitleEditText.hint = Res.string(R.string.add_task_dialog_title_hint)
        noteEditText.setText(task.description)
        noteEditText.hint = Res.string(R.string.task_details_note_hint)
        updateTaskDueDate(task.dueTo)
        createdOnTextView.text = Res.string(
            R.string.task_details_created,
            DateTimeUtils.getShortDayMonthDateText(task.createdAt)
        )
        modifiedOnTextView.text = Res.string(
            R.string.task_details_modified,
            DateTimeUtils.getShortDayMonthDateText(task.modifiedAt)
        )
        taskTitleEditText.clearFocusOnDoneClick()
        noteEditText.setOnTouchListener { v, event ->
            if (v.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        }
        taskTitleEditText.addTextChangedListener {
            task.title = it.trimmed
        }
        noteEditText.addTextChangedListener {
            task.description = it.trimmed
        }
        dueClickableView.setOnClickListenerWithPoint {
            DueDatePopup(requireContext(), childFragmentManager, task.dueTo) {
                task.dueTo = it
                updateTaskDueDate(it)
            }.run {
                showAtLocation(dueClickableView, Gravity.NO_GRAVITY, it.x, it.y)
            }
        }
        drawSubtasksBotLine()
    }

    private fun drawSubtasksBotLine() {
        if (task.subtasks.isEmpty()) {
            botLineView.isInvisible = true
        } else {
            botLineView.isVisible = true
        }
    }

    private fun updateTaskDueDate(offsetDateTime: OffsetDateTime) {
        dueTextView.text = Res.string(R.string.due_to_date, DateTimeUtils.getTaskDueDateFullText(offsetDateTime))
    }

    private fun setupObservers() {
    }
}
