package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateAccountPostController {

	private String username;
	private String password;
	private String email;
	private List<String> wordList = new ArrayList<String>();

	@PostMapping("/CreateAccount")
	public String CreateAccountPost(@RequestBody CreateAccountUser user) {
		System.out.println("This is working as well.");
		System.out.println(user);

		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();

        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://NathanYoder:i94kEgoX95YVnmOy@cluster0-5vsnf.mongodb.net/test?retryWrites=true&w=majority")) {

            MongoDatabase database = mongoClient.getDatabase("copypasta");
            MongoCollection<Document> usersCollection = database.getCollection("users");

            Document doc = new Document("username", username)
                .append("password", password)
                .append("email", email)
                .append("words", wordList);

            usersCollection.insertOne(doc);
        }
        return "SignIn";
	}
}