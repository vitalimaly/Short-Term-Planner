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
import com.vitaliimalone.simpletodo.presentation.utils.*
import com.vitaliimalone.simpletodo.presentation.utils.duedatepopup.DueDatePopup
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
        subtasksRecyclerView.adapter = SubtasksAdapter(task) {
            drawSubtasksBotLine()
            viewModel.updateTask(task)
        }
        taskTitleEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        taskTitleEditText.setText(task.title)
        noteEditText.setText(task.description)
        updateTaskDueDate(task.dueTo)
        createdOnTextView.text = Res.string(R.string.task_details_created,
                DateTimeUtils.getShortDayMonthDate(task.createdAt))
        modifiedOnTextView.text = Res.string(R.string.task_details_modified,
                DateTimeUtils.getShortDayMonthDate(task.modifiedAt))
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
        dueClickableView.setOnClickListener {
            DueDatePopup(requireContext(), dueTextView) {
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
        dueTextView.text = Res.string(R.string.due_to_date, DateTimeUtils.getTaskDueDateFull(offsetDateTime))
    }

    private fun setupObservers() {

    }
}
