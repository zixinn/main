package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.Office;
import seedu.address.model.facilitator.Phone;
import seedu.address.model.module.ModuleCode;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Facilitator[] getSampleFacilitators() {
        return new Facilitator[] {
            new Facilitator(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Office("COM2-03-04"),
                getModuleCodeSet("CS2103T")),
            new Facilitator(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Office("COM1-02-18"),
                getModuleCodeSet("CS2103T", "CS2101")),
            new Facilitator(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
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

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Facilitator sampleFacilitator : getSampleFacilitators()) {
            sampleAb.addFacilitator(sampleFacilitator);
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
