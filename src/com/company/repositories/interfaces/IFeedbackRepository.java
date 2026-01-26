package com.company.repositories.interfaces;

import com.company.models.Feedback;

public interface IFeedbackRepository {
    boolean saveFeedback(Feedback feedback);
}
