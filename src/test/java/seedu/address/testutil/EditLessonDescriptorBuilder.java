package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.logic.commands.lesson.LessonEditCommand;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.ModuleCode;

/**
 * A utility class to help with building EditLessonDescriptor objects.
 */
public class EditLessonDescriptorBuilder {

    private LessonEditCommand.EditLessonDescriptor descriptor;

    public EditLessonDescriptorBuilder() {
        descriptor = new LessonEditCommand.EditLessonDescriptor();
    }

    public EditLessonDescriptorBuilder(LessonEditCommand.EditLessonDescriptor descriptor) {
        this.descriptor = new LessonEditCommand.EditLessonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLessonDescriptor} with fields containing {@code lesson}'s details
     */
    public EditLessonDescriptorBuilder(Lesson lesson) {
        descriptor = new LessonEditCommand.EditLessonDescriptor();
        descriptor.setModuleCode(lesson.getModuleCode());
        descriptor.setLessonType(lesson.getType());
        descriptor.setDay(lesson.getDay());
        descriptor.setStartTime(lesson.getStartTime());
        descriptor.setEndTime(lesson.getEndTime());
        descriptor.setVenue(lesson.getVenue());
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Sets the {@code LessonType} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withLessonType(String type) {
        descriptor.setLessonType(LessonType.valueOf(type));
        return this;
    }

    /**
     * Sets the {@code day} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withDay(String day) {
        descriptor.setDay(DayOfWeek.valueOf(day));
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withStartTime(String time) {
        descriptor.setStartTime(LocalTime.parse(time));
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withEndTime(String time) {
        descriptor.setEndTime(LocalTime.parse(time));
        return this;
    }

    /**
     * Sets the {@code venue} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withVenue(String venue) {
        descriptor.setVenue(venue);
        return this;
    }

    public LessonEditCommand.EditLessonDescriptor build() {
        return descriptor;
    }
}
