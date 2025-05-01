package model.modelGame.modelScore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class ScoreSaveModel {
    private static final String FILE_NAME = "scores.json";
    private static final int MAX_SCORES = 10;
    private List<ScoreModel> scores;

    public ScoreSaveModel() {
        this.scores = new ArrayList<>();
        loadScores();
    }
    private void loadScores() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // No scores yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONArray array = new JSONArray(json.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                scores.add(new ScoreModel(obj.getString("name"), obj.getInt("score")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveScores() {
        JSONArray array = new JSONArray();
        for (ScoreModel s : scores) {
            JSONObject obj = new JSONObject();
            obj.put("name", s.name);
            obj.put("score", s.score);
            array.put(obj);
        }

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(array.toString(4)); // Pretty print with indent
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addScore(String name, int score) {
        scores.add(new ScoreModel(name, score));
        scores.sort((a, b) -> Integer.compare(b.score, a.score));
        if (scores.size() > MAX_SCORES) {
            scores = scores.subList(0, MAX_SCORES);
        }
        saveScores();
    }
    public List<ScoreModel> getScores() {
        return scores;
    }
    public void displayScores() {
        System.out.println("Best Scores :\n");
        for (ScoreModel s : scores) {
            System.out.println(s.name + " - " + s.score+"\n");
        }
    }
}
