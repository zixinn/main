package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.Office;
import seedu.address.model.facilitator.Phone;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Facilitator objects.
 */
public class FacilitatorBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_OFFICE = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Office office;
    private Set<ModuleCode> moduleCodes;

    public FacilitatorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        office = new Office(DEFAULT_OFFICE);
        moduleCodes = new HashSet<>();
    }

    /**
     * Initializes the FacilitatorBuilder with the data of {@code facilitatorToCopy}.
     */
    public FacilitatorBuilder(Facilitator facilitatorToCopy) {
        name = facilitatorToCopy.getName();
        phone = facilitatorToCopy.getPhone();
        email = facilitatorToCopy.getEmail();
        office = facilitatorToCopy.getOffice();
        moduleCodes = new HashSet<>(facilitatorToCopy.getModuleCodes());
    }

    /**
     * Sets the {@code Name} of the {@code Facilitator} that we are building.
     */
    public FacilitatorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code moduleCodes} into a {@code Set<ModuleCode>} and set it to the {@code Facilitator}
     * that we are building.
     */
    public FacilitatorBuilder withModuleCodes(String ... moduleCodes) {
        this.moduleCodes = SampleDataUtil.getModuleCodeSet(moduleCodes);
        return this;
    }

    /**
     * Sets the {@code Office} of the {@code Facilitator} that we are building.
     */
    public FacilitatorBuilder withOffice(String office) {
        this.office = new Office(office);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Facilitator} that we are building.
     */
    public FacilitatorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Facilitator} that we are building.
     */
    public FacilitatorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Facilitator build() {
        return new Facilitator(name, phone, email, office, moduleCodes);
    }

}
