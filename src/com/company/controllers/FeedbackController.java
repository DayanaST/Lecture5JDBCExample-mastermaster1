package com.company.controllers;

import com.company.controllers.interfaces.IFeedbackController;
import com.company.models.Feedback;
import com.company.repositories.interfaces.IFeedbackRepository;

public class FeedbackController implements IFeedbackController {

    private final IFeedbackRepository repo;

    public FeedbackController(IFeedbackRepository repo) {
        this.repo = repo;
    }

    @Override
    public String leaveFeedback(String userName, String message, int rating) {
        if (rating < 1 || rating > 5) {
            return "Rating must be between 1 and 5!";
        }

        Feedback feedback = new Feedback(userName, message, rating);
        boolean success = repo.saveFeedback(feedback);

        return success ? "Feedback saved successfully!" : "Error saving feedback.";
    }
}
