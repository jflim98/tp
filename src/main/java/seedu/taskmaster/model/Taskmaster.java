package seedu.taskmaster.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionList;
import seedu.taskmaster.model.session.SessionListManager;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.session.exceptions.NoSessionException;
import seedu.taskmaster.model.session.exceptions.NoSessionSelectedException;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.student.UniqueStudentList;
import seedu.taskmaster.model.student.exceptions.StudentNotFoundException;

/**
 * Wraps all data at the Taskmaster level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Taskmaster implements ReadOnlyTaskmaster {

    protected Session currentSession;
    private final UniqueStudentList students;
    private final SessionList sessions;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication between constructors.
     * See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are
     * other ways to avoid duplication among constructors.
     */
    {
        students = new UniqueStudentList();
        currentSession = null;
        sessions = SessionListManager.of(new ArrayList<>());
    }

    public Taskmaster() {}

    /**
     * Creates an Taskmaster using the Students in the {@code toBeCopied}
     */
    public Taskmaster(ReadOnlyTaskmaster toBeCopied) {
        this();
        resetData(toBeCopied);
    }


    /* List Overwrite Operations */

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the session list with {@code sessions}.
     * {@code sessions} must not contain duplicate sessions.
     */
    public void setSessions(List<Session> sessions) {
        this.sessions.setSessions(sessions);
    }

    /**
     * Resets the existing data of this {@code Taskmaster} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskmaster newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
        setSessions(newData.getSessionList());
        currentSession = null;
    }

    /* Session-Level Operations */

    /**
     * Changes the current session of this {@code Taskmaster} to a previously
     * created session with name {@code sessionName}.
     */
    public void changeSession(SessionName sessionName) {
        assert sessions.contains(sessionName);
        currentSession = sessions.get(sessionName);
    }

    /**
     * Returns true if {@code session} exists in the session list.
     */
    public boolean hasSession(Session session) {
        requireNonNull(session);
        return sessions.contains(session);
    }

    /**
     * Returns true if a session with {@code sessionName} exists in the session list.
     */
    public boolean hasSession(SessionName sessionName) {
        requireNonNull(sessionName);
        return sessions.contains(sessionName);
    }

    /* Student-Level Operations */

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student list.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a session to the session list.
     * The session must not already exist in the session list.
     */
    public void addSession(Session session) {
        sessions.add(session);
    }

    /**
     * Adds a student to the student list.
     * The student must not already exist in the student list.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the student list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the student list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code Taskmaster}.
     * {@code key} must exist in the student list.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Marks the attendance of a {@code target} student with {@code attendanceType} in the current session.
     *
     * @throws NoSessionException If the session list is empty.
     * @throws NoSessionSelectedException If no session has been selected.
     */
    public void markStudent(Student target, AttendanceType attendanceType)
            throws NoSessionException, NoSessionSelectedException {
        assert target != null;
        assert attendanceType != null;

        if (sessions.isEmpty()) {
            throw new NoSessionException();
        }

        if (!sessions.isEmpty() && currentSession == null) {
            throw new NoSessionSelectedException();
        }

        currentSession.markStudentAttendance(target.getNusnetId(), attendanceType);
    }

    /**
     * Marks the attendance of a student given the {@code nusnetId}
     * with {@code attendanceType} in the current session.
     *
     * @throws NoSessionException If the session list is empty.
     * @throws NoSessionSelectedException If no session has been selected.
     */
    public void markStudentWithNusnetId(NusnetId nusnetId, AttendanceType attendanceType)
            throws NoSessionException, NoSessionSelectedException {
        assert nusnetId != null;
        assert attendanceType != null;

        if (sessions.isEmpty()) {
            throw new NoSessionException();
        }

        if (!sessions.isEmpty() && currentSession == null) {
            throw new NoSessionSelectedException();
        }

        currentSession.markStudentAttendance(nusnetId, attendanceType);
    }

    /**
     * Marks the attendance of all students represented by their {@code nusnetIds} in the {@code studentRecordList}
     * of the {@code currentSession}, with the given {@code attendanceType}.
     *
     * @throws NoSessionException If the session list is empty.
     * @throws NoSessionSelectedException If no session has been selected.
     */
    public void markAllStudents(List<NusnetId> nusnetIds, AttendanceType attendanceType)
            throws NoSessionException, NoSessionSelectedException {
        assert nusnetIds != null;
        assert attendanceType != null;

        if (sessions.isEmpty()) {
            throw new NoSessionException();
        }

        if (!sessions.isEmpty() && currentSession == null) {
            throw new NoSessionSelectedException();
        }

        currentSession.markAllStudents(nusnetIds, attendanceType);
    }

    /* Util Methods */

    @Override
    public String toString() {
        // TODO: refine later
        return students.asUnmodifiableObservableList().size() + " students";
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the {@code StudentRecordList} of the current session.
     *
     * @throws NoSessionException If the session list is empty.
     * @throws NoSessionSelectedException If no session has been selected.
     */
    public ObservableList<StudentRecord> getStudentRecordList()
            throws NoSessionException, NoSessionSelectedException {
        if (sessions.isEmpty()) {
            throw new NoSessionException();
        }

        if (!sessions.isEmpty() && currentSession == null) {
            throw new NoSessionSelectedException();
        }

        return currentSession.getStudentRecords();
    }

    @Override
    public ObservableList<Session> getSessionList() {
        return sessions.asUnmodifiableObservableList();
    }

    /**
     * Sets the {@code AttendanceType} of all {@code StudentRecords} to NO_RECORD in the current session.
     *
     * @throws NoSessionException If the session list is empty.
     * @throws NoSessionSelectedException If no session has been selected.
     */
    public void clearAttendance() throws NoSessionException, NoSessionSelectedException {
        if (sessions.isEmpty()) {
            throw new NoSessionException();
        }

        if (!sessions.isEmpty() && currentSession == null) {
            throw new NoSessionSelectedException();
        }

        currentSession.clearAttendance();
    }

    /**
     * Updates the {@code StudentRecordList} of the current session with the data in {@code studentRecords}.
     *
     * @throws StudentNotFoundException If the operation is unable to find the specified student.
     * @throws NoSessionException If the session list is empty.
     * @throws NoSessionSelectedException If no session has been selected.
     */
    public void updateStudentRecords(List<StudentRecord> studentRecords)
            throws StudentNotFoundException, NoSessionException {
        if (sessions.isEmpty()) {
            throw new NoSessionException();
        }

        if (!sessions.isEmpty() && currentSession == null) {
            throw new NoSessionSelectedException();
        }

        currentSession.updateStudentRecords(studentRecords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Taskmaster // instanceof handles nulls
                && students.equals(((Taskmaster) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}