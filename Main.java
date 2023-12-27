import java.util.*;

public class Main {
    public static void main(String[] args) {
        AVLTree treeAVL = new AVLTree();
        RedBlackTree treeRB = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        System.out.println(Strings.TREE_INSERTING_VALUE);
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase(Strings.END)) {
                treeAVL.finishAVL();
                break;
            }
            try {
                int value = Integer.parseInt(input);
                numbers.add(value);
                treeAVL.root = treeAVL.insert(treeAVL.root, value);
                String visualTree = treeAVL.generateVisualTree(treeAVL.root, "", false);
                System.out.println(visualTree);

            } catch (NumberFormatException e) {
                System.out.println(Strings.INVALIDATE_VALUE);
            }
        }
        while (true) {
            System.out.println("\n" +
                    Strings.MENU_ITEM_1 +
                    Strings.MENU_ITEM_2
            );
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }else if(choice == 1){
                treeRB.insertList(numbers);
            }else {
                System.out.println(Strings.INVALIDATE_VALUE);
            }

        }
    }
}