package com.bikerental.feedback.controller;

import com.bikerental.feedback.model.Feedback;
import com.bikerental.feedback.util.FeedbackFileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submitFeedback")
public class SubmitFeedbackServlet extends HttpServlet {
    private FeedbackFileHandler feedbackFileHandler;

    @Override
    public void init() throws ServletException {
        feedbackFileHandler = new FeedbackFileHandler(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form data
        String user = request.getParameter("user");
        String comments = request.getParameter("comments");
        int rating = Integer.parseInt(request.getParameter("rating"));

        // Generate a unique ID
        int id = feedbackFileHandler.getNextId();

        // Create and save feedback
        Feedback feedback = new Feedback(id, user, comments, rating);
        feedbackFileHandler.addFeedback(feedback);

        // Redirect to thankyou.jsp
        response.sendRedirect("thankyou.jsp");
    }
}