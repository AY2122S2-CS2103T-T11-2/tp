---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `clientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `client` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a client).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

<div markdown="span" class="alert alert-info">:information_source: Note that the usage of the term `CLIENT` is abstract, and represents each Client entity: Buyer, Seller
</div>  

* stores the address book data i.e., all `CLIENT` objects (which are contained in a `UniqueCLIENTList` object).
* stores the currently 'selected' `CLIENT` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<client>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

Let's take a look at the internal structure of the `CLIENT` entity:

<img src="images/ClientClassDiagram.png" width="450" />

* all abstract `CLIENT` objects (Buyer or Seller) have a name and phone number.
* `Buyer` has `PropertyToBuy` while `Seller` has `PropertyToSell`.

Now, what PropertyToBuy and PropertyToSell classes encapsulate:

<img src="images/PropertyClassDiagram.png" width="450" />


<div markdown="span" class="alert alert-info">:information_source: Note that We have decided to separate these 2 fields and NOT make them inherit an abstract `Property` class.
 This is because sellers know the exact property (and address of the property) that they are selling.
 We can hence extend the code base more flexibly in the future if we remove some fields from PropertyToBuy or add more fields to PropertyToSell.
</div>  

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


### Match feature

#### Implementation

We are currently implementing a Match feature. In implements the following operation:

* `match` —  Matches a Buyer to a List of Seller.

Format: `match buyer_index`
* The fields are:
    * `buyer_index` - index of the Buyer that the user is trying to match with Sellers.
    
Example: `match 2`
    
Result:
* The list of sellers that match the buyer's demands are displayed in the UI.

#### How match is going to be implemented

* The match command will match a Buyer with Sellers whose `PropertyToSell` matches the demands of the `PropertyToBuy` of the buyer.

* How does match filter the sellers (How does `PropertyToBuy` match with `PropertyToSell`:

    -  If there exists a **price** where a buyer is willing to buy and seller is willing to sell for in their respective `buyRange` and `sellRange`, **AND**
    - Their House are equal (i.e, the Location and HouseType of the house matches)

* An example:
    - buyer's `PropertyToBuy`(after `edit` or `add`) has `House`, and buyer is currently at *index 2* of UniqueBuyerList.
        - `Name`: *Janald*
    
        - PropertyToBuy: 
            - `HouseType`: `BUNGALOW`,
            - `Location`: `Serangoon` and 
            - `PriceRange`:(50 000, 100 000) in dollars

    - a certain seller has
        - `Name`: *Junhong*
        - `ProperyToSell`: 
            - `House` with `HouseType`: `BUNGALOW` and `Location`: `Serangoon` as well
            - His `PriceRange` that he is willing to sell the property for is (99 999, 200 000)
      
    - In this case, the PropertyToBuy and PropertyToSell can match(same House, and 99 999 - 100 000 dollars is a matching price)
    - `match 2` will display the list of sellers that match the buyer *Janald*. As a result, *Junhong* will be one of the sellers displayed.
    
#### Why match should be implemented

* Our AgentSee application helps housing agents to keep track of their clients in an efficient manner.

* Since there are so many buyers and sellers to keep track of, it would be useful for agents to automate the matching of buyers to sellers.
* The match feature will help agents filter and find a matching property that a buyer wants to buy and a seller wants to sell, which is of great convenience for agents to liase buyers with sellers.
* What buyers look for when buying a Property is its `Location`, `HouseType`, and they have a `PriceRange` they are willing to pay for. Therefore, we are implementing `match` such that these conditions are met.

#### \[Proposed\]  Alternatives considered

* We can match buyers with other less strict conditions as well.
* For example, we can match buyers and sellers with only match:
    - HouseTypes only (example: `COLONIAL`, Since buyers may be looking only for a specific HouseType, regardless of Location)
    - Location only (example: `Toa Payoh`, Since some buyers may like to buy a Property at a specific area, regardless of other conditions)
    - PriceRange only (Since buyers may just be looking for properties in their buy range)

* As such, we have a more flexible match feature which would be ideal for agents to match based on their dynamic client demands.

### \[Proposed\] Bargain/Negotiate feature

* An additional feature that could be implemented in the future.

### Add Buyer feature
The `addbuyer` command mechanism uses a similar interactions as shown in the [Logic Component](#logic-component). Mainly, it can be broken down into these steps:

**Step 1:**

The user types input E.g. `addbuyer n/David p/12345678` into the `CommandBox` (See [UI component](#ui-component) for more info on `CommandBox`)

**Step 2:**

The `execute(input)` method of `LogicManager`, a subclass of the Logic component, is called with the given input.
An instance of the `AddressBookParser` will begin to parse the input into 2 main sections: the **command**
and the **body** of the command.

The main job of `AddressBookParser` at this step is to identify the `addbuyer` **command** which was supplied as the 1st word in the input string.

After which, control is handed over to the `AddBuyerCommandParser` component by calling its `AddBuyerCommandParser#parse(body)` method to parse the **body** which was separated out.

