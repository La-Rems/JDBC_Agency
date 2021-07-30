package agency.dao;

import agency.models.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO implements DAO<Actor, Integer> {

    private Connection connection;

    public ActorDAO(Connection connection){
        this.connection = connection;
    }


    @Override
    public List<Actor> getAll() throws SQLException {
        Actor actor = null;
        String query = "select * from actor";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Actor> actors = new ArrayList<>();

        while(rs.next()){
            actor = new Actor(rs.getString("name"), rs.getInt("age"));
            actor.setId(rs.getInt("id"));
            actors.add(actor);
        }
        ps.close();
        return actors;
    }

    @Override
    public Actor getById(Integer id) throws SQLException {
        String query = "select * from actor where id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Actor actor = null;
        if(rs.next()){
            actor = new Actor(rs.getString("name"), rs.getInt("age"));
            actor.setId(rs.getInt(1));
        }
        ps.close();
        return actor;
    }

    @Override
    public Integer create(Actor actor) throws SQLException {
        String query = "insert into actor (name, age) values (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, actor.getName());
        ps.setInt(2, actor.getAge());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        Integer generatedId = null;

        if(rs.next()){
            generatedId = rs.getInt(1);
        }
        ps.close();
        return generatedId;
    }

    @Override
    public void update(Actor actor) throws SQLException {
        String query = "update actor "
                + "set name = '" + actor.getName() + "', "
                + "age = '" + actor.getAge() + "' "
                + "where id = " + actor.getId();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Actor actor) throws SQLException {
        String query = "delete from actor where id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, actor.getId());
        ps.executeUpdate();
        ps.close();
    }
}
