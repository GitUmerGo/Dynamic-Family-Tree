class FamilyTree {
    private FamilyTreeNode ancestor;

    public FamilyTree(String ancestorName) {
        this.ancestor = new FamilyTreeNode(ancestorName);
    }

    public FamilyTreeNode getAncestor() {
        return ancestor;
    }

    public void addPartner(String personName, String partnerName) throws IllegalArgumentException {
        // Find the specified person in the family tree
        FamilyTreeNode personNode = findNodeByName(personName, ancestor);
        if (personNode == null) {
            throw new IllegalArgumentException("Person not found.");
        }

        // Check if the person already has a partner
        if (personNode.getPartner() != null) {
            throw new IllegalArgumentException("This person already has a partner.");
        }

        // Create a partner node and link it to the person node as a partner
        FamilyTreeNode partnerNode = new FamilyTreeNode(partnerName);
        personNode.setPartner(partnerNode);
        partnerNode.setPartner(personNode);
    }

    public void addChild(String parentName, String childName) throws IllegalArgumentException {
        // Find the parent node in the family tree
        FamilyTreeNode parentNode = findNodeByName(parentName, ancestor);
        if (parentNode == null) {
            throw new IllegalArgumentException("Parent not found.");
        }

        // Check if the parent has a partner
        if (parentNode.getPartner() == null) {
            throw new IllegalArgumentException("Parent must have a partner to add children.");
        }

        // Check if the parent's partner has children
        if (parentNode.getPartner().getFirstChild() != null) {
            throw new IllegalArgumentException("Parent's partner must not have children.");


        }
        // Add the child to the parent
        parentNode.addChild(childName);
    }

    @Override
    public String toString() {
        return displayFamilyTree(ancestor, 0); // Start with the ancestor node
    }

    public FamilyTreeNode findMember(String memberName) {
        return findNodeByName(memberName, ancestor);
    }

    private String displayFamilyTree(FamilyTreeNode node, int depth) {
        StringBuilder builder = new StringBuilder();
        builder.append(node.getName()).append(" (identifier ").append(node.getIdentifier()).append(")");

        // Check if the person has a partner
        if (node.getPartner() != null) {
            builder.append(" partner ").append(node.getPartner().getName()).append(" (identifier ").append(node.getPartner().getIdentifier()).append(")");
        } else {
            builder.append(" has no partner");
        }

        builder.append("\n");

        // Display children
        FamilyTreeNode child = node.getFirstChild();
        if (child != null) {
            while (child != null) {
                builder.append("\t".repeat(depth + 1)).append(child.getName()).append(" (identifier ").append(child.getIdentifier()).append(")");

                // Check if the child has a partner
                if (child.getPartner() != null) {
                    builder.append(" partner ").append(child.getPartner().getName()).append(" (identifier ").append(child.getPartner().getIdentifier()).append(")");
                } else {
                    builder.append(" has no partner");
                }

                builder.append("\n");

                // Recursively display grandchildren
                builder.append(displayFamilyTree(child, depth + 1));

                child = child.getNextSibling();
            }
        } else if (node.getPartner() != null) {
            builder.append("\t".repeat(depth + 1)).append("no children\n");
        }

        return builder.toString();
    }



    public FamilyTreeNode findMemberByIdentifier(int identifier) {
        return findNodeByIdentifier(identifier, ancestor);
    }



    // Utility method to find a node by name
    private FamilyTreeNode findNodeByName(String name, FamilyTreeNode startNode) {
        if (startNode == null) {
            return null;
        }

        if (startNode.getName().equalsIgnoreCase(name)) {
            return startNode;
        }

        FamilyTreeNode sibling = startNode.getNextSibling();
        FamilyTreeNode child = startNode.getFirstChild();
        while (child != null || sibling != null) {
            if (child != null) {
                FamilyTreeNode found = findNodeByName(name, child);
                if (found != null) {
                    return found;
                }
                child = child.getNextSibling();
            }
            if (sibling != null) {
                if (sibling.getName().equalsIgnoreCase(name)) {
                    return sibling;
                }
                sibling = sibling.getNextSibling();
            }
        }
        return null;
    }

    private FamilyTreeNode findNodeByIdentifier(int identifier, FamilyTreeNode startNode) {
        if (startNode == null) {
            return null;
        }

        if (startNode.getIdentifier() == identifier) {
            return startNode;
        }

        FamilyTreeNode sibling = startNode.getNextSibling();
        FamilyTreeNode child = startNode.getFirstChild();
        while (child != null || sibling != null) {
            if (child != null) {
                FamilyTreeNode found = findNodeByIdentifier(identifier, child);
                if (found != null) {
                    return found;
                }
                child = child.getNextSibling();
            }
            if (sibling != null) {
                if (sibling.getIdentifier() == identifier) {
                    return sibling;
                }
                sibling = sibling.getNextSibling();
            }
        }
        return null;
    }
}
