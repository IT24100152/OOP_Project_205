package com.bikerental.feedback.controller;

import com.bikerental.feedback.model.Feedback;
import com.bikerental.feedback.util.FeedbackFileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List; // Import List
import java.util.stream.Collectors; // Import Collectors

@WebServlet("/editFeedback")
public class UpdateFeedbackServlet extends HttpServlet {
    private FeedbackFileHandler feedbackFileHandler;

    @Override
    public void init() throws ServletException {
        // Ensure to pass ServletContext if needed
        feedbackFileHandler = new FeedbackFileHandler(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("id"));
        List<Feedback> feedbacks = feedbackFileHandler.getAllFeedbacks(); // Retrieve all feedbacks

        // Filter feedback by ID and ensure the feedback is found
        Feedback feedback = feedbacks.stream()
                .filter(f -> f.getId() == feedbackId) // Ensure getId() exists in Feedback
                .findFirst()
                .orElse(null);

        if (feedback != null) {
            request.setAttribute("feedback", feedback); // Set the feedback object to JSP
            request.getRequestDispatcher("editFeedback.jsp").forward(request, response); // Forward to edit form
        } else {
            response.sendRedirect("viewFeedback?error=Feedback not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("id"));
        String user = request.getParameter("user");
        String comments = request.getParameter("comments");
        int rating = Integer.parseInt(request.getParameter("rating"));

        // Create the updated Feedback object
        Feedback updatedFeedback = new Feedback(feedbackId, user, comments, rating);

        try {
            feedbackFileHandler.updateFeedback(updatedFeedback); // Update feedback in the file
            response.sendRedirect("viewFeedback?message=Feedback updated successfully");
        } catch (Exception e) {
            // Handle error and forward to the edit page with error message
            request.setAttribute("error", "Error updating feedback: " + e.getMessage());
            request.getRequestDispatcher("editFeedback.jsp").forward(request, response);
        }
    }
}




