# Hailey User Guide

![Hailey Bot Screenshot](ui.png)

Hailey is a friendly chatbot that helps you manage your tasks efficiently. 
Whether it's ToDos, Deadlines, or Events, Hailey keeps your schedule organized and even reminds you of upcoming tasks.

## Viewing help: `help`
Shows a message 

Format: `help`
```
you can add tasks by:
 > todo [description]
 > deadline [description] /by [deadline]
 > event [description] /from [start] /to [end]
* date format: d/M/yyyy HHmm
other commands:
 > list //to view your list of tasks
 > mark [task number] //to mark a task as done
 > unmark [task number] //to unmark a task as done
 > delete [task number] //to delete a task from your list
 > find [keyword] //returns a list of tasks containing this keyword
 > clear //clears all tasks from list
 > bye //exits program
```

## Adding tasks

### Adding To-dos: `todo`

Adds a to-do to your list of tasks. 

Format: `todo DESCRIPTION`

Example: `todo watch cs2103 lecture`

```
got it! i've added this task:
 [T][] watch cs2103 lecture
now you have 1 tasks in the list.
```
### Adding Deadlines: `deadline`

Adds a task with a specific deadline (in date and time format) to your list of tasks.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HHmm`

Example: `deadline submit project /by 21/2/2025 2359`

```
got it! i've added this task:
 [D][] submit project (by: Feb 21 2025 11:59 pm)
now you have 2 tasks in the list.
```

### Adding Events: `event`

Adds an event with a start and end time (in date and time format) to your list of tasks.

Format: `event DESCRIPTION /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm`

Example: `event cs tutorial /from 21/2/2025 1500 /to 21/2/2025 1600`

```
got it! i've added this task:
 [E][] cs tutorial (from: Feb 21 2025 3:00 pm to: Feb 21 2025 4:00 pm)
now you have 3 tasks in the list.
```

## Listing all tasks: `list`

Shows a list of all tasks in your task list. This list remains saved even if the bot is closed and reopened.

Format: `list`

Example: `list`
```
here are the tasks in your list:
1. [T][] watch cs2103 lecture
2. [D][] submit project (by: Feb 21 2025 11:59 pm)
3. [E][] cs tutorial (from: Feb 21 2025 3:00 pm to: Feb 21 2025 4:00 pm)
```

## Marking as done: `mark`

Marks a task as done, represented by `[X]`.

Format: `mark INDEX`

* The index refers to the index number shown in the displayed task list. 
* Index must be a **positive integer** between 1 and total number of tasks.

Example: `mark 2`
```
good job! I've marked this task as done:
 [D][X] submit project (by: Feb 21 2025 11:59 pm)
```

## Unmarking as done: `unmark`

Marks a task as not done, represented by `[]`.

Format: `unmark INDEX`

* The index refers to the index number shown in the displayed task list.
* Index must be a **positive integer** between 1 and total number of tasks.

Example: `unmark 2`
```
okay, I've marked this task as not done yet:
 [D][] submit project (by: Feb 21 2025 11:59 pm)
```
## Deleting a task: `delete`

Deletes a task from the task list, and returns the number of tasks remaining.

Format: `delete INDEX`

* The index refers to the index number shown in the displayed task list.
* Index must be a **positive integer** between 1 and total number of tasks.

Example: `delete 2`
```
okay, I've removed this task:
 [D][] submit project (by: Feb 21 2025 11:59 pm)
now you have 2 tasks in the list.
```

## Search for tasks: `find`

Returns a list of tasks containing a given keyword.

Format: `find KEYWORD`

* Keyword can be of any length, and includes spaces.

Example: `find cs`
```
here are the matching tasks in your list:
1. [T][] watch cs2103 lecture
2. [E][] cs tutorial (from: Feb 21 2025 3:00 pm to: Feb 21 2025 4:00 pm)
```

## Clearing all entries: `clear`

Clears all tasks from list. 

Format: `clear`
```
done! no tasks left. what would you like to do today?
```
## Exit program: `bye`

Saves tasks list and exits program.