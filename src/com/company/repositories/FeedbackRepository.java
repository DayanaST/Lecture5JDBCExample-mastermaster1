package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Feedback;
import com.company.repositories.interfaces.IFeedbackRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FeedbackRepository implements IFeedbackRepository {

    private final IDB db;

    public FeedbackRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean saveFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedbacks(user_name, message, rating) VALUES (?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, feedback.getUserName());
            st.setString(2, feedback.getMessage());
            st.setInt(3, feedback.getRating());

            return st.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
