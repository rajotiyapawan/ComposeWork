package com.rajotiya.mytestapp.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

/**
 * Created by Pawan Rajotiya on 26-06-2025.
 */

// State Holder
class TaskListState {
    var tasks by mutableStateOf(listOf<Task>())
    var filter by mutableStateOf(FilterType.ALL)

    fun addTask(text: String) {
        tasks = tasks + Task(text, text)
    }

    fun toggleTask(id: String) {
        tasks = tasks.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
    }

    val filteredTasks
        get() = when (filter) {
            FilterType.ALL -> tasks
            FilterType.ACTIVE -> tasks.filter { !it.isDone }
            FilterType.COMPLETED -> tasks.filter { it.isDone }
        }
}

data class Task(
    val text: String,
    val id: String,
    val isDone: Boolean = false,
)

enum class FilterType {
    ALL, ACTIVE, COMPLETED
}

class TaskListViewModel : ViewModel() {
    private var _state = TaskListState()
    val state = _state
}

// Exercise Solution
@Composable
fun TaskListScreen(modifier: Modifier, state: TaskListState = viewModel<TaskListViewModel>().state) {
    var newTaskText by rememberSaveable { mutableStateOf("") }

    Column(modifier) {
        // Filter Controls
        Row {
            FilterType.entries.forEach { type ->
                RadioButton(
                    selected = state.filter == type,
                    onClick = { state.filter = type }
                )
                Text(type.name)
            }
        }

        // Add Task
        Row {
            TextField(
                value = newTaskText,
                onValueChange = { newTaskText = it }
            )
            Button(
                onClick = {
                    state.addTask(newTaskText)
                    newTaskText = ""
                }
            ) { Text("Add") }
        }

        // Task List
        LazyColumn {
            items(state.filteredTasks) { task ->
                Row {
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = { state.toggleTask(task.id) }
                    )
                    Text(task.text)
                }
            }
        }
    }
}