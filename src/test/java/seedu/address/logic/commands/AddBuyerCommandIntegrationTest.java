package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newBuyer_success() {
        Buyer validBuyer = new BuyerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addClient(validBuyer);

        assertCommandSuccess(new AddBuyerCommand(validBuyer), model,
                String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer), expectedModel);
    }

    @Test
    public void execute_duplicateBuyer_throwsCommandException() {
        Buyer buyerInList = (Buyer) model.getAddressBook().getclientList().get(0);
        assertCommandFailure(new AddBuyerCommand(buyerInList), model, AddCommand.MESSAGE_DUPLICATE_CLIENT);
    }

}
