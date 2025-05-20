package com.bikerental.feedback.controller;

import com.bikerental.feedback.model.Feedback;
import com.bikerental.feedback.util.FeedbackFileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import java.io.IOException;

@WebServlet("/submitFeedback")
public class SubmitFeedbackServlet extends HttpServlet {
    private FeedbackFileHandler feedbackFileHandler;

    @Override
    public void init() throws ServletException {
        // Initialize FeedbackFileHandler with the ServletContext
        ServletContext context = getServletContext();
        feedbackFileHandler = new FeedbackFileHandler(context);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get form parameters
            String user = request.getParameter("user");
            String comments = request.getParameter("comments");
            int rating = Integer.parseInt(request.getParameter("rating"));

            // Get next available ID
            int id = feedbackFileHandler.getNextId();

            // Create Feedback object
            Feedback feedback = new Feedback(id, user, comments, rating);

            // Save feedback to file
            feedbackFileHandler.addFeedback(feedback);

            // Redirect to view page with success message
            response.sendRedirect("viewFeedback?message=Feedback submitted successfully");

        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();

            // Forward back to the form with error message
            request.setAttribute("error", "Error saving feedback: " + e.getMessage());
            request.getRequestDispatcher("submitFeedback.jsp").forward(request, response);
        }
    }
}







