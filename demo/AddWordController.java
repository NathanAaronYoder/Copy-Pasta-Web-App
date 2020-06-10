package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


@Controller
public class AddWordController {

	@PostMapping("/AddWord")
    @ResponseBody
	public Map<String, String> SignInPost(@RequestBody CopyPastaWord copyPastaWord) {

        String word = copyPastaWord.getWord();
        String username = copyPastaWord.getUsername();

		try (MongoClient mongoClient = MongoClients.create("mongodb+srv://NathanYoder:i94kEgoX95YVnmOy@cluster0-5vsnf.mongodb.net/test?retryWrites=true&w=majority")) {

            MongoDatabase database = mongoClient.getDatabase("copypasta");
            MongoCollection<Document> usersCollection = database.getCollection("users");

            Document doc = new Document("username", username);

            Document userFromDatabase = usersCollection.find(doc).first();

            List<String> newWordList = userFromDatabase.get("words", List.class);

            newWordList.add(word);

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.append("username", username);

            BasicDBObject updateQuery = new BasicDBObject();
            updateQuery.append("$set",
            new BasicDBObject().append("words", newWordList));

            usersCollection.updateOne(searchQuery, updateQuery);

            HashMap<String, String> map = new HashMap<>();
            map.put("addWord", "success");
            return map;
        }
	}
}