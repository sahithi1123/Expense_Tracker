package com.example.Expense.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Expense.model.Expenses;
import com.example.Expense.model.Users;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Integer> {

    List<Expenses> findByUser(Users user);

    Page<Expenses> findByUserId(Integer id, Pageable pageable); // This is valid

    @Query("SELECT SUM(e.amount) FROM Expenses e WHERE e.user.id = :userId " +
           "AND FUNCTION('MONTH', e.date) = :month AND FUNCTION('YEAR', e.date) = :year")
    Double getMonthlyExpenseSum(@Param("userId") int userId,
                                @Param("month") int month,
                                @Param("year") int year);

   List<Expenses> findByUserIdAndCategoryContainingIgnoreCase(Integer userId, String category);



}


