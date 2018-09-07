package com.example.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoConnection {

	public static void main(String[] args) {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
		System.out.println("Connected Successfully");
		MongoDatabase database = client.getDatabase("UserDetails");
		System.out.println("Credential"+credential);
		MongoCollection<Document> collection = database.getCollection("UserDetails");
		System.out.println("collection"+collection);
		Document document = new Document("id",2)
				.append("name", "Viswa")
				.append("address", "Coimbatore");
		collection.insertOne(document);

		FindIterable<Document> find = collection.find();
		MongoCursor<Document> iterator = find.iterator();
		while(iterator.hasNext()) {
			Document next = iterator.next();
			System.out.println("Next Value"+next.toJson());
		}
		collection.deleteOne(Filters.eq("id",2));
		System.out.println(document);
		client.close();
	}
}
