package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditclientDescriptor;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;

/**
 * A utility class for client.
 */
public class ClientUtil {

    /**
     * Returns an add command string for adding the {@code client}.
     */
    public static String getAddCommand(Client client) {
        return AddCommand.COMMAND_WORD + " " + getclientDetails(client);
    }

    /**
     * Returns an add command string for adding the {@code buyer}.
     */
    public static String getAddBuyerCommand(Buyer buyer) {
        return AddBuyerCommand.COMMAND_WORD + " " + getclientDetails(buyer);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getclientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().fullName + " ");
        sb.append(PREFIX_PHONE + client.getPhone().value + " ");
        client.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditclientDescriptor}'s details.
     */
    public static String getEditclientDescriptorDetails(EditclientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
