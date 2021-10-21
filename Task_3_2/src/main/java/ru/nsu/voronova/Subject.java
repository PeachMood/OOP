package ru.nsu.voronova;

/**
 * This record is intended to describe subjects.
 *
 * @param name    - the name of the subject.
 * @param grade   - the grade the student received.
 * @param teacher - the teacher who gave the grade.
 */
public record Subject(String name, Grade grade, String teacher) {
}
