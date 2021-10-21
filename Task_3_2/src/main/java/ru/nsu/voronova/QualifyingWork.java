package ru.nsu.voronova;

/**
 * This record is intended to describe the qualifying work.
 *
 * @param topic   - the topic of the qualifying work.
 * @param grade   - the grade, which was specified for the qualifying work.
 * @param teacher - the surname of the scientific leader of the qualifying work.
 */
public record QualifyingWork(String topic, Grade grade, String teacher) {
}
