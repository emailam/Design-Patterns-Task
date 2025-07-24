package ChainOfResponsibility;

public class Before {
    static class ExpenseApproval {
        public void approveExpense(int amount) {
            if (amount <= 1000) {
                System.out.println("Manager approved: $" + amount);
            } else if (amount <= 5000) {
                System.out.println("Director approved: $" + amount);
            } else if (amount <= 10000) {
                System.out.println("VP approved: $" + amount);
            } else {
                System.out.println("CEO approved: $" + amount);
            }

            // what if we need to add new approval levels?
            // what if approval logic becomes more complex?
        }
    }

    public static void main(String[] args) {
        ExpenseApproval expenseApproval = new ExpenseApproval();
        expenseApproval.approveExpense(1001);
        expenseApproval.approveExpense(7001);
        expenseApproval.approveExpense(20001);
    }
}
