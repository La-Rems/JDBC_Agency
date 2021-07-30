package agency.dao;

import agency.models.Actor;
import agency.models.Cast;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CastDAO implements DAO<Cast, Integer>{

    private Connection connection;

    public CastDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Cast> getAll() throws SQLException {
        Cast cast = null;
        String query = "select * from cast";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Cast> casts = new ArrayList<>();

        while(rs.next()){
            cast = new Cast(rs.getString("appreciation"), rs.getInt("score"), rs.getInt("actor_id"));
            cast.setId(rs.getInt("id"));
            casts.add(cast);
        }
        ps.close();
        return casts;
    }

    @Override
    public Cast getById(Integer id) throws SQLException {
        String query = "select * from cast where id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Cast cast = null;
        if(rs.next()){
            cast = new Cast(rs.getString("appreciation"), rs.getInt("score"), rs.getInt("actor_id"));
            cast.setId(rs.getInt(1));
        }
        ps.close();
        return cast;
    }

    @Override
    public Integer create(Cast cast) throws SQLException {
        String query = "insert into cast (appreciation, score, actor_id) values (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, cast.getAppreciation());
        ps.setInt(2, cast.getScore());
        ps.setInt(3, cast.getIdActor());
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
    public void update(Cast cast) throws SQLException {
        String query = "update cast "
                + "set appreciation = '" + cast.getAppreciation() + "', "
                + "score = '" + cast.getScore() + "' "
                + "where id = " + cast.getId();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Cast cast) throws SQLException {
        String query = "delete from cast where id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, cast.getId());
        ps.executeUpdate();
        ps.close();
    }
}
