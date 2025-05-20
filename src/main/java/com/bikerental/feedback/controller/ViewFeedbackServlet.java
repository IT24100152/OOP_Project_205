package com.bikerental.feedback.controller;

import com.bikerental.feedback.model.Feedback;
import com.bikerental.feedback.util.FeedbackFileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewFeedback")
public class ViewFeedbackServlet extends HttpServlet {
    private FeedbackFileHandler feedbackFileHandler;

    @Override
    public void init() throws ServletException {
        // Pass the ServletContext to the FeedbackFileHandler constructor
        feedbackFileHandler = new FeedbackFileHandler(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all feedbacks from the file
        List<Feedback> feedbackList = feedbackFileHandler.getAllFeedbacks();

        // Set the feedback list as a request attribute to display in the JSP
        request.setAttribute("feedbacks", feedbackList);

        // Forward the request to viewFeedback.jsp
        request.getRequestDispatcher("viewFeedback.jsp").forward(request, response);
    }
}



