package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.SellerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddSellerCommand}.
 */
public class AddSellerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new SellerAddressBook());
    }

    @Test
    public void execute_newSeller_success() {
        Seller validSeller = new SellerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getSellerAddressBook());
        expectedModel.addSeller(validSeller);

        assertCommandSuccess(new AddSellerCommand(validSeller), model,
                String.format(AddSellerCommand.MESSAGE_SUCCESS, validSeller), expectedModel);
    }

}

