package com.bikerental.feedback.controller;

import com.bikerental.feedback.util.FeedbackFileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteFeedback")
public class DeleteFeedbackServlet extends HttpServlet {
    private FeedbackFileHandler feedbackFileHandler;

    @Override
    public void init() throws ServletException {
        // Initialize the FeedbackFileHandler with the servlet context if needed
        feedbackFileHandler = new FeedbackFileHandler(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("id"));

        try {
            // Delete the feedback from the file
            feedbackFileHandler.deleteFeedback(feedbackId);
            // Redirect to the viewFeedback.jsp page with a success message
            response.sendRedirect("viewFeedback?message=Feedback deleted successfully");
        } catch (Exception e) {
            // Handle errors (e.g., feedback not found, file issues)
            response.sendRedirect("viewFeedback?error=Error deleting feedback");
        }
    }
}


