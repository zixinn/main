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
                new Office("Blk 30 Geylang Street 29, #06-40"),
                getModuleCodeSet("friends")),
            new Facilitator(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Office("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getModuleCodeSet("colleagues", "friends")),
            new Facilitator(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Office("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getModuleCodeSet("neighbours")),
            new Facilitator(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Office("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getModuleCodeSet("family")),
            new Facilitator(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Office("Blk 47 Tampines Street 20, #17-35"),
                getModuleCodeSet("classmates")),
            new Facilitator(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Office("Blk 45 Aljunied Street 85, #11-31"),
                getModuleCodeSet("colleagues"))
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
