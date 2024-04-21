public class FamilyTreeNode {
    private String name;
    private FamilyTreeNode partner;
    private FamilyTreeNode nextSibling;
    private FamilyTreeNode firstChild;
    private int identifier; // Unique identifier for each node

    private static int nextIdentifier = 1; // Static variable to generate identifiers

    public FamilyTreeNode(String name) {
        this.name = name;
        this.identifier = nextIdentifier++; // Assign unique identifier
        this.partner = null;
        this.nextSibling = null;
        this.firstChild = null;
    }

    public int getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getIndentedName(0)).append(" (identifier ").append(identifier).append(")");

        // Check if the person has a partner
        if (partner != null) {
            builder.append(" partner ").append(partner.getIndentedName(0)).append(" (identifier ").append(partner.getIdentifier()).append(")");
        } else {
            builder.append(" has no partner");
        }

        builder.append("\n");

        // Display children with indentation
        if (firstChild != null) {
            builder.append(displayChildrenWithIndentation(firstChild, 1));
        }

        return builder.toString();
    }

    private String getIndentedName(int depth) {
        StringBuilder indentedName = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentedName.append(" - ");
        }
        indentedName.append(name);
        return indentedName.toString();
    }

    private String displayChildrenWithIndentation(FamilyTreeNode node, int depth) {
        StringBuilder builder = new StringBuilder();
        while (node != null) {
            builder.append("\t".repeat(depth)).append(node.getIndentedName(depth)).append(" (identifier ").append(node.getIdentifier()).append(")");

            // Check if the child has a partner
            if (node.getPartner() != null) {
                builder.append(" partner ").append(node.getPartner().getIndentedName(depth)).append(" (identifier ").append(node.getPartner().getIdentifier()).append(")");
            } else {
                builder.append(" has no partner");
            }

            builder.append("\n");

            // Recursively display children
            if (node.getFirstChild() != null) {
                builder.append(displayChildrenWithIndentation(node.getFirstChild(), depth + 1));
            }

            node = node.getNextSibling();
        }
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FamilyTreeNode getPartner() {
        return partner;
    }

    public void setPartner(FamilyTreeNode partner) {
        this.partner = partner;
    }

    public FamilyTreeNode getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(FamilyTreeNode sibling) {
        this.nextSibling = sibling;
    }

    public FamilyTreeNode getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(FamilyTreeNode child) {
        this.firstChild = child;
    }

    public void addChild(String childName) {
        FamilyTreeNode childNode = new FamilyTreeNode(childName); // Removed identifier parameter
        if (this.firstChild == null) {
            this.firstChild = childNode;
        } else {
            FamilyTreeNode lastChild = this.firstChild;
            while (lastChild.getNextSibling() != null) {
                lastChild = lastChild.getNextSibling();
            }
            lastChild.setNextSibling(childNode);
        }
    }

    private void appendChildren(StringBuilder builder, FamilyTreeNode node) {
        while (node != null) {
            builder.append(" ").append(node.getName());
            if (node.getPartner() != null) {
                builder.append(" partner ").append(node.getPartner().getName());
            }
            builder.append("\n");
            appendChildren(builder, node.getFirstChild());
            node = node.getNextSibling();
        }
    }
}
