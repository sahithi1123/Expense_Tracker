package com.example.Expense_Tracker_AI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Expense_Tracker_AI.model.Budget;
import com.example.Expense_Tracker_AI.model.Users;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Integer> {
   public  Budget findByUserAndMonth(Users user,int month);

   Budget getBudgetByUserId(@Param("userId") int userId);

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.month = :month AND b.year = :year")
    Budget findByUserIdAndMonthAndYear(@Param("userId") int userId,
                                       @Param("month") int month,
                                       @Param("year") int year);

   public Budget findByUserId(int id);

   public Budget findById(int id);

   public Page<Budget> findByUserId(Integer id, Pageable pageable);
}

