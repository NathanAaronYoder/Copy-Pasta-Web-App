package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.BasicDBList;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SignInPostController {

	@PostMapping(path = "/SignIn", consumes = "application/x-www-form-urlencoded")
    public String SignInPost(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {

		try (MongoClient mongoClient = MongoClients.create("mongodb+srv://NathanYoder:i94kEgoX95YVnmOy@cluster0-5vsnf.mongodb.net/test?retryWrites=true&w=majority")) {

            MongoDatabase database = mongoClient.getDatabase("copypasta");
            MongoCollection<Document> usersCollection = database.getCollection("users");

            Document doc = new Document("username", username)
                .append("password", password);

            Document userFromDatabase = usersCollection.find(doc).first();

            if (userFromDatabase == null)
            {
                return "SignInFail";
            }
            
            model.addAttribute("wordList", userFromDatabase.get("words"));
            model.addAttribute("username", username);
            return "SignInSuccess";
        }
	}
}