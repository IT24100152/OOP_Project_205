package com.bikerental.feedback.util;

import com.bikerental.feedback.model.Feedback;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackFileHandler {
    private ServletContext context;

    public FeedbackFileHandler(ServletContext context) {
        this.context = context;
    }

    // Method to get the absolute file path dynamically
    private String getFilePath() {
        String path = context.getRealPath("/WEB-INF/data/feedback.txt");
        if (path == null) {
            // Fallback to absolute path if getRealPath is null (e.g., in development)
            path = "C:/path/to/your/project/WEB-INF/data/feedback.txt"; // Replace with your path
        }
        return path;
    }

    // Method to get all feedbacks from the file
    public List<Feedback> getAllFeedbacks() throws IOException {
        List<Feedback> feedbacks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String user = data[1];
                    String comments = data[2];
                    int rating = Integer.parseInt(data[3]);
                    feedbacks.add(new Feedback(id, user, comments, rating));
                }
            }
        }
        return feedbacks;
    }

    // Method to add feedback to the file
    public void addFeedback(Feedback feedback) throws IOException {
        List<Feedback> feedbacks = getAllFeedbacks();
        feedbacks.add(feedback);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), false))) {
            for (Feedback fb : feedbacks) {
                writer.write(fb.getId() + "," + fb.getUser() + "," + fb.getComments() + "," + fb.getRating());
                writer.newLine();
            }
        }
    }

    // Method to update feedback in the file
    public void updateFeedback(Feedback feedback) throws IOException {
        List<Feedback> feedbacks = getAllFeedbacks();
        for (int i = 0; i < feedbacks.size(); i++) {
            if (feedbacks.get(i).getId() == feedback.getId()) {
                feedbacks.set(i, feedback);
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), false))) {
            for (Feedback fb : feedbacks) {
                writer.write(fb.getId() + "," + fb.getUser() + "," + fb.getComments() + "," + fb.getRating());
                writer.newLine();
            }
        }
    }

    // Method to delete feedback from the file
    public void deleteFeedback(int feedbackId) throws IOException {
        List<Feedback> feedbacks = getAllFeedbacks();
        feedbacks.removeIf(fb -> fb.getId() == feedbackId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), false))) {
            for (Feedback fb : feedbacks) {
                writer.write(fb.getId() + "," + fb.getUser() + "," + fb.getComments() + "," + fb.getRating());
                writer.newLine();
            }
        }
    }

    // Method to get the next available ID
    public int getNextId() throws IOException {
        List<Feedback> feedbacks = getAllFeedbacks();
        int maxId = 0;
        for (Feedback feedback : feedbacks) {
            maxId = Math.max(maxId, feedback.getId());
        }
        return maxId + 1;
    }
}



