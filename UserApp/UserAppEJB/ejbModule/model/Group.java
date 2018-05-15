package model;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

/**
 * @author Nikola
 *
 */
@Entity
@Indexes(@Index(fields = { @Field("name") }, options = @IndexOptions(unique = true)))
public class Group {

	@Id
	private ObjectId id;
	private String name;
	private ArrayList<String> members;

	public Group() {
	}

	public Group(ObjectId id, String name, ArrayList<String> members) {
		super();
		this.id = id;
		this.name = name;
		this.members = members;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String string) {
		this.name = string;
	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}

	public void addMember(String username) {
		members.add(username);
	}
	
	public void removeMember(String username) {
		members.remove(username);
	}

}