**Step 3:**

`AddBuyerCommandParser#parse(body)` verifies if required fields for `addbuyer` are present.

In our example, since `n/David p/12345678` was included, all required fields are present.

At this step, the new `Buyer` will have been successfully created. A new `AddBuyerCommand` with the Buyer is returned to the `AddressBookParser` to the `LogicManager`

![AddBuyerCommandObjectDiagram](images/AddBuyerCommandObjectDiagram.png)

**Step 4:**

The `LogicManager` component then calls `AddBuyerCommand#execute(model)`method of the new `AddBuyerCommand`instance containing the Buyer, with the `Model`component created from [Model component](#model-component).

In this method, if the Buyer does not currently already reside in the application, he/she is added into the Model through the `Model#addBuyer(Buyer)` command and stored in the Model.

(Refer to [Model component](#model-component) to see how Buyers are stored into the model)

A new `CommandResult` representing the successful `addbuyer` command is initialized and returned.

**Step 5:**

`LogicManager` component will then attempt to update the storage with this new `Model` through the `Storage#saveBuyerAddressBook()` method.

**Step 6:**

Finally, the `CommandResult`is returned to be displayed by `UI` component (Refer to [Architecture](#architecture))

The following Sequence Diagrams summarizes the various steps involved:

![AddBuyerSequenceDiagram](images/AddBuyerSequenceDiagram.png)



### 2. `editbuyer` / `For full details on implementation, check out this [link](https://github.com/AY2122S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/logic)
editseller` feature
The `editbuyer` / `editseller` command mechanism uses a similar interactions as shown in the [Logic Component](). Mainly, it can be broken down into these steps:
#### Syntax:
```editbuyer [index] n/... p/... t/... prop/ h/... l/... pr/...```

```editseller [index] n/... p/... t/... prop/ h/... l/... pr/...```

Note: All the prefix (like n/, p/, ...) are <b>optional</b>, you could omit any of them but at least one prefix should be provided, and the <b>order</b> of prefix does not matter.

Below are some detailed steps while executing `editbuyer` / `editseller` command: 

**We use ```editbuyer``` command as an example, the other command's flow are similar to this command as well.**

---

**Step 1:**

The user types input E.g. `editbuyer 1 n/Chua` into the `CommandBox`

**Step 2:**

Once the user hit Enter,  the  `LogicManager` calls `execute` that takes in everything user typed. 
Then, `AddressBookParser` will investigate the user's input. It will takes the first keyword: `editbuyer` and 
call the corresponding `EditBuyerCommandParser::parse` by providing the **arguments** (anything after than first word) from user input

**Step 3:**

`EditBuyerCommandParser` parse the argument provided and check the validity of the arguments. If any argument provided 
is not valid, an error will be shown the command won't be executed.

In our example, `1 n/Chua` was provided, the index and at least one require prefix are given, so it is a valid argument.

**Step 4:**

Now the `AddressBookParser` returns `EditBuyerCommand` as ``CommandResult``, the `LogicManager` then calls 
`CommandResult::execute`.

**Step 5:**

Now the `EditBuyerCommand` will execute and do the work, that is updating the corresponding information from the given
index. A `CommandResult` containing the successful execution result is returned.

**Note:** The validity of the index will be check at this time, if the index is out of bound, the error will be shown 
and the edit command will not be executed.

**Step 6:**

`LogicManager` component will then update the storage with the edited `Model` through the 
`Storage#saveBuyerAddressBook()` method.

**Step 7:**

Finally, the `CommandResult` is returned to be displayed by `UI` component (Refer to [Architecture](#architecture))

The Sequence Diagrams below summarizes the various steps involved:


![EditBuyerCommandDiagram](diagrams/EditBuyerCommandDiagram.png)

---

### Add property for buyer feature
The `add-ptb` command uses a similar mechanism as the `addbuyer` command mentioned [above](#add-buyer-feature), with the following differences:

1. An index needs to be specified along with the necessary fields
    E.g. `add-ptb 1 h/condo l/Serangoon pr/400000,900000`
2. The Parser (`AddPropertyToBuyCommandParser`) checks if the position parsed in is valid (Greater than equal to 0 and Smaller than or equal to the size of the Buyer list).
3. The updated buyer remains in the same position as before, while a new buyer is added to the end of the list

**\[Proposed\]** Alternatives considered:

- Given the time, the add property to buy feature can be integrated with the `addbuyer` command to allow users to add properties with the buyer,
instead of doing it in 2 commands. 
  - Pros:
    - More flexibility for experienced users
  - Cons:
    - More code to implement and test
- Allow for certain fields to be **optional** if a buyer is yet to give the user the information, but they still wish to add a property first
  - Pros:
    - More flexible design
  - Cons:
    - Hard to implement
    - Error prone

### \[Proposed\] Add Property to sell feature
The `add-pts` command is very similar to the above command with only slight differences:
1. An additional field is required: `address` of the seller's house


### `sort` feature
The `sort` command mechanism can be broken down into the following steps:

**Step 1:**
The user types input E.g.  `sort` into the `CommandBox` (See [UI component](#ui-component) for more info on `CommandBox`)

**Step 2:**
The `execute(input)` method of `LogicManager`, a subclass of the Logic component, is called with the given input.

**Step 3:**
The `sortFilteredClientList()` method of `model` is being called

**Step 4:**
The `Addressbook.sortPersons() ` method is being called.

**Step 5:** 
The `UniqueClientList.sortPersons() ` method is being called.

**Step 6:**
`UniqueClientList`'s `internalList` is being modified permanently by the order of their names alphabetically.

The following Sequence Diagrams summarizes the various steps involved:

`To be added later`

**Pros:**
It alters the internal list completely, so that the app 'saves' users last sorting option.

**Cons:**
Some people might not want the sorted result to be saved. 
Currently we are exploring other options to sort the list besides modifying the structure of internal list directly.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th client in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new client. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the client was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the client being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Clear buyer list/Clear seller list

#### Proposed Implementation

We are currently implementing a clear buyer list and clear seller list function.
#### Syntax:
- `clearb` - clears the buyer list
- `clears` - clears the seller list

#### Result:
- specified list is cleared without affecting the other list

#### Implementation of clear buyer and clear seller
The contents of buyerlist or sellerlist in `BuyerAddressBook.java` or `SellerAddressBook.java` is cleared.

#### Why is it implemented this way
The content of the uncleared list can be kept as such without reloading a fresh new book as seen in the AB3 command `clear`.

#### Alternatives
A copy of the uncleared list is kept, next the content of the whole addressbook can be cleared by `clear`, followed by loading of the uncleared content.

### Find buyer/Find seller

#### Syntax:
- `findb /KEYWORD [MORE_KEYWORDS]`
- `finds D/KEYWORD [MORE_KEYWORDS]`

Examples:
- `findb junhong junheng`
- `finds hdb 5room`

#### Result:
returns a filtered list of sellers of buyers

#### Implementation of find buyer and find seller
The finds and findb command calls `updateFilteredSellerList` of `model` and filters the list based on the keywords. The commands then calls `getFilteredSellerList` 
in order to return the filtered list of sellers.

#### Why is it implemented this way
Having a seperate buyer and seller list means we need to seperate the find command into find buyer and find seller in order to filter the desired list. Having seperate address books helps in this regard as the version of `getFilteredClientList` can be used.

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* real estate agent
* has a need to manage clients in an organized fashion
* mainly uses desktop for work
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Able to keep track of client's preferences, housing details and budget


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new client               |                                                                        |
| `* * *`  | user                                       | delete a client                | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a client by name          | locate details of clients without having to go through the entire list |
| `* *`    | user                                       | hide private contact details   | minimize chance of someone else seeing them by accident                |
| `*`      | user with many clients in the address book | sort clients by name           | locate a client easily                                                 |
| Priority | As a …​                             | I want to …​                                                                     | So that I can…​                         
| `* * *` | housing agent with many clients     | view client details fast                                                         | can deal with customers easily when they contact me |
| `* * *` | housing agent                       | add a new client quickly with a quick description                                | update my client list efficiently                   |
| `* * *` | housing agent                       | see relevant information about my clients                                        | understand their needs                              |
| `* * *` | housing agent                       | edit my client data                                                              | stay in touch with their changing needs             |
| `* * *` | housing agent                       | delete a client when their house has been sold or after they have bought a house | not mix up information in the future                |
| `* * *` | housing agent                       | access some of my favourite clients quickly                                      | always focus on them                                |
| `* * *` | housing agent                       | "tag" my clients with custom text                                                | remember every client easily                        |
| `* * *` | housing agent with too many clients | be able to find a client by name                                                 | locate them easily and quickly                      |
| `* *`  | housing agent                       | have access to my search history                                                 | look up my recently contacted clients               |
| `* *`  | new user                            | have a quick guide to start me off                                               | learn how to use the application                    |
| `* *`  | housing agent                       | check my important deadlines                                                     | avoid missing important meetings with my clients    |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is `AgentSee` and the **Actor** is the `user`, unless specified otherwise)


**Use case: Delete a client**

**MSS**

1.  User requests to list clients
2.  AddressBook shows a list of clients
3.  User requests to delete a specific client in the list
4.  AddressBook deletes the client

**Use case: Add a client**

**MSS**

1.  User types in client information
2.  AddressBook adds the new client


    Use case ends.

**Extensions**

* 1a. The wrong format is used.

  * 1a1. System shows an error message.

    Use case resumes at step 1.

* 1b. A duplicate client is entered.

    * 1b. System shows an error message.

      Use case resumes at step 1.

*{More to be added}*

**Use case: Edit a client**

*{More to be added}*

**Use case: Delete a client**

*{More to be added}*

### Non-Functional Requirements


1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1.  Essential: Technical: Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Typical: Performance: Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
3.  Typical: A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Essential" Technical: Should work on both 32-bit and 64-bit environments.
5.  Novel: Quality: Should be usable by a novice who is not extremely tech savvy.
6.  Typical: Scalability: Features and improvements should be easy to implement iteratively.
6.  Typical: Constraints: Minimal mouse clicking to interact with Ui.
7.  Essential: Process requirements: the project is expected to adhere to a schedule that delivers a feature set according to the module requirements.
8.  Typical: Business/domain: Clients should be unique; they cannot have the exact same fields.
9.  Notes about project scope: the product is not required to handle any sort of physical printing (e.g client list hardcopy).
10. Typical: Disaster recovery: If user makes a mistake on his client list, he should be able to recover from the mistake quickly without too much stress.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Real estate agent**: Agent who is the medium that manages clients, and is the target persona for our product.
* **Client**: Seller who is looking to sell their property.
* **Address**: Address of the Property that Sellers are trying to sell.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a client

1. Deleting a client while all clients are being shown

   1. Prerequisites: List all clients using the `list` command. Multiple clients in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No client is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
