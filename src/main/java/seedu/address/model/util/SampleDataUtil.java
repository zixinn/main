package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ModManager;
import seedu.address.model.ReadOnlyModManager;
import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.Office;
import seedu.address.model.facilitator.Phone;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;


/**
 * Contains utility methods for populating {@code ModManager} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        Module[] modules = new Module[] {
            new Module(new ModuleCode("CS2103T"), new Description("Software Engineering")),
            new Module(new ModuleCode("CS2101"),
            new Description("Effective Communication for Computing Professionals")),
            new Module(new ModuleCode("ES2660"), new Description("Communicating in Information Age")),
            new Module(new ModuleCode("MA1521"), new Description("Calculus for Computing")),
            new Module(new ModuleCode("ST2334"), new Description("Probability and Statistics")),
            new Module(new ModuleCode("CS3233"), new Description("Competitive Programming")),
            new Module(new ModuleCode("CS4223"), new Description("Multi-core Architectures"))
        };
        return modules;
    }

    public static Facilitator[] getSampleFacilitators() {
        return new Facilitator[] {
            new Facilitator(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Office("COM2-03-04"),
                getModuleCodeSet("CS2103T")),
            new Facilitator(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Office("COM1-02-18"),
                        getModuleCodeSet("CS2103T", "CS2101")), new Facilitator(new Name("Charlotte Oliveiro"),
                new Phone("93210283"), new Email("charlotte@example.com"),
                        new Office("AS6-04-11"),
                        getModuleCodeSet("ES2660")),
            new Facilitator(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Office("S16-04-03"),
                        getModuleCodeSet("MA1521")),
            new Facilitator(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Office("S17-03-15"),
                        getModuleCodeSet("ST2334")),
            new Facilitator(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Office("AS4-01-13"),
                        getModuleCodeSet("CS2101"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[]{
                Task.makeScheduledTask(new Description("Programming Assignment 2"), new TaskDateTime("18/03/2020"),
                        new ModuleCode("CS3233"), 111),
                Task.makeScheduledTask(new Description("Programming Assignment 3"), new TaskDateTime("21/03/2020"),
                        new ModuleCode("CS4223"), 999),
                Task.makeNonScheduledTask(new Description("OP2 Presentation"), new ModuleCode("CS2101"), 314)
        };
    }

    public static ReadOnlyModManager getSampleAddressBook() {
        ModManager sampleAb = new ModManager();
        for (Facilitator sampleFacilitator : getSampleFacilitators()) {
            sampleAb.addFacilitator(sampleFacilitator);
        }
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }

    /**
     * Returns a module code set containing the list of strings given.
     */
    public static Set<ModuleCode> getModuleCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(ModuleCode::new)
                .collect(Collectors.toSet());
    }

}
