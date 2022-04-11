---
layout: page
title: Wang Jun Hong's Project Portfolio Page
---

### Project: AgentSee

AgentSee is a desktop application for property agents to efficiently manage their client information.

Given below are my contributions to the project.

* **Code contributed**:
    * `add-b` command
    * `add-ptb` and `add-pts` commands
    * `delete-b` & `delete-s` command
    * `add-ptb` and `add-pts` and `delete-b` and `delete-s` parser & associated test classes
    * `Buyer` class
    * `AddBuyerCommandParser` class
    * `BuyerTest` class
    * `UniqueBuyerList` class
    * `UniqueBuyerListTest` class
    * `BuyerBuilder` & `TypicalBuyer` class
    * `AddBuyerCommandTest` and `AddBuyerCommandIntegrationTest` class
    * `BuyerAddressBookStorage` and other buyer related storage classes.
    * `ReadOnlyBuyerAddressBook`, `BuyerAddressBook`
    * FXML for UI updates
    * `NullPropertyToBuy` (initial class before enhancements).
    * `TypicalPropertyToBuy` & `TypicalPropertyToSell`, `HouseTypeTest`, `PropertyCommandsTestUtil`
    * `BuyerAddressBookTest` & `SellerAddressBookTest` classes

* **Enhancements implemented**:
  * Improved Find Command for v1.2 - Keywords need not exactly match the client name, and can simply be contained in them.
  * The UI - Decent overhaul to the UI in terms of the organization of the fields, font sizes and icons.
  * Improved the House Type - Created methods to query the enum using strings, and translate a set of possible strings to a specific enum.

* **Contributions to the UG**:
  * Added documentation features for `find` command in v1.1.
  * Came up with screenshots shown on the User Guide
  * Added `addbuyer` command to the list of commands in v1.2.
  * Major overhaul of the UG to enforce consistency and enhance navigability for v1.3
    * Change the introduction
    * Added to the quick start
    * Added to the important notes
    * Reorganized and added most of the missing commands and their descriptions for both buyers and sellers
    * Updated the summary table with all added commands
  * Added elaborations for many issues raised in PE-D like:
    * Why names cannot be identical
    * Why phone numbers can be identical
    * Location & address being not matching
    * Price range problems
    * Why text cuts off
    * Added explanation for design choices in `add-b`, `add-s`, `add-ptb`, `add-pts`

* **Contributions to the DG**:
    * Added initial implementation details for `find` command
    * Added implementation details for `add-b` command
    * Added User Stories and Use cases to Developer Guide
    * Fix Use cases
    * Fix UML diagrams and other components referencing AB3 commands

* **Contributions to team tasks**:
    * Set up initial GitHub organization and repository
    * Set up milestone [#v1.1](https://github.com/AY2122S2-CS2103T-T11-2/tp/milestone/1)
    * Set up milestone and some issues for [#v1.2](https://github.com/AY2122S2-CS2103T-T11-2/tp/milestone/2)
    * Set up milestone and issues for [#v1.3](https://github.com/AY2122S2-CS2103T-T11-2/tp/milestone/3)
    * Complete `addbuyer`, `add-pts`, `add-ptb`, UG update and UI update issues.
    * Help to assign PE-D issues to respective teammates.
    * Set up [#v1.4](https://github.com/AY2122S2-CS2103T-T11-2/tp/milestone/4)
    * Edited product demo video + voice over.

* **Review contributions**:
  * Made review for multiple Pull Requests such as
    * [#34](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/34) where I commented that there were some problems with import styling
    * [#48](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/48) where I read through how sort command works and gave my approval
    * [#87](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/87) where I commented on the missing javadoc descriptions
    * [#266](https://github.com/AY2122S2-CS2103T-T11-2/tp/pull/266) where I recommended my teammate to use a more specific error message
