---
layout: page
title: User Guide
---

TAskmaster is a **desktop app for managing students, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a CS Teaching Assistant who can type fast, TAskmaster can help you track your students' attendance and class participation faster than traditional GUI apps.

## Contents:
- [Quick Start](#Quick-Start "Goto Quick Start")
- [Features](#Features "Goto Features")
    - [Adding a student: `add`](#Adding-a-student-add "Goto Adding-a-student-add")
    - [Listing all students: `list`](#Listing-all-students-list "Goto Listing-all-students-list")
    - [Deleting a student: `delete`](#Deleting-a-student-delete "Goto Deleting-a-student-delete")
    - [Marking a student's attendance: `mark`](#Marking-a-student’s-attendance-mark "Goto Marking-a-students-attendance-mark")
    - [Storing students' attendance records: `store_record`](#Storing-students’-attendance-records-store_record "Goto Storing-students'-attendance-records")
    - [Loading students' attendance records: `load_record`](#Loading-students’-attendance-records-load_record "Goto Loading-students'-attendance-records")
    - [Clear all students: `clear`](#Clearing-all-entries-clear "Goto Clearing-all-entries-clear")
    - [Exit the program: `exit`](#Exiting-the-program-exit "Goto Exiting-the-program-exit")
- [Command Summary](#Command-Summary "Goto Command-Summary")
- [FAQ](#Frequently-Asked-Questions-FAQ "Goto Frequently-Asked-Questions-FAQ")

## Quick Start
1. Ensure you have Java 11 or later installed.
2. Download the jar file from the project website.
3. Copy the file to the folder you want to use as the home folder for the application.
4. Double-click the file to start the app. A GUI should appear, with the field bar to input commands. The list of commands are available below.


## Features
> Command format:
> - Words in `UPPER_CASE` are parameters supplied by the user
> - Items in square brackets are optional
> - Items with ellipses (`...`) after them can be used multiple times including zero times
> - Parameters can be in any order

### Adding a student: `add`
Adds a student into the student list.
```
add n/NAME i/NUSNET_ID e/EMAIL
```

### Listing all students: `list`
Shows a list of all students in the student list.
```
list
```

### Deleting a student: `delete`
Deletes the specified student from the student list.
```
delete INDEX
```
- Deletes the student at the specified `INDEX` number shown in the displayed student list.
- The `INDEX` **must be a positive integer**.

### Marking a student's attendance: `mark`
Marks the attendance of the specified student from the student list.
```
mark INDEX a/ATTENDANCE_TYPE
```
- Marks the attendance at the specified `INDEX` number shown in the displayed student list.
- The `INDEX` **must be a positive integer**.
- The `ATTENDANCE_TYPE` must either be `present` or `absent`.

### Storing students' attendance records: `store_record`
Stores the attendance records of all the students to a file.
```
store_record fn/FILENAME
```
- Stores the NUSNET ID and attendance state of all students in the student list, then clears the attendance of all students
- The `FILENAME` can _only_ contain **alphanumeric characters**
- The files are stored in the `/data` folder.

### Loading students' attendance records: `load_record`
```
load_record fn/FILENAME
```
- Loads the attendance state of all students whose attendance is stored in the file.
- Students whose NUSNET IDs do not appear in the file have their attendance cleared to a NO_RECORD state.
- The `FILENAME` must refer to an existing file and can _only_ contain **alphanumeric** characters.

### Clearing all entries: `clear`
Clears all students from the student list.
```
clear
```

### Exiting the program: `exit`
Exits the program.
```
exit
```

## Command Summary
| Action | Format, Examples                                                                                        |
|--------|---------------------------------------------------------------------------------------------------------|
| Add    | ```add n/NAME i/NUSNET_ID e/EMAIL``` <br> e.g., ```add n/John Doe Kai Jie i/E9412345 e/e9412345@u.nus.edu```|
| List   | ```list```                                                                                               |
| Delete | ```delete INDEX``` <br> e.g., ```delete 3```                                                             |
| Mark   | `mark INDEX a/ATTENDANCE_TYPE` <br> e.g., `mark 1 a/present`                                             |
| Store Attendance | `store_record fn/FILENAME` <br> e.g., `store_record tutorial01`                                |
| Load Attendance | `load_record fn/FILENAME`
| Clear  | ```clear```                                                                                              |


## Frequently Asked Questions (FAQ)
How to download java? [Here](https://lmgtfy.com/?q=how+to+download+java)
