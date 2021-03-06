package seedu.taskmaster.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.taskmaster.commons.core.GuiSettings;
import seedu.taskmaster.model.session.AttendanceType;
import seedu.taskmaster.model.session.StudentRecord;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<StudentRecord> PREDICATE_SHOW_ALL_STUDENT_RECORDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' student list file path.
     */
    Path getTaskmasterFilePath();

    /**
     * Sets the user prefs' student list file path.
     */
    void setTaskmasterFilePath(Path taskmasterFilePath);

    /**
     * Replaces student list data with the data in {@code taskmaster}.
     */
    void setTaskmaster(ReadOnlyTaskmaster taskmaster);

    /** Returns the Taskmaster */
    ReadOnlyTaskmaster getTaskmaster();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student list.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the student list.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the student list.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the student list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the student list.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered attendance list */
    ObservableList<StudentRecord> getFilteredStudentRecordList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered attendance list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentRecordList(Predicate<StudentRecord> predicate);

    /**
     * Marks the attendance of the given student {@code target} with the given {@code attendanceType}.
     * The student must exist in the student list.
     */
    void markStudent(Student target, AttendanceType attendanceType);

    void markStudentWithNusnetId(NusnetId nusnetId, AttendanceType attendanceType);

    /**
     * Updates the corresponding attendance statuses with the Attendances in the given list.
     */
    void updateStudentRecords(List<StudentRecord> studentRecords);

    /**
     * Clears the attendance statuses of all students in the student list.
     */
    void clearAttendance();
}
