package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.BuyerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddBuyerCommand}.
 */
public class AddBuyerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new SellerAddressBook());
    }

    @Test
    public void execute_newBuyer_success() {
        Buyer validBuyer = new BuyerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new SellerAddressBook());
        expectedModel.addClient(validBuyer);

        assertCommandSuccess(new AddBuyerCommand(validBuyer), model,
                String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer), expectedModel);
    }

}

