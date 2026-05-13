package com.example.Expense_Tracker_AI.chat;

import com.example.Expense_Tracker_AI.model.Budget;
import com.example.Expense_Tracker_AI.model.Expenses;
import com.example.Expense_Tracker_AI.model.Users;
import com.example.Expense_Tracker_AI.repository.BudgetRepository;
import com.example.Expense_Tracker_AI.repository.ExpenseRepository;
import com.example.Expense_Tracker_AI.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
public class ChatController {
    private final ChatClient chatClient;
    public ChatController (ChatClient.Builder chatClient){
        this.chatClient=chatClient.build();
    }

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ExpenseRepository expRepo;
    @Autowired
    private BudgetRepository budRepo;
    @Autowired
    private HttpSession session;

    @GetMapping("/allinsights")
    public String overAllInsights(){
        Integer id = (Integer) session.getAttribute("loggedInUser");
        if(id==null){
            return "User not logged in";
        }
        Users user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return "Invalid User!";
        }
        List<Expenses> expenses=expRepo.findByUser(user);
        if(expenses == null){
            return "The Expenses is not set";
        }
        Budget budget=budRepo.findByUserId(id);
        if(budget == null){
            return "The budget is not set";
        }

        StringBuilder summary = new StringBuilder();

        double totalExpense = 0;
        double totalBudget = 0;

        /* Store category totals */
        Map<String, Double> categoryTotals = new HashMap<>();

        summary.append("User Financial Summary\n\n");

        /* Total Budget */
        totalBudget = budget.getAmount();

        summary.append("Total Budget Allocated: ")
                .append(totalBudget)
                .append(" rupees\n\n");

        /* Group Expenses By Category */
        for (Expenses exp : expenses) {

            String category = exp.getCategory();
            double amount = exp.getAmount();

            totalExpense += amount;

            categoryTotals.put(
                    category,
                    categoryTotals.getOrDefault(category, 0.0) + amount
            );
        }

        /* Category-wise Spending */
        summary.append("Category-wise Spending:\n");

        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {

            summary.append("- ")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" rupees\n");
        }

        /* Total Expense */
        summary.append("\nTotal Expenses: ")
                .append(totalExpense)
                .append(" rupees\n");

        /* Remaining Balance */
        summary.append("Remaining Savings: ")
                .append(totalBudget - totalExpense)
                .append(" rupees\n\n");
        /* Final AI Request */
        summary.append("""
        Analyze the user's financial habits and provide:
        - spending insights
        - saving suggestions
        - overspending warnings
        - budgeting advice
        """);

        var systeminstructions= """ 
                You are an AI-powered financial insights assistant.

                Your job is to analyze the user's budget and expense data and provide:
                - spending insights
                - saving suggestions
                - overspending warnings
                - budgeting advice
                - financial observations

        Rules:
        - Be concise and practical.
                - Give responses in simple language.
        - Mention categories where spending is high.
                - Suggest ways to reduce unnecessary expenses.
                - Encourage better budgeting habits.
                - Do not generate fake data.
        - Use only the provided financial information.
                - Format the response clearly using bullet points.
        - If expenses are within budget, appreciate the user.
        - If expenses exceed the budget, warn the user politely.
        """;
        return chatClient.prompt()
                .system(systeminstructions)
                .user(u->u.text(summary.toString()))
                .call()
                .content();
    }

    @GetMapping("/minsights")
    public String monthlyInsights(@RequestParam int month,@RequestParam int year){
        Integer id = (Integer) session.getAttribute("loggedInUser");
        if(id==null){
            return "User not logged in";
        }
        Users user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return "Invalid User!";
        }
        List<Expenses> expenses=expRepo.findByUserIdAndMonthAndYear(id,month,year);
        Budget budget=budRepo.findByUserIdAndMonthAndYear(id,month,year);
        if(budget == null){
            return "The budget is not set for the month";
        }
        Double monthexpensesum=expRepo.getMonthlyExpenseSum(id,month,year);
        if(monthexpensesum == null) return "The expenses are not added for the month";

        StringBuilder summary = new StringBuilder();

        summary.append("Monthly Budget: ")
                .append(budget.getAmount())
                .append("\n\n");

        summary.append("Expenses:\n");

        for(Expenses exp : expenses){

            summary.append("- ")
                    .append(exp.getCategory())
                    .append(" : ")
                    .append(exp.getAmount())
                    .append("\n");
        }

        summary.append("\nTotal Expenses: ")
                .append(monthexpensesum);

        summary.append("\nRemaining Budget: ")
                .append(budget.getAmount() - monthexpensesum);

        summary.append("\n\nAnalyze the spending and give insights and suggestions.");

        /* Final AI Request */
        summary.append("""
        Analyze the user's financial habits and provide:
        - spending insights
        - saving suggestions
        - overspending warnings
        - budgeting advice
        """);

        var systeminstructions= """ 
                You are an AI-powered financial insights assistant.

                Your job is to analyze the user's budget and expense data and provide:
                - spending insights
                - saving suggestions
                - overspending warnings
                - budgeting advice
                - financial observations

        Rules:
        - Be concise and practical.
                - Give responses in simple language.
        - Mention categories where spending is high.
                - Suggest ways to reduce unnecessary expenses.
                - Encourage better budgeting habits.
                - Do not generate fake data.
        - Use only the provided financial information.
                - Format the response clearly using bullet points.
        - If expenses are within budget, appreciate the user.
        - If expenses exceed the budget, warn the user politely.
        """;
        return chatClient.prompt()
                .system(systeminstructions)
                .user(u->u.text(summary.toString()))
                .call()
                .content();
    }

}
