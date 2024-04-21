public class FamilyTreeTest {
    public static void main(String[] args) {
        String ancestorName = Input.getString("Please Enter the Ancestor's name: ");
        FamilyTree familyTree = new FamilyTree(ancestorName);
        Integer option;
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Family Tree Menu:");
            System.out.println("Please enter 1 - Add a Partner");
            System.out.println("Please enter 2 - Add a Child");
            System.out.println("Please enter 3 - Display the Whole Family Tree");
            System.out.println("Please enter 4 - Display a Specific Family Member of Ancestor or Children through their identifier ");
            System.out.println("Please enter 5 - Exit");
            System.out.print("Enter your choice: ");
            option = Input.getInteger("");

            switch (option) {
                case 1:
                    addPartner(familyTree);
                    break;
                case 2:
                    addChildMenu(familyTree);
                    break;
                case 3:
                    displayFamilyTree(familyTree);
                    break;
                case 4:
                    displaySpecificFamilyMember(familyTree);
                    break;
                case 5:
                    exitMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addPartner(FamilyTree familyTree) {
        String personName = Input.getString("Enter the name of the family member to add a partner to: ");
        String partnerName = Input.getString("Enter the name of the partner: ");
        try {
            familyTree.addPartner(personName, partnerName);
            System.out.println("Partner added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addChildMenu(FamilyTree familyTree) {
        // Check if the ancestor has a partner
        if (familyTree.getAncestor().getPartner() == null) {
            System.out.println("The ancestor does not have a partner. Unable to add child.");
            return;
        }
        String parentName = Input.getString("Enter the name of the parent: ");
        String childName = Input.getString("Enter the name of the child: ");
        try {
            familyTree.addChild(parentName, childName);
            System.out.println("Child added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayFamilyTree(FamilyTree familyTree) {
        System.out.println(familyTree);
    }

    private static void displaySpecificFamilyMember(FamilyTree familyTree) {
        int identifier = Input.getInteger("Enter the identifier of the family member to display: ");
        FamilyTreeNode member = familyTree.findMemberByIdentifier(identifier);
        if (member != null) {
            System.out.println("Family Member Details:");
            System.out.println(member);
        } else {
            System.out.println("Family member with identifier " + identifier + " not found.");
        }
    }

    private static void exitMenu() {
        System.out.println("Thank you for using the Family Tree Menu !");
        System.exit(0);
    }
}
