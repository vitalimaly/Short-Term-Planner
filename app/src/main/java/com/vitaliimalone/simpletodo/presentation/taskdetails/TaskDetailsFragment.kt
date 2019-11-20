package com.vitaliimalone.simpletodo.presentation.taskdetails

import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.taskdetails.common.SubtasksAdapter
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.Dialogs
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.clearFocusOnDoneClick
import com.vitaliimalone.simpletodo.presentation.utils.trimmed
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
        deleteIv.setOnClickListener {
            Dialogs.showDeleteTaskDialog(requireContext()) {
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
        subtasksRv.adapter = SubtasksAdapter(task) {
            drawSubtasksBotLine()
            viewModel.updateTask(task)
        }
        taskTitleEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        taskTitleEditText.setText(task.title)
        noteEt.setText(task.description)
        updateTaskDueDate(task.dueTo)
        createdOnTv.text = Res.string(R.string.task_details_created,
                DateTimeUtils.getShortDayMonthDate(task.createdAt))
        modifiedOnTv.text = Res.string(R.string.task_details_modified,
                DateTimeUtils.getShortDayMonthDate(task.modifiedAt))
        taskTitleEditText.clearFocusOnDoneClick()
        noteEt.setOnTouchListener { v, event ->
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
        noteEt.addTextChangedListener {
            task.description = it.trimmed
        }
        dueClickableView.setOnClickListener {
            Dialogs.showDatePickerDialog(requireContext(), task.dueTo) {
                task.dueTo = it
                updateTaskDueDate(it)
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
        dueTv.text = Res.string(R.string.due_to_date, DateTimeUtils.getTaskDueDateFull(offsetDateTime))
    }

    private fun setupObservers() {

    }
}
